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
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ import downfall.cards.curses.Aged;
/*     */ import downfall.cards.curses.Bewildered;
/*     */ import downfall.cards.curses.Scatterbrained;
/*     */ import downfall.powers.NeowInvulnerablePower;
/*     */ import downfall.vfx.combat.FakeDeathScene;
/*     */ import guardian.vfx.SmallLaserEffectColored;
/*     */ 
/*     */ public class Neow4Life30Heal extends AbstractMonster {
/*  38 */   public static final String ID = downfallMod.makeID("Neow4Life30Heal");
/*     */   
/*     */   public static final String NAME = "DOWNFALL_Neow4Life30Heal";
/*     */   
/*     */   private static final float HB_X = -40.0F;
/*     */   private static final float HB_Y = -40.0F;
/*     */   private static final float HB_W = 700.0F;
/*     */   private static final float HB_H = 500.0F;
/*  46 */   private static final float EYE1_X = -130.0F * Settings.scale;
/*  47 */   private static final float EYE1_Y = -50.0F * Settings.scale;
/*     */   
/*  49 */   private static final float EYE2_X = -20.0F * Settings.scale;
/*  50 */   private static final float EYE2_Y = -50.0F * Settings.scale;
/*     */   
/*  52 */   private static final float EYE3_X = 80.0F * Settings.scale;
/*  53 */   private static final float EYE3_Y = -50.0F * Settings.scale;
/*     */   
/*  55 */   private static final float INTENT_X = -210.0F * Settings.scale;
/*  56 */   private static final float INTENT_Y = 280.0F * Settings.scale;
/*     */   
/*  58 */   private static final float DRAWX_OFFSET = 100.0F * Settings.scale;
/*  59 */   private static final float DRAWY_OFFSET = 30.0F * Settings.scale;
/*     */   
/*     */   private float baseDrawX;
/*     */   
/*  63 */   private int turnNum = 0;
/*     */   
/*     */   private int strAmt;
/*     */   
/*     */   private int blockAmt;
/*     */   public static Neow4Life30Heal neowboss;
/*  69 */   private int buffCount = 0;
/*     */   
/*     */   public Neow4Life30Heal() {
/*  72 */     super("DOWNFALL_Neow4Life30Heal", ID, 500, -40.0F, -40.0F, 700.0F, 500.0F, "images/npcs/neow/skeleton.png");
/*     */     
/*  74 */     loadAnimation("images/npcs/neow/skeleton.atlas", "images/npcs/neow/skeleton.json", 1.0F);
/*     */     
/*  76 */     this.drawX += DRAWX_OFFSET;
/*  77 */     this.drawY += DRAWY_OFFSET;
/*     */     
/*  79 */     this.type = AbstractMonster.EnemyType.BOSS;
/*  80 */     this.baseDrawX = this.drawX;
/*     */ 
/*     */     
/*  83 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  84 */       setHp(600);
/*     */     } else {
/*  86 */       setHp(550);
/*     */     } 
/*     */     
/*  89 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  90 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  92 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  93 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/*  94 */       this.damage.add(new DamageInfo((AbstractCreature)this, 40));
/*     */     } else {
/*  96 */       this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/*  97 */       this.damage.add(new DamageInfo((AbstractCreature)this, 25));
/*     */     } 
/*     */     
/* 100 */     this.strAmt = 2;
/* 101 */     this.blockAmt = 20;
/*     */     
/* 103 */     this.intentOffsetX = INTENT_X;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (AbstractDungeon.isPlayerInDungeon()) AbstractDungeon.scene = (AbstractScene)new FakeDeathScene(); 
/*     */   }
/*     */   
/*     */   public void curses() {
/* 113 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
/* 114 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 115 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 116 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */     
/* 118 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Aged(), 1, true, false, false, Settings.WIDTH * 0.18F, Settings.HEIGHT / 2.0F));
/* 119 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Bewildered(), 1, true, false, false, Settings.WIDTH * 0.34F, Settings.HEIGHT / 2.0F));
/* 120 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Scatterbrained(), 1, true, false, false, Settings.WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/* 121 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Icky(), 1, true, false, false, Settings.WIDTH * 0.66F, Settings.HEIGHT / 2.0F));
/* 122 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Malfunctioning(), 1, true, false, false, Settings.WIDTH * 0.82F, Settings.HEIGHT / 2.0F));
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
/*     */   public void usePreBattleAction() {
/* 136 */     NeowBoss.neowboss = null;
/* 137 */     neowboss = this;
/* 138 */     super.usePreBattleAction();
/*     */     
/* 140 */     AbstractCharBoss.boss = null;
/*     */     
/* 142 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_ENDING");
/*     */     
/* 144 */     int beatAmount = 3;
/* 145 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 146 */       beatAmount++;
/*     */     }
/*     */     
/* 149 */     int invincibleAmt = 300;
/* 150 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 151 */       invincibleAmt -= 50;
/*     */     }
/*     */     
/* 154 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, beatAmount)));
/*     */     int i;
/* 156 */     for (i = 0; i < 3; i++) {
/* 157 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/* 159 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new InvinciblePower((AbstractCreature)this, invincibleAmt), invincibleAmt));
/*     */     
/* 161 */     for (i = 0; i < 3; i++) {
/* 162 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 169 */     switch (this.nextMove) {
/*     */       case 0:
/* 171 */         playSfx();
/* 172 */         curses();
/*     */         break;
/*     */       case 1:
/* 175 */         playSfx();
/* 176 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.6F));
/* 177 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE1_X, this.hb.cY + EYE1_Y, Color.GOLD), 0.25F));
/*     */         
/* 179 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 182 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.7F));
/* 183 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE2_X, this.hb.cY + EYE2_Y, Color.GOLD), 0.25F));
/*     */         
/* 185 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 187 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 188 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.8F));
/* 189 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE3_X, this.hb.cY + EYE3_Y, Color.GOLD), 0.25F));
/*     */         
/* 191 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 193 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*     */         break;
/*     */       case 2:
/* 196 */         playSfx();
/* 197 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.YELLOW, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
/* 198 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.GOLD, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.5F));
/*     */ 
/*     */         
/* 201 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VampireDamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SMASH));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 208 */         playSfx();
/* 209 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 210 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 211 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/*     */ 
/*     */ 
/*     */         
/* 215 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, 2)));
/* 216 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 6), 6));
/*     */         
/* 218 */         switch (this.buffCount) {
/*     */           case 0:
/* 220 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3), 3));
/*     */             break;
/*     */           case 1:
/* 223 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, 75));
/*     */             break;
/*     */           
/*     */           case 2:
/* 227 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 50)));
/*     */             break;
/*     */           case 3:
/* 230 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 10), 10));
/*     */             break;
/*     */           default:
/* 233 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 30), 30));
/*     */             break;
/*     */         } 
/* 236 */         this.buffCount++;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 250 */     int roll = MathUtils.random(3);
/*     */     
/* 252 */     if (roll == 0) {
/* 253 */       CardCrawlGame.sound.play("VO_NEOW_1A");
/* 254 */     } else if (roll == 1) {
/* 255 */       CardCrawlGame.sound.play("VO_NEOW_1B");
/* 256 */     } else if (roll == 2) {
/* 257 */       CardCrawlGame.sound.play("VO_NEOW_2A");
/*     */     } else {
/* 259 */       CardCrawlGame.sound.play("VO_NEOW_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 268 */     switch (this.turnNum) {
/*     */       case 0:
/* 270 */         setMove((byte)0, AbstractMonster.Intent.STRONG_DEBUFF);
/* 271 */         this.turnNum++;
/*     */         break;
/*     */       case 1:
/* 274 */         setMove((byte)2, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/* 275 */         this.turnNum++;
/*     */         break;
/*     */       case 2:
/* 278 */         setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/* 279 */         this.turnNum++;
/*     */         break;
/*     */       case 3:
/* 282 */         setMove((byte)3, AbstractMonster.Intent.BUFF);
/* 283 */         this.turnNum = 1;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 291 */     super.die();
/* 292 */     useFastShakeAnimation(5.0F);
/* 293 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 294 */     onBossVictoryLogic();
/* 295 */     onFinalBossVictoryLogic();
/* 296 */     CardCrawlGame.stopClock = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 302 */     super.dispose();
/* 303 */     neowboss = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean foggy() {
/* 308 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTip(SpriteBatch sb) {
/* 313 */     if (!foggy()) {
/* 314 */       super.renderTip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderName(SpriteBatch sb) {
/* 321 */     if (!foggy()) {
/* 322 */       SpireSuper.call(new Object[] { sb });
/*     */     }
/*     */   }
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderPowerIcons(SpriteBatch sb, float x, float y) {
/* 328 */     if (!foggy() && !this.halfDead)
/* 329 */       SpireSuper.call(new Object[] { sb, Float.valueOf(x), Float.valueOf(y) }); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\Neow4Life30Heal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */