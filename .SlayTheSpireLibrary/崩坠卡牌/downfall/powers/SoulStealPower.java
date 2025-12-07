/*    */ package downfall.powers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class SoulStealPower
/*    */   extends AbstractPower implements CloneablePowerInterface {
/* 14 */   public static final String POWER_ID = downfallMod.makeID("SoulSteal");
/* 15 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 16 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 18 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/SoulSteal84.png"));
/* 19 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/SoulSteal32.png"));
/*    */   
/*    */   public SoulStealPower(AbstractCreature owner, int amount) {
/* 22 */     this.ID = POWER_ID;
/* 23 */     this.owner = owner;
/* 24 */     this.amount = amount;
/* 25 */     this.type = AbstractPower.PowerType.BUFF;
/* 26 */     this.isTurnBased = true;
/*    */     
/* 28 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 29 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 31 */     this.name = NAME;
/*    */     
/* 33 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 43 */     return new SoulStealPower(this.owner, this.amount);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\SoulStealPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */