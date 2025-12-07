/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyThousandCutsPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Enemy Thousand Cuts";
/* 24 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Thousand Cuts");
/*    */ 
/*    */   
/*    */   public EnemyThousandCutsPower(AbstractCreature owner, int amount) {
/* 28 */     this.name = powerStrings.NAME;
/* 29 */     this.ID = "downfall:Enemy Thousand Cuts";
/* 30 */     this.owner = owner;
/* 31 */     this.amount = amount;
/* 32 */     updateDescription();
/* 33 */     loadRegion("thousandCuts");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 43 */     if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/*    */       return;
/*    */     }
/* 46 */     if (Settings.FAST_MODE) {
/* 47 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new CleaveEffect(true)));
/*    */     } else {
/* 49 */       addToBot((AbstractGameAction)new VFXAction(this.owner, (AbstractGameEffect)new CleaveEffect(true), 0.2F));
/*    */     } 
/*    */     
/* 52 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE, true));
/* 53 */     flash();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyThousandCutsPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */