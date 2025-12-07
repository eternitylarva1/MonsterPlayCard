/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import downfall.patches.GoldSoundPatch;
/*    */ import downfall.vfx.ThrowGoldEffect;
/*    */ 
/*    */ public class MerchantThrowGoldAction extends AbstractGameAction {
/*    */   private int goldAmount;
/*    */   private AbstractGameEffect effect;
/*    */   private boolean waitForEffect;
/*    */   
/*    */   public MerchantThrowGoldAction(AbstractCreature target, AbstractCreature source, int goldAmount, boolean waitForEffect) {
/* 17 */     setValues(target, source, goldAmount);
/* 18 */     this.goldAmount = goldAmount;
/* 19 */     this.waitForEffect = waitForEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.effect == null) {
/* 25 */       GoldSoundPatch.active = false;
/* 26 */       CardCrawlGame.sound.play("GOLD_JINGLE");
/* 27 */       GoldSoundPatch.active = true;
/* 28 */       float stagger = 0.0F;
/* 29 */       for (int i = 0; i < this.goldAmount; i++) {
/* 30 */         this.effect = (AbstractGameEffect)new ThrowGoldEffect(this.source, this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY, stagger);
/* 31 */         AbstractDungeon.effectList.add(this.effect);
/* 32 */         stagger += 0.15F;
/*    */       } 
/*    */     } 
/*    */     
/* 36 */     if (!this.waitForEffect || this.effect == null || this.effect.isDone)
/* 37 */       this.isDone = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\MerchantThrowGoldAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */