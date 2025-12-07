/*    */ package charbosses.vfx;
/*    */ 
/*    */ import charbosses.bosses.Ironclad.CharBossIronclad;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyReaperEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public void update() {
/* 18 */     CardCrawlGame.sound.playA("ORB_LIGHTNING_EVOKE", 0.9F);
/* 19 */     CardCrawlGame.sound.playA("ORB_LIGHTNING_PASSIVE", -0.3F);
/*    */     
/* 21 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 22 */       if (!m.isDeadOrEscaped() && !m.id.equals(CharBossIronclad.ID)) {
/* 23 */         AbstractDungeon.effectsQueue.add(new LightningEffect(m.hb.cX, m.hb.cY));
/*    */       }
/*    */     } 
/*    */     
/* 27 */     AbstractDungeon.effectsQueue.add(new LightningEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY));
/*    */     
/* 29 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyReaperEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */