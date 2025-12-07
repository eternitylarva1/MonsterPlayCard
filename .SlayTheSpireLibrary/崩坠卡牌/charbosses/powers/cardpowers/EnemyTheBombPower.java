/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
/*    */ import java.util.ArrayList;
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
/*    */ public class EnemyTheBombPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "TheBomb";
/*    */   
/*    */   public EnemyTheBombPower(AbstractCreature owner, int turns, int damage) {
/* 35 */     this.name = NAME;
/* 36 */     this.ID = "TheBomb" + bombIdOffset;
/* 37 */     bombIdOffset++;
/* 38 */     this.owner = owner;
/* 39 */     this.amount = turns;
/* 40 */     this.damage = damage;
/* 41 */     updateDescription();
/* 42 */     loadRegion("the_bomb");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 47 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
/* 48 */     if (this.amount == 1) {
/* 49 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(int slot) {
/* 55 */     super.update(slot);
/* 56 */     if (this.amount == 1) {
/* 57 */       if (this.timer <= 0.0F) {
/* 58 */         ArrayList<AbstractGameEffect> effect2 = (ArrayList<AbstractGameEffect>)ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
/* 59 */         effect2.add(new GainPowerEffect(this));
/* 60 */         this.timer = 1.0F;
/*    */       } else {
/* 62 */         this.timer -= Gdx.graphics.getDeltaTime();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {}
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 73 */     if (this.amount == 1) {
/* 74 */       this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.damage) });
/*    */     } else {
/* 76 */       this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.damage) });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 82 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TheBomb");
/* 83 */   public static final String NAME = powerStrings.NAME;
/* 84 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private int damage;
/*    */   private static int bombIdOffset;
/*    */   private float timer;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyTheBombPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */