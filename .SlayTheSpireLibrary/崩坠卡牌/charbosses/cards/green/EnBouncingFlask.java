/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyBouncingFlaskAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnBouncingFlask
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Bouncing Flask";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Bouncing Flask");
/*    */ 
/*    */   
/*    */   public EnBouncingFlask() {
/* 26 */     super("downfall_Charboss:Bouncing Flask", cardStrings.NAME, "green/skill/bouncing_flask", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
/* 27 */     this.baseMagicNumber = 3;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.artifactConsumedIfPlayed = 3;
/* 30 */     if (this.upgraded) {
/* 31 */       this.artifactConsumedIfPlayed = 4;
/*    */     }
/*    */   }
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 36 */     return this.magicNumber * 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionBounceEffect(m.hb.cX, m.hb.cY, p.hb.cX, this.hb.cY), 0.4F));
/* 42 */     addToBot((AbstractGameAction)new EnemyBouncingFlaskAction(3, m, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnBouncingFlask();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnBouncingFlask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */