/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
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
/*    */ public class EnemyPoisonDamageAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private static final float DURATION = 0.33F;
/*    */   
/*    */   public EnemyPoisonDamageAction(AbstractCreature target, AbstractCreature source, int amount, AbstractGameAction.AttackEffect effect) {
/* 28 */     setValues(target, source, amount);
/* 29 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 30 */     this.attackEffect = effect;
/* 31 */     this.duration = 0.33F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 35 */     if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
/* 36 */       this.isDone = true;
/*    */     } else {
/* 38 */       if (this.duration == 0.33F && this.target.currentHealth > 0) {
/* 39 */         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */       }
/*    */       
/* 42 */       tickDuration();
/* 43 */       if (this.isDone) {
/* 44 */         if (this.target.currentHealth > 0) {
/* 45 */           this.target.tint.color = Color.CHARTREUSE.cpy();
/* 46 */           this.target.tint.changeColor(Color.WHITE.cpy());
/* 47 */           this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.THORNS));
/*    */         } 
/*    */         
/* 50 */         AbstractPower p = this.target.getPower("Poison");
/* 51 */         if (p != null) {
/* 52 */           p.amount--;
/* 53 */           if (p.amount == 0) {
/* 54 */             this.target.powers.remove(p);
/*    */           } else {
/* 56 */             p.updateDescription();
/*    */           } 
/*    */         } 
/*    */         
/* 60 */         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 61 */           AbstractDungeon.actionManager.clearPostCombatActions();
/*    */         }
/*    */         
/* 64 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyPoisonDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */