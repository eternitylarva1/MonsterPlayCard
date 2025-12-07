/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.AbstractEnemyOrb;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnChaos
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Chaos";
/*    */   
/*    */   public EnChaos() {
/* 28 */     super("downfall_Charboss:Chaos", cardStrings.NAME, "blue/skill/chaos", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 29 */     this.showEvokeValue = true;
/* 30 */     this.showEvokeOrbCount = 1;
/* 31 */     this.baseMagicNumber = 1;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     if (this.upgraded) {
/* 37 */       addToBot((AbstractGameAction)new EnemyChannelAction(AbstractEnemyOrb.getRandomOrb(true)));
/*    */     }
/*    */     
/* 40 */     addToBot((AbstractGameAction)new EnemyChannelAction(AbstractEnemyOrb.getRandomOrb(true)));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 44 */     if (!this.upgraded) {
/* 45 */       upgradeName();
/* 46 */       upgradeMagicNumber(1);
/* 47 */       this.showEvokeOrbCount = 2;
/* 48 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 49 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 56 */     return 10;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new EnChaos();
/*    */   }
/*    */ 
/*    */   
/* 64 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Chaos");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnChaos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */