/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Necronomicon;
/*    */ 
/*    */ public class EnHandOfGreedHermitNecro extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:HandOfGreed";
/*    */   private static final CardStrings cardStrings;
/*    */   
/*    */   public EnHandOfGreedHermitNecro() {
/* 25 */     super("downfall_Charboss:HandOfGreed", cardStrings.NAME, "colorless/attack/hand_of_greed", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 26 */     this.baseDamage = 20;
/* 27 */     this.baseMagicNumber = this.magicNumber = 20;
/* 28 */     this.isMultiDamage = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 33 */     AbstractBossCard copy = new EnHandOfGreed();
/* 34 */     copy.purgeOnUse = true;
/* 35 */     if (this.upgraded) copy.upgrade(); 
/* 36 */     copy.freeToPlayOnce = true;
/* 37 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)copy, 1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       upgradeDamage(5);
/* 44 */       upgradeMagicNumber(5);
/*    */     } 
/*    */   }
/*    */   
/* 48 */   private static AbstractRelic nCon = (AbstractRelic)new Necronomicon();
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 52 */     super.render(sb);
/* 53 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.hasRelic("Necronomicon") && this.cost >= 2 && this.type == AbstractCard.CardType.ATTACK) {
/* 54 */       nCon.currentX = this.current_x + 390.0F * this.drawScale / 3.0F * Settings.scale;
/* 55 */       nCon.currentY = this.current_y + 546.0F * this.drawScale / 3.0F * Settings.scale;
/* 56 */       nCon.scale = this.drawScale;
/* 57 */       nCon.renderOutline(sb, false);
/* 58 */       nCon.render(sb);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return (AbstractCard)new EnHandOfGreedHermitNecro();
/*    */   }
/*    */   
/*    */   static {
/* 67 */     cardStrings = CardCrawlGame.languagePack.getCardStrings("HandOfGreed");
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnHandOfGreedHermitNecro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */