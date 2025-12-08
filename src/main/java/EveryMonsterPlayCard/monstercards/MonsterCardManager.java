package EveryMonsterPlayCard.monstercards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * MonsterCardManager - 使用 SpireField 的怪物出牌系统管理器
 * 为每个怪物实例直接分配 MonsterCardPlayer，避免ID冲突
 */
public class MonsterCardManager {

    // 静态实例（单例模式）
    private static MonsterCardManager instance = null;

    private MonsterCardManager() {
        // 不再使用 HashMap，直接使用 SpireField
    }

    /**
     * 获取单例实例
     */
    public static MonsterCardManager getInstance() {
        if (instance == null) {
            instance = new MonsterCardManager();
        }
        return instance;
    }

    /**
     * 为怪物启用出牌系统（使用 SpireField）
     */
    public void enableMonster(AbstractMonster monster) {
        if (monster == null) {
            return;
        }

        // 检查是否已经有 CardPlayer
        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
        if (cardPlayer == null) {
            // 创建新的 CardPlayer
            cardPlayer = new MonsterCardPlayer(monster);
            AbstractMonsterAddFieldPatch.setMonsterCardPlayer(monster, cardPlayer);
            Hpr.info("为怪物 " + monster.name + " 创建了CardPlayer");
        }

        // 启用出牌系统
        cardPlayer.enable();
        Hpr.info("已启用怪物 " + monster.name + " 的出牌系统");
    }

    /**
     * 为怪物禁用出牌系统
     */
    public void disableMonster(AbstractMonster monster) {
        if (monster == null) {
            return;
        }

        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
        if (cardPlayer != null) {
            cardPlayer.disable();
            Hpr.info("已禁用怪物 " + monster.name + " 的出牌系统");
        }
    }

    /**
     * 渲染所有启用出牌系统的怪物的UI
     */
    public void renderUI(SpriteBatch sb) {
        if (AbstractDungeon.getCurrRoom() == null ||
            AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
            if (cardPlayer != null && cardPlayer.isEnabled()) {
                cardPlayer.renderUI(sb);
            // 渲染头顶卡牌
            cardPlayer.render(sb);
            }
        }
    }

    /**
     * 更新所有启用出牌系统的怪物的UI
     */
    public void updateUI() {
        if (AbstractDungeon.getCurrRoom() == null ||
            AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
            if (cardPlayer != null && cardPlayer.isEnabled()) {
                cardPlayer.updateUI();
            }
        }
    }

    /**
     * 处理怪物回合开始事件
     */
    public void onMonsterTurnStart(AbstractMonster monster) {
        if (monster == null) {
            return;
        }

        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);

        if (cardPlayer != null && cardPlayer.isEnabled()) {
            cardPlayer.onTurnStart();
            Hpr.info("怪物回合开始触发出牌: " + monster.name);
        }
    }

    /**
     * 更新所有怪物的出牌系统（主要用于卡牌显示更新）
     */
    public void update() {
        // 遍历当前房间的所有怪物
        if (AbstractDungeon.getCurrRoom() != null &&
            AbstractDungeon.getCurrRoom().monsters != null &&
            !AbstractDungeon.getCurrRoom().monsters.monsters.isEmpty()) {

            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
                if (cardPlayer != null && cardPlayer.isEnabled()) {
                    cardPlayer.updateUI();
                }
            }
        }
    }

    /**
     * 渲染指定怪物的头顶卡牌
     */
    public void renderMonster(SpriteBatch sb, AbstractMonster monster) {
        if (monster == null) {
            return;
        }

        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
        if (cardPlayer != null && cardPlayer.isEnabled()) {
            cardPlayer.renderUI(sb);
            // 渲染头顶卡牌
            cardPlayer.render(sb);
        }
    }

    /**
     * 为当前房间的所有怪物启用出牌系统
     */
    public void enableRoomMonsters() {
        if (AbstractDungeon.getCurrRoom() == null ||
            AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }

        int enabledCount = 0;
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster != null && !monster.isDying && !monster.isEscaping&&!monster.isDeadOrEscaped()) {
                enableMonster(monster);
                enabledCount++;
            }
        }

        Hpr.info("为当前房间启用了 " + enabledCount + " 个怪物的出牌系统");
        Hpr.info("怪物组初始化，为 " + enabledCount + " 个怪物启用了出牌系统");
    }

    /**
     * 禁用当前房间的所有怪物的出牌系统
     */
    public void disableRoomMonsters() {
        if (AbstractDungeon.getCurrRoom() == null ||
            AbstractDungeon.getCurrRoom().monsters == null) {
            return;
        }

        int disabledCount = 0;
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster != null) {
                MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
                if (cardPlayer != null && cardPlayer.isEnabled()) {
                    disableMonster(monster);
                    disabledCount++;
                }
            }
        }

        Hpr.info("为当前房间禁用了 " + disabledCount + " 个怪物的出牌系统");
    }

    /**
     * 重置所有系统（用于战斗重置）
     */
    public void resetAll() {
        // 禁用当前房间所有怪物
        disableRoomMonsters();

        // 清理所有怪物的 SpireField
        if (AbstractDungeon.getCurrRoom() != null &&
            AbstractDungeon.getCurrRoom().monsters != null) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (monster != null) {
                    AbstractMonsterAddFieldPatch.clearMonsterCardPlayer(monster);
                }
            }
        }

        Hpr.info("MonsterCardManager系统已完全重置");
    }
}
