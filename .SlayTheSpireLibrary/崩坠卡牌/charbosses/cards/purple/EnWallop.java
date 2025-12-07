/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.WallopAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnWallop
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Wallop";
/*    */   
/*    */   public EnWallop() {
/* 21 */     super("downfall_Charboss:Wallop", cardStrings.NAME, "purple/attack/wallop", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 22 */     this.baseDamage = 9;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 26 */     addToBot((AbstractGameAction)new WallopAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn)));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 30 */     if (!this.upgraded) {
/* 31 */       upgradeName();
/* 32 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 39 */     return autoPriority() * 2;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return (AbstractCard)new EnWallop();
/*    */   }
/*    */ 
/*    */   
/* 47 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Wallop");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnWallop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */