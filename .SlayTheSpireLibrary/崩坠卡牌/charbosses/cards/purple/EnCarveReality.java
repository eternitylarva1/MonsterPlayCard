/*    */ package charbosses.cards.purple;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnCarveReality
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:CarveReality";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("CarveReality");
/*    */   public boolean willUseSmite;
/*    */   
/*    */   public EnCarveReality(boolean willUseSmite) {
/* 27 */     super("downfall_Charboss:CarveReality", cardStrings.NAME, "purple/attack/carve_reality", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 28 */     this.baseDamage = 6;
/* 29 */     this.willUseSmite = willUseSmite;
/* 30 */     this.cardsToPreview = (AbstractCard)new EnSmite();
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 36 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnSmite(), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public int customIntentModifiedDamage() {
/* 41 */     int extraDamage = 0;
/*    */     
/* 43 */     if (this.owner.hasPower("MasterRealityPower")) {
/* 44 */       extraDamage = 6;
/*    */     }
/* 46 */     return extraDamage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return (AbstractCard)new EnCarveReality(this.willUseSmite);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnCarveReality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */