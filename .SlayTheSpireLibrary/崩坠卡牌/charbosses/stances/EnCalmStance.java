/*    */ package charbosses.stances;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.vfx.EnemyCalmParticleEffect;
/*    */ import charbosses.vfx.EnemyStanceAuraEffect;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ 
/*    */ public class EnCalmStance
/*    */   extends AbstractEnemyStance
/*    */ {
/*    */   public static final String STANCE_ID = "Calm";
/*    */   
/*    */   public EnCalmStance() {
/* 23 */     this.ID = "Calm";
/* 24 */     this.name = stanceString.NAME;
/* 25 */     updateDescription();
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 29 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */   
/*    */   public void updateAnimation() {
/* 33 */     if (AbstractCharBoss.boss != null) {
/* 34 */       if (!Settings.DISABLE_EFFECTS) {
/* 35 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 36 */         if (this.particleTimer < 0.0F) {
/* 37 */           this.particleTimer = 0.04F;
/* 38 */           AbstractDungeon.effectsQueue.add(new EnemyCalmParticleEffect());
/*    */         } 
/*    */       } 
/*    */       
/* 42 */       this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 43 */       if (this.particleTimer2 < 0.0F) {
/* 44 */         this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
/* 45 */         AbstractDungeon.effectsQueue.add(new EnemyStanceAuraEffect("Calm"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnterStance() {
/* 51 */     if (sfxId != -1L) {
/* 52 */       stopIdleSfx();
/*    */     }
/*    */     
/* 55 */     CardCrawlGame.sound.play("STANCE_ENTER_CALM");
/* 56 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_CALM");
/* 57 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
/*    */   }
/*    */   
/*    */   public void onExitStance() {
/* 61 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(2));
/* 62 */     stopIdleSfx();
/*    */   }
/*    */   
/*    */   public void stopIdleSfx() {
/* 66 */     if (sfxId != -1L) {
/* 67 */       CardCrawlGame.sound.stop("STANCE_LOOP_CALM", sfxId);
/* 68 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 74 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Calm");
/* 75 */   private static long sfxId = -1L;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\EnCalmStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */