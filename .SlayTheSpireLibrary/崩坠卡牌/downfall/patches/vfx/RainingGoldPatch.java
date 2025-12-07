/*    */ package downfall.patches.vfx;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ import downfall.vfx.RainingSoulsEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = RainingGoldEffect.class, method = "<ctor>", paramtypez = {int.class})
/*    */ public class RainingGoldPatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn<?> Prefix(RainingGoldEffect __instance, int amount) {
/* 20 */     if (EvilModeCharacterSelect.evilMode) {
/* 21 */       AbstractDungeon.effectList.add(new RainingSoulsEffect(amount));
/* 22 */       AbstractDungeon.effectList.remove(__instance);
/* 23 */       return SpireReturn.Return(null);
/*    */     } 
/* 25 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\vfx\RainingGoldPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */