/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.actions.BanditIOUAction;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class RedIOU
/*    */   extends CustomRelic {
/* 14 */   public static String ID = downfallMod.makeID("RedIOU");
/* 15 */   private static Texture IMG = new Texture(downfallMod.assetPath("images/relics/BanditContract.png"));
/* 16 */   private static Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/BanditContract.png"));
/*    */   
/*    */   public RedIOU() {
/* 19 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 30 */     if (!this.usedUp && 
/* 31 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss && AbstractDungeon.actNum == 3) {
/* 32 */       flash();
/* 33 */       AbstractMonster boss = AbstractDungeon.getMonsters().getRandomMonster(true);
/* 34 */       while (!(boss instanceof charbosses.bosses.AbstractCharBoss)) {
/* 35 */         boss = AbstractDungeon.getMonsters().getRandomMonster(true);
/*    */       }
/* 37 */       addToBot((AbstractGameAction)new BanditIOUAction(AbstractDungeon.player, boss));
/* 38 */       usedUp();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\RedIOU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */