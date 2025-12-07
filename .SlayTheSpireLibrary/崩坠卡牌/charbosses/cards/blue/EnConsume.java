/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyDecreaseMaxOrbAction;
/*    */ import charbosses.bosses.Defect.NewAge.ArchetypeAct3OrbsNewAge;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FocusPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnConsume
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Consume";
/*    */   
/*    */   public EnConsume() {
/* 24 */     super("downfall_Charboss:Consume", cardStrings.NAME, "blue/skill/consume", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 25 */     this.baseMagicNumber = 2;
/* 26 */     this.magicNumber = this.baseMagicNumber;
/* 27 */     this.focusGeneratedIfPlayed = this.magicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     ArchetypeAct3OrbsNewAge.resetPretendFocus();
/* 32 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new FocusPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/* 33 */     addToBot((AbstractGameAction)new EnemyDecreaseMaxOrbAction(1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeMagicNumber(1);
/* 40 */       this.focusGeneratedIfPlayed = this.magicNumber;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 47 */     return 50;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return (AbstractCard)new EnConsume();
/*    */   }
/*    */ 
/*    */   
/* 55 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Consume");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnConsume.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */