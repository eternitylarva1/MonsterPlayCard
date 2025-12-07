/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.monsters.ending.SpireShield;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import slimebound.powers.PotencyPower;
/*    */ import theHexaghost.powers.EnhancePower;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = SpireShield.class, method = "takeTurn")
/*    */ public class SpireShieldPatch
/*    */ {
/*    */   @SpireInsertPatch(rloc = 8)
/*    */   public static SpireReturn<Void> Insert(SpireShield __instance) {
/* 24 */     if ((AbstractDungeon.player instanceof slimebound.characters.SlimeboundCharacter || AbstractDungeon.player instanceof theHexaghost.TheHexaghost || AbstractDungeon.player instanceof sneckomod.TheSnecko) && AbstractDungeon.aiRng.randomBoolean()) {
/*    */       
/* 26 */       if (AbstractDungeon.player instanceof slimebound.characters.SlimeboundCharacter && !AbstractDungeon.player.orbs.isEmpty()) {
/* 27 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)__instance, (AbstractPower)new PotencyPower((AbstractCreature)AbstractDungeon.player, (AbstractCreature)__instance, -1)));
/*    */       }
/*    */       
/* 30 */       if (AbstractDungeon.player instanceof slimebound.characters.SlimeboundCharacter && AbstractDungeon.player.orbs.isEmpty()) {
/* 31 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)__instance, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -1)));
/*    */       }
/*    */       
/* 34 */       if (AbstractDungeon.player instanceof theHexaghost.TheHexaghost) {
/* 35 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)__instance, (AbstractPower)new EnhancePower(-1), -1));
/*    */       }
/*    */       
/* 38 */       if (AbstractDungeon.player instanceof sneckomod.TheSnecko || AbstractDungeon.player instanceof guardian.characters.GuardianCharacter) {
/* 39 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)__instance, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -1)));
/*    */       }
/*    */       
/* 42 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction((AbstractMonster)__instance));
/* 43 */       return SpireReturn.Return();
/*    */     } 
/*    */     
/* 46 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\SpireShieldPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */