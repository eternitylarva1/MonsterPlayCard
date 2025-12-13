package EveryMonsterPlayCard.conversion.analyzer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 卡牌动作提取器
 * 负责从卡牌中提取动作信息
 */
public class CardActionExtractor {
    
    /**
     * 提取卡牌的动作列表
     * @param card 卡牌对象
     * @return 动作列表
     */
    public List<AbstractGameAction> extractCardActions(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        if (card == null) {
            return actions;
        }
        
        try {
            // 尝试调用卡牌的use方法来获取动作
            // 这里使用反射来模拟卡牌使用过程
            AbstractCreature target = AbstractDungeon.player;
            AbstractCreature source = AbstractDungeon.player;
            
            // 获取use方法
            Method useMethod = card.getClass().getMethod("use", 
                AbstractCreature.class, AbstractMonster.class);
            
            // 由于无法直接执行use方法（会改变游戏状态），
            // 我们通过分析卡牌属性来推断动作
            actions.addAll(inferActionsFromCard(card));
            
        } catch (Exception e) {
            // 如果反射失败，使用基础推断
            actions.addAll(inferActionsFromCard(card));
        }
        
        return actions;
    }
    
    /**
     * 从卡牌属性推断动作
     * @param card 卡牌对象
     * @return 推断的动作列表
     */
    private List<AbstractGameAction> inferActionsFromCard(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        // 根据卡牌类型推断动作
        switch (card.type) {
            case ATTACK:
                actions.addAll(inferAttackActions(card));
                break;
            case SKILL:
                actions.addAll(inferSkillActions(card));
                break;
            case POWER:
                actions.addAll(inferPowerActions(card));
                break;
            case CURSE:
            case STATUS:
                // 通常不产生直接动作
                break;
        }
        
        return actions;
    }
    
    /**
     * 推断攻击卡牌的动作
     * @param card 攻击卡牌
     * @return 动作列表
     */
    private List<AbstractGameAction> inferAttackActions(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        // 创建伤害动作的模拟
        // 注意：这里只是创建模拟对象，不实际执行
        if (card.baseDamage > 0) {
            // 创建一个模拟的DamageAction
            // 由于无法直接实例化，我们使用一个包装类
            actions.add(new SimulatedDamageAction(card.baseDamage));
        }
        
        // 检查是否有其他效果
        if (card.magicNumber > 0) {
            // 根据卡牌名称判断特殊效果
            String cardName = card.name.toLowerCase();
            if (cardName.contains("虚弱") || cardName.contains("weak")) {
                actions.add(new SimulatedPowerAction("Weak", card.magicNumber, true));
            } else if (cardName.contains("易伤") || cardName.contains("vulnerable")) {
                actions.add(new SimulatedPowerAction("Vulnerable", card.magicNumber, true));
            }
        }
        
        return actions;
    }
    
    /**
     * 推断技能卡牌的动作
     * @param card 技能卡牌
     * @return 动作列表
     */
    private List<AbstractGameAction> inferSkillActions(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        // 防御动作
        if (card.baseBlock > 0) {
            actions.add(new SimulatedBlockAction(card.baseBlock));
        }
        
        // 抽牌动作
        String cardName = card.name.toLowerCase();
        if (cardName.contains("抽牌") || cardName.contains("draw")) {
            actions.add(new SimulatedDrawCardAction(card.magicNumber));
        }
        
        // 能量动作
        if (cardName.contains("能量") || cardName.contains("energy")) {
            actions.add(new SimulatedGainEnergyAction(card.magicNumber));
        }
        
        return actions;
    }
    
    /**
     * 推断能力卡牌的动作
     * @param card 能力卡牌
     * @return 动作列表
     */
    private List<AbstractGameAction> inferPowerActions(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        // 能力卡牌通常会给自身或目标添加能力
        String cardName = card.name.toLowerCase();
        
        // 根据卡牌名称推断能力类型
        if (cardName.contains("力量") || cardName.contains("strength")) {
            actions.add(new SimulatedPowerAction("Strength", card.magicNumber, false));
        } else if (cardName.contains("敏捷") || cardName.contains("dexterity")) {
            actions.add(new SimulatedPowerAction("Dexterity", card.magicNumber, false));
        } else if (cardName.contains("仪式") || cardName.contains("form")) {
            actions.add(new SimulatedPowerAction("Form", card.magicNumber, false));
        } else {
            // 默认添加一个通用能力
            actions.add(new SimulatedPowerAction(cardName, card.magicNumber, false));
        }
        
        return actions;
    }
    
    /**
     * 分析卡牌的动作复杂度
     * @param card 卡牌对象
     * @return 复杂度等级（1-5）
     */
    public int analyzeCardComplexity(AbstractCard card) {
        if (card == null) {
            return 1;
        }
        
        int complexity = 1; // 基础复杂度
        
        // 根据卡牌类型调整
        switch (card.type) {
            case ATTACK:
                complexity += card.baseDamage > 10 ? 1 : 0;
                complexity += card.magicNumber > 0 ? 1 : 0;
                break;
            case SKILL:
                complexity += card.baseBlock > 10 ? 1 : 0;
                complexity += card.magicNumber > 0 ? 1 : 0;
                break;
            case POWER:
                complexity += 2; // 能力卡牌通常更复杂
                break;
        }
        
        // 根据卡牌描述长度调整
        if (card.rawDescription != null) {
            complexity += card.rawDescription.length() / 50;
        }
        
        return Math.min(5, Math.max(1, complexity));
    }
    
    /**
     * 检查卡牌是否包含特定动作类型
     * @param card 卡牌对象
     * @param actionType 动作类型
     * @return 是否包含
     */
    public boolean containsActionType(AbstractCard card, String actionType) {
        List<AbstractGameAction> actions = extractCardActions(card);
        
        for (AbstractGameAction action : actions) {
            if (action instanceof SimulatedDamageAction && actionType.equals("DAMAGE")) {
                return true;
            } else if (action instanceof SimulatedBlockAction && actionType.equals("BLOCK")) {
                return true;
            } else if (action instanceof SimulatedPowerAction && actionType.equals("POWER")) {
                return true;
            } else if (action instanceof SimulatedDrawCardAction && actionType.equals("DRAW")) {
                return true;
            } else if (action instanceof SimulatedGainEnergyAction && actionType.equals("ENERGY")) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 获取卡牌的主要动作类型
     * @param card 卡牌对象
     * @return 主要动作类型
     */
    public String getPrimaryActionType(AbstractCard card) {
        List<AbstractGameAction> actions = extractCardActions(card);
        
        if (actions.isEmpty()) {
            return "UNKNOWN";
        }
        
        // 优先级：攻击 > 防御 > 能力 > 其他
        for (AbstractGameAction action : actions) {
            if (action instanceof SimulatedDamageAction) {
                return "ATTACK";
            }
        }
        
        for (AbstractGameAction action : actions) {
            if (action instanceof SimulatedBlockAction) {
                return "DEFEND";
            }
        }
        
        for (AbstractGameAction action : actions) {
            if (action instanceof SimulatedPowerAction) {
                return "POWER";
            }
        }
        
        return "OTHER";
    }
}