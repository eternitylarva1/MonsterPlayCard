/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagOfKnives
/*    */   extends AbstractBossMechanicPower
/*    */ {
/* 27 */   public static final String POWER_ID = downfallMod.makeID("NeowBagOfKnives");
/* 28 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 29 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 31 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent184.png"));
/* 32 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowSilent132.png"));
/*    */   
/*    */   private boolean firstTurn;
/*    */   public boolean usedThisTurn = false;
/*    */   
/*    */   public BagOfKnives(AbstractCreature owner) {
/* 38 */     this.ID = POWER_ID;
/* 39 */     this.owner = owner;
/* 40 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 42 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 43 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 45 */     this.name = NAME;
/*    */     
/* 47 */     updateDescription();
/*    */     
/* 49 */     this.firstTurn = true;
/* 50 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 55 */     if (!this.usedThisTurn && 
/* 56 */       !(card instanceof charbosses.cards.AbstractBossCard) && card.costForTurn >= 2 && card.cost != -1 && !card.purgeOnUse) {
/*    */       
/* 58 */       flashWithoutSound();
/*    */ 
/*    */       
/* 61 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ThrowDaggerEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
/* 62 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 63 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 64 */       this.usedThisTurn = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 72 */     super.atStartOfTurn();
/* 73 */     this.usedThisTurn = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 78 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\BagOfKnives.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */