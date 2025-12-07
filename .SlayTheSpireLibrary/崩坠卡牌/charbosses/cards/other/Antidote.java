/*    */ package charbosses.cards.other;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import expansioncontent.cards.AbstractDownfallCard;
/*    */ import expansioncontent.cards.AbstractExpansionCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ 
/*    */ public class Antidote extends AbstractDownfallCard {
/* 18 */   public static final String ID = AbstractExpansionCard.makeID("Antidote");
/* 19 */   public static final String IMG_PATH = expansionContentMod.makeCardPath("Antidote.png");
/*    */ 
/*    */   
/*    */   public Antidote() {
/* 23 */     super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
/* 24 */     this.baseMagicNumber = this.magicNumber = 12;
/* 25 */     this.selfRetain = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     if (AbstractDungeon.player.hasPower("Poison") && (AbstractDungeon.player.getPower("Poison")).amount <= this.magicNumber) {
/* 30 */       atb((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)p, (AbstractCreature)p, "Poison"));
/*    */     } else {
/* 32 */       addToBot((AbstractGameAction)new ReducePowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Poison", this.magicNumber));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upp() {
/* 39 */     upgradeBaseCost(0);
/*    */   }
/*    */ 
/*    */   
/* 43 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\other\Antidote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */