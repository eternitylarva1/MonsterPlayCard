package EveryMonsterPlayCard.monstercards;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * CardCostPatch - 修复怪物卡牌费用问题
 * 根据PVP玩家系统的实现，怪物卡牌应该有适当的费用，而不是0费
 */
public class CardCostPatch {

    @SpirePatch(clz = AbstractCard.class, method = "initializeTitle")
    public static class FixMonsterCardCosts {
        @SpirePrefixPatch
        public static void fix(AbstractCard __instance) {
            // 检查是否是怪物卡牌（通过卡牌ID或类型判断）

        }
    }

    /**
     * 判断是否是怪物卡牌
     * 这里可以根据卡牌ID、名称或其他特征来判断
     */
    private static boolean isMonsterCard(AbstractCard card) {
        // 如果卡牌名称包含"怪物"或者是我们自定义的怪物卡牌
        if (card.name != null && (
            card.name.contains("怪物") ||
            card.name.contains("Monster") ||
            card.cardID.startsWith("Monster") ||
            card.cardID.startsWith("En")  // PVP系统中的怪物卡牌前缀
        )) {
            return true;
        }

        // 也可以通过卡牌类型或其他属性来判断
        // 例如：如果卡牌的颜色是COLORLESS且是我们添加的
        return false;
    }
}