/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyApotheosisAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnApotheosis
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Apotheosis";
/*    */   
/*    */   public EnApotheosis() {
/* 24 */     super("downfall_Charboss:Apotheosis", cardStrings.NAME, "colorless/skill/apotheosis", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 25 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new EnemyApotheosisAction());
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 33 */     if (!this.upgraded) {
/* 34 */       upgradeName();
/* 35 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 42 */     return 100;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return (AbstractCard)new EnApotheosis();
/*    */   }
/*    */ 
/*    */   
/* 50 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Apotheosis");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnApotheosis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */