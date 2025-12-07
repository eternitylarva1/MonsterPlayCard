/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class UnbridledRage
/*    */   extends AbstractBossMechanicPower
/*    */ {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("NeowUnbridledRage");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher184.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher132.png"));
/*    */   private boolean active;
/*    */   
/*    */   public UnbridledRage(AbstractCreature owner) {
/* 27 */     this.ID = POWER_ID;
/* 28 */     this.owner = owner;
/* 29 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 31 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 32 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 34 */     this.name = NAME;
/*    */     
/* 36 */     updateDescription();
/*    */     
/* 38 */     this.active = false;
/*    */     
/* 40 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 46 */     super.atEndOfRound();
/* 47 */     if (!this.active && this.owner.currentHealth <= this.owner.maxHealth / 2) {
/* 48 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, 6), 6));
/* 49 */       this.active = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 56 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\UnbridledRage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */