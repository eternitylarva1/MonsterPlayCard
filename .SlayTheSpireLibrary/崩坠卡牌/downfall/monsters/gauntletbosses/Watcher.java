/*     */ package downfall.monsters.gauntletbosses;
/*     */ 
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.watcher.WallopAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.gauntletpowers.MonsterVigor;
/*     */ import downfall.powers.gauntletpowers.OnDeathEveryoneVigor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Watcher
/*     */   extends GauntletBoss
/*     */ {
/*  33 */   public static final String ID = downfallMod.makeID("GauntletWatcher");
/*  34 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("Watcher")).NAMES[0];
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  40 */   int turnNum = 0;
/*     */   
/*     */   public Watcher(float x, float y) {
/*  43 */     super(NAME, ID, 144, 0.0F, -5.0F, 240.0F, 270.0F, (String)null, x, y);
/*  44 */     loadAnimation("images/characters/watcher/idle/skeleton.atlas", "images/characters/watcher/idle/skeleton.json", 1.0F);
/*  45 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  46 */     this.flipHorizontal = true;
/*  47 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  48 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  49 */     e.setTimeScale(0.7F);
/*     */     
/*  51 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */     
/*  53 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  54 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  55 */     this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  60 */     super.usePreBattleAction();
/*  61 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new OnDeathEveryoneVigor((AbstractCreature)this, 8), 8));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  66 */     int dex = 0;
/*  67 */     switch (this.nextMove) {
/*     */       case 1:
/*  69 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  70 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  71 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  72 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 2:
/*  76 */         if (hasPower("Dexterity")) {
/*  77 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  79 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  80 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 5 + dex));
/*  81 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  82 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 3:
/*  86 */         if (hasPower("Dexterity")) {
/*  87 */           dex = (getPower("Dexterity")).amount;
/*     */         }
/*  89 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10 + dex * 2));
/*     */         break;
/*     */       case 4:
/*  92 */         addToBot((AbstractGameAction)new WallopAction((AbstractCreature)AbstractDungeon.player, this.damage.get(2)));
/*  93 */         if (hasPower(MonsterVigor.POWER_ID)) {
/*  94 */           addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, MonsterVigor.POWER_ID));
/*     */         }
/*     */         break;
/*     */       case 5:
/*  98 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new PlatedArmorPower((AbstractCreature)this, 8), 8));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 103 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void bossMove() {
/* 107 */     int rnd = AbstractDungeon.cardRandomRng.random(0, 3);
/* 108 */     switch (rnd) {
/*     */       case 0:
/* 110 */         this.isAttacking = true;
/* 111 */         setMove(moveName("Strike_P", "Strike_P"), (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */       case 1:
/* 114 */         this.isAttacking = true;
/* 115 */         setMove(moveName("Strike_P", "Defend_P"), (byte)2, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 2:
/* 118 */         this.isAttacking = false;
/* 119 */         setMove(moveName("Defend_P", "Defend_P"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 3:
/* 122 */         this.isAttacking = true;
/* 123 */         setMove(moveName("Wallop"), (byte)4, AbstractMonster.Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(2)).base);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 130 */     this.turnNum++;
/* 131 */     if (this.turnNum == 5) {
/* 132 */       this.isAttacking = false;
/* 133 */       setMove(moveName("Wish") + "+", (byte)5, AbstractMonster.Intent.BUFF);
/*     */     }
/* 135 */     else if (this.isThird && this.turnNum > 1 && this.ally1 != null && this.ally2 != null) {
/* 136 */       if (!this.ally1.isDeadOrEscaped() && !this.ally2.isDeadOrEscaped() && this.ally1.isAttacking && this.ally2.isAttacking) {
/* 137 */         setMove(moveName("Defend_P", "Defend_P"), (byte)3, AbstractMonster.Intent.DEFEND);
/*     */       } else {
/* 139 */         bossMove();
/*     */       } 
/*     */     } else {
/*     */       
/* 143 */       bossMove();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\gauntletbosses\Watcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */