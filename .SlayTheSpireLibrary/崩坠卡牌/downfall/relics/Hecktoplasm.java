/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hecktoplasm
/*    */   extends CustomRelic
/*    */ {
/* 20 */   public static final String ID = downfallMod.makeID("Hecktoplasm");
/* 21 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/ectoplasmEvil.png"));
/* 22 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/ectoplasmEvil.png"));
/*    */   
/*    */   public Hecktoplasm() {
/* 25 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 30 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onEquip() {
/* 34 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */   
/*    */   public void onUnequip() {
/* 38 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */   
/*    */   public boolean canSpawn() {
/* 42 */     return (AbstractDungeon.actNum <= 1 && EvilModeCharacterSelect.evilMode);
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 46 */     return (AbstractRelic)new Hecktoplasm();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\Hecktoplasm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */