/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.common.EnemyExhaustAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnTrueGrit
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:True Grit";
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("True Grit");
/*    */ 
/*    */   
/*    */   public EnTrueGrit() {
/* 23 */     super("downfall_Charboss:True Grit", cardStrings.NAME, "red/skill/true_grit", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 24 */     this.baseBlock = 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 30 */     if (this.upgraded) {
/* 31 */       addToBot((AbstractGameAction)new EnemyExhaustAction(1, false));
/*    */     } else {
/* 33 */       addToBot((AbstractGameAction)new EnemyExhaustAction(1, true, false, false));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 40 */     int badCards = 0;
/* 41 */     for (AbstractCard c : hand) {
/* 42 */       if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
/* 43 */         badCards++;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     return autoPriority() + badCards * 5;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeBlock(2);
/* 56 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 57 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return (AbstractCard)new EnTrueGrit();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnTrueGrit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */