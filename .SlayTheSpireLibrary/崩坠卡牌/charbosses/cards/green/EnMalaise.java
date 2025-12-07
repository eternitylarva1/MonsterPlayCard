/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyMalaiseAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.ui.EnemyEnergyPanel;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMalaise
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Bane";
/* 27 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Malaise");
/*    */ 
/*    */   
/*    */   public EnMalaise() {
/* 31 */     super("downfall_Charboss:Bane", cardStrings.NAME, "green/skill/malaise", -1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
/* 32 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new EnemyMalaiseAction((AbstractCharBoss)m, this.upgraded, this.freeToPlayOnce, EnemyEnergyPanel.totalCount));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 42 */     return 100;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 50 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnMalaise();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnMalaise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */