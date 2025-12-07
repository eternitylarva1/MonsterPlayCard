/*    */ package downfall.powers.neowpowers;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class EnergyThief extends AbstractBossMechanicPower {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("NeowEnergyThief");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect184.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect132.png"));
/*    */   private boolean firstTurn;
/*    */   
/*    */   public EnergyThief(AbstractCreature owner) {
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
/* 38 */     this.firstTurn = true;
/* 39 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 44 */     flash();
/* 45 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/* 46 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/* 47 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/* 48 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((new VoidCard()).makeStatEquivalentCopy(), 1, true, false, false, Settings.WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\EnergyThief.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */