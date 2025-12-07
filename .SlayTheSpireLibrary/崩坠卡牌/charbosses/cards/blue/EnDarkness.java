/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.actions.unique.EnemyDarkImpulseAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.EnemyDark;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnDarkness
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Darkness";
/*    */   
/*    */   public EnDarkness() {
/* 30 */     super("downfall_Charboss:Darkness", cardStrings.NAME, "blue/skill/darkness", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 31 */     this.showEvokeValue = true;
/* 32 */     this.showEvokeOrbCount = 1;
/* 33 */     this.baseMagicNumber = 1;
/* 34 */     this.magicNumber = 1;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyDark()));
/* 39 */     if (this.upgraded) {
/* 40 */       addToBot((AbstractGameAction)new EnemyDarkImpulseAction());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
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
/* 60 */     return (AbstractCard)new EnDarkness();
/*    */   }
/*    */ 
/*    */   
/* 64 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Darkness");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnDarkness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */