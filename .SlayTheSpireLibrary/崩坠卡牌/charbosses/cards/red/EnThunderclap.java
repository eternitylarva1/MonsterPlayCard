/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnThunderclap
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Thunderclap";
/* 27 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Thunderclap");
/*    */ 
/*    */   
/*    */   public EnThunderclap() {
/* 31 */     super("downfall_Charboss:Thunderclap", cardStrings.NAME, "red/attack/thunder_clap", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 32 */     this.baseDamage = 4;
/* 33 */     this.baseMagicNumber = this.magicNumber = 1;
/* 34 */     this.artifactConsumedIfPlayed = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(p.drawX, p.drawY), 0.05F));
/*    */     
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/* 43 */     if (!p.hasPower("Artifact") && 
/* 44 */       AbstractCharBoss.boss.hasRelic("Champion Belt")) {
/* 45 */       AbstractCharBoss.boss.getRelic("Champion Belt").onTrigger();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 52 */     if (!this.upgraded) {
/* 53 */       upgradeName();
/* 54 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new EnThunderclap();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 65 */     return autoPriority() + 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnThunderclap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */