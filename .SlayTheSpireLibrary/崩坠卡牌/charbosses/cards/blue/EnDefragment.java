/*    */ package charbosses.cards.blue;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnDefragment
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Defragment";
/*    */   
/*    */   public EnDefragment() {
/* 26 */     super("downfall_Charboss:Defragment", cardStrings.NAME, "blue/power/defragment", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 27 */     this.baseMagicNumber = 1;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.alwaysDisplayText = true;
/* 30 */     this.focusGeneratedIfPlayed = this.magicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     ArchetypeAct3OrbsNewAge.resetPretendFocus();
/* 35 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new FocusPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(1);
/* 42 */       this.focusGeneratedIfPlayed = this.magicNumber;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 49 */     return 50;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new EnDefragment();
/*    */   }
/*    */ 
/*    */   
/* 57 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defragment");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnDefragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */