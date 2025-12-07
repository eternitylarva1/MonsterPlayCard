/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.cards.colorless.EnShiv;
/*    */ import charbosses.powers.cardpowers.EnemyInfiniteBladesPower;
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
/*    */ public class EnInfiniteBlades
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Infinite Blades";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Infinite Blades");
/*    */ 
/*    */   
/*    */   public EnInfiniteBlades() {
/* 25 */     super("downfall_Charboss:Infinite Blades", cardStrings.NAME, "green/power/infinite_blades", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 26 */     this.cardsToPreview = (AbstractCard)new EnShiv();
/* 27 */     this.limit = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 32 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyInfiniteBladesPower((AbstractCreature)m, 1), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       this.isInnate = true;
/* 45 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 46 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getValue() {
/* 52 */     return (new EnShiv()).getValue() * 3 + 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUpgradeValue() {
/* 57 */     return (new EnShiv()).getValue() * 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return (AbstractCard)new EnInfiniteBlades();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnInfiniteBlades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */