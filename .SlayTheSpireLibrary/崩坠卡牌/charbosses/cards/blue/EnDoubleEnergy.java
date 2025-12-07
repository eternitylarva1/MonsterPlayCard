/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ public class EnDoubleEnergy
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Double Energy";
/*    */   
/*    */   public EnDoubleEnergy() {
/* 21 */     this(2);
/*    */   }
/*    */   
/*    */   public EnDoubleEnergy(int eGain) {
/* 25 */     super("downfall_Charboss:Double Energy", cardStrings.NAME, "blue/skill/double_energy", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/*    */     
/* 27 */     this.baseMagicNumber = eGain;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.energyGeneratedIfPlayed = this.magicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     if (m instanceof AbstractCharBoss) {
/* 34 */       addToBot((AbstractGameAction)new EnemyGainEnergyAction(((AbstractCharBoss)m).energyPanel.getCurrentEnergy()));
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 47 */     return autoPriority() * 2;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return (AbstractCard)new EnDoubleEnergy();
/*    */   }
/*    */ 
/*    */   
/* 55 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Double Energy");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnDoubleEnergy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */