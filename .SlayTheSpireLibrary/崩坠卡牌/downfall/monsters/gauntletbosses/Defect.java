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
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.BufferPower;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.gauntletpowers.MonsterVigor;
/*     */ import downfall.powers.gauntletpowers.OnDeathEveryoneBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Defect
/*     */   extends GauntletBoss
/*     */ {
/*  29 */   public static final String ID = downfallMod.makeID("GauntletDefect");
/*  30 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("Defect")).NAMES[0];
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  37 */   int turnNum = 0;
/*     */   
/*     */   public Defect(float x, float y) {
/*  40 */     super(NAME, ID, 150, 0.0F, -5.0F, 240.0F, 244.0F, (String)null, x, y);
/*     */     
/*  42 */     loadAnimation("images/characters/defect/idle/skeleton.atlas", "images/characters/defect/idle/skeleton.json", 1.0F);
/*  43 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  44 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  45 */     this.flipHorizontal = true;
/*  46 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  47 */     e.setTimeScale(0.9F);
/*  48 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */     
/*  50 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  51 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  52 */     this.damage.add(new DamageInfo((AbstractCreature)this, 11));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  57 */     super.usePreBattleAction();
/*     */     
/*  59 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new OnDeathEveryoneBuffer((AbstractCreature)this, 2), 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  64 */     int dex = 0;
/*  65 */     switch (this.nextMove) {
/*     */       case 1:
/*  67 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  68 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         
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
/*  91 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  92 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1), 1));
/*     */         
/*  94 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  95 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 5:
/*  99 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BufferPower((AbstractCreature)this, 2), 2));
/*     */         break;
/*     */     } 
/*     */     
/* 103 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void bossMove() {
/* 109 */     int rnd = AbstractDungeon.cardRandomRng.random(0, 3);
/* 110 */     switch (rnd) {
/*     */       case 0:
/* 112 */         this.isAttacking = true;
/* 113 */         setMove(moveName("Strike_B", "Strike_B"), (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */       case 1:
/* 116 */         this.isAttacking = true;
/* 117 */         setMove(moveName("Strike_B", "Defend_B"), (byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 2:
/* 120 */         this.isAttacking = false;
/* 121 */         setMove(moveName("Defend_B", "Defend_B"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 3:
/* 124 */         this.isAttacking = true;
/* 125 */         setMove(moveName("Core Surge"), (byte)4, AbstractMonster.Intent.ATTACK_BUFF, ((DamageInfo)this.damage.get(2)).base);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 131 */     this.turnNum++;
/* 132 */     if (this.turnNum == 5) {
/* 133 */       this.isAttacking = false;
/* 134 */       setMove(moveName("Buffer") + "+", (byte)5, AbstractMonster.Intent.BUFF);
/*     */     }
/* 136 */     else if (this.isThird && this.turnNum > 1 && this.ally1 != null && this.ally2 != null) {
/*     */       
/* 138 */       if (!this.ally1.isDeadOrEscaped() && !this.ally2.isDeadOrEscaped() && this.ally1.isAttacking && this.ally2.isAttacking) {
/* 139 */         setMove(moveName("Defend_B", "Defend_B"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */       } else {
/* 141 */         bossMove();
/*     */       } 
/*     */     } else {
/* 144 */       bossMove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\gauntletbosses\Defect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */