package EveryMonsterPlayCard.intent2card.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.integration.IntentToCardIntegration;
import EveryMonsterPlayCard.intent2card.service.IntentToCardService;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 意图-卡牌转换系统测试类
 * 用于测试和验证意图-卡牌转换系统的功能
 */
public class IntentToCardSystemTest {
    
    // 测试结果统计
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    /**
     * 运行所有测试
     */
    public static void runAllTests() {
        Hpr.info("开始运行意图-卡牌转换系统测试...");
        
        // 重置测试统计
        totalTests = 0;
        passedTests = 0;
        failedTests = 0;
        
        // 运行各项测试
        testBasicIntentConversion();
        testComplexIntentConversion();
        testCompositeIntentConversion();
        testServiceIntegration();
        testCachePerformance();
        testValidationSystem();
        testOptimizationSystem();
        testConfigurationSystem();
        
        // 输出测试结果
        printTestResults();
    }
    
    /**
     * 测试基本意图转换
     */
    private static void testBasicIntentConversion() {
        Hpr.info("测试基本意图转换...");
        
        // 测试攻击意图
        testIntentConversion("攻击意图测试", new MonsterIntent(IntentType.ATTACK, 6));
        
        // 测试防御意图
        testIntentConversion("防御意图测试", new MonsterIntent(IntentType.DEFEND, 5));
        
        // 测试增益意图
        testIntentConversion("增益意图测试", new MonsterIntent(IntentType.BUFF, 2));
        
        // 测试减益意图
        testIntentConversion("减益意图测试", new MonsterIntent(IntentType.DEBUFF, 2));
        
        // 测试强力攻击意图
        testIntentConversion("强力攻击意图测试", new MonsterIntent(IntentType.STRONG, 12));
        
        // 测试逃跑意图
        testIntentConversion("逃跑意图测试", new MonsterIntent(IntentType.ESCAPE, 0));
    }
    
    /**
     * 测试复杂意图转换
     */
    private static void testComplexIntentConversion() {
        Hpr.info("测试复杂意图转换...");
        
        // 测试高伤害攻击意图
        MonsterIntent highDamageAttack = new MonsterIntent(IntentType.ATTACK, 25);
        highDamageAttack.setProperty("specialEffect", "高额伤害");
        testIntentConversion("高伤害攻击意图测试", highDamageAttack);
        
        // 测试多重效果意图
        MonsterIntent multiEffect = new MonsterIntent(IntentType.ATTACK, 10);
        multiEffect.setProperty("block", 5);
        multiEffect.setProperty("magicNumber", 2);
        testIntentConversion("多重效果意图测试", multiEffect);
        
        // 测试特殊属性意图
        MonsterIntent specialAttributes = new MonsterIntent(IntentType.BUFF, 3);
        specialAttributes.setProperty("duration", 3);
        specialAttributes.setProperty("stackable", true);
        testIntentConversion("特殊属性意图测试", specialAttributes);
    }
    
    /**
     * 测试复合意图转换
     */
    private static void testCompositeIntentConversion() {
        Hpr.info("测试复合意图转换...");
        
        // 测试攻击+防御复合意图
        MonsterIntent attackDefend = new MonsterIntent(IntentType.ATTACK, 8);
        attackDefend.setProperty("isComposite", true);
        attackDefend.setProperty("secondaryIntent", "DEFEND");
        attackDefend.setProperty("secondaryAmount", 5);
        testIntentConversion("攻击+防御复合意图测试", attackDefend);
        
        // 测试攻击+增益复合意图
        MonsterIntent attackBuff = new MonsterIntent(IntentType.ATTACK, 6);
        attackBuff.setProperty("isComposite", true);
        attackBuff.setProperty("secondaryIntent", "BUFF");
        attackBuff.setProperty("secondaryAmount", 2);
        testIntentConversion("攻击+增益复合意图测试", attackBuff);
        
        // 测试防御+增益复合意图
        MonsterIntent defendBuff = new MonsterIntent(IntentType.DEFEND, 8);
        defendBuff.setProperty("isComposite", true);
        defendBuff.setProperty("secondaryIntent", "BUFF");
        defendBuff.setProperty("secondaryAmount", 2);
        testIntentConversion("防御+增益复合意图测试", defendBuff);
    }
    
    /**
     * 测试服务集成
     */
    private static void testServiceIntegration() {
        Hpr.info("测试服务集成...");
        
        try {
            // 测试意图-卡牌转换服务
            IntentToCardService service = IntentToCardService.getInstance();
            boolean serviceAvailable = (service != null);
            runTest("意图-卡牌转换服务可用性测试", serviceAvailable);
            
            // 测试集成系统
            IntentToCardIntegration integration = IntentToCardIntegration.getInstance();
            boolean integrationAvailable = (integration != null);
            runTest("意图-卡牌集成系统可用性测试", integrationAvailable);
            
            // 测试服务配置
            if (serviceAvailable) {
                Map<String, Object> serviceStatus = service.getServiceStatus();
                boolean serviceStatusAvailable = (serviceStatus != null && !serviceStatus.isEmpty());
                runTest("服务状态获取测试", serviceStatusAvailable);
            }
            
            // 测试集成配置
            if (integrationAvailable) {
                Map<String, Object> integrationStats = integration.getIntegrationStats();
                boolean integrationStatsAvailable = (integrationStats != null && !integrationStats.isEmpty());
                runTest("集成统计获取测试", integrationStatsAvailable);
            }
            
        } catch (Exception e) {
            runTest("服务集成异常测试", false);
            Hpr.info("服务集成测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试缓存性能
     */
    private static void testCachePerformance() {
        Hpr.info("测试缓存性能...");
        
        try {
            IntentToCardService service = IntentToCardService.getInstance();
            
            // 创建测试意图
            MonsterIntent testIntent = new MonsterIntent(IntentType.ATTACK, 10);
            
            // 第一次转换（无缓存）
            long startTime = System.currentTimeMillis();
            AbstractCard card1 = service.convertIntentToCard(testIntent);
            long firstConversionTime = System.currentTimeMillis() - startTime;
            
            // 第二次转换（有缓存）
            startTime = System.currentTimeMillis();
            AbstractCard card2 = service.convertIntentToCard(testIntent);
            long secondConversionTime = System.currentTimeMillis() - startTime;
            
            // 验证结果一致性
            boolean cardsConsistent = (card1 != null && card2 != null && 
                                    card1.name.equals(card2.name));
            
            // 验证缓存效果
            boolean cacheEffective = (secondConversionTime < firstConversionTime);
            
            runTest("缓存结果一致性测试", cardsConsistent);
            runTest("缓存性能提升测试", cacheEffective);
            
            // 测试缓存统计
            Map<String, Object> cacheStats = service.getCacheStats();
            boolean cacheStatsAvailable = (cacheStats != null && !cacheStats.isEmpty());
            runTest("缓存统计获取测试", cacheStatsAvailable);
            
        } catch (Exception e) {
            runTest("缓存性能测试异常", false);
            Hpr.info("缓存性能测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试验证系统
     */
    private static void testValidationSystem() {
        Hpr.info("测试验证系统...");
        
        try {
            IntentToCardService service = IntentToCardService.getInstance();
            
            // 创建有效意图
            MonsterIntent validIntent = new MonsterIntent(IntentType.ATTACK, 8);
            AbstractCard validCard = service.convertIntentToCard(validIntent);
            boolean validCardGenerated = (validCard != null);
            runTest("有效卡牌生成测试", validCardGenerated);
            
            // 创建无效意图（极端值）
            MonsterIntent invalidIntent = new MonsterIntent(IntentType.ATTACK, 100);
            AbstractCard invalidCard = service.convertIntentToCard(invalidIntent);
            boolean invalidCardHandled = (invalidCard != null); // 系统应该能处理极端值
            runTest("无效意图处理测试", invalidCardHandled);
            
            // 测试验证统计
            Map<String, Object> validationStats = service.getValidationStats();
            boolean validationStatsAvailable = (validationStats != null && !validationStats.isEmpty());
            runTest("验证统计获取测试", validationStatsAvailable);
            
        } catch (Exception e) {
            runTest("验证系统测试异常", false);
            Hpr.info("验证系统测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试优化系统
     */
    private static void testOptimizationSystem() {
        Hpr.info("测试优化系统...");
        
        try {
            IntentToCardService service = IntentToCardService.getInstance();
            
            // 创建需要优化的意图
            MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, 7);
            AbstractCard card = service.convertIntentToCard(intent);
            
            boolean cardOptimized = (card != null);
            runTest("卡牌优化测试", cardOptimized);
            
            // 测试优化统计
            Map<String, Object> optimizationStats = service.getOptimizationStats();
            boolean optimizationStatsAvailable = (optimizationStats != null && !optimizationStats.isEmpty());
            runTest("优化统计获取测试", optimizationStatsAvailable);
            
        } catch (Exception e) {
            runTest("优化系统测试异常", false);
            Hpr.info("优化系统测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试配置系统
     */
    private static void testConfigurationSystem() {
        Hpr.info("测试配置系统...");
        
        try {
            // 测试配置获取
            IntentToCardIntegration integration = IntentToCardIntegration.getInstance();
            boolean integrationEnabled = integration.isIntegrationEnabled();
            runTest("集成配置获取测试", integrationEnabled);
            
            boolean dynamicGenerationEnabled = integration.isDynamicGenerationEnabled();
            runTest("动态生成配置获取测试", dynamicGenerationEnabled);
            
            boolean fallbackEnabled = integration.isFallbackToConfigEnabled();
            runTest("回退配置获取测试", fallbackEnabled);
            
            // 测试配置修改
            boolean originalIntegrationEnabled = integration.isIntegrationEnabled();
            integration.configureIntegration(false, true, true);
            boolean integrationDisabled = !integration.isIntegrationEnabled();
            runTest("集成配置修改测试", integrationDisabled);
            
            // 恢复原始配置
            integration.configureIntegration(originalIntegrationEnabled, true, true);
            boolean integrationRestored = (integration.isIntegrationEnabled() == originalIntegrationEnabled);
            runTest("集成配置恢复测试", integrationRestored);
            
        } catch (Exception e) {
            runTest("配置系统测试异常", false);
            Hpr.info("配置系统测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试单个意图转换
     */
    private static void testIntentConversion(String testName, MonsterIntent intent) {
        try {
            IntentToCardService service = IntentToCardService.getInstance();
            AbstractCard card = service.convertIntentToCard(intent);
            
            boolean testPassed = (card != null);
            runTest(testName, testPassed);
            
            if (testPassed) {
                Hpr.info("成功转换意图: " + intent + " -> 卡牌: " + card.name);
            } else {
                Hpr.info("转换意图失败: " + intent);
            }
            
        } catch (Exception e) {
            runTest(testName, false);
            Hpr.info("转换意图异常: " + intent + ", 错误: " + e.getMessage());
        }
    }
    
    /**
     * 运行单个测试
     */
    private static void runTest(String testName, boolean passed) {
        totalTests++;
        if (passed) {
            passedTests++;
            Hpr.info("✓ " + testName + " - 通过");
        } else {
            failedTests++;
            Hpr.info("✗ " + testName + " - 失败");
        }
    }
    
    /**
     * 打印测试结果
     */
    private static void printTestResults() {
        Hpr.info("========== 测试结果 ==========");
        Hpr.info("总测试数: " + totalTests);
        Hpr.info("通过测试: " + passedTests);
        Hpr.info("失败测试: " + failedTests);
        
        if (totalTests > 0) {
            double passRate = (double) passedTests / totalTests * 100;
            Hpr.info("通过率: " + String.format("%.2f%%", passRate));
        }
        
        if (failedTests == 0) {
            Hpr.info("所有测试通过！意图-卡牌转换系统运行正常。");
        } else {
            Hpr.info("有测试失败，请检查系统实现。");
        }
        Hpr.info("==============================");
    }
    
    /**
     * 测试怪物卡牌生成
     */
    public static void testMonsterCardGeneration(AbstractMonster monster) {
        if (monster == null) {
            Hpr.info("无法测试空怪物的卡牌生成");
            return;
        }
        
        Hpr.info("测试怪物 " + monster.name + " 的卡牌生成...");
        
        try {
            IntentToCardIntegration integration = IntentToCardIntegration.getInstance();
            List<AbstractCard> cards = integration.generateCardsForMonster(monster);
            
            boolean testPassed = (cards != null && !cards.isEmpty());
            runTest("怪物卡牌生成测试: " + monster.name, testPassed);
            
            if (testPassed) {
                Hpr.info("为怪物 " + monster.name + " 生成了 " + cards.size() + " 张卡牌:");
                for (int i = 0; i < cards.size(); i++) {
                    AbstractCard card = cards.get(i);
                    Hpr.info("  [" + i + "] " + card.name + " (费用: " + card.cost + 
                              ", 类型: " + card.type + ")");
                }
            } else {
                Hpr.info("为怪物 " + monster.name + " 生成卡牌失败");
            }
            
        } catch (Exception e) {
            runTest("怪物卡牌生成测试: " + monster.name, false);
            Hpr.info("怪物卡牌生成异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试系统性能
     */
    public static void testSystemPerformance() {
        Hpr.info("测试系统性能...");
        
        try {
            IntentToCardService service = IntentToCardService.getInstance();
            
            // 创建测试意图列表
            List<MonsterIntent> testIntents = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                IntentType[] types = {IntentType.ATTACK, IntentType.DEFEND, IntentType.BUFF, IntentType.DEBUFF};
                IntentType type = types[i % types.length];
                int amount = 5 + (i % 10);
                testIntents.add(new MonsterIntent(type, amount));
            }
            
            // 测试批量转换性能
            long startTime = System.currentTimeMillis();
            int successCount = 0;
            for (MonsterIntent intent : testIntents) {
                AbstractCard card = service.convertIntentToCard(intent);
                if (card != null) {
                    successCount++;
                }
            }
            long totalTime = System.currentTimeMillis() - startTime;
            
            boolean performanceAcceptable = (totalTime < 5000); // 5秒内完成
            boolean successRateAcceptable = (successCount >= testIntents.size() * 0.9); // 90%成功率
            
            runTest("批量转换性能测试", performanceAcceptable);
            runTest("批量转换成功率测试", successRateAcceptable);
            
            Hpr.info("批量转换性能: " + testIntents.size() + " 个意图，耗时 " + totalTime + "ms");
            Hpr.info("批量转换成功率: " + successCount + "/" + testIntents.size() + 
                      " (" + String.format("%.2f%%", (double) successCount / testIntents.size() * 100) + ")");
            
        } catch (Exception e) {
            runTest("系统性能测试异常", false);
            Hpr.info("系统性能测试异常: " + e.getMessage());
        }
    }
}