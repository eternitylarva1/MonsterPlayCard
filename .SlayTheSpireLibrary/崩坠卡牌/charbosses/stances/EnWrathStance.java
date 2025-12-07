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
/*    */ public class EnWrathStance
/*    */   extends AbstractEnemyStance
/*    */ {
/*    */   public static final String STANCE_ID = "Wrath";
/*    */   
/*    */   public EnWrathStance() {
/* 23 */     this.ID = "Wrath";
/* 24 */     this.name = stanceString.NAME;
/* 25 */     updateDescription();
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 29 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage * 1.5F) : damage;
/*    */   }
/*    */   
/*    */   public void updateAnimation() {
/* 33 */     if (AbstractCharBoss.boss != null) {
/* 34 */       if (!Settings.DISABLE_EFFECTS) {
/* 35 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 36 */         if (this.particleTimer < 0.0F) {
/* 37 */           this.particleTimer = 0.05F;
/* 38 */           AbstractDungeon.effectsQueue.add(new EnemyWrathParticleEffect());
/*    */         } 
/*    */       } 
/*    */       
/* 42 */       this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 43 */       if (this.particleTimer2 < 0.0F) {
/* 44 */         this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
/* 45 */         AbstractDungeon.effectsQueue.add(new EnemyStanceAuraEffect("Wrath"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 52 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */   
/*    */   public void onEnterStance() {
/* 56 */     if (sfxId != -1L) {
/* 57 */       stopIdleSfx();
/*    */     }
/*    */     
/* 60 */     CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
/* 61 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
/* 62 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
/* 63 */     AbstractDungeon.effectsQueue.add(new EnemyStanceChangeParticleGenerator(AbstractCharBoss.boss.hb.cX, AbstractCharBoss.boss.hb.cY, "Wrath"));
/*    */   }
/*    */   
/*    */   public void onExitStance() {
/* 67 */     stopIdleSfx();
/*    */   }
/*    */   
/*    */   public void stopIdleSfx() {
/* 71 */     if (sfxId != -1L) {
/* 72 */       CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
/* 73 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 79 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Wrath");
/* 80 */   private static long sfxId = -1L;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\EnWrathStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */