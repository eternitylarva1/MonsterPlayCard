/*     */ package charbosses.powers.general;
/*     */ 
/*     */ import basemod.ReflectionHacks;
/*     */ import basemod.interfaces.CloneablePowerInterface;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.util.ArrayList;
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
/*     */ @Deprecated
/*     */ public class PoisonProtectionPower
/*     */   extends AbstractPower
/*     */   implements CloneablePowerInterface
/*     */ {
/*  32 */   public static final String POWER_ID = downfallMod.makeID("PoisonResist");
/*  33 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/*  34 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*     */   
/*  36 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/PoisonResist84.png"));
/*  37 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/PoisonResist32.png"));
/*     */   
/*     */   public static boolean CANNOT_END = false;
/*     */   
/*     */   private float timer;
/*     */   private boolean firstTurn = true;
/*     */   
/*     */   public PoisonProtectionPower(AbstractCreature owner) {
/*  45 */     this.ID = POWER_ID;
/*  46 */     this.owner = owner;
/*  47 */     this.type = AbstractPower.PowerType.BUFF;
/*  48 */     this.isTurnBased = false;
/*     */     
/*  50 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/*  51 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*     */     
/*  53 */     this.name = NAME;
/*     */     
/*  55 */     updateDescription();
/*     */   }
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
/*     */   public void atStartOfCombat() {}
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
/*     */   public void update(int slot) {
/*  80 */     super.update(slot);
/*  81 */     if (this.firstTurn) {
/*  82 */       if (this.timer <= 0.0F) {
/*  83 */         ArrayList<AbstractGameEffect> effect2 = (ArrayList<AbstractGameEffect>)ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
/*  84 */         effect2.add(new GainPowerEffect(this));
/*  85 */         this.timer = 1.0F;
/*  86 */         if (AbstractDungeon.player != null && 
/*  87 */           AbstractDungeon.player.hb.hovered) {
/*  88 */           this.firstTurn = false;
/*     */         }
/*     */       } else {
/*     */         
/*  92 */         this.timer -= Gdx.graphics.getDeltaTime();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/* 100 */     this.description = DESCRIPTIONS[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playApplyPowerSfx() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPower makeCopy() {
/* 111 */     return new PoisonProtectionPower(this.owner);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\PoisonProtectionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */