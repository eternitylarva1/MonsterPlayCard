/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class FeedingFrenzy extends AbstractBossMechanicPower {
/* 11 */   public static final String POWER_ID = downfallMod.makeID("NeowFeedingFrenzy");
/* 12 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 13 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 15 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad284.png"));
/* 16 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad232.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   
/*    */   public FeedingFrenzy(AbstractCreature owner) {
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
/* 32 */     this.firstTurn = true;
/* 33 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\FeedingFrenzy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */