/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
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
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnLegSweep
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Leg Sweep";
/* 25 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Leg Sweep");
/*    */ 
/*    */   
/*    */   public EnLegSweep() {
/* 29 */     super("downfall_Charboss:Leg Sweep", cardStrings.NAME, "green/skill/leg_sweep", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.DEFEND_DEBUFF);
/* 30 */     this.baseBlock = 11;
/* 31 */     this.baseMagicNumber = this.magicNumber = 2;
/* 32 */     this.artifactConsumedIfPlayed = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 37 */     return autoPriority() + 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/* 43 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeMagicNumber(1);
/* 51 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return (AbstractCard)new EnLegSweep();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnLegSweep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */