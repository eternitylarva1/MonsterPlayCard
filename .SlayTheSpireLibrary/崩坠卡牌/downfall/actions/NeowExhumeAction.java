/*    */ package downfall.actions;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import slimebound.SlimeboundMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class NeowExhumeAction
/*    */   extends AbstractGameAction
/*    */ {
/* 20 */   public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());
/*    */   
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public NeowExhumeAction() {
/* 25 */     this.p = AbstractDungeon.player;
/*    */     
/* 27 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, this.amount);
/*    */     
/* 29 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*    */     
/* 31 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 38 */     int cardsReturned = 0;
/* 39 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 41 */       if (this.p.exhaustPile.isEmpty()) {
/*    */         
/* 43 */         logger.info("Exhaust is empty");
/* 44 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 49 */       logger.info("Add to hand");
/* 50 */       AbstractCard c = AbstractDungeon.player.exhaustPile.getRandomCard(AbstractDungeon.cardRandomRng);
/*    */       
/* 52 */       this.p.drawPile.addToTop(c);
/*    */       
/* 54 */       c.unfadeOut();
/* 55 */       c.unhover();
/* 56 */       this.p.exhaustPile.removeCard(c);
/*    */       
/* 58 */       c.flash(Color.GOLD.cpy());
/*    */ 
/*    */       
/* 61 */       this.isDone = true;
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 67 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\NeowExhumeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */