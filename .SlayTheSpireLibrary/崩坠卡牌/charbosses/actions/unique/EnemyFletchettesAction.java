/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnemyFletchettesAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public EnemyFletchettesAction(AbstractCreature target, DamageInfo info) {
/* 20 */     setValues(target, this.info = info);
/* 21 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 22 */     this.info = info;
/* 23 */     this.actionType = AbstractGameAction.ActionType.BLOCK;
/* 24 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     Iterator<AbstractCard> var1 = AbstractCharBoss.boss.hand.group.iterator();
/*    */     
/* 31 */     while (var1.hasNext()) {
/* 32 */       AbstractCard c = var1.next();
/* 33 */       if (c.type == AbstractCard.CardType.SKILL) {
/* 34 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, true));
/* 35 */         if (this.target != null && this.target.hb != null) {
/* 36 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyFletchettesAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */