package EveryMonsterPlayCard.tests;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.cards.red.BattleTrance;
import com.megacrit.cardcrawl.cards.red.Berserk;
import com.megacrit.cardcrawl.cards.red.Bloodletting;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.cards.red.BodySlam;
import com.megacrit.cardcrawl.cards.red.BurningPact;
import com.megacrit.cardcrawl.cards.red.Carnage;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.cards.red.Combust;
import com.megacrit.cardcrawl.cards.red.Corruption;
import com.megacrit.cardcrawl.cards.red.DarkEmbrace;
import com.megacrit.cardcrawl.cards.red.DemonForm;
import com.megacrit.cardcrawl.cards.red.Disarm;
import com.megacrit.cardcrawl.cards.red.DoubleTap;
import com.megacrit.cardcrawl.cards.red.Entrench;
import com.megacrit.cardcrawl.cards.red.Evolve;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.cards.red.FlameBarrier;
import com.megacrit.cardcrawl.cards.red.GhostlyArmor;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.cards.red.Headbutt;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.cards.red.Hemokinesis;
import com.megacrit.cardcrawl.cards.red.Impervious;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.cards.red.Intimidate;
import com.megacrit.cardcrawl.cards.red.IronWave;
import com.megacrit.cardcrawl.cards.red.Juggernaut;
import com.megacrit.cardcrawl.cards.red.Metallicize;
import com.megacrit.cardcrawl.cards.red.Offering;
import com.megacrit.cardcrawl.cards.red.PommelStrike;
import com.megacrit.cardcrawl.cards.red.PowerThrough;
import com.megacrit.cardcrawl.cards.red.Rage;
import com.megacrit.cardcrawl.cards.red.RecklessCharge;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.cards.red.SecondWind;
import com.megacrit.cardcrawl.cards.red.SeeingRed;
import com.megacrit.cardcrawl.cards.red.Sentinel;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.cards.red.SpotWeakness;
import com.megacrit.cardcrawl.cards.red.ThunderClap;
import com.megacrit.cardcrawl.cards.red.TrueGrit;
import com.megacrit.cardcrawl.cards.red.TwinStrike;
import com.megacrit.cardcrawl.cards.red.Uppercut;
import com.megacrit.cardcrawl.cards.red.Warcry;
import com.megacrit.cardcrawl.cards.red.Whirlwind;
import com.megacrit.cardcrawl.cards.red.WildStrike;

import EveryMonsterPlayCard.conversion.analyzer.SimulatedBlockAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedDamageAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedDrawCardAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedGainEnergyAction;
import EveryMonsterPlayCard.conversion.analyzer.SimulatedPowerAction;
import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.service.ActionConversionService;
import EveryMonsterPlayCard.service.ActionConversionServiceFactory;
import EveryMonsterPlayCard.validation.ConversionValidator;

/**
 * 集成测试类
 * 测试整个动作转换系统的集成功能
 */
public class IntegrationTest {
    
    /**
     * 运行所有集成测试
     */
    public static void runAllIntegrationTests() {
        System.out.println("=== 开始集成测试 ===");
        
        // 测试完整转换流程
        testCompleteConversionFlow();
        
        // 测试复杂卡牌转换
        testComplexCardConversion();
        
        // 测试多动作序列转换
        testMultiActionSequenceConversion();
        
        // 测试配置系统集成
        testConfigurationIntegration();
        
        // 测试事件系统集成
        testEventIntegration();
        
        // 测试错误处理
        testErrorHandling();
        
        System.out.println("=== 集成测试完成 ===");
    }
    
    /**
     * 测试完整转换流程
     */
    private static void testCompleteConversionFlow() {
        System.out.println("\n--- 测试完整转换流程 ---");
        
        try {
            // 创建服务
            ActionConversionService service = ActionConversionServiceFactory.getDefaultService();
            
            // 创建测试动作
            SimulatedDamageAction damageAction = new SimulatedDamageAction(15);
            SimulatedBlockAction blockAction = new SimulatedBlockAction(8);
            SimulatedPowerAction powerAction = new SimulatedPowerAction("Strength", 3, true);
            
            // 转换单个动作
            ActionConversionService.ServiceResult<MonsterIntent> damageResult = service.convertAction(damageAction);
            ActionConversionService.ServiceResult<MonsterIntent> blockResult = service.convertAction(blockAction);
            ActionConversionService.ServiceResult<MonsterIntent> powerResult = service.convertAction(powerAction);
            
            // 验证结果
            boolean allSuccess = damageResult.isSuccess() && blockResult.isSuccess() && powerResult.isSuccess();
            
            if (allSuccess) {
                System.out.println("✓ 单个动作转换全部成功");
                System.out.println("  伤害意图: " + damageResult.getResult());
                System.out.println("  格挡意图: " + blockResult.getResult());
                System.out.println("  能力意图: " + powerResult.getResult());
            } else {
                System.out.println("✗ 单个动作转换失败");
                if (!damageResult.isSuccess()) System.out.println("  伤害转换错误: " + damageResult.getErrorMessage());
                if (!blockResult.isSuccess()) System.out.println("  格挡转换错误: " + blockResult.getErrorMessage());
                if (!powerResult.isSuccess()) System.out.println("  能力转换错误: " + powerResult.getErrorMessage());
            }
            
            // 转换动作序列
            List<AbstractGameAction> actions = new ArrayList<>();
            actions.add(damageAction);
            actions.add(blockAction);
            actions.add(powerAction);
            
            ActionConversionService.ServiceResult<List<MonsterIntent>> sequenceResult = service.convertActionSequence(actions);
            
            if (sequenceResult.isSuccess()) {
                System.out.println("✓ 动作序列转换成功，生成 " + sequenceResult.getResult().size() + " 个意图");
            } else {
                System.out.println("✗ 动作序列转换失败: " + sequenceResult.getErrorMessage());
            }
            
            // 获取统计信息
            ActionConversionService.ServiceStatistics stats = service.getStatistics();
            System.out.println("服务统计: " + stats);
            
        } catch (Exception e) {
            System.out.println("✗ 完整转换流程测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试复杂卡牌转换
     */
    private static void testComplexCardConversion() {
        System.out.println("\n--- 测试复杂卡牌转换 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        try {
            // 测试多效果卡牌
            AbstractCard[] complexCards = {
                new Cleave(),        // AOE伤害
                new Whirlwind(),     // 多目标伤害
                new TwinStrike(),    // 双重攻击
                new Armaments(),     // 攻击+格挡
                new FlameBarrier(), // 格挡+反伤
                new Inflame(),       // 力量提升
                new Hemokinesis(),   // 自伤+伤害
                new Disarm(),        // 减益
                new ShrugItOff(),    // 格挡+力量
                new TrueGrit(),      // 格挡+抽牌
                new BattleTrance(),  // 抽牌
                new Bloodletting(),  // 自伤+能量
                new Corruption(),    // 技能消耗
                new DemonForm(),     // 力量提升
                new Impervious(),    // 大量格挡
                new Entrench(),      // 格挡翻倍
                new Rage(),          // 怒气
                new Berserk(),       // 怒气+伤害
                new Combust(),       // 自伤+抽牌
                new Offering(),      // 自伤+能量+抽牌
                new DarkEmbrace(),   // 抽牌
                new Evolve(),        // 抽牌
                new GhostlyArmor(),  // 格挡
                new Metallicize(),   // 格挡
                new BurningPact(),   // 消耗+抽牌
                new Juggernaut(),    // 格挡+力量
                new PowerThrough(),  // 格挡+抽牌
                new SecondWind(),    // 格挡+抽牌
                new Sentinel(),      // 格挡+抽牌
                new Uppercut(),      // 伤害+减益
                new Warcry(),        // 抽牌
                new WildStrike(),    // 伤害+抽牌
                new Anger(),         // 伤害+抽牌
                new Bludgeon(),      // 伤害+格挡
                new BodySlam(),      // 格挡伤害
                new Carnage(),       // 大量伤害
                new DoubleTap(),     // 双重伤害
                new Feed(),          // 伤害+治疗
                new Havoc(),         // 随机卡牌效果
                new Headbutt(),      // 伤害+抽牌
                new HeavyBlade(),    // 力量加成伤害
                new IronWave(),      // 伤害+格挡
                new PommelStrike(),  // 伤害+抽牌
                new SearingBlow(),   // 升级伤害
                new ThunderClap(),   // AOE减益
                new RecklessCharge(),// 伤害+减益
                new SeeingRed(),     // 抽牌
                new SpotWeakness(),  // 伤害+力量
                new Intimidate()     // 减益
            };
            
            int successCount = 0;
            int totalCount = complexCards.length;
            
            for (AbstractCard card : complexCards) {
                try {
                    List<MonsterIntent> intents = converter.convertCardActions(card);
                    if (!intents.isEmpty()) {
                        successCount++;
                        System.out.println("✓ " + card.name + " 转换成功: " + intents.size() + " 个意图");
                    } else {
                        System.out.println("✗ " + card.name + " 转换失败: 无意图生成");
                    }
                } catch (Exception e) {
                    System.out.println("✗ " + card.name + " 转换异常: " + e.getMessage());
                }
            }
            
            System.out.println("复杂卡牌转换成功率: " + successCount + "/" + totalCount + " (" + 
                             (successCount * 100.0 / totalCount) + "%)");
            
        } catch (Exception e) {
            System.out.println("✗ 复杂卡牌转换测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试多动作序列转换
     */
    private static void testMultiActionSequenceConversion() {
        System.out.println("\n--- 测试多动作序列转换 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        try {
            // 创建复杂动作序列
            List<AbstractGameAction> complexSequence = new ArrayList<>();
            
            // 模拟一个复杂的战斗回合
            complexSequence.add(new SimulatedDamageAction(8));      // 攻击
            complexSequence.add(new SimulatedBlockAction(5));      // 格挡
            complexSequence.add(new SimulatedPowerAction("Strength", 2, true)); // 增益
            complexSequence.add(new SimulatedDrawCardAction(2));    // 抽牌
            complexSequence.add(new SimulatedGainEnergyAction(1));  // 获得能量
            complexSequence.add(new SimulatedDamageAction(12));     // 再次攻击
            complexSequence.add(new SimulatedPowerAction("Vulnerable", 1, false)); // 减益
            
            // 转换序列
            List<MonsterIntent> intents = converter.convertActionSequence(complexSequence);
            
            if (intents != null && !intents.isEmpty()) {
                System.out.println("✓ 复杂动作序列转换成功，生成 " + intents.size() + " 个意图:");
                for (int i = 0; i < intents.size(); i++) {
                    MonsterIntent intent = intents.get(i);
                    System.out.println("  意图 " + (i+1) + ": " + intent);
                }
            } else {
                System.out.println("✗ 复杂动作序列转换失败");
            }
            
            // 测试空序列
            List<AbstractGameAction> emptySequence = new ArrayList<>();
            List<MonsterIntent> emptyIntents = converter.convertActionSequence(emptySequence);
            
            if (emptyIntents != null && emptyIntents.isEmpty()) {
                System.out.println("✓ 空序列处理正确");
            } else {
                System.out.println("✗ 空序列处理错误");
            }
            
        } catch (Exception e) {
            System.out.println("✗ 多动作序列转换测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试配置系统集成
     */
    private static void testConfigurationIntegration() {
        System.out.println("\n--- 测试配置系统集成 ---");
        
        try {
            // 创建默认服务
            ActionConversionService service = ActionConversionServiceFactory.getDefaultService();
            
            // 测试默认配置是否生效
            SimulatedDamageAction action = new SimulatedDamageAction(10);
            ActionConversionService.ServiceResult<MonsterIntent> result = service.convertAction(action);
            
            if (result.isSuccess()) {
                System.out.println("✓ 默认配置服务转换成功: " + result.getResult());
            } else {
                System.out.println("✗ 默认配置服务转换失败: " + result.getErrorMessage());
            }
            
            // 测试配置管理器
            EveryMonsterPlayCard.config.ConversionConfigManager configManager = 
                EveryMonsterPlayCard.config.ConversionConfigManager.getDefault();
            
            // 设置一些配置
            configManager.setConfig("analyzer.enableDetailedAnalysis", true);
            configManager.setConfig("mapper.strictMode", false);
            configManager.setConfig("validator.enableWarnings", true);
            
            // 验证配置
            boolean detailedAnalysis = configManager.getConfig("analyzer.enableDetailedAnalysis", false);
            boolean strictMode = configManager.getConfig("mapper.strictMode", true);
            boolean enableWarnings = configManager.getConfig("validator.enableWarnings", false);
            
            if (detailedAnalysis && !strictMode && enableWarnings) {
                System.out.println("✓ 配置管理器工作正常");
            } else {
                System.out.println("✗ 配置管理器工作异常");
            }
            
        } catch (Exception e) {
            System.out.println("✗ 配置系统集成测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试事件系统集成
     */
    private static void testEventIntegration() {
        System.out.println("\n--- 测试事件系统集成 ---");
        
        try {
            // 创建事件监听器
            TestEventListener listener = new TestEventListener();
            
            // 注册监听器
            EveryMonsterPlayCard.core.events.EventBus eventBus = new EveryMonsterPlayCard.core.events.EventBus();
            eventBus.registerListener(EveryMonsterPlayCard.core.events.IntentConversionEvent.class, listener);
            
            // 创建转换器并执行转换
            UniversalActionConverter converter = new UniversalActionConverter();
            SimulatedDamageAction action = new SimulatedDamageAction(10);
            MonsterIntent intent = converter.convertAction(action);
            
            // 检查事件是否被触发
            if (listener.getEventCount() > 0) {
                System.out.println("✓ 事件系统集成成功，触发 " + listener.getEventCount() + " 个事件");
            } else {
                System.out.println("✗ 事件系统集成失败，未触发任何事件");
            }
            
            // 注销监听器
            eventBus.unregisterListener(EveryMonsterPlayCard.core.events.IntentConversionEvent.class, listener);
            
        } catch (Exception e) {
            System.out.println("✗ 事件系统集成测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试错误处理
     */
    private static void testErrorHandling() {
        System.out.println("\n--- 测试错误处理 ---");
        
        try {
            UniversalActionConverter converter = new UniversalActionConverter();
            ConversionValidator validator = new ConversionValidator();
            
            // 测试null动作
            MonsterIntent nullResult = converter.convertAction(null);
            if (nullResult == null) {
                System.out.println("✓ null动作处理正确");
            } else {
                System.out.println("✗ null动作处理错误");
            }
            
            // 测试无效动作
            SimulatedDamageAction invalidAction = new SimulatedDamageAction(-5);
            MonsterIntent invalidResult = converter.convertAction(invalidAction);
            
            // 验证无效转换
            EveryMonsterPlayCard.core.data.ValidationResult validationResult = 
                validator.validateConversion(invalidAction, invalidResult);
            
            if (!validationResult.isValid()) {
                System.out.println("✓ 无效动作验证正确，检测到 " + validationResult.getErrors().size() + " 个错误");
            } else {
                System.out.println("✗ 无效动作验证失败");
            }
            
            // 测试服务层错误处理
            ActionConversionService service = ActionConversionServiceFactory.getDefaultService();
            ActionConversionService.ServiceResult<MonsterIntent> errorResult = service.convertAction(null);
            
            if (!errorResult.isSuccess() && errorResult.getErrorMessage() != null) {
                System.out.println("✓ 服务层错误处理正确: " + errorResult.getErrorMessage());
            } else {
                System.out.println("✗ 服务层错误处理失败");
            }
            
        } catch (Exception e) {
            System.out.println("✗ 错误处理测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试事件监听器
     */
    private static class TestEventListener implements EveryMonsterPlayCard.core.interfaces.IEventListener {
        private int eventCount = 0;
        
        @Override
        public void handleEvent(EveryMonsterPlayCard.core.interfaces.IEvent event) {
            eventCount++;
            System.out.println("  收到事件: " + event.getClass().getSimpleName());
        }
        
        public int getEventCount() {
            return eventCount;
        }
    }
    
    /**
     * 主方法，用于运行集成测试
     */
    public static void main(String[] args) {
        runAllIntegrationTests();
    }
}