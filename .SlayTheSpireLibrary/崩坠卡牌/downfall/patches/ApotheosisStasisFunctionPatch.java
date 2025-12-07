/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.FunctionHelper;
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import guardian.orbs.StasisOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApotheosisStasisFunctionPatch
/*    */ {
/*    */   @SpirePatch(clz = ApotheosisAction.class, method = "update")
/*    */   public static class TimeToLearn
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static void Prefix(ApotheosisAction instance) {
/* 26 */       float duration = ((Float)ReflectionHacks.getPrivate(instance, AbstractGameAction.class, "duration")).floatValue();
/* 27 */       if (duration == Settings.ACTION_DUR_MED) {
/* 28 */         for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 29 */           if (o instanceof StasisOrb) {
/* 30 */             ((StasisOrb)o).stasisCard.upgrade();
/*    */           }
/*    */         } 
/* 33 */         if (FunctionHelper.doStuff) {
/* 34 */           for (AbstractCard q : FunctionHelper.held.group) {
/* 35 */             q.upgrade();
/*    */           }
/* 37 */           FunctionHelper.genPreview();
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ApotheosisStasisFunctionPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */