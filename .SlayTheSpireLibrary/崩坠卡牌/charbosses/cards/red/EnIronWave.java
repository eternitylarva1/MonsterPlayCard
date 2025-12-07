/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.vfx.IronWaveReversedEffect;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class EnIronWave
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Iron Wave";
/* 24 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Iron Wave");
/*    */ 
/*    */   
/*    */   public EnIronWave() {
/* 28 */     super("downfall_Charboss:Iron Wave", cardStrings.NAME, "red/attack/iron_wave", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEFEND);
/* 29 */     this.baseDamage = 5;
/* 30 */     this.baseBlock = 5;
/* 31 */     this.tags.add(downfallMod.CHARBOSS_ATTACK);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 37 */     addToBot((AbstractGameAction)new WaitAction(0.1F));
/* 38 */     if (p != null && m != null) {
/* 39 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new IronWaveReversedEffect(p.hb.cX, p.hb.cY, p.hb.cX), 0.5F));
/*    */     }
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeDamage(2);
/* 49 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnIronWave();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnIronWave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */