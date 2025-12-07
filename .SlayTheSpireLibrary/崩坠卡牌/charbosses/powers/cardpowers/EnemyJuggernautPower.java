/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyJuggernautPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Juggernaut";
/*    */   
/*    */   public EnemyJuggernautPower(AbstractCreature owner, int newAmount) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "Juggernaut";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = newAmount;
/* 25 */     updateDescription();
/* 26 */     loadRegion("juggernaut");
/*    */   }
/*    */   
/*    */   public void onGainedBlock(float blockAmount) {
/* 30 */     if (blockAmount > 0.0F) {
/* 31 */       flash();
/* 32 */       addToTop((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 42 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Juggernaut");
/* 43 */   public static final String NAME = powerStrings.NAME;
/* 44 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyJuggernautPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */