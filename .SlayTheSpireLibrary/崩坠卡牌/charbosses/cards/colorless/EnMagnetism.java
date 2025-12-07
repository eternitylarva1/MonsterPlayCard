/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyMagnetismPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMagnetism
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Magnetism";
/*    */   
/*    */   public EnMagnetism() {
/* 24 */     super("downfall_Charboss:Magnetism", cardStrings.NAME, "colorless/power/magnetism", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 25 */     this.baseMagicNumber = 1;
/* 26 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyMagnetismPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 43 */     return 20;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new EnMagnetism();
/*    */   }
/*    */ 
/*    */   
/* 51 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Magnetism");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnMagnetism.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */