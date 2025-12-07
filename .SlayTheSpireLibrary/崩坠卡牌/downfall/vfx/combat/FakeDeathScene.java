/*     */ package downfall.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.scenes.TheEndingScene;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.DeathScreenFloatyEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FakeDeathScene
/*     */   extends TheEndingScene
/*     */ {
/*     */   private ArrayList<DeathScreenFloatyEffect> particles;
/*     */   private Color color;
/*     */   private boolean addColor;
/*     */   
/*     */   public FakeDeathScene() {
/*  30 */     this.particles = new ArrayList<>();
/*  31 */     this.ambianceName = "AMBIANCE_BEYOND";
/*  32 */     fadeInAmbiance();
/*     */     
/*  34 */     this.color = Color.DARK_GRAY.cpy();
/*     */     
/*  36 */     this.color.a = 0.0F;
/*  37 */     this.addColor = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  42 */     if (this.particles.size() < 60) {
/*  43 */       DeathScreenFloatyEffect dfe2 = new DeathScreenFloatyEffect();
/*  44 */       dfe2.renderBehind = true;
/*  45 */       this.particles.add(dfe2);
/*     */     } 
/*  47 */     for (int i = this.particles.size() - 1; i >= 0; i--) {
/*     */       
/*  49 */       DeathScreenFloatyEffect dfe = this.particles.get(i);
/*  50 */       dfe.update();
/*  51 */       if (dfe.isDone) {
/*  52 */         this.particles.remove(i);
/*     */       }
/*     */     } 
/*  55 */     if (this.addColor) {
/*     */       
/*  57 */       this.color.a += Gdx.graphics.getDeltaTime() / 8.0F;
/*  58 */       if (this.color.a >= 0.2F)
/*     */       {
/*  60 */         this.addColor = false;
/*  61 */         this.color.a = 0.2F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  66 */       this.color.a -= Gdx.graphics.getDeltaTime() / 8.0F;
/*  67 */       if (this.color.a <= 0.0F) {
/*     */         
/*  69 */         this.addColor = true;
/*     */         
/*  71 */         this.color.a = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCombatRoomBg(SpriteBatch sb) {
/*  78 */     sb.setColor(Color.WHITE);
/*  79 */     renderAtlasRegionIf(sb, this.bg, true);
/*     */ 
/*     */     
/*  82 */     sb.setColor(Color.WHITE);
/*  83 */     for (AbstractGameEffect age : this.particles) {
/*  84 */       age.render(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderCombatRoomFg(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderCampfireRoom(SpriteBatch sb) {
/*  95 */     sb.setColor(Color.WHITE);
/*  96 */     renderAtlasRegionIf(sb, this.campfireBg, true);
/*  97 */     for (DeathScreenFloatyEffect dfe : this.particles) {
/*  98 */       dfe.render(sb);
/*     */     }
/* 100 */     sb.setColor(Color.WHITE);
/* 101 */     renderAtlasRegionIf(sb, this.campfireBg, true);
/*     */   }
/*     */   
/*     */   public void randomizeScene() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\combat\FakeDeathScene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */