/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class EnemyDarkOrbEvokeAction
/*    */   extends AbstractGameAction {
/*    */   private static final float DURATION = 0.1F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   
/*    */   public EnemyDarkOrbEvokeAction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
/* 19 */     this.muteSfx = false;
/* 20 */     setValues((AbstractCreature)AbstractDungeon.player, this.info = info);
/* 21 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 22 */     this.attackEffect = effect;
/* 23 */     this.duration = 0.1F;
/*    */   }
/*    */   private DamageInfo info; private boolean muteSfx;
/*    */   
/*    */   public void update() {
/* 28 */     if ((shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) || this.target == null) {
/* 29 */       this.isDone = true;
/*    */       return;
/*    */     } 
/* 32 */     if (this.duration == 0.1F) {
/* 33 */       this.info.output = AbstractOrb.applyLockOn(this.target, this.info.base);
/* 34 */       if (this.info.type != DamageInfo.DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
/* 35 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 38 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));
/*    */     } 
/* 40 */     tickDuration();
/* 41 */     if (this.isDone) {
/* 42 */       if (this.attackEffect == AbstractGameAction.AttackEffect.POISON) {
/* 43 */         this.target.tint.color = Color.CHARTREUSE.cpy();
/* 44 */         this.target.tint.changeColor(Color.WHITE.cpy());
/* 45 */       } else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE) {
/* 46 */         this.target.tint.color = Color.RED.cpy();
/* 47 */         this.target.tint.changeColor(Color.WHITE.cpy());
/*    */       } 
/* 49 */       this.target.damage(this.info);
/* 50 */       if (!Settings.FAST_MODE)
/* 51 */         addToTop((AbstractGameAction)new WaitAction(0.1F)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyDarkOrbEvokeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */