/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyEnergyDownPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "EnergyDownPower";
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("EnergyDownPower");
/*    */   
/*    */   public EnemyEnergyDownPower(AbstractCreature owner, int amount, boolean isFasting) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "EnergyDownPower";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
/* 24 */     this.type = AbstractPower.PowerType.DEBUFF;
/*    */ 
/*    */     
/* 27 */     if (isFasting) {
/* 28 */       loadRegion("fasting");
/*    */     } else {
/* 30 */       loadRegion("energized_blue");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EnemyEnergyDownPower(AbstractCreature owner, int amount) {
/* 36 */     this(owner, amount, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     StringBuilder sb = new StringBuilder();
/* 42 */     sb.append(powerStrings.DESCRIPTIONS[0]);
/* 43 */     for (int i = 0; i < this.amount; i++) {
/* 44 */       sb.append("[E] ");
/*    */     }
/* 46 */     if (powerStrings.DESCRIPTIONS[1].isEmpty()) {
/* 47 */       sb.append(LocalizedStrings.PERIOD);
/*    */     } else {
/* 49 */       sb.append(powerStrings.DESCRIPTIONS[1]);
/*    */     } 
/* 51 */     this.description = sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 56 */     flash();
/* 57 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(-this.amount));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyEnergyDownPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */