/*    */ package downfall.relics;
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CultistMask;
/*    */ import com.megacrit.cardcrawl.relics.GremlinMask;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CloakOfManyFaces extends CustomRelic {
/* 12 */   public static final String ID = downfallMod.makeID("CloakOfManyFaces");
/* 13 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/CloakOfManyFaces.png"));
/* 14 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/CloakOfManyFaces.png"));
/*    */   
/*    */   public CloakOfManyFaces() {
/* 17 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 27 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new CultistMask());
/* 28 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new FaceOfCleric());
/* 29 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new NlothsMask());
/* 30 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new GremlinMask());
/* 31 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new SsserpentHead());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\CloakOfManyFaces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */