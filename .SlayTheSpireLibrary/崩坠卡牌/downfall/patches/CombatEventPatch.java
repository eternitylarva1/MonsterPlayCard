/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.events.exordium.Mushrooms;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.NotFoundException;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.Instanceof;
/*    */ import slimebound.SlimeboundMod;
/*    */ 
/*    */ @SpirePatch(clz = ProceedButton.class, method = "update")
/*    */ public class CombatEventPatch
/*    */ {
/*    */   public static ExprEditor Instrument() {
/* 16 */     return new ExprEditor()
/*    */       {
/*    */         public void edit(Instanceof i) throws CannotCompileException {
/*    */           try {
/* 20 */             if (i.getType().getName().equals(Mushrooms.class.getName())) {
/* 21 */               i.replace("$_ = $proceed($$) || currentRoom.event instanceof downfall.events.WomanInBlue_Evil || currentRoom.event instanceof downfall.events.GremlinMatchGame_Evil || currentRoom.event instanceof downfall.events.GremlinWheelGame_Evil || currentRoom.event instanceof downfall.events.LivingWall_Evil || currentRoom.event instanceof downfall.events.DeadGuy_Evil || currentRoom.event instanceof downfall.events.Augmenter_Evil || currentRoom.event instanceof downfall.events.FaceTrader_Evil || currentRoom.event instanceof downfall.events.Beggar_Evil || currentRoom.event instanceof downfall.events.Colosseum_Evil || currentRoom.event instanceof downfall.events.Bandits_Evil || currentRoom.event instanceof downfall.events.MindBloom_Evil || currentRoom.event instanceof slimebound.events.DarklingsSlimebound || currentRoom.event instanceof champ.events.Colosseum_Evil_Champ || currentRoom.event instanceof champ.events.MinorLeagueArena || currentRoom.event instanceof automaton.events.ShapeFactory || currentRoom.event instanceof automaton.events.AncientFactory;");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           }
/* 40 */           catch (NotFoundException e) {
/* 41 */             SlimeboundMod.logger.error("Combat proceed button patch broken.", (Throwable)e);
/*    */           } 
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CombatEventPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */