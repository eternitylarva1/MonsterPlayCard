/*    */ package downfall.patches;
/*    */ 
/*    */ import charbosses.bosses.Merchant.CharBossMerchant;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
/*    */ import slimebound.SlimeboundMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = ProceedButton.class, method = "goToDoubleBoss")
/*    */ public class ProceedButtonPatch2
/*    */ {
/*    */   public static SpireReturn Prefix(ProceedButton __instance) {
/* 23 */     if (EvilModeCharacterSelect.evilMode) {
/* 24 */       SlimeboundMod.logger.info("HIT THE DOUBLE BOSS PATCH!");
/* 25 */       AbstractDungeon.bossList.clear();
/* 26 */       AbstractDungeon.bossList.add(CharBossMerchant.ID);
/* 27 */       AbstractDungeon.bossKey = CharBossMerchant.ID;
/* 28 */       CardCrawlGame.music.fadeOutBGM();
/* 29 */       CardCrawlGame.music.fadeOutTempBGM();
/* 30 */       MapRoomNode node = new MapRoomNode(-1, 15);
/* 31 */       node.room = (AbstractRoom)new MonsterRoomBoss();
/* 32 */       AbstractDungeon.nextRoom = node;
/* 33 */       AbstractDungeon.closeCurrentScreen();
/* 34 */       AbstractDungeon.nextRoomTransitionStart();
/* 35 */       __instance.hide();
/* 36 */       return SpireReturn.Return(null);
/*    */     } 
/* 38 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ProceedButtonPatch2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */