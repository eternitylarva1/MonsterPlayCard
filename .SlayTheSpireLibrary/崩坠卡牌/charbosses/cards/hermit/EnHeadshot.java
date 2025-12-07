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
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import hermit.cards.Headshot;
/*    */ import hermit.characters.hermit;
/*    */ import hermit.patches.EnumPatch;
/*    */ 
/*    */ 
/*    */ public class EnHeadshot
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Headshot";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Headshot.ID);
/*    */   
/*    */   public EnHeadshot() {
/* 25 */     super("downfall_Charboss:Headshot", cardStrings.NAME, "hermitResources/images/cards/headshot.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 26 */     this.baseDamage = 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), EnumPatch.HERMIT_GUN2));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 36 */     this.intentActive = false;
/* 37 */     createIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 42 */     super.calculateCardDamage(mo);
/* 43 */     AbstractPower concentration = this.owner.getPower("downfall:HermitConcentrationPower");
/* 44 */     if (concentration != null && concentration.amount > 0) {
/* 45 */       this.damage *= 2;
/*    */     }
/* 47 */     this.isDamageModified = (this.damage != this.baseDamage);
/* 48 */     this.intentDmg = this.damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return (AbstractCard)new EnHeadshot();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnHeadshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */