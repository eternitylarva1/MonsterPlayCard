package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.monstercards.cards.MonsterAttackCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterSkillCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterPowerCard;
import EveryMonsterPlayCard.utils.Hpr;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    // 当前显示的卡牌（用于显示抽牌堆）
    private List<AbstractCard> displayedCards = new ArrayList<>();        // 当前显示在头顶的卡牌列表
    private float cardDisplayTimer = 0.0f;                // 卡牌显示计时器

    // 卡牌显示控制
    private boolean initialDisplaySetup = false;         // 是否已经设置初始显示

    // 出牌控制
    private boolean enabled = false;                       // 是否启用出牌系统
    private boolean hasPlayedCardThisTurn = false;         // 当前回合是否已出牌

    // 卡牌显示位置
    private static final float CARD_DISPLAY_HEIGHT = 80.0f * Settings.scale;
    private static final float CARD_HOVER_SCALE = 0.6f;
    private static final float CARD_NORMAL_SCALE = 0.4f;
    private static final float CARD_OFFSET_X = 15.0f * Settings.scale;  // 卡牌之间的水平偏移
    private static final float CARD_OFFSET_Y = 5.0f * Settings.scale;   // 卡牌之间的垂直偏移

    public MonsterCardPlayer(AbstractMonster monster) {
        this.monster = monster;
        this.cardConfig = MonsterCardConfig.getInstance();
        initializeCardSystem();
    }

    /**
     * 初始化怪物的牌库系统（基于统一卡牌池）
     */
    private void initializeCardSystem() {
        // 初始化牌库
        monsterDrawPile = new CardGroup(CardGroup.CardGroupType.DRAW_PILE);
        monsterDiscardPile = new CardGroup(CardGroup.CardGroupType.DISCARD_PILE);

        // 获取统一卡牌池
        ArrayList<AbstractCard> monsterCards = getUniversalCards();
        if (monsterCards.isEmpty()) {
            Hpr.info("警告：怪物 " + monster.name + " 没有可用的卡牌");
            return;
        }

        // 洗牌并初始化抽牌堆
        shuffleIntoDrawPile(monsterCards);

        Hpr.info("怪物CardPlayer初始化完成: " + monster.name + " ，牌库包含 " + monsterDrawPile.size() + " 张卡牌");

        // 初始化显示 - 战斗开始时就显示抽牌堆
        refreshDisplayedCards();
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
                monsterDrawPile.addToBottom(card);
            }
        }

        // 洗牌
        ArrayList<AbstractCard> tempList = new ArrayList<>();
        tempList.addAll(monsterDrawPile.group);
        Collections.shuffle(tempList, AbstractDungeon.cardRandomRng.random);
        monsterDrawPile.group = tempList;

        Hpr.info("怪物 " + monster.name + " 洗牌完成，抽牌堆包含 " + monsterDrawPile.size() + " 张卡牌");
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

        // 出牌（每个怪物回合出1张牌）
        playCardForTurn();

        Hpr.info("怪物 " + monster.name + " 回合开始，已出牌: " + hasPlayedCardThisTurn);
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

        // 如果还没有设置初始显示，则进行初始化显示
        if (!initialDisplaySetup) {
            refreshDisplayedCards();
        }

        // 移除时间限制 - 卡牌持续显示
        // 卡牌现在会一直显示在怪物头顶，不会自动消失

        // 更新显示卡牌的位置
        updateCardDisplay();
    }

    /**
     * 为当前回合出牌
     */
    private void playCardForTurn() {
        if (hasPlayedCardThisTurn) {
            return; // 已经出过牌了
        }

        if (monsterDrawPile == null || monsterDrawPile.isEmpty()) {
            // 尝试从弃牌堆重新洗牌
            if (monsterDiscardPile != null && !monsterDiscardPile.isEmpty()) {
                shuffleDiscardToDraw();
            } else {
                Hpr.info("怪物 " + monster.name + " 牌库已空，无法出牌");
                return;
            }
        }

        // 从抽牌堆抽一张牌
        AbstractCard drawnCard = monsterDrawPile.getTopCard();
        if (drawnCard != null) {
            // 移除顶牌
            monsterDrawPile.removeTopCard();

            // 刷新显示的牌列表（显示抽牌堆）
            refreshDisplayedCards();

            // 执行卡牌效果（使用游戏原生Action系统）
            executeMonsterCard(drawnCard);

            // 标记本回合已出牌
            hasPlayedCardThisTurn = true;

            Hpr.info("怪物 " + monster.name + " 回合开始打出了: " + drawnCard.name);
        }
    }

    /**
     * 刷新显示的卡牌列表（显示抽牌堆的前几张卡牌）
     */
    private void refreshDisplayedCards() {
        displayedCards.clear();

        if (monsterDrawPile == null || monsterDrawPile.isEmpty()) {
            return;
        }

        // 显示抽牌堆顶部的最多5张牌
        int maxDisplay = Math.min(5, monsterDrawPile.size());
        for (int i = monsterDrawPile.size() - maxDisplay; i < monsterDrawPile.size(); i++) {
            AbstractCard card = monsterDrawPile.group.get(i);
            if (card != null) {
                AbstractCard cardCopy = card.makeStatEquivalentCopy();
                displayedCards.add(cardCopy);
            }
        }

        // 标记已经设置初始显示
        initialDisplaySetup = true;
    }

    /**
     * 执行怪物的卡牌效果（使用游戏原生Action系统，模仿原版怪物）
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

            // 在调用卡牌之前，设置卡牌的拥有者怪物
            if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card).setOwningMonster(monster);
            }

            // 直接调用卡牌的use方法
            card.use(targetPlayer, monster);

            Hpr.info("怪物 " + monster.name + " 执行了卡牌: " + card.name);

            // 将用过的卡牌加入弃牌堆
            monsterDiscardPile.addToBottom(card.makeStatEquivalentCopy());

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
     * 更新卡牌显示（按照DeadPlayer的样式）
     */
    private void updateCardDisplay() {
        if (displayedCards != null && !displayedCards.isEmpty() && monster != null) {
            // 计算抽牌堆位置的基准点（怪物头顶）
            float baseX = monster.drawX;
            float baseY = monster.drawY + monster.hb_h + CARD_DISPLAY_HEIGHT;

            // 最多显示5张牌（顶部5张）
            int maxDisplay = Math.min(5, displayedCards.size());

            // 卡牌错开的间距
            float cardOffset = 15.0f * Settings.scale;
            float scaleOffset = 0.05f; // 每张牌的缩放递减

            // 从底牌开始渲染，这样顶牌会最后渲染在最前面
            for (int i = displayedCards.size() - maxDisplay, displayCount = maxDisplay - 1; i < displayedCards.size(); i++, displayCount--) {
                AbstractCard card = displayedCards.get(i);

                if (card != null) {
                    // 计算每张牌的位置（水平排列）
                    float cardX = baseX + displayCount * cardOffset;
                    float cardY = baseY;

                    // 更新卡牌位置
                    card.current_x = cardX;
                    card.current_y = cardY;
                    card.target_x = cardX;
                    card.target_y = cardY;

                    // 手动检测当前卡牌是否被hover
                    boolean isHovered = isCardHovered(card, cardX, cardY);

                    // 为非hover状态的卡牌设置默认缩放
                    if (!isHovered) {
                        float cardScale = 0.4f - displayCount * scaleOffset; // 从0.4缩小到0.2
                        card.drawScale = cardScale;
                        card.targetDrawScale = cardScale;
                    } else {
                        // hover状态的卡牌设置更大的缩放
                        float hoverScale = 0.6f - displayCount * scaleOffset * 0.5f; // hover时更大一些
                        card.drawScale = hoverScale;
                        card.targetDrawScale = hoverScale;
                    }

                    // 更新卡牌状态
                    card.update();
                    card.applyPowers();
                }
            }
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
     * 渲染头顶卡牌（显示抽牌堆）
     */
    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch sb) {
        if (displayedCards != null && !displayedCards.isEmpty()) {
            for (AbstractCard card : displayedCards) {
                if (card != null) {
                    card.render(sb);
                }
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
}