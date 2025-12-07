/*    */ package downfall.ui.campfire;
/*    */ 
/*    */ import basemod.CustomEventRoom;
/*    */ import basemod.ReflectionHacks;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.map.MapEdge;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import downfall.downfallMod;
/*    */ import downfall.relics.GremlinWheel;
/*    */ import downfall.util.TextureLoader;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class WheelSpinButton
/*    */   extends AbstractCampfireOption
/*    */ {
/* 23 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("WheelSpinButton"));
/* 24 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public WheelSpinButton(boolean bruh) {
/* 27 */     this.label = TEXT[0];
/*    */     
/* 29 */     this.usable = bruh;
/* 30 */     if (this.usable) {
/* 31 */       this.description = TEXT[1];
/*    */     } else {
/* 33 */       this.description = TEXT[2];
/*    */     } 
/* 35 */     this.img = TextureLoader.getTexture("downfallResources/images/ui/campfire/wheel.png");
/*    */     
/* 37 */     if (AbstractDungeon.player.hasRelic(GremlinWheel.ID)) {
/* 38 */       GremlinWheel gw = (GremlinWheel)AbstractDungeon.player.getRelic(GremlinWheel.ID);
/* 39 */       gw.justFailed = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void doStuff() {
/* 44 */     AbstractDungeon.eventList.add(0, "downfall:GremlinWheelGameRest");
/* 45 */     MapRoomNode cur = AbstractDungeon.currMapNode;
/* 46 */     MapRoomNode node = new MapRoomNode(cur.x, cur.y);
/* 47 */     node.room = (AbstractRoom)new CustomEventRoom();
/* 48 */     ArrayList<MapEdge> curEdges = cur.getEdges();
/*    */     
/* 50 */     for (MapEdge edge : curEdges) {
/* 51 */       node.addEdge(edge);
/*    */     }
/*    */     
/* 54 */     AbstractDungeon.player.releaseCard();
/* 55 */     AbstractDungeon.overlayMenu.hideCombatPanels();
/* 56 */     AbstractDungeon.previousScreen = null;
/* 57 */     AbstractDungeon.dynamicBanner.hide();
/* 58 */     AbstractDungeon.dungeonMapScreen.closeInstantly();
/* 59 */     AbstractDungeon.closeCurrentScreen();
/* 60 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 61 */     AbstractDungeon.fadeIn();
/* 62 */     AbstractDungeon.effectList.clear();
/* 63 */     AbstractDungeon.topLevelEffects.clear();
/* 64 */     AbstractDungeon.topLevelEffectsQueue.clear();
/* 65 */     AbstractDungeon.effectsQueue.clear();
/* 66 */     AbstractDungeon.dungeonMapScreen.dismissable = true;
/* 67 */     AbstractDungeon.nextRoom = node;
/* 68 */     AbstractDungeon.setCurrMapNode(node);
/* 69 */     AbstractDungeon.getCurrRoom().onPlayerEntry();
/* 70 */     AbstractDungeon.scene.nextRoom(node.room);
/* 71 */     AbstractDungeon.rs = (node.room.event instanceof com.megacrit.cardcrawl.events.AbstractImageEvent) ? AbstractDungeon.RenderScene.EVENT : AbstractDungeon.RenderScene.NORMAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 76 */     expansionContentMod.teleportToWheelTime = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 81 */     float hackScale = ((Float)ReflectionHacks.getPrivate(this, AbstractCampfireOption.class, "scale")).floatValue();
/*    */     
/* 83 */     if (this.hb.hovered) {
/*    */       
/* 85 */       if (!this.hb.clickStarted) {
/* 86 */         ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", Float.valueOf(MathHelper.scaleLerpSnap(hackScale, Settings.scale)));
/* 87 */         ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", Float.valueOf(MathHelper.scaleLerpSnap(hackScale, Settings.scale)));
/*    */       } else {
/*    */         
/* 90 */         ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", Float.valueOf(MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale)));
/*    */       } 
/*    */     } else {
/*    */       
/* 94 */       ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", Float.valueOf(MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale)));
/*    */     } 
/* 96 */     super.update();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\ui\campfire\WheelSpinButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */