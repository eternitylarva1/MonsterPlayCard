/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyLimitBreakAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnLimitBreak
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Limit Break";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Limit Break");
/*    */ 
/*    */   
/*    */   public EnLimitBreak() {
/* 25 */     super("downfall_Charboss:Limit Break", cardStrings.NAME, "red/skill/limit_break", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 26 */     this.exhaust = true;
/* 27 */     this.limit = 1;
/* 28 */     this.tags.add(downfallMod.CHARBOSS_SETUP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new EnemyLimitBreakAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 38 */     return (AbstractCard)new EnLimitBreak();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 43 */     if (AbstractCharBoss.boss != null) {
/* 44 */       int v = 0;
/* 45 */       if (AbstractCharBoss.boss.hasPower("Strength")) {
/* 46 */         v = (AbstractCharBoss.boss.getPower("Strength")).amount;
/*    */       }
/* 48 */       for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/* 49 */         if (c.cardID == "downfall_Charboss:Flex") {
/* 50 */           v += c.magicNumber;
/*    */         }
/*    */       } 
/* 53 */       return v * 3;
/*    */     } 
/* 55 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 60 */     if (!this.upgraded) {
/* 61 */       upgradeName();
/* 62 */       this.exhaust = false;
/* 63 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 64 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnLimitBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */