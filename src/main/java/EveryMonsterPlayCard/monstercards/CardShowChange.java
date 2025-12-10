package EveryMonsterPlayCard.monstercards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CardShowChange - 卡牌显示变更工具
 * 用于修改卡牌透明度、实现交互效果
 */
public class CardShowChange {

    //设置卡牌透明度的反射工具
    public static Method transparencyMethod = null;

    public static void initReflectMethod() {
        if (transparencyMethod != null) {
            return;
        }
        try {
            transparencyMethod = AbstractCard.class.getDeclaredMethod("updateTransparency");
            transparencyMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改卡牌透明度
     */
    public static void changeCardAlpha(AbstractCard card, float alpha) {
        initReflectMethod();
        try {
            card.fadingOut = false;
            card.transparency = alpha;
            card.targetTransparency = alpha;
            if (transparencyMethod != null) {
                transparencyMethod.invoke(card);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置卡牌为完全可见
     */
    public static void setCardFullyVisible(AbstractCard card) {
        changeCardAlpha(card, 1.0f);
    }

    /**
     * 设置卡牌为半透明
     */
    public static void setCardSemiTransparent(AbstractCard card) {
        changeCardAlpha(card, 0.3f);
    }

    /**
     * 根据能量设置卡牌透明度（智能多卡牌出牌系统专用）
     * @param card 卡牌
     * @param availableEnergy 可用能量
     * @param monster 关联的怪物（用于额外检查）
     */
    public static void setCardAlphaByEnergy(AbstractCard card, int availableEnergy, com.megacrit.cardcrawl.monsters.AbstractMonster monster) {
        boolean canPlay = canPlayCardWithEnergy(card, availableEnergy, monster);

        if (canPlay) {
            // 可以出的牌：半透明但不影响悬停效果
            changeCardAlpha(card, 0.7f); // 比正常稍暗，但比不可出牌亮
        } else {
            // 不可出的牌：很暗的半透明
            changeCardAlpha(card, 0.2f);
        }
    }

    /**
     * 检查卡牌是否可以基于能量打出（复制MonsterCardPlayer中的逻辑）
     */
    private static boolean canPlayCardWithEnergy(AbstractCard card, int availableEnergy, com.megacrit.cardcrawl.monsters.AbstractMonster monster) {
        // 检查能量是否足够
        int cardCost = Math.max(0, card.cost);
        if (cardCost > availableEnergy) {
            return false;
        }

        // 检查目标是否有效（如果需要目标）
        if (card.target == com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY) {
            // 需要敌人目标，检查玩家是否有效
            if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player == null ||
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.isDead ||
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.isEscaping) {
                return false;
            }
        }

        return true;
    }
}