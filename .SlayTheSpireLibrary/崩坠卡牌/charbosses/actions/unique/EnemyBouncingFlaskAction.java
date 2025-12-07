/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.powers.general.EnemyPoisonPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
/*    */ 
/*    */ public class EnemyBouncingFlaskAction
/*    */   extends AbstractGameAction {
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   
/*    */   public EnemyBouncingFlaskAction(int amount, AbstractMonster source, int numTimes) {
/* 21 */     this.target = (AbstractCreature)AbstractDungeon.player;
/* 22 */     this.actionType = AbstractGameAction.ActionType.DEBUFF;
/* 23 */     this.duration = 0.01F;
/* 24 */     this.numTimes = numTimes;
/* 25 */     this.amount = amount;
/* 26 */     this.source = source;
/*    */   }
/*    */   private int numTimes; private int amount; private AbstractMonster source;
/*    */   public void update() {
/* 30 */     if (this.target == null) {
/* 31 */       this.isDone = true;
/* 32 */     } else if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 33 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 34 */       this.isDone = true;
/*    */     } else {
/* 36 */       if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 37 */         this.numTimes--;
/* 38 */         addToTop(new EnemyBouncingFlaskAction(this.amount, this.source, this.numTimes));
/* 39 */         addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionBounceEffect(this.target.hb.cX, this.target.hb.cY, this.target.hb.cX, this.target.hb.cY), 0.4F));
/*    */       } 
/*    */       
/* 42 */       if (this.target.currentHealth > 0) {
/* 43 */         addToTop((AbstractGameAction)new ApplyPowerAction(this.target, (AbstractCreature)this.source, (AbstractPower)new EnemyPoisonPower(this.target, (AbstractCreature)this.source, this.amount), this.amount, true, AbstractGameAction.AttackEffect.POISON));
/* 44 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */       
/* 47 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyBouncingFlaskAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */