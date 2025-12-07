/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BufferPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnBuffer extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Buffer";
/*    */   
/*    */   public EnBuffer() {
/* 20 */     super("downfall_Charboss:Buffer", cardStrings.NAME, "blue/power/buffer", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 21 */     this.baseMagicNumber = 1;
/* 22 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public EnBuffer(boolean text) {
/* 26 */     super("downfall_Charboss:Buffer", cardStrings.NAME, "blue/power/buffer", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 27 */     this.baseMagicNumber = 1;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.alwaysDisplayText = text;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new BufferPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeMagicNumber(1);
/* 41 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 42 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 49 */     return 20;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new EnBuffer();
/*    */   }
/*    */ 
/*    */   
/* 57 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Buffer");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */