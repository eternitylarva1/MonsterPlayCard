/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CentennialPuzzle;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_CentennialPuzzle extends AbstractCharbossRelic {
/*    */   public CBR_CentennialPuzzle() {
/* 15 */     super((AbstractRelic)new CentennialPuzzle());
/*    */   }
/*    */   
/*    */   public static final String ID = "CentennialPuzzle";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("CentennialPuzzle"))).DESCRIPTIONS[0];
/*    */   }
/*    */   private static boolean usedThisCombat = false;
/*    */   public void atPreBattle() {
/* 25 */     usedThisCombat = false;
/* 26 */     this.pulse = true;
/* 27 */     beginPulse();
/*    */   }
/*    */   
/*    */   public void wasHPLost(int damageAmount) {
/* 31 */     if (!AbstractDungeon.actionManager.turnHasEnded && 
/* 32 */       damageAmount > 0 && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !usedThisCombat) {
/* 33 */       flash();
/* 34 */       this.pulse = false;
/*    */       
/* 36 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */       
/* 38 */       usedThisCombat = true;
/* 39 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 46 */     this.grayscale = false;
/*    */   }
/*    */   
/*    */   public void onVictory() {
/* 50 */     this.pulse = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 56 */     return new CBR_CentennialPuzzle();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CentennialPuzzle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */