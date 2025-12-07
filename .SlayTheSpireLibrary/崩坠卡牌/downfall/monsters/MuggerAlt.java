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
/*     */ import downfall.downfallMod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MuggerAlt
/*     */   extends AbstractMonster
/*     */ {
/*  28 */   public static final String ID = downfallMod.makeID("MuggerAlt");
/*  29 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Looter");
/*  30 */   private static final MonsterStrings monsterStringsAlt = CardCrawlGame.languagePack.getMonsterStrings(downfallMod.makeID("MuggerAlt"));
/*  31 */   public static final String NAME = monsterStrings.NAME;
/*  32 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  33 */   public static final String[] DIALOG = monsterStringsAlt.DIALOG;
/*     */   private int swipeDmg;
/*     */   private int bigSwipeDmg;
/*  36 */   private int escapeDef = 12;
/*  37 */   private int speechCount = 0;
/*  38 */   private static final String SLASH_MSG1 = DIALOG[0];
/*  39 */   private static final String SMOKE_BOMB_MSG = DIALOG[1];
/*  40 */   private int slashCount = 0;
/*     */   
/*     */   public MuggerAlt(float x, float y) {
/*  43 */     super(NAME, ID, 52, 0.0F, 0.0F, 200.0F, 220.0F, null, x, y);
/*     */     
/*  45 */     this.dialogX = -30.0F * Settings.scale;
/*  46 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  48 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  49 */       setHp(46, 50);
/*     */     } else {
/*  51 */       setHp(44, 48);
/*     */     } 
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  55 */       this.swipeDmg = 11;
/*  56 */       this.bigSwipeDmg = 18;
/*     */     } else {
/*  58 */       this.swipeDmg = 10;
/*  59 */       this.bigSwipeDmg = 16;
/*     */     } 
/*     */     
/*  62 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.swipeDmg));
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.bigSwipeDmg));
/*     */     
/*  65 */     loadAnimation("images/monsters/theCity/looterAlt/skeleton.atlas", "images/monsters/theCity/looterAlt/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  69 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  70 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  75 */     switch (this.nextMove) {
/*     */       case 1:
/*  77 */         if (this.slashCount == 0 && this.speechCount == 0) {
/*  78 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SLASH_MSG1, 0.3F, 2.0F));
/*  79 */           this.speechCount = 1;
/*     */         } 
/*     */         
/*  82 */         playSfx();
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  85 */               .get(0)));
/*     */         
/*  87 */         this.slashCount++;
/*  88 */         if (this.slashCount == 2) {
/*  89 */           if (AbstractDungeon.aiRng.randomBoolean(0.5F)) {
/*  90 */             setMove((byte)2, AbstractMonster.Intent.DEFEND); break;
/*     */           } 
/*  92 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[0], (byte)4, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage
/*  93 */                 .get(1)).base));
/*     */           break;
/*     */         } 
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage
/*  97 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 4:
/* 101 */         playSfx();
/* 102 */         this.slashCount++;
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 105 */               .get(1)));
/* 106 */         setMove((byte)2, AbstractMonster.Intent.DEFEND);
/*     */         break;
/*     */       case 2:
/* 109 */         if (this.speechCount == 1) {
/* 110 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SMOKE_BOMB_MSG, 0.75F, 2.5F));
/* 111 */           this.speechCount = 2;
/*     */         } 
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.escapeDef));
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, AbstractMonster.Intent.ATTACK));
/* 115 */         this.slashCount = 0;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 123 */     int roll = MathUtils.random(2);
/* 124 */     if (roll == 0) {
/* 125 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1A"));
/*     */     } else {
/* 127 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 132 */     int roll = MathUtils.random(2);
/* 133 */     if (roll == 0) {
/* 134 */       CardCrawlGame.sound.play("VO_MUGGER_2A");
/*     */     } else {
/* 136 */       CardCrawlGame.sound.play("VO_MUGGER_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 142 */     playDeathSfx();
/* 143 */     this.state.setTimeScale(0.1F);
/* 144 */     useShakeAnimation(5.0F);
/*     */     
/* 146 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 151 */     setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\MuggerAlt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */