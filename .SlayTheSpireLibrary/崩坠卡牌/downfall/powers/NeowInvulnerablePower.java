/*    */ package downfall.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowInvulnerablePower
/*    */   extends AbstractPower
/*    */ {
/* 24 */   public static final String POWER_ID = downfallMod.makeID("NeowInvulnerable");
/* 25 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 26 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 28 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
/* 29 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */ 
/*    */   
/*    */   public NeowInvulnerablePower(AbstractCreature owner, int stack) {
/* 35 */     this.ID = POWER_ID;
/* 36 */     this.owner = owner;
/* 37 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 39 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 40 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 42 */     this.amount = stack;
/* 43 */     this.name = NAME;
/*    */     
/* 45 */     updateDescription();
/*    */     
/* 47 */     this.firstTurn = true;
/* 48 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 53 */     super.onAfterUseCard(card, action);
/* 54 */     this.owner.heal(this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 59 */     this.fontScale = 8.0F;
/* 60 */     this.amount += stackAmount;
/* 61 */     if (this.amount <= 0) {
/* 62 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, (AbstractCreature)AbstractDungeon.player, POWER_ID));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 69 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\NeowInvulnerablePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */