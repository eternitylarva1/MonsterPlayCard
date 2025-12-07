/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
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
/*    */ public class EnMiracle
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Miracle";
/*    */   
/*    */   public EnMiracle() {
/* 21 */     super("downfall_Charboss:Miracle", cardStrings.NAME, "colorless/skill/miracle", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 22 */     this.exhaust = true;
/* 23 */     this.selfRetain = true;
/* 24 */     this.energyGeneratedIfPlayed = 1;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 28 */     addToBot((AbstractGameAction)new EnemyGainEnergyAction(1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 35 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 42 */     return 20;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return (AbstractCard)new EnMiracle();
/*    */   }
/*    */ 
/*    */   
/* 50 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Miracle");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnMiracle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */