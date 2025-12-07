/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.general.EnemyEnergizedPower;
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
/*    */ public class EnOutmaneuver
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Outmaneuver";
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Outmaneuver");
/*    */ 
/*    */   
/*    */   public EnOutmaneuver() {
/* 23 */     super("downfall_Charboss:Outmaneuver", cardStrings.NAME, "green/skill/outmaneuver", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 28 */     if (!this.upgraded) {
/* 29 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyEnergizedPower((AbstractCreature)m, 2), 2));
/*    */     } else {
/* 31 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyEnergizedPower((AbstractCreature)m, 3), 3));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 37 */     return this.upgraded ? 14 : 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 45 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return (AbstractCard)new EnOutmaneuver();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnOutmaneuver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */