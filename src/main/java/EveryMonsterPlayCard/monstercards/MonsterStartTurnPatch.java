package EveryMonsterPlayCard.monstercards;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.MonsterStartTurnAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Monster turn start patch
 * Triggers card playing logic at the start of monster turns
 */
@SpirePatch(
        clz = MonsterStartTurnAction.class,
        method = "update"
)
public class MonsterStartTurnPatch {
    private static final Logger logger = LogManager.getLogger(MonsterStartTurnPatch.class);

    @SpirePrefixPatch
    public static void Prefix(MonsterStartTurnAction __instance) {
        try {
            logger.info("MonsterStartTurnAction - Monster turn started");

            // Get monsters from current room
            if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom() != null &&
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters != null) {

                // 获取当前正在行动的怪物
                com.megacrit.cardcrawl.monsters.AbstractMonster currentMonster = null;
                try {
                    // 使用反射获取MonsterStartTurnAction中的monster字段
                    java.lang.reflect.Field monsterField = MonsterStartTurnAction.class.getDeclaredField("monster");
                    monsterField.setAccessible(true);
                    currentMonster = (com.megacrit.cardcrawl.monsters.AbstractMonster) monsterField.get(__instance);
                } catch (Exception e) {
                    logger.warn("无法获取当前行动的怪物，使用默认处理: " + e.getMessage());
                }

                if (currentMonster != null) {
                    // 只处理当前正在行动的怪物
                    if (!currentMonster.isDying && !currentMonster.isEscaping) {
                        logger.info("处理怪物回合开始: " + currentMonster.name);
                        MonsterCardManager.getInstance().onMonsterTurnStart(currentMonster);
                    }
                } else {
                    // 如果无法获取当前怪物，则回退到原来的处理方式
                    logger.warn("无法确定当前行动的怪物，使用默认处理方式");
                    for (com.megacrit.cardcrawl.monsters.AbstractMonster monster :
                            com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters.monsters) {

                        if (monster != null && !monster.isDying && !monster.isEscaping) {
                            // Call MonsterCardManager to handle monster turn start
                            MonsterCardManager.getInstance().onMonsterTurnStart(monster);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("MonsterStartTurnPatch error: " + e.getMessage(), e);
        }
    }
}