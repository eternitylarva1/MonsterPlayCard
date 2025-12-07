/*    */ package downfall.powers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ public class ExhaustEndOfTurnPower
/*    */   extends AbstractPower
/*    */   implements CloneablePowerInterface
/*    */ {
/* 20 */   public static final String POWER_ID = downfallMod.makeID("ExhaustCardsPower");
/* 21 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 22 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 24 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/TotemExhaust84.png"));
/* 25 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/TotemExhaust32.png"));
/*    */   
/*    */   public static boolean CANNOT_END = false;
/*    */   
/*    */   public ExhaustEndOfTurnPower(AbstractCreature owner) {
/* 30 */     this.ID = POWER_ID;
/* 31 */     this.owner = owner;
/* 32 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 33 */     this.isTurnBased = true;
/*    */     
/* 35 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 36 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 38 */     this.name = NAME;
/*    */     
/* 40 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 45 */     if (isPlayer) {
/* 46 */       addToBot((AbstractGameAction)new ExhaustAction(1, false, false, false));
/*    */     }
/* 48 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 58 */     return new ExhaustEndOfTurnPower(this.owner);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\ExhaustEndOfTurnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */