/*     */ package downfall.actions;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.SummonGremlinAction;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
/*     */ import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.MinionPower;
/*     */ import com.megacrit.cardcrawl.powers.SlowPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ModifiedSummonGremlinAction extends AbstractGameAction {
/*  24 */   private static final Logger logger = LogManager.getLogger(SummonGremlinAction.class.getName());
/*     */   
/*     */   private AbstractMonster m;
/*     */   
/*     */   public ModifiedSummonGremlinAction(AbstractMonster[] gremlins, boolean firstSummon) {
/*  29 */     if (Settings.FAST_MODE) {
/*  30 */       this.startDuration = Settings.ACTION_DUR_FAST;
/*     */     } else {
/*  32 */       this.startDuration = Settings.ACTION_DUR_LONG;
/*     */     } 
/*     */     
/*  35 */     this.duration = this.startDuration;
/*  36 */     int slot = identifySlot(gremlins);
/*  37 */     if (slot == -1) {
/*  38 */       logger.info("INCORRECTLY ATTEMPTED TO CHANNEL GREMLIN.");
/*     */     } else {
/*  40 */       if (firstSummon) {
/*     */         float x, y;
/*     */         
/*  43 */         switch (slot) {
/*     */           case 0:
/*  45 */             x = GremlinLeader.POSX[0];
/*  46 */             y = GremlinLeader.POSY[0];
/*     */             break;
/*     */           case 1:
/*  49 */             x = GremlinLeader.POSX[1];
/*  50 */             y = GremlinLeader.POSY[1];
/*     */             break;
/*     */           case 2:
/*  53 */             x = GremlinLeader.POSX[2];
/*  54 */             y = GremlinLeader.POSY[2];
/*     */             break;
/*     */           default:
/*  57 */             x = GremlinLeader.POSX[0];
/*  58 */             y = GremlinLeader.POSY[0]; break;
/*     */         } 
/*  60 */         this.m = (AbstractMonster)new GremlinNob(x, y);
/*     */       } else {
/*  62 */         this.m = getRandomGremlin(slot);
/*     */       } 
/*     */       
/*  65 */       gremlins[slot] = this.m;
/*  66 */       Iterator<AbstractRelic> var3 = AbstractDungeon.player.relics.iterator();
/*     */       
/*  68 */       while (var3.hasNext()) {
/*  69 */         AbstractRelic r = var3.next();
/*  70 */         r.onSpawnMonster(this.m);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int identifySlot(AbstractMonster[] gremlins) {
/*  76 */     for (int i = 0; i < gremlins.length; i++) {
/*  77 */       if (gremlins[i] == null || (gremlins[i]).isDying) {
/*  78 */         return i;
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return -1;
/*     */   }
/*     */   
/*     */   private AbstractMonster getRandomGremlin(int slot) {
/*  86 */     ArrayList<String> pool = new ArrayList<>();
/*  87 */     pool.add("GremlinWarrior");
/*  88 */     pool.add("GremlinWarrior");
/*  89 */     pool.add("GremlinThief");
/*  90 */     pool.add("GremlinThief");
/*  91 */     pool.add("GremlinFat");
/*  92 */     pool.add("GremlinFat");
/*  93 */     pool.add("GremlinTsundere");
/*  94 */     pool.add("GremlinWizard");
/*     */ 
/*     */     
/*  97 */     switch (slot)
/*     */     { case 0:
/*  99 */         x = GremlinLeader.POSX[0];
/* 100 */         y = GremlinLeader.POSY[0];
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
/* 115 */         return MonsterHelper.getGremlin(pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y);case 1: x = GremlinLeader.POSX[1]; y = GremlinLeader.POSY[1]; return MonsterHelper.getGremlin(pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y);case 2: x = GremlinLeader.POSX[2]; y = GremlinLeader.POSY[2]; return MonsterHelper.getGremlin(pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y); }  float x = GremlinLeader.POSX[0]; float y = GremlinLeader.POSY[0]; return MonsterHelper.getGremlin(pool.get(AbstractDungeon.aiRng.random(0, pool.size() - 1)), x, y);
/*     */   }
/*     */   
/*     */   private int getSmartPosition() {
/* 119 */     int position = 0;
/*     */     
/* 121 */     for (Iterator<AbstractMonster> var2 = (AbstractDungeon.getCurrRoom()).monsters.monsters.iterator(); var2.hasNext(); position++) {
/* 122 */       AbstractMonster mo = var2.next();
/* 123 */       if (this.m.drawX <= mo.drawX) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return position;
/*     */   }
/*     */   
/*     */   public void update() {
/* 132 */     if (this.duration == this.startDuration) {
/* 133 */       this.m.animX = 1200.0F * Settings.xScale;
/* 134 */       this.m.init();
/* 135 */       this.m.applyPowers();
/* 136 */       (AbstractDungeon.getCurrRoom()).monsters.addMonster(getSmartPosition(), this.m);
/* 137 */       if (ModHelper.isModEnabled("Lethality")) {
/* 138 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new StrengthPower((AbstractCreature)this.m, 3), 3));
/*     */       }
/*     */       
/* 141 */       if (ModHelper.isModEnabled("Time Dilation")) {
/* 142 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new SlowPower((AbstractCreature)this.m, 0)));
/*     */       }
/*     */       
/* 145 */       if (!(this.m instanceof GremlinNob)) {
/* 146 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new MinionPower((AbstractCreature)this.m)));
/*     */       }
/*     */     } 
/*     */     
/* 150 */     tickDuration();
/* 151 */     if (this.isDone) {
/* 152 */       this.m.animX = 0.0F;
/* 153 */       this.m.showHealthBar();
/* 154 */       this.m.usePreBattleAction();
/*     */     } else {
/* 156 */       this.m.animX = Interpolation.fade.apply(0.0F, 1200.0F * Settings.xScale, this.duration);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\ModifiedSummonGremlinAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */