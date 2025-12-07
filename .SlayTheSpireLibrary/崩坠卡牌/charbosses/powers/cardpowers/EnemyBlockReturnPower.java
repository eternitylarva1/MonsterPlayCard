/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyBlockReturnPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "BlockReturnPower";
/*    */   
/*    */   public EnemyBlockReturnPower(AbstractCreature owner, int blockAmt) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "BlockReturnPower";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = blockAmt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("talk_to_hand");
/* 25 */     this.type = AbstractPower.PowerType.DEBUFF;
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 29 */     this.fontScale = 8.0F;
/* 30 */     this.amount += stackAmount;
/* 31 */     updateDescription();
/*    */   }
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 35 */     if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
/* 36 */       flash();
/* 37 */       if (AbstractCharBoss.boss != null) {
/* 38 */         addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, this.amount, Settings.FAST_MODE));
/*    */       }
/*    */     } 
/* 41 */     return damageAmount;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 45 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 49 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BlockReturnPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyBlockReturnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */