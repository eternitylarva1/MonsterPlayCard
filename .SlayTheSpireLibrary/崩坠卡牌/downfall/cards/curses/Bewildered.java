/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import sneckomod.actions.MuddleHandAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bewildered
/*    */   extends CustomCard
/*    */ {
/* 24 */   public static final String ID = downfallMod.makeID("Bewildered");
/*    */   public static final String NAME;
/*    */   public static final String DESCRIPTION;
/* 27 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/bewildered.png");
/* 28 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 29 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.CURSE;
/* 30 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); private static final int COST = -2; static {
/* 37 */     NAME = cardStrings.NAME;
/* 38 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 39 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   public static String UPGRADED_DESCRIPTION;
/*    */   private boolean activateThisTurn = false;
/*    */   
/*    */   public Bewildered() {
/* 45 */     super(ID, NAME, IMG_PATH, -2, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/*    */     
/* 47 */     this.exhaust = true;
/* 48 */     this.isEthereal = true;
/* 49 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 58 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MuddleHandAction());
/* 59 */     flash();
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return (AbstractCard)new Bewildered();
/*    */   }
/*    */   
/*    */   public void upgrade() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Bewildered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */