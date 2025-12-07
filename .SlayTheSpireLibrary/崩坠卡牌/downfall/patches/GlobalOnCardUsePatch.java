/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import theHexaghost.GhostflameHelper;
/*    */ import theHexaghost.HexaMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardGroup.class, method = "triggerOnOtherCardPlayed")
/*    */ public class GlobalOnCardUsePatch
/*    */ {
/*    */   public static void Prefix(CardGroup __instance, AbstractCard abstractCard) {
/* 16 */     if (GhostflameHelper.activeGhostFlame == null)
/* 17 */       return;  if (!GhostflameHelper.activeGhostFlame.charged && HexaMod.renderFlames && GhostflameHelper.activeGhostFlame.advanceOnCardUse)
/* 18 */       GhostflameHelper.activeGhostFlame.advanceTrigger(abstractCard); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalOnCardUsePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */