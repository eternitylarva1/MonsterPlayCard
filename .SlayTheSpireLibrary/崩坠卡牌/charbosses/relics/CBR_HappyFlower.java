/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.HappyFlower;
/*    */ 
/*    */ public class CBR_HappyFlower extends AbstractCharbossRelic {
/*    */   public CBR_HappyFlower() {
/* 14 */     super((AbstractRelic)new HappyFlower());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     if (this.owner != null) {
/* 20 */       return setDescription(this.owner.chosenClass);
/*    */     }
/* 22 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   public static final String ID = "HappyFlower";
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 26 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 31 */     this.description = setDescription(c);
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new CBR_HappyFlower();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 44 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 49 */     if (this.counter == -1) {
/* 50 */       this.counter += 2;
/*    */     } else {
/* 52 */       this.counter++;
/*    */     } 
/* 54 */     if (this.counter == 3) {
/* 55 */       this.counter = 0;
/* 56 */       flash();
/* 57 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 58 */       addToBot((AbstractGameAction)new EnemyGainEnergyAction(this.owner, 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_HappyFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */