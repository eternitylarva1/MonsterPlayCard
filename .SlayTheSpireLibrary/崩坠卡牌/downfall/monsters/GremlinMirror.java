/*     */ package downfall.monsters;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.ShoutAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.MinionPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import downfall.actions.ModifiedSummonGremlinAction;
/*     */ import gremlin.GremlinMod;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GremlinMirror
/*     */   extends AbstractMonster
/*     */ {
/*  35 */   public AbstractMonster[] gremlins = new AbstractMonster[3];
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
/*  49 */   private int STAB_DMG = 6;
/*  50 */   private int STAB_AMT = 3;
/*     */   private boolean firstSummon = true;
/*  52 */   private int turn = 0;
/*     */   private boolean hijack = false;
/*     */   
/*     */   public GremlinMirror() {
/*  56 */     super(NAME, "GremlinMirror", 158, 0.0F, -15.0F, 200.0F, 310.0F, (String)null, 35.0F, 0.0F);
/*  57 */     this.type = AbstractMonster.EnemyType.ELITE;
/*  58 */     loadAnimation("images/monsters/theCity/gremlinleader/skeleton.atlas", "images/monsters/theCity/gremlinleader/skeleton.json", 1.0F);
/*  59 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  60 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  61 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  62 */     e.setTimeScale(0.8F);
/*  63 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  64 */       setHp(170);
/*     */     } else {
/*  66 */       setHp(160);
/*     */     } 
/*     */     
/*  69 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  70 */       this.strAmt = 5;
/*  71 */       this.blockAmt = 10;
/*  72 */     } else if (AbstractDungeon.ascensionLevel >= 3) {
/*  73 */       this.strAmt = 4;
/*  74 */       this.blockAmt = 6;
/*     */     } else {
/*  76 */       this.strAmt = 3;
/*  77 */       this.blockAmt = 6;
/*     */     } 
/*     */     
/*  80 */     this.dialogX = -80.0F * Settings.scale;
/*  81 */     this.dialogY = 50.0F * Settings.scale;
/*  82 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.STAB_DMG));
/*     */   }
/*     */   
/*     */   public void usePreBattleAction() {
/*  86 */     this.gremlins[0] = (AbstractDungeon.getMonsters()).monsters.get(0);
/*  87 */     this.gremlins[1] = (AbstractDungeon.getMonsters()).monsters.get(1);
/*  88 */     this.gremlins[2] = null;
/*  89 */     AbstractMonster[] var1 = this.gremlins;
/*  90 */     int var2 = var1.length;
/*     */     
/*  92 */     for (int var3 = 0; var3 < var2; var3++) {
/*  93 */       AbstractMonster m = var1[var3];
/*  94 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MinionPower((AbstractCreature)this)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     Iterator<AbstractMonster> var1;
/* 100 */     this.turn++;
/* 101 */     GremlinMod.logger.info("The turn is: " + this.turn);
/*     */     
/* 103 */     switch (this.nextMove) {
/*     */       case 2:
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "CALL"));
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ModifiedSummonGremlinAction(this.gremlins, this.firstSummon));
/* 107 */         this.firstSummon = false;
/* 108 */         if (!this.hijack) {
/* 109 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ModifiedSummonGremlinAction(this.gremlins, false));
/*     */         }
/* 111 */         this.hijack = false;
/*     */         break;
/*     */       case 3:
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, getEncourageQuote()));
/* 115 */         var1 = (AbstractDungeon.getMonsters()).monsters.iterator();
/*     */ 
/*     */         
/* 118 */         while (var1.hasNext()) {
/*     */ 
/*     */ 
/*     */           
/* 122 */           AbstractMonster m = var1.next();
/* 123 */           if (m == this) {
/* 124 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt)); continue;
/* 125 */           }  if (!m.isDying) {
/* 126 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
/* 127 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)this, this.blockAmt));
/*     */           } 
/*     */         }  break;
/*     */       case 4:
/* 131 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL, true));
/* 135 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */     } 
/* 138 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private String getEncourageQuote() {
/* 142 */     ArrayList<String> list = new ArrayList<>();
/* 143 */     list.add(DIALOG[0]);
/* 144 */     list.add(DIALOG[1]);
/* 145 */     list.add(DIALOG[2]);
/* 146 */     return list.get(AbstractDungeon.aiRng.random(0, list.size() - 1));
/*     */   }
/*     */   
/*     */   protected void getMove(int num) {
/* 150 */     if (this.firstSummon && 
/* 151 */       this.turn >= 1) {
/* 152 */       this.hijack = (numAliveGremlins() > 1);
/* 153 */       setMove(RALLY_NAME, (byte)2, AbstractMonster.Intent.UNKNOWN);
/*     */       
/*     */       return;
/*     */     } 
/* 157 */     if (numAliveGremlins() == 0) {
/* 158 */       if (num < 75) {
/* 159 */         if (!lastMove((byte)2)) {
/* 160 */           setMove(RALLY_NAME, (byte)2, AbstractMonster.Intent.UNKNOWN);
/*     */         } else {
/* 162 */           setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         } 
/* 164 */       } else if (!lastMove((byte)4)) {
/* 165 */         setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 167 */         setMove(RALLY_NAME, (byte)2, AbstractMonster.Intent.UNKNOWN);
/*     */       } 
/* 169 */     } else if (numAliveGremlins() < 2) {
/* 170 */       if (num < 50) {
/* 171 */         if (!lastMove((byte)2)) {
/* 172 */           setMove(RALLY_NAME, (byte)2, AbstractMonster.Intent.UNKNOWN);
/*     */         } else {
/* 174 */           getMove(AbstractDungeon.aiRng.random(50, 99));
/*     */         } 
/* 176 */       } else if (num < 80) {
/* 177 */         if (!lastMove((byte)3)) {
/* 178 */           setMove((byte)3, AbstractMonster.Intent.DEFEND_BUFF);
/*     */         } else {
/* 180 */           setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         } 
/* 182 */       } else if (!lastMove((byte)4)) {
/* 183 */         setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 185 */         getMove(AbstractDungeon.aiRng.random(0, 80));
/*     */       } 
/* 187 */     } else if (numAliveGremlins() > 1) {
/* 188 */       if (num < 66) {
/* 189 */         if (!lastMove((byte)3)) {
/* 190 */           setMove((byte)3, AbstractMonster.Intent.DEFEND_BUFF);
/*     */         } else {
/* 192 */           setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         } 
/* 194 */       } else if (!lastMove((byte)4)) {
/* 195 */         setMove((byte)4, AbstractMonster.Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 197 */         setMove((byte)3, AbstractMonster.Intent.DEFEND_BUFF);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int numAliveGremlins() {
/* 204 */     int count = 0;
/* 205 */     Iterator<AbstractMonster> var2 = (AbstractDungeon.getMonsters()).monsters.iterator();
/*     */     
/* 207 */     while (var2.hasNext()) {
/* 208 */       AbstractMonster m = var2.next();
/* 209 */       if (m != null && m != this && !m.isDying) {
/* 210 */         count++;
/*     */       }
/*     */     } 
/*     */     
/* 214 */     return count;
/*     */   }
/*     */   
/*     */   public void changeState(String stateName) {
/* 218 */     byte var3 = -1;
/* 219 */     switch (stateName.hashCode()) {
/*     */       case 2060894:
/* 221 */         if (stateName.equals("CALL")) {
/* 222 */           var3 = 1;
/*     */         }
/*     */         break;
/*     */       case 1941037640:
/* 226 */         if (stateName.equals("ATTACK")) {
/* 227 */           var3 = 0;
/*     */         }
/*     */         break;
/*     */     } 
/* 231 */     switch (var3) {
/*     */       case 0:
/* 233 */         this.state.setAnimation(0, "Attack", false);
/* 234 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case 1:
/* 237 */         this.state.setAnimation(0, "Call", false);
/* 238 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 244 */     super.damage(info);
/* 245 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 246 */       this.state.setAnimation(0, "Hit", false);
/* 247 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 253 */     super.die();
/* 254 */     boolean first = true;
/* 255 */     Iterator<AbstractMonster> var2 = (AbstractDungeon.getCurrRoom()).monsters.monsters.iterator();
/*     */ 
/*     */     
/* 258 */     while (var2.hasNext()) {
/* 259 */       AbstractMonster m = var2.next();
/* 260 */       if (!m.isDying && 
/* 261 */         m.hasPower("Minion")) {
/* 262 */         if (first) {
/* 263 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)m, DIALOG[3], 0.5F, 1.2F));
/* 264 */           first = false; continue;
/*     */         } 
/* 266 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)m, DIALOG[4], 0.5F, 1.2F));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 272 */     var2 = (AbstractDungeon.getCurrRoom()).monsters.monsters.iterator();
/*     */     
/* 274 */     while (var2.hasNext()) {
/* 275 */       AbstractMonster m = var2.next();
/* 276 */       if (!m.isDying && 
/* 277 */         m.hasPower("Minion")) {
/* 278 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(m));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinLeader");
/* 287 */   public static final String NAME = monsterStrings.NAME;
/* 288 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 289 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/* 290 */   public static final float[] POSX = new float[] { -366.0F, -170.0F, -532.0F };
/* 291 */   public static final float[] POSY = new float[] { -4.0F, 6.0F, 0.0F };
/* 292 */   private static final String RALLY_NAME = MOVES[0];
/*     */   public static final String ID = "GremlinMirror";
/*     */   private static final int HP_MIN = 140;
/*     */   private static final int HP_MAX = 148;
/*     */   private static final int A_2_HP_MIN = 145;
/*     */   private static final int A_2_HP_MAX = 155;
/*     */   public static final String ENC_NAME = "Gremlin Leader Combat";
/*     */   private static final byte RALLY = 2;
/*     */   private static final byte ENCOURAGE = 3;
/*     */   private static final int STR_AMT = 3;
/*     */   private static final int BLOCK_AMT = 6;
/*     */   private static final int A_2_STR_AMT = 4;
/*     */   private static final int A_18_STR_AMT = 5;
/*     */   private static final int A_18_BLOCK_AMT = 10;
/*     */   private int strAmt;
/*     */   private int blockAmt;
/*     */   private static final byte STAB = 4;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\GremlinMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */