package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.utils.Hpr;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * AbstractMonster的更新Patch类
 * 用于在怪物的update方法中注入我们的出牌系统
 */
@SpirePatch(
    clz = AbstractMonster.class,
    method = "update",
    paramtypez = {}
)
public class AbstractMonsterPatch {

    @SpirePostfixPatch
    public static void postfix(AbstractMonster __instance) {
        // 在怪物update方法结束后更新怪物的出牌系统
        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(__instance);
        if (cardPlayer == null) {
            // 创建新的 CardPlayer
            cardPlayer = new MonsterCardPlayer(__instance);
            AbstractMonsterAddFieldPatch.setMonsterCardPlayer(__instance, cardPlayer);
            Hpr.info("为怪物 " + __instance.name + " 创建了CardPlayer");
            cardPlayer.enable();
        }
        // 启用出牌系统
        MonsterCardManager.getInstance().update();
    }
}
