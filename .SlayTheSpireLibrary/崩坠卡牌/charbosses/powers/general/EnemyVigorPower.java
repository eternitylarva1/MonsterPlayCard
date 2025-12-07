/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyVigorPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:EnemyVigor";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 23 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Vigor"); static {
/* 24 */     NAME = powerStrings.NAME;
/* 25 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyVigorPower(AbstractCreature owner, int drawAmount) {
/* 29 */     this.name = NAME;
/* 30 */     this.ID = "downfall:EnemyVigor";
/* 31 */     this.owner = owner;
/* 32 */     this.amount = drawAmount;
/* 33 */     updateDescription();
/* 34 */     loadRegion("vigor");
/* 35 */     this.type = AbstractPower.PowerType.BUFF;
/* 36 */     this.isTurnBased = false;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 40 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   private boolean cardCheck(AbstractCard card) {
/* 44 */     boolean isFirst = true;
/* 45 */     for (AbstractCard q : AbstractCharBoss.boss.hand.group) {
/* 46 */       if (q.type == AbstractCard.CardType.ATTACK) {
/* 47 */         if (q == card && isFirst) {
/* 48 */           return true;
/*    */         }
/*    */         
/* 51 */         isFirst = false;
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/* 59 */     if (card instanceof charbosses.cards.AbstractBossCard) {
/* 60 */       if (cardCheck(card)) {
/* 61 */         return (type == DamageInfo.DamageType.NORMAL) ? (damage + this.amount) : damage;
/*    */       }
/* 63 */       return damage;
/*    */     } 
/* 65 */     return damage;
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 69 */     if (card.type == AbstractCard.CardType.ATTACK && card instanceof charbosses.cards.AbstractBossCard) {
/* 70 */       flash();
/* 71 */       AbstractCharBoss cB = (AbstractCharBoss)this.owner;
/* 72 */       if (cB.hasRelic("Akabeko")) {
/* 73 */         cB.getRelic("Akabeko").flash();
/* 74 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction(this.owner, cB.getRelic("Akabeko")));
/*    */       } 
/* 76 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyVigorPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */