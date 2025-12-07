/*     */ package downfall.cards;
/*     */ 
/*     */ import basemod.BaseMod;
/*     */ import basemod.abstracts.CustomCard;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ public class OctoChoiceCard
/*     */   extends CustomCard {
/*     */   private static final int COST = -2;
/*  17 */   private String IMG = null;
/*     */   
/*     */   private AbstractCard card1;
/*     */   
/*     */   private AbstractCard card2;
/*     */   private AbstractCard card3;
/*     */   private AbstractCard prev1;
/*     */   private AbstractCard prev2;
/*     */   private AbstractCard prev3;
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description, int dmg, int blk, int magic, AbstractCard.CardType type) {
/*  28 */     super(id, name, IMG, -2, description, type, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  29 */     this.IMG = IMG;
/*  30 */     this.baseDamage = dmg;
/*  31 */     this.baseBlock = blk;
/*  32 */     this.baseMagicNumber = this.magicNumber = magic;
/*     */   }
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description, int dmg, int blk, AbstractCard.CardType type) {
/*  36 */     super(id, name, IMG, -2, description, type, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  37 */     this.IMG = IMG;
/*  38 */     this.baseDamage = dmg;
/*  39 */     this.baseBlock = blk;
/*     */   }
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description) {
/*  43 */     super(id, name, IMG, -2, description, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  44 */     this.IMG = IMG;
/*  45 */     if (id != null && (CardCrawlGame.languagePack.getCardStrings(id)).NAME == "[MISSING_TITLE]") {
/*  46 */       BaseMod.loadCustomStrings(CardStrings.class, "{\"" + id + "\": {\"NAME\": \"" + name + "\", \"DESCRIPTION\": \" " + description + "\"}}");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description, AbstractCard.CardColor color) {
/*  53 */     super(id, name, IMG, -2, description, AbstractCard.CardType.SKILL, color, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  54 */     this.IMG = IMG;
/*     */   }
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description, AbstractCard.CardColor color, AbstractCard.CardType type) {
/*  58 */     super(id, name, IMG, -2, description, type, color, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  59 */     this.IMG = IMG;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OctoChoiceCard(String id, String name, String IMG, String description, AbstractCard prev1, AbstractCard prev2, AbstractCard prev3) {
/*  65 */     super(id, name, IMG, -2, description, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
/*  66 */     this.IMG = IMG;
/*  67 */     this.card1 = prev1;
/*  68 */     this.card2 = prev2;
/*  69 */     this.card3 = prev3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void hover() {
/*  74 */     if (this.card1 != null) {
/*  75 */       this.prev1 = this.card1;
/*     */     }
/*  77 */     if (this.card2 != null) {
/*  78 */       this.prev2 = this.card2;
/*     */     }
/*  80 */     if (this.card3 != null) {
/*  81 */       this.prev3 = this.card3;
/*     */     }
/*  83 */     super.hover();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unhover() {
/*  88 */     super.unhover();
/*  89 */     this.prev1 = null;
/*  90 */     this.prev2 = null;
/*  91 */     this.prev3 = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade() {
/* 101 */     upgradeName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCardTip(SpriteBatch sb) {
/* 106 */     super.renderCardTip(sb);
/*     */ 
/*     */     
/* 109 */     if (this.isLocked || (AbstractDungeon.player != null && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode))) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     float drawScale = 0.5F;
/* 114 */     float yPosition1 = Settings.HEIGHT * 0.2F;
/*     */     
/* 116 */     float xPosition1 = Settings.WIDTH * 0.35F;
/* 117 */     float xPosition2 = Settings.WIDTH * 0.5F;
/* 118 */     float xPosition3 = Settings.WIDTH * 0.65F;
/*     */     
/* 120 */     if (this.prev1 != null) {
/* 121 */       AbstractCard card = this.prev1.makeStatEquivalentCopy();
/* 122 */       if (card != null) {
/* 123 */         card.drawScale = drawScale;
/* 124 */         card.current_x = xPosition1;
/* 125 */         card.current_y = yPosition1;
/* 126 */         card.render(sb);
/*     */       } 
/*     */     } 
/* 129 */     if (this.prev2 != null) {
/* 130 */       AbstractCard card = this.prev2.makeStatEquivalentCopy();
/* 131 */       if (card != null) {
/* 132 */         card.drawScale = drawScale;
/* 133 */         card.current_x = xPosition2;
/* 134 */         card.current_y = yPosition1;
/* 135 */         card.render(sb);
/*     */       } 
/*     */     } 
/* 138 */     if (this.prev3 != null) {
/* 139 */       AbstractCard card = this.prev3.makeStatEquivalentCopy();
/* 140 */       if (card != null) {
/* 141 */         card.drawScale = drawScale;
/* 142 */         card.current_x = xPosition3;
/* 143 */         card.current_y = yPosition1;
/* 144 */         card.render(sb);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/* 151 */     return (AbstractCard)new OctoChoiceCard(this.cardID, this.name, this.IMG, this.rawDescription);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\OctoChoiceCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */