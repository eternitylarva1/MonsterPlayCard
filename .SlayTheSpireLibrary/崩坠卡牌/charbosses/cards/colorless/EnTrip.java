/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnTrip
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Trip";
/*    */   
/*    */   public EnTrip() {
/* 30 */     super("downfall_Charboss:Trip", cardStrings.NAME, "colorless/skill/trip", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.DEBUFF);
/* 31 */     this.baseMagicNumber = 2;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/* 33 */     this.artifactConsumedIfPlayed = 1;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return (AbstractCard)new EnTrip();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       this.target = AbstractCard.CardTarget.ALL_ENEMY;
/* 50 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 51 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String overrideIntentText() {
/* 56 */     if (AbstractCharBoss.boss.hasPower("Sadistic")) {
/* 57 */       int count = this.magicNumber + (AbstractCharBoss.boss.getPower("Sadistic")).amount;
/* 58 */       return "(" + count + ")";
/*    */     } 
/* 60 */     return super.overrideIntentText();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 66 */     return 10;
/*    */   }
/*    */ 
/*    */   
/* 70 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Trip");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnTrip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */