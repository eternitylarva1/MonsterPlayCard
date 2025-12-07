/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnLessonLearned extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:LessonLearned";
/*    */   
/*    */   public EnLessonLearned() {
/* 18 */     super("downfall_Charboss:LessonLearned", cardStrings.NAME, "purple/attack/lessons_learned", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 19 */     this.baseDamage = 10;
/* 20 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 24 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 28 */     return (AbstractCard)new EnLessonLearned();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 40 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("LessonLearned");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnLessonLearned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */