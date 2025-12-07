/*    */ package downfall.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ShootAnythingAction
/*    */   extends AbstractGameAction {
/*    */   private ShootAnythingEffect effect;
/*    */   
/*    */   public ShootAnythingAction(AbstractCreature target, AbstractCreature source, Texture texture) {
/* 13 */     this(target, source, texture, 15);
/*    */   }
/*    */   
/*    */   public ShootAnythingAction(AbstractCreature target, AbstractCreature source, Texture texture, int time) {
/* 17 */     this.actionType = AbstractGameAction.ActionType.SPECIAL;
/*    */     
/* 19 */     this.effect = new ShootAnythingEffect(target, source, texture, time);
/* 20 */     AbstractDungeon.effectList.add(this.effect);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.effect.finishedAction)
/* 26 */       this.isDone = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\combat\ShootAnythingAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */