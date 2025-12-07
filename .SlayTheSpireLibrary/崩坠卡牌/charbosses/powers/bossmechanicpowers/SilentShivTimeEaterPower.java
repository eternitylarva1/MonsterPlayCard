/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.colorless.EnShiv;
/*    */ import charbosses.cards.green.EnFinisher;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SilentShivTimeEaterPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public boolean usedThisTurn = false;
/*    */   
/*    */   public SilentShivTimeEaterPower(AbstractCreature owner) {
/* 40 */     this.name = NAME;
/* 41 */     this.ID = "downfall:SilentShivTimeEaterPower";
/* 42 */     this.owner = owner;
/* 43 */     updateDescription();
/* 44 */     loadRegion("curiosity");
/* 45 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 49 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 55 */     if (!this.usedThisTurn && 
/* 56 */       !(card instanceof charbosses.cards.AbstractBossCard) && card.costForTurn >= 2 && card.cost != -1 && !card.purgeOnUse && !card.freeToPlayOnce) {
/* 57 */       flashWithoutSound();
/* 58 */       if (AbstractCharBoss.boss != null && 
/* 59 */         AbstractCharBoss.boss.hand != null && 
/* 60 */         AbstractCharBoss.boss.hand.size() <= 6) {
/*    */         
/* 62 */         addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnShiv(), 1));
/* 63 */         addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnShiv(), 1));
/*    */         
/* 65 */         for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/* 66 */           if (c instanceof EnFinisher) {
/* 67 */             ((EnFinisher)c).increaseHits(2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 72 */       this.usedThisTurn = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 80 */     super.atStartOfTurn();
/* 81 */     this.usedThisTurn = false;
/*    */   }
/*    */ 
/*    */   
/* 85 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:SilentShivTimeEaterPower");
/* 86 */   public static final String NAME = powerStrings.NAME;
/* 87 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "downfall:SilentShivTimeEaterPower";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\SilentShivTimeEaterPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */