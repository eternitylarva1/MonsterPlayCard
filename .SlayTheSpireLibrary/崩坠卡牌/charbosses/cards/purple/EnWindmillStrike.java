/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
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
/*    */ public class EnWindmillStrike
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:WindmillStrike";
/*    */   
/*    */   public EnWindmillStrike() {
/* 22 */     super("downfall_Charboss:WindmillStrike", cardStrings.NAME, "purple/attack/windmill_strike", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 23 */     this.baseDamage = 7;
/* 24 */     this.baseMagicNumber = 4;
/* 25 */     this.magicNumber = this.baseMagicNumber;
/* 26 */     this.selfRetain = true;
/* 27 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/*    */   }
/*    */   
/*    */   public void onRetained() {
/* 31 */     upgradeDamage(this.magicNumber);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeDamage(3);
/* 42 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 49 */     if (this.cost > 0) {
/* 50 */       return 0;
/*    */     }
/* 52 */     return autoPriority() * 5;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnWindmillStrike();
/*    */   }
/*    */ 
/*    */   
/* 60 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WindmillStrike");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnWindmillStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */