package EveryMonsterPlayCard.intent2card.mapper;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 默认映射规则实现类
 * 包含各种默认的映射规则
 */
public class DefaultMappingRules {
    
    /**
     * 默认攻击规则
     */
    public static class DefaultAttackRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.ATTACK;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setDamage(intent.getAmount());
            parameters.setCost(Math.max(1, Math.min(3, intent.getAmount() / 7)));
            parameters.addParameter("intentType", "attack");
            
            // 使用攻击模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("攻击卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认攻击规则";
        }
        
        @Override
        public int getPriority() {
            return 1;
        }
        
        @Override
        public String getDescription() {
            return "将攻击意图映射为攻击卡牌，根据伤害值计算费用";
        }
    }
    
    /**
     * 默认防御规则
     */
    public static class DefaultDefendRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.DEFEND;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setBlock(intent.getAmount());
            parameters.setCost(Math.max(0, Math.min(2, intent.getAmount() / 10)));
            parameters.addParameter("intentType", "defend");
            
            // 使用技能模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("技能卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认防御规则";
        }
        
        @Override
        public int getPriority() {
            return 1;
        }
        
        @Override
        public String getDescription() {
            return "将防御意图映射为技能卡牌，根据格挡值计算费用";
        }
    }
    
    /**
     * 默认增益规则
     */
    public static class DefaultBuffRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.BUFF;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setMagicNumber(intent.getAmount());
            parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 5)));
            parameters.addParameter("intentType", "buff");
            
            // 使用能力模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("能力卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认增益规则";
        }
        
        @Override
        public int getPriority() {
            return 1;
        }
        
        @Override
        public String getDescription() {
            return "将增益意图映射为能力卡牌，根据效果强度计算费用";
        }
    }
    
    /**
     * 默认减益规则
     */
    public static class DefaultDebuffRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.DEBUFF;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setMagicNumber(intent.getAmount());
            parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 5)));
            parameters.addParameter("intentType", "debuff");
            
            // 使用技能模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("技能卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认减益规则";
        }
        
        @Override
        public int getPriority() {
            return 1;
        }
        
        @Override
        public String getDescription() {
            return "将减益意图映射为技能卡牌，根据效果强度计算费用";
        }
    }
    
    /**
     * 默认强力攻击规则
     */
    public static class DefaultStrongAttackRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.STRONG;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setDamage(intent.getAmount());
            parameters.setCost(Math.max(2, Math.min(3, intent.getAmount() / 5)));
            parameters.addParameter("intentType", "strong_attack");
            
            // 使用攻击模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("攻击卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认强力攻击规则";
        }
        
        @Override
        public int getPriority() {
            return 2; // 强力攻击优先级更高
        }
        
        @Override
        public String getDescription() {
            return "将强力攻击意图映射为攻击卡牌，费用更高但效果更强";
        }
    }
    
    /**
     * 默认逃跑规则
     */
    public static class DefaultEscapeRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.ESCAPE;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setCost(0);
            parameters.addParameter("intentType", "escape");
            
            // 使用技能模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("技能卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认逃跑规则";
        }
        
        @Override
        public int getPriority() {
            return 1;
        }
        
        @Override
        public String getDescription() {
            return "将逃跑意图映射为技能卡牌，费用为0";
        }
    }
    
    /**
     * 默认未知意图规则
     */
    public static class DefaultUnknownRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            return intent != null && intent.getType() == IntentType.UNKNOWN;
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.setCost(1);
            parameters.addParameter("intentType", "unknown");
            
            // 如果有数量，尝试推断类型
            if (intent.getAmount() > 0) {
                if (intent.getAmount() >= 10) {
                    parameters.setDamage(intent.getAmount());
                    parameters.addParameter("inferredType", "attack");
                } else {
                    parameters.setBlock(intent.getAmount());
                    parameters.addParameter("inferredType", "defend");
                }
            }
            
            // 使用标准模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "默认未知意图规则";
        }
        
        @Override
        public int getPriority() {
            return 0; // 未知意图优先级最低
        }
        
        @Override
        public String getDescription() {
            return "将未知意图映射为标准卡牌，尝试推断最佳类型";
        }
    }
    
    /**
     * 复合意图规则
     */
    public static class CompositeIntentRule implements MappingRule {
        @Override
        public boolean matches(MonsterIntent intent) {
            if (intent == null) {
                return false;
            }
            
            // 检查是否为复合意图
            return (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) ||
                   (intent.hasProperty("secondaryIntent")) ||
                   (intent.getComponentActions() != null && intent.getComponentActions().size() > 1);
        }
        
        @Override
        public AbstractCard apply(MonsterIntent intent) {
            if (!matches(intent)) {
                return null;
            }
            
            IntentParameters parameters = new IntentParameters();
            parameters.addParameter("isComposite", true);
            
            // 设置主要意图参数
            switch (intent.getType()) {
                case ATTACK:
                parameters.setDamage(intent.getAmount());
                    break;
                case DEFEND:
                    parameters.setBlock(intent.getAmount());
                    break;
                case BUFF:
                case DEBUFF:
                    parameters.setMagicNumber(intent.getAmount());
                    break;
            }
            
            // 设置次要意图参数
            if (intent.hasProperty("secondaryIntent")) {
                parameters.addParameter("secondaryIntent", intent.getStringParameter("secondaryIntent"));
            }
            
            // 计算复合意图费用
            int baseCost = calculateBaseCost(intent);
            parameters.setCost(Math.min(3, baseCost + 1)); // 复合意图费用+1
            
            // 使用自定义模板创建卡牌
            CardTemplateEngine templateEngine = CardTemplateEngine.getInstance();
            EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("自定义卡牌模板");
            return template != null ? template.createCard(intent, parameters) : null;
        }
        
        @Override
        public String getRuleName() {
            return "复合意图规则";
        }
        
        @Override
        public int getPriority() {
            return 3; // 复合意图优先级最高
        }
        
        @Override
        public String getDescription() {
            return "将复合意图映射为自定义卡牌，包含多种效果";
        }
        
        /**
         * 计算基础费用
         */
        private int calculateBaseCost(MonsterIntent intent) {
            int amount = intent.getAmount();
            switch (intent.getType()) {
                case ATTACK:
                    return Math.max(1, Math.min(3, amount / 7));
                case DEFEND:
                    return Math.max(0, Math.min(2, amount / 10));
                case BUFF:
                case DEBUFF:
                    return Math.max(1, Math.min(2, amount / 5));
                default:
                    return 1;
            }
        }
    }
}