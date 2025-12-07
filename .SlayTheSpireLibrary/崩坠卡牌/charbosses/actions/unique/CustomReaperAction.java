/*    */ package charbosses.actions.unique;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
/*    */ 
/*    */ public class CustomReaperAction extends AbstractGameAction {
/*    */   public CustomReaperAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect) {
/* 17 */     setValues(null, source, amount[0]);
/* 18 */     this.damage = amount;
/* 19 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 20 */     this.damageType = type;
/* 21 */     this.attackEffect = effect;
/* 22 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   public int[] damage;
/*    */   
/*    */   public void update() {
/* 27 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 28 */       boolean playedMusic = false;
/* 29 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/* 30 */       for (int i = 0; i < temp; i++) {
/* 31 */         if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDying && 
/* 32 */           ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).currentHealth > 0 && 
/* 33 */           !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isEscaping) {
/* 34 */           if (playedMusic) {
/* 35 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*    */                   
/* 37 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/* 38 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
/*    */           } else {
/* 40 */             playedMusic = true;
/* 41 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*    */                   
/* 43 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/* 44 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect));
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 51 */     tickDuration();
/*    */     
/* 53 */     if (this.isDone) {
/* 54 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/* 55 */         p.onDamageAllEnemies(this.damage);
/*    */       }
/*    */       
/* 58 */       int healAmount = 0;
/* 59 */       for (int i = 0; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size(); i++) {
/* 60 */         AbstractMonster target = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i);
/* 61 */         if (target != this.source && !target.isDying && target.currentHealth > 0 && !target.isEscaping) {
/* 62 */           target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
/* 63 */           if (target.lastDamageTaken > 0) {
/* 64 */             healAmount += target.lastDamageTaken;
/* 65 */             for (int j = 0; j < target.lastDamageTaken / 2 && j < 10; j++) {
/* 66 */               addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlyingOrbEffect(target.hb.cX, target.hb.cY)));
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 72 */       AbstractPlayer targetPlayer = AbstractDungeon.player;
/* 73 */       if (!targetPlayer.isDying && targetPlayer.currentHealth > 0 && !targetPlayer.isEscaping) {
/* 74 */         targetPlayer.damage(new DamageInfo(this.source, this.damage[0], this.damageType));
/* 75 */         if (targetPlayer.lastDamageTaken > 0) {
/* 76 */           healAmount += targetPlayer.lastDamageTaken;
/* 77 */           for (int j = 0; j < targetPlayer.lastDamageTaken / 2 && j < 10; j++) {
/* 78 */             addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlyingOrbEffect(targetPlayer.hb.cX, targetPlayer.hb.cY)));
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 83 */       if (healAmount > 0) {
/* 84 */         if (!Settings.FAST_MODE) {
/* 85 */           addToBot((AbstractGameAction)new WaitAction(0.3F));
/*    */         }
/* 87 */         addToBot((AbstractGameAction)new HealAction(this.source, this.source, healAmount));
/*    */       } 
/*    */       
/* 90 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 91 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 93 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\CustomReaperAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */