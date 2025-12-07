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
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.NeowInvulnerablePower;
/*     */ import downfall.powers.neowpowers.TrueNeowPower;
/*     */ import downfall.vfx.combat.FakeDeathScene;
/*     */ import guardian.vfx.SmallLaserEffectColored;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class OldNeow extends AbstractMonster {
/*  36 */   public static final String ID = downfallMod.makeID("OldNeow");
/*  37 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString(ID)).NAMES[0];
/*     */   
/*     */   private static final float HB_X = -40.0F;
/*     */   
/*     */   private static final float HB_Y = -40.0F;
/*     */   private static final float HB_W = 700.0F;
/*     */   private static final float HB_H = 500.0F;
/*  44 */   private static final float EYE1_X = -130.0F * Settings.scale;
/*  45 */   private static final float EYE1_Y = -50.0F * Settings.scale;
/*     */   
/*  47 */   private static final float EYE2_X = -20.0F * Settings.scale;
/*  48 */   private static final float EYE2_Y = -50.0F * Settings.scale;
/*     */   
/*  50 */   private static final float EYE3_X = 80.0F * Settings.scale;
/*  51 */   private static final float EYE3_Y = -50.0F * Settings.scale;
/*     */   
/*  53 */   private static final float INTENT_X = -210.0F * Settings.scale;
/*  54 */   private static final float INTENT_Y = 280.0F * Settings.scale;
/*     */   
/*  56 */   private static final float DRAWX_OFFSET = 100.0F * Settings.scale;
/*  57 */   private static final float DRAWY_OFFSET = 30.0F * Settings.scale;
/*     */   
/*     */   private float baseDrawX;
/*     */   
/*  61 */   private int turnNum = 0;
/*     */   
/*     */   private int strAmt;
/*     */   
/*     */   private int blockAmt;
/*     */   
/*     */   public static OldNeow neowboss;
/*     */   
/*     */   public OldNeow() {
/*  70 */     super(NAME, ID, 750, -40.0F, -40.0F, 700.0F, 500.0F, "images/npcs/neow/skeleton.png");
/*     */     
/*  72 */     loadAnimation("images/npcs/neow/skeleton.atlas", "images/npcs/neow/skeleton.json", 1.0F);
/*     */     
/*  74 */     this.drawX += DRAWX_OFFSET;
/*  75 */     this.drawY += DRAWY_OFFSET;
/*     */     
/*  77 */     this.type = AbstractMonster.EnemyType.BOSS;
/*  78 */     this.baseDrawX = this.drawX;
/*     */ 
/*     */     
/*  81 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  82 */       setHp(800);
/*     */     } else {
/*  84 */       setHp(750);
/*     */     } 
/*     */ 
/*     */     
/*  88 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  89 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  91 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  92 */     this.damage.add(new DamageInfo((AbstractCreature)this, 16));
/*     */     
/*  94 */     this.strAmt = 2;
/*  95 */     this.blockAmt = 20;
/*     */     
/*  97 */     this.intentOffsetX = INTENT_X;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (AbstractDungeon.isPlayerInDungeon()) AbstractDungeon.scene = (AbstractScene)new FakeDeathScene(); 
/*     */   }
/*     */   
/*     */   public void curses() {
/* 109 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
/* 110 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 111 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/* 112 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */     
/* 114 */     ArrayList<AbstractCard> ac = downfallMod.getRandomDownfallCurse(4);
/* 115 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(((AbstractCard)ac.get(0)).makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
/* 116 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(((AbstractCard)ac.get(1)).makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.4F, Settings.HEIGHT / 2.0F));
/* 117 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(((AbstractCard)ac.get(2)).makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.6F, Settings.HEIGHT / 2.0F));
/* 118 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(((AbstractCard)ac.get(3)).makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.8F, Settings.HEIGHT / 2.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 125 */     NeowBoss.neowboss = null;
/* 126 */     neowboss = this;
/* 127 */     super.usePreBattleAction();
/*     */     
/* 129 */     AbstractCharBoss.boss = null;
/*     */     
/* 131 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_ENDING");
/*     */ 
/*     */     
/* 134 */     int beatAmount = 2;
/* 135 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 136 */       beatAmount++;
/*     */     }
/*     */     
/* 139 */     int invincibleAmt = 250;
/* 140 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 141 */       invincibleAmt -= 50;
/*     */     }
/*     */     
/* 144 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)this, beatAmount)));
/*     */     int i;
/* 146 */     for (i = 0; i < 3; i++) {
/* 147 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/* 149 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new InvinciblePower((AbstractCreature)this, invincibleAmt), invincibleAmt));
/*     */     
/* 151 */     for (i = 0; i < 3; i++) {
/* 152 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/* 154 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new NeowGainMinionPowersAction(this, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 161 */     switch (this.nextMove) {
/*     */       case 0:
/* 163 */         playSfx();
/* 164 */         curses();
/*     */         break;
/*     */       case 1:
/* 167 */         playSfx();
/* 168 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.6F));
/* 169 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE1_X, this.hb.cY + EYE1_Y, Color.GOLD), 0.25F));
/*     */         
/* 171 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 173 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 174 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.7F));
/* 175 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE2_X, this.hb.cY + EYE2_Y, Color.GOLD), 0.25F));
/*     */         
/* 177 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 179 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 180 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.8F));
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX + EYE3_X, this.hb.cY + EYE3_Y, Color.GOLD), 0.25F));
/*     */         
/* 183 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, false, true));
/*     */         
/* 185 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*     */         break;
/*     */       case 2:
/* 188 */         playSfx();
/* 189 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.YELLOW, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
/* 190 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.GOLD, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.5F));
/*     */         
/* 192 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SMASH));
/*     */         
/* 194 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(downfallMod.getRandomDownfallCurse().makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.35F, Settings.HEIGHT / 2.0F));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 199 */         playSfx();
/* 200 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 201 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 202 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/*     */ 
/*     */         
/* 205 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt * 3), this.strAmt * 3));
/* 206 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, this.blockAmt));
/* 207 */         if (hasPower(TrueNeowPower.POWER_ID)) {
/* 208 */           getPower(TrueNeowPower.POWER_ID).onSpecificTrigger();
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 213 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 218 */     int roll = MathUtils.random(3);
/*     */     
/* 220 */     if (roll == 0) {
/* 221 */       CardCrawlGame.sound.play("VO_NEOW_1A");
/* 222 */     } else if (roll == 1) {
/* 223 */       CardCrawlGame.sound.play("VO_NEOW_1B");
/* 224 */     } else if (roll == 2) {
/* 225 */       CardCrawlGame.sound.play("VO_NEOW_2A");
/*     */     } else {
/* 227 */       CardCrawlGame.sound.play("VO_NEOW_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 236 */     switch (this.turnNum) {
/*     */       case 0:
/* 238 */         setMove((byte)0, AbstractMonster.Intent.STRONG_DEBUFF);
/* 239 */         this.turnNum++;
/*     */         break;
/*     */       case 1:
/* 242 */         setMove((byte)2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/* 243 */         this.turnNum++;
/*     */         break;
/*     */       case 2:
/* 246 */         setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/* 247 */         this.turnNum++;
/*     */         break;
/*     */       case 3:
/* 250 */         setMove((byte)3, AbstractMonster.Intent.DEFEND_BUFF);
/* 251 */         this.turnNum = 1;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 259 */     super.die();
/* 260 */     useFastShakeAnimation(5.0F);
/* 261 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 262 */     onBossVictoryLogic();
/* 263 */     onFinalBossVictoryLogic();
/* 264 */     CardCrawlGame.stopClock = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 270 */     super.dispose();
/* 271 */     neowboss = null;
/*     */   }
/*     */   
/*     */   public boolean foggy() {
/* 275 */     return hasPower(SeeingDoubleProduct.POWER_ID);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTip(SpriteBatch sb) {
/* 280 */     if (!foggy()) {
/* 281 */       super.renderTip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderName(SpriteBatch sb) {
/* 288 */     if (!foggy()) {
/* 289 */       SpireSuper.call(new Object[] { sb });
/*     */     }
/*     */   }
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderPowerIcons(SpriteBatch sb, float x, float y) {
/* 295 */     if (!foggy() && !this.halfDead)
/* 296 */       SpireSuper.call(new Object[] { sb, Float.valueOf(x), Float.valueOf(y) }); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\OldNeow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */