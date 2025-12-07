/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.AbstractEnemyOrb;
/*    */ import charbosses.orbs.EnemyDark;
/*    */ import charbosses.orbs.EnemyFrost;
/*    */ import charbosses.orbs.EnemyLightning;
/*    */ import charbosses.vfx.EnemyRainbowCardEffect;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnRainbow
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Rainbow";
/*    */   
/*    */   public EnRainbow() {
/* 32 */     super("downfall_Charboss:Rainbow", cardStrings.NAME, "blue/skill/rainbow", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 33 */     this.showEvokeValue = true;
/* 34 */     this.showEvokeOrbCount = 3;
/* 35 */     this.exhaust = true;
/* 36 */     this.alwaysDisplayText = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 40 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new EnemyRainbowCardEffect()));
/* 41 */     addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/* 42 */     addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyFrost()));
/* 43 */     addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyDark()));
/*    */   }
/*    */   
/*    */   public static int getFocusAmountSafe() {
/* 47 */     if (AbstractCharBoss.boss.hasPower("Focus")) {
/* 48 */       return (AbstractCharBoss.boss.getPower("Focus")).amount;
/*    */     }
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String overrideIntentText() {
/* 55 */     return "(" + (3 + AbstractEnemyOrb.masterPretendFocus + getFocusAmountSafe()) + ")";
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 59 */     if (!this.upgraded) {
/* 60 */       upgradeName();
/* 61 */       this.exhaust = false;
/* 62 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 63 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 70 */     return 10;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 74 */     return (AbstractCard)new EnRainbow();
/*    */   }
/*    */ 
/*    */   
/* 78 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Rainbow");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnRainbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */