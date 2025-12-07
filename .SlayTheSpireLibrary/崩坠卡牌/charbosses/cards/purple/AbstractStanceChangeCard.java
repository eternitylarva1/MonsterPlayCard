/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public abstract class AbstractStanceChangeCard extends AbstractBossCard {
/*    */   public AbstractStanceChangeCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
/* 10 */     super(id, name, img, cost, rawDescription, type, color, rarity, target);
/*    */   }
/*    */   
/*    */   public AbstractStanceChangeCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractMonster.Intent intent) {
/* 14 */     super(id, name, img, cost, rawDescription, type, color, rarity, target, intent);
/*    */   }
/*    */   
/*    */   public AbstractStanceChangeCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractMonster.Intent intent, boolean isCustomCard) {
/* 18 */     super(id, name, img, cost, rawDescription, type, color, rarity, target, intent, isCustomCard);
/*    */   }
/*    */   
/*    */   public AbstractStanceChangeCard(AbstractCard baseCard) {
/* 22 */     super(baseCard);
/*    */   }
/*    */   
/*    */   public abstract AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance paramAbstractEnemyStance);
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\AbstractStanceChangeCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */