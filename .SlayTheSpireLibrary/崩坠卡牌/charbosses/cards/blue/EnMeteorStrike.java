/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.orbs.EnemyPlasma;
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
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnMeteorStrike
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Meteor Strike";
/* 26 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Meteor Strike");
/*    */ 
/*    */   
/*    */   public EnMeteorStrike() {
/* 30 */     super("downfall_Charboss:Meteor Strike", cardStrings.NAME, "blue/attack/meteor_strike", 5, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 31 */     this.showEvokeValue = true;
/* 32 */     this.showEvokeOrbCount = 3;
/* 33 */     this.baseMagicNumber = 3;
/* 34 */     this.magicNumber = this.baseMagicNumber;
/* 35 */     this.baseDamage = 24;
/* 36 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 41 */     return autoPriority() + 8;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 45 */     if (p != null) {
/* 46 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(p.hb.cX, p.hb.cY)));
/*    */     }
/* 48 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */     
/* 50 */     for (int i = 0; i < this.magicNumber; i++) {
/* 51 */       addToBot((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyPlasma()));
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 56 */     if (!this.upgraded) {
/* 57 */       upgradeName();
/* 58 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return (AbstractCard)new EnMeteorStrike();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnMeteorStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */