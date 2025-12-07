/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyBarrageAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnBarrage
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Barrage";
/*    */   
/*    */   public EnBarrage() {
/* 21 */     this(1);
/*    */   }
/*    */   
/*    */   public EnBarrage(int hitCount) {
/* 25 */     super("downfall_Charboss:Barrage", cardStrings.NAME, "blue/attack/barrage", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 26 */     this.baseDamage = 4;
/* 27 */     this.isMultiDamage = true;
/* 28 */     this.magicNumber = hitCount;
/* 29 */     this.intentMultiAmt = this.magicNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyPowers() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 44 */     addToBot((AbstractGameAction)new EnemyBarrageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, DamageInfo.DamageType.NORMAL)));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 57 */     return autoPriority() * this.owner.orbs.size();
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return (AbstractCard)new EnBarrage();
/*    */   }
/*    */ 
/*    */   
/* 65 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Barrage");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnBarrage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */