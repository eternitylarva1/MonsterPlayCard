/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Torii;
/*    */ 
/*    */ public class CBR_Torii extends AbstractCharbossRelic {
/*    */   public CBR_Torii() {
/* 13 */     super((AbstractRelic)new Torii());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   public static final String ID = "Torii";
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 23 */     if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 1 && damageAmount <= 5) {
/* 24 */       flash();
/* 25 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 26 */       return 1;
/*    */     } 
/* 28 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_Torii();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Torii.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */