/*     */ package downfall.monsters;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.IntangiblePower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
/*     */ import downfall.vfx.PotionThrowEffect;
/*     */ 
/*     */ public class LadyInBlue extends AbstractMonster {
/*  21 */   public static final String ID = downfallMod.makeID("LadyInBlue");
/*  22 */   public static final String NAME = (CardCrawlGame.languagePack.getEventString("The Woman in Blue")).NAME;
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 150.0F;
/*     */   private static final float HB_H = 320.0F;
/*  28 */   int turnNum = 0;
/*     */   
/*     */   boolean usedDebuffs = false;
/*     */   boolean usedEmergency = false;
/*     */   
/*     */   public LadyInBlue() {
/*  34 */     super(NAME, ID, 120, 0.0F, 0.0F, 150.0F, 320.0F, "downfallResources/images/monsters/womaninblue/womaninblue2.png");
/*     */     
/*  36 */     loadAnimation("downfallResources/images/monsters/womaninblue/WomanInBlue.atlas", "downfallResources/images/monsters/womaninblue/WomanInBlue.json", 1.0F);
/*     */     
/*  38 */     switch (AbstractDungeon.actNum) {
/*     */       case 1:
/*  40 */         setHp(150);
/*     */         break;
/*     */       case 2:
/*  43 */         setHp(200);
/*     */         break;
/*     */       case 3:
/*  46 */         setHp(250);
/*     */         break;
/*     */     } 
/*     */     
/*  50 */     this.damage.add(new DamageInfo((AbstractCreature)this, 20));
/*  51 */     this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */ 
/*     */ 
/*     */     
/*  55 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  56 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  57 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  58 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/*  59 */     this.state.setTimeScale(0.8F);
/*  60 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  66 */     if ("ATTACK".equals(stateName)) {
/*  67 */       this.state.setAnimation(0, "Attack", false);
/*  68 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  74 */     switch (this.nextMove) {
/*     */       case 1:
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  77 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/FirePotion.png", this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 2.0F, 0.6F, false, false), 0.6F));
/*  78 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         
/*  80 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/CultistPotion.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/*  81 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RitualPower((AbstractCreature)this, 1, false), 1));
/*     */         break;
/*     */       case 2:
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  85 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/ExplosivePotion.png", this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 2.0F, 0.6F, false, false), 0.6F));
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new ExplosionSmallEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.1F));
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.NONE));
/*     */         
/*  89 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/BlockPotion.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 12));
/*     */         break;
/*     */       case 3:
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  94 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/EssenceOfSteel.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new PlatedArmorPower((AbstractCreature)this, 4), 4));
/*     */         
/*  97 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/AncientPotion.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1), 1));
/*     */         break;
/*     */       case 4:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 102 */         this.usedDebuffs = true;
/* 103 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/WeakPotion.png", this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 2.0F, 0.6F, false, false), 0.6F));
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 105 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/FearPotion.png", this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 2.0F, 0.6F, false, false), 0.6F));
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */         break;
/*     */       case 5:
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 110 */         this.usedEmergency = true;
/* 111 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/GhostInAJar.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/* 112 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new IntangiblePower((AbstractCreature)this, 1), 1));
/* 113 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/FairyinaBottle.png", this.hb.cX, this.hb.cY, this.hb.cX, this.hb.cY, 2.0F, 0.6F, false, true), 0.6F));
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new FairyPotionPower((AbstractCreature)this, 1), 1));
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 123 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 124 */       this.state.setAnimation(0, "Hit", false);
/* 125 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/* 127 */     if (info.output > 0 && hasPower("Intangible")) {
/* 128 */       info.output = 1;
/*     */     }
/* 130 */     super.damage(info);
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 134 */     if (this.currentHealth <= 0.5F * this.maxHealth && !this.usedEmergency) {
/* 135 */       setMove((byte)5, AbstractMonster.Intent.BUFF);
/*     */     } else {
/* 137 */       switch (this.turnNum) {
/*     */         case 0:
/* 139 */           setMove((byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */           break;
/*     */         case 1:
/* 142 */           setMove((byte)3, AbstractMonster.Intent.BUFF);
/*     */           break;
/*     */         case 2:
/* 145 */           setMove((byte)1, AbstractMonster.Intent.ATTACK_BUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */           break;
/*     */         case 3:
/* 148 */           if (!this.usedDebuffs) {
/* 149 */             setMove((byte)4, AbstractMonster.Intent.STRONG_DEBUFF); break;
/*     */           } 
/* 151 */           this.turnNum--;
/* 152 */           getMove(num);
/*     */           break;
/*     */       } 
/*     */       
/* 156 */       this.turnNum++;
/* 157 */       if (this.turnNum == (this.usedDebuffs ? 3 : 4))
/* 158 */         this.turnNum = 0; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\LadyInBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */