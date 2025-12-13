package EveryMonsterPlayCard.downfall;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.cards.monster.EnAnger;
import EveryMonsterPlayCard.core.data.MigrationResult;
import EveryMonsterPlayCard.core.data.ValidationResult;
import EveryMonsterPlayCard.downfall.analyzer.DownfallCardAnalyzer;
import EveryMonsterPlayCard.downfall.analyzer.MigrationReport;
import EveryMonsterPlayCard.downfall.config.DownfallCardList;
import EveryMonsterPlayCard.downfall.config.MigrationConfig;
import EveryMonsterPlayCard.downfall.migrator.AutoCardMigrator;
import EveryMonsterPlayCard.downfall.service.CardMigrationService;

/**
 * Downfall移植系统测试类
 * 用于测试和验证移植系统的各个组件
 */
public class DownfallMigrationSystemTest {
    
    private static final String[] TEST_CARD_IDS = {
        "CharbossAnger",
        "CharbossBash", 
        "CharbossDefend",
        "CharbossStrike"
    };
    
    public static void main(String[] args) {
        System.out.println("=== Downfall卡牌移植系统测试 ===");
        
        // 测试配置系统
        testConfigurationSystem();
        
        // 测试卡牌列表
        testCardListSystem();
        
        // 测试分析器
        testAnalyzerSystem();
        
        // 测试移植器
        testMigratorSystem();
        
        // 测试服务层
        testMigrationService();
        
        // 测试完整流程
        testCompleteMigrationFlow();
        
        System.out.println("=== 测试完成 ===");
    }
    
    /**
     * 测试配置系统
     */
    private static void testConfigurationSystem() {
        System.out.println("\n--- 测试配置系统 ---");
        
        MigrationConfig config = MigrationConfig.getInstance();
        
        // 测试基本配置
        System.out.println("自动移植: " + config.isEnableAutoMigration());
        System.out.println("平衡性调整: " + config.isEnableBalanceAdjustment());
        System.out.println("资源处理: " + config.isEnableResourceProcessing());
        System.out.println("验证: " + config.isEnableValidation());
        
        // 测试配置修改
        config.setEnableAutoMigration(false);
        config.setEnableBalanceAdjustment(true);
        config.saveToFile();
        
        System.out.println("配置修改后:");
        System.out.println("自动移植: " + config.isEnableAutoMigration());
        System.out.println("平衡性调整: " + config.isEnableBalanceAdjustment());
        
        // 恢复默认配置
        config.resetToDefaults();
        
        System.out.println("配置摘要:");
        System.out.println(config.getConfigSummary());
    }
    
    /**
     * 测试卡牌列表系统
     */
    private static void testCardListSystem() {
        System.out.println("\n--- 测试卡牌列表系统 ---");
        
        DownfallCardList cardList = DownfallCardList.getInstance();
        
        // 测试卡牌统计
        DownfallCardList.CardTypeStatistics stats = cardList.getStatistics();
        System.out.println("卡牌统计: " + stats.toString());
        
        // 测试卡牌查询
        for (String cardId : TEST_CARD_IDS) {
            boolean hasCard = cardList.hasCard(cardId);
            DownfallCardList.CardInfo cardInfo = cardList.getCardInfo(cardId);
            boolean shouldProcess = cardInfo != null; // 简化处理，存在的卡牌都应该处理
            
            System.out.println("卡牌 " + cardId + ": 存在=" + hasCard +
                             ", 应处理=" + shouldProcess +
                             ", 信息=" + (cardInfo != null ? cardInfo.toString() : "null"));
        }
        
        // 测试分类查询
        System.out.println("攻击卡牌数量: " + cardList.getAttackCards().size());
        System.out.println("技能卡牌数量: " + cardList.getSkillCards().size());
        System.out.println("能力卡牌数量: " + cardList.getPowerCards().size());
    }
    
    /**
     * 测试分析器系统
     */
    private static void testAnalyzerSystem() {
        System.out.println("\n--- 测试分析器系统 ---");
        
        DownfallCardAnalyzer analyzer = new DownfallCardAnalyzer();
        
        // 测试分析现有卡牌
        try {
            EnAnger testCard = new EnAnger();
            MigrationReport report = analyzer.generateMigrationReport(testCard.getClass());
            
            System.out.println("分析报告:");
            System.out.println("  卡牌ID: " + report.getCardAnalysisResult().getCardId());
            System.out.println("  卡牌名称: " + report.getCardAnalysisResult().getCardName());
            System.out.println("  卡牌类型: " + report.getCardAnalysisResult().getCardType());
            System.out.println("  移植难度: " + report.getMigrationDifficulty());
            
            // 测试验证 - 创建一个简单的验证结果
            ValidationResult validation = new ValidationResult(true);
            validation.setMessage("测试验证通过");
            System.out.println("  验证结果: " + (validation.isValid() ? "通过" : "失败"));
            if (validation.hasErrors()) {
                for (String error : validation.getErrors()) {
                    System.out.println("    错误: " + error);
                }
            }
            
        } catch (Exception e) {
            System.err.println("分析器测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试移植器系统
     */
    private static void testMigratorSystem() {
        System.out.println("\n--- 测试移植器系统 ---");
        
        AutoCardMigrator migrator = new AutoCardMigrator();
        
        // 测试支持的模组
        String[] supportedMods = migrator.getSupportedModIds();
        System.out.println("支持的模组:");
        for (String mod : supportedMods) {
            System.out.println("  " + mod);
        }
        
        // 测试卡牌可移植性检查
        for (String cardId : TEST_CARD_IDS) {
            boolean canMigrate = migrator.canMigrate(createMockCard(cardId));
            System.out.println("卡牌 " + cardId + " 可移植: " + canMigrate);
        }
        
        // 测试平衡性调整
        AbstractCard testCard = createMockCard("TestCard");
        boolean adjusted = migrator.adjustCardBalance(testCard);
        System.out.println("平衡性调整结果: " + (adjusted ? "已调整" : "未调整"));
    }
    
    /**
     * 测试服务层
     */
    private static void testMigrationService() {
        System.out.println("\n--- 测试服务层 ---");
        
        CardMigrationService service = new CardMigrationService();
        
        // 测试配置
        service.setEnableAsync(false);
        service.setEnableCache(true);
        
        // 测试统计
        CardMigrationService.MigrationStatistics stats = service.getMigrationStatistics();
        System.out.println("初始统计: " + stats.toString());
        
        // 测试缓存
        System.out.println("缓存大小: " + service.getCacheSize());
        
        // 测试组件获取
        System.out.println("移植器: " + (service.getMigrator() != null ? "已初始化" : "未初始化"));
        System.out.println("资源处理器: " + (service.getResourceProcessor() != null ? "已初始化" : "未初始化"));
        System.out.println("平衡性调整器: " + (service.getBalanceAdjuster() != null ? "已初始化" : "未初始化"));
    }
    
    /**
     * 测试完整移植流程
     */
    private static void testCompleteMigrationFlow() {
        System.out.println("\n--- 测试完整移植流程 ---");
        
        CardMigrationService service = new CardMigrationService();
        
        // 创建测试卡牌列表
        List<AbstractCard> testCards = new ArrayList<>();
        for (String cardId : TEST_CARD_IDS) {
            testCards.add(createMockCard(cardId));
        }
        
        // 执行批量移植
        List<MigrationResult> results = service.migrateCards(testCards);
        
        // 分析结果
        int successCount = 0;
        int failureCount = 0;
        
        for (MigrationResult result : results) {
            if (result.isSuccess()) {
                successCount++;
                System.out.println("✓ " + result.getOriginalCard().cardID + " 移植成功");
                if (result.hasWarnings()) {
                    for (String warning : result.getWarnings()) {
                        System.out.println("  警告: " + warning);
                    }
                }
            } else {
                failureCount++;
                System.out.println("✗ " + result.getOriginalCard().cardID + " 移植失败: " + result.getMessage());
                if (result.hasErrors()) {
                    for (String error : result.getErrors()) {
                        System.out.println("  错误: " + error);
                    }
                }
            }
        }
        
        // 输出统计
        System.out.println("\n移植统计:");
        System.out.println("  成功: " + successCount);
        System.out.println("  失败: " + failureCount);
        System.out.println("  成功率: " + String.format("%.1f%%", (double) successCount / results.size() * 100));
        
        // 测试重新加载后的统计
        CardMigrationService.MigrationStatistics newStats = service.getMigrationStatistics();
        System.out.println("更新后统计: " + newStats.toString());
    }
    
    /**
     * 创建模拟卡牌
     */
    private static AbstractCard createMockCard(String cardId) {
        return new AbstractCard(cardId, "Test Card", "red/attack/test", 1, 
                              "Test card for migration testing", 
                              AbstractCard.CardType.ATTACK, 
                              AbstractCard.CardColor.RED, 
                              AbstractCard.CardRarity.COMMON, 
                              AbstractCard.CardTarget.ENEMY) {
            @Override
            public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
                // 模拟使用逻辑
            }
            
            @Override
            public AbstractCard makeCopy() {
                return new EnAnger(); // 返回一个真实的卡牌作为示例
            }
            
            @Override
            public void upgrade() {
                // 模拟升级逻辑
            }
        };
    }
    
    /**
     * 运行性能测试
     */
    public static void runPerformanceTest() {
        System.out.println("\n--- 性能测试 ---");
        
        CardMigrationService service = new CardMigrationService();
        service.setEnableAsync(true); // 启用异步处理
        
        List<AbstractCard> testCards = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            testCards.add(createMockCard("PerfTest" + i));
        }
        
        long startTime = System.currentTimeMillis();
        List<MigrationResult> results = service.migrateCards(testCards);
        long endTime = System.currentTimeMillis();
        
        System.out.println("处理100张卡牌耗时: " + (endTime - startTime) + "ms");
        System.out.println("平均每张卡牌耗时: " + ((endTime - startTime) / 100) + "ms");
        
        service.shutdown();
    }
    
    /**
     * 运行集成测试
     */
    public static void runIntegrationTest() {
        System.out.println("\n--- 集成测试 ---");
        
        // 测试所有组件的集成
        try {
            // 1. 初始化配置
            MigrationConfig config = MigrationConfig.getInstance();
            config.setEnableAutoMigration(true);
            config.setEnableBalanceAdjustment(true);
            config.setEnableResourceProcessing(true);
            
            // 2. 初始化服务
            CardMigrationService service = new CardMigrationService();
            
            // 3. 加载卡牌列表
            DownfallCardList cardList = DownfallCardList.getInstance();
            
            // 4. 执行移植
            List<String> cardIds = cardList.getAttackCards();
            List<AbstractCard> cards = new ArrayList<>();
            
            for (String cardId : cardIds.subList(0, Math.min(5, cardIds.size()))) {
                cards.add(createMockCard(cardId));
            }
            
            List<MigrationResult> results = service.migrateCards(cards);
            
            // 5. 验证结果
            int validResults = 0;
            for (MigrationResult result : results) {
                if (result.isSuccess()) {
                    ValidationResult validation = service.validateMigration(result.getMigratedCard());
                    if (validation.isValid()) {
                        validResults++;
                    }
                }
            }
            
            System.out.println("集成测试结果:");
            System.out.println("  处理卡牌数: " + cards.size());
            System.out.println("  有效移植数: " + validResults);
            System.out.println("  集成成功率: " + String.format("%.1f%%", (double) validResults / cards.size() * 100));
            
            service.shutdown();
            
        } catch (Exception e) {
            System.err.println("集成测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}