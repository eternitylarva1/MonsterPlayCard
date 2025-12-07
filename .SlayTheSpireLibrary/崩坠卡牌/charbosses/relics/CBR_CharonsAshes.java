/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CharonsAshes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_CharonsAshes
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "CharonsAshes";
/*    */   
/*    */   public CBR_CharonsAshes() {
/* 25 */     super((AbstractRelic)new CharonsAshes());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 29 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onExhaust(AbstractCard card) {
/* 34 */     flash();
/* 35 */     addToTop((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)this.owner, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     
/* 37 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 38 */       if (!mo.isDead) {
/* 39 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)mo, this));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 46 */     return new CBR_CharonsAshes();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CharonsAshes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */