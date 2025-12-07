/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.general.EnemyMarkPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.PressurePointEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnPressurePoints
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Pressure Points";
/* 29 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("PathToVictory");
/*    */ 
/*    */   
/*    */   public EnPressurePoints() {
/* 33 */     super("downfall_Charboss:Pressure Points", cardStrings.NAME, "purple/skill/pressure_points", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 34 */     this.baseDamage = 0;
/* 35 */     this.baseMagicNumber = this.magicNumber = 8;
/*    */   }
/*    */   
/*    */   public String overrideIntentText() {
/* 39 */     return String.valueOf(countMarks());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 46 */     PressurePointEffect pressurePointEffect = new PressurePointEffect(p.hb.cX, p.hb.cY);
/* 47 */     ((AbstractGameEffect)pressurePointEffect).renderBehind = true;
/* 48 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)pressurePointEffect));
/* 49 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new EnemyMarkPower((AbstractCreature)p, (AbstractCreature)m, this.magicNumber), this.magicNumber));
/* 50 */     if (countMarks() > 0) {
/* 51 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, countMarks(), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 56 */     return countMarks();
/*    */   }
/*    */   
/*    */   public int countMarks() {
/* 60 */     int count = 0;
/* 61 */     if (AbstractDungeon.player.hasPower("PathToVictoryPower")) {
/* 62 */       count += (AbstractDungeon.player.getPower("PathToVictoryPower")).amount;
/*    */     }
/* 64 */     if (!AbstractDungeon.player.hasPower("Artifact")) {
/* 65 */       count += this.magicNumber;
/*    */     }
/* 67 */     if ((AbstractDungeon.player.hasPower("Intangible") || AbstractDungeon.player.hasPower("IntangiblePlayer")) && 
/* 68 */       count > 0) {
/* 69 */       count = 1;
/*    */     }
/* 71 */     return count;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 75 */     if (!this.upgraded) {
/* 76 */       upgradeName();
/* 77 */       upgradeMagicNumber(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 83 */     return (AbstractCard)new EnPressurePoints();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnPressurePoints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */