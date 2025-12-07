/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*    */ import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_M;
/*    */ import com.megacrit.cardcrawl.monsters.exordium.LouseDefensive;
/*    */ import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;
/*    */ import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;
/*    */ import downfall.monsters.LooterAlt;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = MonsterHelper.class, method = "bottomHumanoid")
/*    */ public class ExordiumThugsPatch
/*    */ {
/*    */   public static MonsterGroup Postfix(MonsterGroup p) {
/* 23 */     if (EvilModeCharacterSelect.evilMode) {
/* 24 */       AbstractMonster[] monsters = new AbstractMonster[2];
/* 25 */       monsters[0] = bottomGetWeakWildlife(randomXOffset(-160.0F), randomYOffset(20.0F));
/* 26 */       monsters[1] = (AbstractMonster)new LooterAlt(randomXOffset(130.0F), randomYOffset(20.0F));
/* 27 */       return new MonsterGroup(monsters);
/*    */     } 
/* 29 */     return p;
/*    */   }
/*    */   
/*    */   private static AbstractMonster bottomGetWeakWildlife(float x, float y) {
/* 33 */     ArrayList<AbstractMonster> monsters = new ArrayList<>();
/* 34 */     if (AbstractDungeon.miscRng.randomBoolean()) {
/* 35 */       monsters.add(new LouseNormal(x, y));
/*    */     }
/* 37 */     monsters.add(new LouseDefensive(x, y));
/* 38 */     monsters.add(new SpikeSlime_M(x, y));
/* 39 */     monsters.add(new AcidSlime_M(x, y));
/*    */     
/* 41 */     return monsters.get(AbstractDungeon.miscRng.random(0, monsters.size() - 1));
/*    */   }
/*    */   
/*    */   private static float randomYOffset(float y) {
/* 45 */     return y + MathUtils.random(-20.0F, 20.0F);
/*    */   }
/*    */   
/*    */   private static float randomXOffset(float x) {
/* 49 */     return x + MathUtils.random(-20.0F, 20.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ExordiumThugsPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */