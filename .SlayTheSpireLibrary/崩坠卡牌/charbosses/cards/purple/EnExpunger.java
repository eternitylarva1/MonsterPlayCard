/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyExpungeVFXAction;
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
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnExpunger
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Expunger";
/*    */   
/*    */   public EnExpunger() {
/* 27 */     super("downfall_Charboss:Expunger", cardStrings.NAME, "colorless/attack/expunger", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 28 */     this.baseDamage = 9;
/* 29 */     this.intentMultiAmt = 3;
/* 30 */     this.baseMagicNumber = this.magicNumber = 3;
/* 31 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new EnemyExpungeVFXAction(p));
/* 37 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 38 */     addToBot((AbstractGameAction)new EnemyExpungeVFXAction(p));
/* 39 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 40 */     addToBot((AbstractGameAction)new EnemyExpungeVFXAction(p));
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 45 */     return (AbstractCard)new EnExpunger();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 58 */     return autoPriority() * 2;
/*    */   }
/*    */ 
/*    */   
/* 62 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Expunger");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnExpunger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */