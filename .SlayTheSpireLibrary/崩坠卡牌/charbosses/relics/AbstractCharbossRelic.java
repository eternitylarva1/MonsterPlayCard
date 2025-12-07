/*     */ package charbosses.relics;
/*     */ 
/*     */ import charbosses.bosses.AbstractBossDeckArchetype;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
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
/*     */ public abstract class AbstractCharbossRelic
/*     */   extends AbstractRelic
/*     */ {
/*  34 */   private static float START_X = 364.0F * Settings.scale;
/*  35 */   private static float START_Y = Settings.HEIGHT - 174.0F * Settings.scale;
/*  36 */   private static float PAD_X = 72.0F * Settings.scale;
/*     */   
/*     */   public AbstractCharBoss owner;
/*     */   
/*     */   private AbstractRelic baseRelic;
/*     */   
/*     */   public AbstractCharbossRelic(String setId, String imgName, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
/*  43 */     super(setId, imgName, tier, sfx);
/*  44 */     this.isSeen = true;
/*  45 */     UnlockTracker.markRelicAsSeen(this.relicId);
/*  46 */     refreshDescription();
/*     */   }
/*     */   
/*     */   public AbstractCharbossRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, Texture texture) {
/*  50 */     super(setId, "", tier, sfx);
/*  51 */     texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/*  52 */     setTexture(texture);
/*     */     
/*  54 */     this.isSeen = true;
/*  55 */     UnlockTracker.markRelicAsSeen(this.relicId);
/*  56 */     refreshDescription();
/*     */   }
/*     */   public AbstractCharbossRelic(AbstractRelic baseRelic) {
/*  59 */     super(baseRelic.relicId, baseRelic.imgUrl, baseRelic.tier, AbstractRelic.LandingSound.CLINK);
/*  60 */     this.baseRelic = baseRelic;
/*  61 */     this.isSeen = true;
/*  62 */     UnlockTracker.markRelicAsSeen(this.relicId);
/*  63 */     refreshDescription();
/*     */   }
/*     */   public AbstractCharbossRelic(AbstractRelic baseRelic, AbstractRelic.RelicTier tier) {
/*  66 */     super(baseRelic.relicId, baseRelic.imgUrl, tier, AbstractRelic.LandingSound.CLINK);
/*  67 */     this.baseRelic = baseRelic;
/*  68 */     this.isSeen = true;
/*  69 */     UnlockTracker.markRelicAsSeen(this.relicId);
/*  70 */     refreshDescription();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  76 */     renderInTopPanel(sb);
/*     */     
/*  78 */     if (this.hb.hovered && !CardCrawlGame.relicPopup.isOpen) {
/*     */       
/*  80 */       if (!this.isSeen) {
/*  81 */         if (InputHelper.mX < 1400.0F * Settings.scale) {
/*  82 */           TipHelper.renderGenericTip(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[1], MSG[1]);
/*     */         } else {
/*  84 */           TipHelper.renderGenericTip(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[1], MSG[1]);
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  91 */       renderTip(sb);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshDescription() {
/*  98 */     this.tips.clear();
/*  99 */     this.tips.add(new PowerTip(this.name, this.description));
/* 100 */     initializeTips();
/*     */   }
/*     */   
/*     */   public void setTexture(Texture t) {
/* 104 */     this.img = t;
/* 105 */     this.largeImg = t;
/* 106 */     this.outlineImg = t;
/*     */   }
/*     */   
/*     */   public void reorganizeObtain(AbstractCharBoss p, int slot, boolean callOnEquip, int relicAmount) {
/* 110 */     this.owner = p;
/* 111 */     this.isDone = true;
/* 112 */     this.isObtained = true;
/* 113 */     p.relics.add(this);
/* 114 */     this.currentX = START_X + slot * PAD_X;
/* 115 */     this.currentY = START_Y;
/* 116 */     this.targetX = this.currentX;
/* 117 */     this.targetY = this.currentY;
/* 118 */     this.hb.move(this.currentX, this.currentY);
/* 119 */     if (callOnEquip) {
/* 120 */       onEquip();
/* 121 */       relicTip();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void instantObtain(AbstractCharBoss p, int slot, boolean callOnEquip) {
/* 126 */     this.owner = p;
/* 127 */     if (this.relicId.equals("Circlet") && p != null && p.hasRelic("Circlet")) {
/*     */       
/* 129 */       AbstractRelic relic = p.getRelic("Circlet"), circ = relic;
/* 130 */       relic.counter++;
/* 131 */       circ.flash();
/* 132 */       this.isDone = true;
/* 133 */       this.isObtained = true;
/* 134 */       this.discarded = true;
/*     */     } else {
/* 136 */       this.isDone = true;
/* 137 */       this.isObtained = true;
/* 138 */       if (slot >= p.relics.size()) {
/* 139 */         p.relics.add(this);
/*     */       } else {
/* 141 */         p.relics.set(slot, this);
/*     */       } 
/* 143 */       this.currentX = START_X + slot * PAD_X;
/* 144 */       this.currentY = START_Y;
/* 145 */       this.targetX = this.currentX;
/* 146 */       this.targetY = this.currentY;
/* 147 */       this.hb.move(this.currentX, this.currentY);
/* 148 */       if (callOnEquip) {
/* 149 */         onEquip();
/* 150 */         relicTip();
/*     */       } 
/*     */       
/* 153 */       getUpdatedDescription();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void instantObtain(AbstractCharBoss boss) {
/* 159 */     this.owner = boss;
/* 160 */     instantObtain();
/*     */   }
/*     */   
/*     */   public void instantObtain() {
/* 164 */     if (this.owner == null) {
/* 165 */       AbstractBossDeckArchetype.logger.info("OWNER IS NULL!");
/*     */       return;
/*     */     } 
/* 168 */     if (this.relicId == "Circlet" && this.owner.hasRelic("Circlet")) {
/*     */       
/* 170 */       AbstractRelic relic = this.owner.getRelic("Circlet"), circ = relic;
/* 171 */       relic.counter++;
/* 172 */       circ.flash();
/*     */     } else {
/* 174 */       this.isDone = true;
/* 175 */       this.isObtained = true;
/* 176 */       this.currentX = Settings.WIDTH - START_X - this.owner.relics.size() * PAD_X;
/* 177 */       this.currentY = START_Y;
/* 178 */       this.targetX = this.currentX;
/* 179 */       this.targetY = this.currentY;
/* 180 */       flash();
/* 181 */       this.owner.relics.add(this);
/* 182 */       this.hb.move(this.currentX, this.currentY);
/* 183 */       onEquip();
/*     */       
/* 185 */       relicTip();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void obtain() {
/* 191 */     if (this.relicId == "Circlet" && this.owner.hasRelic("Circlet")) {
/*     */       
/* 193 */       AbstractRelic relic = this.owner.getRelic("Circlet"), circ = relic;
/* 194 */       relic.counter++;
/* 195 */       circ.flash();
/* 196 */       this.hb.hovered = false;
/*     */     } else {
/* 198 */       this.hb.hovered = false;
/* 199 */       int row = this.owner.relics.size();
/* 200 */       this.targetX = Settings.WIDTH - START_X - row * AbstractRelic.PAD_X;
/* 201 */       this.targetY = START_Y;
/* 202 */       this.owner.relics.add(this);
/* 203 */       relicTip();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void obtain(AbstractCharBoss boss) {
/* 208 */     this.owner = boss;
/* 209 */     obtain();
/*     */   }
/*     */   
/*     */   public int getColumn() {
/* 213 */     return this.owner.relics.indexOf(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 218 */     if (!this.isDone) {
/* 219 */       super.update();
/*     */     } else {
/* 221 */       if (this.flashTimer != 0.0F) {
/* 222 */         this.flashTimer -= Gdx.graphics.getDeltaTime();
/* 223 */         if (this.flashTimer < 0.0F) {
/* 224 */           if (this.pulse) {
/* 225 */             this.flashTimer = 1.0F;
/*     */           } else {
/* 227 */             this.flashTimer = 0.0F;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 232 */       this.hb.update();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       if (this.hb.hovered && AbstractDungeon.topPanel.potionUi.isHidden) {
/* 241 */         this.scale = Settings.scale * 1.25F;
/* 242 */         CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/*     */       } else {
/*     */         
/* 245 */         this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */       } 
/*     */       
/* 248 */       updateBosscharRelicPopupClick();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateBosscharRelicPopupClick() {
/* 253 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 254 */       this.hb.clickStarted = true;
/*     */     }
/* 256 */     if (this.hb.clicked || (this.hb.hovered && CInputActionSet.select.isJustPressed())) {
/* 257 */       CardCrawlGame.relicPopup.open(this);
/* 258 */       CInputActionSet.select.unpress();
/* 259 */       this.hb.clicked = false;
/* 260 */       this.hb.clickStarted = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playLandingSFX() {}
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/* 269 */     return false;
/*     */   }
/*     */   
/*     */   public void onEnergyRecharge() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\AbstractCharbossRelic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */