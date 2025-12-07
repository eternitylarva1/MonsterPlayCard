/*     */ package downfall.monsters.gauntletbosses;
/*     */ import com.badlogic.gdx.math.MathUtils;
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
/*     */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import downfall.powers.gauntletpowers.MonsterVigor;
/*     */ import downfall.powers.gauntletpowers.OnDeathEveryoneRuggedVuln;
/*     */ import hermit.cards.Defend_Hermit;
/*     */ import hermit.cards.Maintenance;
/*     */ import hermit.cards.Scavenge;
/*     */ import hermit.cards.Strike_Hermit;
/*     */ 
/*     */ public class Hermit extends GauntletBoss {
/*  26 */   public static final String ID = downfallMod.makeID("GauntletHermit");
/*  27 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("hermit:hermit")).NAMES[0];
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  33 */   int turnNum = 0;
/*     */   
/*     */   public Hermit(float x, float y) {
/*  36 */     super(NAME, ID, 144, 0.0F, -5.0F, 240.0F, 270.0F, (String)null, x, y);
/*  37 */     loadAnimation("hermitResources/images/char/hermit/Hermit.atlas", "hermitResources/images/char/hermit/Hermit.json", 1.0F);
/*  38 */     this.flipHorizontal = true;
/*  39 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  40 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  41 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  42 */     e.setTimeScale(0.7F);
/*     */     
/*  44 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */     
/*  46 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  47 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  48 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  53 */     super.usePreBattleAction();
/*  54 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new OnDeathEveryoneRuggedVuln((AbstractCreature)this, 2), 2));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*  58 */     int dex = 0;
/*  59 */     switch (this.nextMove) {
/*     */       case 1:
/*  61 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  62 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  63 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  64 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 2:
/*  68 */         if (hasPower("Dexterity")) {
/*  69 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  71 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  72 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 5 + dex));
/*  73 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  74 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 3:
/*  78 */         if (hasPower("Dexterity")) {
/*  79 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  81 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10 + dex * 2));
/*     */         break;
/*     */       case 4:
/*  84 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new PlatedArmorPower((AbstractCreature)this, 4), 4));
/*     */         break;
/*     */       case 5:
/*  87 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/*  88 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new DexterityPower((AbstractCreature)this, 3), 3));
/*     */         break;
/*     */     } 
/*     */     
/*  92 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void bossMove() {
/*  96 */     int rnd = AbstractDungeon.cardRandomRng.random(0, 3);
/*  97 */     switch (rnd) {
/*     */       case 0:
/*  99 */         this.isAttacking = true;
/* 100 */         setMove(moveName(Strike_Hermit.ID, Strike_Hermit.ID), (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */       case 1:
/* 103 */         this.isAttacking = true;
/* 104 */         setMove(moveName(Strike_Hermit.ID, Defend_Hermit.ID), (byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 2:
/* 107 */         this.isAttacking = false;
/* 108 */         setMove(moveName(Defend_Hermit.ID, Defend_Hermit.ID), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 3:
/* 111 */         this.isAttacking = false;
/* 112 */         setMove(moveName(Scavenge.ID), (byte)4, AbstractMonster.Intent.BUFF);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 118 */     this.turnNum++;
/* 119 */     if (this.turnNum == 5) {
/* 120 */       this.isAttacking = false;
/* 121 */       setMove(moveName(Maintenance.ID), (byte)5, AbstractMonster.Intent.BUFF);
/*     */     }
/* 123 */     else if (this.isThird && this.turnNum > 1 && this.ally1 != null && this.ally2 != null) {
/*     */       
/* 125 */       if (!this.ally1.isDeadOrEscaped() && !this.ally2.isDeadOrEscaped() && this.ally1.isAttacking && this.ally2.isAttacking) {
/* 126 */         if (AbstractDungeon.cardRandomRng.randomBoolean()) {
/* 127 */           setMove(moveName(Defend_Hermit.ID, Defend_Hermit.ID), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         } else {
/* 129 */           setMove(moveName(Scavenge.ID), (byte)4, AbstractMonster.Intent.BUFF);
/*     */         } 
/*     */       } else {
/*     */         
/* 133 */         bossMove();
/*     */       } 
/*     */     } else {
/* 136 */       bossMove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\gauntletbosses\Hermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */