/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.common.EnemyReduceCostAction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnStreamline
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Streamline";
/*    */   
/*    */   public EnStreamline() {
/* 24 */     super("downfall_Charboss:Streamline", cardStrings.NAME, "blue/attack/streamline", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 25 */     this.baseDamage = 15;
/* 26 */     this.baseMagicNumber = 1;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */     
/* 33 */     addToBot((AbstractGameAction)new EnemyReduceCostAction((AbstractCard)this));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeDamage(5);
/* 40 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return (AbstractCard)new EnStreamline();
/*    */   }
/*    */ 
/*    */   
/* 50 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Streamline");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnStreamline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */