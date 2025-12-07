/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Pear;
/*    */ 
/*    */ public class CBR_Pear
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Pear";
/*    */   
/*    */   public CBR_Pear() {
/* 12 */     super((AbstractRelic)new Pear());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\n' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     this.owner.increaseMaxHp(10, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 26 */     return new CBR_Pear();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Pear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */