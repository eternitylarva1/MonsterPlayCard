/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ public class EnShiv
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Shiv";
/*    */   public static final int ATTACK_DMG = 4;
/*    */   public static final int UPG_ATTACK_DMG = 2;
/* 24 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Shiv");
/*    */ 
/*    */   
/*    */   public EnShiv() {
/* 28 */     super("downfall_Charboss:Shiv", cardStrings.NAME, "colorless/attack/shiv", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 29 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.hasPower("downfall:Enemy Accuracy")) {
/* 30 */       this.baseDamage = 4 + (AbstractCharBoss.boss.getPower("downfall:Enemy Accuracy")).amount;
/*    */     } else {
/* 32 */       this.baseDamage = 4;
/*    */     } 
/* 34 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 39 */     return autoPriority() * 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 44 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnShiv();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 54 */     if (!this.upgraded) {
/* 55 */       upgradeName();
/* 56 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnShiv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */