/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.EnemyFrost;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnColdSnap
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Cold Snap";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Cold Snap");
/*    */ 
/*    */   
/*    */   public EnColdSnap() {
/* 26 */     super("downfall_Charboss:Cold Snap", cardStrings.NAME, "blue/attack/cold_snap", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 27 */     this.showEvokeValue = true;
/* 28 */     this.showEvokeOrbCount = 1;
/* 29 */     this.baseMagicNumber = 1;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.baseDamage = 6;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */     
/* 37 */     for (int i = 0; i < this.magicNumber; i++) {
/* 38 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyFrost()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 44 */     return autoPriority() + 6;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnColdSnap();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnColdSnap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */