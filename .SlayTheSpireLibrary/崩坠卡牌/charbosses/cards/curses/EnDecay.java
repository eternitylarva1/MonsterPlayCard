/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.actions.util.CharbossDoCardQueueAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnDecay
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Decay";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Decay");
/*    */ 
/*    */   
/*    */   public EnDecay() {
/* 25 */     super("downfall_Charboss:Decay", cardStrings.NAME, "curse/decay", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     if (this.dontTriggerOnUseCard) {
/* 31 */       addToTop((AbstractGameAction)new DamageAction((AbstractCreature)AbstractCharBoss.boss, new DamageInfo((AbstractCreature)AbstractCharBoss.boss, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 37 */     this.dontTriggerOnUseCard = true;
/* 38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CharbossDoCardQueueAction((AbstractCard)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new EnDecay();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnDecay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */