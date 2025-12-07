/*    */ package charbosses.monsters;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.powers.ExhaustEndOfTurnPower;
/*    */ 
/*    */ public class MushroomPurple extends AbstractMonster {
/* 14 */   public static final String ID = downfallMod.makeID("MushroomPurple");
/* 15 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 16 */   public static final String NAME = monsterStrings.NAME;
/*    */   private static final int MAX_HP = 20;
/*    */   
/*    */   public MushroomPurple(float x, float y) {
/* 20 */     super(NAME, ID, 20, 0.0F, 10.0F, 120.0F, 165.0F, null, x, y);
/* 21 */     loadAnimation(downfallMod.assetPath("images/monsters/purpleshroom/purpleshroom.atlas"), downfallMod.assetPath("images/monsters/purpleshroom/purpleshroom.json"), 1.0F);
/* 22 */     this.state.setAnimation(0, "Idle", true);
/* 23 */     this.stateData.setMix("Idle", "Kyuuuuu", 0.2F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 30 */     this.state.setAnimation(0, "Kyuuuuu", false);
/*    */     
/* 32 */     this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */     
/* 34 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ExhaustEndOfTurnPower((AbstractCreature)AbstractDungeon.player), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void getMove(int i) {
/* 40 */     setMove((byte)0, AbstractMonster.Intent.DEBUFF);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\MushroomPurple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */