/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MercuryHourglass;
/*    */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class CBR_MercuryHourglass extends AbstractCharbossRelic {
/* 23 */   private static Texture red = TextureLoader.getTexture(downfallMod.assetPath("images/relics/RedHourglass.png")); public static final String ID = "MercuryHourglass";
/*    */   private BobEffect bob;
/*    */   
/*    */   public CBR_MercuryHourglass() {
/* 27 */     super((AbstractRelic)new MercuryHourglass());
/* 28 */     this.bob = new BobEffect();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 33 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 38 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)AbstractCharBoss.boss, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 44 */     return new CBR_MercuryHourglass();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 49 */     super.update();
/* 50 */     this.bob.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 55 */     super.render(sb);
/* 56 */     float damage = 3.0F;
/* 57 */     for (AbstractPower pw : AbstractDungeon.player.powers) {
/* 58 */       damage = pw.atDamageFinalReceive(damage, DamageInfo.DamageType.THORNS);
/*    */     }
/* 60 */     sb.setColor(Color.WHITE.cpy());
/* 61 */     sb.draw(red, this.owner.hb.x - 230.0F * Settings.scale, this.owner.hb.y + 240.0F * Settings.scale + this.bob.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
/* 62 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(MathUtils.floor(damage)), this.owner.hb.x - 205.0F * Settings.scale, this.owner.hb.y + 290.0F * Settings.scale + this.bob.y, Color.WHITE.cpy());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MercuryHourglass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */