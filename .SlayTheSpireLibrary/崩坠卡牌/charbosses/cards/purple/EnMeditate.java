/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnMeditate
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Meditate";
/*    */   
/*    */   public EnMeditate() {
/* 20 */     super("downfall_Charboss:Meditate", cardStrings.NAME, "purple/skill/meditate", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 21 */     this.baseBlock = 8;
/* 22 */     this.block = this.baseBlock;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 26 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Calm"));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 31 */     if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnCalmStance) return 4; 
/* 32 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 39 */     return (AbstractCard)new EnMeditate();
/*    */   }
/*    */ 
/*    */   
/* 43 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Meditate");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnMeditate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */