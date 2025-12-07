/*     */ package charbosses.powers.bossmechanicpowers;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WatcherCripplePower
/*     */   extends AbstractBossMechanicPower
/*     */ {
/*     */   private boolean firstused = false;
/*     */   private boolean secondused = false;
/*     */   private boolean thirdused = false;
/*     */   
/*     */   public WatcherCripplePower(AbstractCreature owner, int newAmount) {
/*  31 */     this.name = NAME;
/*  32 */     this.ID = "downfall:WatcherCripplePower";
/*  33 */     this.owner = owner;
/*  34 */     this.amount = newAmount;
/*  35 */     updateDescription();
/*  36 */     loadRegion("curiosity");
/*     */   }
/*     */   
/*     */   public void updateDescription() {
/*  40 */     if (this.amount > 0) {
/*  41 */       this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
/*     */     } else {
/*  43 */       this.description = DESCRIPTIONS[2];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int onLoseHp(int damageAmount) {
/*  49 */     flash();
/*  50 */     stackPower(damageAmount * -1);
/*  51 */     updateDescription();
/*  52 */     return super.onLoseHp(damageAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSpecificTrigger() {
/*  57 */     if (this.amount <= 50 && !this.firstused) {
/*  58 */       this.firstused = true;
/*  59 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
/*  60 */       addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05F));
/*  61 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, -1), -1, true, AbstractGameAction.AttackEffect.NONE));
/*     */     } 
/*  63 */     if (this.amount <= 25 && !this.secondused) {
/*  64 */       this.secondused = true;
/*  65 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
/*  66 */       addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05F));
/*  67 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, -1), -1, true, AbstractGameAction.AttackEffect.NONE));
/*     */     } 
/*  69 */     if (this.amount <= 0 && !this.thirdused) {
/*  70 */       this.thirdused = true;
/*  71 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
/*  72 */       addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05F));
/*  73 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, -1), -1, true, AbstractGameAction.AttackEffect.NONE));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void stackPower(int stackAmount) {
/*  79 */     super.stackPower(stackAmount);
/*  80 */     if (this.amount <= 50 && 
/*  81 */       this.owner != null) {
/*  82 */       onSpecificTrigger();
/*     */     }
/*     */     
/*  85 */     if (this.amount <= 25 && 
/*  86 */       this.owner != null) {
/*  87 */       onSpecificTrigger();
/*     */     }
/*     */     
/*  90 */     if (this.amount <= 0 && 
/*  91 */       this.owner != null) {
/*  92 */       onSpecificTrigger();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void atEndOfRound() {
/*  99 */     super.atEndOfRound();
/* 100 */     this.amount = 75;
/* 101 */     this.thirdused = false;
/* 102 */     this.secondused = false;
/* 103 */     this.firstused = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/* 108 */     if (damage > 1.0F && 
/* 109 */       this.owner instanceof AbstractCharBoss && 
/* 110 */       ((AbstractCharBoss)this.owner).stance instanceof charbosses.stances.EnRealWrathStance) {
/* 111 */       return damage * 2.0F;
/*     */     }
/*     */ 
/*     */     
/* 115 */     return damage;
/*     */   }
/*     */   
/* 118 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:WatcherCripplePower");
/* 119 */   public static final String NAME = powerStrings.NAME;
/* 120 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*     */   public static final String POWER_ID = "downfall:WatcherCripplePower";
/*     */   public static final int LOSE_1_STRENGTH_PER_X_HP = 25;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\WatcherCripplePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */