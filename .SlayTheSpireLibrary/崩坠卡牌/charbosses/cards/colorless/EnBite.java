/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class EnBite
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Bite";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Bite");
/*    */ 
/*    */   
/*    */   public EnBite() {
/* 27 */     super("downfall_Charboss:Bite", cardStrings.NAME, "colorless/attack/bite", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 28 */     this.baseDamage = 7;
/* 29 */     this.baseMagicNumber = 2;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.tags.add(AbstractCard.CardTags.HEALING);
/* 32 */     this.tags.add(downfallMod.CHARBOSS_ATTACK);
/* 33 */     this.magicValue = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     if (m != null) {
/* 39 */       if (Settings.FAST_MODE) {
/* 40 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
/*    */       } else {
/* 42 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
/*    */       } 
/*    */     }
/* 45 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 46 */     addToBot((AbstractGameAction)new HealAction((AbstractCreature)m, (AbstractCreature)m, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(1);
/* 54 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new EnBite();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnBite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */