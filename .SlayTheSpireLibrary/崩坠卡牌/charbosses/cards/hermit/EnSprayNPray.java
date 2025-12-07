/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import hermit.cards.SprayPray;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnSprayNPray extends AbstractHermitBossCard {
/*    */   public static final String ID = "downfall_Charboss:SprayNPray";
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(SprayPray.ID);
/*    */   
/*    */   public EnSprayNPray() {
/* 20 */     super("downfall_Charboss:SprayNPray", cardStrings.NAME, "hermitResources/images/cards/spray_and_pray.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 21 */     this.baseDamage = 4;
/* 22 */     this.isMultiDamage = true;
/* 23 */     this.baseMagicNumber = this.magicNumber = 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 28 */     for (int i = 0; i < 3; i++) {
/* 29 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       upgradeDamage(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return (AbstractCard)new EnSprayNPray();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnSprayNPray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */