/*    */ package downfall.monsters;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import downfall.downfallMod;
/*    */ import downfall.monsters.gauntletbosses.Defect;
/*    */ import downfall.monsters.gauntletbosses.Hermit;
/*    */ import downfall.monsters.gauntletbosses.Ironclad;
/*    */ import downfall.monsters.gauntletbosses.Silent;
/*    */ import downfall.monsters.gauntletbosses.Watcher;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Random;
/*    */ import slimebound.SlimeboundMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowBossSelector
/*    */ {
/*    */   public static boolean validClass(String key) {
/* 20 */     switch (key) {
/*    */       
/*    */       case "downfall:Ironclad":
/* 23 */         return true;
/*    */       case "downfall:Silent":
/* 25 */         return true;
/*    */       case "downfall:Defect":
/* 27 */         return true;
/*    */       case "downfall:Watcher":
/* 29 */         return true;
/*    */       case "downfall:Hermit":
/* 31 */         return true;
/*    */     } 
/*    */ 
/*    */     
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public static String charbossToGauntlet(String key) {
/* 39 */     switch (key) {
/*    */       case "downfall:Ironclad":
/* 41 */         return Ironclad.ID;
/*    */       case "downfall:Silent":
/* 43 */         return Silent.ID;
/*    */       case "downfall:Defect":
/* 45 */         return Defect.ID;
/*    */       case "downfall:Watcher":
/* 47 */         return Watcher.ID;
/*    */       case "downfall:Hermit":
/* 49 */         return Hermit.ID;
/*    */     } 
/*    */     
/* 52 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public static ArrayList<String> returnBossOptions() {
/* 57 */     ArrayList<String> results = new ArrayList<>();
/* 58 */     ArrayList<String> bosses = new ArrayList<>();
/* 59 */     SlimeboundMod.logger.info("Bosses! " + downfallMod.Act1BossFaced + " " + validClass(downfallMod.Act1BossFaced) + downfallMod.Act2BossFaced + " " + 
/* 60 */         validClass(downfallMod.Act2BossFaced) + downfallMod.Act3BossFaced + " " + 
/* 61 */         validClass(downfallMod.Act3BossFaced));
/* 62 */     if (validClass(downfallMod.Act1BossFaced) && validClass(downfallMod.Act2BossFaced) && validClass(downfallMod.Act3BossFaced)) {
/*    */ 
/*    */       
/* 65 */       results.add(charbossToGauntlet(downfallMod.Act1BossFaced));
/* 66 */       results.add(charbossToGauntlet(downfallMod.Act2BossFaced));
/* 67 */       results.add(charbossToGauntlet(downfallMod.Act3BossFaced));
/*    */       
/* 69 */       if (results.get(2) == Ironclad.ID) {
/* 70 */         Collections.swap(results, 2, 0);
/*    */       }
/* 72 */       else if (results.get(1) == Ironclad.ID) {
/* 73 */         Collections.swap(results, 1, 0);
/*    */       } 
/*    */       
/* 76 */       return results;
/*    */     } 
/* 78 */     bosses.add(Ironclad.ID);
/* 79 */     bosses.add(Silent.ID);
/* 80 */     bosses.add(Defect.ID);
/* 81 */     bosses.add(Watcher.ID);
/* 82 */     bosses.add(Hermit.ID);
/* 83 */     Collections.shuffle(bosses, (Random)AbstractDungeon.cardRandomRng.random);
/* 84 */     for (int i = 0; i < 3; i++) {
/* 85 */       results.add(bosses.get(i));
/*    */     }
/*    */     
/* 88 */     if (results.get(2) == Ironclad.ID) {
/* 89 */       Collections.swap(results, 2, 0);
/*    */     }
/* 91 */     else if (results.get(1) == Ironclad.ID) {
/* 92 */       Collections.swap(results, 1, 0);
/*    */     } 
/* 94 */     return results;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\NeowBossSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */