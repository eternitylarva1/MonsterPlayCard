/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.DollysMirror;
/*    */ 
/*    */ public class CBR_DollysMirror
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "DollysMirror";
/*  9 */   private String addedDesc = "";
/*    */   
/*    */   public CBR_DollysMirror() {
/* 12 */     super((AbstractRelic)new DollysMirror());
/* 13 */     this.tier = AbstractRelic.RelicTier.RARE;
/*    */   }
/*    */   
/*    */   public CBR_DollysMirror(String cardName) {
/* 17 */     super((AbstractRelic)new DollysMirror());
/* 18 */     this.tier = AbstractRelic.RelicTier.RARE;
/* 19 */     this.addedDesc = cardName;
/*    */     
/* 21 */     this.description = getUpdatedDescription();
/* 22 */     refreshDescription();
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 26 */     return this.DESCRIPTIONS[0] + this.addedDesc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 37 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new CBR_DollysMirror();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_DollysMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */