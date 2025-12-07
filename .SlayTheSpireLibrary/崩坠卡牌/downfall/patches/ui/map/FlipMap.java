/*     */ package downfall.patches.ui.map;
/*     */ import chronoMods.TogetherManager;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.evacipated.cardcrawl.modthespire.Loader;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.map.DungeonMap;
/*     */ import com.megacrit.cardcrawl.map.MapEdge;
/*     */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*     */ import com.megacrit.cardcrawl.map.RoomTypeAssigner;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
/*     */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*     */ import com.megacrit.cardcrawl.screens.DungeonMapScreen;
/*     */ import downfall.patches.EvilModeCharacterSelect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.CtBehavior;
/*     */ import javassist.expr.ExprEditor;
/*     */ import javassist.expr.FieldAccess;
/*     */ import javassist.expr.MethodCall;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class FlipMap {
/*  37 */   public static final Logger logger = LogManager.getLogger(FlipMap.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private static final HashSet<String> invalidActs = new HashSet<>(); static {
/*  43 */     invalidActs.add("paleoftheancients:PaleOfTheAncients");
/*  44 */     invalidActs.add("infinite-spire:TheVoid");
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = AbstractDungeon.class, method = "generateMap")
/*     */   public static class StandardMapFlipper
/*     */   {
/*     */     private static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/*  54 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(RoomTypeAssigner.class, "distributeRoomsAcrossMap");
/*  55 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = Locator.class)
/*     */     public static void Insert() {
/*  63 */       FlipMap.MapFlipper.flipflipflipflipflip();
/*     */     }
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = TheEnding.class, method = "generateSpecialMap")
/*     */   public static class EndingMapFlipper
/*     */   {
/*     */     private static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/*  74 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(Logger.class, "info");
/*  75 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = Locator.class)
/*     */     public static void Insert() {
/*  83 */       FlipMap.MapFlipper.flipflipflipflipflip();
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean moddedActFourCheck() {
/*  88 */     return (AbstractDungeon.actNum >= 4 && !Settings.isEndless && !AbstractDungeon.id.equals("TheEnding"));
/*     */   }
/*     */   
/*     */   public static class MapFlipper {
/*  92 */     public static int startY = 0;
/*     */     
/*     */     public static void flipflipflipflipflip() {
/*  95 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id) && !FlipMap.moddedActFourCheck()) {
/*  96 */         if (downfallMod.normalMapLayout) {
/*  97 */           if (!AbstractDungeon.id.equals("TheEnding")) {
/*  98 */             flipCampfire();
/*     */           }
/* 100 */           flip(AbstractDungeon.map);
/*     */         }
/*     */         else {
/*     */           
/* 104 */           flip(AbstractDungeon.map);
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     public static void connectNode(MapRoomNode src, MapRoomNode dst) {
/* 110 */       src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static ArrayList<ArrayList<MapRoomNode>> SWFAct4Override() {
/* 116 */       ArrayList<ArrayList<MapRoomNode>> map = new ArrayList<>();
/*     */       
/* 118 */       MapRoomNode courierNode = new MapRoomNode(3, 0);
/* 119 */       courierNode.room = ModCrossoverHelperClass.returnCourierRoom();
/* 120 */       MapRoomNode restNode = new MapRoomNode(3, 1);
/* 121 */       restNode.room = (AbstractRoom)new RestRoom();
/* 122 */       MapRoomNode shopNode = new MapRoomNode(3, 2);
/* 123 */       shopNode.room = (AbstractRoom)new ShopRoom();
/* 124 */       MapRoomNode enemyNode = new MapRoomNode(3, 3);
/* 125 */       enemyNode.room = (AbstractRoom)new MonsterRoomElite();
/* 126 */       MapRoomNode bossNode = new MapRoomNode(3, 4);
/* 127 */       bossNode.room = (AbstractRoom)new MonsterRoomBoss();
/* 128 */       MapRoomNode victoryNode = new MapRoomNode(3, 5);
/* 129 */       victoryNode.room = (AbstractRoom)new TrueVictoryRoom();
/*     */ 
/*     */       
/* 132 */       connectNode(courierNode, restNode);
/* 133 */       connectNode(restNode, shopNode);
/* 134 */       connectNode(shopNode, enemyNode);
/*     */       
/* 136 */       enemyNode.addEdge(new MapEdge(enemyNode.x, enemyNode.y, enemyNode.offsetX, enemyNode.offsetY, bossNode.x, bossNode.y, bossNode.offsetX, bossNode.offsetY, false));
/*     */ 
/*     */       
/* 139 */       ArrayList<MapRoomNode> row1 = new ArrayList<>();
/* 140 */       row1.add(new MapRoomNode(0, 0));
/* 141 */       row1.add(new MapRoomNode(1, 0));
/* 142 */       row1.add(new MapRoomNode(2, 0));
/* 143 */       row1.add(courierNode);
/* 144 */       row1.add(new MapRoomNode(4, 0));
/* 145 */       row1.add(new MapRoomNode(5, 0));
/* 146 */       row1.add(new MapRoomNode(6, 0));
/*     */       
/* 148 */       ArrayList<MapRoomNode> row2 = new ArrayList<>();
/* 149 */       row2.add(new MapRoomNode(0, 1));
/* 150 */       row2.add(new MapRoomNode(1, 1));
/* 151 */       row2.add(new MapRoomNode(2, 1));
/* 152 */       row2.add(restNode);
/* 153 */       row2.add(new MapRoomNode(4, 1));
/* 154 */       row2.add(new MapRoomNode(5, 1));
/* 155 */       row2.add(new MapRoomNode(6, 1));
/*     */       
/* 157 */       ArrayList<MapRoomNode> row3 = new ArrayList<>();
/* 158 */       row3.add(new MapRoomNode(0, 2));
/* 159 */       row3.add(new MapRoomNode(1, 2));
/* 160 */       row3.add(new MapRoomNode(2, 2));
/* 161 */       row3.add(shopNode);
/* 162 */       row3.add(new MapRoomNode(4, 2));
/* 163 */       row3.add(new MapRoomNode(5, 2));
/* 164 */       row3.add(new MapRoomNode(6, 2));
/*     */       
/* 166 */       ArrayList<MapRoomNode> row4 = new ArrayList<>();
/* 167 */       row4.add(new MapRoomNode(0, 3));
/* 168 */       row4.add(new MapRoomNode(1, 3));
/* 169 */       row4.add(new MapRoomNode(2, 3));
/* 170 */       row4.add(enemyNode);
/* 171 */       row4.add(new MapRoomNode(4, 3));
/* 172 */       row4.add(new MapRoomNode(5, 3));
/* 173 */       row4.add(new MapRoomNode(6, 3));
/*     */       
/* 175 */       ArrayList<MapRoomNode> row5 = new ArrayList<>();
/* 176 */       row5.add(new MapRoomNode(0, 4));
/* 177 */       row5.add(new MapRoomNode(1, 4));
/* 178 */       row5.add(new MapRoomNode(2, 4));
/* 179 */       row5.add(bossNode);
/* 180 */       row5.add(new MapRoomNode(4, 4));
/* 181 */       row5.add(new MapRoomNode(5, 4));
/* 182 */       row5.add(new MapRoomNode(6, 4));
/*     */ 
/*     */       
/* 185 */       ArrayList<MapRoomNode> row6 = new ArrayList<>();
/* 186 */       row6.add(new MapRoomNode(0, 5));
/* 187 */       row6.add(new MapRoomNode(1, 5));
/* 188 */       row6.add(new MapRoomNode(2, 5));
/* 189 */       row6.add(victoryNode);
/* 190 */       row6.add(new MapRoomNode(4, 5));
/* 191 */       row6.add(new MapRoomNode(5, 5));
/* 192 */       row6.add(new MapRoomNode(6, 5));
/*     */ 
/*     */       
/* 195 */       map.add(row1);
/* 196 */       map.add(row2);
/* 197 */       map.add(row3);
/* 198 */       map.add(row4);
/* 199 */       map.add(row5);
/* 200 */       map.add(row6);
/*     */       
/* 202 */       AbstractDungeon.map.clear();
/* 203 */       AbstractDungeon.map = map;
/*     */       
/* 205 */       return map;
/*     */     }
/*     */ 
/*     */     
/*     */     private static void flipCampfire() {
/* 210 */       ArrayList<ArrayList<MapRoomNode>> map = AbstractDungeon.map;
/*     */ 
/*     */       
/* 213 */       assignRowAsRoomType(map.get(0), (Class)RestRoom.class);
/* 214 */       assignRowAsRoomType(map.get(map.size() - 1), (Class)MonsterRoom.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static void assignRowAsRoomType(ArrayList<MapRoomNode> row, Class<? extends AbstractRoom> c) {
/* 220 */       for (MapRoomNode n : row) {
/*     */         try {
/* 222 */           n.setRoom(c.newInstance());
/* 223 */         } catch (InstantiationException|IllegalAccessException e) {
/* 224 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static void flip(ArrayList<ArrayList<MapRoomNode>> map) {
/* 234 */       if (AbstractDungeon.id == "TheEnding" && 
/* 235 */         Loader.isModLoaded("chronoMods") && 
/* 236 */         TogetherManager.gameMode.equals(TogetherManager.mode.Coop)) {
/* 237 */         map = SWFAct4Override();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       startY = 0;
/*     */       
/* 245 */       ArrayList<MapNodeData> edges = new ArrayList<>();
/*     */       
/* 247 */       for (ArrayList<MapRoomNode> row : map) {
/* 248 */         for (MapRoomNode n : row) {
/* 249 */           if (n.room instanceof MonsterRoomBoss) {
/*     */             continue;
/*     */           }
/* 252 */           for (MapEdge e : n.getEdges()) {
/* 253 */             if (!edgeArrayContains(edges, e) && 
/* 254 */               e.dstY >= 0 && e.dstY < map.size())
/*     */             {
/* 256 */               edges.add(new MapNodeData(e, n, ((ArrayList<MapRoomNode>)map.get(e.dstY)).get(e.dstX)));
/*     */             }
/*     */           } 
/*     */           
/* 260 */           n.getEdges().clear();
/* 261 */           n.getParents().clear();
/*     */         } 
/*     */       } 
/*     */       
/* 265 */       ArrayList<MapRoomNode> finalNodes = new ArrayList<>();
/*     */       
/* 267 */       for (MapNodeData data : edges) {
/* 268 */         if (data.end.room instanceof MonsterRoomBoss) {
/*     */           continue;
/*     */         }
/* 271 */         if (data.end.y > startY) {
/* 272 */           startY = data.end.y;
/*     */         }
/* 274 */         data.end.addEdge(new MapEdge(data.end.x, data.end.y, data.end.offsetX, data.end.offsetY, data.start.x, data.start.y, data.start.offsetX, data.start.offsetY, false));
/* 275 */         data.end.getEdges().sort(MapEdge::compareTo);
/* 276 */         data.start.getParents().add(data.end);
/* 277 */         if (data.start.y == 0) {
/* 278 */           finalNodes.add(data.start);
/*     */         }
/*     */       } 
/* 281 */       for (MapRoomNode n : finalNodes) {
/* 282 */         n.addEdge(new MapEdge(n.x, n.y, n.offsetX, n.offsetY, 3, -1, 0.0F, Settings.MAP_DST_Y * 2.0F, true));
/*     */       }
/*     */     }
/*     */     
/*     */     private static boolean edgeArrayContains(ArrayList<MapNodeData> data, MapEdge e) {
/* 287 */       for (MapNodeData d : data) {
/* 288 */         if (d.e.equals(e))
/* 289 */           return true; 
/*     */       } 
/* 291 */       return false;
/*     */     }
/*     */     
/*     */     private static class MapNodeData { public MapEdge e;
/*     */       public MapRoomNode start;
/*     */       public MapRoomNode end;
/*     */       
/*     */       public MapNodeData(MapEdge e, MapRoomNode start, MapRoomNode end) {
/* 299 */         this.e = e;
/* 300 */         this.start = start;
/* 301 */         this.end = end;
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = RoomTypeAssigner.class, method = "ruleAssignableToRow")
/*     */   public static class EliteRoomPatch
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static SpireReturn<Boolean> Prefix(MapRoomNode n, AbstractRoom roomToBeSet) {
/* 316 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 317 */         List<Class<? extends AbstractRoom>> applicableRooms = Arrays.asList((Class<? extends AbstractRoom>[])new Class[] { RestRoom.class, MonsterRoomElite.class });
/* 318 */         List<Class<RestRoom>> applicableRooms2 = Collections.singletonList(RestRoom.class);
/*     */         
/* 320 */         if (n.y >= 10 && applicableRooms.contains(roomToBeSet.getClass())) {
/* 321 */           return SpireReturn.Return(Boolean.valueOf(false));
/*     */         }
/*     */         
/* 324 */         if (n.y <= 1 && applicableRooms2.contains(roomToBeSet.getClass())) {
/* 325 */           return SpireReturn.Return(Boolean.valueOf(false));
/*     */         }
/* 327 */         return SpireReturn.Return(Boolean.valueOf(true));
/*     */       } 
/*     */       
/* 330 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = RoomTypeAssigner.class, method = "assignRoomsToNodes")
/*     */   public static class AssignRoomsInReverse
/*     */   {
/*     */     public static ExprEditor Instrument() {
/* 339 */       return new ExprEditor()
/*     */         {
/*     */           public void edit(MethodCall m) throws CannotCompileException {
/* 342 */             if (m.getMethodName().equals("iterator")) {
/* 343 */               m.replace("if (downfall.patches.EvilModeCharacterSelect.evilMode) {    java.util.ArrayList _tmp = (java.util.ArrayList)$0.clone();    java.util.Collections.reverse(_tmp);    $_ = _tmp.iterator();} else { $_ = $0.iterator(); }");
/*     */             }
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = EmeraldElite.class, method = "alternate")
/*     */   public static class FixNoRestEmeraldElite
/*     */   {
/*     */     @SpireInsertPatch(locator = Locator.class, localvars = {"chosenNode"})
/*     */     public static void patch(MapRoomNode chosenNode) {
/* 358 */       if (AbstractDungeon.actNum == 3 && EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id) && 
/* 359 */         !hasRestSite(chosenNode)) {
/* 360 */         ArrayList<MapRoomNode> cN = FlipMap.getConnectedNodes(chosenNode);
/* 361 */         FlipMap.logger.info("Found and fixed emerald elite without campfire.");
/* 362 */         if (!cN.isEmpty()) {
/* 363 */           for (MapRoomNode n : cN) {
/* 364 */             if (!(n.room instanceof com.megacrit.cardcrawl.rooms.TreasureRoom)) {
/* 365 */               n.room = (AbstractRoom)new RestRoom();
/*     */               return;
/*     */             } 
/*     */           } 
/* 369 */           FlipMap.getConnectedNodes(cN.get(0)).stream().findAny().ifPresent(m -> m.room = (AbstractRoom)new RestRoom());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private static boolean hasRestSite(MapRoomNode origin) {
/* 376 */       ArrayList<MapRoomNode> cN = FlipMap.getConnectedNodes(origin);
/* 377 */       return cN.stream().anyMatch(mn -> (mn.room instanceof RestRoom || hasRestSite(mn)));
/*     */     }
/*     */     
/*     */     private static class Locator extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 382 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(Logger.class, "info");
/* 383 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static ArrayList<MapRoomNode> getConnectedNodes(MapRoomNode origin) {
/* 389 */     ArrayList<MapRoomNode> retVal = new ArrayList<>();
/* 390 */     for (MapEdge mn : origin.getEdges()) {
/* 391 */       if (AbstractDungeon.map.size() >= mn.dstY && mn.dstY >= 0 && ((ArrayList)AbstractDungeon.map.get(mn.dstY)).size() >= mn.dstX) {
/* 392 */         MapRoomNode cN = ((ArrayList<MapRoomNode>)AbstractDungeon.map.get(mn.dstY)).get(mn.dstX);
/* 393 */         if (cN != null) {
/* 394 */           retVal.add(cN);
/*     */         }
/*     */       } 
/*     */     } 
/* 398 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MapRoomNode.class, method = "update")
/*     */   public static class FirstRoom
/*     */   {
/*     */     public static void Raw(CtBehavior ctBehavior) throws CannotCompileException {
/* 408 */       ctBehavior.instrument(new ree());
/*     */     }
/*     */     
/*     */     public static int isValidFirstNode(MapRoomNode n) {
/* 412 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 413 */         if (n.y == FlipMap.MapFlipper.startY)
/* 414 */           return 0; 
/* 415 */         if (n.y == 0) {
/* 416 */           return 1;
/*     */         }
/*     */       } 
/* 419 */       return n.y;
/*     */     }
/*     */     
/*     */     private static class ree extends ExprEditor {
/* 423 */       int count = 0;
/*     */ 
/*     */       
/*     */       public void edit(FieldAccess f) throws CannotCompileException {
/* 427 */         if (f.getFieldName().equals("y") && f.getClassName().equals(MapRoomNode.class.getName())) {
/* 428 */           this.count++;
/*     */           
/* 430 */           if (this.count == 4 || this.count == 6) {
/* 431 */             f.replace("{$_ = " + FlipMap.FirstRoom.class
/* 432 */                 .getName() + ".isValidFirstNode($0);}");
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       private ree() {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MapRoomNode.class, method = "<ctor>")
/*     */   public static class PositionAdjustment
/*     */   {
/* 447 */     private static final float EVIL_ADJUST = 400.0F * Settings.scale;
/*     */     
/*     */     @SpirePostfixPatch
/*     */     public static void upYouGo(MapRoomNode __instance, int x, int y) {
/* 451 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 452 */         __instance.offsetY += EVIL_ADJUST;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MapEdge.class, method = "<ctor>", paramtypez = {int.class, int.class, float.class, float.class, int.class, int.class, float.class, float.class, boolean.class})
/*     */   public static class JustATeensyNudge
/*     */   {
/* 464 */     private static final float bop = 13.666F * Settings.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = Locator.class, localvars = {"tmpDY", "tmpSY"})
/*     */     public static void boop(MapEdge __instance, int srcX, int srcY, float srcOffsetX, float srcOffsetY, int dstX, int dstY, float dstOffsetX, float dstOffsetY, boolean isBoss, @ByRef float[] tmpSY, @ByRef float[] tmpDY) {
/* 471 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 472 */         tmpDY[0] = tmpDY[0] + bop;
/* 473 */         tmpSY[0] = tmpSY[0] + bop;
/*     */       } 
/*     */     }
/*     */     
/*     */     private static class Locator extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 479 */         Matcher.NewExprMatcher newExprMatcher = new Matcher.NewExprMatcher(Vector2.class);
/* 480 */         return LineFinder.findInOrder(ctBehavior, (Matcher)newExprMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = DungeonMapScreen.class, method = "open")
/*     */   public static class YouAreScrollingTheWrongWay
/*     */   {
/* 490 */     private static final float ADJUST = 50.0F * Settings.scale;
/* 491 */     private static final float MORE_ADJUST = 250.0F * Settings.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = Locator.class, localvars = {"targetOffsetY"})
/*     */     public static void doot(DungeonMapScreen __instance, boolean scrolly, float ___mapScrollUpperLimit, @ByRef float[] targetOffsetY) {
/* 498 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 499 */         if (scrolly) {
/* 500 */           FlipMap.WhyDoYouNotPayAnyRealAttentionToTheVariables.lower = targetOffsetY[0] + ADJUST;
/* 501 */           FlipMap.WhyDoYouNotPayAnyRealAttentionToTheVariables.upper = targetOffsetY[0] = DungeonMapScreen.offsetY + MORE_ADJUST;
/* 502 */           DungeonMapScreen.offsetY = FlipMap.WhyDoYouNotPayAnyRealAttentionToTheVariables.lower;
/*     */         }
/* 504 */         else if (!AbstractDungeon.firstRoomChosen) {
/* 505 */           targetOffsetY[0] = DungeonMapScreen.offsetY = ___mapScrollUpperLimit;
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     private static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 514 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(DynamicBanner.class, "hide");
/* 515 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = DungeonMapScreen.class, method = "updateAnimation")
/*     */   public static class WhyDoYouNotPayAnyRealAttentionToTheVariables
/*     */   {
/* 525 */     public static float upper = 0.0F;
/* 526 */     public static float lower = 0.0F;
/*     */     
/*     */     @SpirePrefixPatch
/*     */     public static SpireReturn<?> WellThenIHaveToDoItMyself(DungeonMapScreen __instance, float ___targetOffsetY, @ByRef float[] ___scrollWaitTimer) {
/* 530 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 531 */         ___scrollWaitTimer[0] = ___scrollWaitTimer[0] - Gdx.graphics.getDeltaTime();
/* 532 */         if (___scrollWaitTimer[0] < 0.0F) {
/* 533 */           DungeonMapScreen.offsetY = MathUtils.lerp(DungeonMapScreen.offsetY, ___targetOffsetY, Gdx.graphics.getDeltaTime() * 12.0F);
/* 534 */         } else if (___scrollWaitTimer[0] < 3.0F) {
/* 535 */           DungeonMapScreen.offsetY = Interpolation.exp10.apply(upper, lower, ___scrollWaitTimer[0] / 3.0F);
/*     */         } 
/*     */         
/* 538 */         return SpireReturn.Return(null);
/*     */       } 
/* 540 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = DungeonMap.class, method = "update")
/*     */   public static class BossStuff
/*     */   {
/* 551 */     public static float BOSS_OFFSET = 60.0F * Settings.scale;
/* 552 */     public static float BOSS_HB_OFFSET = 256.0F * Settings.scale;
/*     */     
/*     */     public static void Raw(CtBehavior ctBehavior) throws CannotCompileException {
/* 555 */       ctBehavior.instrument(new BossRepositionifier());
/* 556 */       ctBehavior.instrument(new BossVisitifier());
/*     */     }
/*     */     
/*     */     private static class BossRepositionifier extends ExprEditor { private BossRepositionifier() {}
/*     */       
/*     */       public void edit(MethodCall m) throws CannotCompileException {
/* 562 */         if (m.getMethodName().equals("move") && m.getClassName().equals(Hitbox.class.getName()))
/* 563 */           m.replace("{$proceed($1, " + EvilModeCharacterSelect.class
/* 564 */               .getName() + ".evilMode ? " + DungeonMapScreen.class
/* 565 */               .getName() + ".offsetY + " + FlipMap.BossStuff.class.getName() + ".BOSS_OFFSET + " + FlipMap.BossStuff.class.getName() + ".BOSS_HB_OFFSET : $2);}"); 
/*     */       } }
/*     */ 
/*     */     
/*     */     private static class BossVisitifier
/*     */       extends ExprEditor {
/*     */       private int count;
/*     */       
/*     */       private BossVisitifier() {
/* 574 */         this.count = 0;
/*     */       }
/*     */       
/*     */       public void edit(FieldAccess f) throws CannotCompileException {
/* 578 */         if (f.getFieldName().equals("y") && f.getClassName().equals(MapRoomNode.class.getName())) {
/* 579 */           this.count++;
/*     */           
/* 581 */           if (this.count == 1) {
/* 582 */             f.replace("{$_ = " + FlipMap.BossStuff.class
/* 583 */                 .getName() + ".compatibleGetARealY($0);}");
/*     */           }
/* 585 */           else if (this.count == 2) {
/* 586 */             f.replace("{$_ = " + FlipMap.BossStuff.class
/* 587 */                 .getName() + ".getARealY($0);}");
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static int compatibleGetARealY(MapRoomNode mmmmmm) {
/* 596 */       if (Loader.isModLoaded("actlikeit"))
/* 597 */         return MapCompatiblity.actLikeItCheck(); 
/* 598 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 599 */         if (mmmmmm.y == 0)
/* 600 */           return AbstractDungeon.id.equals("TheEnding") ? 2 : 14; 
/* 601 */         return 0;
/*     */       } 
/* 603 */       return mmmmmm.y;
/*     */     }
/*     */     
/*     */     public static int getARealY(MapRoomNode mmmmmm) {
/* 607 */       if (EvilModeCharacterSelect.evilMode && !FlipMap.invalidActs.contains(AbstractDungeon.id)) {
/* 608 */         if (mmmmmm.y == 0)
/* 609 */           return AbstractDungeon.id.equals("TheEnding") ? 2 : 14; 
/* 610 */         return 0;
/*     */       } 
/* 612 */       return mmmmmm.y;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = DungeonMap.class, method = "renderBossIcon")
/*     */   public static class BossIconPosition
/*     */   {
/*     */     public static void Raw(CtBehavior ctBehavior) throws CannotCompileException {
/* 623 */       ctBehavior.instrument(new BossIconRepositionifier());
/*     */     }
/*     */     
/*     */     private static class BossIconRepositionifier extends ExprEditor { private BossIconRepositionifier() {}
/*     */       
/*     */       public void edit(MethodCall m) throws CannotCompileException {
/* 629 */         if (m.getMethodName().equals("draw") && m.getClassName().equals(SpriteBatch.class.getName()))
/* 630 */           m.replace("{$proceed($1, $2, " + EvilModeCharacterSelect.class
/* 631 */               .getName() + ".evilMode ? " + DungeonMapScreen.class
/* 632 */               .getName() + ".offsetY + " + FlipMap.BossStuff.class.getName() + ".BOSS_OFFSET : $3, $4, $5);}"); 
/*     */       } }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\map\FlipMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */