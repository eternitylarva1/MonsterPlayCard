/*     */ package downfall.monsters;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.RegenerateMonsterPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ import downfall.cards.curses.Scatterbrained;
/*     */ import downfall.powers.NeowInvulnerablePower;
/*     */ import downfall.powers.neowpowers.NeowInvinciblePower;
/*     */ import downfall.vfx.combat.FakeDeathScene;
/*     */ import guardian.vfx.SmallLaserEffectColored;
/*     */ 
/*     */ public class NeowBossFinal extends AbstractMonster {
/*  39 */   public static final String ID = downfallMod.makeID("NeowBossFinal");
/*  40 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString(ID)).NAMES[0];
/*     */   
/*     */   private static final float HB_X = -40.0F;
/*     */   
/*     */   private static final float HB_Y = -40.0F;
/*     */   private static final float HB_W = 700.0F;
/*     */   private static final float HB_H = 500.0F;
/*  47 */   private static final float EYE1_X = -130.0F * Settings.scale;
/*  48 */   private static final float EYE1_Y = -50.0F * Settings.scale;
/*     */   
/*  50 */   private static final float EYE2_X = -20.0F * Settings.scale;
/*  51 */   private static final float EYE2_Y = -50.0F * Settings.scale;
/*     */   
/*  53 */   private static final float EYE3_X = 80.0F * Settings.scale;
/*  54 */   private static final float EYE3_Y = -50.0F * Settings.scale;
/*     */   
/*  56 */   private static final float INTENT_X = -210.0F * Settings.scale;
/*  57 */   private static final float INTENT_Y = 280.0F * Settings.scale;
/*     */   
/*  59 */   private static final float DRAWX_OFFSET = 100.0F * Settings.scale;
/*  60 */   private static final float DRAWY_OFFSET = 30.0F * Settings.scale;
/*     */   
/*     */   private float baseDrawX;
/*     */   
/*  64 */   private int turnNum = 0;
/*     */   
/*     */   private int strAmt;
/*     */   
/*     */   private int blockAmt;
/*     */   public static NeowBossFinal neowboss;
/*  70 */   private int buffCount = 0;
/*     */   
/*     */   public NeowBossFinal() {
/*  73 */     super(NAME, ID, 600, -40.0F, -40.0F, 700.0F, 500.0F, "images/npcs/neow/skeleton.png");
/*     */     
/*  75 */     loadAnimation("images/npcs/neow/skeleton.atlas", "images/npcs/neow/skeleton.json", 1.0F);
/*     */     
/*  77 */     this.drawX += DRAWX_OFFSET;
/*  78 */     this.drawY += DRAWY_OFFSET;
/*     */     
/*  80 */     this.type = AbstractMonster.EnemyType.BOSS;
/*  81 */     this.baseDrawX = this.drawX;
/*     */     
/*  83 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  84 */       setHp(650);
/*     */     } else {
/*  86 */       setHp(600);
/*     */     } 
/*     */     
/*  89 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  90 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (AbstractDungeon.ascensionLevel >= 4) {
/* 103 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/* 104 */       this.damage.add(new DamageInfo((AbstractCreature)this, 44));
/*     */     } else {
/* 106 */       this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/* 107 */       this.damage.add(new DamageInfo((AbstractCreature)this, 33));
/*     */     } 
/*     */     
/* 110 */     this.strAmt = 2;
/* 111 */     this.blockAmt = 20;
/*     */     
/* 113 */     this.intentOffsetX = INTENT_X;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (AbstractDungeon.isPlayerInDungeon()) AbstractDungeon.scene = (AbstractScene)new FakeDeathScene(); 
/*     */   }
/*     */   
/*     */   public void curses() {
/* 123 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
/* 124 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 125 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 126 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */ 
/*     */     
/* 129 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Haunted(), 1, true, false, false, Settings.WIDTH * 0.18F, Settings.HEIGHT / 2.0F));
/* 130 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Scatterbrained(), 1, true, false, false, Settings.WIDTH * 0.34F, Settings.HEIGHT / 2.0F));
/* 131 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new ImpendingDoom(), 1, true, false, false, Settings.WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/* 132 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Icky(), 1, true, false, false, Settings.WIDTH * 0.66F, Settings.HEIGHT / 2.0F));
/* 133 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Bewildered(), 1, true, false, false, Settings.WIDTH * 0.82F, Settings.HEIGHT / 2.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 148 */     NeowBoss.neowboss = null;
/* 149 */     neowboss = this;
/* 150 */     super.usePreBattleAction();
/*     */     
/* 152 */     AbstractCharBoss.boss = null;
/*     */     
/* 154 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_ENDING");
/*     */     
/* 156 */     int beatAmount = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     int invincibleAmt = 250;
/* 162 */     if (AbstractDungeon.ascensionLevel >= 19);
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 167 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, beatAmount)));
/*     */     }
/*     */     
/* 170 */     if (AbstractDungeon.ascensionLevel < 19) {
/* 171 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, beatAmount - 1)));
/*     */     }
/*     */     int i;
/* 174 */     for (i = 0; i < 3; i++) {
/* 175 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/* 177 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvinciblePower((AbstractCreature)this, invincibleAmt), invincibleAmt));
/*     */     
/* 179 */     if (AbstractDungeon.ascensionLevel < 19) {
/* 180 */       for (i = 0; i < 3; i++) {
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */       }
/* 183 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, null, (AbstractPower)new HeartsFavorPower((AbstractCreature)this, 2), 2));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*     */     int buf;
/* 199 */     switch (this.nextMove) {
/*     */       case 0:
/* 201 */         playSfx();
/* 202 */         curses();
/*     */         break;
/*     */       case 1:
/* 205 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.6F));
/* 206 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE1_X, this.hb.cY + EYE1_Y, Color.GOLD), 0.25F));
/*     */         
/* 208 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 210 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 211 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.7F));
/* 212 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE2_X, this.hb.cY + EYE2_Y, Color.GOLD), 0.25F));
/*     */         
/* 214 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 216 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 217 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.8F));
/* 218 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE3_X, this.hb.cY + EYE3_Y, Color.GOLD), 0.25F));
/*     */         
/* 220 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 222 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*     */         break;
/*     */       case 2:
/* 225 */         playSfx();
/* 226 */         playSfx();
/* 227 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.YELLOW, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
/* 228 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.GOLD, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.5F));
/*     */ 
/*     */         
/* 231 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VampireDamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SMASH));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 238 */         playSfx();
/* 239 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 240 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 241 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 247 */         buf = 0;
/* 248 */         if (hasPower(NeowInvulnerablePower.POWER_ID)) {
/* 249 */           buf = (getPower(NeowInvulnerablePower.POWER_ID)).amount;
/*     */         }
/* 251 */         if (buf < 6) {
/* 252 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, 2)));
/*     */         }
/*     */ 
/*     */         
/* 256 */         if (!hasPower(NeowInvulnerablePower.POWER_ID)) {
/* 257 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, 2)));
/*     */         }
/*     */         
/* 260 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 261 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 9), 9));
/*     */         }
/*     */         
/* 264 */         if (AbstractDungeon.ascensionLevel < 19) {
/* 265 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 7), 7));
/*     */         }
/*     */ 
/*     */         
/* 269 */         switch (this.buffCount) {
/*     */           case 0:
/* 271 */             if (AbstractDungeon.ascensionLevel >= 19) {
/* 272 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3), 3));
/*     */             }
/*     */ 
/*     */             
/* 276 */             if (AbstractDungeon.ascensionLevel < 19) {
/* 277 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2), 3));
/*     */             }
/*     */             break;
/*     */           
/*     */           case 1:
/* 282 */             if (AbstractDungeon.ascensionLevel >= 19) {
/* 283 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, 100));
/*     */             }
/*     */             
/* 286 */             if (AbstractDungeon.ascensionLevel < 19) {
/* 287 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, 75));
/*     */             }
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 294 */             if (AbstractDungeon.ascensionLevel >= 19) {
/* 295 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 50)));
/*     */             }
/*     */ 
/*     */             
/* 299 */             if (AbstractDungeon.ascensionLevel < 19) {
/* 300 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 30)));
/*     */             }
/*     */             break;
/*     */           
/*     */           case 3:
/* 305 */             if (AbstractDungeon.ascensionLevel >= 19) {
/* 306 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 15), 10));
/*     */             }
/*     */             
/* 309 */             if (AbstractDungeon.ascensionLevel < 19) {
/* 310 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 12), 8));
/*     */             }
/*     */             break;
/*     */           
/*     */           default:
/* 315 */             if (AbstractDungeon.ascensionLevel >= 19) {
/* 316 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 30), 30));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 321 */             if (AbstractDungeon.ascensionLevel < 19) {
/* 322 */               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 20), 20));
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 328 */         this.buffCount++;
/*     */         
/* 330 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 20));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 342 */     int roll = MathUtils.random(3);
/*     */     
/* 344 */     if (roll == 0) {
/* 345 */       CardCrawlGame.sound.play("VO_NEOW_1A");
/* 346 */     } else if (roll == 1) {
/* 347 */       CardCrawlGame.sound.play("VO_NEOW_1B");
/* 348 */     } else if (roll == 2) {
/* 349 */       CardCrawlGame.sound.play("VO_NEOW_2A");
/*     */     } else {
/* 351 */       CardCrawlGame.sound.play("VO_NEOW_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 360 */     switch (this.turnNum) {
/*     */       case 0:
/* 362 */         setMove((byte)0, AbstractMonster.Intent.STRONG_DEBUFF);
/* 363 */         this.turnNum++;
/*     */         break;
/*     */       case 1:
/* 366 */         setMove((byte)2, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/* 367 */         this.turnNum++;
/*     */         break;
/*     */       case 2:
/* 370 */         setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/* 371 */         this.turnNum++;
/*     */         break;
/*     */       case 3:
/* 374 */         setMove((byte)3, AbstractMonster.Intent.BUFF);
/* 375 */         this.turnNum = 1;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 383 */     super.die();
/* 384 */     useFastShakeAnimation(5.0F);
/* 385 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 386 */     onBossVictoryLogic();
/* 387 */     onFinalBossVictoryLogic();
/* 388 */     CardCrawlGame.stopClock = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 394 */     super.dispose();
/* 395 */     neowboss = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean foggy() {
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTip(SpriteBatch sb) {
/* 405 */     if (!foggy()) {
/* 406 */       super.renderTip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderName(SpriteBatch sb) {
/* 413 */     if (!foggy()) {
/* 414 */       SpireSuper.call(new Object[] { sb });
/*     */     }
/*     */   }
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderPowerIcons(SpriteBatch sb, float x, float y) {
/* 420 */     if (!foggy() && !this.halfDead)
/* 421 */       SpireSuper.call(new Object[] { sb, Float.valueOf(x), Float.valueOf(y) }); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\NeowBossFinal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */