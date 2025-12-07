/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.curses.Decay;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BufferPower;
/*    */ import com.megacrit.cardcrawl.powers.ThornsPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.powers.EnemyDemonFormPower;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ public class TrueNeowPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/* 25 */   public static final String POWER_ID = downfallMod.makeID("NeowSpirit");
/* 26 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 27 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 29 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad384.png"));
/* 30 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowIronclad332.png"));
/*    */   
/*    */   private boolean ironclad;
/*    */   private boolean silent;
/*    */   private boolean defect;
/*    */   private boolean watcher;
/*    */   private boolean hermit;
/*    */   
/*    */   public TrueNeowPower(AbstractCreature owner, boolean ic, boolean si, boolean de, boolean wa, boolean he) {
/* 39 */     this.ID = POWER_ID;
/* 40 */     this.owner = owner;
/* 41 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 43 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 44 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 46 */     this.name = NAME;
/*    */     
/* 48 */     this.canGoNegative = false;
/* 49 */     this.ironclad = ic;
/* 50 */     this.silent = si;
/* 51 */     this.defect = de;
/* 52 */     this.watcher = wa;
/* 53 */     this.hermit = he;
/* 54 */     updateDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 60 */     if (this.ironclad) {
/* 61 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new EnemyDemonFormPower(this.owner, 1), 1));
/*    */     }
/* 63 */     if (this.silent) {
/* 64 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new ThornsPower(this.owner, 2), 2));
/*    */     }
/* 66 */     if (this.defect) {
/* 67 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new BufferPower(this.owner, 2), 2));
/*    */     }
/* 69 */     if (this.watcher) {
/* 70 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new NeowMantraPower(this.owner, 5), 5));
/*    */     }
/* 72 */     if (this.hermit) {
/* 73 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Decay(), 1, true, false, false, Settings.WIDTH * 0.35F, Settings.HEIGHT / 2.0F));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 81 */     this.description = DESCRIPTIONS[0];
/* 82 */     if (this.ironclad) this.description += DESCRIPTIONS[1]; 
/* 83 */     if (this.silent) this.description += DESCRIPTIONS[2]; 
/* 84 */     if (this.defect) this.description += DESCRIPTIONS[3]; 
/* 85 */     if (this.watcher) this.description += DESCRIPTIONS[4]; 
/* 86 */     if (this.hermit) this.description += DESCRIPTIONS[5]; 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\TrueNeowPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */