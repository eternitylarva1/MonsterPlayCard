/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Waffle;
/*    */ 
/*    */ public class CBR_LeesWaffle
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "LeesWaffle";
/*    */   
/*    */   public CBR_LeesWaffle() {
/* 12 */     super((AbstractRelic)new Waffle());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\007' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     this.owner.increaseMaxHp(7, true);
/* 22 */     this.owner.heal(this.owner.maxHealth);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new CBR_LeesWaffle();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_LeesWaffle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */