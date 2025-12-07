/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*    */ import hermit.util.TextureLoader;
/*    */ 
/*    */ public class TangerinePower extends AbstractPower {
/*    */   public static final String POWER_ID = "downfall:TangerinePower";
/*    */   private static final PowerStrings powerStrings;
/*    */   public static final String NAME;
/*    */   public static final String[] DESC;
/* 26 */   private static final Texture tex84 = TextureLoader.getTexture("expansioncontentResources/images/powers/TangerinePower84.png");
/* 27 */   private static final Texture tex32 = TextureLoader.getTexture("expansioncontentResources/images/powers/TangerinePower32.png");
/*    */   
/*    */   public TangerinePower(AbstractCreature owner, int _amount) {
/* 30 */     this.name = NAME;
/* 31 */     this.ID = "downfall:TangerinePower";
/* 32 */     this.owner = owner;
/* 33 */     this.amount = _amount;
/* 34 */     updateDescription();
/* 35 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 37 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 38 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 43 */     if (this.owner.currentBlock > 0 && !this.owner.hasPower("Blur")) {
/* 44 */       addToBot((AbstractGameAction)new LoseBlockAction(this.owner, this.owner, this.owner.currentBlock));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 50 */     if (AbstractCharBoss.boss != null) {
/* 51 */       if ((AbstractCharBoss.boss.getPower("downfall:HermitConcentrationPower")).amount > 0)
/*    */       {
/* 53 */         AbstractCharBoss.boss.getPower("downfall:HermitConcentrationPower").stackPower((AbstractCharBoss.boss.getPower("downfall:HermitConcentrationPower")).amount * -1);
/*    */       }
/*    */       
/* 56 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_PIERCING_WAIL"));
/* 57 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)AbstractCharBoss.boss, (AbstractGameEffect)new ShockWaveEffect(AbstractCharBoss.boss.hb.cX, AbstractCharBoss.boss.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
/* 58 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new StrengthPower((AbstractCreature)AbstractCharBoss.boss, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 63 */     this.description = DESC[0] + this.amount + DESC[1];
/*    */   }
/*    */   
/*    */   static {
/* 67 */     powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:TangerinePower");
/* 68 */     NAME = powerStrings.NAME;
/* 69 */     DESC = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\TangerinePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */