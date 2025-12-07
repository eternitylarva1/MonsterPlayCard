/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import collector.cards.OnOtherCardExhaustInHand;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class Haunted
/*    */   extends CustomCard
/*    */   implements OnOtherCardExhaustInHand
/*    */ {
/* 23 */   public static final String ID = downfallMod.makeID("Haunted");
/*    */   public static final String NAME;
/*    */   public static final String DESCRIPTION;
/* 26 */   public static final String IMG_PATH = downfallMod.assetPath("images/cards/haunted.png");
/* 27 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.CURSE;
/* 28 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.CURSE;
/* 29 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID); private static final int COST = -2; private static final int BLOCK = 5; static {
/* 38 */     NAME = cardStrings.NAME;
/* 39 */     DESCRIPTION = cardStrings.DESCRIPTION;
/* 40 */     UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   }
/*    */   private static final int UPGRADE_BONUS = 3; public static String UPGRADED_DESCRIPTION;
/*    */   public Haunted() {
/* 44 */     super(ID, NAME, IMG_PATH, -2, DESCRIPTION, TYPE, AbstractCard.CardColor.CURSE, RARITY, TARGET);
/*    */     
/* 46 */     this.magicNumber = this.baseMagicNumber = 1;
/*    */     
/* 48 */     this.isEthereal = true;
/* 49 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atTurnStart() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onOtherCardExhaustWhileInHand(AbstractCard card) {
/* 80 */     if (card != this) {
/* 81 */       flash(Color.PURPLE.cpy());
/* 82 */       addToTop((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)AbstractDungeon.player, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 88 */     return (AbstractCard)new Haunted();
/*    */   }
/*    */   
/*    */   public void upgrade() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Haunted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */