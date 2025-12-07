/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.EnemyPlasma;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnFusion
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Fusion";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Fusion");
/*    */   
/*    */   public EnFusion(boolean upgraded) {
/* 26 */     this();
/* 27 */     if (upgraded)
/* 28 */       upgrade(); 
/*    */   }
/*    */   
/*    */   public EnFusion() {
/* 32 */     super("downfall_Charboss:Fusion", cardStrings.NAME, "blue/skill/fusion", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 33 */     this.showEvokeValue = true;
/* 34 */     this.showEvokeOrbCount = 1;
/* 35 */     this.baseMagicNumber = 1;
/* 36 */     this.magicNumber = this.baseMagicNumber;
/* 37 */     this.alwaysDisplayText = true;
/*    */   }
/*    */   public EnFusion(boolean upgraded, boolean text) {
/* 40 */     super("downfall_Charboss:Fusion", cardStrings.NAME, "blue/skill/fusion", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 41 */     this.showEvokeValue = true;
/* 42 */     this.showEvokeOrbCount = 1;
/* 43 */     this.baseMagicNumber = 1;
/* 44 */     this.magicNumber = this.baseMagicNumber;
/* 45 */     this.alwaysDisplayText = text;
/* 46 */     if (upgraded)
/* 47 */       upgrade(); 
/*    */   }
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 50 */     for (int i = 0; i < this.magicNumber; i++) {
/* 51 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyPlasma()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 57 */     return 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 61 */     if (!this.upgraded) {
/* 62 */       upgradeName();
/* 63 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return (AbstractCard)new EnFusion();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnFusion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */