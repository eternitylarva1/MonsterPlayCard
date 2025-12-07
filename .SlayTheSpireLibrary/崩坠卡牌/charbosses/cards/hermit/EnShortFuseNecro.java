/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
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
/*    */ import hermit.cards.Shortfuse;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnShortFuseNecro
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:ShortFuse";
/* 31 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Shortfuse.ID);
/*    */   
/*    */   public EnShortFuseNecro() {
/* 34 */     super("downfall_Charboss:ShortFuse", cardStrings.NAME, "hermitResources/images/cards/short_fuse.png", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 35 */     this.baseDamage = 18;
/* 36 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
/* 42 */     AbstractBossCard copy = new EnShortFuse();
/* 43 */     copy.purgeOnUse = true;
/* 44 */     if (this.upgraded) copy.upgrade(); 
/* 45 */     copy.freeToPlayOnce = true;
/* 46 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)copy, 1));
/*    */   }
/*    */   
/*    */   public void updateCostToSpecific(int specific) {
/* 50 */     setCostForTurn(specific);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return (AbstractCard)new EnShortFuseNecro();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnShortFuseNecro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */