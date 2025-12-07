/*    */ package charbosses.cards;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import hermit.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractCustomBossCard
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public AbstractCustomBossCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
/* 16 */     super(id.replace("downfall_Charboss:", "downfall_Charboss:downfall:"), name, img, cost, rawDescription, type, color, rarity, target);
/* 17 */     loadJokeCardImage();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCustomBossCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractMonster.Intent intent) {
/* 22 */     super(id.replace("downfall_Charboss:", "downfall_Charboss:downfall:"), name, img, cost, rawDescription, type, color, rarity, target, intent);
/* 23 */     loadJokeCardImage();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadJokeCardImage() {
/* 29 */     Texture cardTexture = TextureLoader.getTexture(this.assetUrl.replace(".png", "_b.png"));
/* 30 */     cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/* 31 */     int tw = cardTexture.getWidth();
/* 32 */     int th = cardTexture.getHeight();
/* 33 */     TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
/* 34 */     ReflectionHacks.setPrivate(this, AbstractCard.class, "jokePortrait", cardImg);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\AbstractCustomBossCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */