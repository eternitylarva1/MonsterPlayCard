package EveryMonsterPlayCard.monstercards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import basemod.interfaces.PostBattleSubscriber;

/**
 * MonsterTurnEndPatch - 怪物回合结束补丁
 * 使用OnPlayerTurnStartSubscriber接口，在玩家回合开始时处理上一回合怪物的手牌
 * 同时使用PostBattleSubscriber接口，在战斗结束时清理手牌
 */
public class MonsterTurnEndPatch implements OnPlayerTurnStartSubscriber, PostBattleSubscriber {
    private static final Logger logger = LogManager.getLogger(MonsterTurnEndPatch.class);

    public MonsterTurnEndPatch() {
        // 订阅玩家回合开始事件和战斗结束事件
        BaseMod.subscribe(this);
        logger.info("MonsterTurnEndPatch已订阅OnPlayerTurnStart和PostBattle事件");
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        try {

            // 修复：不应该在玩家回合开始时清空怪物手牌
            // 这会导致怪物手牌被清空，无法显示卡牌
            // 怪物手牌应该在怪物真正回合结束时才清空
            
            // 注释掉清空手牌的逻辑，改为只补充能量
            /*
            // 在玩家回合开始时，处理所有怪物的手牌
            if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().monsters != null) {
                for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (monster != null && !monster.isDead && !monster.isDying && !monster.isEscaping) {
                        MonsterCardManager.getInstance().onMonsterTurnEnd(monster);
                    }
                }
            }
            */
            
        } catch (Exception e) {
            logger.error("MonsterTurnEndPatch错误: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void receivePostBattle(AbstractRoom room) {
        try {
            logger.info("战斗结束，清理怪物手牌");
            
            // 在战斗结束时，清理所有怪物的手牌
            if (room != null && room.monsters != null) {
                for (AbstractMonster monster : room.monsters.monsters) {
                    if (monster != null) {
                        MonsterCardManager.getInstance().onMonsterTurnEnd(monster);
                    }
                }
            }
            
        } catch (Exception e) {
            logger.error("MonsterTurnEndPatch战斗结束处理错误: " + e.getMessage(), e);
        }
    }
}