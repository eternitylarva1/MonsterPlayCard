/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.cards.status.EnWound;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Wound;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnPowerThrough
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:PowerThrough";
/*    */   
/*    */   public EnPowerThrough() {
/* 22 */     super("downfall_Charboss:PowerThrough", cardStrings.NAME, "red/skill/power_through", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 23 */     this.baseBlock = 15;
/* 24 */     this.cardsToPreview = (AbstractCard)new Wound();
/* 25 */     addWoundsToPlayer = false;
/*    */   }
/*    */   
/*    */   public EnPowerThrough(boolean wounds) {
/* 29 */     this();
/* 30 */     addWoundsToPlayer = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnWound(), 2));
/* 35 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 36 */     if (addWoundsToPlayer) {
/* 37 */       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Wound(), 2));
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       upgradeBlock(5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return (AbstractCard)new EnPowerThrough();
/*    */   }
/*    */ 
/*    */   
/* 54 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Power Through");
/*    */   private static boolean addWoundsToPlayer;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnPowerThrough.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */