/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.util.CharbossDoCardQueueAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyDoubleTapPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Double Tap";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 23 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Double Tap"); static {
/* 24 */     NAME = powerStrings.NAME;
/* 25 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyDoubleTapPower(AbstractCreature owner, int amount) {
/* 29 */     this.name = NAME;
/* 30 */     this.ID = "Double Tap";
/* 31 */     this.owner = owner;
/* 32 */     this.amount = amount;
/* 33 */     updateDescription();
/* 34 */     loadRegion("doubleTap");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     if (this.amount == 1) {
/* 40 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 42 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 48 */     if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && this.amount > 0 && card instanceof charbosses.cards.AbstractBossCard) {
/* 49 */       flash();
/* 50 */       AbstractMonster m = null;
/* 51 */       if (action.target != null) {
/* 52 */         m = (AbstractMonster)action.target;
/*    */       }
/* 54 */       AbstractCard tmp = card.makeSameInstanceOf();
/*    */       
/* 56 */       tmp.current_x = card.current_x;
/* 57 */       tmp.current_y = card.current_y;
/* 58 */       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
/* 59 */       tmp.target_y = Settings.HEIGHT / 2.0F;
/* 60 */       if (m != null) {
/* 61 */         tmp.calculateCardDamage(m);
/*    */       }
/* 63 */       tmp.purgeOnUse = true;
/*    */ 
/*    */       
/* 66 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CharbossDoCardQueueAction(tmp));
/* 67 */       this.amount--;
/* 68 */       if (this.amount == 0) {
/* 69 */         addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Double Tap"));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 76 */     if (isPlayer)
/* 77 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Double Tap")); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyDoubleTapPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */