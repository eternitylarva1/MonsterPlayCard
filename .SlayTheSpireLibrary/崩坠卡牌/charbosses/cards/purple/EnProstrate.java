/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyMantraPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnProstrate
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Prostrate";
/*    */   
/*    */   public EnProstrate() {
/* 26 */     super("downfall_Charboss:Prostrate", cardStrings.NAME, "purple/skill/prostrate", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
/* 27 */     this.baseMagicNumber = 2;
/* 28 */     this.magicNumber = 2;
/* 29 */     this.baseBlock = 4;
/* 30 */     this.block = this.baseBlock;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyMantraPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/* 35 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeDescription() {
/* 47 */     super.initializeDescription();
/* 48 */     this.keywords.add(GameDictionary.ENLIGHTENMENT.NAMES[0].toLowerCase());
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 52 */     return (AbstractCard)new EnProstrate();
/*    */   }
/*    */ 
/*    */   
/* 56 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Prostrate");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnProstrate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */