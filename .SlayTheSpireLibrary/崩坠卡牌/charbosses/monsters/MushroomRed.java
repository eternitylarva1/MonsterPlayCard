/*    */ package charbosses.monsters;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class MushroomRed extends AbstractMonster {
/* 13 */   public static final String ID = downfallMod.makeID("MushroomRed");
/* 14 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 15 */   public static final String NAME = monsterStrings.NAME;
/*    */   private static final int MAX_HP = 20;
/*    */   private int protectionAmt;
/*    */   
/*    */   public MushroomRed(float x, float y) {
/* 20 */     super(NAME, ID, 20, 0.0F, 10.0F, 120.0F, 165.0F, null, x, y);
/* 21 */     loadAnimation(downfallMod.assetPath("images/monsters/redshroom/redshroom.atlas"), downfallMod.assetPath("images/monsters/redshroom/redshroom.json"), 1.0F);
/* 22 */     this.state.setAnimation(0, "Idle", true);
/* 23 */     this.stateData.setMix("Idle", "Rawr", 0.2F);
/*    */     
/* 25 */     this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 31 */     this.state.setAnimation(0, "Rawr", false);
/*    */     
/* 33 */     this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */     
/* 35 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void getMove(int i) {
/* 41 */     setMove((byte)0, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\MushroomRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */