/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DescriptionLine;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractCard.class, method = "initializeDescriptionCN")
/*    */ public class CNCardTextColorPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class, localvars = {"word", "currentWidth", "numLines", "sbuilder", "CN_DESC_BOX_WIDTH"})
/*    */   public static void Insert(AbstractCard __instance, @ByRef String[] word, @ByRef float[] currentWidth, @ByRef int[] numLines, @ByRef StringBuilder[] sbuilder, float CN_DESC_BOX_WIDTH) {
/* 33 */     if (word[0].startsWith("[#") && word[0].endsWith("[]")) {
/* 34 */       String wordTrim = word[0].substring(9, word[0].length() - 2).trim();
/* 35 */       String colorTag = word[0].substring(0, 9);
/* 36 */       char[] lettersOfWord = wordTrim.toCharArray();
/* 37 */       int wordLength = lettersOfWord.length;
/*    */ 
/*    */       
/* 40 */       for (int i = 0; i < wordLength; i++) {
/* 41 */         char letter = lettersOfWord[i];
/* 42 */         float letterWidth = (new GlyphLayout(FontHelper.cardDescFont_N, String.valueOf(letter))).width;
/* 43 */         sbuilder[0].append(" " + colorTag).append(letter).append("[]");
/* 44 */         if (!Settings.manualLineBreak) {
/*    */ 
/*    */           
/* 47 */           if (LocalizedStrings.PERIOD.length() != 0 && letter == LocalizedStrings.PERIOD.charAt(0) && (currentWidth[0] + letterWidth) >= 0.8D * CN_DESC_BOX_WIDTH) {
/* 48 */             letterWidth = 0.0F;
/*    */           }
/* 50 */           if (letter == '，' && (currentWidth[0] + letterWidth) >= 0.8D * CN_DESC_BOX_WIDTH) {
/* 51 */             letterWidth = 0.0F;
/*    */           }
/* 53 */           if (letter == '、' && (currentWidth[0] + letterWidth) >= 0.8D * CN_DESC_BOX_WIDTH) {
/* 54 */             letterWidth = 0.0F;
/*    */           }
/* 56 */           if (currentWidth[0] + letterWidth > CN_DESC_BOX_WIDTH) {
/* 57 */             numLines[0] = numLines[0] + 1;
/* 58 */             __instance.description.add(new DescriptionLine(sbuilder[0].toString(), currentWidth[0]));
/* 59 */             sbuilder[0].setLength(0);
/* 60 */             currentWidth[0] = letterWidth;
/*    */           } else {
/* 62 */             currentWidth[0] = currentWidth[0] + letterWidth;
/*    */           } 
/*    */         } 
/*    */       } 
/* 66 */       sbuilder[0].append(" ");
/* 67 */       word[0] = "";
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Locator extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
/* 73 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(String.class, "equals");
/*    */       
/* 75 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher)[0] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CNCardTextColorPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */