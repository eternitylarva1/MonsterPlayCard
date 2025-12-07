/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.cards.colorless.EnShiv;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnCloakAndDagger
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Cloak And Dagger";
/* 24 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Cloak And Dagger");
/*    */ 
/*    */   
/*    */   public EnCloakAndDagger() {
/* 28 */     super("downfall_Charboss:Cloak And Dagger", cardStrings.NAME, "green/skill/cloak_and_dagger", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.ATTACK_DEFEND);
/* 29 */     this.baseBlock = 6;
/* 30 */     this.magicNumber = this.baseMagicNumber = 1;
/* 31 */     this.cardsToPreview = (AbstractCard)new EnShiv();
/* 32 */     this.magicValue = 4;
/* 33 */     this.baseDamage = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int customIntentModifiedDamage() {
/* 38 */     int tmp = 0;
/*    */     
/* 40 */     if (this.owner.hasPower("downfall:Enemy Accuracy")) {
/* 41 */       tmp = (this.owner.getPower("downfall:Enemy Accuracy")).amount;
/*    */     }
/* 43 */     return tmp;
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 48 */     super.calculateCardDamage(mo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 53 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 54 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnShiv(), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 59 */     if (!this.upgraded) {
/* 60 */       upgradeName();
/* 61 */       this.isMultiDamage = true;
/* 62 */       upgradeMagicNumber(1);
/* 63 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 64 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 70 */     if (this.upgraded) {
/* 71 */       return autoPriority() * 2;
/*    */     }
/* 73 */     return autoPriority();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 79 */     return (AbstractCard)new EnCloakAndDagger();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnCloakAndDagger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */