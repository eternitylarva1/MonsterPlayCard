/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInDiscardAction;
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
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class EnAnger
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Anger";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Anger");
/*    */ 
/*    */   
/*    */   public EnAnger() {
/* 27 */     super("downfall_Charboss:Anger", cardStrings.NAME, "red/attack/anger", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 28 */     this.baseDamage = 6;
/* 29 */     this.tags.add(downfallMod.CHARBOSS_ATTACK);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 35 */     addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new VerticalAuraEffect(Color.FIREBRICK, m.hb.cX, m.hb.cY), 0.0F));
/* 36 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInDiscardAction(makeStatEquivalentCopy(), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnAnger();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnAnger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */