/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class SeeingDoubleProduct extends AbstractPower {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("NeowSeeingDoubleProduct");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 21 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent284.png"));
/* 22 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent232.png"));
/*    */   
/*    */   public SeeingDoubleProduct(AbstractCreature owner) {
/* 25 */     this.ID = POWER_ID;
/* 26 */     this.owner = owner;
/* 27 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 29 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 30 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 32 */     this.name = NAME;
/*    */     
/* 34 */     updateDescription();
/*    */     
/* 36 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 41 */     if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null) {
/* 42 */       flash();
/* 43 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
/* 44 */       return 0;
/*    */     } 
/* 46 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 51 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 56 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\SeeingDoubleProduct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */