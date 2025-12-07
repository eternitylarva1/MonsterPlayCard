/*    */ package charbosses.helpers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import java.util.HashSet;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class EnemyGetAllInBattleInstances
/*    */ {
/*    */   public static HashSet<AbstractCard> get(UUID uuid) {
/* 11 */     HashSet<AbstractCard> cards = new HashSet<>();
/* 12 */     if (AbstractCharBoss.boss.cardInUse.uuid.equals(uuid)) {
/* 13 */       cards.add(AbstractCharBoss.boss.cardInUse);
/*    */     }
/*    */     
/* 16 */     for (AbstractCard c : AbstractCharBoss.boss.limbo.group) {
/* 17 */       if (!c.uuid.equals(uuid)) {
/*    */         continue;
/*    */       }
/* 20 */       cards.add(c);
/*    */     } 
/* 22 */     for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/* 23 */       if (!c.uuid.equals(uuid)) {
/*    */         continue;
/*    */       }
/* 26 */       cards.add(c);
/*    */     } 
/* 28 */     return cards;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\helpers\EnemyGetAllInBattleInstances.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */