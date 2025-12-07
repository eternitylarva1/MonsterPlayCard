/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyMantraPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnWorship
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Worship";
/*    */   
/*    */   public EnWorship() {
/* 24 */     super("downfall_Charboss:Worship", cardStrings.NAME, "purple/skill/worship", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 25 */     this.baseMagicNumber = 5;
/* 26 */     this.magicNumber = 5;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyMantraPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       this.selfRetain = true;
/* 37 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 38 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 45 */     return 8;
/*    */   }
/*    */   
/*    */   public void initializeDescription() {
/* 49 */     super.initializeDescription();
/* 50 */     this.keywords.add(GameDictionary.ENLIGHTENMENT.NAMES[0].toLowerCase());
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new EnWorship();
/*    */   }
/*    */ 
/*    */   
/* 58 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Worship");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnWorship.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */