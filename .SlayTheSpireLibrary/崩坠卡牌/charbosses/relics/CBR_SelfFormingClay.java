/*    */ package charbosses.relics;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SelfFormingClay;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CBR_SelfFormingClay extends AbstractCharbossRelic {
/*    */   public CBR_SelfFormingClay() {
/* 15 */     super((AbstractRelic)new SelfFormingClay());
/*    */   }
/*    */   public static final String ID = "SelfFormingClay";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void wasHPLost(int damageAmount) {
/* 25 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && damageAmount > 0) {
/* 26 */       flash();
/* 27 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new NextTurnBlockPower((AbstractCreature)AbstractCharBoss.boss, 3, this.name), 3));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_SelfFormingClay();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SelfFormingClay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */