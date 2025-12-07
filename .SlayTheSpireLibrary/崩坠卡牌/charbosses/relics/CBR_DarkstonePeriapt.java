/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.DarkstonePeriapt;
/*    */ 
/*    */ public class CBR_DarkstonePeriapt extends AbstractCharbossRelic {
/*    */   public static final String ID = "Darkstone Periapt";
/*    */   
/*    */   public CBR_DarkstonePeriapt() {
/* 11 */     super((AbstractRelic)new DarkstonePeriapt());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\006' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_DarkstonePeriapt();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_DarkstonePeriapt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */