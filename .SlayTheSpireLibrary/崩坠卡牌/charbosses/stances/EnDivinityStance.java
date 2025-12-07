/*    */ package charbosses.stances;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.vfx.EnemyDivinityParticleEffect;
/*    */ import charbosses.vfx.EnemyStanceAuraEffect;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
/*    */ 
/*    */ 
/*    */ public class EnDivinityStance
/*    */   extends AbstractEnemyStance
/*    */ {
/*    */   public static final String STANCE_ID = "Divinity";
/*    */   
/*    */   public EnDivinityStance() {
/* 26 */     this.ID = "Divinity";
/* 27 */     this.name = stanceString.NAME;
/* 28 */     updateDescription();
/*    */   }
/*    */   
/*    */   public void updateAnimation() {
/* 32 */     if (AbstractCharBoss.boss != null) {
/* 33 */       if (!Settings.DISABLE_EFFECTS) {
/* 34 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 35 */         if (this.particleTimer < 0.0F) {
/* 36 */           this.particleTimer = 0.2F;
/* 37 */           AbstractDungeon.effectsQueue.add(new EnemyDivinityParticleEffect());
/*    */         } 
/*    */       } 
/*    */       
/* 41 */       this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 42 */       if (this.particleTimer2 < 0.0F) {
/* 43 */         this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
/* 44 */         AbstractDungeon.effectsQueue.add(new EnemyStanceAuraEffect("Divinity"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEndOfTurn() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 56 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage * 3.0F) : damage;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 60 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */   
/*    */   public void onEnterStance() {
/* 64 */     if (sfxId != -1L) {
/* 65 */       stopIdleSfx();
/*    */     }
/*    */     
/* 68 */     CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
/* 69 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_DIVINITY");
/* 70 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.PINK, true));
/* 71 */     AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractCharBoss.boss.hb.cX, AbstractCharBoss.boss.hb.cY, "Divinity"));
/* 72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(3));
/*    */   }
/*    */   
/*    */   public void onExitStance() {
/* 76 */     stopIdleSfx();
/*    */   }
/*    */   
/*    */   public void stopIdleSfx() {
/* 80 */     if (sfxId != -1L) {
/* 81 */       CardCrawlGame.sound.stop("STANCE_LOOP_DIVINITY", sfxId);
/* 82 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 88 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Divinity");
/* 89 */   private static long sfxId = -1L;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\EnDivinityStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */