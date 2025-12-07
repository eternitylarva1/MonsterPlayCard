/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DescriptionLine;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import sneckomod.OffclassHelper;
/*    */ 
/*    */ public class Malfunctioning extends CustomCard {
/* 20 */   public static final String ID = downfallMod.makeID("Malfunctioning");
/*    */   public static final String NAME;
/*    */   public static final String DESCRIPTION;
/* 23 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/malfunctioning.png");
/* 24 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 25 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.CURSE;
/* 26 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); private static final int COST = 1; static {
/* 33 */     NAME = cardStrings.NAME;
/* 34 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 35 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   public static String UPGRADED_DESCRIPTION;
/*    */   
/*    */   public Malfunctioning() {
/* 40 */     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/* 41 */     this.exhaust = true;
/* 42 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 52 */     super.triggerOnEndOfTurnForPlayingCard();
/* 53 */     flash();
/* 54 */     superFlash();
/* 55 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 56 */       addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand, true));
/* 57 */       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(OffclassHelper.getARandomStatus(), 1));
/*    */     } 
/* 59 */     addToBot((AbstractGameAction)new WaitAction(0.1F));
/* 60 */     addToBot((AbstractGameAction)new WaitAction(0.1F));
/* 61 */     addToBot((AbstractGameAction)new WaitAction(0.1F));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 65 */     return (AbstractCard)new Malfunctioning();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public void initializeDescriptionCN() {
/* 73 */     super.initializeDescriptionCN();
/* 74 */     if ((Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) && this.description != null && this.description.size() >= 1)
/* 75 */       for (int i = 0; i < this.description.size(); i++) {
/* 76 */         if (((DescriptionLine)this.description.get(i)).text.equals("，")) {
/* 77 */           StringBuilder sb = new StringBuilder();
/* 78 */           ((DescriptionLine)this.description.get(i - 1)).text = sb.append(((DescriptionLine)this.description.get(i - 1)).text).append("，").toString();
/* 79 */           this.description.remove(i);
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Malfunctioning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */