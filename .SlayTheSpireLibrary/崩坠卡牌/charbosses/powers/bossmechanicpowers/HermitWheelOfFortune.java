/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.bosses.Hermit.CharBossHermit;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HermitWheelOfFortune
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:HermitWheelOfFortune";
/*    */   
/*    */   public HermitWheelOfFortune(AbstractCreature owner) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "downfall:HermitWheelOfFortune";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = 2;
/* 27 */     updateDescription();
/* 28 */     loadRegion("curiosity");
/* 29 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 38 */     if (this.owner instanceof CharBossHermit && !(card instanceof charbosses.cards.AbstractBossCard) && card.type == AbstractCard.CardType.ATTACK && 
/* 39 */       ((CharBossHermit)this.owner).chosenArchetype instanceof charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge) {
/* 40 */       addToBot(new ReInitializeHandAction(this.owner, this));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 47 */     flash();
/* 48 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
/*    */   }
/*    */ 
/*    */   
/* 52 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:HermitWheelOfFortune");
/* 53 */   public static final String NAME = powerStrings.NAME;
/* 54 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\HermitWheelOfFortune.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */