/*    */ package charbosses.monsters;
/*    */ 
/*    */ import charbosses.bosses.Defect.CharBossDefect;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.ApplyStasisAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BronzeOrbWhoReallyLikesDefectForSomeReason
/*    */   extends AbstractMonster
/*    */ {
/*    */   private boolean usedStasis = false;
/*    */   
/*    */   public BronzeOrbWhoReallyLikesDefectForSomeReason(float x, float y, int count) {
/* 41 */     super(monsterStrings.NAME, "BronzeOrbWhoReallyLikesDefectForSomeReason", AbstractDungeon.monsterHpRng.random(52, 58), 0.0F, 0.0F, 160.0F, 160.0F, "images/monsters/theCity/automaton/orb.png", x, y);
/* 42 */     if (AbstractDungeon.ascensionLevel >= 9) {
/* 43 */       setHp(54, 60);
/*    */     } else {
/* 45 */       setHp(52, 58);
/*    */     } 
/*    */     
/* 48 */     this.count = count;
/* 49 */     this.damage.add(new DamageInfo((AbstractCreature)this, 8));
/*    */   }
/*    */   
/*    */   public void takeTurn() {
/* 53 */     switch (this.nextMove) {
/*    */       case 1:
/* 55 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 56 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.SKY)));
/* 57 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
/* 58 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
/*    */         break;
/*    */       case 2:
/* 61 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.getMonsters().getMonster(CharBossDefect.ID), (AbstractCreature)this, 12));
/*    */         break;
/*    */       case 3:
/* 64 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyStasisAction((AbstractCreature)this));
/*    */         break;
/*    */     } 
/* 67 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */   }
/*    */   
/*    */   public void update() {
/* 71 */     super.update();
/* 72 */     if (this.count % 2 == 0) {
/* 73 */       this.animY = MathUtils.cosDeg((float)(System.currentTimeMillis() / 6L % 360L)) * 6.0F * Settings.scale;
/*    */     } else {
/* 75 */       this.animY = -MathUtils.cosDeg((float)(System.currentTimeMillis() / 6L % 360L)) * 6.0F * Settings.scale;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 81 */     if (!this.usedStasis && num >= 25) {
/* 82 */       setMove((byte)3, AbstractMonster.Intent.STRONG_DEBUFF);
/* 83 */       this.usedStasis = true;
/* 84 */     } else if (num >= 70 && !lastTwoMoves((byte)2)) {
/* 85 */       setMove((byte)2, AbstractMonster.Intent.DEFEND);
/* 86 */     } else if (!lastTwoMoves((byte)1)) {
/* 87 */       setMove((byte)1, AbstractMonster.Intent.ATTACK, 8);
/*    */     } else {
/* 89 */       setMove((byte)2, AbstractMonster.Intent.DEFEND);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 94 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BronzeOrb");
/* 95 */   public static final String[] MOVES = monsterStrings.MOVES;
/* 96 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*    */   public static final String ID = "BronzeOrbWhoReallyLikesDefectForSomeReason";
/*    */   private static final int HP_MIN = 52;
/*    */   private static final int HP_MAX = 58;
/*    */   private static final int A_2_HP_MIN = 54;
/*    */   private static final int A_2_HP_MAX = 60;
/*    */   private static final int BEAM_DMG = 8;
/*    */   private static final int BLOCK_AMT = 12;
/*    */   private static final byte BEAM = 1;
/*    */   private static final byte SUPPORT_BEAM = 2;
/*    */   private static final byte STASIS = 3;
/*    */   private int count;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\BronzeOrbWhoReallyLikesDefectForSomeReason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */