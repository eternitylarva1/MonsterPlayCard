/*    */ package downfall.relics;
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class HeartBlessingBlue extends CustomRelic {
/* 12 */   public static final String ID = downfallMod.makeID("HeartBlessingBlue");
/* 13 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/HeartBlessingBlue.png"));
/* 14 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/HeartBlessing.png"));
/*    */   
/*    */   public HeartBlessingBlue() {
/* 17 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 27 */     if ((AbstractDungeon.getCurrRoom()).monsters.monsters.stream().anyMatch(q -> (q.type == AbstractMonster.EnemyType.BOSS))) {
/* 28 */       flash();
/* 29 */       addToBot((AbstractGameAction)new AddTemporaryHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 10));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\HeartBlessingBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */