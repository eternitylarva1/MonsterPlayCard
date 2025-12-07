/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import hermit.cards.LowProfile;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnLowProfile
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:LowProfile";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(LowProfile.ID);
/*    */   
/*    */   public EnLowProfile() {
/* 23 */     super("downfall_Charboss:LowProfile", cardStrings.NAME, "hermitResources/images/cards/low_profile.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 24 */     this.baseBlock = 7;
/* 25 */     this.baseMagicNumber = this.magicNumber = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   private int countDebuffs() {
/* 30 */     int debuffs = 0;
/* 31 */     for (AbstractPower pow : this.owner.powers) {
/* 32 */       if (pow.type == AbstractPower.PowerType.DEBUFF)
/* 33 */         debuffs++; 
/*    */     } 
/* 35 */     return debuffs;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     applyPowers();
/* 42 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void applyPowers() {
/* 46 */     int realBaseBlock = this.baseBlock;
/*    */     
/* 48 */     this.baseBlock += this.magicNumber * countDebuffs();
/* 49 */     super.applyPowers();
/*    */     
/* 51 */     this.baseBlock = realBaseBlock;
/* 52 */     this.isBlockModified = (this.block != this.baseBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 58 */     if (!this.upgraded) {
/* 59 */       upgradeName();
/* 60 */       upgradeBlock(2);
/* 61 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return (AbstractCard)new EnLowProfile();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnLowProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */