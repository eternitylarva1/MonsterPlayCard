/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Mango;
/*    */ 
/*    */ public class CBR_Mango extends AbstractCharbossRelic {
/*    */   public static final String ID = "Mango";
/*    */   
/*    */   public CBR_Mango() {
/* 11 */     super((AbstractRelic)new Mango());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\016' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     this.owner.increaseMaxHp(14, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 26 */     return new CBR_Mango();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Mango.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */