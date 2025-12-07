/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnSteamBarrier
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:SteamBarrier";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Steam");
/*    */ 
/*    */   
/*    */   public EnSteamBarrier() {
/* 22 */     super("downfall_Charboss:SteamBarrier", cardStrings.NAME, "blue/skill/steam_barrier", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 23 */     this.baseBlock = 6;
/*    */   }
/*    */   
/*    */   public EnSteamBarrier(int blockModifier) {
/* 27 */     this();
/* 28 */     this.baseBlock -= blockModifier;
/* 29 */     this.block = this.baseBlock;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 34 */     addToBot((AbstractGameAction)new ModifyBlockAction(this.uuid, -1));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 45 */     return (AbstractCard)new EnSteamBarrier();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnSteamBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */