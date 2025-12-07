/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MagicFlower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CBR_MagicFlower extends AbstractCharbossRelic {
/*    */   public static final String ID = "MagicFlower";
/*    */   
/*    */   public CBR_MagicFlower() {
/* 13 */     super((AbstractRelic)new MagicFlower(), AbstractRelic.RelicTier.COMMON);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onPlayerHeal(int healAmount) {
/* 23 */     if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 24 */       flash();
/* 25 */       return MathUtils.round(healAmount * 1.5F);
/*    */     } 
/* 27 */     return healAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_MagicFlower();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MagicFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */