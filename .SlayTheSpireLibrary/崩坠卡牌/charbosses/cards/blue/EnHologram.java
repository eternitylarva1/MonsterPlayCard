/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnHologram extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Hologram";
/*    */   
/*    */   public EnHologram() {
/* 17 */     super("downfall_Charboss:Hologram", cardStrings.NAME, "blue/skill/hologram", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
/* 18 */     this.baseBlock = 3;
/* 19 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 24 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 28 */     if (!this.upgraded) {
/* 29 */       upgradeName();
/* 30 */       this.exhaust = false;
/* 31 */       upgradeBlock(2);
/* 32 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 33 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 39 */     return (AbstractCard)new EnHologram();
/*    */   }
/*    */ 
/*    */   
/* 43 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Hologram");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */