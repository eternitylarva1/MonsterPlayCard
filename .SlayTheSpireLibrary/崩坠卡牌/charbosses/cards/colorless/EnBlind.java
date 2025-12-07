/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnBlind
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Blind";
/*    */   
/*    */   public EnBlind() {
/* 30 */     super("downfall_Charboss:Blind", cardStrings.NAME, "colorless/skill/blind", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.DEBUFF);
/* 31 */     this.baseMagicNumber = this.magicNumber = 2;
/*    */     
/* 33 */     this.artifactConsumedIfPlayed = 1;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       this.target = AbstractCard.CardTarget.ALL_ENEMY;
/* 44 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 45 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String overrideIntentText() {
/* 51 */     if (AbstractCharBoss.boss.hasPower("Sadistic")) {
/* 52 */       int count = this.magicNumber + (AbstractCharBoss.boss.getPower("Sadistic")).amount;
/* 53 */       return "(" + count + ")";
/*    */     } 
/* 55 */     return super.overrideIntentText();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 60 */     return 10;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return (AbstractCard)new EnBlind();
/*    */   }
/*    */ 
/*    */   
/* 68 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blind");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnBlind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */