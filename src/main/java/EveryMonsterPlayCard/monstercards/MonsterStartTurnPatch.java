package EveryMonsterPlayCard.monstercards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;
import basemod.interfaces.OnPlayerTurnStartSubscriber;

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
            logger.info("Player turn started - Recharging monster energy and drawing cards");

            // Get monsters from current room
            if (AbstractDungeon.getCurrRoom() != null &&
                AbstractDungeon.getCurrRoom().monsters != null) {

                // For all monsters: recharge energy and draw new cards
                for (com.megacrit.cardcrawl.monsters.AbstractMonster monster :
                        AbstractDungeon.getCurrRoom().monsters.monsters) {

                    if (monster != null && !monster.isDying && !monster.isEscaping) {
                        logger.info("补充怪物能量: " + monster.name);
                        MonsterCardManager.getInstance().rechargeMonsterEnergy(monster);
                        
                        // 重要：在玩家回合开始时让怪物抽新手牌
                        logger.info("怪物 " + monster.name + " 在玩家回合开始时抽新手牌");
                        MonsterCardManager.getInstance().onPlayerTurnStart(monster);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("MonsterStartTurnPatch error: " + e.getMessage(), e);
        }
    }
}