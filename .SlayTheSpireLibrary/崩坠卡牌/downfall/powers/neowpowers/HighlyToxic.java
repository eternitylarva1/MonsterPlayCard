/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import charbosses.powers.general.EnemyPoisonPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ public class HighlyToxic
/*    */   extends AbstractBossMechanicPower
/*    */ {
/* 20 */   public static final String POWER_ID = downfallMod.makeID("NeowHighlyToxic");
/* 21 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 22 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 24 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent384.png"));
/* 25 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent332.png"));
/*    */ 
/*    */ 
/*    */   
/*    */   public HighlyToxic(AbstractCreature owner, int amount) {
/* 30 */     this.ID = POWER_ID;
/* 31 */     this.owner = owner;
/* 32 */     this.amount = amount;
/* 33 */     this.type = AbstractPower.PowerType.BUFF;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 51 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 52 */       flash();
/* 53 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new EnemyPoisonPower((AbstractCreature)AbstractDungeon.player, this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 64 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\HighlyToxic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */