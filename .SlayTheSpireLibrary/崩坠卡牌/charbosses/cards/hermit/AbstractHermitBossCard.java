/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Necronomicon;
/*    */ import hermit.util.TextureLoader;
/*    */ 
/*    */ public abstract class AbstractHermitBossCard
/*    */   extends AbstractBossCard {
/*    */   public AbstractHermitBossCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractMonster.Intent intent) {
/* 21 */     super(id.replace("downfall_Charboss:", "downfall_Charboss:hermit:"), name, img, cost, rawDescription, type, color, rarity, target, intent, true);
/* 22 */     loadJokeCardImage();
/*    */   }
/*    */   
/*    */   protected void renderHelperU(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
/* 26 */     sb.setColor(color);
/* 27 */     sb.draw((TextureRegion)img, drawX + img.offsetX - img.originalWidth / 2.0F, drawY + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
/*    */   }
/*    */ 
/*    */   
/* 31 */   private static AbstractRelic nCon = (AbstractRelic)new Necronomicon();
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 35 */     super.render(sb);
/* 36 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.hasRelic("Necronomicon") && this.cost >= 2 && this.type == AbstractCard.CardType.ATTACK) {
/* 37 */       nCon.currentX = this.current_x + 390.0F * this.drawScale / 3.0F * Settings.scale;
/* 38 */       nCon.currentY = this.current_y + 546.0F * this.drawScale / 3.0F * Settings.scale;
/* 39 */       nCon.scale = this.drawScale;
/* 40 */       nCon.renderOutline(sb, false);
/* 41 */       nCon.render(sb);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadJokeCardImage() {
/* 47 */     Texture cardTexture = TextureLoader.getTexture(this.assetUrl.replace("cards", "betacards"));
/* 48 */     cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/* 49 */     int tw = cardTexture.getWidth();
/* 50 */     int th = cardTexture.getHeight();
/* 51 */     TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
/* 52 */     ReflectionHacks.setPrivate(this, AbstractCard.class, "jokePortrait", cardImg);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\AbstractHermitBossCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */