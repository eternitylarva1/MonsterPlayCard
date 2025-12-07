/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.actions.util.CharbossDoCardQueueAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnRegret
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Regret";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Regret");
/*    */ 
/*    */   
/*    */   public EnRegret() {
/* 24 */     super("downfall_Charboss:Regret", cardStrings.NAME, "curse/regret", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/* 25 */     this.magicValue = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     if (this.dontTriggerOnUseCard) {
/* 31 */       addToTop((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 37 */     this.dontTriggerOnUseCard = true;
/* 38 */     int size = AbstractCharBoss.boss.hand.size();
/* 39 */     this.baseMagicNumber = size;
/* 40 */     this.magicNumber = size;
/* 41 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CharbossDoCardQueueAction((AbstractCard)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return (AbstractCard)new EnRegret();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnRegret.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */