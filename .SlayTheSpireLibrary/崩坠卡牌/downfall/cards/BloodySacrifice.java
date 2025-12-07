/*    */ package downfall.cards;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.RitualPower;
/*    */ import expansioncontent.cards.AbstractDownfallCard;
/*    */ import expansioncontent.cards.AbstractExpansionCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ 
/*    */ public class BloodySacrifice
/*    */   extends AbstractDownfallCard
/*    */ {
/* 22 */   public static final String ID = AbstractExpansionCard.makeID("BloodySacrifice");
/* 23 */   public static final String IMG_PATH = expansionContentMod.makeCardPath("BloodySacrifice.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public BloodySacrifice() {
/* 28 */     super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
/* 29 */     this.isEthereal = true;
/* 30 */     this.baseMagicNumber = this.magicNumber = 8;
/* 31 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 36 */     super.applyPowers();
/* 37 */     this.lose_hp = MathUtils.floor(AbstractDungeon.player.maxHealth * this.magicNumber * 0.01F);
/* 38 */     this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0].replace("{amount}", Integer.toString(this.lose_hp));
/* 39 */     initializeDescription();
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 43 */     AbstractDungeon.player.maxHealth -= this.lose_hp;
/* 44 */     if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth) {
/* 45 */       AbstractDungeon.player.currentHealth = AbstractDungeon.player.maxHealth;
/*    */     }
/* 47 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new RitualPower((AbstractCreature)p, 1, true), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 52 */     if (!this.upgraded) {
/* 53 */       upgradeName();
/* 54 */       upgradeMagicNumber(-3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 59 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
/*    */   private int lose_hp;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\BloodySacrifice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */