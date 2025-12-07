/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ public class EnTranquility
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:ClearTheMind";
/*    */   
/*    */   public EnTranquility() {
/* 21 */     super("downfall_Charboss:ClearTheMind", cardStrings.NAME, "purple/skill/tranquility", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 22 */     this.exhaust = true;
/* 23 */     this.selfRetain = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Calm"));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 32 */     if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnCalmStance) return 4; 
/* 33 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 45 */     return (AbstractCard)new EnTranquility();
/*    */   }
/*    */ 
/*    */   
/* 49 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ClearTheMind");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnTranquility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */