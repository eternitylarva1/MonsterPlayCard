/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyBerserkPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Berserk";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Berserk");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EnemyBerserkPower(AbstractCreature owner) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Berserk";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = 1;
/* 22 */     updateDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     if (this.amount == 1) {
/* 29 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 31 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 37 */     flash();
/* 38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(this.amount));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyBerserkPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */