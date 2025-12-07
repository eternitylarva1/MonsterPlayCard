/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Circlet;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.dailymods.StatusAbuse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AllRelicsAreCircletsPatch
/*    */ {
/*    */   @SpirePatch(clz = AbstractRoom.class, method = "spawnRelicAndObtain", paramtypez = {float.class, float.class, AbstractRelic.class})
/*    */   public static class RelicPatch
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix(AbstractRoom __instance, float x, float y, AbstractRelic relic) {
/* 26 */       if ((CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(StatusAbuse.ID)) || ModHelper.isModEnabled(StatusAbuse.ID)) {
/* 27 */         if (AbstractDungeon.player.hasRelic("Circlet")) {
/* 28 */           AbstractRelic circ = AbstractDungeon.player.getRelic("Circlet");
/* 29 */           circ.counter++;
/* 30 */           circ.flash();
/*    */         } else {
/* 32 */           Circlet circlet = new Circlet();
/* 33 */           circlet.spawn(x, y);
/* 34 */           circlet.obtain();
/* 35 */           ((AbstractRelic)circlet).isObtained = true;
/* 36 */           ((AbstractRelic)circlet).isAnimating = false;
/* 37 */           ((AbstractRelic)circlet).isDone = false;
/* 38 */           circlet.flash();
/*    */         } 
/* 40 */         return SpireReturn.Return(null);
/*    */       } 
/* 42 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\AllRelicsAreCircletsPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */