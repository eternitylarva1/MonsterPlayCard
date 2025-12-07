/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.common.EnemyNotStanceCheckAction;
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnNeutralStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnEmptyFist
/*    */   extends AbstractStanceChangeCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:EmptyFist";
/*    */   
/*    */   public EnEmptyFist() {
/* 28 */     super("downfall_Charboss:EmptyFist", cardStrings.NAME, "purple/attack/empty_fist", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.ATTACK);
/* 29 */     this.baseDamage = 9;
/* 30 */     this.tags.add(AbstractCard.CardTags.EMPTY);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 35 */     addToBot((AbstractGameAction)new EnemyNotStanceCheckAction((AbstractGameAction)new VFXAction((AbstractGameEffect)new EmptyStanceEffect(m.hb.cX, m.hb.cY), 0.1F)));
/* 36 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Neutral"));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 40 */     return (AbstractCard)new EnEmptyFist();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 44 */     if (!this.upgraded) {
/* 45 */       upgradeName();
/* 46 */       upgradeDamage(5);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("EmptyFist");
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance previousStance) {
/* 57 */     return (AbstractEnemyStance)new EnNeutralStance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnEmptyFist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */