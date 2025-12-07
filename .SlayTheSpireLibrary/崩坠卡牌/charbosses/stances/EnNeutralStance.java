/*    */ package charbosses.stances;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ 
/*    */ public class EnNeutralStance
/*    */   extends AbstractEnemyStance
/*    */ {
/*    */   public static final String STANCE_ID = "Neutral";
/*    */   
/*    */   public EnNeutralStance() {
/* 13 */     this.ID = "Neutral";
/* 14 */     this.img = null;
/* 15 */     this.name = null;
/* 16 */     updateDescription();
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 20 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterStance() {}
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ 
/*    */   
/* 30 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Neutral");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\EnNeutralStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */