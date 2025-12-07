/*    */ package charbosses.patches;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplyFocusrPatch
/*    */ {
/*    */   @SpirePatch(clz = RemoveSpecificPowerAction.class, method = "update")
/*    */   public static class ApplyFocusrPatchA
/*    */   {
/*    */     @SpireInsertPatch(rloc = 24)
/*    */     public static SpireReturn<Void> Insert(RemoveSpecificPowerAction instance) {
/* 29 */       if (AbstractCharBoss.boss != null) {
/* 30 */         for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/* 31 */           o.updateDescription();
/*    */         }
/*    */       }
/*    */       
/* 35 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = AbstractDungeon.class, method = "onModifyPower")
/*    */   public static class ApplyFocusrPatchB
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn Prefix() {
/* 48 */       if (AbstractCharBoss.boss != null) {
/* 49 */         for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/* 50 */           o.updateDescription();
/*    */         }
/*    */       }
/*    */       
/* 54 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\ApplyFocusrPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */