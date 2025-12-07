/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyPenNibPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Pen Nib";
/*    */   
/*    */   public EnemyPenNibPower(AbstractCreature owner, int amount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Pen Nib";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     updateDescription();
/* 27 */     loadRegion("penNib");
/* 28 */     this.type = AbstractPower.PowerType.BUFF;
/* 29 */     this.isTurnBased = true;
/* 30 */     this.priority = 6;
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 34 */     if (card.type == AbstractCard.CardType.ATTACK && card instanceof charbosses.cards.AbstractBossCard) {
/* 35 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Pen Nib"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 45 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage * 2.0F) : damage;
/*    */   }
/*    */ 
/*    */   
/* 49 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Pen Nib");
/* 50 */   public static final String NAME = powerStrings.NAME;
/* 51 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyPenNibPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */