/*    */ package charbosses.ui;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*    */ 
/*    */ public class EnergyOrbHermit
/*    */   implements EnergyOrbInterface
/*    */ {
/*    */   private static final int ORB_W = 128;
/* 15 */   public static float fontScale = 1.0F;
/*    */   
/*    */   private static final float ORB_IMG_SCALE;
/*    */   private float angle5;
/*    */   private float angle4;
/*    */   private float angle3;
/*    */   private float angle2;
/*    */   private float angle1;
/* 23 */   private static Texture ENERGY_HERMIT_LAYER1 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer1.png");
/* 24 */   private static Texture ENERGY_HERMIT_LAYER2 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer2.png");
/* 25 */   private static Texture ENERGY_HERMIT_LAYER3 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer3.png");
/* 26 */   private static Texture ENERGY_HERMIT_LAYER4 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer4.png");
/* 27 */   private static Texture ENERGY_HERMIT_LAYER5 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer5.png");
/* 28 */   private static Texture ENERGY_HERMIT_LAYER6 = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer6.png");
/*    */   
/* 30 */   private static Texture ENERGY_HERMIT_LAYER1D = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer1d.png");
/* 31 */   private static Texture ENERGY_HERMIT_LAYER2D = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer2d.png");
/* 32 */   private static Texture ENERGY_HERMIT_LAYER3D = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer3d.png");
/* 33 */   private static Texture ENERGY_HERMIT_LAYER4D = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer4d.png");
/* 34 */   private static Texture ENERGY_HERMIT_LAYER5D = ImageMaster.loadImage("hermitResources/images/char/hermit/orb/layer5d.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateOrb(int orbCount) {
/* 39 */     if (orbCount == 0) {
/* 40 */       this.angle5 += Gdx.graphics.getDeltaTime() * -5.0F;
/* 41 */       this.angle4 += Gdx.graphics.getDeltaTime() * 5.0F;
/* 42 */       this.angle3 += Gdx.graphics.getDeltaTime() * -8.0F;
/* 43 */       this.angle2 += Gdx.graphics.getDeltaTime() * 8.0F;
/* 44 */       this.angle1 += Gdx.graphics.getDeltaTime() * 72.0F;
/*    */     } else {
/* 46 */       this.angle5 += Gdx.graphics.getDeltaTime() * -20.0F;
/* 47 */       this.angle4 += Gdx.graphics.getDeltaTime() * 20.0F;
/* 48 */       this.angle3 += Gdx.graphics.getDeltaTime() * -40.0F;
/* 49 */       this.angle2 += Gdx.graphics.getDeltaTime() * 40.0F;
/* 50 */       this.angle1 += Gdx.graphics.getDeltaTime() * 360.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/* 56 */     if (enabled) {
/* 57 */       sb.setColor(Color.WHITE);
/* 58 */       this; sb.draw(ENERGY_HERMIT_LAYER1, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle1, 0, 0, 128, 128, false, false);
/* 59 */       this; sb.draw(ENERGY_HERMIT_LAYER2, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 128, 128, false, false);
/* 60 */       this; sb.draw(ENERGY_HERMIT_LAYER3, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 128, 128, false, false);
/* 61 */       this; sb.draw(ENERGY_HERMIT_LAYER4, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 128, 128, false, false);
/* 62 */       this; sb.draw(ENERGY_HERMIT_LAYER5, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle5, 0, 0, 128, 128, false, false);
/* 63 */       this; sb.draw(ENERGY_HERMIT_LAYER6, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
/*    */     } else {
/* 65 */       sb.setColor(Color.WHITE);
/* 66 */       this; sb.draw(ENERGY_HERMIT_LAYER1D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle1, 0, 0, 128, 128, false, false);
/* 67 */       this; sb.draw(ENERGY_HERMIT_LAYER2D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 128, 128, false, false);
/* 68 */       this; sb.draw(ENERGY_HERMIT_LAYER3D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 128, 128, false, false);
/* 69 */       this; sb.draw(ENERGY_HERMIT_LAYER4D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 128, 128, false, false);
/* 70 */       this; sb.draw(ENERGY_HERMIT_LAYER5D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle5, 0, 0, 128, 128, false, false);
/* 71 */       this; sb.draw(ENERGY_HERMIT_LAYER6, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   static {
/* 77 */     ORB_IMG_SCALE = 1.15F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosse\\ui\EnergyOrbHermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */