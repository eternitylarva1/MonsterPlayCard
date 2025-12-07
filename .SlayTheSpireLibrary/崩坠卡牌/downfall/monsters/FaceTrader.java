/*     */ package downfall.monsters;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.RitualPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import downfall.powers.DrawReductionPowerPlus;
/*     */ 
/*     */ public class FaceTrader extends AbstractMonster {
/*  23 */   public static final String ID = downfallMod.makeID("FaceTrader");
/*  24 */   public static final String NAME = (CardCrawlGame.languagePack.getEventString("FaceTrader")).NAME;
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 0.0F;
/*     */   private static final float HB_W = 225.0F;
/*     */   private static final float HB_H = 250.0F;
/*  30 */   int turnNum = 0;
/*     */   
/*     */   public FaceTrader() {
/*  33 */     super(NAME, ID, 100, 0.0F, 0.0F, 225.0F, 250.0F, "downfallResources/images/monsters/facetrader/facetrader.png");
/*  34 */     switch (AbstractDungeon.actNum) {
/*     */       case 1:
/*  36 */         setHp(150);
/*     */         break;
/*     */       case 2:
/*  39 */         setHp(200);
/*     */         break;
/*     */       case 3:
/*  42 */         setHp(250);
/*     */         break;
/*     */     } 
/*     */     
/*  46 */     loadAnimation("downfallResources/images/monsters/facetrader/facetrader.atlas", "downfallResources/images/monsters/facetrader/facetrader.json", 1.0F);
/*  47 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  48 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  50 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  51 */     this.stateData.setMix("AttackGremlin", "Idle", 0.2F);
/*  52 */     this.stateData.setMix("AttackNloth", "Idle", 0.2F);
/*  53 */     this.stateData.setMix("AttackCleric", "Idle", 0.2F);
/*  54 */     this.stateData.setMix("AttackCultist", "Idle", 0.2F);
/*  55 */     this.stateData.setMix("AttackSerpent", "Idle", 0.2F);
/*     */     
/*  57 */     this.damage.add(new DamageInfo((AbstractCreature)this, 15));
/*  58 */     this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*  59 */     this.type = AbstractMonster.EnemyType.ELITE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  64 */     switch (stateName) {
/*     */       case "AttackGremlin":
/*  66 */         this.state.setAnimation(0, "AttackGremlin", false);
/*  67 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "AttackNloth":
/*  70 */         this.state.setAnimation(0, "AttackNloth", false);
/*  71 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "AttackCleric":
/*  74 */         this.state.setAnimation(0, "AttackCleric", false);
/*  75 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "AttackCultist":
/*  78 */         this.state.setAnimation(0, "AttackCultist", false);
/*  79 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "AttackSerpent":
/*  82 */         this.state.setAnimation(0, "AttackSerpent", false);
/*  83 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  90 */     switch (this.nextMove) {
/*     */       case 1:
/*  92 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
/*  93 */         addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Doubt(), 1, true, false));
/*  94 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "AttackSerpent"));
/*     */         break;
/*     */       
/*     */       case 2:
/*  98 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE));
/*  99 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, false), 2));
/* 100 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "AttackGremlin"));
/*     */         break;
/*     */       case 3:
/* 103 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE));
/* 104 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DrawReductionPowerPlus((AbstractCreature)AbstractDungeon.player, 2), 2));
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "AttackNloth"));
/*     */         break;
/*     */       case 4:
/* 108 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10));
/* 109 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RitualPower((AbstractCreature)this, 1, false), 1));
/* 110 */         if (!hasPower("Ritual")) {
/* 111 */           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 1), 1));
/*     */         }
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "AttackCultist"));
/*     */         break;
/*     */       case 5:
/* 116 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this, 10));
/* 117 */         addToBot((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, 10));
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "AttackCleric"));
/*     */         break;
/*     */     } 
/*     */     
/* 122 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/*     */     int bruh;
/* 127 */     switch (this.turnNum) {
/*     */       case 0:
/* 129 */         bruh = AbstractDungeon.cardRandomRng.random(0, 2);
/* 130 */         this.turnNum = 1;
/* 131 */         switch (bruh) {
/*     */           case 0:
/* 133 */             setMove((byte)1, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */             break;
/*     */           
/*     */           case 1:
/* 137 */             setMove((byte)2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */             break;
/*     */           
/*     */           case 2:
/* 141 */             setMove((byte)3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case 1:
/* 147 */         bruh = AbstractDungeon.cardRandomRng.random(0, 1);
/* 148 */         this.turnNum = 0;
/* 149 */         switch (bruh) {
/*     */           case 0:
/* 151 */             setMove((byte)4, AbstractMonster.Intent.DEFEND_BUFF);
/*     */             break;
/*     */           
/*     */           case 1:
/* 155 */             setMove((byte)5, AbstractMonster.Intent.DEFEND_BUFF);
/*     */             break;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\FaceTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */