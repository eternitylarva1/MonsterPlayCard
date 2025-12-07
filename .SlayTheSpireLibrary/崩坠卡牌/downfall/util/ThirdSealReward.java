/*    */ package downfall.util;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import basemod.abstracts.CustomReward;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import downfall.patches.RewardItemTypeEnumPatch;
/*    */ 
/*    */ 
/*    */ public class ThirdSealReward
/*    */   extends CustomReward
/*    */ {
/* 18 */   private static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("RewardItem")).TEXT;
/*    */   
/*    */   public ThirdSealReward() {
/* 21 */     super(ImageMaster.REWARD_CARD_NORMAL, TEXT[2], RewardItemTypeEnumPatch.THIRDSEALCARDREWARD);
/* 22 */     this.hb = new Hitbox(460.0F * Settings.xScale, 90.0F * Settings.yScale);
/* 23 */     this.flashTimer = 0.0F;
/* 24 */     this.isDone = false;
/* 25 */     this.ignoreReward = false;
/* 26 */     this.redText = false;
/* 27 */     ReflectionHacks.setPrivate(this, RewardItem.class, "reticleColor", new Color(1.0F, 1.0F, 1.0F, 0.0F));
/* 28 */     this.type = RewardItemTypeEnumPatch.THIRDSEALCARDREWARD;
/* 29 */     ReflectionHacks.setPrivate(this, RewardItem.class, "isBoss", Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean claimReward() {
/* 36 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 37 */       AbstractDungeon.cardRewardScreen.open(this.cards, (RewardItem)this, TEXT[4]);
/* 38 */       AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*    */     } 
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   public void generate_reward_cards() {
/* 44 */     this.cards.clear();
/* 45 */     this.cards.addAll(AbstractDungeon.getRewardCards());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\ThirdSealReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */