package EveryMonsterPlayCard.monstercards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import EveryMonsterPlayCard.core.LocalEventBus;
import EveryMonsterPlayCard.core.events.CardPlayEvent;
import EveryMonsterPlayCard.core.events.EnergyUpdateEvent;
import EveryMonsterPlayCard.core.events.HandUpdateEvent;
import EveryMonsterPlayCard.core.events.TurnStartEvent;
import EveryMonsterPlayCard.monstercards.actions.executeMonsterCardAction;
import EveryMonsterPlayCard.monstercards.cards.MonsterAttackCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterPowerCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterSkillCard;
import EveryMonsterPlayCard.ui.BattleUI.BattleCardPanel;
import EveryMonsterPlayCard.ui.BattleUI.CardRecorder;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * MonsterCardPlayer - 改进版怪物出牌系统
 * 怪物回合开始时自动出牌
 */
public class MonsterCardPlayer {
    // 关联的怪物
    public AbstractMonster monster;

    // 卡牌配置管理器
    private MonsterCardConfig cardConfig;

    // 独立牌库系统
    private CardGroup monsterDrawPile;                     // 怪物抽牌堆
    private CardGroup monsterDiscardPile;                  // 怪物弃牌堆
    private CardGroup monsterHand;                          // 怪物手牌

    // 当前显示的卡牌（用于显示手牌堆）
    private List<AbstractCard> displayedCards = new ArrayList<>();        // 当前显示在头顶的卡牌列表
    private float cardDisplayTimer = 0.0f;                // 卡牌显示计时器
    // 卡牌显示控制
    private boolean initialDisplaySetup = false;         // 是否已经设置初始显示

    // 出牌控制
    private boolean enabled = false;                       // 是否启用出牌系统
    private boolean hasPlayedCardThisTurn = false;         // 当前回合是否已出牌

    // UI组件 (从PVP系统移植)
    public BattleCardPanel battleCardPanel;                // 战斗信息面板
    public PlayerCardManager cardManager;                  // 卡牌管理器
    public CardRecorder cardRecorder;                      // 卡牌记录器

    // 当前能量
    private int currentEnergy = 3;
    // 能量上限
    private int maxEnergy = 3;

    // 事件驱动系统 (从PVP系统移植)
    private LocalEventBus eventBus;                           // 事件总线
    private int playerId;                                     // 玩家ID（用于事件区分）
    private int turnNumber = 1;                               // 当前回合数
    private int cardsPlayedThisTurn = 0;                      // 当前回合出牌数
    private int previousEnergy = 3;                           // 之前的能量（用于事件）

    // 卡牌显示位置
    private static final float CARD_DISPLAY_HEIGHT = 80.0f * Settings.scale;
    private static final float CARD_HOVER_SCALE = 0.6f;
    private static final float CARD_NORMAL_SCALE = 0.4f;
    private static final float CARD_OFFSET_X = 15.0f * Settings.scale;  // 卡牌之间的水平偏移
    private static final float CARD_OFFSET_Y = 5.0f * Settings.scale;   // 卡牌之间的垂直偏移

    public MonsterCardPlayer(AbstractMonster monster) {
        this.monster = monster;
        this.cardConfig = MonsterCardConfig.getInstance();

        // 初始化UI组件 (从PVP系统移植)
        this.cardManager = new PlayerCardManager();
        this.cardRecorder = new CardRecorder();

        // 初始化战斗信息面板
        this.battleCardPanel = new BattleCardPanel(
            monster.drawX, monster.drawY + monster.hb_h * 1.5f,
            cardRecorder, monster
        );

        // 重要：设置cardPlayer引用，确保BattleCardPanel能正确获取能量信息
        this.battleCardPanel.setCardPlayer(this);

        // 初始化能量面板（使用新的怪物专用接口）
        if (this.battleCardPanel != null && this.battleCardPanel.energyPanel != null) {
            this.battleCardPanel.energyPanel.init(this.monster, maxEnergy);
        }

        // 初始化事件系统
        initializeEventSystem();

        // 初始化卡牌系统
        initializeCardSystem();

        // 设置初始能量显示
        setEnergy(currentEnergy);
    }

    /**
     * 初始化事件系统
     */
    private void initializeEventSystem() {
        // 获取事件总线实例
        this.eventBus = LocalEventBus.getInstance();

        // 生成玩家ID（使用怪物的哈希码）
        this.playerId = monster.hashCode();

        // 注册事件监听器
        registerEventListeners();

        Hpr.info("为怪物 " + monster.name + " 初始化事件系统，玩家ID: " + playerId);
    }

    /**
     * 注册事件监听器
     */
    private void registerEventListeners() {
        // 注册HandUpdateEvent处理器，避免"没有找到事件处理器"的警告
        eventBus.registerEvent(HandUpdateEvent.class, (event) -> {
            // 可以在这里处理手牌更新事件，目前暂时留空
            Hpr.info("收到手牌更新事件: " + event.toString());
        });
    }

    /**
     * 初始化怪物的牌库系统（基于统一卡牌池）
     */
    private void initializeCardSystem() {
        // 初始化牌库
        monsterDrawPile = new CardGroup(CardGroup.CardGroupType.DRAW_PILE);
        monsterDiscardPile = new CardGroup(CardGroup.CardGroupType.DISCARD_PILE);
        monsterHand = new CardGroup(CardGroup.CardGroupType.HAND);

        // 获取统一卡牌池
        ArrayList<AbstractCard> monsterCards = getUniversalCards();
        if (monsterCards.isEmpty()) {
            Hpr.info("警告：怪物 " + monster.name + " 没有可用的卡牌");
            return;
        }

        // 洗牌并初始化抽牌堆
        shuffleIntoDrawPile(monsterCards);

        Hpr.info("怪物CardPlayer初始化完成: " + monster.name + " ，牌库包含 " + monsterDrawPile.size() + " 张卡牌");

        // 初始化手牌 - 战斗开始时抽5张牌到手牌
        drawCardsToHand(5);
    }

    /**
     * 获取统一怪物卡牌池
     */
    private ArrayList<AbstractCard> getUniversalCards() {
        ArrayList<AbstractCard> cards = new ArrayList<>();

        // 直接从配置管理器获取统一卡牌池
        List<AbstractCard> configCards = cardConfig.getMonsterCardConfig(monster.id);
        if (configCards != null && !configCards.isEmpty()) {
            cards.addAll(configCards);
            Hpr.info("为怪物 " + monster.name + " 加载了统一卡牌池，包含 " + cards.size() + " 张卡牌");
        } else {
            // 如果配置中没有卡牌，创建默认的怪物卡牌
            Hpr.info("为怪物 " + monster.name + " 创建了默认怪物卡牌池，包含 " + cards.size() + " 张卡牌");
        }

        return cards;
    }

    /**
     * 洗牌到抽牌堆
     */
    private void shuffleIntoDrawPile(ArrayList<AbstractCard> cards) {
        monsterDrawPile.clear();

        if (cards == null || cards.isEmpty()) {
            Hpr.info("警告：怪物 " + monster.name + " 没有可用的卡牌");
            return;
        }

        // 添加卡牌到抽牌堆
        for (AbstractCard card : cards) {
            if (card != null) {
                // 重要：设置卡牌的拥有怪物，确保applyPowers正确工作
                if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                    ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card).setOwningMonster(monster);
                }
                monsterDrawPile.addToBottom(card);
            }
        }

        // 洗牌
        ArrayList<AbstractCard> tempList = new ArrayList<>();
        tempList.addAll(monsterDrawPile.group);
        Collections.shuffle(tempList, AbstractDungeon.cardRandomRng.random);
        monsterDrawPile.group = tempList;

        Hpr.info("怪物 " + monster.name + " 洗牌完成，抽牌堆包含 " + monsterDrawPile.size() + " 张卡牌");
        
        // 调试：打印抽牌堆中的所有卡牌名称
        Hpr.info("怪物 " + monster.name + " 抽牌堆包含的卡牌：");
        for (int i = 0; i < monsterDrawPile.group.size(); i++) {
            AbstractCard card = monsterDrawPile.group.get(i);
            Hpr.info("  [" + i + "] " + card.name + " (ID: " + card.cardID + ")");
        }
    }

    /**
     * 启用出牌系统
     */
    public void enable() {
        if (!enabled) {
            enabled = true;
            hasPlayedCardThisTurn = false;
            Hpr.info("怪物 " + monster.name + " 出牌系统已启用");
        }
    }

    /**
     * 禁用出牌系统
     */
    public void disable() {
        if (enabled) {
            enabled = false;
            displayedCards.clear();
            cardDisplayTimer = 0.0f;
            Hpr.info("怪物 " + monster.name + " 出牌系统已禁用");
        }
    }

    /**
     * 怪物回合开始时调用（由补丁触发）
     */
    public void onTurnStart() {
        if (!enabled || monster == null) {
            return;
        }

        // 重置回合标记
        hasPlayedCardThisTurn = false;
        cardsPlayedThisTurn = 0;

        // 修复：每个怪物回合开始时补充能量到上限
        refillEnergyToMax();

        // 重要：在抽新手牌前，先清空上一回合的手牌
        // 这样可以避免手牌堆积，确保每个回合都是新的手牌
        if (monsterHand != null && !monsterHand.isEmpty()) {
            int handSize = monsterHand.size();
            while (!monsterHand.isEmpty()) {
                AbstractCard card = monsterHand.getTopCard();
                monsterDiscardPile.addToBottom(card.makeStatEquivalentCopy());
                monsterHand.removeCard(card);
            }
            Hpr.info("怪物 " + monster.name + " 回合开始，清空上一回合的 " + handSize + " 张手牌");
        }

        // 每回合开始时抽5张牌到手牌
        drawCardsToHand(5);

        // 发送回合开始事件
        sendTurnStartEvent();

        // 出牌（每个怪物回合出1张牌）
        playCardForTurn();

        Hpr.info("怪物 " + monster.name + " 回合开始，已出牌: " + hasPlayedCardThisTurn);
    }

    /**
     * 怪物回合结束时调用（处理手牌）
     */
    public void onTurnEnd() {
        if (!enabled || monster == null) {
            return;
        }

        // 将剩余手牌全部放入弃牌堆
        if (monsterHand != null && !monsterHand.isEmpty()) {
            int handSize = monsterHand.size();
            while (!monsterHand.isEmpty()) {
                AbstractCard card = monsterHand.getTopCard();
                monsterDiscardPile.addToBottom(card.makeStatEquivalentCopy());
                monsterHand.removeCard(card);
            }
            Hpr.info("怪物 " + monster.name + " 回合结束，将 " + handSize + " 张手牌放入弃牌堆");
        }

        Hpr.info("怪物 " + monster.name + " 回合结束");
    }

    /**
     * 发送回合开始事件
     */
    private void sendTurnStartEvent() {
        if (eventBus != null) {
            TurnStartEvent event = new TurnStartEvent(monster.hashCode(), playerId, turnNumber, currentEnergy);
            eventBus.sendEvent(event);
            Hpr.info("发送回合开始事件: " + event.toString());
        }
    }

    /**
     * 更新出牌逻辑（主要用于卡牌显示更新）
     */
    public void update() {
        if (!enabled || monster == null) {
            return;
        }

        // 检查当前是否处于非战斗状态（如商店、休息等），如果是则不更新卡牌
        // 这样可以避免在非战斗场景中显示卡牌，同时确保战斗中正常显示
        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE) {
            return;
        }

        // 每次更新都刷新显示的卡牌列表
        refreshDisplayedCards();

        // 移除时间限制 - 卡牌持续显示
        // 卡牌现在会一直显示在怪物头顶，不会自动消失
    }

    /**
     * 为当前回合出牌（修改为分离的动画和使用流程）
     */
    private void playCardForTurn() {
        if (hasPlayedCardThisTurn) {
            return; // 已经出过牌了（这个标志现在改为"回合开始"标志）
        }

        // 刷新显示的牌列表（显示手牌）
        refreshDisplayedCards();

        // 分离的卡牌动画和使用流程
        playCardsWithAnimation();

        // 标记本回合已开始（每个怪物回合只调用一次）
        hasPlayedCardThisTurn = true;
    }

    /**
     * 分离的卡牌动画和使用流程
     * 顺序：怪物回合开始-第一张卡牌打出动画-第一张卡牌使用-第二张卡牌打出动画-第二张卡牌使用...
     */
    private void playCardsWithAnimation() {
        int availableEnergy = currentEnergy;
        int cardsPlayed = 0;

        Hpr.info("怪物 " + monster.name + " 开始分离式出牌流程，可用能量: " + availableEnergy);

        // 循环出牌直到能量不够或手牌为空
        while (availableEnergy > 0 && (monsterHand != null && !monsterHand.isEmpty())) {
            // 获取当前可出的最高优先级卡牌
            AbstractCard cardToPlay = getBestPlayableCard(availableEnergy);

            if (cardToPlay == null) {
                Hpr.info("怪物 " + monster.name + " 没有足够的能量出更多牌");
                break;
            }

            // 计算出牌后的能量
            int cardCost = Math.max(0, cardToPlay.cost); // 确保成本不为负
            
            // 重要：在出牌前再次检查能量是否足够
            if (cardCost > availableEnergy) {
                Hpr.info("怪物 " + monster.name + " 能量不足，无法打出卡牌 " + cardToPlay.name + "，需要 " + cardCost + "，可用 " + availableEnergy);
                break;
            }
            
            availableEnergy -= cardCost;

            // 从手牌移除卡牌
            removeCardFromHand(cardToPlay);
            // 使用卡牌效果
            executeMonsterCard(cardToPlay);

            // 发送卡牌出牌事件
            sendCardPlayEvent(cardToPlay);

            cardsPlayed++;
            cardsPlayedThisTurn++;

            Hpr.info("怪物 " + monster.name + " 打出第 " + cardsPlayed + " 张牌: " + cardToPlay.name +
                    " (消耗能量: " + cardCost + ", 剩余能量: " + availableEnergy + ")");

            // 添加出牌间隔
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));

            // 刷新显示（更新透明度）
            refreshDisplayedCards();
            setEnergy(availableEnergy);
        }

        Hpr.info("怪物 " + monster.name + " 本回合共打出 " + cardsPlayed + " 张牌，剩余能量: " + availableEnergy);
    }



    /**
     * 获取当前可出的最高优先级卡牌（从左往右顺序）
     */
    private AbstractCard getBestPlayableCard(int availableEnergy) {
        // 修改为从左往右顺序出牌，而不是基于优先级
        // 手牌的索引0就是最左边的牌
        for (AbstractCard card : monsterHand.group) {
            if (canPlayCard(card, availableEnergy)) {
                return card; // 返回第一个可以出的牌（从左往右）
            }
        }

        return null; // 没有可出的牌
    }

    /**
     * 检查是否可以打出某张牌
     */
    private boolean canPlayCard(AbstractCard card, int availableEnergy) {
        // 检查能量是否足够
        int cardCost = Math.max(0, card.cost);
        if (cardCost > availableEnergy) {
            // 移除详细日志输出，减少日志污染
            return false;
        }

        // 检查目标是否有效（如果需要目标）
        if (card.target == AbstractCard.CardTarget.ENEMY) {
            // 需要敌人目标，检查玩家是否有效
            if (AbstractDungeon.player == null || AbstractDungeon.player.isDead || AbstractDungeon.player.isEscaping) {
                Hpr.info("怪物 " + monster.name + " 无法打出卡牌 " + card.name + "，目标无效");
                return false;
            }
        }

        // 移除详细日志输出，减少日志污染
        // 可以添加更多条件检查，比如状态要求等
        return true;
    }

    /**
     * 获取卡牌优先级（数值越高优先级越高）
     */
    private int getCardPriority(AbstractCard card) {
        // 检查是否是怪物卡牌并有自定义优先级
        if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
            EveryMonsterPlayCard.cards.monster.AbstractMonsterCard monsterCard =
                (EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card;

            // 获取当前手牌作为参考
            java.util.ArrayList<AbstractCard> currentHand = new java.util.ArrayList<>(monsterDrawPile.group);
            return monsterCard.getPriority(currentHand);
        }

        // 默认优先级基于卡牌类型
        switch (card.type) {
            case ATTACK:
                return 50;
            case SKILL:
                return 30;
            case POWER:
                return 70; // Power类优先级最高
            case CURSE:
            case STATUS:
                return 10;
            default:
                return 20;
        }
    }

    /**
     * 获取最左边的卡牌（即将打出的第一张牌）
     */
    private AbstractCard getLeftmostCard() {
        if (monsterDrawPile == null || monsterDrawPile.isEmpty()) {
            return null;
        }

        // 获取最左边的卡牌（抽牌堆底部的牌，索引0）
        AbstractCard leftmostCard = monsterDrawPile.group.get(0);
        if (leftmostCard != null) {
            Hpr.info("获取最左边卡牌: " + leftmostCard.name + " for monster: " + monster.name);
        }
        return leftmostCard;
    }

    /**
     * 从手牌移除指定的卡牌
     */
    private void removeCardFromHand(AbstractCard cardToRemove) {
        if (monsterHand != null && cardToRemove != null) {
            monsterHand.removeCard(cardToRemove);
            // 将用过的卡牌加入弃牌堆
            monsterDiscardPile.addToBottom(cardToRemove.makeStatEquivalentCopy());
            Hpr.info("从手牌移除卡牌: " + cardToRemove.name + " for monster: " + monster.name);
        }
    }

    /**
     * 创建卡牌出牌动画（使用游戏原生动画系统，自动分散位置）
     */
    public void createCardPlayAnimation(AbstractCard card) {
        if (card == null || monster == null) {
            return;
        }

        try {
            // 创建卡牌副本，避免修改原始卡牌导致显示问题
            AbstractCard cardCopy = card.makeStatEquivalentCopy();
            cardCopy.unfadeOut();

            // 使用无指定位置的构造函数，让游戏自动处理位置分散
            // ShowCardBrieflyEffect会自动检测现有效果数量并分散位置
            ShowCardBrieflyEffect effect = new ShowCardBrieflyEffect(cardCopy);

            // 添加到全局动画效果列表
            AbstractDungeon.effectList.add(effect);

            Hpr.info("为怪物 " + monster.name + " 创建自动分散卡牌动画（使用副本）: " + card.name);

        } catch (Exception e) {
            Hpr.info("创建卡牌动画时出错: " + e.getMessage());
        }
    }

    /**
     * 发送卡牌出牌事件
     */
    private void sendCardPlayEvent(AbstractCard card) {
        if (eventBus != null && card != null) {
            // 生成卡牌ID（使用哈希码）
            int cardId = card.cardID.hashCode();
            CardPlayEvent event = new CardPlayEvent(monster.hashCode(), cardId, playerId, card.name);
            eventBus.sendEvent(event);
            Hpr.info("发送卡牌出牌事件: " + event.toString());
        }
    }

    /**
     * 从抽牌堆抽牌到手牌
     */
    private void drawCardsToHand(int count) {
        if (monsterDrawPile == null || monsterDrawPile.isEmpty()) {
            // 如果抽牌堆为空，尝试从弃牌堆洗牌
            if (monsterDiscardPile != null && !monsterDiscardPile.isEmpty()) {
                shuffleDiscardToDraw();
            } else {
                Hpr.info("怪物 " + monster.name + " 牌库已空，无法抽牌");
                return;
            }
        }

        int actualDrawCount = Math.min(count, monsterDrawPile.size());
        for (int i = 0; i < actualDrawCount; i++) {
            if (monsterDrawPile.isEmpty()) {
                break;
            }
            AbstractCard card = monsterDrawPile.getTopCard();
            // 重要：设置卡牌的拥有怪物，确保applyPowers正确工作
            if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card).setOwningMonster(monster);
            }
            monsterHand.addToTop(card);
            Hpr.info("怪物 " + monster.name + " 抽取手牌: " + card.name + " (ID: " + card.cardID + ")");
        }

        Hpr.info("怪物 " + monster.name + " 抽取了 " + actualDrawCount + " 张牌到手牌，当前手牌数量: " + monsterHand.size());
        
        // 调试：打印手牌中的所有卡牌
        Hpr.info("怪物 " + monster.name + " 当前手牌：");
        for (int i = 0; i < monsterHand.group.size(); i++) {
            AbstractCard card = monsterHand.group.get(i);
            Hpr.info("  [" + i + "] " + card.name + " (ID: " + card.cardID + ")");
        }
    }

    /**
     * 刷新显示的卡牌列表（显示手牌）
     */
    private void refreshDisplayedCards() {
        displayedCards.clear();
        if (monsterHand == null || monsterHand.isEmpty()) {
            Hpr.info("No cards in hand for monster: " + monster.name);
            // 同步空列表到CardRecorder
            syncCardsToRecorder();
            return;
        }

        // 显示所有手牌
        // 从手牌中获取所有卡牌进行显示
        for (int i = 0; i < monsterHand.size(); i++) {
            AbstractCard card = monsterHand.group.get(i);
            if (card != null) {
                AbstractCard cardCopy = card.makeStatEquivalentCopy();
                // 重要：设置卡牌的拥有怪物，确保applyPowers正确工作
                if (cardCopy instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                    ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) cardCopy).setOwningMonster(monster);
                }
                displayedCards.add(cardCopy);
          }
        }

        Hpr.info("Total cards displayed for " + monster.name + ": " + displayedCards.size());
        
        // 调试：显示当前显示的卡牌详细信息
        Hpr.info("怪物 " + monster.name + " 当前显示的卡牌：");
        for (int i = 0; i < displayedCards.size(); i++) {
            AbstractCard card = displayedCards.get(i);
            Hpr.info("  [" + i + "] " + card.name + " (ID: " + card.cardID + ")");
        }

        // 同步卡牌数据到CardRecorder以供UI使用
        syncCardsToRecorder();

        // 发送手牌更新事件
        sendHandUpdateEvent();

        // 标记已经设置初始显示
        initialDisplaySetup = true;
    }

    /**
     * 同步卡牌数据到CardRecorder以供UI组件使用
     */
    private void syncCardsToRecorder() {
        if (cardRecorder != null) {
            // 清空现有数据
            cardRecorder.cardList.clear();
            cardRecorder.drawingCards.clear();

            // 将当前显示的卡牌同步到CardRecorder
            cardRecorder.cardList.addAll(displayedCards);

            // 设置更新标记，通知UI需要重新定位
            cardRecorder.justUpdateFlag = true;

            Hpr.info("Synced " + displayedCards.size() + " cards to CardRecorder for monster: " + monster.name);
        }
    }

    /**
     * 发送手牌更新事件
     */
    private void sendHandUpdateEvent() {
        if (eventBus != null) {
            ArrayList<Integer> cardIds = new ArrayList<>();
            for (AbstractCard card : displayedCards) {
                cardIds.add(card.cardID.hashCode());
            }
            HandUpdateEvent event = new HandUpdateEvent(monster.hashCode(), cardIds, playerId);
            eventBus.sendEvent(event);
      }
    }

    /**
     * 执行怪物的卡牌效果（使用游戏原生Action系统）
     */
    private void executeMonsterCard(AbstractCard card) {
        if (card == null || monster == null) {
            return;
        }

        try {
            // 安全检查
            if (AbstractDungeon.getCurrRoom() == null ||
                AbstractDungeon.getCurrRoom().monsters == null ||
                AbstractDungeon.getCurrRoom().monsters.monsters.isEmpty()) {
                Hpr.info("怪物 " + monster.name + " 当前房间没有有效目标，跳过卡牌执行");
                return;
            }

            AbstractPlayer targetPlayer = AbstractDungeon.player;
            if (targetPlayer == null) {
                Hpr.info("怪物 " + monster.name + " 无法找到有效目标，跳过卡牌执行");
                return;
            }

            // 使用Action系统执行卡牌，避免直接调用导致的动画冲突
            executeMonsterCardAction action = new executeMonsterCardAction(card, targetPlayer, monster);
            AbstractDungeon.actionManager.addToBottom(action);

            Hpr.info("怪物 " + monster.name + " 通过Action系统执行卡牌: " + card.name);

        } catch (Exception e) {
            Hpr.info("怪物 " + monster.name + " 执行卡牌时出错: " + e.getMessage());
        }
    }

    /**
     * 从弃牌堆重新洗牌到抽牌堆
     */
    private void shuffleDiscardToDraw() {
        if (monsterDiscardPile == null || monsterDiscardPile.isEmpty()) {
            return;
        }

        int discardCount = monsterDiscardPile.size();

        monsterDrawPile.clear();
        ArrayList<AbstractCard> tempList = new ArrayList<>();
        tempList.addAll(monsterDiscardPile.group);
        Collections.shuffle(tempList, AbstractDungeon.cardRandomRng.random);
        monsterDrawPile.group = tempList;

        monsterDiscardPile.clear();

        Hpr.info("怪物 " + monster.name + " 从弃牌堆重新洗牌，将 " + discardCount + " 张牌重新加入抽牌堆");
    }

    /**
     * 根据当前是第几个牌来计算偏移量（从PVP系统移植）
     */
    private int getXOffsetById(int idCard) {
        // 计算向左最多能放置的id
        int maxSet = (int)(monster.drawX / (AbstractCard.IMG_WIDTH * 0.4f)) - 1;
        if (maxSet > idCard)
            return -idCard;
        return -maxSet;
    }

    /**
     * 更新卡牌透明度（从PVP系统移植）
     */
    private void updateCardTransparency(AbstractCard card) {
        if (card == null) {
            return;
        }

        try {
            // 使用反射调用updateTransparency方法
            java.lang.reflect.Method transparencyMethod = AbstractCard.class.getDeclaredMethod("updateTransparency");
            transparencyMethod.setAccessible(true);
            transparencyMethod.invoke(card);
        } catch (Exception e) {
            Hpr.info("更新卡牌透明度时出错: " + e.getMessage());
        }
    }

    /**
     * 手动检测卡牌是否被hover
     */
    private boolean isCardHovered(AbstractCard card, float cardX, float cardY) {
        if (card == null) {
            return false;
        }

        float mouseX = InputHelper.mX;
        float mouseY = InputHelper.mY;

        float cardWidth = card.hb.width;
        float cardHeight = card.hb.height;
        float cardLeft = cardX - cardWidth / 2.0f;
        float cardRight = cardX + cardWidth / 2.0f;
        float cardTop = cardY + cardHeight / 2.0f;
        float cardBottom = cardY - cardHeight / 2.0f;

        return mouseX >= cardLeft && mouseX <= cardRight &&
               mouseY >= cardBottom && mouseY <= cardTop;
    }

    /**
     * 渲染头顶卡牌（显示抽牌堆）- 简化版，避免重复渲染
     */
    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch sb) {/*
        if (displayedCards != null && !displayedCards.isEmpty()) {
            // 只更新位置和hover状态，避免复杂的重复计算
            updateCardPositions();

            // 渲染所有卡牌
            for (AbstractCard card : displayedCards) {
                if (card != null) {
                    // 确保卡牌不会消失
                    card.fadingOut = false;
                  card.render(sb);
                }
            }
        } else {
            Hpr.info("MonsterCardPlayer.render() called but no cards to render for monster: " + monster.name);
        }*/
    }

    /**
     * 更新卡牌位置（简化版，只做必要的位置跟随）
     */
    private void updateCardPositions() {
        if (displayedCards == null || displayedCards.isEmpty() || monster == null) {
            return;
        }

        // 基本参数
        float xCenter = monster.drawX;
        float yCenter = monster.drawY + monster.hb_h + CARD_DISPLAY_HEIGHT;
        final float SHOW_SCALE = 0.4f;
        int xOffset = getXOffsetById(displayedCards.size() - 1);

        // 更新每张卡牌的位置和hover状态
        for (int i = 0; i < displayedCards.size(); i++) {
            AbstractCard card = displayedCards.get(i);
            if (card != null) {
                // 计算目标位置
                float cardX = xCenter + xOffset * AbstractCard.IMG_WIDTH * SHOW_SCALE;
                float cardY = yCenter;

                // 平滑移动到目标位置
                card.current_x = cardX;
                card.current_y = cardY;
                card.target_x = cardX;
                card.target_y = cardY;

                // 设置基础缩放
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;

                // 检测hover状态
                boolean isHovered = isCardHovered(card, cardX, cardY);

                if (isHovered) {
                    // hover效果：放大，不透明
                    card.drawScale = 0.6f;
                    card.transparency = 1.0f;
                    card.targetTransparency = 1.0f;
                } else {
                    // 基于能量系统设置透明度
                    boolean canPlay = canPlayCard(card, currentEnergy);
                    if (canPlay) {
                        // 可以出的牌：半透明但不影响悬停效果
                        card.transparency = 0.7f;
                        card.targetTransparency = 0.7f;
                    } else {
                        // 不可出的牌：很暗的半透明
                        card.transparency = 0.2f;
                        card.targetTransparency = 0.2f;
                    }
                }

                // 更新透明度
                updateCardTransparency(card);

                xOffset++;
            }
        }
    }

    /**
     * 检查是否启用
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 获取抽牌堆大小
     */
    public int getDrawPileSize() {
        return monsterDrawPile != null ? monsterDrawPile.size() : 0;
    }

    /**
     * 获取弃牌堆大小
     */
    public int getDiscardPileSize() {
        return monsterDiscardPile != null ? monsterDiscardPile.size() : 0;
    }

    /**
     * 重置系统（用于重置战斗状态）
     */
    public void reset() {
        disable();
        if (monsterDrawPile != null) {
            monsterDrawPile.clear();
        }
        if (monsterDiscardPile != null) {
            monsterDiscardPile.clear();
        }
        if (monsterHand != null) {
            monsterHand.clear();
        }

        Hpr.info("怪物 " + monster.name + " CardPlayer系统已重置");
    }

    /**
     * 创建默认的怪物卡牌
     */
    private List<AbstractCard> createDefaultMonsterCards() {
        List<AbstractCard> defaultCards = new ArrayList<>();

        // 默认的怪物攻击卡牌
        defaultCards.add(new MonsterAttackCard(
            "MonsterAttack_1",
            "怪物打击",
            "card/attack/default_attack",
            1,
            6,
            AbstractCard.CardTarget.ENEMY,
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        ));

        // 默认的怪物防御卡牌
        defaultCards.add(new MonsterSkillCard(
            "MonsterDefend_1",
            "怪物防御",
            "card/skill/default_defend",
            1,
            5,
            AbstractCard.CardTarget.SELF
        ));

        // 默认的怪物能力卡牌
        defaultCards.add(new MonsterPowerCard(
            "MonsterPower_1",
            "怪物力量",
            "card/power/default_power",
            1,
            MonsterPowerCard.PowerType.STRENGTH,
            1,
            AbstractCard.CardTarget.SELF
        ));

        return defaultCards;
    }

    /**
     * 手动添加卡牌到抽牌堆（用于测试）
     */
    public void addCardToDrawPile(AbstractCard card) {
        if (card != null && monsterDrawPile != null) {
            AbstractCard cardCopy = card.makeStatEquivalentCopy();
            cardCopy.color = AbstractCard.CardColor.COLORLESS;
            cardCopy.freeToPlayOnce = true;
            cardCopy.cost = 0;
            cardCopy.costForTurn = 0;
            monsterDrawPile.addToBottom(cardCopy);

            Hpr.info("为怪物 " + monster.name + " 添加了卡牌到抽牌堆: " + cardCopy.name);
        }
    }

    /**
     * UI渲染方法 (从PVP系统移植)
     */
    public void renderUI(SpriteBatch sb) {
        if (battleCardPanel != null && enabled) {
           battleCardPanel.render(sb);
        } else {
            Hpr.info("MonsterCardPlayer.renderUI() called but battleCardPanel is null or not enabled for monster: " + monster.name);
        }
    }

    /**
     * UI更新方法 (从PVP系统移植)
     */
    public void updateUI() {
        if (battleCardPanel != null && enabled) {

            // 更新能量球位置跟随怪物移动
            if (battleCardPanel.energyPanel != null && monster != null) {
                battleCardPanel.energyPanel.updatePosition(monster.drawX, monster.drawY);
            }
            // 更新卡牌透明度（基于能量系统）
            battleCardPanel.updateCardTransparency();
            battleCardPanel.update();
        }
    }

    /**
     * 设置能量（发送能量更新事件）
     */
    public void setEnergy(int newEnergy) {
        int oldEnergy = this.currentEnergy;
        this.previousEnergy = oldEnergy;
        this.currentEnergy = Math.max(0, Math.min(newEnergy, maxEnergy)); // 能量在0到上限之间

        // 发送能量更新事件
        sendEnergyUpdateEvent(oldEnergy, this.currentEnergy);

        // 更新UI面板
        if (battleCardPanel != null) {
            battleCardPanel.setEnergy(this.currentEnergy);
        }

        Hpr.info("怪物 " + monster.name + " 能量变化: " + oldEnergy + " -> " + this.currentEnergy);
    }

    /**
     * 回合开始时自动补充能量到上限
     */
    public void refillEnergyToMax() {
        int oldEnergy = this.currentEnergy;
        this.currentEnergy = this.maxEnergy;
        
        // 发送能量更新事件
        sendEnergyUpdateEvent(oldEnergy, this.currentEnergy);
        
        // 更新UI面板
        if (battleCardPanel != null) {
            battleCardPanel.setEnergy(this.currentEnergy);
        }
        
        Hpr.info("怪物 " + monster.name + " 回合开始，能量补充到上限: " + this.currentEnergy + "/" + this.maxEnergy);
    }

    /**
     * 设置能量上限
     */
    public void setMaxEnergy(int newMaxEnergy) {
        int oldMaxEnergy = this.maxEnergy;
        this.maxEnergy = Math.max(1, newMaxEnergy); // 能量上限至少为1
        
        // 如果当前能量超过新的上限，则调整当前能量
        if (this.currentEnergy > this.maxEnergy) {
            setEnergy(this.maxEnergy);
        }
        
        // 更新UI面板的能量上限显示
        if (battleCardPanel != null && battleCardPanel.energyPanel != null) {
            battleCardPanel.energyPanel.masterEnergy = this.maxEnergy;
        }
        
        Hpr.info("怪物 " + monster.name + " 能量上限变化: " + oldMaxEnergy + " -> " + this.maxEnergy);
    }

    /**
     * 获取能量上限
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * 发送能量更新事件
     */
    private void sendEnergyUpdateEvent(int oldEnergy, int newEnergy) {
        if (eventBus != null && oldEnergy != newEnergy) {
            EnergyUpdateEvent event = new EnergyUpdateEvent(monster.hashCode(), playerId, oldEnergy, newEnergy);
            eventBus.sendEvent(event);
            Hpr.info("发送能量更新事件: " + event.toString());
        }
    }

    /**
     * 获取当前能量
     */
    public int getCurrentEnergy() {
        return currentEnergy;
    }
}