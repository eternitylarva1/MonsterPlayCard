/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.FearNoEvilAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FearNoEvilForHeroPatch
/*    */ {
/*    */   static AbstractMonster boss;
/*    */   
/*    */   @SpirePatch(clz = FearNoEvilAction.class, method = "<ctor>")
/*    */   public static class GetMonsterVariable
/*    */   {
/*    */     public static void Prefix(FearNoEvilAction __instance, AbstractMonster m, DamageInfo info) {
/* 21 */       FearNoEvilForHeroPatch.boss = m;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = FearNoEvilAction.class, method = "update")
/*    */   public static class ApplyBossIntentCheck
/*    */   {
/*    */     public static void Prefix(FearNoEvilAction __instance) {
/* 31 */       if (FearNoEvilForHeroPatch.boss != null && FearNoEvilForHeroPatch.boss.getIntentBaseDmg() >= 0)
/* 32 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ChangeStanceAction("Calm")); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\FearNoEvilForHeroPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */