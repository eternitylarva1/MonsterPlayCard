/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
/*    */ import downfall.monsters.NeowBossFinal;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractTwoAmountBossMechanicPower
/*    */   extends TwoAmountPower
/*    */ {
/*    */   private float timer;
/*    */   private boolean firstTurn = true;
/*    */   
/*    */   public void update(int slot) {
/* 21 */     super.update(slot);
/* 22 */     if (this.firstTurn) {
/* 23 */       if (this.timer <= 0.0F) {
/* 24 */         ArrayList<AbstractGameEffect> effect2 = (ArrayList<AbstractGameEffect>)ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
/* 25 */         effect2.add(new GainPowerEffect((AbstractPower)this));
/* 26 */         this.timer = 1.0F;
/* 27 */         if (AbstractCharBoss.boss != null && 
/* 28 */           AbstractCharBoss.boss.hb.hovered) {
/* 29 */           this.firstTurn = false;
/*    */         }
/*    */         
/* 32 */         if (NeowBossFinal.neowboss != null && 
/* 33 */           NeowBossFinal.neowboss.hb.hovered) {
/* 34 */           this.firstTurn = false;
/*    */         }
/*    */       } else {
/*    */         
/* 38 */         this.timer -= Gdx.graphics.getDeltaTime();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 45 */     super.atStartOfTurn();
/* 46 */     this.firstTurn = false;
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {}
/*    */   
/*    */   public void PreRoundLoseBlock() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\AbstractTwoAmountBossMechanicPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */