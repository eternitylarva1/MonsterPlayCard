/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.actions.util.CharbossDoCardQueueAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class EnDoubt extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Doubt";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Doubt");
/*    */ 
/*    */   
/*    */   public EnDoubt() {
/* 25 */     super("downfall_Charboss:Doubt", cardStrings.NAME, "curse/doubt", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     if (this.dontTriggerOnUseCard) {
/* 31 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new WeakPower((AbstractCreature)AbstractCharBoss.boss, 1, true), 1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 37 */     addToBot((AbstractGameAction)new SetDontTriggerAction((AbstractCard)this, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 42 */     this.dontTriggerOnUseCard = true;
/* 43 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CharbossDoCardQueueAction((AbstractCard)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 52 */     return (AbstractCard)new EnDoubt();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnDoubt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */