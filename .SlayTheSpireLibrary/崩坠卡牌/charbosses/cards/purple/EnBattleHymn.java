/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyBattleHymnPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Smite;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnBattleHymn
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:BattleHymn";
/*    */   
/*    */   public EnBattleHymn() {
/* 23 */     super("downfall_Charboss:BattleHymn", cardStrings.NAME, "purple/power/battle_hymn", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 24 */     this.baseMagicNumber = 1;
/* 25 */     this.magicNumber = this.baseMagicNumber;
/* 26 */     this.cardsToPreview = (AbstractCard)new Smite();
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyBattleHymnPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       this.isInnate = true;
/* 37 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 38 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 45 */     return 50;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnBattleHymn();
/*    */   }
/*    */ 
/*    */   
/* 53 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BattleHymn");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnBattleHymn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */