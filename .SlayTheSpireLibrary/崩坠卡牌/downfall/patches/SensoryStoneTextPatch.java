/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.screens.CardRewardScreen;
/*    */ import downfall.events.SensoryStone_Evil;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardRewardScreen.class, method = "acquireCard")
/*    */ public class SensoryStoneTextPatch
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void Postfix(CardRewardScreen _instance, AbstractCard c) {
/* 18 */     AbstractEvent e = (AbstractDungeon.getCurrRoom()).event;
/*    */     
/* 20 */     if (e != null)
/*    */     {
/* 22 */       if (e instanceof SensoryStone_Evil) {
/*    */         
/* 24 */         SensoryStone_Evil ee = (SensoryStone_Evil)e;
/* 25 */         ee.setMemoryCard(c);
/* 26 */         ee.getMemoryText();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\SensoryStoneTextPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */