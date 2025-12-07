/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.powers.general.EnemyPoisonPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyNoxiousFumesPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Enemy Noxious Fumes";
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Noxious Fumes");
/*    */ 
/*    */   
/*    */   public EnemyNoxiousFumesPower(AbstractCreature owner, int bladeAmt) {
/* 21 */     this.name = powerStrings.NAME;
/* 22 */     this.ID = "downfall:Enemy Noxious Fumes";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = bladeAmt;
/* 25 */     updateDescription();
/* 26 */     loadRegion("fumes");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 31 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 32 */       flash();
/* 33 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new EnemyPoisonPower((AbstractCreature)AbstractDungeon.player, this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 39 */     this.fontScale = 8.0F;
/* 40 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 45 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyNoxiousFumesPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */