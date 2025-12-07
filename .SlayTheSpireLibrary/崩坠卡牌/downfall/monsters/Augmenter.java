/*    */ package downfall.monsters;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class Augmenter extends AbstractMonster {
/* 20 */   public static final String ID = downfallMod.makeID("Augmenter");
/* 21 */   public static final String NAME = (CardCrawlGame.languagePack.getEventString("Drug Dealer")).NAME;
/*    */   private static final float HB_X = 0.0F;
/*    */   private static final float HB_Y = 0.0F;
/*    */   private static final float HB_W = 150.0F;
/*    */   private static final float HB_H = 320.0F;
/*    */   boolean move2 = false;
/*    */   
/*    */   public Augmenter() {
/* 29 */     super(NAME, ID, 200, 0.0F, 0.0F, 150.0F, 320.0F, "downfallResources/images/monsters/augmenter/augmenter.png");
/* 30 */     loadAnimation(downfallMod.assetPath("images/monsters/augmenter/augmenter.atlas"), downfallMod.assetPath("images/monsters/augmenter/augmenter.json"), 1.0F);
/*    */     
/* 32 */     this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/* 33 */     this.damage.add(new DamageInfo((AbstractCreature)this, 18));
/*    */     
/* 35 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 36 */     e.setTime(e.getEndTime() * MathUtils.random());
/* 37 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/* 38 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/* 39 */     this.state.setTimeScale(0.8F);
/*    */     
/* 41 */     this.type = AbstractMonster.EnemyType.ELITE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void usePreBattleAction() {
/* 46 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/* 47 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new LoseStrengthPower((AbstractCreature)this, 3), 3));
/* 48 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this, RelicLibrary.getRelic("MutagenicStrength")));
/*    */   }
/*    */   
/*    */   public void takeTurn() {
/* 52 */     switch (this.nextMove) {
/*    */       case 1:
/* 54 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
/* 55 */         addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)this, (AbstractCreature)this, 3));
/* 56 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/*    */         break;
/*    */       case 2:
/* 59 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE));
/* 60 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new TransformDrawnCardsPower((AbstractCreature)AbstractDungeon.player, 2), 2));
/*    */         break;
/*    */     } 
/*    */     
/* 64 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void damage(DamageInfo info) {
/* 69 */     super.damage(info);
/* 70 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 71 */       this.state.setAnimation(0, "Hit", false);
/* 72 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 78 */     if (this.move2) {
/* 79 */       this.move2 = false;
/* 80 */       setMove((byte)1, AbstractMonster.Intent.ATTACK_BUFF, ((DamageInfo)this.damage.get(0)).base);
/*    */     } else {
/* 82 */       this.move2 = true;
/* 83 */       setMove((byte)2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\Augmenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */