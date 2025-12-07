/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*     */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.ui.DialogWord;
/*     */ import com.megacrit.cardcrawl.ui.SpeechWord;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Scanner;
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
/*     */ public class CustomSpeechTextEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*  33 */   private float wordTimer = 0.0F;
/*     */   
/*     */   private boolean textDone = false;
/*     */   
/*  37 */   private ArrayList<SpeechWord> words = new ArrayList<>();
/*  38 */   private int curLine = 0;
/*     */   
/*  40 */   private float curLineWidth = 0.0F;
/*     */ 
/*     */   
/*     */   public CustomSpeechTextEffect(float x, float y, float duration, String msg, DialogWord.AppearEffect a_effect) {
/*  44 */     if (gl == null) {
/*  45 */       gl = new GlyphLayout();
/*     */     }
/*     */     
/*  48 */     this.duration = duration;
/*  49 */     this.x = x;
/*  50 */     this.y = y;
/*  51 */     this.font = FontHelper.turnNumFont;
/*  52 */     this.a_effect = a_effect;
/*  53 */     this.s = new Scanner(msg);
/*     */   }
/*     */   
/*     */   public void update() {
/*  57 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/*  58 */     if (this.wordTimer < 0.0F && !this.textDone) {
/*  59 */       if (Settings.lineBreakViaCharacter) {
/*  60 */         addWordCN();
/*     */       } else {
/*  62 */         addWord();
/*     */       } 
/*     */     }
/*     */     
/*  66 */     Iterator<SpeechWord> var1 = this.words.iterator();
/*     */ 
/*     */     
/*  69 */     while (var1.hasNext()) {
/*  70 */       SpeechWord w = var1.next();
/*  71 */       w.update();
/*     */     } 
/*     */     
/*  74 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  75 */     if (this.duration < 0.0F) {
/*  76 */       this.words.clear();
/*  77 */       this.isDone = true;
/*     */     } 
/*     */     
/*  80 */     if (this.duration < 0.3F) {
/*  81 */       var1 = this.words.iterator();
/*     */       
/*  83 */       while (var1.hasNext()) {
/*  84 */         SpeechWord w = var1.next();
/*  85 */         w.fadeOut();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addWord() {
/*  92 */     this.wordTimer = 0.03F;
/*  93 */     if (this.s.hasNext()) {
/*  94 */       String word = this.s.next();
/*  95 */       if (word.equals("NL")) {
/*  96 */         this.curLine++;
/*  97 */         Iterator<SpeechWord> var7 = this.words.iterator();
/*     */         
/*  99 */         while (var7.hasNext()) {
/* 100 */           SpeechWord w = var7.next();
/* 101 */           w.shiftY(LINE_SPACING);
/*     */         } 
/*     */         
/* 104 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 108 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/* 109 */       if (color != DialogWord.WordColor.DEFAULT) {
/* 110 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/* 113 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/* 114 */       if (effect != DialogWord.WordEffect.NONE) {
/* 115 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */       
/* 118 */       gl.setText(this.font, word);
/* 119 */       float temp = 0.0F;
/*     */ 
/*     */       
/* 122 */       if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 123 */         this.curLine++;
/* 124 */         Iterator<SpeechWord> var5 = this.words.iterator();
/*     */         
/* 126 */         while (var5.hasNext()) {
/* 127 */           SpeechWord w = var5.next();
/* 128 */           w.shiftY(LINE_SPACING);
/*     */         } 
/*     */         
/* 131 */         this.curLineWidth = gl.width + CHAR_SPACING;
/* 132 */         temp = -this.curLineWidth / 2.0F;
/*     */       } else {
/* 134 */         this.curLineWidth += gl.width;
/* 135 */         temp = -this.curLineWidth / 2.0F;
/* 136 */         Iterator<SpeechWord> var5 = this.words.iterator();
/*     */         
/* 138 */         while (var5.hasNext()) {
/* 139 */           SpeechWord w = var5.next();
/* 140 */           if (w.line == this.curLine) {
/* 141 */             w.setX(this.x + temp);
/* 142 */             gl.setText(this.font, w.word);
/* 143 */             temp += gl.width + CHAR_SPACING;
/*     */           } 
/*     */         } 
/*     */         
/* 147 */         this.curLineWidth += CHAR_SPACING;
/* 148 */         gl.setText(this.font, word + " ");
/*     */       } 
/*     */       
/* 151 */       this.words.add(new SpeechWord(this.font, word, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */     } else {
/* 153 */       this.textDone = true;
/* 154 */       this.s.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addWordCN() {
/* 160 */     this.wordTimer = 0.03F;
/* 161 */     if (this.s.hasNext()) {
/* 162 */       String word = this.s.next();
/* 163 */       if (word.equals("NL")) {
/* 164 */         this.curLine++;
/* 165 */         Iterator<SpeechWord> var9 = this.words.iterator();
/*     */         
/* 167 */         while (var9.hasNext()) {
/* 168 */           SpeechWord w = var9.next();
/* 169 */           w.shiftY(LINE_SPACING);
/*     */         } 
/*     */         
/* 172 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 176 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/* 177 */       if (color != DialogWord.WordColor.DEFAULT) {
/* 178 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/* 181 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/* 182 */       if (effect != DialogWord.WordEffect.NONE) {
/* 183 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */       
/* 186 */       for (int i = 0; i < word.length(); i++) {
/* 187 */         String tmp = Character.toString(word.charAt(i));
/* 188 */         gl.setText(this.font, tmp);
/* 189 */         float temp = 0.0F;
/*     */ 
/*     */         
/* 192 */         if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 193 */           this.curLine++;
/* 194 */           Iterator<SpeechWord> var7 = this.words.iterator();
/*     */           
/* 196 */           while (var7.hasNext()) {
/* 197 */             SpeechWord w = var7.next();
/* 198 */             w.shiftY(LINE_SPACING);
/*     */           } 
/*     */           
/* 201 */           this.curLineWidth = gl.width;
/* 202 */           temp = -this.curLineWidth / 2.0F;
/*     */         } else {
/* 204 */           this.curLineWidth += gl.width;
/* 205 */           temp = -this.curLineWidth / 2.0F;
/* 206 */           Iterator<SpeechWord> var7 = this.words.iterator();
/*     */           
/* 208 */           while (var7.hasNext()) {
/* 209 */             SpeechWord w = var7.next();
/* 210 */             if (w.line == this.curLine) {
/* 211 */               w.setX(this.x + temp);
/* 212 */               gl.setText(this.font, w.word);
/* 213 */               temp += gl.width;
/*     */             } 
/*     */           } 
/*     */           
/* 217 */           gl.setText(this.font, tmp + " ");
/*     */         } 
/*     */         
/* 220 */         this.words.add(new SpeechWord(this.font, tmp, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */       } 
/*     */     } else {
/* 223 */       this.textDone = true;
/* 224 */       this.s.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 230 */     if (!AbstractDungeon.isScreenUp) {
/* 231 */       Iterator<SpeechWord> var2 = this.words.iterator();
/*     */       
/* 233 */       while (var2.hasNext()) {
/* 234 */         SpeechWord w = var2.next();
/* 235 */         w.render(sb);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */   
/* 245 */   private static final float DEFAULT_WIDTH = 280.0F * Settings.scale;
/* 246 */   private static final float LINE_SPACING = 15.0F * Settings.scale;
/* 247 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/*     */   private static GlyphLayout gl;
/*     */   private BitmapFont font;
/*     */   private DialogWord.AppearEffect a_effect;
/*     */   private static final float WORD_TIME = 0.03F;
/*     */   private float x;
/*     */   private float y;
/*     */   private Scanner s;
/*     */   private static final float FADE_TIME = 0.3F;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\CustomSpeechTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */