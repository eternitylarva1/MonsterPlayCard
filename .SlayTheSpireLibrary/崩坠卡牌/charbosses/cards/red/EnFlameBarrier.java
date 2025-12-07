/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyFlameBarrierPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnFlameBarrier
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Flame Barrier";
/* 24 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flame Barrier");
/*    */ 
/*    */   
/*    */   public EnFlameBarrier() {
/* 28 */     super("Flame Barrier", cardStrings.NAME, "red/skill/flame_barrier", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
/* 29 */     this.baseBlock = 12;
/* 30 */     this.baseMagicNumber = 4;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     if (Settings.FAST_MODE) {
/* 37 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new FlameBarrierEffect(m.hb.cX, m.hb.cY), 0.1F));
/*    */     } else {
/* 39 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new FlameBarrierEffect(m.hb.cX, m.hb.cY), 0.5F));
/*    */     } 
/* 41 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyFlameBarrierPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 48 */     return autoPriority() + 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeBlock(4);
/* 56 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return (AbstractCard)new EnFlameBarrier();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnFlameBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */