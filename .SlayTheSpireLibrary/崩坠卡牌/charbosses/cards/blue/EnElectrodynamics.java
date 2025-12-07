/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.AbstractEnemyOrb;
/*    */ import charbosses.orbs.EnemyLightning;
/*    */ import charbosses.powers.cardpowers.EnemyElectroPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnElectrodynamics
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Electrodynamics";
/* 31 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Electrodynamics");
/*    */ 
/*    */   
/*    */   public EnElectrodynamics() {
/* 35 */     super("downfall_Charboss:Electrodynamics", cardStrings.NAME, "blue/power/electrodynamics", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 36 */     this.showEvokeValue = true;
/* 37 */     this.showEvokeOrbCount = 2;
/* 38 */     this.baseMagicNumber = 2;
/* 39 */     this.magicNumber = this.baseMagicNumber;
/* 40 */     this.alwaysDisplayText = true;
/*    */   }
/*    */   
/*    */   public static int getFocusAmountSafe() {
/* 44 */     if (AbstractCharBoss.boss.hasPower("Focus")) {
/* 45 */       return (AbstractCharBoss.boss.getPower("Focus")).amount;
/*    */     }
/* 47 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String overrideIntentText() {
/* 52 */     if (AbstractCharBoss.boss.hasPower("Storm")) {
/* 53 */       int count = this.magicNumber + (AbstractCharBoss.boss.getPower("Storm")).amount;
/* 54 */       return "(" + (3 + AbstractEnemyOrb.masterPretendFocus + getFocusAmountSafe()) + "×" + count + ")";
/*    */     } 
/* 56 */     return "(" + (3 + AbstractEnemyOrb.masterPretendFocus + getFocusAmountSafe()) + "×" + this.magicNumber + ")";
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 60 */     if (!p.hasPower("Electro")) {
/* 61 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyElectroPower((AbstractCreature)m)));
/*    */     }
/* 63 */     for (int i = 0; i < this.magicNumber; i++) {
/* 64 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 70 */     return 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 74 */     if (!this.upgraded) {
/* 75 */       upgradeName();
/* 76 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 81 */     return (AbstractCard)new EnElectrodynamics();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnElectrodynamics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */