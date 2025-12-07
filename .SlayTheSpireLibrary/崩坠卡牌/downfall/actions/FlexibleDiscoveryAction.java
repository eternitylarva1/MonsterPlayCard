/*    */ package downfall.actions;
/*    */ 
/*    */ import basemod.BaseMod;
/*    */ import basemod.abstracts.AbstractCardModifier;
/*    */ import basemod.helpers.CardModifierManager;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.CardRewardScreen;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ @Deprecated
/*    */ public class FlexibleDiscoveryAction
/*    */   extends AbstractGameAction {
/*    */   private boolean retrieveCard = false;
/*    */   private final ArrayList<AbstractCard> cards;
/*    */   private boolean costsZeroThisTurn;
/*    */   private Consumer<AbstractCard> callback;
/*    */   private AbstractCardModifier cardModifier;
/*    */   private boolean skippable;
/*    */   
/*    */   public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn) {
/* 28 */     this(cards, costsZeroThisTurn, false, (Consumer<AbstractCard>)null, (AbstractCardModifier)null);
/*    */   }
/*    */   
/*    */   public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn, AbstractCardModifier cardModifier) {
/* 32 */     this(cards, costsZeroThisTurn, false, (Consumer<AbstractCard>)null, cardModifier);
/*    */   }
/*    */   
/*    */   public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, Consumer<AbstractCard> callback, boolean costsZeroThisTurn) {
/* 36 */     this(cards, costsZeroThisTurn, false, callback, (AbstractCardModifier)null);
/*    */   }
/*    */   
/*    */   public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn, boolean skippable, AbstractCardModifier cardModifier) {
/* 40 */     this(cards, costsZeroThisTurn, skippable, (Consumer<AbstractCard>)null, cardModifier);
/*    */   }
/*    */   
/*    */   public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn, boolean skippable, Consumer<AbstractCard> callback, AbstractCardModifier cardModifier) {
/* 44 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 45 */     this.duration = Settings.ACTION_DUR_FAST;
/* 46 */     this.cards = cards;
/* 47 */     this.costsZeroThisTurn = costsZeroThisTurn;
/* 48 */     this.cardModifier = cardModifier;
/* 49 */     this.callback = callback;
/* 50 */     this.skippable = skippable;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 55 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 56 */       AbstractDungeon.cardRewardScreen.customCombatOpen(this.cards, CardRewardScreen.TEXT[1], this.skippable);
/* 57 */       AbstractDungeon.cardRewardScreen.rewardGroup = this.cards;
/*    */       
/* 59 */       for (AbstractCard tmp : AbstractDungeon.cardRewardScreen.rewardGroup) {
/* 60 */         UnlockTracker.markCardAsSeen(tmp.cardID);
/*    */       }
/* 62 */       tickDuration();
/*    */     } else {
/* 64 */       if (!this.retrieveCard) {
/* 65 */         if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
/* 66 */           AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/* 67 */           if (this.callback != null)
/*    */           {
/* 69 */             this.callback.accept(disCard);
/*    */           }
/* 71 */           if (this.costsZeroThisTurn) {
/* 72 */             disCard.modifyCostForCombat(-9);
/*    */           }
/* 74 */           if (this.cardModifier != null) {
/* 75 */             CardModifierManager.addModifier(disCard, this.cardModifier);
/*    */           }
/* 77 */           disCard.current_x = -1000.0F * Settings.scale;
/* 78 */           if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
/* 79 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */           } else {
/* 81 */             AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */           } 
/*    */           
/* 84 */           AbstractDungeon.cardRewardScreen.discoveryCard = null;
/*    */         } 
/*    */         
/* 87 */         this.retrieveCard = true;
/*    */       } 
/*    */       
/* 90 */       tickDuration();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\FlexibleDiscoveryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */