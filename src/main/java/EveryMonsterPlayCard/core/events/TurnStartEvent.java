package EveryMonsterPlayCard.core.events;

/**
 * 回合开始事件
 * 当怪物回合开始时触发
 */
public class TurnStartEvent {

    // 怪物ID
    public final int monsterId;
    // 玩家ID
    public final int playerId;
    // 当前回合数
    public final int turnNumber;
    // 当前能量
    public final int currentEnergy;
    // 事件时间戳
    public final long timestamp;

    public TurnStartEvent(int monsterId, int playerId, int turnNumber, int currentEnergy) {
        this.monsterId = monsterId;
        this.playerId = playerId;
        this.turnNumber = turnNumber;
        this.currentEnergy = currentEnergy;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 简化构造函数
     */
    public TurnStartEvent(int monsterId, int playerId) {
        this(monsterId, playerId, 1, 3); // 默认值
    }

    @Override
    public String toString() {
        return String.format("TurnStartEvent{monsterId=%d, playerId=%d, turnNumber=%d, energy=%d}",
                monsterId, playerId, turnNumber, currentEnergy);
    }
}
