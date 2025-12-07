/*    */ package downfall.util;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import expansioncontent.patches.CenterGridCardSelectScreen;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class SelectCardsCenteredAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private Consumer<List<AbstractCard>> callback;
/*    */   private String text;
/*    */   private boolean anyNumber;
/*    */   private CardGroup selectGroup;
/*    */   
/*    */   public SelectCardsCenteredAction(ArrayList<AbstractCard> group, int amount, String textForSelect, boolean anyNumber, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback) {
/* 25 */     this.amount = amount;
/* 26 */     this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
/* 27 */     this.text = textForSelect;
/* 28 */     this.anyNumber = anyNumber;
/* 29 */     this.callback = callback;
/* 30 */     this.selectGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 31 */     this.selectGroup.group.addAll((Collection)group.stream().distinct().filter(cardFilter).collect(Collectors.toList()));
/*    */   }
/*    */   
/*    */   public SelectCardsCenteredAction(ArrayList<AbstractCard> group, String textForSelect, boolean anyNumber, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback) {
/* 35 */     this(group, 1, textForSelect, anyNumber, cardFilter, callback);
/*    */   }
/*    */   
/*    */   public SelectCardsCenteredAction(ArrayList<AbstractCard> group, String textForSelect, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback) {
/* 39 */     this(group, 1, textForSelect, false, cardFilter, callback);
/*    */   }
/*    */   
/*    */   public SelectCardsCenteredAction(ArrayList<AbstractCard> group, String textForSelect, Consumer<List<AbstractCard>> callback) {
/* 43 */     this(group, 1, textForSelect, false, c -> true, callback);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectCardsCenteredAction(ArrayList<AbstractCard> group, int amount, String textForSelect, Consumer<List<AbstractCard>> callback) {
/* 49 */     this(group, amount, textForSelect, false, c -> true, callback);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 55 */     if (this.duration == this.startDuration) {
/* 56 */       if (this.selectGroup.size() == 0 || this.callback == null) {
/* 57 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 61 */       if (this.selectGroup.size() <= this.amount && !this.anyNumber) {
/* 62 */         this.callback.accept(this.selectGroup.group);
/* 63 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 67 */       CenterGridCardSelectScreen.centerGridSelect = true;
/* 68 */       AbstractDungeon.gridSelectScreen.open(this.selectGroup, this.amount, this.text, this.anyNumber);
/* 69 */       tickDuration();
/*    */     } 
/*    */     
/* 72 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 73 */       CenterGridCardSelectScreen.centerGridSelect = false;
/* 74 */       this.callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
/* 75 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 76 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 77 */       this.isDone = true;
/*    */     } else {
/* 79 */       tickDuration();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\SelectCardsCenteredAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */