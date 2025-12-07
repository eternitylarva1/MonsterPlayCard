/*     */ package downfall.util;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.utils.Disposable;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import downfall.downfallMod;
/*     */ import downfall.vfx.CustomAnimatedNPC;
/*     */ import downfall.vfx.TopLevelSpeechBubble;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Objects;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeartMerchant
/*     */   implements Disposable
/*     */ {
/*     */   public HeartMerchant() {
/*  46 */     this(0.0F, 0.0F, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  51 */   private ArrayList<AbstractCard> cards1 = new ArrayList<>();
/*  52 */   private ArrayList<AbstractCard> cards2 = new ArrayList<>();
/*  53 */   private ArrayList<String> idleMessages = new ArrayList<>();
/*  54 */   private float speechTimer = 1.5F;
/*     */   private boolean saidWelcome = false;
/*  56 */   private int shopScreen = 1;
/*  57 */   public CustomAnimatedNPC anim = new CustomAnimatedNPC(DRAW_X, DRAW_Y, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 0); public HeartMerchant(float x, float y, int newShopScreen) {
/*     */     AbstractCard c;
/*  59 */     for (c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy(); c.color == AbstractCard.CardColor.COLORLESS; c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy());
/*     */ 
/*     */     
/*  62 */     this.cards1.add(c);
/*     */     
/*  64 */     for (c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy(); Objects.equals(c.cardID, ((AbstractCard)this.cards1.get(this.cards1.size() - 1)).cardID) || c.color == AbstractCard.CardColor.COLORLESS; c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy());
/*     */ 
/*     */     
/*  67 */     this.cards1.add(c);
/*     */     
/*  69 */     for (c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy(); c.color == AbstractCard.CardColor.COLORLESS; c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy());
/*     */ 
/*     */     
/*  72 */     this.cards1.add(c);
/*     */     
/*  74 */     for (c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy(); Objects.equals(c.cardID, ((AbstractCard)this.cards1.get(this.cards1.size() - 1)).cardID) || c.color == AbstractCard.CardColor.COLORLESS; c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy());
/*     */ 
/*     */     
/*  77 */     this.cards1.add(c);
/*     */     
/*  79 */     for (c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy(); c.color == AbstractCard.CardColor.COLORLESS; c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy());
/*     */ 
/*     */     
/*  82 */     this.cards1.add(c);
/*  83 */     this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/*  84 */     this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.RARE).makeCopy());
/*  85 */     if (AbstractDungeon.id.equals("TheEnding")) {
/*  86 */       Collections.addAll(this.idleMessages, ENDING_TEXT);
/*     */     } else {
/*  88 */       Collections.addAll(this.idleMessages, TEXT);
/*     */     } 
/*     */     
/*  91 */     this.speechTimer = 1.5F;
/*  92 */     this.modX = x;
/*  93 */     this.modY = y;
/*  94 */     this.shopScreen = newShopScreen;
/*  95 */     AbstractDungeon.shopScreen.init(this.cards1, this.cards2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 101 */     this.hb.update();
/* 102 */     this.anim.update();
/*     */ 
/*     */     
/* 105 */     if (this.hb.hovered) {
/* 106 */       this.anim.changeBorderColor(Color.WHITE);
/* 107 */       this.anim.highlighted = true;
/*     */     } else {
/* 109 */       this.anim.changeBorderColor(Color.VIOLET);
/* 110 */       this.anim.highlighted = false;
/*     */     } 
/*     */     
/* 113 */     if (((this.hb.hovered && InputHelper.justClickedLeft) || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !AbstractDungeon.isFadingOut && !AbstractDungeon.player.viewingRelics) {
/* 114 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(NAMES[0]);
/* 115 */       this.saidWelcome = true;
/* 116 */       (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 117 */       (AbstractDungeon.getCurrRoom()).rewardAllowed = false;
/* 118 */       AbstractDungeon.shopScreen.open();
/* 119 */       this.hb.hovered = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.speechTimer -= Gdx.graphics.getDeltaTime();
/* 125 */     if (this.speechTimer < 0.0F && this.shopScreen == 1) {
/* 126 */       String msg = this.idleMessages.get(MathUtils.random(0, this.idleMessages.size() - 1));
/* 127 */       if (!this.saidWelcome) {
/* 128 */         this.saidWelcome = true;
/* 129 */         welcomeSfx();
/* 130 */         msg = NAMES[1];
/*     */       } else {
/* 132 */         playMiscSfx();
/*     */       } 
/*     */       
/* 135 */       if (MathUtils.randomBoolean()) {
/* 136 */         AbstractDungeon.effectList.add(new TopLevelSpeechBubble(this.hb.cX - 50.0F * Settings.scale, this.hb.cY + 70.0F * Settings.scale, 3.0F, msg, false));
/*     */       } else {
/* 138 */         AbstractDungeon.effectList.add(new TopLevelSpeechBubble(this.hb.cX + 50.0F * Settings.scale, this.hb.cY + 70.0F * Settings.scale, 3.0F, msg, true));
/*     */       } 
/*     */       
/* 141 */       this.speechTimer = MathUtils.random(40.0F, 60.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void welcomeSfx() {
/* 149 */     CardCrawlGame.sound.play("HEART_SIMPLE");
/*     */   }
/*     */   
/*     */   private void playMiscSfx() {
/* 153 */     CardCrawlGame.sound.play("HEART_SIMPLE");
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 158 */     if (Settings.isControllerMode) {
/* 159 */       sb.setColor(Color.WHITE);
/* 160 */       sb.draw(CInputActionSet.select.getKeyImg(), DRAW_X - 32.0F + 150.0F * Settings.scale, DRAW_Y - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */     
/* 163 */     if (this.hb != null) {
/* 164 */       this.anim.render(sb);
/* 165 */       this.hb.render(sb);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 172 */     if (this.anim != null) {
/* 173 */       this.anim.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnHitbox() {
/* 180 */     this.hb = new Hitbox(500.0F * Settings.scale, 700.0F * Settings.scale);
/* 181 */     this.hb.move(DRAW_X, DRAW_Y);
/* 182 */     this.anim.portalRenderActive = true;
/*     */   }
/*     */ 
/*     */   
/* 186 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartMerchant"));
/* 187 */   public static final String[] NAMES = characterStrings.NAMES;
/* 188 */   public static final String[] TEXT = characterStrings.TEXT;
/* 189 */   public static final String[] ENDING_TEXT = characterStrings.OPTIONS;
/* 190 */   public static final float DRAW_X = 1240.0F * Settings.scale;
/* 191 */   public static final float DRAW_Y = AbstractDungeon.floorY + 220.0F * Settings.scale;
/*     */   public Hitbox hb;
/*     */   protected float modX;
/*     */   protected float modY;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\HeartMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */