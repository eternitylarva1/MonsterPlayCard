package EveryMonsterPlayCard.monstercards;

import basemod.BaseMod;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Player turn start patch
 * Triggers monster energy recharge at the start of player turn
 */
public class MonsterStartTurnPatch implements OnPlayerTurnStartSubscriber {
    private static final Logger logger = LogManager.getLogger(MonsterStartTurnPatch.class);

    public MonsterStartTurnPatch() {
        // Subscribe to player turn start events
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        try {
            logger.info("Player turn started - Recharging monster energy");

            // Get monsters from current room
            if (AbstractDungeon.getCurrRoom() != null &&
                AbstractDungeon.getCurrRoom().monsters != null) {

                // Recharge energy for all monsters
                for (com.megacrit.cardcrawl.monsters.AbstractMonster monster :
                        AbstractDungeon.getCurrRoom().monsters.monsters) {

                    if (monster != null && !monster.isDying && !monster.isEscaping) {
                        logger.info("补充怪物能量: " + monster.name);
                        MonsterCardManager.getInstance().rechargeMonsterEnergy(monster);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("MonsterStartTurnPatch error: " + e.getMessage(), e);
        }
    }
}