/*    */ package charbosses.cards.hermit;
/*    */ import charbosses.powers.cardpowers.EnemyShadowCloakPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import hermit.cards.ShadowCloak;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnShadowCloak extends AbstractHermitBossCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ShadowCloak.ID); public static final String ID = "downfall_Charboss:ShadowClock";
/*    */   
/*    */   public EnShadowCloak() {
/* 19 */     super("downfall_Charboss:ShadowClock", cardStrings.NAME, "hermitResources/images/cards/shadow_cloak.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 20 */     this.baseMagicNumber = this.magicNumber = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 25 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyShadowCloakPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 30 */     if (!this.upgraded) {
/* 31 */       upgradeName();
/* 32 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 38 */     return (AbstractCard)new EnShadowCloak();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnShadowCloak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */