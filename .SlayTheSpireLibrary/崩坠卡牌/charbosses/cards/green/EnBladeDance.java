/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.cards.colorless.EnShiv;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnBladeDance
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Blade Dance";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade Dance");
/*    */ 
/*    */   
/*    */   public EnBladeDance() {
/* 24 */     super("downfall_Charboss:Blade Dance", cardStrings.NAME, "green/skill/blade_dance", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.ATTACK);
/* 25 */     this.baseMagicNumber = 3;
/* 26 */     this.magicNumber = this.baseMagicNumber;
/* 27 */     this.cardsToPreview = (AbstractCard)new EnShiv();
/* 28 */     this.magicValue = 4;
/* 29 */     this.baseDamage = 4;
/* 30 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnShiv(), this.magicNumber));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int customIntentModifiedDamage() {
/* 41 */     int tmp = 0;
/*    */     
/* 43 */     if (this.owner.hasPower("downfall:Enemy Accuracy")) {
/* 44 */       tmp = (this.owner.getPower("downfall:Enemy Accuracy")).amount;
/*    */     }
/* 46 */     return tmp;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 59 */     return autoPriority() * 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 65 */     return (AbstractCard)new EnBladeDance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnBladeDance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */