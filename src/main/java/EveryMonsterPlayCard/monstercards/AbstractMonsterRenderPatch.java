package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.utils.Hpr;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * AbstractMonster的渲染Patch类
 * 用于在怪物render方法中渲染头顶卡牌
 */
@SpirePatch(
    clz = AbstractMonster.class,
    method = "render",
    paramtypez = {SpriteBatch.class}
)
class AbstractMonsterRenderPatch {

    @SpireInsertPatch(
        loc = 999,  // 在render方法的最后插入
        rloc = 999
    )
    public static void insert(AbstractMonster __instance, SpriteBatch sb) {
        // 在怪物render方法结束后渲染怪物的头顶卡牌
        try {
            MonsterCardManager.getInstance().renderMonster(sb, __instance);
        } catch (Exception e) {
            Hpr.info("渲染怪物卡牌时出错: " + e.getMessage());
        }
    }
}
