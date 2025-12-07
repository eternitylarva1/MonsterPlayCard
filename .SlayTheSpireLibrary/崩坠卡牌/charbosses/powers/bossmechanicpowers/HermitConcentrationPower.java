/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.bosses.Hermit.CharBossHermit;
/*    */ import charbosses.cards.AbstractBossCard;
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
/*    */ public class HermitConcentrationPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:HermitConcentrationPower";
/*    */   
/*    */   public HermitConcentrationPower(AbstractCreature owner) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "downfall:HermitConcentrationPower";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = 10;
/* 28 */     updateDescription();
/* 29 */     loadRegion("curiosity");
/* 30 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public int onLoseHp(int damageAmount) {
/* 35 */     flash();
/* 36 */     stackPower(damageAmount * -1);
/* 37 */     updateDescription();
/* 38 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 43 */     super.stackPower(stackAmount);
/* 44 */     if (this.amount <= 0 && 
/* 45 */       this.owner instanceof CharBossHermit) {
/* 46 */       for (AbstractCard bc : ((CharBossHermit)this.owner).hand.group) {
/* 47 */         ((AbstractBossCard)bc).onSpecificTrigger();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 54 */     this.amount = 10;
/* 55 */     updateDescription();
/*    */     
/* 57 */     if (this.owner instanceof CharBossHermit) {
/* 58 */       for (AbstractCard bc : ((CharBossHermit)this.owner).hand.group) {
/* 59 */         ((AbstractBossCard)bc).onSpecificTrigger();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 65 */     if (this.amount > 0) {
/* 66 */       this.description = DESC[0] + this.amount + DESC[1];
/*    */     } else {
/* 68 */       this.description = DESC[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 73 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:HermitConcentrationPower");
/* 74 */   public static final String NAME = powerStrings.NAME;
/* 75 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\HermitConcentrationPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */