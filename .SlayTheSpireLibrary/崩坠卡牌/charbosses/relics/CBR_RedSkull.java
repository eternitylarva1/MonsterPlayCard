/*    */ package charbosses.relics;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.RedSkull;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CBR_RedSkull extends AbstractCharbossRelic {
/*    */   public static final String ID = "RedSkull";
/*    */   
/*    */   public CBR_RedSkull() {
/* 17 */     super((AbstractRelic)new RedSkull());
/* 18 */     this.isActive = false;
/*    */   }
/*    */   private boolean isActive;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBloodied() {
/* 28 */     flash();
/* 29 */     this.pulse = true;
/* 30 */     if (!this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 31 */       AbstractCharBoss p = AbstractCharBoss.boss;
/* 32 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, 3), 3));
/* 33 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)p, this));
/* 34 */       this.isActive = true;
/* 35 */       p.hand.applyPowers();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNotBloodied() {
/* 41 */     if (this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 42 */       AbstractCharBoss p = AbstractCharBoss.boss;
/* 43 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, -3), -3));
/*    */     } 
/* 45 */     stopPulse();
/* 46 */     this.isActive = false;
/* 47 */     AbstractCharBoss.boss.hand.applyPowers();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 52 */     this.pulse = false;
/* 53 */     this.isActive = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 58 */     return new CBR_RedSkull();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_RedSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */