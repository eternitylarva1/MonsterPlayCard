/*    */ package downfall.dailymods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ import downfall.downfallMod;
/*    */ import hermit.util.TextureLoader;
/*    */ 
/*    */ public class Analytical
/*    */   extends AbstractDailyMod
/*    */ {
/* 12 */   public static final String ID = downfallMod.makeID("Analytical");
/*    */   
/*    */   public static final String NAME;
/*    */   
/*    */   public static final String DESC;
/*    */   
/* 18 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString(ID); static {
/* 19 */     NAME = modStrings.NAME;
/* 20 */     DESC = modStrings.DESCRIPTION;
/*    */   }
/*    */   
/*    */   public Analytical() {
/* 24 */     super(ID, NAME, DESC, null, false);
/* 25 */     this.img = TextureLoader.getTexture("downfallResources/images/dailies/analytical.png");
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\dailymods\Analytical.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */