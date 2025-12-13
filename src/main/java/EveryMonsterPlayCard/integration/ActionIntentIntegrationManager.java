package EveryMonsterPlayCard.integration;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;
import EveryMonsterPlayCard.service.ActionConversionService;
import EveryMonsterPlayCard.service.ActionConversionServiceFactory;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 动作-意图集成管理器
 * 负责将动作转换系统集成到现有的MonsterPlayCard系统中
 */
public class ActionIntentIntegrationManager {
    
    // 单例实例
    private static ActionIntentIntegrationManager instance = null;
    
    // 转换服务
    private ActionConversionService conversionService;
    
    // 动作分析器
    private ActionAnalyzer actionAnalyzer;
    
    // 通用转换器
    private UniversalActionConverter universalConverter;
    
    // 是否启用集成
    private boolean integrationEnabled = true;
    
    private ActionIntentIntegrationManager() {
        initializeConversionSystem();
    }
    
    /**
     * 获取单例实例
     */
    public static ActionIntentIntegrationManager getInstance() {
        if (instance == null) {
            instance = new ActionIntentIntegrationManager();
        }
        return instance;
    }
    
    /**
     * 初始化转换系统
     */
    private void initializeConversionSystem() {
        try {
            // 创建转换服务
            conversionService = ActionConversionServiceFactory.getDefaultService();
            
            // 创建动作分析器
            actionAnalyzer = new ActionAnalyzer();
            
            // 创建通用转换器
            universalConverter = new UniversalActionConverter();
            
            Hpr.info("动作-意图转换系统初始化完成");
            
        } catch (Exception e) {
            Hpr.info("动作-意图转换系统初始化失败: " + e.getMessage());
            integrationEnabled = false;
        }
    }
    
    /**
     * 处理怪物卡牌动作转换
     * 在怪物出牌时自动调用，将卡牌动作转换为意图
     * 
     * @param monster 怪物实例
     * @param card 出牌的卡牌
     * @param actions 卡牌执行的动作列表
     */
    public void processMonsterCardActions(AbstractMonster monster, AbstractCard card, List<AbstractGameAction> actions) {
        if (!integrationEnabled || conversionService == null) {
            return;
        }
        
        try {
            // 转换动作为意图
            List<MonsterIntent> intents = convertActionsToIntents(actions);
            
            if (intents != null && !intents.isEmpty()) {
                // 应用意图到怪物
                applyIntentsToMonster(monster, intents);
                
                Hpr.info("怪物 " + monster.name + " 出牌 " + card.name + " 转换为 " + intents.size() + " 个意图");
            } else {
                Hpr.info("怪物 " + monster.name + " 出牌 " + card.name + " 未生成意图");
            }
            
        } catch (Exception e) {
            Hpr.info("处理怪物卡牌动作转换时出错: " + e.getMessage());
        }
    }
    
    /**
     * 处理怪物卡牌动作转换（简化版，只处理卡牌）
     * 
     * @param monster 怪物实例
     * @param card 出牌的卡牌
     */
    public void processMonsterCard(AbstractMonster monster, AbstractCard card) {
        if (!integrationEnabled || universalConverter == null) {
            return;
        }
        
        try {
            // 直接转换卡牌为意图
            List<MonsterIntent> intents = universalConverter.convertCardActions(card);
            
            if (intents != null && !intents.isEmpty()) {
                // 应用意图到怪物
                applyIntentsToMonster(monster, intents);
                
                Hpr.info("怪物 " + monster.name + " 出牌 " + card.name + " 转换为 " + intents.size() + " 个意图");
            } else {
                Hpr.info("怪物 " + monster.name + " 出牌 " + card.name + " 未生成意图");
            }
            
        } catch (Exception e) {
            Hpr.info("处理怪物卡牌转换时出错: " + e.getMessage());
        }
    }
    
    /**
     * 转换动作列表为意图列表
     * 
     * @param actions 动作列表
     * @return 意图列表
     */
    private List<MonsterIntent> convertActionsToIntents(List<AbstractGameAction> actions) {
        if (actions == null || actions.isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            // 使用转换服务转换动作序列
            ActionConversionService.ServiceResult<List<MonsterIntent>> result = 
                conversionService.convertActionSequence(actions);
            
            if (result.isSuccess()) {
                return result.getResult();
            } else {
                Hpr.info("动作序列转换失败: " + result.getErrorMessage());
                return new ArrayList<>();
            }
            
        } catch (Exception e) {
            Hpr.info("转换动作序列时出错: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * 应用意图到怪物
     * 
     * @param monster 怪物实例
     * @param intents 意图列表
     */
    private void applyIntentsToMonster(AbstractMonster monster, List<MonsterIntent> intents) {
        if (monster == null || intents == null || intents.isEmpty()) {
            return;
        }
        
        try {
            // 获取怪物的CardPlayer
            MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
            
            if (cardPlayer != null) {
                // 将意图应用到怪物的意图系统
                for (MonsterIntent intent : intents) {
                    applySingleIntentToMonster(monster, intent);
                }
                
                // 更新怪物的显示
                updateMonsterIntentDisplay(monster, intents);
            }
            
        } catch (Exception e) {
            Hpr.info("应用意图到怪物时出错: " + e.getMessage());
        }
    }
    
    /**
     * 应用单个意图到怪物
     * 
     * @param monster 怪物实例
     * @param intent 意图
     */
    private void applySingleIntentToMonster(AbstractMonster monster, MonsterIntent intent) {
        if (monster == null || intent == null) {
            return;
        }
        
        try {
            // 根据意图类型设置怪物的意图
            switch (intent.getType()) {
                case ATTACK:
                    // 设置攻击意图
                    monster.setMove((byte) 0, AbstractMonster.Intent.ATTACK, intent.getAmount());
                    break;
                    
                case DEFEND:
                    // 设置防御意图
                    monster.setMove((byte) 0, AbstractMonster.Intent.DEFEND, intent.getAmount());
                    break;
                    
                case BUFF:
                    // 设置增益意图
                    monster.setMove((byte) 0, AbstractMonster.Intent.BUFF, intent.getAmount());
                    break;
                    
                case DEBUFF:
                    // 设置减益意图
                    monster.setMove((byte) 0, AbstractMonster.Intent.DEBUFF, intent.getAmount());
                    break;
                    
                case UNKNOWN:
                default:
                    // 未知意图，不设置
                    break;
            }
            
            Hpr.info("为怪物 " + monster.name + " 设置意图: " + intent.getType() + " (数量: " + intent.getAmount() + ")");
            
        } catch (Exception e) {
            Hpr.info("应用单个意图时出错: " + e.getMessage());
        }
    }
    
    /**
     * 更新怪物意图显示
     * 
     * @param monster 怪物实例
     * @param intents 意图列表
     */
    private void updateMonsterIntentDisplay(AbstractMonster monster, List<MonsterIntent> intents) {
        if (monster == null || intents == null || intents.isEmpty()) {
            return;
        }
        
        try {
            // 获取主要意图（第一个意图）
            MonsterIntent primaryIntent = intents.get(0);
            
            // 更新怪物的意图显示
            monster.createIntent();
            
            Hpr.info("更新怪物 " + monster.name + " 的意图显示: " + primaryIntent.getType());
            
        } catch (Exception e) {
            Hpr.info("更新怪物意图显示时出错: " + e.getMessage());
        }
    }
    
    /**
     * 分析怪物卡牌行为
     * 
     * @param card 卡牌实例
     * @return 分析结果
     */
    public String analyzeCardBehavior(AbstractCard card) {
        if (card == null || actionAnalyzer == null) {
            return "未知行为";
        }
        
        try {
            // 使用动作分析器分析卡牌
            EveryMonsterPlayCard.conversion.analyzer.ActionAnalysisResult result = 
                actionAnalyzer.analyzeCardActions(card);
            
            if (result != null) {
                return result.toString();
            } else {
                return "分析失败";
            }
            
        } catch (Exception e) {
            Hpr.info("分析卡牌行为时出错: " + e.getMessage());
            return "分析错误";
        }
    }
    
    /**
     * 预测怪物下一步意图
     * 
     * @param monster 怪物实例
     * @return 预测的意图
     */
    public MonsterIntent predictNextIntent(AbstractMonster monster) {
        if (monster == null || universalConverter == null) {
            return null;
        }
        
        try {
            // 获取怪物的CardPlayer
            MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
            
            if (cardPlayer != null && cardPlayer.getMonsterHand() != null && !cardPlayer.getMonsterHand().isEmpty()) {
                // 获取第一张可出的牌
                AbstractCard nextCard = cardPlayer.getMonsterHand().group.get(0);
                
                if (nextCard != null) {
                    // 转换卡牌为意图
                    List<MonsterIntent> intents = universalConverter.convertCardActions(nextCard);
                    
                    if (intents != null && !intents.isEmpty()) {
                        return intents.get(0); // 返回主要意图
                    }
                }
            }
            
            return null;
            
        } catch (Exception e) {
            Hpr.info("预测怪物下一步意图时出错: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 启用集成
     */
    public void enableIntegration() {
        integrationEnabled = true;
        Hpr.info("动作-意图集成已启用");
    }
    
    /**
     * 禁用集成
     */
    public void disableIntegration() {
        integrationEnabled = false;
        Hpr.info("动作-意图集成已禁用");
    }
    
    /**
     * 检查集成是否启用
     * 
     * @return 是否启用
     */
    public boolean isIntegrationEnabled() {
        return integrationEnabled;
    }
    
    /**
     * 获取转换统计信息
     * 
     * @return 统计信息
     */
    public String getConversionStatistics() {
        if (conversionService == null) {
            return "转换服务未初始化";
        }
        
        try {
            ActionConversionService.ServiceStatistics stats = conversionService.getStatistics();
            return stats.toString();
            
        } catch (Exception e) {
            return "获取统计信息失败: " + e.getMessage();
        }
    }
    
    /**
     * 重置集成系统
     */
    public void resetIntegration() {
        try {
            // 重新初始化转换系统
            initializeConversionSystem();
            
            Hpr.info("动作-意图集成系统已重置");
            
        } catch (Exception e) {
            Hpr.info("重置集成系统时出错: " + e.getMessage());
        }
    }
}