/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.cards.AbstractCustomBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnAged
/*    */   extends AbstractCustomBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Aged";
/*    */   private static final CardStrings cardStrings;
/* 24 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/aged.png");
/*    */   
/*    */   static {
/* 27 */     cardStrings = CardCrawlGame.languagePack.getCardStrings("downfall:Aged");
/*    */   }
/*    */   
/*    */   public EnAged() {
/* 31 */     super("downfall_Charboss:Aged", cardStrings.NAME, IMG_PATH, -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/* 32 */     this.isEthereal = true;
/* 33 */     this.portrait = TextureLoader.getTextureAsAtlasRegion(IMG_PATH);
/* 34 */     this.portraitImg = TextureLoader.getTexture(IMG_PATH);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 43 */     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new VoidCard(), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 52 */     return (AbstractCard)new EnAged();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnAged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */