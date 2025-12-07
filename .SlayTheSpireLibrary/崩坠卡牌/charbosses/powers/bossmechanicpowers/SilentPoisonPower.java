/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyPoisonDamageAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SilentPoisonPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:SilentPoisonPower";
/*    */   
/*    */   public SilentPoisonPower(AbstractCreature owner) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "downfall:SilentPoisonPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = 0;
/* 24 */     updateDescription();
/* 25 */     loadRegion("curiosity");
/* 26 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void atStartOfTurn() {
/* 30 */     if (AbstractDungeon.player.hasPower("Poison") && 
/* 31 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 32 */       AbstractPower poison = AbstractDungeon.player.getPower("Poison");
/* 33 */       poison.flashWithoutSound();
/* 34 */       addToBot((AbstractGameAction)new EnemyPoisonDamageAction(poison.owner, this.owner, poison.amount, AbstractGameAction.AttackEffect.POISON));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/* 45 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:SilentPoisonPower");
/* 46 */   public static final String NAME = powerStrings.NAME;
/* 47 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\SilentPoisonPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */