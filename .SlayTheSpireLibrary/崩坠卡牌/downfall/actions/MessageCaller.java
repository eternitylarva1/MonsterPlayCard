/*    */ package downfall.actions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.ui.FtueTip;
/*    */ import downfall.downfallMod;
/*    */ import downfall.tutorials.AutomatonTutorials;
/*    */ import downfall.tutorials.ChampTutorials;
/*    */ import downfall.tutorials.CharbossTutorials;
/*    */ import downfall.tutorials.CollectorTutorials;
/*    */ import downfall.tutorials.GremlinsTutorials;
/*    */ import downfall.tutorials.GuardianTutorials;
/*    */ import downfall.tutorials.HexaghostTutorials;
/*    */ import downfall.tutorials.SlimeBossTutorials;
/*    */ import downfall.tutorials.SneckoTutorials;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class MessageCaller extends AbstractGameAction {
/*    */   public MessageCaller(int code) {
/* 19 */     this.startingDuration = Settings.ACTION_DUR_FAST;
/* 20 */     this.duration = this.startingDuration;
/* 21 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   private float startingDuration;
/*    */   public int code;
/*    */   
/*    */   public void update() {
/* 29 */     if (downfallMod.unseenTutorials[this.code]) {
/* 30 */       switch (this.code) {
/*    */         
/*    */         case 0:
/* 33 */           AbstractDungeon.ftue = (FtueTip)new HermitTutorials();
/*    */           break;
/*    */         case 1:
/* 36 */           AbstractDungeon.ftue = (FtueTip)new GuardianTutorials();
/*    */           break;
/*    */         case 2:
/* 39 */           AbstractDungeon.ftue = (FtueTip)new HexaghostTutorials();
/*    */           break;
/*    */         case 3:
/* 42 */           AbstractDungeon.ftue = (FtueTip)new CharbossTutorials();
/*    */           break;
/*    */         case 4:
/* 45 */           AbstractDungeon.ftue = (FtueTip)new CollectorTutorials();
/*    */           break;
/*    */         case 6:
/* 48 */           AbstractDungeon.ftue = (FtueTip)new SlimeBossTutorials();
/*    */           break;
/*    */         case 7:
/* 51 */           AbstractDungeon.ftue = (FtueTip)new ChampTutorials();
/*    */           break;
/*    */         case 8:
/* 54 */           AbstractDungeon.ftue = (FtueTip)new AutomatonTutorials();
/*    */           break;
/*    */         case 9:
/* 57 */           AbstractDungeon.ftue = (FtueTip)new GremlinsTutorials();
/*    */           break;
/*    */         case 10:
/* 60 */           AbstractDungeon.ftue = (FtueTip)new SneckoTutorials();
/*    */           break;
/*    */       } 
/* 63 */       downfallMod.unseenTutorials[this.code] = false;
/*    */       
/*    */       try {
/* 66 */         downfallMod.saveTutorialsSeen();
/* 67 */         this.isDone = true;
/*    */       }
/* 69 */       catch (IOException e) {
/* 70 */         e.printStackTrace();
/* 71 */         this.isDone = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\MessageCaller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */