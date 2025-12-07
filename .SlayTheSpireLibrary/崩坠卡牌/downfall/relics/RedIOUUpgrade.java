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
/*    */ public class RedIOUUpgrade
/*    */   extends CustomRelic {
/* 14 */   public static String ID = downfallMod.makeID("RedIOUUpgrade");
/* 15 */   private static Texture IMG = new Texture(downfallMod.assetPath("images/relics/BanditContractUpgrade.png"));
/* 16 */   private static Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/BanditContractUpgrade.png"));
/*    */   
/*    */   public RedIOUUpgrade() {
/* 19 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 29 */     if (!this.usedUp && 
/* 30 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss && AbstractDungeon.actNum == 3) {
/* 31 */       flash();
/* 32 */       AbstractMonster boss = AbstractDungeon.getMonsters().getRandomMonster(true);
/* 33 */       while (!(boss instanceof charbosses.bosses.AbstractCharBoss)) {
/* 34 */         boss = AbstractDungeon.getMonsters().getRandomMonster(true);
/*    */       }
/* 36 */       addToBot((AbstractGameAction)new BanditIOUAction(AbstractDungeon.player, boss));
/* 37 */       usedUp();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\RedIOUUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */