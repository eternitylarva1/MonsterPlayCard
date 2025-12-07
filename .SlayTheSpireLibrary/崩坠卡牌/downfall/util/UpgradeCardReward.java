/*    */ package downfall.util;
/*    */ 
/*    */ import basemod.abstracts.CustomReward;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.downfallMod;
/*    */ import downfall.patches.RewardItemTypeEnumPatch;
/*    */ 
/*    */ public class UpgradeCardReward extends CustomReward {
/* 13 */   public static final String ID = downfallMod.makeID("UpgradeCardReward");
/* 14 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
/*    */   
/*    */   public UpgradeCardReward() {
/* 17 */     super(TextureLoader.getTexture("downfallResources/images/rewards/upgrade_card_reward.png"), TEXT[0], RewardItemTypeEnumPatch.UPGRADECARD);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean claimReward() {
/* 22 */     downfallMod.choosingUpgradeCard = true;
/* 23 */     if (AbstractDungeon.isScreenUp) {
/* 24 */       AbstractDungeon.dynamicBanner.hide();
/* 25 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/* 26 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*    */     } 
/*    */     
/* 29 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/* 30 */     CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */     
/* 32 */     for (AbstractCard card : (AbstractDungeon.player.masterDeck.getUpgradableCards()).group) {
/* 33 */       tmp.addToTop(card);
/*    */     }
/*    */     
/* 36 */     if (tmp.group.isEmpty()) {
/* 37 */       downfallMod.choosingUpgradeCard = false;
/*    */     } else {
/* 39 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, TEXT[1], true, false, false, false);
/*    */     } 
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\UpgradeCardReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */