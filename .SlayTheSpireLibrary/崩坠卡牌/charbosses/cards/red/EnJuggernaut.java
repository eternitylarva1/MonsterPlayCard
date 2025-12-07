/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyJuggernautPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnJuggernaut
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Juggernaut";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Juggernaut");
/*    */ 
/*    */   
/*    */   public EnJuggernaut() {
/* 24 */     super("downfall_Charboss:Juggernaut", cardStrings.NAME, "red/power/juggernaut", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 25 */     this.baseMagicNumber = 5;
/* 26 */     this.magicNumber = this.baseMagicNumber;
/* 27 */     this.tags.add(downfallMod.CHARBOSS_SETUP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyJuggernautPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 45 */     return 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return (AbstractCard)new EnJuggernaut();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnJuggernaut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */