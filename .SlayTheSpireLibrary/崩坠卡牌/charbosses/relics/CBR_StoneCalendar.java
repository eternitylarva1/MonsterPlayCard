/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.StoneCalendar;
/*    */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class CBR_StoneCalendar extends AbstractCharbossRelic {
/* 23 */   private static final Texture red = TextureLoader.getTexture(downfallMod.assetPath("images/relics/RedStoneCalendar.png")); public static final String ID = "Stone Calendar";
/*    */   private final BobEffect bob;
/*    */   
/*    */   public CBR_StoneCalendar() {
/* 27 */     super((AbstractRelic)new StoneCalendar());
/* 28 */     this.bob = new BobEffect();
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 32 */     return this.DESCRIPTIONS[0] + '\007' + this.DESCRIPTIONS[1] + '4' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 37 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 42 */     this.counter++;
/* 43 */     if (this.counter == 7) {
/* 44 */       beginLongPulse();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 50 */     super.update();
/* 51 */     this.bob.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     super.render(sb);
/* 57 */     float damage = 52.0F;
/* 58 */     for (AbstractPower pw : AbstractDungeon.player.powers) {
/* 59 */       damage = pw.atDamageFinalReceive(damage, DamageInfo.DamageType.THORNS);
/*    */     }
/* 61 */     if (this.counter == 7) {
/* 62 */       sb.setColor(Color.WHITE.cpy());
/* 63 */       sb.draw(red, this.owner.hb.x - 180.0F * Settings.scale, this.owner.hb.y + 240.0F * Settings.scale + this.bob.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
/* 64 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(MathUtils.floor(damage)), this.owner.hb.x - 155.0F * Settings.scale, this.owner.hb.y + 290.0F * Settings.scale + this.bob.y, Color.WHITE.cpy());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 69 */     if (this.counter == 7) {
/* 70 */       flash();
/* 71 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 72 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)this.owner, 52, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/* 73 */       stopPulse();
/* 74 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 81 */     this.counter = -1;
/* 82 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 88 */     return new CBR_StoneCalendar();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_StoneCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */