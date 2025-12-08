package EveryMonsterPlayCard.ui.BattleUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.core.Settings;
import EveryMonsterPlayCard.monstercards.CardShowChange;

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

    public CardBox(float xCenter, float yCenter,
       CardRecorder shownCards,AbstractMonster monster)
    {
        //记录传入的属性
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.shownCards = shownCards;
        //记录当前的box所属的monster
        this.belongMonster = monster;
    }

    //默认的无monster的构造函数
    public CardBox(float xCenter, float yCenter,
                   CardRecorder shownCards)
    {
        this(xCenter,yCenter,shownCards,null);
    }

    /**
     * 更新卡牌位置以跟随怪物移动
     */
    public void updateCardPositions() {
        if (belongMonster != null) {
            // 更新位置以跟随怪物
            this.xCenter = belongMonster.drawX;
            this.yCenter = belongMonster.drawY + belongMonster.hb_h * 1.5f;
        }
    }

    /**
     * 检查卡牌是否被鼠标悬停
     */
    private boolean isCardHovered(AbstractCard card, float cardX, float cardY) {
        float mouseX = InputHelper.mX;
        float mouseY = InputHelper.mY;

        float cardWidth = card.hb.width * card.drawScale;
        float cardHeight = card.hb.height * card.drawScale;
        float cardLeft = cardX - cardWidth / 2.0f;
        float cardRight = cardX + cardWidth / 2.0f;
        float cardTop = cardY + cardHeight / 2.0f;
        float cardBottom = cardY - cardHeight / 2.0f;

        return mouseX >= cardLeft && mouseX <= cardRight &&
               mouseY >= cardBottom && mouseY <= cardTop;
    }

    /**
     * 应用悬停效果
     */
    private void applyHoverEffect(AbstractCard card, boolean isHovered) {
        if (isHovered) {
            // 悬停时放大并设置为完全不透明
            card.targetDrawScale = SHOW_SCALE * 1.3f;  // 增强放大效果到130%
            CardShowChange.setCardFullyVisible(card);
            card.drawScale = card.targetDrawScale;     // 强制立即更新缩放
        } else {
            // 非悬停时恢复正常大小和半透明
            card.targetDrawScale = SHOW_SCALE;
            CardShowChange.setCardSemiTransparent(card);
        }
            card.drawScale = card.targetDrawScale;     // 强制立即更新缩放
    }

    /**
     * 在每次渲染前更新所有卡牌的位置
     */
    public void update() {
        // 频繁更新位置以跟随怪物移动
        updateCardPositions();

        // 更新所有显示卡牌的位置
        if (shownCards != null) {
            // 修复：重新设计卡牌定位逻辑，第一张牌在怪物左侧
            int totalCards = shownCards.cardList.size() + shownCards.drawingCards.size();
            if (totalCards > 0) {
                // 计算第一张牌的起始位置（怪物左侧）
                int startOffset = -(totalCards - 1) / 2;

                // 更新即将抽到的卡牌位置
                for (int i = 0; i < shownCards.drawingCards.size(); i++) {
                    AbstractCard card = shownCards.drawingCards.get(i);
                    if (card != null) {
                        int currentOffset = startOffset + i;
                        card.target_x = xCenter + currentOffset * 15.0F * Settings.scale;
                        card.target_y = yCenter;
                    }
                }

                // 更新已出卡牌的位置
                for (int i = 0; i < shownCards.cardList.size(); i++) {
                    AbstractCard card = shownCards.cardList.get(i);
                    if (card != null) {
                        int currentOffset = startOffset + shownCards.drawingCards.size() + i;
                        card.target_x = xCenter + currentOffset * 15.0F * Settings.scale;
                        card.target_y = yCenter;
                    }
                }
            }
        }
    }

    //根据当前是第几个牌来计算当前的偏移量
    int getXOffsetById(int idCard)
    {
        //计算向左最多能放置的id
        int maxSet = 0;  // 修复：简化定位计算
        if(maxSet > idCard)
            return idCard;  // 修复：从负数改为正数，实现从左向右排列
        return maxSet;      // 修复：从负数改为正数
    }

    //获得接下来5张牌的攻击总数
    public int sumDamageAmount(int cardNum)
    {
        //判断查找的牌数是否超过
        if(cardNum > shownCards.drawingCards.size())
        {
            cardNum = shownCards.drawingCards.size();
        }
        //总的伤害数
        int sumDamage = 0;
        //遍历即将读取到的每个牌
        for(int idCard=0;idCard<cardNum;++idCard)
        {
            //获取对应的牌
            AbstractCard card = shownCards.drawingCards.get(idCard);
            //判断是不是攻击牌
            if(card.type == AbstractCard.CardType.ATTACK)
            {
                sumDamage += card.baseDamage;
            }
        }
        return  sumDamage;
    }

    //根据即将抽到的第一张牌更新意图
    public AbstractMonster.Intent getIntent()
    {
        //如果没有牌的话就是unknown
        if(shownCards.drawingCards.isEmpty())
            return AbstractMonster.Intent.UNKNOWN;
        //求和接下来5张牌里面的伤害总和
        this.damageAmount = sumDamageAmount(5);
        if(damageAmount > 0)
            return AbstractMonster.Intent.ATTACK;
        //获得第一张牌
        AbstractCard firstCard = shownCards.drawingCards.get(0);
        //判断是不是能力牌
        if(firstCard.type == AbstractCard.CardType.POWER)
            return AbstractMonster.Intent.BUFF;
        //技能牌，判断是不是给自己使用的
        if(firstCard.target == AbstractCard.CardTarget.SELF)
        {
            if(firstCard.baseBlock>0)
                return AbstractMonster.Intent.DEFEND;
            return AbstractMonster.Intent.MAGIC;
        }
        //判断是不是金卡或者大于1费
        if(firstCard.rarity == AbstractCard.CardRarity.RARE ||
            firstCard.cost>1 )
            return AbstractMonster.Intent.STRONG_DEBUFF;
        return AbstractMonster.Intent.DEBUFF;
    }

    //对牌内容的渲染
    public void render(SpriteBatch sb)
    {
        // 更新所有卡牌位置和悬停效果
        update();

        //判断是否需要更新显示位置
        boolean updateLocation=false;
        if(this.belongMonster != null && (shownCards.justUpdateFlag ||
            this.belongMonster.intent == AbstractMonster.Intent.DEBUG))
        {
            updateLocation = true;
            shownCards.justUpdateFlag = false;
            //更新意图
            AbstractMonster.Intent tempIntent = getIntent();
            //如果是攻击意图，需要设置对应的伤害值
            if(tempIntent== AbstractMonster.Intent.ATTACK)
            {
                this.belongMonster.setMove((byte)1,tempIntent,
                        this.damageAmount);
                if(this.belongMonster.intent == AbstractMonster.Intent.DEBUG)
                    this.belongMonster.createIntent();
            }
            else {
                this.belongMonster.setMove((byte)1,tempIntent,-1);
            }
        }
        //遍历所有需要显示的牌

        //修复：重新设计渲染定位逻辑，与update()方法保持一致
        int totalCards = shownCards.cardList.size() + shownCards.drawingCards.size();
        int showDrawNum = Math.min(MAX_SHOW_NUM - shownCards.cardList.size(), shownCards.drawingCards.size());

        if (totalCards > 0 && updateLocation) {
            // 计算第一张牌的起始位置（怪物左侧）
            int startOffset = -(totalCards - 1) / 2;
            int currentOffset = startOffset;

            // 先显示要抽的牌（从左向右）
            for (int idCard = 0; idCard < showDrawNum; idCard++) {
                AbstractCard card = shownCards.drawingCards.get(idCard);
                if (card != null) {
                    card.current_y = yCenter;
                    card.target_y = yCenter;
                    card.target_x = xCenter + currentOffset * 15.0F * Settings.scale;
                    card.current_x = card.target_x;
                    //更新卡牌的缩放大小
                    card.targetDrawScale = SHOW_SCALE;
                    card.drawScale = SHOW_SCALE;
                    //设置这个牌的透明度
                    CardShowChange.setCardSemiTransparent(card);
                }
                currentOffset++;
            }

            // 更新已出卡牌的显示（从左向右）
            for (AbstractCard card : shownCards.cardList) {
                if (card != null) {
                    //更新卡牌的位置
                    card.current_y = yCenter;
                    card.target_y = yCenter;
                    card.target_x = xCenter + currentOffset * 15.0F * Settings.scale;
                    card.current_x = card.target_x;
                    //更新卡牌的缩放大小
                    card.targetDrawScale = SHOW_SCALE;
                    card.drawScale = SHOW_SCALE;
                    card.unfadeOut();
                }
                currentOffset++;
            }
        }

        // 渲染所有卡牌（修复：添加渲染调用）
        int renderOffset = -(totalCards - 1) / 2;

        // 渲染即将抽到的牌
        for (int idCard = 0; idCard < showDrawNum; idCard++) {
            AbstractCard card = shownCards.drawingCards.get(idCard);
            if (card != null) {
                float cardX = xCenter + renderOffset * 15.0F * Settings.scale;
                float cardY = yCenter;

                // 检查悬停效果
                boolean isHovered = isCardHovered(card, cardX, cardY);
                applyHoverEffect(card, isHovered);

                card.render(sb);
                renderOffset++;
            }
        }

        // 渲染已出卡牌
        for (AbstractCard card : shownCards.cardList) {
            if (card != null) {
                float cardX = xCenter + renderOffset * 15.0F * Settings.scale;
                float cardY = yCenter;

                // 检查悬停效果
                boolean isHovered = isCardHovered(card, cardX, cardY);
                applyHoverEffect(card, isHovered);

                //渲染这个牌
                card.render(sb);
                renderOffset++;
            }
        }
    }

}
