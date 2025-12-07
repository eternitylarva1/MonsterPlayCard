/*    */ package downfall.monsters;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ import hermit.cards.ImpendingDoom;
/*    */ 
/*    */ public class DoomedDagger extends AbstractMonster {
/* 20 */   public static final String ID = downfallMod.makeID("DoomedDagger");
/* 21 */   public static final String NAME = (CardCrawlGame.languagePack.getMonsterStrings(downfallMod.makeID("DoomedDagger"))).NAME;
/*    */   
/*    */   public boolean firstMove = true;
/*    */   
/*    */   public DoomedDagger(float x, float y) {
/* 26 */     super(NAME, ID, AbstractDungeon.monsterHpRng.random(50, 60), 0.0F, -50.0F, 140.0F, 130.0F, null, x, y);
/* 27 */     initializeAnimation();
/* 28 */     this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/* 29 */     this.damage.add(new DamageInfo((AbstractCreature)this, 25));
/*    */   }
/*    */   
/*    */   public void initializeAnimation() {
/* 33 */     loadAnimation("images/monsters/theForest/mage_dagger/skeleton.atlas", "images/monsters/theForest/mage_dagger/skeleton.json", 1.0F);
/* 34 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 35 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */   }
/*    */   public void takeTurn() {
/*    */     ImpendingDoom impendingDoom;
/* 39 */     switch (this.nextMove) {
/*    */       case 1:
/* 41 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 42 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 43 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/* 44 */         impendingDoom = new ImpendingDoom();
/*    */         
/* 46 */         addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)impendingDoom, 1, false, true));
/*    */         break;
/*    */       case 2:
/* 49 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "SUICIDE"));
/* 50 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/* 51 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 52 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LoseHPAction((AbstractCreature)this, (AbstractCreature)this, this.currentHealth));
/*    */         break;
/*    */     } 
/* 55 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */   }
/*    */   
/*    */   public void damage(DamageInfo info) {
/* 59 */     super.damage(info);
/* 60 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 61 */       this.state.setAnimation(0, "Hurt", false);
/* 62 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 63 */       this.stateData.setMix("Hurt", "Idle", 0.1F);
/* 64 */       this.stateData.setMix("Idle", "Hurt", 0.1F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 70 */     if (this.firstMove) {
/* 71 */       this.firstMove = false;
/* 72 */       setMove((byte)1, AbstractMonster.Intent.ATTACK_DEBUFF, 9);
/*    */     } else {
/* 74 */       setMove((byte)2, AbstractMonster.Intent.ATTACK, 25);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void changeState(String key) {
/* 79 */     byte var3 = -1;
/* 80 */     switch (key.hashCode()) {
/*    */       case -1143642610:
/* 82 */         if (key.equals("SUICIDE")) {
/* 83 */           var3 = 1;
/*    */         }
/*    */         break;
/*    */       case 1941037640:
/* 87 */         if (key.equals("ATTACK")) {
/* 88 */           var3 = 0;
/*    */         }
/*    */         break;
/*    */     } 
/* 92 */     switch (var3) {
/*    */       case 0:
/* 94 */         this.state.setAnimation(0, "Attack", false);
/* 95 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */         break;
/*    */       case 1:
/* 98 */         this.state.setAnimation(0, "Attack2", false);
/* 99 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\DoomedDagger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */