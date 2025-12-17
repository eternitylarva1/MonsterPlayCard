package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 伤口状态卡
 */
public class EnWound extends AbstractMonsterCard {
    public static final String ID = "EnWound";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Wound");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnWound() {
        super(ID, NAME, "status/wound", -2, DESCRIPTION, 
              AbstractCard.CardType.STATUS, AbstractCard.CardColor.COLORLESS, 
              AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 状态卡通常没有使用效果
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnWound();
    }
    
    @Override
    public void upgrade() {
        // 状态卡通常不能升级
    }
}