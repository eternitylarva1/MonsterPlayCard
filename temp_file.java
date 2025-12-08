package EveryMonsterPlayCard.automation;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import EveryMonsterPlayCard.core.LocalEventBus;
import EveryMonsterPlayCard.core.events.CardPlayEvent;
import EveryMonsterPlayCard.core.events.TurnEndEvent;
import EveryMonsterPlayCard.core.events.TurnStartEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * SimplifiedAutoPlayManager - 简化的自动出牌管理器
 * 实现固定出牌序列：从左往右，按费用判断，5张手牌机制，弃牌堆
 */
public class SimplifiedAutoPlayManager {
    private static SimplifiedAutoPlayManager instance;
    private boolean isEnabled = true;
    private boolean isInAutoMode = false;
    private int turnCount = 0;
    private int handSize = 5; // 每回合固定手牌数
    private List<AbstractCard> discardPile = new ArrayList<>(); // 弃牌堆
    private List<AbstractCard> hand = new ArrayList<>(); // 当前手牌

    private SimplifiedAutoPlayManager() {
        // 初始化弃牌堆
        discardPile = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public static SimplifiedAutoPlayManager getInstance() {
        if (instance == null) {
            instance = new SimplifiedAutoPlayManager();
        }
        return instance;
    }

    /**
     * 启用自动出牌
     */
    public void enableAutoPlay() {
        isEnabled = true;
    }

    /**
     * 禁用自动出牌
     */
    public void disableAutoPlay() {
        isEnabled = false;
        isInAutoMode = false;
    }

    /**
     * 开始回合
     */
    public void onTurnStart() {
        if (!isEnabled) return;

        turnCount++;
        isInAutoMode = true;

        // 抽5张牌（从弃牌堆或卡牌池）
        drawCards(handSize);

        // 清空上一回合的已出牌记录
        // 发送回合开始事件
        LocalEventBus.getInstance().post(new TurnStartEvent(turnCount));
    }

    /**
     * 回合结束
     */
    public void onTurnEnd() {
        if (!isEnabled || !isInAutoMode) return;

        isInAutoMode = false;

        // 当前手牌全部放入弃牌堆
        discardPile.addAll(hand);
        hand.clear();

        // 发送回合结束事件
        LocalEventBus.getInstance().post(new TurnEndEvent(turnCount));
    }

    /**
     * 自动出牌逻辑：从左往右，按费用判断
     */
    public AbstractCard autoPlayCard(List<AbstractCard> availableCards, int currentEnergy, AbstractCreature target, AbstractMonster monster) {
        if (!isEnabled || !isInAutoMode || availableCards.isEmpty() || currentEnergy <= 0) {
            return null;
        }

        // 按固定顺序（从左往右）检查每张卡牌
        for (int i = 0; i < availableCards.size(); i++) {
            AbstractCard card = availableCards.get(i);

            // 检查费用是否足够
            if (card.cost <= currentEnergy && card.canUse(monster, target)) {
                // 尝试出牌
                if (tryPlayCard(card, target, monster)) {
                    // 出牌成功，更新手牌
                    updateHandAfterPlay(card);

                    // 发送卡牌使用事件
                    LocalEventBus.getInstance().post(new CardPlayEvent(card, target, monster, true));

                    return card;
                }
            }
        }

        return null;
    }

    /**
     * 尝试出牌
     */
    private boolean tryPlayCard(AbstractCard card, AbstractCreature target, AbstractMonster monster) {
        try {
            // 检查卡牌是否可以出
            if (card.canUse(monster, target)) {
                // 这里可以添加具体的出牌逻辑
                // 由于这是在模拟环境，实际出牌逻辑由MonsterCardPlayer处理
                return true;
            }
        } catch (Exception e) {
            // 记录错误但不中断流程
            System.err.println("出牌失败: " + card.name + ", 错误: " + e.getMessage());
        }
        return false;
    }

    /**
     * 出牌后更新手牌
     */
    private void updateHandAfterPlay(AbstractCard card) {
        // 从手牌中移除已出的牌
        hand.remove(card);
    }

    /**
     * 抽牌机制
     */
    private void drawCards(int cardCount) {
        // 清空当前手牌
        hand.clear();

        // 模拟抽牌（这里应该从实际的卡牌库中获取）
        // 由于这是简化版本，我们假设卡牌池有足够的牌
        for (int i = 0; i < cardCount; i++) {
            // 这里应该实现真正的抽牌逻辑
            // 暂时用null表示，实际使用时需要替换为真实的卡牌
            hand.add(null);
        }
    }

    /**
     * 获取当前手牌
     */
    public List<AbstractCard> getCurrentHand() {
        return new ArrayList<>(hand);
    }

    /**
     * 获取弃牌堆
     */
    public List<AbstractCard> getDiscardPile() {
        return new ArrayList<>(discardPile);
    }

    /**
     * 添加卡牌到弃牌堆
     */
    public void addToDiscardPile(AbstractCard card) {
        if (card != null) {
            discardPile.add(card);
        }
    }

    /**
     * 检查是否应该继续自动出牌
     */
    public boolean shouldContinueAutoPlay(int currentEnergy) {
        if (!isEnabled || !isInAutoMode || currentEnergy <= 0) {
            return false;
        }

        // 检查当前手牌中是否有费用足够的牌
        for (AbstractCard card : hand) {
            if (card != null && card.cost <= currentEnergy) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取当前回合数
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * 检查自动模式是否启用
     */
    public boolean isAutoPlayEnabled() {
        return isEnabled;
    }

    /**
     * 检查当前是否处于自动模式
     */
    public boolean isInAutoPlayMode() {
        return isInAutoMode;
    }

    /**
     * 重置状态
     */
    public void reset() {
        isInAutoMode = false;
        hand.clear();
        discardPile.clear();
        turnCount = 0;
    }

    /**
     * 获取手牌数量
     */
    public int getHandSize() {
        return hand.size();
    }

    /**
     * 获取弃牌堆数量
     */
    public int getDiscardPileSize() {
        return discardPile.size();
    }
}