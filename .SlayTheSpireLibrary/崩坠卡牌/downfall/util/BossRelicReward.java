/*    */ package downfall.util;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.cards.OctoChoiceCard;
/*    */ import downfall.downfallMod;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import expansioncontent.patches.CenterGridCardSelectScreen;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class BossRelicReward
/*    */   extends RewardItem
/*    */ {
/* 19 */   public static final String ID = downfallMod.makeID("BossRelicReward");
/* 20 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean claimReward() {
/* 34 */     downfallMod.choosingBossRelic = true;
/* 35 */     if (AbstractDungeon.isScreenUp) {
/* 36 */       AbstractDungeon.dynamicBanner.hide();
/* 37 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/* 38 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*    */     } 
/*    */     
/* 41 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/* 42 */     ArrayList<AbstractCard> tmp = new ArrayList<>();
/*    */     
/* 44 */     for (String q : AbstractDungeon.bossRelicPool) {
/* 45 */       String d = q;
/* 46 */       if (q.contains(":")) {
/* 47 */         d = q.replaceAll("(.)([A-Z])", "$1 $2").substring(q.indexOf(":") + 1);
/* 48 */         d = d.trim();
/*    */       } 
/* 50 */       tmp.add(new OctoChoiceCard(q, d, expansionContentMod.makeCardPath("QuickGuardian.png"), TEXT[1] + d + TEXT[2]));
/*    */     } 
/*    */     
/* 53 */     CardGroup qqq = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 54 */     for (int i = 0; i < 3; i++) {
/* 55 */       qqq.addToTop(tmp.remove(AbstractDungeon.cardRandomRng.random(tmp.size() - 1)));
/*    */     }
/*    */     
/* 58 */     CenterGridCardSelectScreen.centerGridSelect = true;
/* 59 */     AbstractDungeon.gridSelectScreen.open(qqq, 1, TEXT[3], false, false, false, false);
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\BossRelicReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */