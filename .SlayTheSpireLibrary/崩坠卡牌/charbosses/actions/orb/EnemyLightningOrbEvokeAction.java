/*    */ package charbosses.actions.orb;
/*    */ 
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
/*    */ 
/*    */ public class EnemyLightningOrbEvokeAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public EnemyLightningOrbEvokeAction(DamageInfo info, boolean hitAll) {
/* 20 */     this.info = info;
/* 21 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 22 */     this.attackEffect = AbstractGameAction.AttackEffect.NONE;
/* 23 */     this.hitAll = hitAll;
/*    */   }
/*    */   private boolean hitAll;
/*    */   
/*    */   public void update() {
/* 28 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 29 */     float speedTime = 0.1F;
/* 30 */     if (!AbstractDungeon.player.orbs.isEmpty()) {
/* 31 */       speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/*    */     }
/*    */     
/* 34 */     if (Settings.FAST_MODE) {
/* 35 */       speedTime = 0.0F;
/*    */     }
/*    */     
/* 38 */     this.info.output = AbstractOrb.applyLockOn((AbstractCreature)abstractPlayer, this.info.base);
/* 39 */     addToTop((AbstractGameAction)new DamageAction((AbstractCreature)abstractPlayer, this.info, AbstractGameAction.AttackEffect.NONE, true));
/* 40 */     addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(((AbstractCreature)abstractPlayer).drawX, ((AbstractCreature)abstractPlayer).drawY), speedTime));
/* 41 */     addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/*    */ 
/*    */     
/* 44 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyLightningOrbEvokeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */