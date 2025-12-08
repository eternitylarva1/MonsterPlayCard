package EveryMonsterPlayCard.monstercards;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

/**
 * UnlockPatch - 解决所有卡牌的显示问题
 * 当判断一个牌能不能被显示时，直接永远显示true
 */
public class UnlockPatch {

    @SpirePatch(clz = UnlockTracker.class, method = "isCardSeen")
    public static class UnlockAllCard {
        @SpirePrefixPatch
        public static SpireReturn<Boolean> fix(String key) {
            //直接默认返回true，让所有卡牌都能显示
            return SpireReturn.Return(true);
        }
    }
}