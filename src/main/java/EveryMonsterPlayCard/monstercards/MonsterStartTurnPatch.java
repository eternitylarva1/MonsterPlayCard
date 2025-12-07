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

                // Iterate through all monsters and trigger card playing logic
                for (com.megacrit.cardcrawl.monsters.AbstractMonster monster :
                        com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters.monsters) {

                    if (monster != null && !monster.isDying && !monster.isEscaping) {
                        // Call MonsterCardManager to handle monster turn start
                        MonsterCardManager.getInstance().onMonsterTurnStart(monster);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("MonsterStartTurnPatch error: " + e.getMessage(), e);
        }
    }
}