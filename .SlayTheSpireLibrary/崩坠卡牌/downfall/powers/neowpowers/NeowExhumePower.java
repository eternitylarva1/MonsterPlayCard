/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.actions.NeowExhumeAction;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class NeowExhumePower
/*    */   extends AbstractPower
/*    */ {
/* 22 */   public static final String POWER_ID = downfallMod.makeID("NeowExhumePower");
/* 23 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 24 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   protected float angle;
/* 26 */   protected float particleTimer = 0.0F; protected float particleTimer2 = 0.0F;
/*    */   
/* 28 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent384.png"));
/* 29 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent332.png"));
/*    */ 
/*    */   
/*    */   public NeowExhumePower(AbstractCreature owner) {
/* 33 */     this.ID = POWER_ID;
/* 34 */     this.owner = owner;
/* 35 */     this.type = AbstractPower.PowerType.BUFF;
/* 36 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 37 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 39 */     this.name = NAME;
/*    */     
/* 41 */     updateDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 47 */     addToBot((AbstractGameAction)new NeowExhumeAction());
/*    */     
/* 49 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 54 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\NeowExhumePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */