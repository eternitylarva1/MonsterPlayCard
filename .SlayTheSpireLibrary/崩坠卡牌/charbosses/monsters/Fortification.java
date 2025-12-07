/*    */ package charbosses.monsters;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DemonFormPower;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class Fortification
/*    */   extends AbstractMonster
/*    */ {
/* 23 */   public static final String ID = downfallMod.makeID("Fortification");
/* 24 */   public static final String NAME = (CardCrawlGame.languagePack.getMonsterStrings(ID)).NAME;
/*    */   
/*    */   public Fortification() {
/* 27 */     super(NAME, "SpireShield", 140, 0.0F, -20.0F, 250.0F, 290.0F, (String)null, -450.0F, -15.0F);
/* 28 */     this.type = AbstractMonster.EnemyType.NORMAL;
/* 29 */     loadAnimation("images/monsters/theEnding/shield/skeleton.atlas", "images/monsters/theEnding/shield/skeleton.json", 1.0F);
/* 30 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 31 */     e.setTime(e.getEndTime() * MathUtils.random());
/* 32 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/* 33 */     this.flipHorizontal = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 38 */     if (AbstractCharBoss.boss != null) {
/* 39 */       if (!AbstractCharBoss.boss.isDead && !AbstractCharBoss.boss.isDying)
/* 40 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)this, 10)); 
/* 41 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/* 42 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 48 */     setMove((byte)0, AbstractMonster.Intent.DEFEND);
/*    */   }
/*    */ 
/*    */   
/*    */   public void die() {
/* 53 */     super.die();
/* 54 */     if (AbstractCharBoss.boss != null) {
/* 55 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, "Barricade"));
/*    */       
/* 57 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new DemonFormPower((AbstractCreature)AbstractCharBoss.boss, 5), 5));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\Fortification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */