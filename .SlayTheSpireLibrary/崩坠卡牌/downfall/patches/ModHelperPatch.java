/*    */ package downfall.patches;
/*    */ import basemod.ReflectionHacks;
/*    */ import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
/*    */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*    */ import downfall.dailymods.ChampStances;
/*    */ import downfall.dailymods.Enraging;
/*    */ import downfall.dailymods.ExchangeController;
/*    */ import downfall.dailymods.Improvised;
/*    */ import downfall.dailymods.Jewelcrafting;
/*    */ import downfall.dailymods.TransformRewards;
/*    */ import downfall.dailymods.WorldOfGoo;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ @SpirePatch(clz = ModHelper.class, method = "initialize")
/*    */ public class ModHelperPatch {
/*    */   public static void Postfix() {
/* 17 */     HashMap<String, AbstractDailyMod> myMapS = (HashMap<String, AbstractDailyMod>)ReflectionHacks.getPrivateStatic(ModHelper.class, "starterMods");
/* 18 */     HashMap<String, AbstractDailyMod> myMapG = (HashMap<String, AbstractDailyMod>)ReflectionHacks.getPrivateStatic(ModHelper.class, "genericMods");
/* 19 */     HashMap<String, AbstractDailyMod> myMapD = (HashMap<String, AbstractDailyMod>)ReflectionHacks.getPrivateStatic(ModHelper.class, "difficultyMods");
/* 20 */     myMapD.put(Hexed.ID, new Hexed());
/* 21 */     myMapS.put(Improvised.ID, new Improvised());
/* 22 */     myMapG.put(Jewelcrafting.ID, new Jewelcrafting());
/* 23 */     myMapD.put(WorldOfGoo.ID, new WorldOfGoo());
/* 24 */     myMapD.put(ExchangeController.ID, new ExchangeController());
/* 25 */     myMapS.put(ChampStances.ID, new ChampStances());
/* 26 */     myMapD.put(Enraging.ID, new Enraging());
/* 27 */     myMapS.put(TransformRewards.ID, new TransformRewards());
/* 28 */     ReflectionHacks.setPrivateStatic(ModHelper.class, "starterMods", myMapS);
/* 29 */     ReflectionHacks.setPrivateStatic(ModHelper.class, "genericMods", myMapG);
/* 30 */     ReflectionHacks.setPrivateStatic(ModHelper.class, "difficultyMods", myMapD);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ModHelperPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */