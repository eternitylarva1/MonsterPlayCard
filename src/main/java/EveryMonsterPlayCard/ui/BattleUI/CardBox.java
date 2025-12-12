package EveryMonsterPlayCard.ui.BattleUI;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import EveryMonsterPlayCard.monstercards.CardShowChange;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//这是一个用来显示牌的界面，会显示在敌人的头上。
public class CardBox {

    public float xCenter;
    public float yCenter;
    public CardRecorder shownCards;

    //显示牌时的缩放倍率
    public static float SHOW_SCALE = 0.4F;

    //上一次迭代的卡牌数量
    int lastCardNum = 0;

    //最多同时显示的牌数
    public static final int MAX_SHOW_NUM = 10;

    //目前的攻击意图的值
    public int damageAmount = 0;

    //当前的card box所属的monster
    AbstractMonster belongMonster;
    
    //关联的MonsterCardPlayer，用于获取能量信息
    MonsterCardPlayer cardPlayer;

    public CardBox(float xCenter, float yCenter,
       CardRecorder shownCards,AbstractMonster monster)
    {
        //记录传入的属性
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.shownCards = shownCards;
        //记录当前的box所属的monster
        this.belongMonster = monster;
        this.cardPlayer = null;
    }
    
    public CardBox(float xCenter, float yCenter,
       CardRecorder shownCards,AbstractMonster monster, MonsterCardPlayer cardPlayer)
    {
        //记录传入的属性
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.shownCards = shownCards;
        //记录当前的box所属的monster
        this.belongMonster = monster;
        this.cardPlayer = cardPlayer;
    }

    //默认的无monster的构造函数
    public CardBox(float xCenter, float yCenter,
                   CardRecorder shownCards)
    {
        this(xCenter,yCenter,shownCards,null);
    }
    
    /**
     * 获取关联的MonsterCardPlayer
     */
    private MonsterCardPlayer getMonsterCardPlayer() {
        return this.cardPlayer;
    }
    
    /**
     * 设置关联的MonsterCardPlayer
     */
    public void setMonsterCardPlayer(MonsterCardPlayer cardPlayer) {
        this.cardPlayer = cardPlayer;
    }

    /**
     * 更新卡牌位置以跟随怪物移动（修复：添加持续跟随功能）
     */
    public void updateCardPositions() {
        if (belongMonster != null) {
            // 更新位置以跟随怪物
            this.xCenter = belongMonster.drawX;
            this.yCenter = belongMonster.drawY + belongMonster.hb_h * 1.5f;
        }
    }

    /**
     * 更新卡牌透明度（基于能量系统）
     */
    public void updateCardTransparency(int availableEnergy) {
        // 更新要抽的牌的透明度
        for (AbstractCard card : shownCards.drawingCards) {
            if (card != null) {
                CardShowChange.setCardAlphaByEnergy(card, availableEnergy, belongMonster);
            }
        }

        // 更新手牌的透明度
        for (AbstractCard card : shownCards.cardList) {
            if (card != null) {
                CardShowChange.setCardAlphaByEnergy(card, availableEnergy, belongMonster);
            }
        }
    }

    /**
     * 更新卡牌预估透明度（基于费用预估系统）
     * @param availableEnergy 可用能量
     */
    public void updateCardEstimateTransparency(int availableEnergy) {
        if (shownCards == null) {
            return;
        }

        // 创建预估卡牌列表（从左到右顺序）
        java.util.List<AbstractCard> cardsToEstimate = new java.util.ArrayList<>();
        
        // 卡牌列表（从左到右）
        for (int i = 0; i < shownCards.cardList.size(); i++) {
            AbstractCard card = shownCards.cardList.get(i);
            if (card != null) {
                cardsToEstimate.add(card);
            }
        }

        // 使用预估算法计算并设置透明度
        CardShowChange.estimatePlayableCards(cardsToEstimate, availableEnergy);
    }

    /**
     * 检查卡牌是否被鼠标悬停
     */
    private boolean isCardHovered(AbstractCard card) {
        float mouseX = InputHelper.mX;
        float mouseY = InputHelper.mY;

        float cardWidth = card.hb.width * card.drawScale;
        float cardHeight = card.hb.height * card.drawScale;
        float cardLeft = card.current_x - cardWidth / 2.0f;
        float cardRight = card.current_x + cardWidth / 2.0f;
        float cardTop = card.current_y + cardHeight / 2.0f;
        float cardBottom = card.current_y - cardHeight / 2.0f;

        return mouseX >= cardLeft && mouseX <= cardRight &&
               mouseY >= cardBottom && mouseY <= cardTop;
    }

    /**
     * 持续更新卡牌位置以跟随怪物移动
     */
    public void update() {
        // 频繁更新位置以跟随怪物移动
        updateCardPositions();

        // 更新所有显示卡牌的位置（修复：使用hitbox宽度作为间距）
        if (shownCards != null) {
            int showDrawNum = Math.min(MAX_SHOW_NUM - shownCards.cardList.size(), shownCards.drawingCards.size());

            int xOffset = getXOffsetById(shownCards.cardList.size() + showDrawNum - 1);

            // 更新即将抽到的卡牌位置（从右向左）


            // 更新已出卡牌的位置
            for (AbstractCard card : shownCards.cardList) {
                if (card != null) {
                    card.target_y = yCenter;
                    // 修复：使用hitbox宽度作为间距而非IMG_WIDTH
                    card.target_x = xCenter + xOffset * card.hb.width * SHOW_SCALE;
                    // 修复：对于所有卡牌都更新，包括fadingOut的卡牌，以确保持续跟随
                    xOffset++;
                }
            }
        }
    }

    //根据当前是第几个牌来计算当前的偏移量
    int getXOffsetById(int idCard)
    {
        //修复：使用hitbox宽度作为间距计算基准
        //获取一个参考卡牌来计算间距（假设所有卡牌规格相同）
        AbstractCard referenceCard = null;
        if (shownCards != null && !shownCards.drawingCards.isEmpty()) {
            referenceCard = shownCards.drawingCards.get(0);
        } else if (shownCards != null && !shownCards.cardList.isEmpty()) {
            referenceCard = shownCards.cardList.get(0);
        }

        float cardWidth = referenceCard != null ? referenceCard.hb.width * SHOW_SCALE : 100.0f;

        int maxSet = (int)(xCenter / cardWidth) - 1;
        if(maxSet > idCard)
            return -idCard;
        return -maxSet;
    }

    //获得接下来5张牌的攻击总数

    //根据即将抽到的第一张牌更新意图

    //对牌内容的渲染
    public void render(SpriteBatch sb)
    {
        //判断是否需要更新显示位置
        boolean updateLocation=true;

        //遍历所有需要显示的牌
        //下回合抽牌显示的数量
        int showDrawNum = Math.min(MAX_SHOW_NUM-shownCards.cardList.size(),shownCards.drawingCards.size());
        int xOffset = getXOffsetById(shownCards.cardList.size() + showDrawNum -1);
        // 应用预估透明度系统
        MonsterCardPlayer cardPlayer = getMonsterCardPlayer();
        if (cardPlayer != null) {
            updateCardEstimateTransparency(cardPlayer.getCurrentEnergy());
        }
        for (AbstractCard card : shownCards.cardList) {
            //获取当前位置的牌
            //判断是否需要更新位置
            //更新卡牌的位置
            card.current_y = yCenter;
            card.target_y = yCenter;
            final float calculateCardWidth=card.transparency>0.1f?card.hb.width:card.hb.width/2;
            card.target_x = xCenter + xOffset * calculateCardWidth * SHOW_SCALE;
            card.current_x = card.target_x;
            //更新卡牌的缩放大小
            card.targetDrawScale = SHOW_SCALE;
            card.drawScale = SHOW_SCALE;
            ++xOffset;
           // card.unfadeOut();

            //修复：添加悬停效果检测（已出卡牌）
            boolean isHovered = isCardHovered(card);
            if (isHovered) {
                // 悬停时设为完全不透明并轻微放大
                CardShowChange.setCardFullyVisible(card);
                card.targetDrawScale = SHOW_SCALE * 1.1f;
                card.drawScale = card.targetDrawScale;
            } else if (updateLocation) {
                // 已出卡牌保持半透明
                //CardShowChange.setCardSemiTransparent(card);
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;
            }
           card.render(sb);
        }
    }

}
