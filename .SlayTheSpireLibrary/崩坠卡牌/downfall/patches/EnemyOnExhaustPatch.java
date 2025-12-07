/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardGroup.class, method = "moveToExhaustPile")
/*    */ public class EnemyOnExhaustPatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static void TriggerEnemyPowers(CardGroup __instance, AbstractCard c) {
/* 23 */     if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null)
/* 24 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 25 */         if (!m.isDying && !m.isEscaping)
/* 26 */           for (AbstractPower p : m.powers) {
/* 27 */             if (p instanceof EnemyOnExhaustPower)
/* 28 */               ((EnemyOnExhaustPower)p).enemyOnExhaust(c); 
/*    */           }  
/*    */       }  
/*    */   }
/*    */   
/*    */   public static interface EnemyOnExhaustPower {
/*    */     void enemyOnExhaust(AbstractCard param1AbstractCard);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EnemyOnExhaustPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */