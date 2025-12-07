/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class SeeingDouble extends AbstractBossMechanicPower {
/* 13 */   public static final String POWER_ID = downfallMod.makeID("NeowSeeingDouble");
/* 14 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 15 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 17 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent284.png"));
/* 18 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent232.png"));
/*    */   
/*    */   public SeeingDouble(AbstractCreature owner) {
/* 21 */     this.ID = POWER_ID;
/* 22 */     this.owner = owner;
/* 23 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 25 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 26 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 28 */     this.name = NAME;
/*    */     
/* 30 */     updateDescription();
/*    */     
/* 32 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 37 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new SeeingDoubleProduct(this.owner), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 42 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\SeeingDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */