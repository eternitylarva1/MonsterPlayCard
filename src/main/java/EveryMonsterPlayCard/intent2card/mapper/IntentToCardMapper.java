package EveryMonsterPlayCard.intent2card.mapper;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.intent2card.generator.CardGenerator;
import EveryMonsterPlayCard.intent2card.template.CardTemplate;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 意图到卡牌映射器
 * 负责将怪物意图映射到合适的卡牌
 */
public class IntentToCardMapper {
    
    private final MappingRulesEngine rulesEngine;
    private final SpecialIntentHandler specialHandler;
    private final CompositeIntentResolver compositeResolver;
    private final CardTemplateEngine templateEngine;
    private final Map<IntentType, MappingRule> defaultRules;
    
    public IntentToCardMapper() {
        this.rulesEngine = new MappingRulesEngine();
        this.specialHandler = new SpecialIntentHandler();
        this.compositeResolver = new CompositeIntentResolver();
        this.templateEngine = CardTemplateEngine.getInstance();
        this.defaultRules = new HashMap<>();
        initializeDefaultRules();
    }
    
    /**
     * 映射攻击意图
     */
    public AbstractCard mapAttackIntent(MonsterIntent intent) {
        if (intent == null || intent.getType() != IntentType.ATTACK) {
            return null;
        }
        
        // 检查特殊处理
        AbstractCard specialCard = specialHandler.handleSpecialIntent(intent);
        if (specialCard != null) {
            return specialCard;
        }
        
        // 获取映射规则
        MappingRule rule = getMappingRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        // 使用默认攻击映射
        return applyDefaultAttackMapping(intent);
    }
    
    /**
     * 映射防御意图
     */
    public AbstractCard mapDefendIntent(MonsterIntent intent) {
        if (intent == null || intent.getType() != IntentType.DEFEND) {
            return null;
        }
        
        // 检查特殊处理
        AbstractCard specialCard = specialHandler.handleSpecialIntent(intent);
        if (specialCard != null) {
            return specialCard;
        }
        
        // 获取映射规则
        MappingRule rule = getMappingRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        // 使用默认防御映射
        return applyDefaultDefendMapping(intent);
    }
    
    /**
     * 映射增益意图
     */
    public AbstractCard mapBuffIntent(MonsterIntent intent) {
        if (intent == null || intent.getType() != IntentType.BUFF) {
            return null;
        }
        
        // 检查特殊处理
        AbstractCard specialCard = specialHandler.handleSpecialIntent(intent);
        if (specialCard != null) {
            return specialCard;
        }
        
        // 获取映射规则
        MappingRule rule = getMappingRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        // 使用默认增益映射
        return applyDefaultBuffMapping(intent);
    }
    
    /**
     * 映射减益意图
     */
    public AbstractCard mapDebuffIntent(MonsterIntent intent) {
        if (intent == null || intent.getType() != IntentType.DEBUFF) {
            return null;
        }
        
        // 检查特殊处理
        AbstractCard specialCard = specialHandler.handleSpecialIntent(intent);
        if (specialCard != null) {
            return specialCard;
        }
        
        // 获取映射规则
        MappingRule rule = getMappingRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        // 使用默认减益映射
        return applyDefaultDebuffMapping(intent);
    }
    
    /**
     * 映射特殊意图
     */
    public AbstractCard mapSpecialIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 优先使用特殊处理器
        AbstractCard specialCard = specialHandler.handleSpecialIntent(intent);
        if (specialCard != null) {
            return specialCard;
        }
        
        // 获取映射规则
        MappingRule rule = getMappingRule(intent);
        if (rule != null) {
            return rule.apply(intent);
        }
        
        // 使用默认特殊映射
        return applyDefaultSpecialMapping(intent);
    }
    
    /**
     * 映射复合意图
     */
    public AbstractCard mapCompositeIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 使用复合意图解析器
        return compositeResolver.resolveCompositeIntent(intent);
    }
    
    /**
     * 通用映射方法
     */
    public AbstractCard mapIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 检查是否为复合意图
        if (isCompositeIntent(intent)) {
            return mapCompositeIntent(intent);
        }
        
        // 根据意图类型选择映射方法
        switch (intent.getType()) {
            case ATTACK:
                return mapAttackIntent(intent);
            case DEFEND:
                return mapDefendIntent(intent);
            case BUFF:
                return mapBuffIntent(intent);
            case DEBUFF:
                return mapDebuffIntent(intent);
            case STRONG:
                return mapStrongAttackIntent(intent);
            case ESCAPE:
                return mapEscapeIntent(intent);
            case UNKNOWN:
                return mapUnknownIntent(intent);
            default:
                return null;
        }
    }
    
    /**
     * 映射强力攻击意图
     */
    private AbstractCard mapStrongAttackIntent(MonsterIntent intent) {
        // 强力攻击是攻击的特殊形式
        return mapAttackIntent(intent);
    }
    
    /**
     * 映射逃跑意图
     */
    private AbstractCard mapEscapeIntent(MonsterIntent intent) {
        // 逃跑意图的特殊处理
        return mapSpecialIntent(intent);
    }
    
    /**
     * 映射未知意图
     */
    private AbstractCard mapUnknownIntent(MonsterIntent intent) {
        // 未知意图的特殊处理
        return mapSpecialIntent(intent);
    }
    
    /**
     * 获取映射规则
     */
    private MappingRule getMappingRule(MonsterIntent intent) {
        // 首先检查自定义规则
        MappingRule customRule = rulesEngine.getCustomRule(intent.getType());
        if (customRule != null) {
            return customRule;
        }
        
        // 然后检查默认规则
        return defaultRules.get(intent.getType());
    }
    
    /**
     * 应用默认攻击映射
     */
    private AbstractCard applyDefaultAttackMapping(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        parameters.setDamage(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(3, intent.getAmount() / 7)));
        parameters.addParameter("intentType", "attack");
        
        // 使用攻击模板创建卡牌
        CardTemplate template = templateEngine.getTemplate("攻击卡牌模板");
        if (template != null) {
            return template.createCard(intent, parameters);
        }
        
        // 使用标准模板
        template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 应用默认防御映射
     */
    private AbstractCard applyDefaultDefendMapping(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        parameters.setBlock(intent.getAmount());
        parameters.setCost(Math.max(0, Math.min(2, intent.getAmount() / 10)));
        parameters.addParameter("intentType", "defend");
        
        // 使用技能模板创建卡牌
        CardTemplate template = templateEngine.getTemplate("技能卡牌模板");
        if (template != null) {
            return template.createCard(intent, parameters);
        }
        
        // 使用标准模板
        template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 应用默认增益映射
     */
    private AbstractCard applyDefaultBuffMapping(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        parameters.setMagicNumber(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 5)));
        parameters.addParameter("intentType", "buff");
        
        // 使用能力模板创建卡牌
        CardTemplate template = templateEngine.getTemplate("能力卡牌模板");
        if (template != null) {
            return template.createCard(intent, parameters);
        }
        
        // 使用技能模板
        template = templateEngine.getTemplate("技能卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 应用默认减益映射
     */
    private AbstractCard applyDefaultDebuffMapping(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        parameters.setMagicNumber(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 5)));
        parameters.addParameter("intentType", "debuff");
        
        // 使用技能模板创建卡牌
        CardTemplate template = templateEngine.getTemplate("技能卡牌模板");
        if (template != null) {
            return template.createCard(intent, parameters);
        }
        
        // 使用标准模板
        template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 应用默认特殊映射
     */
    private AbstractCard applyDefaultSpecialMapping(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        parameters.setCost(1);
        parameters.addParameter("intentType", "special");
        
        // 使用自定义模板创建卡牌
        CardTemplate template = templateEngine.getTemplate("自定义卡牌模板");
        if (template != null) {
            return template.createCard(intent, parameters);
        }
        
        // 使用标准模板
        template = templateEngine.getTemplate("标准卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 检查是否为复合意图
     */
    private boolean isCompositeIntent(MonsterIntent intent) {
        if (intent == null) {
            return false;
        }
        
        // 检查明确的复合标记
        if (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) {
            return true;
        }
        
        // 检查次要意图
        if (intent.hasProperty("secondaryIntent")) {
            return true;
        }
        
        // 检查多动作
        if (intent.getComponentActions() != null && intent.getComponentActions().size() > 1) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 初始化默认规则
     */
    private void initializeDefaultRules() {
        // 攻击意图规则
        defaultRules.put(IntentType.ATTACK, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.ATTACK;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultAttackMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultAttackRule";
            }
        });
        
        // 防御意图规则
        defaultRules.put(IntentType.DEFEND, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.DEFEND;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultDefendMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultDefendRule";
            }
        });
        
        // 增益意图规则
        defaultRules.put(IntentType.BUFF, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.BUFF;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultBuffMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultBuffRule";
            }
        });
        
        // 减益意图规则
        defaultRules.put(IntentType.DEBUFF, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.DEBUFF;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultDebuffMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultDebuffRule";
            }
        });
        
        // 强力攻击意图规则
        defaultRules.put(IntentType.STRONG, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.STRONG;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultAttackMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultStrongAttackRule";
            }
        });
        
        // 逃跑意图规则
        defaultRules.put(IntentType.ESCAPE, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.ESCAPE;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultSpecialMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultEscapeRule";
            }
        });
        
        // 未知意图规则
        defaultRules.put(IntentType.UNKNOWN, new MappingRule() {
            @Override
            public boolean matches(MonsterIntent intent) {
                return intent != null && intent.getType() == IntentType.UNKNOWN;
            }
            
            @Override
            public AbstractCard apply(MonsterIntent intent) {
                return applyDefaultSpecialMapping(intent);
            }
            
            @Override
            public String getRuleName() {
                return "DefaultUnknownRule";
            }
        });
    }
    
    /**
     * 添加自定义映射规则
     */
    public void addCustomRule(IntentType intentType, MappingRule rule) {
        rulesEngine.addCustomRule(intentType, rule);
    }
    
    /**
     * 移除自定义映射规则
     */
    public void removeCustomRule(IntentType intentType) {
        rulesEngine.removeCustomRule(intentType);
    }
    
    /**
     * 获取映射统计信息
     */
    public MappingStatistics getStatistics() {
        MappingStatistics stats = new MappingStatistics();
        stats.totalRules = defaultRules.size() + rulesEngine.getCustomRuleCount();
        stats.customRules = rulesEngine.getCustomRuleCount();
        stats.defaultRules = defaultRules.size();
        return stats;
    }
    
    /**
     * 映射统计信息
     */
    public static class MappingStatistics {
        public int totalRules;
        public int customRules;
        public int defaultRules;
        
        @Override
        public String toString() {
            return "MappingStatistics{" +
                    "totalRules=" + totalRules +
                    ", customRules=" + customRules +
                    ", defaultRules=" + defaultRules +
                    '}';
        }
    }
}