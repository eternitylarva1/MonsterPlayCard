/*    */ package downfall.dailymods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class TransformRewards
/*    */   extends AbstractDailyMod {
/* 11 */   public static final String ID = downfallMod.makeID("TransformRewards");
/*    */   
/*    */   public static final String NAME;
/*    */   
/*    */   public static final String DESC;
/*    */   
/* 17 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString(ID); static {
/* 18 */     NAME = modStrings.NAME;
/* 19 */     DESC = modStrings.DESCRIPTION;
/*    */   }
/*    */   
/*    */   public TransformRewards() {
/* 23 */     super(ID, NAME, DESC, null, false);
/* 24 */     this.img = TextureLoader.getTexture("downfallResources/images/dailies/transformrewards.png");
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\dailymods\TransformRewards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */