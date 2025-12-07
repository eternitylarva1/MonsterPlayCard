/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.AbstractEnemyOrb;
/*    */ import charbosses.orbs.EnemyLightning;
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
/*    */ public class EnBallLightning
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Ball Lightning";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ball Lightning");
/*    */ 
/*    */   
/*    */   public EnBallLightning() {
/* 27 */     super("downfall_Charboss:Ball Lightning", cardStrings.NAME, "blue/attack/ball_lightning", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 28 */     this.showEvokeValue = true;
/* 29 */     this.showEvokeOrbCount = 1;
/* 30 */     this.baseMagicNumber = 1;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/* 32 */     this.baseDamage = 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public String overrideIntentText() {
/* 37 */     return this.intentDmg + "+ (" + (3 + AbstractEnemyOrb.masterPretendFocus + EnZap.getFocusAmountSafe()) + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 42 */     return autoPriority() + 8;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 46 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */     
/* 48 */     for (int i = 0; i < this.magicNumber; i++) {
/* 49 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 54 */     if (!this.upgraded) {
/* 55 */       upgradeName();
/* 56 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return (AbstractCard)new EnBallLightning();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnBallLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */