/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.AbstractCustomBossCard;
/*    */ import charbosses.cards.status.EnSlimed;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Slimed;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class EnIcky extends AbstractCustomBossCard {
/*    */   public static final String ID = "downfall_Charboss:Icky";
/* 19 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/icky.png"); private static final CardStrings cardStrings;
/*    */   
/*    */   static {
/* 22 */     cardStrings = CardCrawlGame.languagePack.getCardStrings("downfall:Icky");
/*    */   }
/*    */   
/*    */   public EnIcky() {
/* 26 */     super("downfall_Charboss:Icky", cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/* 27 */     this.exhaust = true;
/* 28 */     this.portrait = TextureLoader.getTextureAsAtlasRegion(IMG_PATH);
/* 29 */     this.portraitImg = TextureLoader.getTexture(IMG_PATH);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnSlimed(), 1));
/* 35 */     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new EnIcky();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnIcky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */