/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.vfx.EnemyDivinityParticleEffect;
/*    */ import charbosses.vfx.EnemyStanceAuraEffect;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class NeowDivinityPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "downfall:NeowDivinity";
/*    */   private static final PowerStrings powerStrings;
/* 21 */   private static long sfxId = -1L;
/*    */   private boolean justapplied;
/*    */   protected float angle;
/* 24 */   protected float particleTimer = 0.0F, particleTimer2 = 0.0F;
/*    */   
/*    */   public NeowDivinityPower(AbstractCreature owner) {
/* 27 */     this.name = powerStrings.NAME;
/* 28 */     this.ID = "downfall:NeowDivinity";
/* 29 */     this.owner = owner;
/* 30 */     updateDescription();
/* 31 */     loadRegion("mantra");
/* 32 */     this.type = AbstractPower.PowerType.BUFF;
/* 33 */     this.justapplied = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onRemove() {
/* 41 */     super.onRemove();
/* 42 */     CardCrawlGame.sound.stop("STANCE_LOOP_DIVINITY", sfxId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 47 */     super.atEndOfRound();
/* 48 */     if (!this.justapplied) {
/* 49 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */     } else {
/* 51 */       this.justapplied = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 56 */     CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
/* 57 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_DIVINITY");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 61 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 66 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 67 */       return damage * 3.0F;
/*    */     }
/* 69 */     return damage;
/*    */   }
/*    */   
/*    */   static {
/* 73 */     powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:NeowDivinity");
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(int slot) {
/* 78 */     super.update(slot);
/* 79 */     if (!Settings.DISABLE_EFFECTS) {
/* 80 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 81 */       if (this.particleTimer < 0.0F) {
/* 82 */         this.particleTimer = 0.2F;
/* 83 */         AbstractDungeon.effectsQueue.add(new EnemyDivinityParticleEffect());
/*    */       } 
/*    */     } 
/* 86 */     this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 87 */     if (this.particleTimer2 < 0.0F) {
/* 88 */       this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
/* 89 */       AbstractDungeon.effectsQueue.add(new EnemyStanceAuraEffect("Divinity"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\NeowDivinityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */