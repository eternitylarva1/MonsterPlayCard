/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*    */ import guardian.stances.DefensiveMode;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = MonsterGroup.class, method = "applyEndOfTurnPowers")
/*    */ public class GlobalEndOfRoundHook
/*    */ {
/*    */   public static void Postfix(MonsterGroup __instance) {
/* 14 */     if (AbstractDungeon.player.stance instanceof DefensiveMode)
/* 15 */       ((DefensiveMode)AbstractDungeon.player.stance).onEndOfRound(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalEndOfRoundHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */