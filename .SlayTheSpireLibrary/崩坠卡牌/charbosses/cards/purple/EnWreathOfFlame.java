/*    */ package charbosses.cards.purple;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.general.EnemyVigorPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
/*    */ 
/*    */ public class EnWreathOfFlame extends AbstractBossCard {
/*    */   public EnWreathOfFlame() {
/* 20 */     super("downfall_Charboss:WreathOfFlame", cardStrings.NAME, "purple/skill/wreathe_of_flame", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.BUFF);
/* 21 */     this.baseMagicNumber = 5;
/* 22 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   public static final String ID = "downfall_Charboss:WreathOfFlame";
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 26 */     if (Settings.FAST_MODE) {
/* 27 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new FlameBarrierEffect(m.hb.cX, m.hb.cY), 0.1F));
/*    */     } else {
/* 29 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new FlameBarrierEffect(m.hb.cX, m.hb.cY), 0.5F));
/*    */     } 
/* 31 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new EnemyVigorPower((AbstractCreature)this.owner, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 36 */     if (!this.upgraded) {
/* 37 */       upgradeName();
/* 38 */       upgradeMagicNumber(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new EnWreathOfFlame();
/*    */   }
/*    */ 
/*    */   
/* 48 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WreathOfFlame");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnWreathOfFlame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */