/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class Syncronize extends AbstractBossMechanicPower {
/* 14 */   public static final String POWER_ID = downfallMod.makeID("NeowSyncronize");
/* 15 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 16 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 18 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad184.png"));
/* 19 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad132.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   
/*    */   public Syncronize(AbstractCreature owner) {
/* 24 */     this.ID = POWER_ID;
/* 25 */     this.owner = owner;
/*    */     
/* 27 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 29 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 30 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 32 */     this.name = NAME;
/*    */     
/* 34 */     updateDescription();
/*    */     
/* 36 */     this.firstTurn = true;
/* 37 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 42 */     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(SneckoMod.getRandomStatus().makeStatEquivalentCopy(), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 48 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\Syncronize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */