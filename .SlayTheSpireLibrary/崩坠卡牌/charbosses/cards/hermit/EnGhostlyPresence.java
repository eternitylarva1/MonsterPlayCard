/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import hermit.cards.GhostlyPresence;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnGhostlyPresence
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:GhostlyPresence";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(GhostlyPresence.ID);
/*    */   
/*    */   public EnGhostlyPresence() {
/* 26 */     super("downfall_Charboss:GhostlyPresence", cardStrings.NAME, "hermitResources/images/cards/ghostly_presence.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_DEBUFF);
/* 27 */     this.baseBlock = 8;
/* 28 */     this.baseMagicNumber = this.magicNumber = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 34 */     AbstractPower concentration = this.owner.getPower("downfall:HermitConcentrationPower");
/* 35 */     if (concentration != null && concentration.amount > 0) {
/* 36 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 42 */     this.intentActive = false;
/* 43 */     this.intent = AbstractMonster.Intent.DEFEND;
/* 44 */     createIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeBlock(1);
/* 52 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return (AbstractCard)new EnGhostlyPresence();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnGhostlyPresence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */