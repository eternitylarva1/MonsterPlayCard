/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.cards.OctoChoiceCard;
/*    */ import downfall.util.OctopusCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import expansioncontent.patches.CenterGridCardSelectScreen;
/*    */ 
/*    */ public class OctoChoiceAction extends AbstractGameAction {
/* 17 */   private CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED); private boolean pickCard = false;
/*    */   private OctopusCard funCard;
/* 19 */   public String[] TEXT = (CardCrawlGame.languagePack.getUIString("downfall:OctoChoiceAction")).TEXT;
/*    */   
/*    */   public OctoChoiceAction(AbstractMonster m, OctopusCard card) {
/* 22 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 23 */     this.actionType = AbstractGameAction.ActionType.WAIT;
/* 24 */     this.funCard = card;
/* 25 */     this.target = (AbstractCreature)m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (this.funCard instanceof AbstractCard) {
/* 31 */       ((AbstractCard)this.funCard).applyPowers();
/* 32 */       ((AbstractCard)this.funCard).calculateCardDamage((AbstractMonster)this.target);
/*    */     } 
/* 34 */     for (OctoChoiceCard q : this.funCard.choiceList()) {
/* 35 */       this.group.addToTop((AbstractCard)q);
/*    */     }
/* 37 */     if (this.duration == Settings.ACTION_DUR_XFAST && !this.group.isEmpty()) {
/* 38 */       this.pickCard = true;
/* 39 */       CenterGridCardSelectScreen.centerGridSelect = true;
/* 40 */       AbstractDungeon.gridSelectScreen.open(this.group, 1, this.TEXT[0], false);
/* 41 */     } else if (this.pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 42 */       OctoChoiceCard cardChoice = new OctoChoiceCard("null", "null", expansionContentMod.makeCardPath("AwakenDeath.png"), this.TEXT[1]);
/* 43 */       if (this.pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 44 */         this.pickCard = false;
/* 45 */         cardChoice = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 46 */         AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 47 */         CenterGridCardSelectScreen.centerGridSelect = false;
/*    */       } 
/* 49 */       this.funCard.doChoiceStuff((AbstractMonster)this.target, cardChoice);
/*    */       
/* 51 */       this.isDone = true;
/* 52 */     } else if (this.group.isEmpty()) {
/* 53 */       this.isDone = true;
/*    */     } 
/* 55 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\OctoChoiceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */