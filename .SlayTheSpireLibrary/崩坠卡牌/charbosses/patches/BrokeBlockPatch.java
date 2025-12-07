/*    */ package charbosses.patches;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractCreature.class, method = "brokeBlock")
/*    */ public class BrokeBlockPatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static void Prefix(AbstractCreature instance) {
/* 22 */     if (instance instanceof com.megacrit.cardcrawl.characters.AbstractPlayer && 
/* 23 */       (AbstractDungeon.getMonsters()).monsters.size() > 0 && 
/* 24 */       (AbstractDungeon.getMonsters()).monsters.get(0) instanceof AbstractCharBoss) {
/* 25 */       AbstractCharBoss cB = (AbstractDungeon.getMonsters()).monsters.get(0);
/*    */       
/* 27 */       for (AbstractCharbossRelic abstractCharbossRelic : cB.relics) {
/* 28 */         AbstractCharbossRelic abstractCharbossRelic1 = abstractCharbossRelic;
/* 29 */         abstractCharbossRelic1.onBlockBroken(instance);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\BrokeBlockPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */