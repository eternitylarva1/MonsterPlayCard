/*    */ package downfall.cardmods;
/*    */ 
/*    */ import basemod.abstracts.AbstractCardModifier;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import slimebound.actions.CommandAction;
/*    */ 
/*    */ public class CommandMod
/*    */   extends AbstractCardModifier
/*    */ {
/*    */   public String modifyDescription(String rawDescription, AbstractCard card) {
/* 16 */     return rawDescription + (CardCrawlGame.languagePack.getUIString("slimeboundmod:CommandMod")).TEXT[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
/* 21 */     super.onUse(card, target, action);
/* 22 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CommandAction());
/* 23 */     checkMinionMaster();
/*    */   }
/*    */   
/*    */   public static void checkMinionMaster() {
/* 27 */     if (AbstractDungeon.player.hasPower("Slimebound:BuffSecondarySlimeEffectsPower")) {
/* 28 */       for (int i = 0; i < (AbstractDungeon.player.getPower("Slimebound:BuffSecondarySlimeEffectsPower")).amount; i++) {
/* 29 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CommandAction());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCardModifier makeCopy() {
/* 36 */     return new CommandMod();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cardmods\CommandMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */