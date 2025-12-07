/*    */ package charbosses.stances;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.vfx.EnemyStanceAuraEffect;
/*    */ import charbosses.vfx.EnemyStanceChangeParticleGenerator;
/*    */ import charbosses.vfx.EnemyWrathParticleEffect;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ 
/*    */ public class EnRealWrathStance
/*    */   extends AbstractEnemyStance
/*    */ {
/*    */   public static final String STANCE_ID = "Real Wrath";
/*    */   
/*    */   public EnRealWrathStance() {
/* 23 */     this.ID = "Real Wrath";
/* 24 */     this.name = stanceString.NAME;
/* 25 */     updateDescription();
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 29 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage * 2.0F) : damage;
/*    */   }
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
/* 33 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage * 2.0F) : damage;
/*    */   }
/*    */   
/*    */   public void updateAnimation() {
/* 37 */     if (AbstractCharBoss.boss != null) {
/* 38 */       if (!Settings.DISABLE_EFFECTS) {
/* 39 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 40 */         if (this.particleTimer < 0.0F) {
/* 41 */           this.particleTimer = 0.05F;
/* 42 */           AbstractDungeon.effectsQueue.add(new EnemyWrathParticleEffect());
/*    */         } 
/*    */       } 
/*    */       
/* 46 */       this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 47 */       if (this.particleTimer2 < 0.0F) {
/* 48 */         this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
/* 49 */         AbstractDungeon.effectsQueue.add(new EnemyStanceAuraEffect("Wrath"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 56 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */   
/*    */   public void onEnterStance() {
/* 60 */     if (sfxId != -1L) {
/* 61 */       stopIdleSfx();
/*    */     }
/*    */     
/* 64 */     CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
/* 65 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
/* 66 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
/* 67 */     AbstractDungeon.effectsQueue.add(new EnemyStanceChangeParticleGenerator(AbstractCharBoss.boss.hb.cX, AbstractCharBoss.boss.hb.cY, "Wrath"));
/*    */   }
/*    */   
/*    */   public void onExitStance() {
/* 71 */     stopIdleSfx();
/*    */   }
/*    */   
/*    */   public void stopIdleSfx() {
/* 75 */     if (sfxId != -1L) {
/* 76 */       CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
/* 77 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 83 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Wrath");
/* 84 */   private static long sfxId = -1L;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\EnRealWrathStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */