package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.utils.Hpr;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
            // 移除频繁调用的调试信息，避免游戏卡顿
            // 只在关键状态变化时保留调试信息
            cardPlayer.enable();
        }
        // 启用出牌系统
        MonsterCardManager.getInstance().update();
        
        // 修复：添加UI渲染调用，确保卡牌能正常显示
        // 但只在这里调用一次，避免双重渲染
        if (AbstractDungeon.getCurrRoom() != null &&
            AbstractDungeon.getCurrRoom().monsters != null) {
            // 修复API调用问题
            // 注意：这里不应该在update中调用render，会导致性能问题
            // 移除这个调用，因为render应该在专门的render方法中调用
        }
    }
}
