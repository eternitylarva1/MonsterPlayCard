package EveryMonsterPlayCard.core.events;

/**
 * 卡牌出牌事件
 * 当怪物出牌时触发
 */
public class CardPlayEvent {

    // 怪物ID
    public final int monsterId;
    // 卡牌ID
    public final int cardId;
    // 玩家ID
    public final int playerId;
    // 卡牌名称（用于日志）
    public final String cardName;
    // 事件时间戳
    public final long timestamp;

    public CardPlayEvent(int monsterId, int cardId, int playerId, String cardName) {
        this.monsterId = monsterId;
        this.cardId = cardId;
        this.playerId = playerId;
        this.cardName = cardName;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 简化的构造函数（不包含卡牌名称）
     */
    public CardPlayEvent(int monsterId, int cardId, int playerId) {
        this(monsterId, cardId, playerId, "Unknown");
    }

    @Override
    public String toString() {
        return String.format("CardPlayEvent{monsterId=%d, cardId=%d, cardName='%s', playerId=%d}",
                monsterId, cardId, cardName, playerId);
    }
}
