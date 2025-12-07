/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyAnimateOrbAction;
/*    */ import charbosses.actions.orb.EnemyEvokeOrbAction;
/*    */ import charbosses.actions.orb.EnemyEvokeWithoutRemovingOrbAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMulticast
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Multi-Cast";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Multi-Cast");
/*    */   private int cost;
/*    */   
/*    */   public EnMulticast() {
/* 25 */     this(2);
/*    */   }
/*    */   
/*    */   public EnMulticast(int inCost) {
/* 29 */     super("downfall_Charboss:Multi-Cast", cardStrings.NAME, "blue/skill/multicast", inCost, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 30 */     this.cost = inCost;
/* 31 */     this.showEvokeValue = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     for (int i = 0; i < this.cost - 1; i++) {
/* 37 */       addToBot((AbstractGameAction)new EnemyAnimateOrbAction(1));
/* 38 */       addToBot((AbstractGameAction)new EnemyEvokeWithoutRemovingOrbAction(1));
/*    */     } 
/* 40 */     addToBot((AbstractGameAction)new EnemyAnimateOrbAction(1));
/* 41 */     addToBot((AbstractGameAction)new EnemyEvokeOrbAction(1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 45 */     if (!this.upgraded) {
/* 46 */       upgradeName();
/* 47 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 48 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new EnMulticast();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnMulticast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */