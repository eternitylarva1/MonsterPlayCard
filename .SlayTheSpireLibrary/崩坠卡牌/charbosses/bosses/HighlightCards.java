/*     */ package charbosses.bosses;
/*     */ 
/*     */ import charbosses.powers.bossmechanicpowers.SilentShivTimeEaterPower;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
/*     */ public class HighlightCards
/*     */ {
/*  31 */   private static Texture shivTexture = new Texture(Gdx.files.internal("downfallResources/images/powers/infinite_blades_marker.png"));
/*  32 */   private static TextureAtlas.AtlasRegion shivRegion = new TextureAtlas.AtlasRegion(shivTexture, 0, 0, shivTexture.getWidth(), shivTexture.getHeight());
/*  33 */   private static Texture biasTexture = new Texture(Gdx.files.internal("downfallResources/images/powers/inverse_bias_marker.png"));
/*  34 */   private static TextureAtlas.AtlasRegion biasRegion = new TextureAtlas.AtlasRegion(biasTexture, 0, 0, biasTexture.getWidth(), biasTexture.getHeight());
/*     */ 
/*     */   
/*     */   @SpirePostfixPatch
/*     */   public static void patch(AbstractCard c, SpriteBatch sb) {
/*  39 */     if (highCostChecker(c)) {
/*  40 */       renderIcon(c, sb, shivRegion);
/*  41 */     } else if (powerChecker(c)) {
/*  42 */       renderIcon(c, sb, biasRegion);
/*     */     } 
/*     */   }
/*     */   private static void renderIcon(AbstractCard c, SpriteBatch sb, TextureAtlas.AtlasRegion region) {
/*  46 */     sb.setColor(Color.WHITE);
/*  47 */     renderHelper(sb, region, c.current_x, c.current_y, c);
/*  48 */     sb.setBlendFunction(770, 1);
/*  49 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, (MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) + 1.25F) / 3.0F));
/*  50 */     renderHelper(sb, region, c.current_x, c.current_y, c);
/*  51 */     sb.setBlendFunction(770, 771);
/*  52 */     sb.setColor(Color.WHITE);
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = CardGlowBorder.class, method = "<ctor>", paramtypez = {AbstractCard.class, Color.class})
/*     */   public static class CardGlowPatch {
/*     */     @SpirePostfixPatch
/*     */     public static void patch(CardGlowBorder __instance, AbstractCard c, Color col, @ByRef Color[] ___color) {
/*  59 */       if (HighlightCards.highCostChecker(c) || HighlightCards.powerChecker(c)) {
/*  60 */         ___color[0] = Color.RED.cpy();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard C) {
/*  67 */     sb.draw((TextureRegion)img, drawX + img.offsetX - img.originalWidth / 2.0F, drawY + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, C.drawScale * Settings.scale, C.drawScale * Settings.scale, C.angle);
/*     */   }
/*     */   
/*     */   private static boolean highCostChecker(AbstractCard c) {
/*  71 */     if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && 
/*  72 */       AbstractCharBoss.boss != null && 
/*  73 */       !AbstractCharBoss.boss.isDeadOrEscaped() && 
/*  74 */       AbstractCharBoss.boss.hasPower("downfall:SilentShivTimeEaterPower") && 
/*  75 */       !((SilentShivTimeEaterPower)AbstractCharBoss.boss.getPower("downfall:SilentShivTimeEaterPower")).usedThisTurn && 
/*  76 */       !(c instanceof charbosses.cards.AbstractBossCard) && c.cost != -1 && c.costForTurn >= 2 && !c.purgeOnUse && !c.freeToPlayOnce) {
/*  77 */       return true;
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean powerChecker(AbstractCard c) {
/* 101 */     if (!isInCombat()) {
/* 102 */       return false;
/*     */     }
/* 104 */     if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && 
/* 105 */       AbstractCharBoss.boss != null && 
/* 106 */       !AbstractCharBoss.boss.isDeadOrEscaped() && 
/* 107 */       AbstractCharBoss.boss.hasPower("downfall:DefectCuriosity") && 
/* 108 */       !(c instanceof charbosses.cards.AbstractBossCard) && c.type == AbstractCard.CardType.POWER) {
/* 109 */       return true;
/*     */     }
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
/*     */ 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isInCombat() {
/* 129 */     return (CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\HighlightCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */