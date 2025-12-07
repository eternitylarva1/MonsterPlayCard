/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*    */ import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
/*    */ import hermit.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = EmptyOrbSlot.class, method = "updateDescription")
/*    */ public class EmptyOrbSlotPatch
/*    */ {
/* 16 */   public static Texture NORMAL_ORB = ImageMaster.ORB_SLOT_1;
/* 17 */   public static Texture SLIME_ORB = TextureLoader.getTexture("slimeboundResources/SlimeboundImages/orbs/empty1.png");
/* 18 */   public static final OrbStrings normalOrbString = CardCrawlGame.languagePack.getOrbString("Empty");
/* 19 */   public static final OrbStrings slimeOrbString = CardCrawlGame.languagePack.getOrbString("Slimebound:EmptySlimeSlot");
/* 20 */   public static final OrbStrings guardianOrbString = CardCrawlGame.languagePack.getOrbString("Guardian:EmptyStasisSlot");
/*    */   
/*    */   public static void Postfix(EmptyOrbSlot EmptyOrbSlot_instance) {
/*    */     OrbStrings orbString;
/* 24 */     if (AbstractDungeon.player instanceof slimebound.characters.SlimeboundCharacter) {
/* 25 */       ImageMaster.ORB_SLOT_1 = SLIME_ORB;
/* 26 */       orbString = slimeOrbString;
/*    */     } else {
/* 28 */       ImageMaster.ORB_SLOT_1 = NORMAL_ORB;
/* 29 */       if (AbstractDungeon.player instanceof guardian.characters.GuardianCharacter) {
/* 30 */         orbString = guardianOrbString;
/*    */       } else {
/* 32 */         orbString = normalOrbString;
/*    */       } 
/* 34 */     }  EmptyOrbSlot_instance.name = orbString.NAME;
/* 35 */     EmptyOrbSlot_instance.description = orbString.DESCRIPTION[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EmptyOrbSlotPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */