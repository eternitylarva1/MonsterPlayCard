/*     */ package downfall.monsters;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ConfusionPower;
/*     */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
/*     */ import downfall.powers.EnemyDemonFormPower;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SneckoMirror
/*     */   extends AbstractMonster
/*     */ {
/*     */   public static final String ID = "Snecko";
/*     */   
/*     */   public SneckoMirror() {
/*  54 */     this(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public SneckoMirror(float x, float y) {
/*  58 */     super(NAME, "Snecko", 120, -30.0F, -20.0F, 310.0F, 305.0F, (String)null, x, y);
/*  59 */     this.firstTurn = true;
/*  60 */     loadAnimation("sneckomodResources/images/char/skeleton.atlas", "sneckomodResources/images/char/skeletonM.json", 1.0F);
/*  61 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  62 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  63 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  64 */     e.setTimeScale(0.8F);
/*  65 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  66 */       setHp(240);
/*     */     } else {
/*  68 */       setHp(220);
/*     */     } 
/*     */     
/*  71 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  72 */       this.biteDmg = 24;
/*  73 */       this.tailDmg = 16;
/*     */     } else {
/*  75 */       this.biteDmg = 20;
/*  76 */       this.tailDmg = 12;
/*     */     } 
/*     */ 
/*     */     
/*  80 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDmg));
/*  81 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.tailDmg));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*  85 */     switch (this.nextMove) {
/*     */       case 1:
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_SNECKO_GLARE"));
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntimidateEffect(this.hb.cX, this.hb.cY), 0.5F));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)AbstractDungeon.player, 1.0F, 1.0F));
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ConfusionPower((AbstractCreature)AbstractDungeon.player)));
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new EnemyDemonFormPower((AbstractCreature)this, 1), 1));
/*     */         break;
/*     */       case 2:
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_2"));
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE.cpy()), 0.3F));
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 3:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 103 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 104 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         }
/*     */         
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */     } 
/* 110 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   public void changeState(String stateName) {
/* 114 */     byte var3 = -1;
/* 115 */     switch (stateName.hashCode()) {
/*     */       case 1321368571:
/* 117 */         if (stateName.equals("ATTACK_2")) {
/* 118 */           var3 = 1;
/*     */         }
/*     */         break;
/*     */       case 1941037640:
/* 122 */         if (stateName.equals("ATTACK")) {
/* 123 */           var3 = 0;
/*     */         }
/*     */         break;
/*     */     } 
/* 127 */     switch (var3) {
/*     */       case 0:
/* 129 */         this.state.setAnimation(0, "Attack", false);
/* 130 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case 1:
/* 133 */         this.state.setAnimation(0, "Attack_2", false);
/* 134 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 140 */     super.damage(info);
/* 141 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 142 */       this.state.setAnimation(0, "Hit", false);
/* 143 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 149 */     if (this.firstTurn) {
/* 150 */       this.firstTurn = false;
/* 151 */       setMove(MOVES[0], (byte)1, AbstractMonster.Intent.STRONG_DEBUFF);
/* 152 */     } else if (num < 40) {
/* 153 */       setMove(MOVES[1], (byte)3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */     }
/* 155 */     else if (lastTwoMoves((byte)2)) {
/* 156 */       setMove(MOVES[1], (byte)3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */     } else {
/* 158 */       setMove(MOVES[2], (byte)2, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 165 */     super.die();
/* 166 */     CardCrawlGame.sound.play("SNECKO_DEATH");
/*     */   }
/*     */ 
/*     */   
/* 170 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Snecko");
/* 171 */   public static final String NAME = monsterStrings.NAME;
/* 172 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 173 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   private static final byte GLARE = 1;
/*     */   private static final byte BITE = 2;
/*     */   private static final byte TAIL = 3;
/*     */   private static final int BITE_DAMAGE = 15;
/*     */   private static final int TAIL_DAMAGE = 8;
/*     */   private static final int A_2_BITE_DAMAGE = 18;
/*     */   private static final int A_2_TAIL_DAMAGE = 10;
/*     */   private int biteDmg;
/*     */   private int tailDmg;
/*     */   private static final int VULNERABLE_AMT = 2;
/*     */   private static final int HP_MIN = 114;
/*     */   private static final int HP_MAX = 120;
/*     */   private static final int A_2_HP_MIN = 120;
/*     */   private static final int A_2_HP_MAX = 125;
/*     */   private boolean firstTurn;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\SneckoMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */