/*     */ package downfall.events;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import gremlin.characters.GremlinCharacter;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoaiHead_Evil
/*     */   extends AbstractImageEvent
/*     */ {
/*  20 */   private int hpAmt = 0;
/*  21 */   private final int goldAmount = 333;
/*  22 */   private int screenNum = 0;
/*     */   
/*     */   public MoaiHead_Evil() {
/*  25 */     super(NAME, DESCRIPTIONS[0], "images/events/moaiHead.jpg");
/*  26 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  27 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.18F);
/*     */     } else {
/*  29 */       this.hpAmt = MathUtils.round(AbstractDungeon.player.maxHealth * 0.125F);
/*     */     } 
/*     */     
/*  32 */     if (AbstractDungeon.player instanceof GremlinCharacter) {
/*  33 */       this.imageEventText.setDialogOption(OPTIONSALT[6] + this.hpAmt + OPTIONS[1]);
/*     */     } else {
/*  35 */       this.imageEventText.setDialogOption(OPTIONS[0] + this.hpAmt + OPTIONS[1]);
/*     */     } 
/*     */     
/*  38 */     Objects.requireNonNull(this); if (AbstractDungeon.player.gold >= 333) {
/*  39 */       if (AbstractDungeon.player instanceof GremlinCharacter) {
/*  40 */         Objects.requireNonNull(this); this.imageEventText.setDialogOption(OPTIONSALT[0] + 'ō' + OPTIONSALT[5] + ((this.hpAmt + 4) / 5) + OPTIONSALT[2]);
/*     */       } else {
/*  42 */         Objects.requireNonNull(this); this.imageEventText.setDialogOption(OPTIONSALT[0] + 'ō' + OPTIONSALT[1] + this.hpAmt + OPTIONSALT[2]);
/*     */       } 
/*     */     } else {
/*  45 */       Objects.requireNonNull(this); this.imageEventText.setDialogOption(OPTIONSALT[3] + 'ō' + OPTIONSALT[4], true);
/*     */     } 
/*     */     
/*  48 */     this.imageEventText.setDialogOption(OPTIONS[4]);
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  52 */     switch (this.screenNum) {
/*     */       case 0:
/*  54 */         switch (buttonPressed) {
/*     */           case 0:
/*  56 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  57 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
/*  58 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  59 */             AbstractDungeon.player.maxHealth -= this.hpAmt;
/*  60 */             if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth) {
/*  61 */               AbstractDungeon.player.currentHealth = AbstractDungeon.player.maxHealth;
/*     */             }
/*     */             
/*  64 */             if (AbstractDungeon.player.maxHealth < 1) {
/*  65 */               AbstractDungeon.player.maxHealth = 1;
/*     */             }
/*  67 */             logMetricHealAndLoseMaxHP("downfall:MoaiHead", "Heal", AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth, this.hpAmt);
/*  68 */             AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/*  69 */             if (AbstractDungeon.player instanceof GremlinCharacter) {
/*  70 */               ((GremlinCharacter)AbstractDungeon.player).healGremlins(AbstractDungeon.player.maxHealth);
/*     */             }
/*  72 */             this.screenNum = 1;
/*  73 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*  74 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           case 1:
/*  77 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  78 */             this.screenNum = 1;
/*  79 */             Objects.requireNonNull(this); AbstractDungeon.player.loseGold(333);
/*  80 */             AbstractDungeon.player.increaseMaxHp(this.hpAmt, false);
/*  81 */             logMetric("downfall:MoaiHead", "Gave Souls", null, null, null, null, null, null, null, 0, AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth, 0, this.hpAmt, 333, 0);
/*     */             
/*  83 */             AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/*  84 */             if (AbstractDungeon.player instanceof GremlinCharacter) {
/*  85 */               ((GremlinCharacter)AbstractDungeon.player).healGremlins(AbstractDungeon.player.maxHealth);
/*     */             }
/*  87 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*  88 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/*  91 */         this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/*  92 */         this.screenNum = 1;
/*  93 */         this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*  94 */         this.imageEventText.clearRemainingOptions();
/*  95 */         logMetricIgnored("downfall:MoaiHead");
/*     */         return;
/*     */     } 
/*     */     
/*  99 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 104 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Moai Head");
/* 105 */   public static final String NAME = eventStrings.NAME;
/* 106 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getEventString("downfall:MoaiHead")).DESCRIPTIONS;
/* 107 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/* 108 */   public static final String[] OPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:MoaiHead")).OPTIONS;
/*     */   public static final String ID = "downfall:MoaiHead";
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\MoaiHead_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */