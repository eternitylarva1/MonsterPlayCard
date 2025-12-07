/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.AbstractEnemyOrb;
/*    */ import charbosses.orbs.EnemyLightning;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnZap
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Zap";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Zap");
/*    */ 
/*    */   
/*    */   public EnZap() {
/* 26 */     super("downfall_Charboss:Zap", cardStrings.NAME, "blue/skill/zap", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 27 */     this.showEvokeValue = true;
/* 28 */     this.showEvokeOrbCount = 1;
/* 29 */     this.baseMagicNumber = 1;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.alwaysDisplayText = true;
/*    */   }
/*    */   
/*    */   public static int getFocusAmountSafe() {
/* 35 */     if (AbstractCharBoss.boss.hasPower("Focus")) {
/* 36 */       return (AbstractCharBoss.boss.getPower("Focus")).amount;
/*    */     }
/* 38 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String overrideIntentText() {
/* 43 */     return "(" + (3 + AbstractEnemyOrb.masterPretendFocus + getFocusAmountSafe()) + ")";
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 47 */     for (int i = 0; i < this.magicNumber; i++) {
/* 48 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 54 */     return 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 58 */     if (!this.upgraded) {
/* 59 */       upgradeName();
/* 60 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 65 */     return (AbstractCard)new EnZap();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnZap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */