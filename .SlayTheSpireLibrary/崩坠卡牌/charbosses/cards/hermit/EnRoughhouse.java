/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import hermit.cards.Roughhouse;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnRoughhouse
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Roughhouse";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Roughhouse.ID);
/*    */   
/*    */   public EnRoughhouse() {
/* 26 */     super("downfall_Charboss:Roughhouse", cardStrings.NAME, "hermitResources/images/cards/roughhouse.png", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEFEND);
/* 27 */     this.baseDamage = 24;
/* 28 */     this.baseBlock = 20;
/* 29 */     modifyCostForCombat(-3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 35 */     AbstractPower concentration = this.owner.getPower("downfall:HermitConcentrationPower");
/* 36 */     if (concentration != null && concentration.amount > 0) {
/* 37 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 44 */     this.intentActive = false;
/* 45 */     this.intent = AbstractMonster.Intent.ATTACK;
/* 46 */     createIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(6);
/* 54 */       upgradeBlock(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return (AbstractCard)new EnRoughhouse();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnRoughhouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */