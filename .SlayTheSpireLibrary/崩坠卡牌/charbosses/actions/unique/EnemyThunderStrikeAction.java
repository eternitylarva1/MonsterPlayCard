/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyThunderStrikeAction
/*    */   extends AbstractGameAction
/*    */ {
/* 21 */   private DamageInfo info = null;
/*    */   private AbstractCreature target;
/*    */   AbstractBossCard card;
/*    */   
/*    */   public EnemyThunderStrikeAction(AbstractBossCard card, AbstractCreature m) {
/* 26 */     this.target = m;
/* 27 */     this.card = card;
/*    */   }
/*    */   
/*    */   public void update() {
/* 31 */     if (!Settings.FAST_MODE) {
/* 32 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     }
/*    */     
/* 35 */     if (this.target != null) {
/* 36 */       addToTop((AbstractGameAction)new DamageAction(this.target, new DamageInfo((AbstractCreature)this.card.owner, this.card.damage, this.card.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 37 */       this.isDone = true;
/* 38 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.target.drawX, this.target.drawY)));
/* 39 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect)));
/* 40 */       addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyThunderStrikeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */