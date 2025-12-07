/*    */ package downfall.util;
/*    */ import automaton.AutomatonMod;
/*    */ import basemod.abstracts.CustomReward;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.colorless.JAX;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import downfall.patches.RewardItemTypeEnumPatch;
/*    */ 
/*    */ public class JaxReward extends CustomReward {
/* 13 */   public static final String ID = AutomatonMod.makeID("JaxReward");
/* 14 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("bronze:SpecificCardReward")).TEXT;
/*    */   
/*    */   public JaxReward() {
/* 17 */     super(TextureLoader.getTexture("downfallResources/images/rewards/placeholder.png"), "ERROR", RewardItemTypeEnumPatch.JAXCARD);
/* 18 */     this.cards.clear();
/* 19 */     JAX jax = new JAX();
/* 20 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 21 */       r.onPreviewObtainCard((AbstractCard)jax);
/*    */     }
/* 23 */     this.cards.add(jax);
/* 24 */     this.text = TEXT[0] + ((AbstractCard)this.cards.get(0)).name + TEXT[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean claimReward() {
/* 29 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 30 */       AbstractDungeon.cardRewardScreen.open(this.cards, (RewardItem)this, TEXT[2]);
/* 31 */       AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\JaxReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */