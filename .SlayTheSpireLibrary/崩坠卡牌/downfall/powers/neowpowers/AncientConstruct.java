/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class AncientConstruct extends AbstractBossMechanicPower {
/* 15 */   public static final String POWER_ID = downfallMod.makeID("NeowAncientConstruct");
/* 16 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 17 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 19 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect284.png"));
/* 20 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect232.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   
/*    */   public AncientConstruct(AbstractCreature owner, int amount) {
/* 25 */     this.ID = POWER_ID;
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 30 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 31 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 33 */     this.name = NAME;
/*    */     
/* 35 */     updateDescription();
/*    */     
/* 37 */     this.firstTurn = true;
/* 38 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 45 */     if (!this.owner.hasPower("Artifact")) {
/* 46 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ArtifactPower(this.owner, this.amount), this.amount));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\AncientConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */