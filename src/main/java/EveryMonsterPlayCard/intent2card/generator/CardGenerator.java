package EveryMonsterPlayCard.intent2card.generator;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.CardAttributes;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.interfaces.IIntentToCardConverter;
import EveryMonsterPlayCard.intent2card.analyzer.IntentAnalyzer;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.intent2card.template.CardTemplate;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 卡牌生成器
 * 实现IIntentToCardConverter接口，负责将怪物意图转换为卡牌
 */
public class CardGenerator implements IIntentToCardConverter {
    
    private final IntentAnalyzer intentAnalyzer;
    private final CardTemplateEngine templateEngine;
    private final DynamicCardBuilder dynamicBuilder;
    private final CardAttributeCalculator attributeCalculator;
    
    public CardGenerator() {
        this.intentAnalyzer = new IntentAnalyzer();
        this.templateEngine = CardTemplateEngine.getInstance();
        this.dynamicBuilder = new DynamicCardBuilder();
        this.attributeCalculator = new CardAttributeCalculator();
    }
    
    @Override
    public AbstractCard convertIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        try {
            // 分析意图
            EveryMonsterPlayCard.intent2card.analyzer.IntentAnalysisResult analysisResult = intentAnalyzer.analyzeIntent(intent);
            
            // 提取参数
            IntentParameters parameters = intentAnalyzer.extractIntentParameters(intent);
            
            // 选择最佳模板
            CardTemplate template = templateEngine.selectBestTemplate(intent, parameters);
            
            if (template != null) {
                // 使用模板创建卡牌
                AbstractCard card = template.createCard(intent, parameters);
                
                // 优化卡牌属性
                if (card != null) {
                    optimizeCard(card, intent, parameters);
                }
                
                return card;
            } else {
                // 使用动态构建器创建卡牌
                return dynamicBuilder.buildCard(intent, parameters);
            }
            
        } catch (Exception e) {
            // 生成失败，返回null
            return null;
        }
    }
    
    @Override
    public CardAttributes inferAttributes(MonsterIntent intent) {
        if (intent == null) {
            return new CardAttributes();
        }
        
        return attributeCalculator.calculateAttributes(intent);
    }
    
    @Override
    public List<AbstractGameAction> simulateEffects(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        if (card == null) {
            return actions;
        }
        
        // 根据卡牌类型模拟效果
        switch (card.type) {
            case ATTACK:
                actions.addAll(simulateAttackEffects(card));
                break;
            case SKILL:
                actions.addAll(simulateSkillEffects(card));
                break;
            case POWER:
                actions.addAll(simulatePowerEffects(card));
                break;
            case CURSE:
            case STATUS:
                // 诅咒和状态卡牌通常没有直接效果
                break;
        }
        
        return actions;
    }
    
    @Override
    public AbstractCard optimizeCard(AbstractCard card) {
        if (card == null) {
            return null;
        }
        
        // 基础优化
        optimizeBasicAttributes(card);
        
        // 平衡性优化
        optimizeBalance(card);
        
        // 性能优化
        optimizePerformance(card);
        
        return card;
    }
    
    @Override
    public boolean validateCard(AbstractCard card, MonsterIntent originalIntent) {
        if (card == null || originalIntent == null) {
            return false;
        }
        
        // 基础验证
        if (!validateBasicAttributes(card)) {
            return false;
        }
        
        // 意图匹配验证
        if (!validateIntentMatch(card, originalIntent)) {
            return false;
        }
        
        // 平衡性验证
        if (!validateBalance(card)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean canHandle(MonsterIntent intent) {
        return intent != null && templateEngine.selectBestTemplate(intent, 
                intentAnalyzer.extractIntentParameters(intent)) != null;
    }
    
    @Override
    public EveryMonsterPlayCard.core.data.IntentType[] getSupportedIntentTypes() {
        return EveryMonsterPlayCard.core.data.IntentType.values();
    }
    
    @Override
    public int getPriority() {
        return 1; // 标准优先级
    }
    
    /**
     * 转换多个意图到卡牌列表
     */
    public List<AbstractCard> convertIntentsToCards(List<MonsterIntent> intents) {
        List<AbstractCard> cards = new ArrayList<>();
        
        if (intents == null || intents.isEmpty()) {
            return cards;
        }
        
        for (MonsterIntent intent : intents) {
            AbstractCard card = convertIntent(intent);
            if (card != null) {
                cards.add(card);
            }
        }
        
        return cards;
    }
    
    /**
     * 转换复合意图到卡牌
     */
    public AbstractCard convertCompositeIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 分析复合意图
        EveryMonsterPlayCard.intent2card.analyzer.IntentAnalysisResult analysisResult = intentAnalyzer.analyzeIntent(intent);
        
        if (!analysisResult.isComposite()) {
            // 不是复合意图，使用普通转换
            return convertIntent(intent);
        }
        
        // 提取复合参数
        IntentParameters parameters = intentAnalyzer.extractIntentParameters(intent);
        parameters.addParameter("isComposite", true);
        
        // 尝试使用复合意图模板
        CardTemplate template = templateEngine.selectBestTemplate(intent, parameters);
        
        if (template != null) {
            AbstractCard card = template.createCard(intent, parameters);
            if (card != null) {
                optimizeCard(card, intent, parameters);
                return card;
            }
        }
        
        // 如果没有合适的模板，使用动态构建器
        return dynamicBuilder.buildCompositeCard(intent, parameters);
    }
    
    /**
     * 验证生成的卡牌
     */
    public ValidationResult validateGeneratedCard(AbstractCard card) {
        ValidationResult result = new ValidationResult();
        
        if (card == null) {
            result.setValid(false);
            result.addError("卡牌为空");
            return result;
        }
        
        // 验证基础属性
        if (!validateBasicAttributes(card)) {
            result.setValid(false);
            result.addError("卡牌基础属性无效");
        }
        
        // 验证平衡性
        if (!validateBalance(card)) {
            result.addWarning("卡牌可能不平衡");
        }
        
        // 验证性能
        if (!validatePerformance(card)) {
            result.addWarning("卡牌可能影响性能");
        }
        
        result.setValid(result.getErrors().isEmpty());
        return result;
    }
    
    /**
     * 优化卡牌
     */
    private void optimizeCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 应用意图特定的优化
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                optimizeAttackCard(card, intent, parameters);
                break;
            case DEFEND:
                optimizeDefendCard(card, intent, parameters);
                break;
            case BUFF:
            case DEBUFF:
                optimizeBuffCard(card, intent, parameters);
                break;
            case ESCAPE:
                optimizeEscapeCard(card, intent, parameters);
                break;
            case UNKNOWN:
                optimizeUnknownCard(card, intent, parameters);
                break;
        }
    }
    
    /**
     * 优化攻击卡牌
     */
    private void optimizeAttackCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 确保伤害值合理
        if (card.baseDamage <= 0) {
            card.baseDamage = Math.max(1, intent.getAmount());
            card.damage = card.baseDamage;
        }
        
        // 根据伤害调整费用
        if (card.cost < 0) {
            card.cost = Math.max(1, Math.min(3, card.baseDamage / 7));
        }
        
        // 设置正确的卡牌类型
        card.type = com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
    }
    
    /**
     * 优化防御卡牌
     */
    private void optimizeDefendCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 确保格挡值合理
        if (card.baseBlock <= 0) {
            card.baseBlock = Math.max(1, intent.getAmount());
            card.block = card.baseBlock;
        }
        
        // 根据格挡调整费用
        if (card.cost < 0) {
            card.cost = Math.max(0, Math.min(2, card.baseBlock / 10));
        }
        
        // 设置正确的卡牌类型
        card.type = com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
    }
    
    /**
     * 优化增益卡牌
     */
    private void optimizeBuffCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 确保魔法数字合理
        if (card.baseMagicNumber <= 0) {
            card.baseMagicNumber = Math.max(1, intent.getAmount());
            card.magicNumber = card.baseMagicNumber;
        }
        
        // 根据效果强度调整费用
        if (card.cost < 0) {
            card.cost = Math.max(1, Math.min(2, card.baseMagicNumber / 5));
        }
        
        // 设置正确的卡牌类型
        card.type = com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
    }
    
    /**
     * 优化逃跑卡牌
     */
    private void optimizeEscapeCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 逃跑卡牌通常费用为0
        card.cost = 0;
        
        // 设置正确的卡牌类型
        card.type = com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
    }
    
    /**
     * 优化未知卡牌
     */
    private void optimizeUnknownCard(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 未知意图使用默认优化
        if (card.cost < 0) {
            card.cost = 1;
        }
        
        // 设置默认卡牌类型
        card.type = com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
    }
    
    /**
     * 优化基础属性
     */
    private void optimizeBasicAttributes(AbstractCard card) {
        // 确保名称不为空
        if (card.name == null || card.name.trim().isEmpty()) {
            card.name = "未知卡牌";
        }
        
        // 确保描述不为空
        if (card.rawDescription == null || card.rawDescription.trim().isEmpty()) {
            card.rawDescription = "无描述";
            card.initializeDescription();
        }
        
        // 确保费用合理
        if (card.cost < -2) {
            card.cost = -2;
        } else if (card.cost > 3) {
            card.cost = 3;
        }
    }
    
    /**
     * 优化平衡性
     */
    private void optimizeBalance(AbstractCard card) {
        // 根据卡牌效果调整费用
        int suggestedCost = calculateBalancedCost(card);
        
        // 如果当前费用与建议费用差异过大，进行调整
        if (Math.abs(card.cost - suggestedCost) > 1) {
            card.cost = suggestedCost;
        }
    }
    
    /**
     * 优化性能
     */
    private void optimizePerformance(AbstractCard card) {
        // 确保卡牌不会造成性能问题
        if (card.baseDamage > 999) {
            card.baseDamage = 999;
            card.damage = card.baseDamage;
        }
        
        if (card.baseBlock > 999) {
            card.baseBlock = 999;
            card.block = card.baseBlock;
        }
        
        if (card.baseMagicNumber > 999) {
            card.baseMagicNumber = 999;
            card.magicNumber = card.baseMagicNumber;
        }
    }
    
    /**
     * 计算平衡的费用
     */
    private int calculateBalancedCost(AbstractCard card) {
        int cost = 1;
        
        // 根据伤害计算费用
        if (card.baseDamage > 0) {
            cost += Math.max(0, card.baseDamage / 7);
        }
        
        // 根据格挡计算费用
        if (card.baseBlock > 0) {
            cost += Math.max(0, card.baseBlock / 10);
        }
        
        // 根据魔法数字计算费用
        if (card.baseMagicNumber > 0) {
            cost += Math.max(0, card.baseMagicNumber / 5);
        }
        
        // 考虑特殊效果
        if (card.exhaust) {
            cost -= 1; // 消耗卡牌可以便宜一些
        }
        
        if (card.isEthereal) {
            cost -= 1; // 虚无卡牌可以便宜一些
        }
        
        return Math.max(0, Math.min(3, cost));
    }
    
    /**
     * 验证基础属性
     */
    private boolean validateBasicAttributes(AbstractCard card) {
        return card.name != null && !card.name.trim().isEmpty() &&
               card.rawDescription != null && !card.rawDescription.trim().isEmpty() &&
               card.type != null &&
               card.color != null &&
               card.rarity != null &&
               card.target != null;
    }
    
    /**
     * 验证意图匹配
     */
    private boolean validateIntentMatch(AbstractCard card, MonsterIntent intent) {
        // 检查卡牌类型是否与意图类型匹配
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return card.type == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
            case DEFEND:
            case BUFF:
            case DEBUFF:
            case ESCAPE:
                return card.type == com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
            default:
                return true; // 未知意图允许任何类型
        }
    }
    
    /**
     * 验证平衡性
     */
    private boolean validateBalance(AbstractCard card) {
        // 检查费用是否合理
        if (card.cost < -2 || card.cost > 3) {
            return false;
        }
        
        // 检查数值是否合理
        if (card.baseDamage < 0 || card.baseDamage > 999) {
            return false;
        }
        
        if (card.baseBlock < 0 || card.baseBlock > 999) {
            return false;
        }
        
        if (card.baseMagicNumber < 0 || card.baseMagicNumber > 999) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证性能
     */
    private boolean validatePerformance(AbstractCard card) {
        // 检查是否有可能影响性能的属性
        return card.baseDamage <= 999 && card.baseBlock <= 999 && card.baseMagicNumber <= 999;
    }
    
    /**
     * 模拟攻击效果
     */
    private List<AbstractGameAction> simulateAttackEffects(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        // 这里应该根据具体的攻击效果创建相应的动作
        // 简化实现，返回空列表
        return actions;
    }
    
    /**
     * 模拟技能效果
     */
    private List<AbstractGameAction> simulateSkillEffects(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        // 这里应该根据具体的技能效果创建相应的动作
        // 简化实现，返回空列表
        return actions;
    }
    
    /**
     * 模拟能力效果
     */
    private List<AbstractGameAction> simulatePowerEffects(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        // 这里应该根据具体的能力效果创建相应的动作
        // 简化实现，返回空列表
        return actions;
    }
}