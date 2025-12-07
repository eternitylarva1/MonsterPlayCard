/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BarricadePower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class Bastion
/*    */   extends AbstractBossMechanicPower {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("NeowBastion");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad384.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad332.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   
/*    */   public Bastion(AbstractCreature owner, int amount) {
/* 28 */     this.ID = POWER_ID;
/* 29 */     this.owner = owner;
/* 30 */     this.amount = amount;
/* 31 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 33 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 34 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 36 */     this.name = NAME;
/*    */     
/* 38 */     updateDescription();
/*    */     
/* 40 */     this.firstTurn = true;
/* 41 */     this.canGoNegative = false;
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BarricadePower(this.owner)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 47 */     flash();
/* 48 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\Bastion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */