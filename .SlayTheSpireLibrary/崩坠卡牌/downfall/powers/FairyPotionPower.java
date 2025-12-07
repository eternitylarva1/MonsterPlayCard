/*    */ package downfall.powers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.combat.HealEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class FairyPotionPower extends AbstractPower implements CloneablePowerInterface {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("FairyPotion");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/FairyPotion84.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/FairyPotion32.png"));
/*    */   
/*    */   public static boolean CANNOT_END = false;
/*    */   
/*    */   public FairyPotionPower(AbstractCreature owner, int amount) {
/* 28 */     this.ID = POWER_ID;
/* 29 */     this.owner = owner;
/* 30 */     this.amount = amount;
/* 31 */     this.type = AbstractPower.PowerType.BUFF;
/* 32 */     this.isTurnBased = true;
/*    */     
/* 34 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 35 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 37 */     this.name = NAME;
/*    */     
/* 39 */     updateDescription();
/*    */   }
/*    */   
/*    */   private void healUndead(AbstractCreature m, int healAmount) {
/* 43 */     if (m.isDying) m.isDying = false; 
/* 44 */     for (AbstractPower p : m.powers) {
/* 45 */       p.onHeal(healAmount);
/*    */     }
/*    */     
/* 48 */     m.currentHealth += healAmount;
/* 49 */     if (m.currentHealth > m.maxHealth) {
/* 50 */       m.currentHealth = m.maxHealth;
/*    */     }
/*    */ 
/*    */     
/* 54 */     if (healAmount > 0) {
/* 55 */       m.healthBarUpdatedEvent();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 62 */     CANNOT_END = true;
/* 63 */     super.onDeath();
/* 64 */     int healAmt = (int)(this.owner.maxHealth * 0.3F);
/* 65 */     healUndead(this.owner, healAmt);
/* 66 */     AbstractDungeon.effectsQueue.add(new HealEffect(this.owner.hb.cX, this.owner.hb.cY, healAmt));
/* 67 */     addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
/* 68 */     addToTop((AbstractGameAction)new TalkAction(this.owner, DESCRIPTIONS[1]));
/* 69 */     CANNOT_END = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 79 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 84 */     return new FairyPotionPower(this.owner, this.amount);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\FairyPotionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */