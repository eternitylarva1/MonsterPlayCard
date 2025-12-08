package EveryMonsterPlayCard.core.events;

import java.util.ArrayList;

/**
 * 手牌更新事件
 * 当怪物的手牌发生变化时触发
 */
public class HandUpdateEvent {

    // 怪物ID
    public final int monsterId;
    // 新的手牌ID列表
    public final ArrayList<Integer> cardIds;
    // 玩家ID（用于区分不同怪物）
    public final int playerId;
    // 事件时间戳
    public final long timestamp;

    public HandUpdateEvent(int monsterId, ArrayList<Integer> cardIds, int playerId) {
        this.monsterId = monsterId;
        this.cardIds = new ArrayList<>(cardIds); // 复制列表避免外部修改
        this.playerId = playerId;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 获取手牌数量
     */
    public int getCardCount() {
        return cardIds.size();
    }

    /**
     * 检查是否包含指定卡牌
     */
    public boolean containsCard(int cardId) {
        return cardIds.contains(cardId);
    }

    @Override
    public String toString() {
        return String.format("HandUpdateEvent{monsterId=%d, cardCount=%d, playerId=%d}",
                monsterId, cardIds.size(), playerId);
    }
}
