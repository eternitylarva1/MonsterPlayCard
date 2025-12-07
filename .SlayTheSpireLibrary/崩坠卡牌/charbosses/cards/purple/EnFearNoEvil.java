/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.powers.cardpowers.EnemyFearNoEvilPower;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnCalmStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnFearNoEvil
/*    */   extends AbstractStanceChangeCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:FearNoEvil";
/* 28 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FearNoEvil");
/*    */ 
/*    */   
/*    */   public EnFearNoEvil() {
/* 32 */     super("downfall_Charboss:FearNoEvil", cardStrings.NAME, "purple/attack/fear_no_evil", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 33 */     this.baseDamage = 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 39 */     System.out.println("Playing fear no evil: " + m.hasPower("Fear No Evil"));
/* 40 */     if (m.hasPower("Fear No Evil")) {
/* 41 */       EnemyFearNoEvilPower power = (EnemyFearNoEvilPower)m.getPower("Fear No Evil");
/* 42 */       if (power.isActive) {
/* 43 */         flash();
/* 44 */         addToBot((AbstractGameAction)new EnemyChangeStanceAction("Calm"));
/*    */       } 
/* 46 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)power));
/* 47 */       System.out.println("Removed Fear No Evil: " + m.hasPower("Fear No Evil"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 53 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 59 */     if (!this.upgraded) {
/* 60 */       upgradeName();
/* 61 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 67 */     return (AbstractCard)new EnFearNoEvil();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance previousStance) {
/* 72 */     if (this.owner.hasPower("Fear No Evil")) {
/* 73 */       EnemyFearNoEvilPower power = (EnemyFearNoEvilPower)this.owner.getPower("Fear No Evil");
/* 74 */       if (power.isActive) {
/* 75 */         return (AbstractEnemyStance)new EnCalmStance();
/*    */       }
/*    */     } 
/* 78 */     return previousStance;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnFearNoEvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */