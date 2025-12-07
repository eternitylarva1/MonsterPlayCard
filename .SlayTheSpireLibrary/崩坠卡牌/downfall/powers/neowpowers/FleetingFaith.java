/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class FleetingFaith extends AbstractBossMechanicPower {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("NeowFleetingFaith");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 21 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher384.png"));
/* 22 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher332.png"));
/*    */   private boolean firstTurn;
/*    */   
/*    */   public FleetingFaith(AbstractCreature owner) {
/* 26 */     this.ID = POWER_ID;
/* 27 */     this.owner = owner;
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
/*    */   public void playApplyPowerSfx() {
/* 43 */     CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 48 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "downfall:NeowMantra", 5));
/*    */   }
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 52 */     if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/* 53 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new NeowMantraPower(this.owner, 1), 1));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 60 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\FleetingFaith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */