/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class EnemyWaveOfTheHandPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "WaveOfTheHandPower";
/*    */   
/*    */   public EnemyWaveOfTheHandPower(AbstractCreature owner, int newAmount) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "WaveOfTheHandPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = newAmount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("wave_of_the_hand");
/*    */   }
/*    */   
/*    */   public void onGainedBlock(float blockAmount) {
/* 29 */     if (blockAmount > 0.0F) {
/* 30 */       flash();
/* 31 */       AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/* 32 */       AbstractPlayer abstractPlayer = AbstractDungeon.player;
/*    */       
/* 34 */       if (abstractCharBoss != null) {
/* 35 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractCharBoss, (AbstractPower)new WeakPower((AbstractCreature)abstractPlayer, this.amount, true), this.amount, true, AbstractGameAction.AttackEffect.NONE));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 42 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "WaveOfTheHandPower"));
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 46 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 50 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WaveOfTheHandPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyWaveOfTheHandPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */