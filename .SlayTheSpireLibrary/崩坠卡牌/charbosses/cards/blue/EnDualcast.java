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
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnDualcast
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Dualcast";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Dualcast");
/*    */ 
/*    */   
/*    */   public EnDualcast() {
/* 25 */     super("downfall_Charboss:Dualcast", cardStrings.NAME, "blue/skill/dualcast", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 26 */     this.showEvokeValue = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 31 */     return 6;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new EnemyAnimateOrbAction(1));
/* 36 */     addToBot((AbstractGameAction)new EnemyEvokeWithoutRemovingOrbAction(1));
/* 37 */     addToBot((AbstractGameAction)new EnemyAnimateOrbAction(1));
/* 38 */     addToBot((AbstractGameAction)new EnemyEvokeOrbAction(1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnDualcast();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnDualcast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */