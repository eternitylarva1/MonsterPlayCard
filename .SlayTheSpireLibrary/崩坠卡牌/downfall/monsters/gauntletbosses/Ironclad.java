/*     */ package downfall.monsters.gauntletbosses;
/*     */ 
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
/*     */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.EnemyDemonFormPower;
/*     */ import downfall.powers.gauntletpowers.MonsterVigor;
/*     */ import downfall.powers.gauntletpowers.OnDeathEveryoneStr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ironclad
/*     */   extends GauntletBoss
/*     */ {
/*  31 */   public static final String ID = downfallMod.makeID("GauntletIronclad");
/*  32 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("Ironclad")).NAMES[0];
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  38 */   int turnNum = 0;
/*     */   
/*     */   public Ironclad(float x, float y) {
/*  41 */     super(NAME, ID, 160, -4.0F, -16.0F, 220.0F, 290.0F, (String)null, x, y);
/*     */     
/*  43 */     loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0F);
/*  44 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  45 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  46 */     this.flipHorizontal = true;
/*  47 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  48 */     e.setTimeScale(0.6F);
/*  49 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */     
/*  51 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  52 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  53 */     this.damage.add(new DamageInfo((AbstractCreature)this, 8));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  58 */     super.usePreBattleAction();
/*  59 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new OnDeathEveryoneStr((AbstractCreature)this, 3), 3));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  64 */     int dex = 0;
/*  65 */     switch (this.nextMove) {
/*     */       case 1:
/*  67 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  68 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  69 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  70 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 2:
/*  74 */         if (hasPower("Dexterity")) {
/*  75 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  77 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  78 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 5 + dex));
/*  79 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  80 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 3:
/*  84 */         if (hasPower("Dexterity")) {
/*  85 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  87 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10 + dex * 2));
/*     */         break;
/*     */       case 4:
/*  90 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  91 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*  92 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  93 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 5:
/*  97 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new EnemyDemonFormPower((AbstractCreature)this, 2), 2));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void bossMove() {
/* 106 */     int rnd = AbstractDungeon.cardRandomRng.random(0, 3);
/* 107 */     if (this.turnNum > 5) {
/* 108 */       rnd = AbstractDungeon.cardRandomRng.random(0, 4);
/*     */     }
/* 110 */     switch (rnd) {
/*     */       case 0:
/* 112 */         this.isAttacking = true;
/* 113 */         setMove(moveName("Strike_R", "Strike_R"), (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */       case 1:
/* 116 */         this.isAttacking = true;
/* 117 */         setMove(moveName("Strike_R", "Defend_R"), (byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 2:
/* 120 */         this.isAttacking = false;
/* 121 */         setMove(moveName("Defend_R", "Defend_R"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 3:
/* 124 */         this.isAttacking = true;
/* 125 */         setMove(moveName("Bash"), (byte)4, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(2)).base);
/*     */         break;
/*     */       case 4:
/* 128 */         this.isAttacking = true;
/* 129 */         setMove(moveName("Bash"), (byte)4, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(2)).base);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 135 */     this.turnNum++;
/* 136 */     if (this.turnNum == 5) {
/* 137 */       this.isAttacking = false;
/* 138 */       setMove(moveName("Demon Form"), (byte)5, AbstractMonster.Intent.BUFF);
/*     */     }
/* 140 */     else if (this.isThird && this.turnNum > 1 && this.ally1 != null && this.ally2 != null) {
/*     */       
/* 142 */       if (!this.ally1.isDeadOrEscaped() && !this.ally2.isDeadOrEscaped() && this.ally1.isAttacking && this.ally2.isAttacking) {
/* 143 */         setMove(moveName("Defend_R", "Defend_R"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */       } else {
/* 145 */         bossMove();
/*     */       } 
/*     */     } else {
/*     */       
/* 149 */       bossMove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\gauntletbosses\Ironclad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */