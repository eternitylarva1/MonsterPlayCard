/*    */ package charbosses.cards.red;
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
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
/*    */ 
/*    */ public class EnHeavyBlade
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Heavy Blade";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Heavy Blade");
/*    */ 
/*    */   
/*    */   public EnHeavyBlade() {
/* 25 */     super("downfall_Charboss:Heavy Blade", cardStrings.NAME, "red/attack/heavy_blade", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 26 */     this.baseDamage = 14;
/* 27 */     this.baseMagicNumber = 3;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     if (m != null) {
/* 33 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(p.hb.cX + p.hb.width / 4.0F, p.hb.cY - p.hb.height / 4.0F)));
/*    */     }
/*    */     
/* 36 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 55 */     AbstractPower strength = this.owner.getPower("Strength");
/* 56 */     if (strength != null) {
/* 57 */       strength.amount *= this.magicNumber;
/*    */     }
/*    */     
/* 60 */     super.calculateCardDamage(mo);
/* 61 */     if (strength != null) {
/* 62 */       strength.amount /= this.magicNumber;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 68 */     if (!this.upgraded) {
/* 69 */       upgradeName();
/* 70 */       upgradeMagicNumber(2);
/* 71 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 72 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 79 */     return (AbstractCard)new EnHeavyBlade();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnHeavyBlade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */