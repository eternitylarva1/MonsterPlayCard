package EveryMonsterPlayCard.tests;

import java.util.ArrayList;
import java.util.List;

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
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.service.ActionConversionService;
import EveryMonsterPlayCard.service.ActionConversionServiceFactory;
import EveryMonsterPlayCard.validation.ConversionValidator;

/**
 * 动作转换测试类
 * 测试动作到意图的转换功能
 */
public class ActionConversionTest {
    
    /**
     * 运行所有测试
     */
    public static void runAllTests() {
        System.out.println("=== 开始动作转换测试 ===");
        
        // 测试单个动作转换
        testSingleActionConversion();
        
        // 测试动作序列转换
        testActionSequenceConversion();
        
        // 测试卡牌动作转换
        testCardActionConversion();
        
        // 测试转换验证
        testConversionValidation();
        
        // 测试服务层
        testServiceLayer();
        
        System.out.println("=== 动作转换测试完成 ===");
    }
    
    /**
     * 测试单个动作转换
     */
    private static void testSingleActionConversion() {
        System.out.println("\n--- 测试单个动作转换 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        // 测试伤害动作
        testDamageAction(converter);
        
        // 测试格挡动作
        testBlockAction(converter);
        
        // 测试能力动作
        testPowerAction(converter);
        
        // 测试抽牌动作
        testDrawAction(converter);
        
        // 测试能量动作
        testEnergyAction(converter);
        
        // 测试治疗动作
        testHealAction(converter);
        
        // 测试弃牌动作
        testDiscardAction(converter);
        
        // 测试消耗动作
        testExhaustAction(converter);
    }
    
    /**
     * 测试伤害动作
     */
    private static void testDamageAction(UniversalActionConverter converter) {
        System.out.println("测试伤害动作转换...");
        
        try {
            // 创建模拟的伤害动作
            SimulatedDamageAction damageAction = new SimulatedDamageAction(10);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(damageAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.ATTACK && intent.getAmount() == 10) {
                System.out.println("✓ 伤害动作转换成功: " + intent);
            } else {
                System.out.println("✗ 伤害动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(damageAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 伤害动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试格挡动作
     */
    private static void testBlockAction(UniversalActionConverter converter) {
        System.out.println("测试格挡动作转换...");
        
        try {
            // 创建模拟的格挡动作
            SimulatedBlockAction blockAction = new SimulatedBlockAction(8);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(blockAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.DEFEND && intent.getAmount() == 8) {
                System.out.println("✓ 格挡动作转换成功: " + intent);
            } else {
                System.out.println("✗ 格挡动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(blockAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 格挡动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试能力动作
     */
    private static void testPowerAction(UniversalActionConverter converter) {
        System.out.println("测试能力动作转换...");
        
        try {
            // 创建模拟的能力动作
            SimulatedPowerAction powerAction = new SimulatedPowerAction("Vulnerable", 2, false);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(powerAction);
            
            // 验证结果
            if (intent != null && (intent.getType() == IntentType.DEBUFF || intent.getType() == IntentType.BUFF)) {
                System.out.println("✓ 能力动作转换成功: " + intent);
            } else {
                System.out.println("✗ 能力动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(powerAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 能力动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试抽牌动作
     */
    private static void testDrawAction(UniversalActionConverter converter) {
        System.out.println("测试抽牌动作转换...");
        
        try {
            // 创建模拟的抽牌动作
            SimulatedDrawCardAction drawAction = new SimulatedDrawCardAction(3);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(drawAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.BUFF && intent.getAmount() == 3) {
                System.out.println("✓ 抽牌动作转换成功: " + intent);
            } else {
                System.out.println("✗ 抽牌动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(drawAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 抽牌动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试能量动作
     */
    private static void testEnergyAction(UniversalActionConverter converter) {
        System.out.println("测试能量动作转换...");
        
        try {
            // 创建模拟能量动作
            SimulatedGainEnergyAction energyAction = new SimulatedGainEnergyAction(2);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(energyAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.BUFF && intent.getAmount() == 2) {
                System.out.println("✓ 能量动作转换成功: " + intent);
            } else {
                System.out.println("✗ 能量动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(energyAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 能量动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试治疗动作
     */
    private static void testHealAction(UniversalActionConverter converter) {
        System.out.println("测试治疗动作转换...");
        
        try {
            // 创建模拟治疗动作
            SimulatedPowerAction healAction = new SimulatedPowerAction("Heal", 5, true);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(healAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.BUFF && intent.getAmount() == 5) {
                System.out.println("✓ 治疗动作转换成功: " + intent);
            } else {
                System.out.println("✗ 治疗动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(healAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 治疗动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试弃牌动作
     */
    private static void testDiscardAction(UniversalActionConverter converter) {
        System.out.println("测试弃牌动作转换...");
        
        try {
            // 创建模拟弃牌动作
            SimulatedPowerAction discardAction = new SimulatedPowerAction("Discard", 2, false);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(discardAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.DEBUFF && intent.getAmount() == 2) {
                System.out.println("✓ 弃牌动作转换成功: " + intent);
            } else {
                System.out.println("✗ 弃牌动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(discardAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 弃牌动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试消耗动作
     */
    private static void testExhaustAction(UniversalActionConverter converter) {
        System.out.println("测试消耗动作转换...");
        
        try {
            // 创建模拟消耗动作
            SimulatedPowerAction exhaustAction = new SimulatedPowerAction("Exhaust", 1, false);
            
            // 转换为意图
            MonsterIntent intent = converter.convertAction(exhaustAction);
            
            // 验证结果
            if (intent != null && intent.getType() == IntentType.DEBUFF && intent.getAmount() == 1) {
                System.out.println("✓ 消耗动作转换成功: " + intent);
            } else {
                System.out.println("✗ 消耗动作转换失败: " + intent);
            }
            
            // 测试验证
            boolean isValid = converter.validateConversion(exhaustAction, intent);
            System.out.println("验证结果: " + (isValid ? "通过" : "失败"));
            
        } catch (Exception e) {
            System.out.println("✗ 消耗动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试动作序列转换
     */
    private static void testActionSequenceConversion() {
        System.out.println("\n--- 测试动作序列转换 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        try {
            // 创建混合动作序列
            List<AbstractGameAction> actions = new ArrayList<>();
            actions.add(new SimulatedDamageAction(8));
            actions.add(new SimulatedBlockAction(5));
            actions.add(new SimulatedDrawCardAction(2));
            
            // 转换为意图列表
            List<MonsterIntent> intents = converter.convertActionSequence(actions);
            
            // 验证结果
            if (intents != null && intents.size() == 3) {
                System.out.println("✓ 动作序列转换成功，生成 " + intents.size() + " 个意图:");
                for (int i = 0; i < intents.size(); i++) {
                    MonsterIntent intent = intents.get(i);
                    System.out.println("  意图 " + (i+1) + ": " + intent);
                }
            } else {
                System.out.println("✗ 动作序列转换失败: " + intents);
            }
            
        } catch (Exception e) {
            System.out.println("✗ 动作序列转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试卡牌动作转换
     */
    private static void testCardActionConversion() {
        System.out.println("\n--- 测试卡牌动作转换 ---");
        
        UniversalActionConverter converter = new UniversalActionConverter();
        
        try {
            // 测试攻击卡牌
            AbstractCard strikeCard = new Strike_Red();
            List<MonsterIntent> strikeIntents = converter.convertCardActions(strikeCard);
            System.out.println("打击卡牌转换结果: " + (strikeIntents.isEmpty() ? "无意图" : strikeIntents.size() + " 个意图"));
            
            // 测试防御卡牌
            AbstractCard defendCard = new Defend_Red();
            List<MonsterIntent> defendIntents = converter.convertCardActions(defendCard);
            System.out.println("防御卡牌转换结果: " + (defendIntents.isEmpty() ? "无意图" : defendIntents.size() + " 个意图"));
            
            // 测试技能卡牌
            AbstractCard bashCard = new Bash();
            List<MonsterIntent> bashIntents = converter.convertCardActions(bashCard);
            System.out.println("重击卡牌转换结果: " + (bashIntents.isEmpty() ? "无意图" : bashIntents.size() + " 个意图"));
            
            // 测试能力卡牌
            AbstractCard survivorCard = new Survivor();
            List<MonsterIntent> survivorIntents = converter.convertCardActions(survivorCard);
            System.out.println("生存卡牌转换结果: " + (survivorIntents.isEmpty() ? "无意图" : survivorIntents.size() + " 个意图"));
            
            // 测试无色卡牌
            AbstractCard flexCard = new Flex();
            List<MonsterIntent> flexIntents = converter.convertCardActions(flexCard);
            System.out.println("屈伸卡牌转换结果: " + (flexIntents.isEmpty() ? "无意图" : flexIntents.size() + " 个意图"));
            
        } catch (Exception e) {
            System.out.println("✗ 卡牌动作转换异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试转换验证
     */
    private static void testConversionValidation() {
        System.out.println("\n--- 测试转换验证 ---");
        
        ConversionValidator validator = new ConversionValidator();
        UniversalActionConverter converter = new UniversalActionConverter();
        
        try {
            // 创建测试动作和意图
            SimulatedDamageAction action = new SimulatedDamageAction(10);
            MonsterIntent intent = converter.convertAction(action);
            
            // 验证转换
            EveryMonsterPlayCard.core.data.ValidationResult result = validator.validateConversion(action, intent);
            
            if (result.isValid()) {
                System.out.println("✓ 转换验证通过");
            } else {
                System.out.println("✗ 转换验证失败:");
                for (String error : result.getErrors()) {
                    System.out.println("  错误: " + error);
                }
                for (String warning : result.getWarnings()) {
                    System.out.println("  警告: " + warning);
                }
            }
            
        } catch (Exception e) {
            System.out.println("✗ 转换验证异常: " + e.getMessage());
        }
    }
    
    /**
     * 测试服务层
     */
    private static void testServiceLayer() {
        System.out.println("\n--- 测试服务层 ---");
        
        try {
            // 获取默认服务
            ActionConversionService service = ActionConversionServiceFactory.getDefaultService();
            
            // 测试单个动作转换
            SimulatedDamageAction action = new SimulatedDamageAction(10);
            
            ActionConversionService.ServiceResult<MonsterIntent> result = service.convertAction(action);
            
            if (result.isSuccess()) {
                System.out.println("✓ 服务层转换成功: " + result.getResult());
            } else {
                System.out.println("✗ 服务层转换失败: " + result.getErrorMessage());
            }
            
            // 测试统计信息
            ActionConversionService.ServiceStatistics stats = service.getStatistics();
            System.out.println("服务统计: " + stats);
            
        } catch (Exception e) {
            System.out.println("✗ 服务层测试异常: " + e.getMessage());
        }
    }
    
    /**
     * 主方法，用于运行测试
     */
    public static void main(String[] args) {
        runAllTests();
    }
}