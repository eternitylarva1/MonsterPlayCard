/*    */ package downfall.powers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import sneckomod.SneckoMod;
/*    */ 
/*    */ public class TransformDrawnCardsPower
/*    */   extends TwoAmountPower
/*    */   implements CloneablePowerInterface
/*    */ {
/* 24 */   public static final String POWER_ID = downfallMod.makeID("TransformDrawnCardsPower");
/* 25 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 26 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 28 */   private static final Texture tex84 = TextureLoader.getTexture(SneckoMod.getModID() + "Resources/images/powers/TransformDrawnCards84.png");
/* 29 */   private static final Texture tex32 = TextureLoader.getTexture(SneckoMod.getModID() + "Resources/images/powers/TransformDrawnCards32.png");
/*    */   
/*    */   public TransformDrawnCardsPower(AbstractCreature owner, int amount) {
/* 32 */     this.ID = POWER_ID;
/* 33 */     this.owner = owner;
/* 34 */     this.amount = amount;
/* 35 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 36 */     this.isTurnBased = true;
/* 37 */     this.name = NAME;
/*    */     
/* 39 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 40 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 42 */     this.amount2 = this.amount;
/*    */     
/* 44 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCardDraw(AbstractCard card) {
/* 49 */     if (this.amount2 > 0) {
/* 50 */       flash();
/* 51 */       this.amount2--;
/* 52 */       addToBot((AbstractGameAction)new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
/* 53 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat()));
/* 54 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 60 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 65 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + Math.max(0, this.amount2) + DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 70 */     return (AbstractPower)new TransformDrawnCardsPower(this.owner, this.amount);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\TransformDrawnCardsPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */