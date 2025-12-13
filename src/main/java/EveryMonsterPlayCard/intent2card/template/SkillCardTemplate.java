package EveryMonsterPlayCard.intent2card.template;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.monstercards.cards.MonsterSkillCard;

/**
 * 技能卡牌模板
 * 专门用于创建技能类型的怪物卡牌（防御、增益、减益等）
 */
public class SkillCardTemplate extends AbstractCardTemplate {
    
    public SkillCardTemplate() {
        super("技能卡牌模板", "skill_card", 
              new IntentType[]{IntentType.DEFEND, IntentType.BUFF, IntentType.DEBUFF, IntentType.ESCAPE}, 
              2);
    }
    
    @Override
    protected AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                          int cardCost, String cardDescription, 
                                          MonsterIntent intent, IntentParameters parameters) {
        
        // 获取技能参数
        int block = 0;
        int magicNumber = 0;
        CardTarget target = determineCardTarget(intent, parameters);
        
        // 根据意图类型设置参数
        switch (intent.getType()) {
            case DEFEND:
                block = parameters.hasBlock() ? parameters.getBlock() : intent.getAmount();
                break;
            case BUFF:
            case DEBUFF:
                magicNumber = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
                break;
            case ESCAPE:
                // 逃跑意图不需要特殊参数
                break;
        }
        
        // 创建怪物技能卡牌
        MonsterSkillCard card = new MonsterSkillCard(cardId, cardName, cardImage, cardCost, block, target);
        
        // 设置魔法数字
        if (magicNumber > 0) {
            card.baseMagicNumber = magicNumber;
            card.magicNumber = card.baseMagicNumber;
        }
        
        // 设置描述
        card.rawDescription = cardDescription;
        card.initializeDescription();
        
        return card;
    }
    
    @Override
    protected CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        return CardType.SKILL;
    }
    
    @Override
    protected CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardColor")) {
            String colorStr = parameters.getStringParameter("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据技能类型调整颜色
        switch (intent.getType()) {
            case DEFEND:
                return CardColor.GREEN; // 防御使用绿色
            case BUFF:
                return CardColor.BLUE; // 增益使用蓝色
            case DEBUFF:
                return CardColor.PURPLE; // 减益使用紫色
            case ESCAPE:
                return CardColor.COLORLESS; // 逃跑使用无色
            default:
                return CardColor.GREEN; // 默认使用绿色
        }
    }
    
    @Override
    protected CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardRarity")) {
            String rarityStr = parameters.getStringParameter("cardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据效果强度确定稀有度
        int amount = intent.getAmount();
        switch (intent.getType()) {
            case DEFEND:
                if (amount >= 30) {
                    return CardRarity.RARE;
                } else if (amount >= 20) {
                    return CardRarity.UNCOMMON;
                }
                break;
            case BUFF:
            case DEBUFF:
                if (amount >= 15) {
                    return CardRarity.RARE;
                } else if (amount >= 10) {
                    return CardRarity.UNCOMMON;
                }
                break;
            case ESCAPE:
                return CardRarity.BASIC; // 逃跑卡牌总是基础稀有度
        }
        
        return CardRarity.BASIC;
    }
    
    @Override
    protected CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardTarget")) {
            String targetStr = parameters.getStringParameter("cardTarget");
            try {
                return CardTarget.valueOf(targetStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据技能类型确定目标
        switch (intent.getType()) {
            case DEFEND:
            case BUFF:
                return CardTarget.SELF;
            case DEBUFF:
                return CardTarget.ENEMY;
            case ESCAPE:
                return CardTarget.NONE;
            default:
                return CardTarget.SELF;
        }
    }
    
    @Override
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 根据技能类型选择图片
        switch (intent.getType()) {
            case DEFEND:
                return "everyMonsterPlayCardResources/images/cards/monster_defend.png";
            case BUFF:
                return "everyMonsterPlayCardResources/images/cards/monster_buff.png";
            case DEBUFF:
                return "everyMonsterPlayCardResources/images/cards/monster_debuff.png";
            case ESCAPE:
                return "everyMonsterPlayCardResources/images/cards/monster_escape.png";
            default:
                return "everyMonsterPlayCardResources/images/cards/monster_skill.png";
        }
    }
    
    @Override
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = super.generateCardName(intent, parameters);
        
        // 根据技能类型调整名称
        switch (intent.getType()) {
            case DEFEND:
                baseName = baseName.replace("怪物", "防御");
                break;
            case BUFF:
                baseName = baseName.replace("怪物", "增益");
                break;
            case DEBUFF:
                baseName = baseName.replace("怪物", "减益");
                break;
            case ESCAPE:
                baseName = baseName.replace("怪物", "逃跑");
                break;
        }
        
        // 根据效果强度添加修饰词
        int amount = intent.getAmount();
        if (amount > 0) {
            String modifier = getSkillModifier(intent.getType(), amount);
            if (!modifier.isEmpty()) {
                baseName = modifier + baseName;
            }
        }
        
        return baseName;
    }
    
    @Override
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        // 根据技能类型生成描述
        switch (intent.getType()) {
            case DEFEND:
                int block = parameters.hasBlock() ? parameters.getBlock() : intent.getAmount();
                description.append("获得 !B! 点格挡。");
                
                // 添加防御特有的描述
                if (block >= 20) {
                    description.append(" 这是一个强大的防御。");
                }
                break;
                
            case BUFF:
                int buffAmount = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
                description.append("获得 !M! 点增益效果。");
                
                // 添加增益类型描述
                if (parameters.hasParameter("buffType")) {
                    String buffType = parameters.getStringParameter("buffType");
                    description.append(" 类型：").append(buffType).append("。");
                }
                
                if (buffAmount >= 10) {
                    description.append(" 这是一个强大的增益。");
                }
                break;
                
            case DEBUFF:
                int debuffAmount = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
                description.append("对敌人施加 !M! 点减益效果。");
                
                // 添加减益类型描述
                if (parameters.hasParameter("debuffType")) {
                    String debuffType = parameters.getStringParameter("debuffType");
                    description.append(" 类型：").append(debuffType).append("。");
                }
                
                if (debuffAmount >= 10) {
                    description.append(" 这是一个强大的减益。");
                }
                break;
                
            case ESCAPE:
                description.append("尝试从战斗中逃跑。");
                break;
        }
        
        // 添加多目标描述
        if (parameters.hasParameter("multiTarget") && parameters.getBooleanParameter("multiTarget")) {
            description.append(" 影响所有目标。");
        }
        
        // 添加持续时间描述
        if (parameters.hasParameter("duration")) {
            int duration = parameters.getIntParameter("duration");
            description.append(" 持续 ").append(duration).append(" 回合。");
        }
        
        // 添加特殊效果描述
        String specialDescription = generateSpecialEffectDescription(intent, parameters);
        if (specialDescription != null && !specialDescription.isEmpty()) {
            description.append(" ").append(specialDescription);
        }
        
        return description.toString();
    }
    
    @Override
    protected void applySpecialEffects(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        super.applySpecialEffects(card, intent, parameters);
        
        // 应用技能特有的特殊效果
        if (parameters.hasParameter("duration")) {
            int duration = parameters.getIntParameter("duration");
            if (duration > 0) {
                card.baseMagicNumber = duration;
                card.magicNumber = card.baseMagicNumber;
            }
        }
        
        // 设置技能类型标记
        switch (intent.getType()) {
            case DEFEND:
                if (parameters.hasParameter("blockRetain") && parameters.getBooleanParameter("blockRetain")) {
                    card.retain = true;
                }
                break;
            case BUFF:
                if (parameters.hasParameter("permanent") && parameters.getBooleanParameter("permanent")) {
                    card.exhaust = true; // 永久增益通常需要消耗
                }
                break;
            case DEBUFF:
                if (parameters.hasParameter("contagious") && parameters.getBooleanParameter("contagious")) {
                    card.baseMagicNumber = 1; // 标记为传染性
                    card.magicNumber = card.baseMagicNumber;
                }
                break;
        }
    }
    
    @Override
    protected String generateSpecialEffectDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder effects = new StringBuilder(super.generateSpecialEffectDescription(intent, parameters));
        
        // 添加技能特有的特殊效果描述
        switch (intent.getType()) {
            case DEFEND:
                if (parameters.hasParameter("blockRetain") && parameters.getBooleanParameter("blockRetain")) {
                    effects.append(" 格挡保留。");
                }
                if (parameters.hasParameter("blockToAll") && parameters.getBooleanParameter("blockToAll")) {
                    effects.append(" 为所有友方提供格挡。");
                }
                break;
            case BUFF:
                if (parameters.hasParameter("permanent") && parameters.getBooleanParameter("permanent")) {
                    effects.append(" 永久效果。");
                }
                if (parameters.hasParameter("stackable") && parameters.getBooleanParameter("stackable")) {
                    effects.append(" 可叠加。");
                }
                break;
            case DEBUFF:
                if (parameters.hasParameter("contagious") && parameters.getBooleanParameter("contagious")) {
                    effects.append(" 传染性。");
                }
                if (parameters.hasParameter("irremovable") && parameters.getBooleanParameter("irremovable")) {
                    effects.append(" 不可移除。");
                }
                break;
            case ESCAPE:
                if (parameters.hasParameter("guaranteed") && parameters.getBooleanParameter("guaranteed")) {
                    effects.append(" 必定成功。");
                }
                break;
        }
        
        return effects.toString();
    }
    
    /**
     * 根据技能类型和数量获取修饰词
     */
    private String getSkillModifier(IntentType skillType, int amount) {
        switch (skillType) {
            case DEFEND:
                if (amount >= 30) return "铁壁";
                if (amount >= 20) return "坚固";
                if (amount >= 15) return "强力";
                if (amount >= 10) return "标准";
                return "基础";
                
            case BUFF:
                if (amount >= 15) return "强力";
                if (amount >= 10) return "增强";
                if (amount >= 5) return "普通";
                return "微弱";
                
            case DEBUFF:
                if (amount >= 15) return "致命";
                if (amount >= 10) return "强力";
                if (amount >= 5) return "普通";
                return "微弱";
                
            case ESCAPE:
                return ""; // 逃跑不需要修饰词
                
            default:
                return "";
        }
    }
    
    @Override
    public double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        double baseScore = super.calculateMatchScore(intent, parameters);
        
        // 对技能意图有更高的匹配度
        if (intent.getType() == IntentType.DEFEND || 
            intent.getType() == IntentType.BUFF || 
            intent.getType() == IntentType.DEBUFF || 
            intent.getType() == IntentType.ESCAPE) {
            baseScore += 0.4;
        }
        
        // 如果有技能相关参数，提高匹配度
        if (parameters.hasBlock() || parameters.hasMagicNumber() || 
            parameters.hasParameter("buffType") || parameters.hasParameter("debuffType")) {
            baseScore += 0.2;
        }
        
        return Math.min(1.0, baseScore);
    }
    
    @Override
    public String getTemplateDescription() {
        return "技能卡牌模板，专门用于创建技能类型的怪物卡牌，包括防御、增益、减益和逃跑效果";
    }
}