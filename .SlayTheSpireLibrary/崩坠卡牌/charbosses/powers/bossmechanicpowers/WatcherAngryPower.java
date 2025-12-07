/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WatcherAngryPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   private boolean active = false;
/*    */   
/*    */   public WatcherAngryPower(AbstractCreature owner) {
/* 25 */     this.name = NAME;
/* 26 */     this.ID = "downfall:WatcherAngryPower";
/* 27 */     this.owner = owner;
/* 28 */     updateDescription();
/* 29 */     loadRegion("curiosity");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 38 */     super.atEndOfRound();
/* 39 */     if (!this.active && this.owner.currentHealth <= this.owner.maxHealth / 2) {
/*    */       
/* 41 */       addToBot((AbstractGameAction)new EnemyChangeStanceAction("Wrath"));
/* 42 */       this.active = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/* 49 */     if (damage > 1.0F && 
/* 50 */       this.owner instanceof AbstractCharBoss && 
/* 51 */       ((AbstractCharBoss)this.owner).stance instanceof charbosses.stances.EnWrathStance) {
/* 52 */       return damage * 1.5F;
/*    */     }
/*    */ 
/*    */     
/* 56 */     return damage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 61 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:WatcherAngryPower");
/* 62 */   public static final String NAME = powerStrings.NAME;
/* 63 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "downfall:WatcherAngryPower";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\WatcherAngryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */