/*     */ package downfall.monsters;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.InstantKillAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import downfall.powers.TotemInvulnerablePower;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractTotemMonster
/*     */   extends AbstractMonster
/*     */ {
/*  28 */   public static Float beamOffsetX = Float.valueOf(25.0F * Settings.scale);
/*  29 */   public static Float beamOffsetY = Float.valueOf(-20.0F * Settings.scale);
/*  30 */   public static Float beamOffsetX2 = Float.valueOf(-35.0F * Settings.scale);
/*  31 */   public static Float beamOffsetY2 = Float.valueOf(-20.0F * Settings.scale);
/*  32 */   public Integer baseHP = Integer.valueOf(50);
/*  33 */   public Integer HPAscBuffed = Integer.valueOf(10);
/*  34 */   public AbstractMonster.Intent intentType = AbstractMonster.Intent.BUFF;
/*     */   
/*     */   public Color totemLivingColor;
/*     */   private Method refupdateIntent;
/*     */   private boolean wasFalling = false;
/*     */   private boolean spawnedAfterFirst3 = false;
/*     */   
/*     */   public AbstractTotemMonster(String name, String ID, String imgPath) {
/*  42 */     super(name, ID, 420, 0.0F, 60.0F, 200.0F, 200.0F, null, -50.0F, 160.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  49 */       this.refupdateIntent = AbstractMonster.class.getDeclaredMethod("updateIntent", new Class[0]);
/*     */     
/*     */     }
/*  52 */     catch (NoSuchMethodException e) {
/*  53 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  56 */     this.refupdateIntent.setAccessible(true);
/*     */     
/*  58 */     this.type = AbstractMonster.EnemyType.ELITE;
/*  59 */     this.dialogX = -100.0F * Settings.scale;
/*  60 */     this.dialogY = 10.0F * Settings.scale;
/*  61 */     this.drawY = AbstractDungeon.floorY - 50.0F * Settings.scale;
/*  62 */     this.drawX = Settings.WIDTH * 0.75F;
/*     */     
/*  64 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  65 */       setHp(this.baseHP.intValue() + this.HPAscBuffed.intValue());
/*     */     } else {
/*  67 */       setHp(this.baseHP.intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  74 */     super.usePreBattleAction();
/*  75 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new TotemInvulnerablePower((AbstractCreature)this)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void totemAttack() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  85 */     switch (stateName) {
/*     */       case "ATTACK":
/*  87 */         this.state.setAnimation(0, "Attack", false);
/*  88 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void healthBarUpdatedEvent() {
/*  96 */     if (this.currentHealth <= 0 && hasPower(TotemInvulnerablePower.POWER_ID))
/*     */     {
/*  98 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  99 */         if (m != this && m.currentHealth > 1 && m instanceof AbstractTotemMonster) {
/* 100 */           this.currentHealth = 1;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 105 */     super.healthBarUpdatedEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 111 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 112 */       if (m != this && m.hasPower(TotemInvulnerablePower.POWER_ID))
/* 113 */         addToTop((AbstractGameAction)new InstantKillAction((AbstractCreature)m)); 
/*     */     } 
/* 115 */     super.die();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderHealth(SpriteBatch sb) {
/* 122 */     this.hb.height *= 1.4F;
/* 123 */     super.renderHealth(sb);
/* 124 */     this.hb.height /= 1.4F;
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/* 128 */     totemAttack();
/*     */     
/* 130 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 136 */     super.update();
/*     */     
/*     */     try {
/* 139 */       this.intentHb.move(this.hb.cX - 140.0F * Settings.scale, this.drawY + 170.0F * Settings.scale);
/* 140 */       this.refupdateIntent.invoke(this, new Object[0]);
/* 141 */     } catch (InvocationTargetException|IllegalAccessException e) {
/* 142 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 150 */     getUniqueTotemMove();
/*     */   }
/*     */   
/*     */   public void getUniqueTotemMove() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\AbstractTotemMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */