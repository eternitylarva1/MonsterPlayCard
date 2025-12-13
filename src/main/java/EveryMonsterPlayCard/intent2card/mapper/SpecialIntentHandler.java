package EveryMonsterPlayCard.intent2card.mapper;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 特殊意图处理器
 * 处理特殊的、非标准的意图类型
 */
public class SpecialIntentHandler {
    
    private final Map<String, SpecialIntentRule> specialRules;
    private final CardTemplateEngine templateEngine;
    
    public SpecialIntentHandler() {
        this.specialRules = new HashMap<>();
        this.templateEngine = CardTemplateEngine.getInstance();
        initializeSpecialRules();
    }
    
    /**
     * 处理特殊意图
     */
    public AbstractCard handleSpecialIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 检查是否有特殊标记
        if (hasSpecialMarker(intent)) {
            return handleMarkedSpecialIntent(intent);
        }
        
        // 检查特殊属性
        if (hasSpecialProperties(intent)) {
            return handlePropertySpecialIntent(intent);
        }
        
        // 检查特殊元数据
        if (hasSpecialMetadata(intent)) {
            return handleMetadataSpecialIntent(intent);
        }
        
        // 检查特殊动作
        if (hasSpecialActions(intent)) {
            return handleActionSpecialIntent(intent);
        }
        
        // 检查命名特殊规则
        SpecialIntentRule rule = getSpecialRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        return null;
    }
    
    /**
     * 检查是否有特殊标记
     */
    private boolean hasSpecialMarker(MonsterIntent intent) {
        return intent.hasProperty("special") && intent.getBooleanParameter("special") ||
               intent.hasProperty("custom") && intent.getBooleanParameter("custom") ||
               intent.hasProperty("unique") && intent.getBooleanParameter("unique");
    }
    
    /**
     * 处理标记的特殊意图
     */
    private AbstractCard handleMarkedSpecialIntent(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        // 复制基础参数
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateSpecialCost(intent));
        parameters.addParameter("special", true);
        
        // 根据特殊标记类型处理
        if (intent.hasProperty("custom") && intent.getBooleanParameter("custom")) {
            return handleCustomIntent(intent, parameters);
        }
        
        if (intent.hasProperty("unique") && intent.getBooleanParameter("unique")) {
            return handleUniqueIntent(intent, parameters);
        }
        
        // 默认特殊处理
        return handleDefaultSpecialIntent(intent, parameters);
    }
    
    /**
     * 处理自定义意图
     */
    private AbstractCard handleCustomIntent(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("useCustomTemplate", true);
        
        // 使用自定义模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("自定义卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 处理唯一意图
     */
    private AbstractCard handleUniqueIntent(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("unique", true);
        parameters.setCost(Math.max(0, parameters.getCost() - 1)); // 唯一卡牌费用-1
        
        // 使用稀有模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
        if (template != null) {
            AbstractCard card = template.createCard(intent, parameters);
            if (card != null) {
                card.rarity = com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE;
            }
            return card;
        }
        
        return null;
    }
    
    /**
     * 处理默认特殊意图
     */
    private AbstractCard handleDefaultSpecialIntent(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("specialEffect", true);
        
        // 使用标准模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
        AbstractCard card = template != null ? template.createCard(intent, parameters) : null;
        
        if (card != null) {
            // 添加特殊效果
            card.exhaust = true; // 特殊意图通常消耗
        }
        
        return card;
    }
    
    /**
     * 检查是否有特殊属性
     */
    private boolean hasSpecialProperties(MonsterIntent intent) {
        return intent.hasProperty("multiTarget") ||
               intent.hasProperty("randomTarget") ||
               intent.hasProperty("scaling") ||
               intent.hasProperty("conditional");
    }
    
    /**
     * 处理属性特殊意图
     */
    private AbstractCard handlePropertySpecialIntent(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        // 复制基础参数
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateSpecialCost(intent));
        
        // 处理多目标
        if (intent.hasProperty("multiTarget") && intent.getBooleanParameter("multiTarget")) {
            parameters.addParameter("multiTarget", true);
            parameters.setCost(parameters.getCost() + 1);
        }
        
        // 处理随机目标
        if (intent.hasProperty("randomTarget") && intent.getBooleanParameter("randomTarget")) {
            parameters.addParameter("randomTarget", true);
            parameters.setCost(Math.max(0, parameters.getCost() - 1));
        }
        
        // 处理成长性
        if (intent.hasProperty("scaling") && intent.getBooleanParameter("scaling")) {
            parameters.addParameter("scaling", true);
            parameters.setCost(parameters.getCost() + 1);
        }
        
        // 处理条件性
        if (intent.hasProperty("conditional") && intent.getBooleanParameter("conditional")) {
            parameters.addParameter("conditional", true);
            parameters.setCost(Math.max(0, parameters.getCost() - 1));
        }
        
        // 使用标准模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 检查是否有特殊元数据
     */
    private boolean hasSpecialMetadata(MonsterIntent intent) {
        if (intent.getMetadata() == null) {
            return false;
        }
        
        return intent.getMetadata().hasProperty("critical") ||
               intent.getMetadata().hasProperty("urgent") ||
               intent.getMetadata().hasProperty("hidden");
    }
    
    /**
     * 处理元数据特殊意图
     */
    private AbstractCard handleMetadataSpecialIntent(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        // 复制基础参数
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateSpecialCost(intent));
        
        // 处理关键标记
        if (intent.getMetadata().hasProperty("critical") && 
            intent.getMetadata().getBooleanParameter("critical")) {
            parameters.addParameter("critical", true);
            parameters.setCost(parameters.getCost() + 1);
        }
        
        // 处理紧急标记
        if (intent.getMetadata().hasProperty("urgent") && 
            intent.getMetadata().getBooleanParameter("urgent")) {
            parameters.addParameter("urgent", true);
            parameters.setCost(Math.max(0, parameters.getCost() - 1));
        }
        
        // 处理隐藏标记
        if (intent.getMetadata().hasProperty("hidden") && 
            intent.getMetadata().getBooleanParameter("hidden")) {
            parameters.addParameter("hidden", true);
            parameters.setCost(Math.max(0, parameters.getCost() - 1));
        }
        
        // 使用标准模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 检查是否有特殊动作
     */
    private boolean hasSpecialActions(MonsterIntent intent) {
        if (intent.getComponentActions() == null || intent.getComponentActions().isEmpty()) {
            return false;
        }
        
        // 检查动作类型是否特殊
        return intent.getComponentActions().stream().anyMatch(action -> 
            action.getClass().getSimpleName().contains("Special") ||
            action.getClass().getSimpleName().contains("Unique") ||
            action.getClass().getSimpleName().contains("Chained"));
    }
    
    /**
     * 处理动作特殊意图
     */
    private AbstractCard handleActionSpecialIntent(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        // 复制基础参数
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateSpecialCost(intent));
        
        // 标记为动作特殊
        parameters.addParameter("specialAction", true);
        parameters.setCost(parameters.getCost() + 1);
        
        // 使用标准模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 获取特殊规则
     */
    private SpecialIntentRule getSpecialRule(MonsterIntent intent) {
        if (intent == null || intent.getMetadata() == null) {
            return null;
        }
        
        String ruleName = intent.getMetadata().getStringParameter("specialRule");
        if (ruleName != null && !ruleName.trim().isEmpty()) {
            return specialRules.get(ruleName);
        }
        
        return null;
    }
    
    /**
     * 计算特殊意图费用
     */
    private int calculateSpecialCost(MonsterIntent intent) {
        int baseCost = Math.max(1, Math.min(3, intent.getAmount() / 7));
        
        // 特殊意图通常费用调整
        if (intent.getType() == IntentType.STRONG) {
            baseCost = Math.max(2, baseCost);
        }
        
        return baseCost;
    }
    
    /**
     * 添加特殊规则
     */
    public void addSpecialRule(String ruleName, SpecialIntentRule rule) {
        if (ruleName != null && !ruleName.trim().isEmpty() && rule != null) {
            specialRules.put(ruleName, rule);
        }
    }
    
    /**
     * 移除特殊规则
     */
    public void removeSpecialRule(String ruleName) {
        specialRules.remove(ruleName);
    }
    
    /**
     * 获取特殊规则数量
     */
    public int getSpecialRuleCount() {
        return specialRules.size();
    }
    
    /**
     * 初始化特殊规则
     */
    private void initializeSpecialRules() {
        // 可以在这里添加一些默认的特殊规则
        // 例如：致命一击规则、连锁反应规则等
    }
    
    /**
     * 特殊意图规则接口
     */
    public interface SpecialIntentRule {
        boolean matches(MonsterIntent intent);
        AbstractCard apply(MonsterIntent intent);
        String getRuleName();
        int getPriority();
    }
}