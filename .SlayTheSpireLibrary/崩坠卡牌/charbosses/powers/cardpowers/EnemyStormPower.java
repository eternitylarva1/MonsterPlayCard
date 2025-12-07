/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.orbs.EnemyLightning;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyStormPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Storm";
/*    */   
/*    */   public EnemyStormPower(AbstractCreature owner, int amount) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Storm";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = amount;
/* 27 */     updateDescription();
/* 28 */     loadRegion("storm");
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 32 */     if (card.type == AbstractCard.CardType.POWER && this.amount > 0 && card instanceof charbosses.cards.AbstractBossCard) {
/* 33 */       flash();
/*    */       
/* 35 */       for (int i = 0; i < this.amount; i++) {
/* 36 */         addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 43 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 47 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Storm");
/* 48 */   public static final String NAME = powerStrings.NAME;
/* 49 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyStormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */