/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Slimed;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class Icky
/*    */   extends CustomCard
/*    */ {
/* 19 */   public static final String ID = downfallMod.makeID("Icky");
/*    */   public static final String NAME;
/*    */   public static final String DESCRIPTION;
/* 22 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/icky.png");
/* 23 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 24 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.CURSE;
/* 25 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); private static final int COST = 1; private static final int BLOCK = 5; static {
/* 34 */     NAME = cardStrings.NAME;
/* 35 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 36 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   private static final int UPGRADE_BONUS = 3; public static String UPGRADED_DESCRIPTION;
/*    */   public Icky() {
/* 40 */     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/*    */     
/* 42 */     this.magicNumber = this.baseMagicNumber = 1;
/* 43 */     this.cardsToPreview = (AbstractCard)new Slimed();
/* 44 */     this.exhaust = true;
/* 45 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Slimed(), 1));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new Icky();
/*    */   }
/*    */   
/*    */   public void upgrade() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Icky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */