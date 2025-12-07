/*    */ package charbosses.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.relics.CBR_NeowsBlessing;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = ApplyPowerAction.class, method = "update")
/*    */ public class ApplyPowerPatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn Prefix(ApplyPowerAction instance) {
/* 31 */     float duration = ((Float)ReflectionHacks.getPrivate(instance, AbstractGameAction.class, "duration")).floatValue();
/* 32 */     float startingDuration = ((Float)ReflectionHacks.getPrivate(instance, ApplyPowerAction.class, "startingDuration")).floatValue();
/* 33 */     AbstractPower powerToApply = (AbstractPower)ReflectionHacks.getPrivate(instance, ApplyPowerAction.class, "powerToApply");
/*    */     
/* 35 */     if (instance.target != null && !instance.target.isDeadOrEscaped() && 
/* 36 */       duration == startingDuration) {
/* 37 */       if (powerToApply instanceof com.megacrit.cardcrawl.powers.NoDrawPower && instance.target.hasPower(powerToApply.ID)) {
/* 38 */         instance.isDone = true;
/* 39 */         return SpireReturn.Return(null);
/*    */       } 
/* 41 */       if (instance.target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster && instance.target.isDeadOrEscaped()) {
/* 42 */         ReflectionHacks.setPrivate(instance, AbstractGameAction.class, "duration", Float.valueOf(0.0F));
/* 43 */         instance.isDone = true;
/* 44 */         return SpireReturn.Return(null);
/*    */       } 
/*    */       
/* 47 */       if (instance.target instanceof AbstractCharBoss) {
/*    */         
/* 49 */         AbstractCharBoss cB = (AbstractCharBoss)instance.target;
/*    */ 
/*    */         
/* 52 */         if (cB.hasRelic("Ginger") && powerToApply.ID.equals("Weakened")) {
/* 53 */           cB.getRelic("Ginger").flash();
/* 54 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new TextAboveCreatureAction(instance.target, ApplyPowerAction.TEXT[1]));
/* 55 */           float newDuration = duration -= Gdx.graphics.getDeltaTime();
/* 56 */           ReflectionHacks.setPrivate(instance, AbstractGameAction.class, "duration", Float.valueOf(newDuration));
/* 57 */           return SpireReturn.Return(null);
/*    */         } 
/*    */ 
/*    */         
/* 61 */         if (cB.hasRelic(CBR_NeowsBlessing.ID) && Objects.equals(powerToApply.ID, "stslib:Stunned")) {
/* 62 */           cB.getRelic(CBR_NeowsBlessing.ID).flash();
/* 63 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new TextAboveCreatureAction(instance.target, ApplyPowerAction.TEXT[1]));
/* 64 */           float newDuration = duration -= Gdx.graphics.getDeltaTime();
/* 65 */           ReflectionHacks.setPrivate(instance, AbstractGameAction.class, "duration", Float.valueOf(newDuration));
/* 66 */           return SpireReturn.Return();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 72 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\ApplyPowerPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */