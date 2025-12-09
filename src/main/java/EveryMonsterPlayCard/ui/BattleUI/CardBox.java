package EveryMonsterPlayCard.ui.BattleUI;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import EveryMonsterPlayCard.monstercards.CardShowChange;
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
            for (int idCard = showDrawNum - 1; idCard >= 0; --idCard) {
                AbstractCard card = shownCards.drawingCards.get(idCard);
                if (card != null) {
                    card.target_y = yCenter;
                    // 修复：使用hitbox宽度作为间距而非IMG_WIDTH
                    card.target_x = xCenter + xOffset * card.hb.width * SHOW_SCALE;
                    // 修复：对于所有卡牌都更新，包括fadingOut的卡牌，以确保持续跟随
                    xOffset++;
                }
            }

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

        //下回合抽牌显示的数量
        int showDrawNum = Math.min(MAX_SHOW_NUM-shownCards.cardList.size(),shownCards.drawingCards.size());
        //有圆顶的情况下不显示即将抽到的牌
        // 注释掉SocketServer引用: hasDome检查
            showDrawNum=0;
        int xOffset = getXOffsetById(shownCards.cardList.size() + showDrawNum -1);
        //先显示要抽的牌
        for(int idCard=showDrawNum-1;idCard>=0;--idCard)
        {
            //当前的牌
            AbstractCard card = shownCards.drawingCards.get(idCard);
            //判断是否需要更新位置
            if(updateLocation)
            {
                card.current_y = yCenter;
                card.target_y = yCenter;
                card.target_x = xCenter + xOffset * card.hb.width * SHOW_SCALE;
                card.current_x = card.target_x;
                //更新卡牌的缩放大小
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;
                ++xOffset;
                //强行设置这个牌的透明度
                CardShowChange.setCardSemiTransparent(card);
            }

            //修复：添加悬停效果检测
            boolean isHovered = isCardHovered(card);
            if (isHovered) {
                // 悬停时设为完全不透明并轻微放大
                CardShowChange.setCardFullyVisible(card);
                card.targetDrawScale = SHOW_SCALE * 1.1f;
                card.drawScale = card.targetDrawScale;
            } else if (updateLocation) {
                // 非悬停时恢复半透明和原缩放
                CardShowChange.setCardSemiTransparent(card);
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;
            }

            card.render(sb);
        }
        for (AbstractCard card : shownCards.cardList) {
            //获取当前位置的牌
            //判断是否需要更新位置
            if (updateLocation) {
                //更新卡牌的位置
                card.current_y = yCenter;
                card.target_y = yCenter;
                card.target_x = xCenter + xOffset * card.hb.width * SHOW_SCALE;
                card.current_x = card.target_x;
                //更新卡牌的缩放大小
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;
                ++xOffset;
                card.unfadeOut();
            }

            //修复：添加悬停效果检测（已出卡牌）
            boolean isHovered = isCardHovered(card);
            if (isHovered) {
                // 悬停时设为完全不透明并轻微放大
                CardShowChange.setCardFullyVisible(card);
                card.targetDrawScale = SHOW_SCALE * 1.1f;
                card.drawScale = card.targetDrawScale;
            } else if (updateLocation) {
                // 非悬停时恢复半透明和原缩放
                CardShowChange.setCardSemiTransparent(card);
                card.targetDrawScale = SHOW_SCALE;
                card.drawScale = SHOW_SCALE;
            }

            //渲染这个牌
            card.render(sb);
        }
    }

}
