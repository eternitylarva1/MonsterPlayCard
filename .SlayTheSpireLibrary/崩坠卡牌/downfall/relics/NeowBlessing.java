/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class NeowBlessing
/*    */   extends CustomRelic
/*    */ {
/* 13 */   public static final String ID = downfallMod.makeID("NeowBlessing_Player");
/*    */   
/* 15 */   private static Texture texture = new Texture(downfallMod.assetPath("images/relics/blessing.png"));
/* 16 */   private static Texture outline = new Texture(downfallMod.assetPath("images/relics/Outline/blessing.png"));
/*    */   
/*    */   public NeowBlessing() {
/* 19 */     super(ID, texture, outline, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     AbstractDungeon.player.energy.energyMaster--;
/* 26 */     AbstractDungeon.player.masterHandSize--;
/* 27 */     AbstractDungeon.player.increaseMaxHp(100, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 32 */     AbstractDungeon.player.energy.energyMaster++;
/* 33 */     AbstractDungeon.player.masterHandSize++;
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 37 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public CustomRelic makeCopy() {
/* 41 */     return new NeowBlessing();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\NeowBlessing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */