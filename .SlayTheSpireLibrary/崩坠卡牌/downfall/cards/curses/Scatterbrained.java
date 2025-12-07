/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.cards.DescriptionLine;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class Scatterbrained
/*    */   extends CustomCard
/*    */ {
/* 21 */   public static final String ID = downfallMod.makeID("Scatterbrained");
/*    */ 
/*    */   
/* 24 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/scatterbrained.png");
/*    */   
/* 26 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 27 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
/* 28 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean auto_used_end_turn = false;
/*    */ 
/*    */   
/* 35 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); public static final String NAME; public static final String DESCRIPTION; static {
/* 36 */     NAME = cardStrings.NAME;
/* 37 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 38 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   private static final int COST = 1; public static String UPGRADED_DESCRIPTION;
/*    */   public Scatterbrained() {
/* 42 */     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/* 43 */     this.exhaust = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 48 */     super.onMoveToDiscard();
/* 49 */     this.auto_used_end_turn = false;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 53 */     if (!this.auto_used_end_turn) this.exhaust = true; 
/* 54 */     if (this.dontTriggerOnUseCard) {
/* 55 */       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Scatterbrained(), 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 60 */     this.auto_used_end_turn = true;
/* 61 */     this.dontTriggerOnUseCard = true;
/* 62 */     AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem((AbstractCard)this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeDescriptionCN() {
/* 67 */     super.initializeDescriptionCN();
/* 68 */     if (Settings.language == Settings.GameLanguage.ZHS && this.description != null && this.description.size() >= 1) {
/* 69 */       for (int i = 0; i < this.description.size(); i++) {
/* 70 */         if (((DescriptionLine)this.description.get(i)).text.equals("，")) {
/* 71 */           StringBuilder sb = new StringBuilder();
/* 72 */           ((DescriptionLine)this.description.get(i - 1)).text = sb.append(((DescriptionLine)this.description.get(i - 1)).text).append("，").toString();
/* 73 */           this.description.remove(i);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 86 */     return (AbstractCard)new Scatterbrained();
/*    */   }
/*    */   
/*    */   public void upgrade() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Scatterbrained.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */