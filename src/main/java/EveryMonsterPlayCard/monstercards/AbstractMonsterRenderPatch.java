package EveryMonsterPlayCard.monstercards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.utils.Hpr;

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

    @SpirePostfixPatch
    public static void postfixPatch(AbstractMonster __instance, SpriteBatch sb) {
        // 在怪物render方法结束后渲染怪物的头顶卡牌
        try {
            MonsterCardManager.getInstance().renderMonster(sb, __instance);
        } catch (Exception e) {
            Hpr.info("渲染怪物卡牌时出错: " + e.getMessage());
        }
        
        // 渲染额外意图（在原有意图旁边显示卡牌信息）
        try {
            MonsterIntentRenderer.renderExtraIntent(sb, __instance);
        } catch (Exception e) {
            Hpr.info("渲染怪物额外意图时出错: " + e.getMessage());
        }
    }
}
