/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyFletchettesAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnFlechettes
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Flechettes";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flechettes");
/*    */ 
/*    */   
/*    */   public EnFlechettes() {
/* 26 */     super("downfall_Charboss:Flechettes", cardStrings.NAME, "green/attack/flechettes", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 27 */     this.baseDamage = 4;
/* 28 */     this.isMultiDamage = true;
/* 29 */     this.intentMultiAmt = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new EnemyFletchettesAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn)));
/* 35 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 36 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 41 */     super.applyPowers();
/* 42 */     int count = 0;
/* 43 */     Iterator<AbstractCard> var2 = AbstractCharBoss.boss.hand.group.iterator();
/*    */     
/* 45 */     while (var2.hasNext()) {
/* 46 */       AbstractCard c = var2.next();
/* 47 */       if (c.type == AbstractCard.CardType.SKILL) {
/* 48 */         count++;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 53 */     this.magicNumber = count;
/* 54 */     this.intentMultiAmt = this.magicNumber;
/*    */     
/* 56 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 57 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/* 58 */     if (count == 1) {
/* 59 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/*    */     } else {
/* 61 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
/*    */     } 
/*    */     
/* 64 */     initializeDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 71 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 72 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 77 */     if (!this.upgraded) {
/* 78 */       upgradeName();
/* 79 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 85 */     return (AbstractCard)new EnFlechettes();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnFlechettes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */