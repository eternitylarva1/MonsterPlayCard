package EveryMonsterPlayCard.core.events;

/**
 * 回合结束事件
 * 当怪物回合结束时触发
 */
public class TurnEndEvent {

    // 怪物ID
    public final int monsterId;
    // 玩家ID
    public final int playerId;
    // 当前回合数
    public final int turnNumber;
    // 剩余能量
    public final int remainingEnergy;
    // 出牌数量
    public final int cardsPlayed;
    // 事件时间戳
    public final long timestamp;

    public TurnEndEvent(int monsterId, int playerId, int turnNumber, int remainingEnergy, int cardsPlayed) {
        this.monsterId = monsterId;
        this.playerId = playerId;
        this.turnNumber = turnNumber;
        this.remainingEnergy = remainingEnergy;
        this.cardsPlayed = cardsPlayed;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 简化构造函数
     */
    public TurnEndEvent(int monsterId, int playerId) {
        this(monsterId, playerId, 1, 0, 0); // 默认值
    }

    @Override
    public String toString() {
        return String.format("TurnEndEvent{monsterId=%d, playerId=%d, turnNumber=%d, remainingEnergy=%d, cardsPlayed=%d}",
                monsterId, playerId, turnNumber, remainingEnergy, cardsPlayed);
    }
}
