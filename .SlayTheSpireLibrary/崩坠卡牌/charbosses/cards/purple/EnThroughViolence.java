/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
/*    */ 
/*    */ 
/*    */ public class EnThroughViolence
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:ThroughViolence";
/*    */   
/*    */   public EnThroughViolence() {
/* 26 */     super("downfall_Charboss:ThroughViolence", cardStrings.NAME, "colorless/attack/through_violence", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 27 */     this.baseDamage = 20;
/* 28 */     this.selfRetain = true;
/* 29 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     if (m != null) {
/* 34 */       if (Settings.FAST_MODE) {
/* 35 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.VIOLET)));
/*    */       } else {
/* 37 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.VIOLET), 0.4F));
/*    */       } 
/*    */     }
/*    */     
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 45 */     if (!this.upgraded) {
/* 46 */       upgradeName();
/* 47 */       upgradeDamage(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new EnThroughViolence();
/*    */   }
/*    */ 
/*    */   
/* 57 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ThroughViolence");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnThroughViolence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */