/*     */ package downfall.monsters;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ import downfall.downfallMod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LooterAlt
/*     */   extends AbstractMonster
/*     */ {
/*  28 */   public static final String ID = downfallMod.makeID("LooterAlt");
/*  29 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Looter");
/*  30 */   private static final MonsterStrings monsterStringsAlt = CardCrawlGame.languagePack.getMonsterStrings(downfallMod.makeID("LooterAlt"));
/*  31 */   public static final String NAME = monsterStrings.NAME;
/*  32 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  33 */   public static final String[] DIALOG = monsterStringsAlt.DIALOG;
/*     */   private int swipeDmg;
/*     */   private int lungeDmg;
/*  36 */   private int escapeDef = 12;
/*  37 */   private int speechCount = 0;
/*  38 */   private static final String SLASH_MSG1 = DIALOG[0];
/*  39 */   private static final String DEATH_MSG1 = DIALOG[1];
/*  40 */   private static final String SMOKE_BOMB_MSG = DIALOG[2];
/*  41 */   private int slashCount = 0;
/*     */   
/*     */   public LooterAlt(float x, float y) {
/*  44 */     super(NAME, ID, 48, 0.0F, 0.0F, 200.0F, 220.0F, null, x, y);
/*     */     
/*  46 */     this.dialogX = -30.0F * Settings.scale;
/*  47 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  49 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  50 */       setHp(46, 50);
/*     */     } else {
/*  52 */       setHp(44, 48);
/*     */     } 
/*     */     
/*  55 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  56 */       this.swipeDmg = 11;
/*  57 */       this.lungeDmg = 14;
/*     */     } else {
/*  59 */       this.swipeDmg = 10;
/*  60 */       this.lungeDmg = 12;
/*     */     } 
/*     */     
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.swipeDmg));
/*  64 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.lungeDmg));
/*     */     
/*  66 */     loadAnimation("images/monsters/theBottom/looter/skeleton.atlas", "images/monsters/theBottom/looter/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  70 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  71 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  76 */     switch (this.nextMove) {
/*     */       case 1:
/*  78 */         if (this.slashCount == 0 && this.speechCount == 0) {
/*  79 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SLASH_MSG1, 0.3F, 2.0F));
/*  80 */           this.speechCount = 1;
/*     */         } 
/*     */         
/*  83 */         playSfx();
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  85 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  86 */               .get(0)));
/*     */         
/*  88 */         this.slashCount++;
/*  89 */         if (this.slashCount == 2) {
/*  90 */           if (AbstractDungeon.aiRng.randomBoolean(0.5F)) {
/*  91 */             setMove((byte)2, AbstractMonster.Intent.DEFEND); break;
/*     */           } 
/*  93 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[0], (byte)4, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage
/*  94 */                 .get(1)).base));
/*     */           break;
/*     */         } 
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage
/*  98 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 4:
/* 102 */         playSfx();
/* 103 */         this.slashCount++;
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 106 */               .get(1)));
/* 107 */         setMove((byte)2, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 2:
/* 110 */         if (this.speechCount == 1) {
/* 111 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SMOKE_BOMB_MSG, 0.75F, 2.5F));
/* 112 */           this.speechCount = 2;
/*     */         } 
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.escapeDef));
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, AbstractMonster.Intent.ATTACK));
/* 116 */         this.slashCount = 0;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 124 */     int roll = MathUtils.random(2);
/* 125 */     if (roll == 0) {
/* 126 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1A"));
/* 127 */     } else if (roll == 1) {
/* 128 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1B"));
/*     */     } else {
/* 130 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 135 */     int roll = MathUtils.random(2);
/* 136 */     if (roll == 0) {
/* 137 */       CardCrawlGame.sound.play("VO_LOOTER_2A");
/* 138 */     } else if (roll == 1) {
/* 139 */       CardCrawlGame.sound.play("VO_LOOTER_2B");
/*     */     } else {
/* 141 */       CardCrawlGame.sound.play("VO_LOOTER_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 147 */     playDeathSfx();
/* 148 */     this.state.setTimeScale(0.1F);
/* 149 */     useShakeAnimation(5.0F);
/* 150 */     if (MathUtils.randomBoolean(0.3F)) {
/* 151 */       AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.0F, DEATH_MSG1, false));
/* 152 */       if (!Settings.FAST_MODE) {
/* 153 */         this.deathTimer += 1.5F;
/*     */       }
/*     */     } 
/* 156 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 161 */     setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\LooterAlt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */