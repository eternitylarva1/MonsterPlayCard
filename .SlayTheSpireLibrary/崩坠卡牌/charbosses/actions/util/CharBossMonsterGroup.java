/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.monsters.NeowBoss;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CharBossMonsterGroup
/*    */   extends MonsterGroup
/*    */ {
/*    */   public CharBossMonsterGroup(AbstractMonster[] input) {
/* 17 */     super(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyPreTurnLogic() {
/* 24 */     for (AbstractMonster m : this.monsters) {
/* 25 */       if (m instanceof AbstractCharBoss) {
/* 26 */         AbstractCharBoss cB = (AbstractCharBoss)m;
/* 27 */         if (!m.isDying && !m.isEscaping) {
/* 28 */           for (AbstractPower p : m.powers) {
/* 29 */             if (p instanceof AbstractBossMechanicPower) {
/* 30 */               ((AbstractBossMechanicPower)p).PreRoundLoseBlock();
/*    */             }
/*    */           } 
/* 33 */           if (!m.hasPower("Barricade") && !m.hasPower("Blur")) {
/* 34 */             if (cB.hasRelic("Calipers")) {
/*    */               
/* 36 */               m.loseBlock(15);
/*    */             } else {
/*    */               
/* 39 */               m.loseBlock();
/*    */             } 
/*    */           }
/*    */           
/* 43 */           if (NeowBoss.neowboss != null) {
/* 44 */             if (NeowBoss.neowboss.offscreen)
/*    */             {
/* 46 */               m.applyStartOfTurnPowers();
/*    */             }
/*    */             
/*    */             continue;
/*    */           } 
/*    */           
/* 52 */           m.applyStartOfTurnPowers();
/*    */         } 
/*    */         
/*    */         continue;
/*    */       } 
/* 57 */       m.applyStartOfTurnPowers();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\CharBossMonsterGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */