/*    */ package downfall.powers;
/*    */ 
/*    */ import basemod.interfaces.CloneablePowerInterface;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TotemInvulnerablePower
/*    */   extends AbstractPower
/*    */   implements CloneablePowerInterface
/*    */ {
/* 22 */   public static final String POWER_ID = downfallMod.makeID("TotemInvulnerable");
/* 23 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 24 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 26 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/totemImmunity84.png"));
/* 27 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/totemImmunity32.png"));
/*    */   
/*    */   public static boolean CANNOT_END = false;
/*    */   
/*    */   public TotemInvulnerablePower(AbstractCreature owner) {
/* 32 */     this.ID = POWER_ID;
/* 33 */     this.owner = owner;
/* 34 */     this.type = AbstractPower.PowerType.BUFF;
/* 35 */     this.isTurnBased = true;
/*    */     
/* 37 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 38 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 40 */     this.name = NAME;
/*    */     
/* 42 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/* 47 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 48 */       if (m != this.owner && m.currentHealth > 1 && m instanceof downfall.monsters.AbstractTotemMonster) {
/* 49 */         flash();
/* 50 */         return super.onAttackedToChangeDamage(info, Math.min(damageAmount, this.owner.currentHealth - 1));
/*    */       } 
/*    */     } 
/* 53 */     return super.onAttackedToChangeDamage(info, damageAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 58 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPower makeCopy() {
/* 63 */     return new TotemInvulnerablePower(this.owner);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\TotemInvulnerablePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */