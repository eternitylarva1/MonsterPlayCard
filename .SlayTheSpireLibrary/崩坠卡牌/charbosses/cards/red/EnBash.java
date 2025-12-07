/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnBash
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Bash";
/* 26 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Bash");
/*    */ 
/*    */   
/*    */   public EnBash() {
/* 30 */     super("downfall_Charboss:Bash", cardStrings.NAME, "red/attack/bash", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 31 */     this.baseDamage = 8;
/* 32 */     this.baseMagicNumber = 2;
/* 33 */     this.magicNumber = this.baseMagicNumber;
/* 34 */     this.tags.add(downfallMod.CHARBOSS_SETUP);
/* 35 */     this.artifactConsumedIfPlayed = 1;
/* 36 */     if (AbstractCharBoss.boss.hasRelic("Champion Belt")) {
/* 37 */       this.artifactConsumedIfPlayed = 2;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 43 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/* 45 */     if (!p.hasPower("Artifact") && 
/* 46 */       AbstractCharBoss.boss.hasRelic("Champion Belt")) {
/* 47 */       AbstractCharBoss.boss.getRelic("Champion Belt").onTrigger();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeDamage(2);
/* 58 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return (AbstractCard)new EnThunderclap();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnBash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */