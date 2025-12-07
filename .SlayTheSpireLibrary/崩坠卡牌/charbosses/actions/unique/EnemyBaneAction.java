/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyBaneAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   private AbstractCreature m;
/*    */   
/*    */   public EnemyBaneAction(AbstractCreature target, DamageInfo info) {
/* 21 */     this.info = info;
/* 22 */     setValues(target, info);
/* 23 */     this.m = target;
/* 24 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 25 */     this.attackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
/* 26 */     this.duration = 0.01F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 30 */     if (this.target == null) {
/* 31 */       this.isDone = true;
/*    */     }
/* 33 */     else if (this.m.hasPower("Poison")) {
/* 34 */       if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
/* 35 */         if (this.info.type != DamageInfo.DamageType.THORNS && this.info.owner.isDying) {
/* 36 */           this.isDone = true;
/*    */           
/*    */           return;
/*    */         } 
/* 40 */         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */       } 
/*    */       
/* 43 */       tickDuration();
/* 44 */       if (this.isDone && this.target != null && this.target.currentHealth > 0) {
/* 45 */         this.target.damage(this.info);
/* 46 */         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 47 */           AbstractDungeon.actionManager.clearPostCombatActions();
/*    */         }
/*    */         
/* 50 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */     } else {
/* 53 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyBaneAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */