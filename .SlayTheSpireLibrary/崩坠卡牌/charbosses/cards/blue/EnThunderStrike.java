/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyThunderStrikeAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnThunderStrike
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Thunder Strike";
/*    */   
/*    */   public EnThunderStrike() {
/* 27 */     this(1);
/*    */   }
/*    */   
/*    */   public EnThunderStrike(int hitCount) {
/* 31 */     super("downfall_Charboss:Thunder Strike", cardStrings.NAME, "blue/attack/thunder_strike", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 32 */     this.baseDamage = 7;
/* 33 */     this.isMultiDamage = true;
/* 34 */     this.baseMagicNumber = hitCount;
/* 35 */     this.magicNumber = this.baseMagicNumber;
/* 36 */     this.intentMultiAmt = this.magicNumber;
/* 37 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     this.baseMagicNumber = getLightningCount();
/* 42 */     this.magicNumber = this.baseMagicNumber;
/*    */     
/* 44 */     for (int i = 0; i < this.magicNumber; i++) {
/* 45 */       addToBot((AbstractGameAction)new EnemyThunderStrikeAction(this, (AbstractCreature)p));
/*    */     }
/*    */   }
/*    */   
/*    */   public static int getLightningCount() {
/* 50 */     int lightningCount = 0;
/*    */     
/* 52 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/* 53 */       if (o instanceof charbosses.orbs.EnemyLightning) {
/* 54 */         lightningCount++;
/*    */       }
/*    */     } 
/* 57 */     return lightningCount;
/*    */   }
/*    */   
/*    */   public void applyPowers() {
/* 61 */     super.applyPowers();
/* 62 */     this.baseMagicNumber = getLightningCount();
/* 63 */     this.magicNumber = this.baseMagicNumber;
/*    */     
/* 65 */     if (this.baseMagicNumber > 0) {
/* 66 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/* 67 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 73 */     super.calculateCardDamage(mo);
/* 74 */     if (this.baseMagicNumber > 0) {
/* 75 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/*    */     }
/*    */     
/* 78 */     initializeDescription();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 82 */     if (!this.upgraded) {
/* 83 */       upgradeName();
/* 84 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 91 */     return autoPriority() * this.owner.orbs.size();
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 95 */     return (AbstractCard)new EnThunderStrike();
/*    */   }
/*    */ 
/*    */   
/* 99 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Thunder Strike");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnThunderStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */