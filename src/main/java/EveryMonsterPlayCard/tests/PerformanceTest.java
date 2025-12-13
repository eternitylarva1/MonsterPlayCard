package EveryMonsterPlayCard.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Flex;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

import EveryMonsterPlayCard.conversion.analyzer.SimulatedBlockAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedDamageAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedDrawCardAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedGainEnergyAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedPowerAction;
import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.service.ActionConversionService;
import EveryMonsterPlayCard.service.ActionConversionServiceFactory;

/**
 * 性能测试类
 * 测试动作转换系统的性能
 */
public class PerformanceTest {
    
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 1000;
    private static final int LARGE_SEQUENCE_SIZE = 100;
    
    /**
     * 运行所有性能测试
     */
    public static void runAllPerformanceTests() {
        System.out.println("=== 开始性能测试 ===");
        
        // 测试单个动作转换性能
        testSingleActionPerformance();
        
        // 测试动作序列转换性能
        testActionSequencePerformance();
        
        // 测试卡牌动作转换性能
        testCardActionPerformance();
        
        // 测试服务层性能
        testServiceLayerPerformance();
        
        // 测试内存使用
        testMemoryUsage();
        
        System.out.println("=== 性能测试完成 ===");
    }
    
    /**
     * 测试单个动作转换性能
     */
    private static void testSingleActionPerformance() {
        System.out.println("\n--- 测试单个动作转换性能 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        Random random = new Random();
        
        // 预热
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            SimulatedDamageAction action = new SimulatedDamageAction(random.nextInt(20) + 1);
            converter.convertAction(action);
        }
        
        // 测试伤害动作转换
        long startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SimulatedDamageAction action = new SimulatedDamageAction(random.nextInt(20) + 1);
            converter.convertAction(action);
        }
        long endTime = System.nanoTime();
        long damageActionTime = (endTime - startTime) / 1_000_000; // 转换为毫秒
        
        // 测试格挡动作转换
        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SimulatedBlockAction action = new SimulatedBlockAction(random.nextInt(15) + 1);
            converter.convertAction(action);
        }
        endTime = System.nanoTime();
        long blockActionTime = (endTime - startTime) / 1_000_000;
        
        // 测试能力动作转换
        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SimulatedPowerAction action = new SimulatedPowerAction("TestPower", random.nextInt(5) + 1, random.nextBoolean());
            converter.convertAction(action);
        }
        endTime = System.nanoTime();
        long powerActionTime = (endTime - startTime) / 1_000_000;
        
        // 输出结果
        System.out.println("伤害动作转换平均时间: " + (damageActionTime / (double)TEST_ITERATIONS) + " ms");
        System.out.println("格挡动作转换平均时间: " + (blockActionTime / (double)TEST_ITERATIONS) + " ms");
        System.out.println("能力动作转换平均时间: " + (powerActionTime / (double)TEST_ITERATIONS) + " ms");
        System.out.println("总转换次数: " + (TEST_ITERATIONS * 3));
        System.out.println("总耗时: " + (damageActionTime + blockActionTime + powerActionTime) + " ms");
    }
    
    /**
     * 测试动作序列转换性能
     */
    private static void testActionSequencePerformance() {
        System.out.println("\n--- 测试动作序列转换性能 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        Random random = new Random();
        
        // 预热
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            List<AbstractGameAction> actions = createRandomActionSequence(random, 10);
            converter.convertActionSequence(actions);
        }
        
        // 测试小序列转换
        long startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            List<AbstractGameAction> actions = createRandomActionSequence(random, 5);
            converter.convertActionSequence(actions);
        }
        long endTime = System.nanoTime();
        long smallSequenceTime = (endTime - startTime) / 1_000_000;
        
        // 测试中等序列转换
        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS / 2; i++) {
            List<AbstractGameAction> actions = createRandomActionSequence(random, 20);
            converter.convertActionSequence(actions);
        }
        endTime = System.nanoTime();
        long mediumSequenceTime = (endTime - startTime) / 1_000_000;
        
        // 测试大序列转换
        startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS / 10; i++) {
            List<AbstractGameAction> actions = createRandomActionSequence(random, LARGE_SEQUENCE_SIZE);
            converter.convertActionSequence(actions);
        }
        endTime = System.nanoTime();
        long largeSequenceTime = (endTime - startTime) / 1_000_000;
        
        // 输出结果
        System.out.println("小序列(5个动作)转换平均时间: " + (smallSequenceTime / (double)TEST_ITERATIONS) + " ms");
        System.out.println("中等序列(20个动作)转换平均时间: " + (mediumSequenceTime / (double)(TEST_ITERATIONS / 2)) + " ms");
        System.out.println("大序列(" + LARGE_SEQUENCE_SIZE + "个动作)转换平均时间: " + (largeSequenceTime / (double)(TEST_ITERATIONS / 10)) + " ms");
    }
    
    /**
     * 测试卡牌动作转换性能
     */
    private static void testCardActionPerformance() {
        System.out.println("\n--- 测试卡牌动作转换性能 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        // 预热
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            AbstractCard card = new Strike_Red();
            converter.convertCardActions(card);
        }
        
        // 测试不同类型卡牌的转换性能
        AbstractCard[] testCards = {
            new Strike_Red(),
            new Defend_Red(),
            new Bash(),
            new Survivor(),
            new Flex()
        };
        
        long totalTime = 0;
        int totalConversions = 0;
        
        for (AbstractCard card : testCards) {
            long startTime = System.nanoTime();
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                converter.convertCardActions(card);
            }
            long endTime = System.nanoTime();
            long cardTime = (endTime - startTime) / 1_000_000;
            
            System.out.println(card.name + " 转换平均时间: " + (cardTime / (double)TEST_ITERATIONS) + " ms");
            totalTime += cardTime;
            totalConversions += TEST_ITERATIONS;
        }
        
        System.out.println("卡牌转换总平均时间: " + (totalTime / (double)totalConversions) + " ms");
    }
    
    /**
     * 测试服务层性能
     */
    private static void testServiceLayerPerformance() {
        System.out.println("\n--- 测试服务层性能 ---");
        
        ActionConversionService service = ActionConversionServiceFactory.getDefaultService();
        Random random = new Random();
        
        // 预热
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            SimulatedDamageAction action = new SimulatedDamageAction(random.nextInt(20) + 1);
            service.convertAction(action);
        }
        
        // 测试服务层转换性能
        long startTime = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SimulatedDamageAction action = new SimulatedDamageAction(random.nextInt(20) + 1);
            service.convertAction(action);
        }
        long endTime = System.nanoTime();
        long serviceTime = (endTime - startTime) / 1_000_000;
        
        System.out.println("服务层转换平均时间: " + (serviceTime / (double)TEST_ITERATIONS) + " ms");
        
        // 获取服务统计信息
        ActionConversionService.ServiceStatistics stats = service.getStatistics();
        System.out.println("服务统计信息: " + stats);
    }
    
    /**
     * 测试内存使用
     */
    private static void testMemoryUsage() {
        System.out.println("\n--- 测试内存使用 ---");
        
        Runtime runtime = Runtime.getRuntime();
        
        // 强制垃圾回收
        System.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // 创建大量转换对象
        UniversalActionConverter converter = new UniversalActionConverter();
        List<MonsterIntent> intents = new ArrayList<>();
        
        for (int i = 0; i < TEST_ITERATIONS * 10; i++) {
            SimulatedDamageAction action = new SimulatedDamageAction(i % 20 + 1);
            MonsterIntent intent = converter.convertAction(action);
            if (intent != null) {
                intents.add(intent);
            }
        }
        
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = afterMemory - beforeMemory;
        
        System.out.println("转换 " + intents.size() + " 个意图使用的内存: " + (memoryUsed / 1024) + " KB");
        System.out.println("平均每个意图使用的内存: " + (memoryUsed / intents.size()) + " bytes");
        
        // 清理
        intents.clear();
        System.gc();
    }
    
    /**
     * 创建随机动作序列
     */
    private static List<AbstractGameAction> createRandomActionSequence(Random random, int size) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            int actionType = random.nextInt(5);
            switch (actionType) {
                case 0:
                    actions.add(new SimulatedDamageAction(random.nextInt(20) + 1));
                    break;
                case 1:
                    actions.add(new SimulatedBlockAction(random.nextInt(15) + 1));
                    break;
                case 2:
                    actions.add(new SimulatedPowerAction("TestPower", random.nextInt(5) + 1, random.nextBoolean()));
                    break;
                case 3:
                    actions.add(new SimulatedDrawCardAction(random.nextInt(3) + 1));
                    break;
                case 4:
                    actions.add(new SimulatedGainEnergyAction(random.nextInt(3) + 1));
                    break;
            }
        }
        
        return actions;
    }
    
    /**
     * 主方法，用于运行性能测试
     */
    public static void main(String[] args) {
        runAllPerformanceTests();
    }
}