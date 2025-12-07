/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
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
/*    */ 
/*    */ public class EnTurbo
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Turbo";
/*    */   
/*    */   public EnTurbo() {
/* 27 */     super("downfall_Charboss:Turbo", cardStrings.NAME, "blue/skill/turbo", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/*    */     
/* 29 */     this.baseMagicNumber = 2;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.energyGeneratedIfPlayed = this.magicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new EnemyGainEnergyAction(this.magicNumber));
/* 37 */     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new VoidCard(), 1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       upgradeMagicNumber(1);
/* 44 */       this.energyGeneratedIfPlayed = this.magicNumber;
/* 45 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 46 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 53 */     return autoPriority() * 2;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return (AbstractCard)new EnTurbo();
/*    */   }
/*    */ 
/*    */   
/* 61 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Turbo");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnTurbo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */