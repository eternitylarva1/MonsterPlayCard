/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Anchor;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CBR_Anchor
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_Anchor() {
/* 15 */     super((AbstractRelic)new Anchor());
/*    */   }
/*    */   public static final String ID = "Anchor";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\n' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     flash();
/* 26 */     if (AbstractCharBoss.boss.chosenArchetype instanceof charbosses.bosses.Silent.NewAge.ArchetypeAct2MirrorImageNewAge) {
/*    */       
/* 28 */       AbstractCharBoss.boss.addBlock(10);
/*    */     } else {
/* 30 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 31 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, 10));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.grayscale = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 41 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 46 */     return new CBR_Anchor();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Anchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */