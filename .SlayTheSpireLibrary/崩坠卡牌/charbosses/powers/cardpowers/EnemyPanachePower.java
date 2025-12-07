/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyPanachePower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:EnemyPanache";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/*    */   public static final int CARD_AMT = 5;
/* 25 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Panache"); static {
/* 26 */     NAME = powerStrings.NAME;
/* 27 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   private int damage;
/*    */   
/*    */   public EnemyPanachePower(AbstractCreature owner, int damage) {
/* 33 */     this.name = NAME;
/* 34 */     this.ID = "downfall:EnemyPanache";
/* 35 */     this.owner = owner;
/* 36 */     this.amount = 5;
/* 37 */     this.damage = damage;
/* 38 */     updateDescription();
/* 39 */     loadRegion("panache");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     if (this.amount == 1) {
/* 45 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.damage + DESCRIPTIONS[2];
/*    */     } else {
/* 47 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 53 */     this.fontScale = 8.0F;
/* 54 */     this.damage += stackAmount;
/* 55 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 60 */     if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/*    */       return;
/*    */     }
/* 63 */     this.amount--;
/* 64 */     if (this.amount == 0) {
/* 65 */       flash();
/* 66 */       this.amount = 5;
/* 67 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)AbstractCharBoss.boss, this.damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */     } 
/* 69 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 74 */     this.amount = 5;
/* 75 */     updateDescription();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyPanachePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */