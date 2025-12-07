/*    */ package downfall.monsters;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.vfx.TotemBeamEffect;
/*    */ 
/*    */ public class ChangingTotem extends AbstractTotemMonster {
/* 21 */   public static final String ID = downfallMod.makeID("ChangingTotem");
/*    */   public static final String NAME;
/*    */   public static final String[] MOVES;
/*    */   public static final String[] DIALOG;
/*    */   private static final MonsterStrings monsterStrings;
/* 26 */   public static Color totemColor = Color.GREEN; public Integer attackDmg; public Integer secondaryEffect;
/*    */   
/*    */   static {
/* 29 */     monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 30 */     NAME = monsterStrings.NAME;
/* 31 */     MOVES = monsterStrings.MOVES;
/* 32 */     DIALOG = monsterStrings.DIALOG;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ChangingTotem() {
/* 41 */     super(NAME, ID, downfallMod.assetPath("images/monsters/livingwall/livingwall.png"));
/* 42 */     loadAnimation(downfallMod.assetPath("images/monsters/livingwall/livingwall1.atlas"), downfallMod.assetPath("images/monsters/livingwall/livingwall1.json"), 1.0F);
/*    */     
/* 44 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 45 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */     
/* 47 */     if (AbstractDungeon.ascensionLevel >= 18) {
/* 48 */       this.attackDmg = Integer.valueOf(6);
/* 49 */       this.secondaryEffect = Integer.valueOf(2);
/* 50 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/* 51 */       this.attackDmg = Integer.valueOf(6);
/* 52 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } else {
/* 54 */       this.attackDmg = Integer.valueOf(5);
/* 55 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } 
/* 57 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg.intValue()));
/*    */     
/* 59 */     this.intentType = AbstractMonster.Intent.DEBUFF;
/*    */     
/* 61 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/* 62 */     this.totemLivingColor = totemColor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void totemAttack() {
/* 69 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/* 70 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*    */     
/* 72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GREEN)));
/* 73 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 74 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new TotemBeamEffect(this.hb.cX + beamOffsetX.floatValue(), this.hb.cY + beamOffsetY.floatValue(), AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.GOLD.cpy(), this.hb.cX + beamOffsetX2.floatValue(), this.hb.cY + beamOffsetY2.floatValue()), 0.1F));
/*    */     
/* 76 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new TransformDrawnCardsPower((AbstractCreature)AbstractDungeon.player, this.secondaryEffect.intValue()), this.secondaryEffect.intValue()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void getUniqueTotemMove() {
/* 82 */     setMove((byte)1, this.intentType, this.attackDmg.intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\ChangingTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */