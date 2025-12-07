/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
/*    */ 
/*    */ public class EnDaggerSpray
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Dagger Spray";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Dagger Spray");
/*    */ 
/*    */   
/*    */   public EnDaggerSpray() {
/* 24 */     super("downfall_Charboss:Dagger Spray", cardStrings.NAME, "green/attack/dagger_spray", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY, AbstractMonster.Intent.ATTACK);
/* 25 */     this.baseDamage = 4;
/* 26 */     this.intentMultiAmt = 2;
/* 27 */     this.baseMagicNumber = this.magicNumber = 2;
/* 28 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DaggerSprayEffect(true), 0.0F));
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 35 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DaggerSprayEffect(true), 0.0F));
/* 36 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getValue() {
/* 41 */     return super.getValue() * 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new EnDaggerSpray();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnDaggerSpray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */