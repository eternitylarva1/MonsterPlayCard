/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.powers.cardpowers.EnemyMantraPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WatcherDivinityPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:WatcherDivinityPower";
/*    */   
/*    */   public WatcherDivinityPower(AbstractCreature owner) {
/* 31 */     this.name = NAME;
/* 32 */     this.ID = "downfall:WatcherDivinityPower";
/* 33 */     this.owner = owner;
/* 34 */     this.amount = 0;
/* 35 */     updateDescription();
/* 36 */     loadRegion("curiosity");
/* 37 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = DESC[0];
/*    */   }
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 45 */     if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/* 46 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new EnemyMantraPower(this.owner, 1), 1));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:WatcherDivinityPower");
/* 53 */   public static final String NAME = powerStrings.NAME;
/* 54 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\WatcherDivinityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */