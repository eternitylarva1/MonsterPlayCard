/*    */ package charbosses.stances;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ import java.util.ArrayList;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractEnemyStance
/*    */   extends AbstractStance
/*    */ {
/* 20 */   private static final Logger logger = LogManager.getLogger(AbstractStance.class.getName());
/*    */   public String name;
/*    */   public String description;
/* 23 */   protected ArrayList<PowerTip> tips = new ArrayList<>();
/*    */   protected Color c;
/*    */   protected static final int W = 512;
/*    */   protected Texture img;
/*    */   protected float angle;
/*    */   protected float particleTimer;
/*    */   protected float particleTimer2;
/*    */   
/*    */   public AbstractEnemyStance() {
/* 32 */     this.c = Color.WHITE.cpy();
/* 33 */     this.img = null;
/* 34 */     this.particleTimer = 0.0F;
/* 35 */     this.particleTimer2 = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void updateDescription();
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {}
/*    */ 
/*    */   
/*    */   public void onEndOfTurn() {}
/*    */ 
/*    */   
/*    */   public void onEnterStance() {}
/*    */   
/*    */   public void onExitStance() {}
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 53 */     return damage;
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/* 57 */     return atDamageGive(damage, type);
/*    */   }
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/* 61 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayCard(AbstractCard card) {}
/*    */   
/*    */   public void update() {
/* 68 */     updateAnimation();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateAnimation() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 75 */     if (this.img != null) {
/* 76 */       sb.setColor(this.c);
/* 77 */       sb.setBlendFunction(770, 1);
/* 78 */       sb.draw(this.img, AbstractCharBoss.boss.drawX - 256.0F + AbstractCharBoss.boss.animX, AbstractCharBoss.boss.drawY - 256.0F + AbstractCharBoss.boss.animY + AbstractCharBoss.boss.hb_h / 2.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, -this.angle, 0, 0, 512, 512, false, false);
/* 79 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopIdleSfx() {}
/*    */   
/*    */   public static AbstractEnemyStance getStanceFromName(String name) {
/* 87 */     if (name.equals("Calm"))
/* 88 */       return new EnCalmStance(); 
/* 89 */     if (name.equals("Wrath"))
/* 90 */       return new EnWrathStance(); 
/* 91 */     if (name.equals("Real Wrath"))
/* 92 */       return new EnRealWrathStance(); 
/* 93 */     if (name.equals("Divinity"))
/* 94 */       return new EnDivinityStance(); 
/* 95 */     if (name.equals("Neutral")) {
/* 96 */       return new EnNeutralStance();
/*    */     }
/* 98 */     logger.info("[ERROR] Unknown stance: " + name + " called for in getStanceFromName()");
/* 99 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\stances\AbstractEnemyStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */