package EveryMonsterPlayCard.monstercards;

import basemod.BaseMod;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import EveryMonsterPlayCard.utils.Hpr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MonsterTurnStartPatch - 怪物回合开始补丁
 * 实现PreMonsterTurnSubscriber接口，在每个怪物回合开始时触发怪物出牌
 */
public class MonsterTurnStartPatch implements PreMonsterTurnSubscriber {
    private static final Logger logger = LogManager.getLogger(MonsterTurnStartPatch.class);

    public MonsterTurnStartPatch() {
        // 订阅怪物回合开始事件
        BaseMod.subscribe(this);
        logger.info("MonsterTurnStartPatch已订阅PreMonsterTurn事件");
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster monster) {
        try {
            if (monster == null) {
                logger.warn("receivePreMonsterTurn: monster为null");
                return true;
            }

            // 移除频繁调用的调试信息，避免游戏卡顿
            // 只在关键状态变化时保留调试信息
            
            // 调用MonsterCardManager的onMonsterTurnStart方法
            MonsterCardManager.getInstance().onMonsterTurnStart(monster);
            
            // 返回true表示允许怪物正常执行其回合
            return true;
        } catch (Exception e) {
            logger.error("MonsterTurnStartPatch错误: " + e.getMessage(), e);
            // 发生错误时仍然允许怪物正常执行其回合
            return true;
        }
    }
}