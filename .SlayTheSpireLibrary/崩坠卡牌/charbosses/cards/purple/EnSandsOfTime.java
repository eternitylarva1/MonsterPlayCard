/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnSandsOfTime
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:SandsOfTime";
/*    */   
/*    */   public EnSandsOfTime() {
/* 23 */     super("downfall_Charboss:SandsOfTime", cardStrings.NAME, "purple/attack/sands_of_time", 4, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 24 */     this.baseDamage = 20;
/* 25 */     this.selfRetain = true;
/*    */   }
/*    */   
/*    */   public EnSandsOfTime(int costReduce) {
/* 29 */     this();
/* 30 */     modifyCostForCombat(-costReduce);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 35 */     return 0;
/*    */   }
/*    */   
/*    */   public void onRetained() {
/* 39 */     addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 43 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnSandsOfTime();
/*    */   }
/*    */ 
/*    */   
/* 59 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SandsOfTime");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnSandsOfTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */