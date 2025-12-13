package EveryMonsterPlayCard.downfall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MigrationResult;
import EveryMonsterPlayCard.core.data.ValidationResult;
import EveryMonsterPlayCard.core.events.CardMigrationEvent;
import EveryMonsterPlayCard.core.interfaces.IEventBus;
import EveryMonsterPlayCard.downfall.migrator.AutoCardMigrator;

/**
 * 卡牌移植服务
 * 提供完整的卡牌移植服务，包括分析、移植、验证等
 */
public class CardMigrationService {
    
    private AutoCardMigrator migrator;
    private ResourceProcessor resourceProcessor;
    private BalanceAdjuster balanceAdjuster;
    private IEventBus eventBus;
    private ExecutorService executorService;
    private Map<String, MigrationResult> migrationCache;
    private boolean enableCache;
    private boolean enableAsync;
    
    public CardMigrationService() {
        this.migrator = new AutoCardMigrator();
        this.resourceProcessor = new ResourceProcessor();
        this.balanceAdjuster = new BalanceAdjuster();
        this.executorService = Executors.newFixedThreadPool(4);
        this.migrationCache = new HashMap<>();
        this.enableCache = true;
        this.enableAsync = false;
    }
    
    public CardMigrationService(IEventBus eventBus) {
        this();
        this.eventBus = eventBus;
    }
    
    /**
     * 移植单个卡牌
     */
    public MigrationResult migrateCard(AbstractCard card) {
        if (card == null) {
            MigrationResult result = new MigrationResult(false);
            result.setMessage("卡牌为空");
            result.addError("输入卡牌为null");
            return result;
        }
        
        // 检查缓存
        if (enableCache && migrationCache.containsKey(card.cardID)) {
            return migrationCache.get(card.cardID);
        }
        
        // 发送移植开始事件
        if (eventBus != null) {
            eventBus.publishEvent(new CardMigrationEvent(this, card, null, null));
        }
        
        MigrationResult result = migrator.migrateCard(card);
        
        // 处理资源
        if (result.isSuccess()) {
            processCardResources(card, result);
            
            // 调整平衡性
            if (result.getMigratedCard() != null) {
                boolean adjusted = balanceAdjuster.adjustCardBalance(result.getMigratedCard());
                if (adjusted) {
                    result.addWarning("已进行平衡性调整");
                }
            }
        }
        
        // 缓存结果
        if (enableCache) {
            migrationCache.put(card.cardID, result);
        }
        
        // 发送移植完成事件
        if (eventBus != null) {
            eventBus.publishEvent(new CardMigrationEvent(this, card, result.getMigratedCard(), result));
        }
        
        return result;
    }
    
    /**
     * 批量移植卡牌
     */
    public List<MigrationResult> migrateCards(List<AbstractCard> cards) {
        if (cards == null || cards.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<MigrationResult> results = new ArrayList<>();
        
        if (enableAsync) {
            // 异步移植
            List<Future<MigrationResult>> futures = new ArrayList<>();
            
            for (AbstractCard card : cards) {
                Future<MigrationResult> future = executorService.submit(() -> migrateCard(card));
                futures.add(future);
            }
            
            // 等待所有任务完成
            for (Future<MigrationResult> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    MigrationResult errorResult = new MigrationResult(false);
                    errorResult.setMessage("异步移植失败: " + e.getMessage());
                    errorResult.addError(e.getMessage());
                    results.add(errorResult);
                }
            }
        } else {
            // 同步移植
            for (AbstractCard card : cards) {
                results.add(migrateCard(card));
            }
        }
        
        return results;
    }
    
    /**
     * 异步移植卡牌
     */
    public CompletableFuture<MigrationResult> migrateCardAsync(AbstractCard card) {
        return CompletableFuture.supplyAsync(() -> migrateCard(card), executorService);
    }
    
    /**
     * 异步批量移植卡牌
     */
    public CompletableFuture<List<MigrationResult>> migrateCardsAsync(List<AbstractCard> cards) {
        return CompletableFuture.supplyAsync(() -> migrateCards(cards), executorService);
    }
    
    /**
     * 验证移植结果
     */
    public ValidationResult validateMigration(AbstractCard migratedCard) {
        return migrator.validateMigration(migratedCard);
    }
    
    /**
     * 重新移植卡牌
     */
    public MigrationResult remigrateCard(AbstractCard originalCard, AbstractCard previousMigratedCard) {
        // 清除缓存
        if (enableCache && originalCard.cardID != null) {
            migrationCache.remove(originalCard.cardID);
        }
        
        // 发送重新移植事件
        if (eventBus != null) {
            eventBus.publishEvent(new CardMigrationEvent(this, originalCard, null, null));
        }
        
        MigrationResult result = migrateCard(originalCard);
        
        // 合并之前的调整
        if (result.isSuccess() && previousMigratedCard != null) {
            mergePreviousAdjustments(result.getMigratedCard(), previousMigratedCard);
        }
        
        return result;
    }
    
    /**
     * 处理卡牌资源
     */
    private void processCardResources(AbstractCard originalCard, MigrationResult result) {
        try {
            // 处理图像资源
            resourceProcessor.processImageResources(originalCard, result);
            
            // 处理本地化资源
            resourceProcessor.processLocalizationResources(originalCard, result);
            
            // 处理音频资源
            resourceProcessor.processAudioResources(originalCard, result);
            
        } catch (Exception e) {
            result.addWarning("资源处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 合并之前的调整
     */
    private void mergePreviousAdjustments(AbstractCard newCard, AbstractCard previousCard) {
        if (newCard == null || previousCard == null) {
            return;
        }
        
        // 合并平衡性调整
        if (previousCard.baseDamage != newCard.baseDamage) {
            newCard.baseDamage = previousCard.baseDamage;
            newCard.damage = newCard.baseDamage;
        }
        
        if (previousCard.baseBlock != newCard.baseBlock) {
            newCard.baseBlock = previousCard.baseBlock;
            newCard.block = newCard.baseBlock;
        }
        
        if (previousCard.cost != newCard.cost) {
            newCard.cost = previousCard.cost;
            newCard.costForTurn = newCard.cost;
        }
    }
    
    /**
     * 获取移植统计信息
     */
    public MigrationStatistics getMigrationStatistics() {
        int total = migrationCache.size();
        int successful = 0;
        int failed = 0;
        
        for (MigrationResult result : migrationCache.values()) {
            if (result.isSuccess()) {
                successful++;
            } else {
                failed++;
            }
        }
        
        return new MigrationStatistics(total, successful, failed);
    }
    
    /**
     * 清除缓存
     */
    public void clearCache() {
        migrationCache.clear();
    }
    
    /**
     * 清除指定卡牌的缓存
     */
    public void clearCache(String cardId) {
        if (cardId != null) {
            migrationCache.remove(cardId);
        }
    }
    
    /**
     * 获取缓存大小
     */
    public int getCacheSize() {
        return migrationCache.size();
    }
    
    /**
     * 设置是否启用缓存
     */
    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }
    
    /**
     * 设置是否启用异步处理
     */
    public void setEnableAsync(boolean enableAsync) {
        this.enableAsync = enableAsync;
    }
    
    /**
     * 设置事件总线
     */
    public void setEventBus(IEventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    /**
     * 获取移植器
     */
    public AutoCardMigrator getMigrator() {
        return migrator;
    }
    
    /**
     * 获取资源处理器
     */
    public ResourceProcessor getResourceProcessor() {
        return resourceProcessor;
    }
    
    /**
     * 获取平衡性调整器
     */
    public BalanceAdjuster getBalanceAdjuster() {
        return balanceAdjuster;
    }
    
    /**
     * 关闭服务
     */
    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
    
    /**
     * 移植统计信息
     */
    public static class MigrationStatistics {
        private final int total;
        private final int successful;
        private final int failed;
        
        public MigrationStatistics(int total, int successful, int failed) {
            this.total = total;
            this.successful = successful;
            this.failed = failed;
        }
        
        public int getTotal() { return total; }
        public int getSuccessful() { return successful; }
        public int getFailed() { return failed; }
        public double getSuccessRate() { 
            return total > 0 ? (double) successful / total : 0.0; 
        }
        
        @Override
        public String toString() {
            return String.format("MigrationStatistics{total=%d, successful=%d, failed=%d, successRate=%.2f%%}", 
                               total, successful, failed, getSuccessRate() * 100);
        }
    }
}