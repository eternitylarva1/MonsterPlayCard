/*    */ package downfall.util;
/*    */ 
/*    */ import basemod.abstracts.CustomReward;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import downfall.downfallMod;
/*    */ import downfall.patches.RewardItemTypeEnumPatch;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import expansioncontent.patches.ShopBossPatch;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BossCardReward extends CustomReward {
/* 18 */   public static final String ID = downfallMod.makeID("BossCardReward");
/* 19 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
/*    */   
/*    */   public BossCardReward() {
/* 22 */     super(TextureLoader.getTexture("downfallResources/images/rewards/placeholder.png"), TEXT[0], RewardItemTypeEnumPatch.BOSSCARD);
/* 23 */     this.cards.clear();
/* 24 */     this.cards.addAll(getCards());
/*    */   }
/*    */   
/*    */   public static ArrayList<AbstractCard> getCards() {
/* 28 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/* 29 */     int numCards = 3;
/*    */     
/* 31 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 32 */       numCards = r.changeNumberOfCardsInReward(numCards);
/*    */     }
/*    */     
/* 35 */     if (ModHelper.isModEnabled("Binary")) {
/* 36 */       numCards--;
/*    */     }
/* 38 */     while (cardsList.size() < numCards) {
/* 39 */       AbstractCard q = getBossCard();
/* 40 */       if (!cardListDuplicate(cardsList, q) && q.rarity != AbstractCard.CardRarity.SPECIAL) {
/* 41 */         q = q.makeCopy();
/* 42 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 43 */           r.onPreviewObtainCard(q);
/*    */         }
/* 45 */         cardsList.add(q);
/*    */       } 
/*    */     } 
/* 48 */     return cardsList;
/*    */   }
/*    */   
/*    */   public static AbstractCard getBossCard() {
/* 52 */     ArrayList<AbstractCard> potentialCardsList = new ArrayList<>();
/* 53 */     for (AbstractCard q : CardLibrary.getAllCards()) {
/* 54 */       if (q.hasTag(expansionContentMod.STUDY) && ShopBossPatch.okayToSpawn(q)) {
/* 55 */         potentialCardsList.add(q);
/*    */       }
/*    */     } 
/* 58 */     return potentialCardsList.get(AbstractDungeon.merchantRng.random(0, potentialCardsList.size() - 1));
/*    */   }
/*    */   
/*    */   public static boolean cardListDuplicate(ArrayList<AbstractCard> cardsList, AbstractCard card) {
/* 62 */     for (AbstractCard alreadyHave : cardsList) {
/* 63 */       if (alreadyHave.cardID.equals(card.cardID)) {
/* 64 */         return true;
/*    */       }
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean claimReward() {
/* 72 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 73 */       AbstractDungeon.cardRewardScreen.open(this.cards, (RewardItem)this, TEXT[1]);
/* 74 */       AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*    */     } 
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\BossCardReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */