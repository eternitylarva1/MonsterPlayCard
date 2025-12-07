/*     */ package downfall.rooms;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.ShopRoom;
/*     */ import com.megacrit.cardcrawl.shop.Merchant;
/*     */ import downfall.monsters.FleeingMerchant;
/*     */ import downfall.patches.EvilModeCharacterSelect;
/*     */ import downfall.util.HeartMerchant;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeartShopRoom
/*     */   extends ShopRoom
/*     */ {
/*     */   public HeartMerchant heartMerchant;
/*     */   public boolean heartMerchantShown;
/*     */   public boolean startedCombat = false;
/*     */   public boolean yesFight = true;
/*     */   
/*     */   public HeartShopRoom() {}
/*     */   
/*     */   public HeartShopRoom(boolean yesFight) {
/*  31 */     this.yesFight = yesFight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMerchant(Merchant merc) {
/*  36 */     super.setMerchant(merc);
/*     */   }
/*     */   
/*     */   public void onPlayerEntry() {
/*  40 */     if (EvilModeCharacterSelect.evilMode) {
/*  41 */       if (!FleeingMerchant.DEAD && this.yesFight && AbstractDungeon.actNum != 4) {
/*  42 */         startCombat();
/*  43 */         this.startedCombat = true;
/*     */       } else {
/*  45 */         showHeartMerchant();
/*     */       } 
/*     */     }
/*  48 */     if (!AbstractDungeon.id.equals("TheEnding") && !this.yesFight) {
/*  49 */       playBGM("SHOP");
/*     */     }
/*     */     
/*  52 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void startCombat() {
/*  57 */     AbstractDungeon.closeCurrentScreen();
/*  58 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/*  59 */     AbstractDungeon.lastCombatMetricKey = FleeingMerchant.ID;
/*  60 */     (AbstractDungeon.getCurrRoom()).monsters = new MonsterGroup((AbstractMonster)new FleeingMerchant());
/*  61 */     (AbstractDungeon.getCurrRoom()).monsters.init();
/*  62 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  63 */       m.usePreBattleAction();
/*  64 */       m.useUniversalPreBattleAction();
/*     */     } 
/*  66 */     AbstractRoom.waitTimer = 0.1F;
/*  67 */     AbstractDungeon.player.preBattlePrep();
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  71 */     if (this.heartMerchant != null && this.heartMerchantShown) {
/*  72 */       this.heartMerchant.render(sb);
/*     */     }
/*     */     
/*  75 */     super.render(sb);
/*  76 */     renderTips(sb);
/*     */   }
/*     */   
/*     */   public void dispose() {
/*  80 */     super.dispose();
/*  81 */     if (this.heartMerchant != null) {
/*  82 */       this.heartMerchant.dispose();
/*  83 */       this.heartMerchant = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  88 */     super.update();
/*  89 */     if (this.heartMerchant != null && this.heartMerchantShown) {
/*  90 */       this.heartMerchant.update();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void showHeartMerchant() {
/*  97 */     if (this.heartMerchant == null) {
/*  98 */       this.heartMerchant = new HeartMerchant();
/*     */     }
/*     */     
/* 101 */     this.heartMerchantShown = true;
/* 102 */     this.heartMerchant.spawnHitbox();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\rooms\HeartShopRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */