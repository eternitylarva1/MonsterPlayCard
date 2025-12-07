/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
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
/*    */ public class WatcherSkillPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   private boolean used = false;
/*    */   
/*    */   public WatcherSkillPower(AbstractCreature owner, int newAmount) {
/* 35 */     this.name = NAME;
/* 36 */     this.ID = "downfall:WatcherSkillPower";
/* 37 */     this.owner = owner;
/* 38 */     this.amount = newAmount;
/* 39 */     updateDescription();
/* 40 */     loadRegion("curiosity");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 48 */     if (card instanceof charbosses.cards.AbstractBossCard) {
/*    */       return;
/*    */     }
/* 51 */     if (card.type.equals(AbstractCard.CardType.SKILL)) {
/* 52 */       flash();
/* 53 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount, true, AbstractGameAction.AttackEffect.NONE));
/* 54 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/* 61 */     if (damage > 1.0F && 
/* 62 */       this.owner instanceof AbstractCharBoss) {
/* 63 */       if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnWrathStance) {
/* 64 */         return damage * 1.5F;
/*    */       }
/* 66 */       if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnRealWrathStance) {
/* 67 */         return damage * 2.0F;
/*    */       }
/*    */     } 
/*    */     
/* 71 */     return damage;
/*    */   }
/*    */ 
/*    */   
/* 75 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:WatcherSkillPower");
/* 76 */   public static final String NAME = powerStrings.NAME;
/* 77 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "downfall:WatcherSkillPower";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\WatcherSkillPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */