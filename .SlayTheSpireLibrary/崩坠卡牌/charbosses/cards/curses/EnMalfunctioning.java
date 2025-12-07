/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.actions.util.CharbossDoCardQueueAction;
/*    */ import charbosses.cards.AbstractCustomBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Wound;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMalfunctioning
/*    */   extends AbstractCustomBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Malfunctioning";
/*    */   private static final CardStrings cardStrings;
/* 27 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/malfunctioning.png");
/*    */   
/*    */   static {
/* 30 */     cardStrings = CardCrawlGame.languagePack.getCardStrings("downfall:Malfunctioning");
/*    */   }
/*    */   
/*    */   public EnMalfunctioning() {
/* 34 */     super("downfall_Charboss:Malfunctioning", cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/* 35 */     this.exhaust = true;
/* 36 */     this.isEthereal = true;
/* 37 */     this.portrait = TextureLoader.getTextureAsAtlasRegion(IMG_PATH);
/* 38 */     this.portraitImg = TextureLoader.getTexture(IMG_PATH);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 44 */     if (this.dontTriggerOnUseCard) {
/* 45 */       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Wound(), 1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 51 */     addToBot((AbstractGameAction)new SetDontTriggerAction((AbstractCard)this, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 56 */     this.dontTriggerOnUseCard = true;
/* 57 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CharbossDoCardQueueAction((AbstractCard)this));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 70 */     return (AbstractCard)new EnMalfunctioning();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnMalfunctioning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */