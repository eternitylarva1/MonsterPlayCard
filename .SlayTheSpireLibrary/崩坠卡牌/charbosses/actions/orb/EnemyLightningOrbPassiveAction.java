/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*    */ 
/*    */ public class EnemyLightningOrbPassiveAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public EnemyLightningOrbPassiveAction(DamageInfo info, AbstractOrb orb, boolean hitAll) {
/* 23 */     this.info = info;
/* 24 */     this.orb = orb;
/* 25 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 26 */     this.attackEffect = AbstractGameAction.AttackEffect.NONE;
/* 27 */     this.hitAll = hitAll;
/*    */   }
/*    */   private AbstractOrb orb; private boolean hitAll;
/*    */   public void update() {
/* 31 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/*    */     
/* 33 */     float speedTime = 0.2F / AbstractCharBoss.boss.orbs.size();
/* 34 */     if (Settings.FAST_MODE) {
/* 35 */       speedTime = 0.0F;
/*    */     }
/*    */     
/* 38 */     this.info.output = AbstractOrb.applyLockOn((AbstractCreature)abstractPlayer, this.info.base);
/* 39 */     addToTop((AbstractGameAction)new DamageAction((AbstractCreature)abstractPlayer, this.info, AbstractGameAction.AttackEffect.NONE, true));
/* 40 */     addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(((AbstractCreature)abstractPlayer).drawX, ((AbstractCreature)abstractPlayer).drawY), speedTime));
/* 41 */     addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/* 42 */     if (this.orb != null) {
/* 43 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this.orb, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*    */     }
/*    */     
/* 46 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyLightningOrbPassiveAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */