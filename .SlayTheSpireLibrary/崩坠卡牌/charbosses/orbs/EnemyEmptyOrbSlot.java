/*    */ package charbosses.orbs;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ public class EnemyEmptyOrbSlot
/*    */   extends AbstractEnemyOrb
/*    */ {
/*    */   public static final String ORB_ID = "Empty";
/*    */   public static final String[] DESC;
/* 18 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Empty"); static {
/* 19 */     DESC = orbString.DESCRIPTION;
/*    */   }
/*    */   
/*    */   public EnemyEmptyOrbSlot(float x, float y) {
/* 23 */     this.angle = MathUtils.random(360.0F);
/* 24 */     this.ID = "Empty";
/* 25 */     this.name = orbString.NAME;
/* 26 */     this.evokeAmount = 0;
/* 27 */     this.cX = x;
/* 28 */     this.cY = y;
/* 29 */     updateDescription();
/* 30 */     this.channelAnimTimer = 0.5F;
/*    */   }
/*    */   
/*    */   public EnemyEmptyOrbSlot() {
/* 34 */     this.angle = MathUtils.random(360.0F);
/* 35 */     this.name = orbString.NAME;
/* 36 */     this.evokeAmount = 0;
/* 37 */     this.cX = AbstractCharBoss.boss.drawX + AbstractCharBoss.boss.hb_x;
/* 38 */     this.cY = AbstractCharBoss.boss.drawY + AbstractCharBoss.boss.hb_y + AbstractCharBoss.boss.hb_h / 2.0F;
/* 39 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEvoke() {}
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 53 */     super.updateAnimation();
/* 54 */     this.angle += Gdx.graphics.getDeltaTime() * 10.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 59 */     sb.setColor(this.c);
/* 60 */     sb.draw(ImageMaster.ORB_SLOT_2, this.cX - 48.0F - this.bobEffect.y / 8.0F, this.cY - 48.0F + this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
/* 61 */     sb.draw(ImageMaster.ORB_SLOT_1, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
/*    */     
/* 63 */     this.hb.render(sb);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 68 */     return new EnemyEmptyOrbSlot();
/*    */   }
/*    */   
/*    */   public void playChannelSFX() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\EnemyEmptyOrbSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */