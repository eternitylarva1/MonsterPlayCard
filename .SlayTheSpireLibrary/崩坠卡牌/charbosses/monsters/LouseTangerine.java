/*    */ package charbosses.monsters;
/*    */ import charbosses.powers.bossmechanicpowers.TangerinePower;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.CurlUpPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class LouseTangerine extends AbstractMonster {
/*    */   public static final String ID = "FuzzyLouseTangerine";
/* 21 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("hermit:SpecialFriend"); private static final MonsterStrings monsterStrings;
/*    */   public static final String NAME;
/*    */   private int biteDamage;
/*    */   public boolean sleepy = true;
/*    */   
/*    */   public LouseTangerine(float x, float y) {
/* 27 */     super(NAME, "FuzzyLouseNormal", 48, 0.0F, -5.0F, 180.0F, 140.0F, (String)null, x, y);
/* 28 */     loadAnimation("expansioncontentResources/images/bosses/hermit/1/tangerine/skeleton_2.atlas", "expansioncontentResources/images/bosses/hermit/1/tangerine/skeleton_2.json", 1.0F);
/* 29 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 30 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */ 
/*    */     
/* 33 */     if (AbstractDungeon.ascensionLevel >= 9) {
/* 34 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(6, 8);
/*    */     } else {
/* 36 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(5, 7);
/*    */     } 
/*    */     
/* 39 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDamage));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void usePreBattleAction() {
/* 45 */     int curl_amount = AbstractDungeon.monsterHpRng.random(8);
/*    */     
/* 47 */     if (AbstractDungeon.ascensionLevel >= 9) curl_amount = 12;
/*    */     
/* 49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, curl_amount)));
/* 50 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new TangerinePower((AbstractCreature)this, 3)));
/* 51 */     setMove((byte)1, AbstractMonster.Intent.SLEEP);
/*    */   }
/*    */ 
/*    */   
/*    */   public void takeTurn() {
/* 56 */     switch (this.nextMove) {
/*    */       case 3:
/* 58 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 59 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */         break;
/*    */       case 4:
/* 62 */         if (AbstractDungeon.ascensionLevel >= 9) {
/* 63 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 4), 4)); break;
/*    */         } 
/* 65 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/*    */         break;
/*    */     } 
/*    */     
/* 69 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void getMove(int num) {
/* 74 */     if (this.sleepy) {
/*    */       
/* 76 */       if (lastMove((byte)1)) {
/*    */         
/* 78 */         setMove((byte)2, AbstractMonster.Intent.SLEEP);
/*    */       } else {
/*    */         
/* 81 */         this.sleepy = false;
/* 82 */         setMove(MOVES[0], (byte)4, AbstractMonster.Intent.BUFF);
/*    */       }
/*    */     
/* 85 */     } else if (lastMove((byte)4)) {
/* 86 */       setMove((byte)3, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*    */     } else {
/* 88 */       setMove(MOVES[0], (byte)4, AbstractMonster.Intent.BUFF);
/*    */     } 
/*    */   }
/*    */   
/*    */   static {
/* 93 */     monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("FuzzyLouseNormal");
/* 94 */     NAME = uiStrings.TEXT[0];
/* 95 */     MOVES = monsterStrings.MOVES;
/* 96 */     DIALOG = monsterStrings.DIALOG;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\LouseTangerine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */