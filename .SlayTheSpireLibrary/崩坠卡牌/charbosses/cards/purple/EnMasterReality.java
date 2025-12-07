/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyMasterRealityPower;
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
/*    */ 
/*    */ public class EnMasterReality
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Devotion";
/*    */   
/*    */   public EnMasterReality() {
/* 25 */     super("downfall_Charboss:Devotion", cardStrings.NAME, "purple/power/master_reality", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyMasterRealityPower((AbstractCreature)m)));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 33 */     return (AbstractCard)new EnMasterReality();
/*    */   }
/*    */   
/*    */   public void initializeDescription() {
/* 37 */     super.initializeDescription();
/* 38 */     this.keywords.add(GameDictionary.ENLIGHTENMENT.NAMES[0].toLowerCase());
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 51 */     return 100;
/*    */   }
/*    */ 
/*    */   
/* 55 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("MasterReality");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnMasterReality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */