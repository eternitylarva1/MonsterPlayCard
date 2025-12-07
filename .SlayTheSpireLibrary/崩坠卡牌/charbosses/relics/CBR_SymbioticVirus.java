/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.orbs.EnemyDark;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SymbioticVirus;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_SymbioticVirus
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "SymbioticVirus";
/*    */   
/*    */   public CBR_SymbioticVirus() {
/* 18 */     super((AbstractRelic)new SymbioticVirus());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 29 */     this.owner.channelOrb((AbstractOrb)new EnemyDark());
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_SymbioticVirus();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SymbioticVirus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */