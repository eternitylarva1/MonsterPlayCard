/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Toolbox;
/*    */ 
/*    */ public class CBR_Toolbox
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Toolbox";
/*    */   
/*    */   public CBR_Toolbox() {
/* 11 */     super((AbstractRelic)new Toolbox());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_Toolbox();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Toolbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */