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
/*    */ public class WheelOfDeath
/*    */   extends AbstractBossMechanicPower {
/* 19 */   public static final String POWER_ID = downfallMod.makeID("NeowWheelOfDeath");
/* 20 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 21 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 23 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit284.png"));
/* 24 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit232.png"));
/*    */   
/* 26 */   private final int STRENGTHGAIN = 2;
/* 27 */   private final int EVERYXCARDS = 13;
/*    */   
/*    */   public WheelOfDeath(AbstractCreature owner, int amount) {
/* 30 */     this.ID = POWER_ID;
/* 31 */     this.owner = owner;
/* 32 */     this.amount = amount;
/* 33 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 35 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 36 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 38 */     this.name = NAME;
/*    */     
/* 40 */     updateDescription();
/*    */     
/* 42 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 47 */     super.onAfterUseCard(card, action);
/* 48 */     this.amount--;
/* 49 */     if (this.amount == 0) {
/* 50 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, 2), 2));
/* 51 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new WheelOfDeath(this.owner, 13)));
/*    */     } 
/* 53 */     updateDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 59 */     this.description = DESCRIPTIONS[0] + '\r' + DESCRIPTIONS[1] + '\002' + DESCRIPTIONS[2];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\WheelOfDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */