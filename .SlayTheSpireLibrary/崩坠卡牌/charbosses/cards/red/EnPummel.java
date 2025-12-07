/*    */ package charbosses.cards.red;
/*    */ 
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
/*    */ public class EnPummel
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Pummel";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Pummel");
/*    */ 
/*    */   
/*    */   public EnPummel() {
/* 27 */     super("downfall_Charboss:Pummel", "Pummel", "red/attack/pummel", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 28 */     this.baseDamage = 2;
/* 29 */     this.exhaust = true;
/* 30 */     this.baseMagicNumber = 4;
/* 31 */     this.isMultiDamage = true;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     for (int i = 0; i < this.magicNumber; i++) {
/* 37 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 48 */     int badCards = 0;
/* 49 */     for (AbstractCard c : hand) {
/* 50 */       if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
/* 51 */         badCards++;
/*    */       }
/*    */     } 
/*    */     
/* 55 */     return autoPriority() + badCards * 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 60 */     if (!this.upgraded) {
/* 61 */       upgradeName();
/* 62 */       upgradeMagicNumber(1);
/* 63 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 64 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 71 */     return (AbstractCard)new EnPummel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnPummel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */