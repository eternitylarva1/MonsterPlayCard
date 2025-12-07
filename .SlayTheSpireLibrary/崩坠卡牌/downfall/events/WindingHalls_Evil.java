/*     */ package downfall.events;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.events.GenericEventDialog;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoom;
/*     */ import downfall.rooms.HeartShopRoom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WindingHalls_Evil
/*     */   extends AbstractImageEvent
/*     */ {
/*  24 */   private int screenNum = 0;
/*     */   
/*     */   public WindingHalls_Evil() {
/*  27 */     super(NAME, DESCRIPTIONS[0], "images/events/winding.jpg");
/*  28 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  29 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*  30 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */   
/*     */   public void onEnterRoom() {
/*  34 */     if (Settings.AMBIANCE_ON)
/*  35 */       CardCrawlGame.sound.play("EVENT_WINDING"); 
/*     */   }
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     boolean hackery, hackery2;
/*     */     TreasureRoom treasureRoom;
/*     */     HeartShopRoom heartShopRoom;
/*  41 */     switch (this.screenNum) {
/*     */       case 0:
/*  43 */         switch (buttonPressed) {
/*     */           case 0:
/*  45 */             this.imageEventText.clearAllDialogs();
/*  46 */             hackery = false;
/*  47 */             hackery2 = false;
/*  48 */             if (AbstractDungeon.eventList.contains("Slimebound:Darklings")) {
/*  49 */               AbstractDungeon.eventList.remove("Slimebound:Darklings");
/*  50 */               hackery = true;
/*     */             } 
/*     */             
/*  53 */             if (AbstractDungeon.eventList.contains("Mysterious Sphere")) {
/*  54 */               AbstractDungeon.eventList.remove("Mysterious Sphere");
/*  55 */               hackery2 = true;
/*     */             } 
/*     */             
/*  58 */             AbstractDungeon.getCurrRoom().onPlayerEntry();
/*  59 */             if (hackery) {
/*  60 */               AbstractDungeon.eventList.add("Slimebound:Darklings");
/*     */             }
/*  62 */             if (hackery2) {
/*  63 */               AbstractDungeon.eventList.add("Mysterious Sphere");
/*     */             }
/*     */             return;
/*     */           case 1:
/*  67 */             AbstractDungeon.getCurrRoom().clearEvent();
/*  68 */             treasureRoom = new TreasureRoom();
/*  69 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/*  70 */             (AbstractDungeon.getCurrRoom()).smoked = false;
/*  71 */             AbstractDungeon.player.isEscaping = false;
/*  72 */             AbstractRoom.waitTimer = 0.1F;
/*  73 */             this.hasFocus = false;
/*  74 */             GenericEventDialog.hide();
/*  75 */             AbstractDungeon.currMapNode.setRoom((AbstractRoom)treasureRoom);
/*  76 */             AbstractDungeon.scene.nextRoom((AbstractRoom)treasureRoom);
/*  77 */             CardCrawlGame.fadeIn(1.5F);
/*  78 */             AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
/*  79 */             treasureRoom.onPlayerEntry();
/*     */             return;
/*     */           case 2:
/*  82 */             AbstractDungeon.getCurrRoom().clearEvent();
/*  83 */             heartShopRoom = new HeartShopRoom();
/*  84 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/*  85 */             (AbstractDungeon.getCurrRoom()).smoked = false;
/*  86 */             AbstractDungeon.player.isEscaping = false;
/*  87 */             AbstractRoom.waitTimer = 0.1F;
/*  88 */             this.hasFocus = false;
/*  89 */             GenericEventDialog.hide();
/*  90 */             AbstractDungeon.currMapNode.setRoom((AbstractRoom)heartShopRoom);
/*  91 */             AbstractDungeon.scene.nextRoom((AbstractRoom)heartShopRoom);
/*  92 */             CardCrawlGame.fadeIn(1.5F);
/*  93 */             AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
/*  94 */             heartShopRoom.onPlayerEntry();
/*     */             return;
/*     */         } 
/*     */         
/*     */         return;
/*     */     } 
/* 100 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("downfall:WindingHalls");
/* 108 */   public static final String NAME = eventStrings.NAME;
/* 109 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 110 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   public static final String ID = "downfall:WindingHalls";
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\WindingHalls_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */