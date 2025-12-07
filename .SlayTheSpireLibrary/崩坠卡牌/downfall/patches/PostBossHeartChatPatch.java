/*     */ package downfall.patches;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.vfx.CustomAnimatedNPC;
/*     */ import downfall.vfx.TopLevelInfiniteSpeechBubble;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PostBossHeartChatPatch
/*     */ {
/*     */   private static CustomAnimatedNPC heart;
/*     */   private static CustomAnimatedNPC behindPlayerPortal;
/*     */   private static TopLevelInfiniteSpeechBubble speechBubble;
/*     */   private static boolean activated;
/*     */   
/*     */   public static class Initialize
/*     */   {
/*     */     @SpirePatch(clz = TreasureRoomBoss.class, method = "onPlayerEntry")
/*     */     public static class InitPortals
/*     */     {
/*     */       @SpirePostfixPatch
/*     */       public static void Postfix(TreasureRoomBoss instance) {
/*  38 */         if (AbstractDungeon.actNum == 1 && EvilModeCharacterSelect.evilMode) {
/*     */           
/*  40 */           PostBossHeartChatPatch.heart = new CustomAnimatedNPC(1600.0F * Settings.scale, AbstractDungeon.floorY + 300.0F * Settings.scale, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 0, false, 0.75F);
/*  41 */           PostBossHeartChatPatch.behindPlayerPortal = new CustomAnimatedNPC(AbstractDungeon.player.hb.cX + 450.0F * Settings.scale, AbstractDungeon.player.hb.cY, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 1, true, 1.0F);
/*  42 */           PostBossHeartChatPatch.heart.changeBorderColor(Color.MAROON);
/*  43 */           PostBossHeartChatPatch.behindPlayerPortal.changeBorderColor(Color.MAROON);
/*     */         } 
/*  45 */         if (AbstractDungeon.actNum == 2 && EvilModeCharacterSelect.evilMode) {
/*     */           
/*  47 */           PostBossHeartChatPatch.heart = new CustomAnimatedNPC(1600.0F * Settings.scale, AbstractDungeon.floorY + 300.0F * Settings.scale, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 0, false, 0.75F);
/*  48 */           PostBossHeartChatPatch.behindPlayerPortal = new CustomAnimatedNPC(AbstractDungeon.player.hb.cX + 450.0F * Settings.scale, AbstractDungeon.player.hb.cY, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 0, true, 1.0F);
/*  49 */           PostBossHeartChatPatch.heart.changeBorderColor(Color.MAROON);
/*  50 */           PostBossHeartChatPatch.behindPlayerPortal.changeBorderColor(Color.MAROON);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Trigger
/*     */   {
/*     */     @SpirePatch(clz = TreasureRoomBoss.class, method = "update")
/*     */     public static class TriggerOnUpdate
/*     */     {
/*     */       @SpirePostfixPatch
/*     */       public static void Postfix(TreasureRoomBoss instance) {
/*  67 */         if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.BOSS_REWARD && instance.chest.isOpen && PostBossHeartChatPatch.heart != null && !PostBossHeartChatPatch.activated) {
/*     */           
/*  69 */           PostBossHeartChatPatch.activated = true;
/*  70 */           int Rand = AbstractDungeon.cardRng.random(0, 4);
/*  71 */           String msg = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartProceed"))).TEXT[Rand];
/*  72 */           if (AbstractDungeon.actNum == 1) {
/*  73 */             msg = msg + (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartProceed"))).OPTIONS[0];
/*     */           }
/*  75 */           if (AbstractDungeon.actNum == 2) {
/*  76 */             msg = msg + (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartProceed"))).OPTIONS[1];
/*     */           }
/*  78 */           PostBossHeartChatPatch.heart.portalRenderActive = true;
/*     */           
/*  80 */           PostBossHeartChatPatch.heart.changeBorderColor(Color.MAROON);
/*  81 */           PostBossHeartChatPatch.behindPlayerPortal.portalRenderActive = true;
/*  82 */           PostBossHeartChatPatch.speechBubble = new TopLevelInfiniteSpeechBubble(PostBossHeartChatPatch.heart.skeleton.getX() - 200.0F * Settings.scale, PostBossHeartChatPatch.heart.skeleton.getY() + 150.0F * Settings.scale, msg);
/*  83 */           AbstractDungeon.topLevelEffects.add(PostBossHeartChatPatch.speechBubble);
/*     */         } 
/*  85 */         if (PostBossHeartChatPatch.activated) {
/*  86 */           if (PostBossHeartChatPatch.heart != null) PostBossHeartChatPatch.heart.update(); 
/*  87 */           if (PostBossHeartChatPatch.behindPlayerPortal != null) PostBossHeartChatPatch.behindPlayerPortal.update();
/*     */         
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Renderer
/*     */   {
/*     */     @SpirePatch(clz = TreasureRoomBoss.class, method = "render", paramtypez = {SpriteBatch.class})
/*     */     public static class Render
/*     */     {
/*     */       @SpirePostfixPatch
/*     */       public static void Postfix(TreasureRoomBoss instance, SpriteBatch sb) {
/* 103 */         if (PostBossHeartChatPatch.activated) {
/* 104 */           if (PostBossHeartChatPatch.heart != null) PostBossHeartChatPatch.heart.render(sb); 
/* 105 */           if (PostBossHeartChatPatch.behindPlayerPortal != null) PostBossHeartChatPatch.behindPlayerPortal.render(sb);
/*     */         
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CleanUp
/*     */   {
/*     */     @SpirePatch(clz = AbstractRoom.class, method = "dispose")
/*     */     public static class Dispose
/*     */     {
/*     */       @SpirePostfixPatch
/*     */       public static void Postfix(AbstractRoom instance) {
/* 122 */         if (PostBossHeartChatPatch.heart != null) {
/*     */           
/* 124 */           PostBossHeartChatPatch.heart.dispose();
/* 125 */           PostBossHeartChatPatch.heart = null;
/* 126 */           PostBossHeartChatPatch.activated = false;
/*     */         } 
/*     */         
/* 129 */         if (PostBossHeartChatPatch.behindPlayerPortal != null) {
/* 130 */           PostBossHeartChatPatch.behindPlayerPortal.dispose();
/* 131 */           PostBossHeartChatPatch.behindPlayerPortal = null;
/*     */         } 
/*     */ 
/*     */         
/* 135 */         Iterator<AbstractGameEffect> var1 = AbstractDungeon.topLevelEffects.iterator();
/* 136 */         while (var1.hasNext()) {
/* 137 */           AbstractGameEffect e = var1.next();
/* 138 */           if (e instanceof TopLevelInfiniteSpeechBubble) {
/* 139 */             ((TopLevelInfiniteSpeechBubble)e).dismiss();
/* 140 */             ((TopLevelInfiniteSpeechBubble)e).isDone = true;
/*     */           } 
/* 142 */           if (e instanceof com.megacrit.cardcrawl.vfx.SpeechTextEffect) {
/* 143 */             e.dispose();
/* 144 */             e.isDone = true;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\PostBossHeartChatPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */