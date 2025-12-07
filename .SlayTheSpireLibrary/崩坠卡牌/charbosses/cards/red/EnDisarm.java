/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnDisarm
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Disarm";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Disarm");
/*    */ 
/*    */   
/*    */   public EnDisarm() {
/* 25 */     super("downfall_Charboss:Disarm", cardStrings.NAME, "red/skill/disarm", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
/* 26 */     this.baseMagicNumber = 2;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/* 28 */     this.exhaust = true;
/* 29 */     this.limit = 1;
/* 30 */     this.tags.add(downfallMod.CHARBOSS_SETUP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)p, -this.magicNumber), -this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 40 */     return 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUpgrade() {
/* 45 */     return AbstractCharBoss.finishedSetup;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 50 */     if (!this.upgraded) {
/* 51 */       upgradeName();
/* 52 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 58 */     return (AbstractCard)new EnDisarm();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnDisarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */