/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyEnergyDownPower;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FastingEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnFasting
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Fasting2";
/*    */   
/*    */   public EnFasting() {
/* 32 */     super("downfall_Charboss:Fasting2", cardStrings.NAME, "purple/power/fasting", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 33 */     this.baseMagicNumber = 3;
/* 34 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     if (m != null) {
/* 39 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new FastingEffect(m.hb.cX, m.hb.cY, Color.CHARTREUSE)));
/*    */     }
/*    */     
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/* 43 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new DexterityPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyEnergyDownPower((AbstractCreature)m, 1, true), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return (AbstractCard)new EnFasting();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeMagicNumber(1);
/* 58 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 64 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Fasting2");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnFasting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */