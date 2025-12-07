/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyHeadStompAction;
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
/*    */ 
/*    */ public class EnSashWhip
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:SashWhip";
/*    */   
/*    */   public EnSashWhip() {
/* 25 */     super("SashWhip", cardStrings.NAME, "purple/attack/sash_whip", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 26 */     this.baseDamage = 8;
/* 27 */     this.baseMagicNumber = 1;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 33 */     addToBot((AbstractGameAction)new EnemyHeadStompAction(p, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeDamage(2);
/* 41 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new EnSashWhip();
/*    */   }
/*    */ 
/*    */   
/* 51 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SashWhip");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnSashWhip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */