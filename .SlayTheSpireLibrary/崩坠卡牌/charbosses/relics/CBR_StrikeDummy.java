/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.StrikeDummy;
/*    */ 
/*    */ public class CBR_StrikeDummy extends AbstractCharbossRelic {
/*    */   public static final String ID = "StrikeDummy";
/*    */   
/*    */   public CBR_StrikeDummy() {
/* 11 */     super((AbstractRelic)new StrikeDummy());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageModify(float damage, AbstractCard c) {
/* 21 */     if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 22 */       return damage + 3.0F;
/*    */     }
/* 24 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_StrikeDummy();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_StrikeDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */