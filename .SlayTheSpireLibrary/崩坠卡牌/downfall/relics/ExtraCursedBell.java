/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class ExtraCursedBell
/*    */   extends CustomRelic {
/*    */   private boolean cardsReceived = true;
/* 20 */   public static final String ID = downfallMod.makeID("ExtraCursedBell");
/* 21 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/ExtraCursedBell.png"));
/* 22 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/ExtraCursedBell.png"));
/*    */   
/*    */   public ExtraCursedBell() {
/* 25 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 30 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onEquip() {
/* 34 */     this.cardsReceived = false;
/* 35 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 36 */     CurseOfTheBell curseOfTheBell = new CurseOfTheBell();
/* 37 */     UnlockTracker.markCardAsSeen(((AbstractCard)curseOfTheBell).cardID);
/* 38 */     for (int i = 0; i < 1; i++)
/* 39 */       group.addToBottom(curseOfTheBell.makeCopy()); 
/* 40 */     AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
/* 41 */     CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
/*    */   }
/*    */   
/*    */   public void update() {
/* 45 */     super.update();
/* 46 */     if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
/* 47 */       AbstractDungeon.combatRewardScreen.open();
/* 48 */       AbstractDungeon.combatRewardScreen.rewards.clear();
/* 49 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON)));
/* 50 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON)));
/* 51 */       AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON)));
/*    */ 
/*    */       
/* 54 */       AbstractDungeon.combatRewardScreen.positionRewards();
/* 55 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
/* 56 */       this.cardsReceived = true;
/* 57 */       (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*    */     } 
/*    */     
/* 60 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 61 */       CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
/* 62 */       CardCrawlGame.sound.playA("souls1", -0.1F);
/* 63 */       flash();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\ExtraCursedBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */