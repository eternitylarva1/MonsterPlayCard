/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class Aged
/*    */   extends CustomCard
/*    */ {
/* 18 */   public static final String ID = downfallMod.makeID("Aged");
/*    */   public static final String NAME;
/*    */   public static final String DESCRIPTION;
/* 21 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/aged.png");
/*    */   
/* 23 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 24 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.CURSE;
/* 25 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); private static final int COST = -2; static {
/* 32 */     NAME = cardStrings.NAME;
/* 33 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 34 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   public static String UPGRADED_DESCRIPTION;
/*    */   public Aged() {
/* 38 */     super(ID, NAME, IMG_PATH, -2, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/*    */     
/* 40 */     this.magicNumber = this.baseMagicNumber = 1;
/* 41 */     this.cardsToPreview = (AbstractCard)new VoidCard();
/*    */     
/* 43 */     this.isEthereal = true;
/* 44 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 54 */     super.triggerOnEndOfTurnForPlayingCard();
/* 55 */     flash();
/* 56 */     superFlash();
/* 57 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new VoidCard(), 1, false, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return (AbstractCard)new Aged();
/*    */   }
/*    */   
/*    */   public void upgrade() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Aged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */