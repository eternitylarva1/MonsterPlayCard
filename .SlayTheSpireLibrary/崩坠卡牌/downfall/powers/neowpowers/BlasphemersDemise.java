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
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class BlasphemersDemise extends AbstractBossMechanicPower {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("NeowBlasphemersDemise");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 21 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher284.png"));
/* 22 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowWatcher232.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   
/*    */   public BlasphemersDemise(AbstractCreature owner, int amount) {
/* 27 */     this.ID = POWER_ID;
/* 28 */     this.owner = owner;
/* 29 */     this.amount = amount;
/* 30 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 32 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 33 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 35 */     this.name = NAME;
/*    */     
/* 37 */     updateDescription();
/*    */     
/* 39 */     this.firstTurn = true;
/* 40 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 45 */     if (this.amount > 0) {
/* 46 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 48 */       this.description = DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 54 */     flash();
/* 55 */     stackPower(damageAmount * -1);
/* 56 */     updateDescription();
/* 57 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 62 */     if (this.amount > 0) {
/* 63 */       flash();
/* 64 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, 10), 10, true, AbstractGameAction.AttackEffect.NONE));
/*    */     } else {
/* 66 */       flash();
/* 67 */       this.amount = 150;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\BlasphemersDemise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */