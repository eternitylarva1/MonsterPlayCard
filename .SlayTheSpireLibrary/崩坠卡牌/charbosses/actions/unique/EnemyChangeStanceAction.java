/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnemyChangeStanceAction
/*    */   extends AbstractGameAction {
/*    */   private String id;
/*    */   private AbstractEnemyStance newStance;
/*    */   
/*    */   public EnemyChangeStanceAction(String stanceId) {
/* 19 */     this.newStance = null;
/* 20 */     this.duration = Settings.ACTION_DUR_FAST;
/* 21 */     this.id = stanceId;
/*    */   }
/*    */   
/*    */   public EnemyChangeStanceAction(AbstractEnemyStance newStance) {
/* 25 */     this(newStance.ID);
/* 26 */     this.newStance = newStance;
/*    */   }
/*    */   
/*    */   public void update() {
/* 30 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 31 */       AbstractCharBoss.boss != null) {
/* 32 */       if (AbstractCharBoss.boss.hasPower("CannotChangeStancePower")) {
/* 33 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 38 */       AbstractEnemyStance oldStance = (AbstractEnemyStance)AbstractCharBoss.boss.stance;
/* 39 */       if (!oldStance.ID.equals(this.id)) {
/* 40 */         if (this.newStance == null) {
/* 41 */           this.newStance = AbstractEnemyStance.getStanceFromName(this.id);
/*    */         }
/*    */         
/* 44 */         Iterator<AbstractPower> var2 = AbstractCharBoss.boss.powers.iterator();
/*    */         
/* 46 */         while (var2.hasNext()) {
/* 47 */           AbstractPower p = var2.next();
/* 48 */           p.onChangeStance((AbstractStance)oldStance, (AbstractStance)this.newStance);
/*    */         } 
/*    */         
/* 51 */         var2 = AbstractCharBoss.boss.relics.iterator();
/*    */         
/* 53 */         while (var2.hasNext()) {
/* 54 */           AbstractRelic r = (AbstractRelic)var2.next();
/* 55 */           r.onChangeStance((AbstractStance)oldStance, (AbstractStance)this.newStance);
/*    */         } 
/*    */         
/* 58 */         oldStance.onExitStance();
/* 59 */         AbstractCharBoss.boss.stance = (AbstractStance)this.newStance;
/* 60 */         this.newStance.onEnterStance();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 70 */         AbstractCharBoss.boss.switchedStance();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 80 */         AbstractCharBoss.boss.onStanceChange(this.id);
/*    */       } 
/*    */       
/* 83 */       AbstractDungeon.onModifyPower();
/* 84 */       if (Settings.FAST_MODE) {
/* 85 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyChangeStanceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */