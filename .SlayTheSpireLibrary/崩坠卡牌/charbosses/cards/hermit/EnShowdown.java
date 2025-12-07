/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import hermit.cards.Showdown;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ public class EnShowdown
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Showdown";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Showdown.ID);
/*    */   
/*    */   public EnShowdown() {
/* 24 */     super("downfall_Charboss:Showdown", cardStrings.NAME, "hermitResources/images/cards/showdown.png", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 25 */     this.baseDamage = 8;
/*    */ 
/*    */     
/* 28 */     this.selfRetain = true;
/* 29 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 40 */     for (AbstractCard q : AbstractCharBoss.boss.hand.group) {
/* 41 */       if (q instanceof EnFreeStrikeHermit) {
/* 42 */         q.setCostForTurn(1);
/* 43 */         q.isCostModified = false;
/* 44 */         q.isCostModifiedForTurn = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return (AbstractCard)new EnShowdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnShowdown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */