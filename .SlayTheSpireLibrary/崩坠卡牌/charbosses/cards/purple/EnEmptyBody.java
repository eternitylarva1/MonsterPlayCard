/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.common.EnemyNotStanceCheckAction;
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnNeutralStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnEmptyBody
/*    */   extends AbstractStanceChangeCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:EmptyBody";
/*    */   
/*    */   public EnEmptyBody() {
/* 27 */     this(2);
/*    */   }
/*    */   public EnEmptyBody(int energyGeneratedIfPlayed) {
/* 30 */     super("downfall_Charboss:EmptyBody", cardStrings.NAME, "purple/skill/empty_body", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 31 */     this.baseBlock = 7;
/* 32 */     this.tags.add(AbstractCard.CardTags.EMPTY);
/* 33 */     this.energyGeneratedIfPlayed = energyGeneratedIfPlayed;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 38 */     addToBot((AbstractGameAction)new EnemyNotStanceCheckAction((AbstractGameAction)new VFXAction((AbstractGameEffect)new EmptyStanceEffect(m.hb.cX, m.hb.cY), 0.1F)));
/* 39 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Neutral"));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return (AbstractCard)new EnEmptyBody();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 56 */     if (AbstractCharBoss.boss.stance.ID == "Calm") {
/* 57 */       this.energyGeneratedIfPlayed = 2;
/* 58 */       return autoPriority() + 20;
/*    */     } 
/* 60 */     this.energyGeneratedIfPlayed = 0;
/*    */     
/* 62 */     return super.getPriority(hand);
/*    */   }
/*    */ 
/*    */   
/* 66 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("EmptyBody");
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance previousStance) {
/* 71 */     return (AbstractEnemyStance)new EnNeutralStance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnEmptyBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */