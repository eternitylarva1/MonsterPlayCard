/*    */ package charbosses.powers.cardpowers;
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.colorless.EnBandage;
/*    */ import charbosses.cards.colorless.EnBlind;
/*    */ import charbosses.cards.colorless.EnDramaticEntrance;
/*    */ import charbosses.cards.colorless.EnGoodInstincts;
/*    */ import charbosses.cards.colorless.EnHandOfGreed;
/*    */ import charbosses.cards.colorless.EnPanacea;
/*    */ import charbosses.cards.colorless.EnPanicButton;
/*    */ import charbosses.cards.colorless.EnSadisticNature;
/*    */ import charbosses.cards.colorless.EnTrip;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.random.Random;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnemyMagnetismPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Magnetism";
/*    */   private static final PowerStrings powerStrings;
/* 25 */   public static CardGroup srcColorlessCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL); public static final String NAME; public static final String SINGULAR_DESCRIPTION; public static final String PLURAL_DESCRIPTION;
/*    */   
/*    */   public EnemyMagnetismPower(AbstractCreature owner, int cardAmount) {
/* 28 */     this.name = NAME;
/* 29 */     this.ID = "Magnetism";
/* 30 */     this.owner = owner;
/* 31 */     this.amount = cardAmount;
/* 32 */     updateDescription();
/* 33 */     loadRegion("magnet");
/* 34 */     if (srcColorlessCardPool.isEmpty()) {
/* 35 */       srcColorlessCardPool.addToTop((AbstractCard)new EnBlind());
/* 36 */       srcColorlessCardPool.addToTop((AbstractCard)new EnDramaticEntrance());
/* 37 */       srcColorlessCardPool.addToTop((AbstractCard)new EnGoodInstincts());
/* 38 */       srcColorlessCardPool.addToTop((AbstractCard)new EnPanacea());
/* 39 */       srcColorlessCardPool.addToTop((AbstractCard)new EnSwiftStrike());
/* 40 */       srcColorlessCardPool.addToTop((AbstractCard)new EnTrip());
/* 41 */       srcColorlessCardPool.addToTop((AbstractCard)new EnSadisticNature());
/* 42 */       srcColorlessCardPool.addToTop((AbstractCard)new EnHandOfGreed());
/* 43 */       srcColorlessCardPool.addToTop((AbstractCard)new EnTheBomb());
/* 44 */       srcColorlessCardPool.addToTop((AbstractCard)new EnBandage());
/* 45 */       srcColorlessCardPool.addToTop((AbstractCard)new EnPanicButton());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 50 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 51 */       flash();
/*    */       
/* 53 */       for (int i = 0; i < this.amount; i++) {
/* 54 */         addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction(returnBossColorlessCard().makeCopy(), 1, false));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 61 */     this.fontScale = 8.0F;
/* 62 */     this.amount += stackAmount;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 66 */     if (this.amount > 1) {
/* 67 */       this.description = String.format(PLURAL_DESCRIPTION, new Object[] { Integer.valueOf(this.amount) });
/*    */     } else {
/* 69 */       this.description = String.format(SINGULAR_DESCRIPTION, new Object[] { Integer.valueOf(this.amount) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static AbstractCard returnBossColorlessCard() {
/* 75 */     return returnBossColorlessCard(AbstractDungeon.cardRandomRng);
/*    */   }
/*    */   
/*    */   public static AbstractCard returnBossColorlessCard(Random rng) {
/* 79 */     ArrayList<AbstractCard> list = new ArrayList<>();
/* 80 */     Iterator<AbstractCard> var2 = srcColorlessCardPool.group.iterator();
/*    */     
/* 82 */     while (var2.hasNext()) {
/* 83 */       AbstractCard c = var2.next();
/* 84 */       list.add(c);
/*    */     } 
/*    */     
/* 87 */     return list.get(rng.random(list.size() - 1));
/*    */   }
/*    */   
/*    */   static {
/* 91 */     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magnetism");
/* 92 */     NAME = powerStrings.NAME;
/* 93 */     SINGULAR_DESCRIPTION = powerStrings.DESCRIPTIONS[0];
/* 94 */     PLURAL_DESCRIPTION = powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyMagnetismPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */