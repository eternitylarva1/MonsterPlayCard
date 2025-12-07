/*    */ package downfall.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class EndOfTurnStrengthDex
/*    */   extends AbstractPower {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("NeowBuff");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 21 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
/* 22 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));
/*    */ 
/*    */   
/*    */   public EndOfTurnStrengthDex(AbstractCreature owner, int amount) {
/* 26 */     this.ID = POWER_ID;
/* 27 */     this.owner = owner;
/* 28 */     this.amount = amount;
/* 29 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 31 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 32 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 34 */     this.name = NAME;
/*    */     
/* 36 */     updateDescription();
/*    */     
/* 38 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 43 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
/* 44 */     super.atEndOfRound();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 49 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\EndOfTurnStrengthDex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */