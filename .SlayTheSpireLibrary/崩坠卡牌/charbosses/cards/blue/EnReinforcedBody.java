/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.ui.EnemyEnergyPanel;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnReinforcedBody
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Reinforced Body";
/* 24 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reinforced Body");
/*    */   int cost;
/*    */   
/*    */   public EnReinforcedBody() {
/* 28 */     this(2);
/*    */   }
/*    */   
/*    */   public EnReinforcedBody(int inCost) {
/* 32 */     super("downfall_Charboss:Reinforced Body", cardStrings.NAME, "blue/skill/reinforced_body", inCost, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.DEFEND);
/* 33 */     this.baseBlock = 7;
/* 34 */     this.cost = inCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     int realCost = this.owner.energyPanel.getCurrentEnergy();
/* 40 */     for (int i = 0; i < realCost; i++) {
/* 41 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */     }
/*    */     
/* 44 */     EnemyEnergyPanel.useEnergy(realCost - this.cost);
/* 45 */     this.owner.hand.glowCheck();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return (AbstractCard)new EnReinforcedBody();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnReinforcedBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */