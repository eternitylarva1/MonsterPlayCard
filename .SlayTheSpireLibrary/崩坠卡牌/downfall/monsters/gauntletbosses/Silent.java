/*     */ package downfall.monsters.gauntletbosses;
/*     */ 
/*     */ import charbosses.powers.BossIntangiblePower;
/*     */ import charbosses.powers.cardpowers.EnemyWraithFormPower;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.gauntletpowers.MonsterVigor;
/*     */ import downfall.powers.gauntletpowers.OnDeathEveryoneThorns;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Silent
/*     */   extends GauntletBoss
/*     */ {
/*  34 */   public static final String ID = downfallMod.makeID("GauntletSilent");
/*  35 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("Silent")).NAMES[0];
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  41 */   int turnNum = 0;
/*     */   
/*     */   public Silent(float x, float y) {
/*  44 */     super(NAME, ID, 140, -4.0F, -16.0F, 240.0F, 290.0F, (String)null, x, y);
/*  45 */     loadAnimation("images/characters/theSilent/idle/skeleton.atlas", "images/characters/theSilent/idle/skeleton.json", 1.0F);
/*  46 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  47 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  48 */     this.flipHorizontal = true;
/*  49 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  50 */     e.setTimeScale(0.9F);
/*  51 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */     
/*  53 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  54 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  60 */     super.usePreBattleAction();
/*  61 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new OnDeathEveryoneThorns((AbstractCreature)this, 5), 5));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*  65 */     int dex = 0;
/*  66 */     switch (this.nextMove) {
/*     */       case 1:
/*  68 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  69 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  70 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  71 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 2:
/*  75 */         if (hasPower("Dexterity")) {
/*  76 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  78 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  79 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 5 + dex));
/*  80 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  81 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 3:
/*  85 */         if (hasPower("Dexterity")) {
/*  86 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  88 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10 + dex * 2));
/*     */         break;
/*     */       case 4:
/*  91 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*  92 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 11));
/*     */         break;
/*     */       case 5:
/*  95 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BossIntangiblePower((AbstractCreature)this, 2), 2));
/*  96 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new EnemyWraithFormPower((AbstractCreature)this, -1), -1));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void bossMove() {
/* 105 */     int rnd = AbstractDungeon.cardRandomRng.random(0, 3);
/* 106 */     if (hasPower("Wraith Form v2")) {
/* 107 */       rnd = 0;
/*     */     }
/* 109 */     switch (rnd) {
/*     */       case 0:
/* 111 */         this.isAttacking = true;
/* 112 */         setMove(moveName("Strike_G", "Strike_G"), (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */       case 1:
/* 115 */         this.isAttacking = true;
/* 116 */         setMove(moveName("Strike_G", "Defend_G"), (byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 2:
/* 119 */         this.isAttacking = false;
/* 120 */         setMove(moveName("Defend_G", "Defend_G"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 3:
/* 123 */         this.isAttacking = false;
/* 124 */         setMove(moveName("Leg Sweep"), (byte)4, AbstractMonster.Intent.DEFEND_DEBUFF);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 130 */     this.turnNum++;
/* 131 */     if (this.turnNum == 5) {
/* 132 */       this.isAttacking = false;
/* 133 */       setMove(moveName("Wraith Form v2"), (byte)5, AbstractMonster.Intent.BUFF);
/*     */     }
/* 135 */     else if (this.isThird && this.turnNum > 1 && this.ally1 != null && this.ally2 != null) {
/*     */       
/* 137 */       if (!this.ally1.isDeadOrEscaped() && !this.ally2.isDeadOrEscaped() && this.ally1.isAttacking && this.ally2.isAttacking) {
/* 138 */         if (AbstractDungeon.cardRandomRng.randomBoolean()) {
/* 139 */           setMove(moveName("Defend_G", "Defend_G"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         } else {
/* 141 */           setMove(moveName("Leg Sweep"), (byte)4, AbstractMonster.Intent.DEFEND_DEBUFF);
/*     */         } 
/*     */       } else {
/* 144 */         bossMove();
/*     */       } 
/*     */     } else {
/* 147 */       bossMove();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 154 */     if (info.output > 0 && hasPower("Intangible")) {
/* 155 */       info.output = 1;
/*     */     }
/* 157 */     super.damage(info);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\gauntletbosses\Silent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */