/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class UnbiasedCognition
/*    */   extends AbstractBossMechanicPower {
/* 19 */   public static final String POWER_ID = downfallMod.makeID("NeowUnbiasedCognition");
/* 20 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 21 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 23 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect384.png"));
/* 24 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect332.png"));
/*    */   private boolean firstTurn;
/*    */   
/*    */   public UnbiasedCognition(AbstractCreature owner) {
/* 28 */     this.ID = POWER_ID;
/* 29 */     this.owner = owner;
/* 30 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 32 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 33 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/* 34 */     this.amount = 2;
/* 35 */     this.name = NAME;
/*    */     
/* 37 */     updateDescription();
/*    */     
/* 39 */     this.firstTurn = true;
/* 40 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 46 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 51 */     if (card.type == AbstractCard.CardType.POWER && !(card instanceof charbosses.cards.AbstractBossCard)) {
/* 52 */       flash();
/* 53 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\UnbiasedCognition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */