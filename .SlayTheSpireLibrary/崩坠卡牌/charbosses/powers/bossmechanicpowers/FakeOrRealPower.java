/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Silent.CharBossSilent;
/*    */ import charbosses.vfx.QuietSpecialSmokeBombEffect;
/*    */ import charbosses.vfx.SmallerSmokeBombEffect;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class FakeOrRealPower
/*    */   extends AbstractPower
/*    */   implements CloneablePowerInterface, InvisiblePower {
/* 25 */   public static final String POWER_ID = downfallMod.makeID("FakeOrRealPower");
/*    */   
/* 27 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/InverseBias84.png"));
/* 28 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/InverseBias32.png"));
/*    */   
/* 30 */   float particleTimer = 0.025F;
/*    */   
/*    */   public FakeOrRealPower(AbstractCreature owner) {
/* 33 */     this.ID = POWER_ID;
/* 34 */     this.owner = owner;
/* 35 */     this.type = AbstractPower.PowerType.BUFF;
/* 36 */     this.isTurnBased = false;
/*    */     
/* 38 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 39 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 41 */     this.name = "???";
/* 42 */     this.amount = 5;
/*    */     
/* 44 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 49 */     this.description = "You should never see this.";
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 54 */     if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
/* 55 */       for (AbstractMonster q : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 56 */         if (q instanceof charbosses.monsters.MirrorImageSilent) {
/* 57 */           q.isDead = true;
/* 58 */           q.isDying = true;
/* 59 */           AbstractDungeon.effectList.add(new SmallerSmokeBombEffect(q.hb.cX, q.hb.cY));
/*    */         } 
/* 61 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)q, (AbstractCreature)q, this.ID));
/*    */       } 
/* 63 */       if (AbstractCharBoss.boss != null) {
/* 64 */         ((CharBossSilent)AbstractCharBoss.boss).foggy = false;
/*    */       }
/*    */     } 
/* 67 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateParticles() {
/* 72 */     this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 73 */     if (this.particleTimer <= 0.0F) {
/* 74 */       this.particleTimer = 0.025F;
/* 75 */       AbstractDungeon.effectsQueue.add(new QuietSpecialSmokeBombEffect(AbstractDungeon.cardRandomRng.random(this.owner.healthHb.x, this.owner.healthHb.x + this.owner.hb.width), this.owner.hb.y));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 81 */     return new FakeOrRealPower(this.owner);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\FakeOrRealPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */