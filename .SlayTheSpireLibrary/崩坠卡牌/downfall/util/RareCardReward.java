/*     */ package downfall.util;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.RewardGlowEffect;
/*     */ import downfall.downfallMod;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class RareCardReward extends RewardItem {
/*  27 */   private float REWARD_TEXT_X = 833.0F * Settings.scale;
/*  28 */   private ArrayList<AbstractGameEffect> effects = new ArrayList<>();
/*  29 */   public static final String ID = downfallMod.makeID("RareCardReward");
/*  30 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
/*     */ 
/*     */   
/*     */   public RareCardReward(AbstractCard.CardColor colorType) {
/*  34 */     this.hb = new Hitbox(460.0F * Settings.scale, 90.0F * Settings.scale);
/*  35 */     this.flashTimer = 0.0F;
/*  36 */     this.isDone = false;
/*  37 */     this.ignoreReward = false;
/*  38 */     this.redText = false;
/*  39 */     this.type = RewardItem.RewardType.CARD;
/*  40 */     if (colorType == AbstractCard.CardColor.COLORLESS) {
/*  41 */       this.cards = AbstractDungeon.getColorlessRewardCards();
/*     */     } else {
/*  43 */       this.cards = AbstractDungeon.getRewardCards();
/*     */     } 
/*     */     
/*  46 */     int cardsToJankReplace = this.cards.size();
/*     */     
/*  48 */     this.cards.clear();
/*     */     
/*  50 */     for (int i = 0; i < cardsToJankReplace; i++) {
/*  51 */       AbstractCard cardToAdd = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy();
/*  52 */       while (cardListDuplicate(cardToAdd)) {
/*  53 */         cardToAdd = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy();
/*     */       }
/*  55 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  56 */         r.onPreviewObtainCard(cardToAdd);
/*     */       }
/*  58 */       this.cards.add(cardToAdd);
/*     */     } 
/*     */     
/*  61 */     this.text = TEXT[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbstractCard getRandomSeal() {
/*  66 */     ArrayList<AbstractCard> list = new ArrayList<>();
/*  67 */     for (AbstractCard c : CardLibrary.getAllCards()) {
/*  68 */       if (c instanceof theHexaghost.cards.seals.AbstractSealCard)
/*  69 */         list.add(c); 
/*     */     } 
/*  71 */     return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
/*     */   }
/*     */   
/*     */   public boolean cardListDuplicate(AbstractCard card) {
/*  75 */     for (AbstractCard alreadyHave : this.cards) {
/*  76 */       if (alreadyHave.cardID.equals(card.cardID)) {
/*  77 */         return true;
/*     */       }
/*     */     } 
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public void update() {
/*  84 */     if (this.flashTimer > 0.0F) {
/*  85 */       this.flashTimer -= Gdx.graphics.getDeltaTime();
/*  86 */       if (this.flashTimer < 0.0F) {
/*  87 */         this.flashTimer = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/*  91 */     this.hb.update();
/*  92 */     if (this.effects.size() == 0) {
/*  93 */       this.effects.add(new RewardGlowEffect(this.hb.cX, this.hb.cY));
/*     */     }
/*     */     
/*  96 */     Iterator<AbstractGameEffect> i = this.effects.iterator();
/*     */     
/*  98 */     while (i.hasNext()) {
/*  99 */       AbstractGameEffect e = i.next();
/* 100 */       e.update();
/* 101 */       if (e.isDone) {
/* 102 */         i.remove();
/*     */       }
/*     */     } 
/*     */     
/* 106 */     if (this.hb.hovered) {
/* 107 */       switch (this.type) {
/*     */         case POTION:
/* 109 */           if (!AbstractDungeon.topPanel.potionCombine) {
/* 110 */             TipHelper.renderGenericTip(360.0F * Settings.scale, InputHelper.mY, this.potion.name, this.potion.description);
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/* 115 */     if (this.relicLink != null) {
/* 116 */       this.relicLink.redText = this.hb.hovered;
/*     */     }
/*     */     
/* 119 */     if (this.hb.justHovered) {
/* 120 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/* 123 */     if (this.hb.hovered && InputHelper.justClickedLeft && !this.isDone) {
/* 124 */       CardCrawlGame.sound.playA("UI_CLICK_1", 0.1F);
/* 125 */       this.hb.clickStarted = true;
/*     */     } 
/*     */     
/* 128 */     if (this.hb.hovered && CInputActionSet.select.isJustPressed() && !this.isDone) {
/* 129 */       this.hb.clicked = true;
/* 130 */       CardCrawlGame.sound.playA("UI_CLICK_1", 0.1F);
/*     */     } 
/*     */     
/* 133 */     if (this.hb.clicked) {
/* 134 */       this.hb.clicked = false;
/* 135 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*     */     Color color;
/* 144 */     if (this.hb.hovered) {
/* 145 */       sb.setColor(new Color(0.4F, 0.6F, 0.6F, 1.0F));
/*     */     } else {
/* 147 */       sb.setColor(new Color(0.5F, 0.6F, 0.6F, 0.8F));
/*     */     } 
/*     */     
/* 150 */     if (this.hb.clickStarted) {
/* 151 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.scale * 0.98F, Settings.scale * 0.98F, 0.0F, 0, 0, 464, 98, false, false);
/*     */     } else {
/* 153 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 464, 98, false, false);
/*     */     } 
/*     */     
/* 156 */     if (this.flashTimer != 0.0F) {
/* 157 */       sb.setColor(0.6F, 1.0F, 1.0F, this.flashTimer * 1.5F);
/* 158 */       sb.setBlendFunction(770, 1);
/* 159 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.scale * 1.03F, Settings.scale * 1.15F, 0.0F, 0, 0, 464, 98, false, false);
/* 160 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 163 */     Texture cardImg = ImageMaster.REWARD_CARD_BOSS;
/*     */     
/* 165 */     sb.setColor(Color.WHITE);
/* 166 */     sb.draw(cardImg, REWARD_ITEM_X - 32.0F, this.y - 32.0F - 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */     
/* 170 */     if (this.hb.hovered) {
/* 171 */       color = Settings.GOLD_COLOR;
/*     */     } else {
/* 173 */       color = Settings.CREAM_COLOR;
/*     */     } 
/*     */     
/* 176 */     if (this.redText) {
/* 177 */       color = Settings.RED_TEXT_COLOR;
/*     */     }
/*     */     
/* 180 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, TEXT[0], this.REWARD_TEXT_X, this.y + 5.0F * Settings.scale, 1000.0F * Settings.scale, 0.0F, color);
/* 181 */     if (!this.hb.hovered)
/*     */     {
/* 183 */       for (AbstractGameEffect e : this.effects) {
/* 184 */         e.render(sb);
/*     */       }
/*     */     }
/*     */     
/* 188 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\RareCardReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */