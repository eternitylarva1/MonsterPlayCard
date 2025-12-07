/*     */ package downfall.actions;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ import downfall.vfx.BanditIOUEffect;
/*     */ import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BanditIOUAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   public boolean shouldDamage = false;
/*     */   public boolean shouldPlayEffect = false;
/*     */   public boolean shouldPlayTextEffect = false;
/*     */   public boolean shouldFinish = false;
/*     */   public boolean screenshake = false;
/*     */   private BanditIOUEffect effect;
/*     */   private boolean initialized = false;
/*     */   public AbstractPlayer p;
/*     */   public AbstractMonster m;
/*     */   public int currentDamage;
/*     */   public AbstractGameEffect currentEffect;
/*     */   public AbstractGameEffect textEffect;
/*     */   public AbstractPower currentPower;
/*     */   
/*     */   public BanditIOUAction(AbstractPlayer p, AbstractMonster m) {
/*  43 */     this.p = p;
/*  44 */     this.m = m;
/*     */ 
/*     */     
/*  47 */     this.duration = 13.0F;
/*     */   }
/*     */   
/*     */   public void dealDamage(int damage) {
/*  51 */     if (this.m.hasPower("Buffer")) {
/*  52 */       this.m.getPower("Buffer").flash();
/*  53 */       (this.m.getPower("Buffer")).amount--;
/*  54 */       if ((this.m.getPower("Buffer")).amount <= 0) {
/*  55 */         this.m.powers.remove(this.m.getPower("Buffer"));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  60 */     if (!this.m.isDeadOrEscaped()) {
/*     */       
/*  62 */       DamageInfo info = new DamageInfo((AbstractCreature)this.p, damage, DamageInfo.DamageType.THORNS);
/*     */       
/*  64 */       this.m.tint.color.set(Color.GRAY.cpy());
/*  65 */       this.m.tint.changeColor(Color.WHITE.cpy());
/*  66 */       this.m.damage(info);
/*     */     } 
/*     */     
/*  69 */     if (this.currentPower != null) {
/*  70 */       applyPower();
/*  71 */       this.currentPower = null;
/*     */     } 
/*     */     
/*  74 */     if (this.screenshake = true) {
/*  75 */       this.screenshake = false;
/*  76 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playEffect(AbstractGameEffect effect) {
/*  82 */     AbstractDungeon.effectsQueue.add(effect);
/*  83 */     if (this.shouldPlayTextEffect && this.textEffect != null) {
/*  84 */       AbstractDungeon.effectsQueue.add(this.textEffect);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPower() {
/*  90 */     if (!this.m.isDeadOrEscaped()) {
/*  91 */       if (this.m.hasPower("Artifact")) {
/*  92 */         CardCrawlGame.sound.play("NULLIFY_SFX");
/*  93 */         this.m.getPower("Artifact").flashWithoutSound();
/*  94 */         this.m.getPower("Artifact").onSpecificTrigger();
/*     */         return;
/*     */       } 
/*  97 */       if (AbstractDungeon.player.hasRelic("Ginger") && this.m.isPlayer && this.currentPower.ID.equals("Weakened")) {
/*  98 */         AbstractDungeon.player.getRelic("Ginger").flash();
/*     */         return;
/*     */       } 
/* 101 */       this.m.useFastShakeAnimation(0.5F);
/*     */ 
/*     */       
/* 104 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.m.hb.cX, this.m.hb.cY, this.attackEffect));
/*     */       
/* 106 */       if (this.m.hasPower(this.currentPower.ID)) {
/* 107 */         this.m.getPower(this.currentPower.ID).stackPower(this.currentPower.amount);
/*     */       } else {
/*     */         
/* 110 */         this.m.powers.add(this.currentPower);
/* 111 */         this.currentPower.onInitialApplication();
/*     */       } 
/* 113 */       Collections.sort(this.m.powers);
/* 114 */       this.currentPower.flash();
/* 115 */       AbstractDungeon.onModifyPower();
/* 116 */       this.currentPower.updateDescription();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 122 */     if (!this.initialized) {
/* 123 */       this.initialized = true;
/*     */       
/* 125 */       this.effect = new BanditIOUEffect();
/* 126 */       this.effect.action = this;
/* 127 */       this.effect.pointyStartX = this.m.drawX - this.m.hb.width / 2.0F - 150.0F * Settings.scale;
/* 128 */       this.effect.pointyStartY = this.m.drawY;
/*     */       
/* 130 */       this.effect.bearStartX = this.m.drawX + 250.0F * Settings.scale;
/* 131 */       this.effect.bearLandX = this.m.drawX + 50.0F * Settings.scale;
/* 132 */       this.effect.bearLandY = this.m.drawY;
/*     */       
/* 134 */       AbstractDungeon.effectsQueue.add(this.effect);
/*     */     } else {
/*     */       
/* 137 */       if (this.shouldDamage) {
/* 138 */         dealDamage(this.currentDamage);
/* 139 */         this.shouldDamage = false;
/*     */       } 
/*     */       
/* 142 */       if (this.shouldPlayEffect) {
/* 143 */         if (this.currentEffect != null) playEffect(this.currentEffect); 
/* 144 */         this.shouldPlayEffect = false;
/*     */       } 
/*     */       
/* 147 */       if (this.shouldFinish) {
/* 148 */         this.effect.doneBlasting = true;
/* 149 */         this.isDone = true;
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\BanditIOUAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */