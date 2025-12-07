/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import expansioncontent.powers.DeEnergizedPower;
/*    */ 
/*    */ public class Distracting
/*    */   extends AbstractBossMechanicPower {
/* 19 */   public static final String POWER_ID = downfallMod.makeID("NeowDistracting");
/* 20 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 21 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 23 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit184.png"));
/* 24 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit132.png"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean appliedThisTurn;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Distracting(AbstractCreature owner, int amount) {
/* 42 */     this.appliedThisTurn = false; this.ID = POWER_ID; this.owner = owner; this.amount = amount; this.type = AbstractPower.PowerType.BUFF; this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/*    */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     this.name = NAME;
/*    */     updateDescription();
/* 46 */     this.canGoNegative = false; } public void atStartOfTurn() { this.appliedThisTurn = false; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 51 */     if (damageAmount > 0 && info.type != DamageInfo.DamageType.THORNS && !this.appliedThisTurn) {
/* 52 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DeEnergizedPower(1), 1));
/* 53 */       this.appliedThisTurn = true;
/*    */     } 
/* 55 */     return damageAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 61 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\Distracting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */