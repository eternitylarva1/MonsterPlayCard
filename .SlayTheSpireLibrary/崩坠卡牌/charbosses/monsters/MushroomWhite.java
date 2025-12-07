/*    */ package charbosses.monsters;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class MushroomWhite
/*    */   extends AbstractMonster {
/* 15 */   public static final String ID = downfallMod.makeID("MushroomWhite");
/* 16 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 17 */   public static final String NAME = monsterStrings.NAME;
/*    */   private static final int MAX_HP = 20;
/*    */   private static final int PROTECTION_AMT = 10;
/*    */   private int protectionAmt;
/*    */   
/*    */   public MushroomWhite(float x, float y) {
/* 23 */     super(NAME, ID, 20, 0.0F, 10.0F, 120.0F, 165.0F, null, x, y);
/* 24 */     loadAnimation(downfallMod.assetPath("images/monsters/whiteshroom/whiteshroom.atlas"), downfallMod.assetPath("images/monsters/whiteshroom/whiteshroom.json"), 1.0F);
/* 25 */     this.state.setAnimation(0, "Idle", true);
/* 26 */     this.stateData.setMix("Idle", "Floop", 0.2F);
/* 27 */     this.protectionAmt = 10;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 33 */     this.state.setAnimation(0, "Floop", false);
/*    */     
/* 35 */     this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */     
/* 37 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, this.protectionAmt));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void getMove(int i) {
/* 43 */     setMove((byte)0, AbstractMonster.Intent.DEFEND);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\MushroomWhite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */