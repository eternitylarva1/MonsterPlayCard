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
        changeCardAlpha(card, 0.5f);
    }
}