/*     */ package charbosses.powers.cardpowers;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnemyAccuracyPower
/*     */   extends AbstractPower
/*     */ {
/*     */   public static final String POWER_ID = "downfall:Enemy Accuracy";
/*     */   public static final String[] DESCRIPTIONS;
/*  17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Accuracy"); static {
/*  18 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*     */   }
/*     */   
/*     */   public EnemyAccuracyPower(AbstractCreature owner, int amt) {
/*  22 */     this.name = powerStrings.NAME;
/*  23 */     this.ID = "downfall:Enemy Accuracy";
/*  24 */     this.owner = owner;
/*  25 */     this.amount = amt;
/*  26 */     updateDescription();
/*  27 */     loadRegion("accuracy");
/*  28 */     updateExistingShivs();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  33 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public void stackPower(int stackAmount) {
/*  38 */     this.fontScale = 8.0F;
/*  39 */     this.amount += stackAmount;
/*  40 */     updateExistingShivs();
/*     */   }
/*     */   
/*     */   private void updateExistingShivs() {
/*  44 */     if (AbstractCharBoss.boss != null && 
/*  45 */       AbstractCharBoss.boss.hand != null) {
/*  46 */       for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/*  47 */         if (c instanceof charbosses.cards.colorless.EnShiv) {
/*  48 */           if (!c.upgraded) {
/*  49 */             c.baseDamage = 4 + this.amount; continue;
/*     */           } 
/*  51 */           c.baseDamage = 6 + this.amount;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDrawOrDiscard() {
/*  99 */     if (AbstractCharBoss.boss != null && 
/* 100 */       AbstractCharBoss.boss.hand != null)
/* 101 */       for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/* 102 */         if (c instanceof charbosses.cards.colorless.EnShiv) {
/* 103 */           if (!c.upgraded) {
/* 104 */             c.baseDamage = 4 + this.amount; continue;
/*     */           } 
/* 106 */           c.baseDamage = 6 + this.amount;
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyAccuracyPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */