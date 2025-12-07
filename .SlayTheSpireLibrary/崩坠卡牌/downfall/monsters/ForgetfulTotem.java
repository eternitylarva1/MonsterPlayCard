/*    */ package downfall.monsters;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.powers.ExhaustEndOfTurnPower;
/*    */ import downfall.vfx.TotemBeamEffect;
/*    */ 
/*    */ public class ForgetfulTotem extends AbstractTotemMonster {
/* 25 */   public static final String ID = downfallMod.makeID("ForgetfulTotem");
/*    */   public static final String NAME;
/*    */   public static final String[] MOVES;
/*    */   public static final String[] DIALOG;
/*    */   private static final MonsterStrings monsterStrings;
/* 30 */   public static Color totemColor = Color.GREEN; public Integer attackDmg; public Integer secondaryEffect;
/*    */   
/*    */   static {
/* 33 */     monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 34 */     NAME = monsterStrings.NAME;
/* 35 */     MOVES = monsterStrings.MOVES;
/* 36 */     DIALOG = monsterStrings.DIALOG;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ForgetfulTotem() {
/* 45 */     super(NAME, ID, downfallMod.assetPath("images/monsters/livingwall/livingwall.png"));
/* 46 */     loadAnimation(downfallMod.assetPath("images/monsters/livingwall/livingwall3.atlas"), downfallMod.assetPath("images/monsters/livingwall/livingwall3.json"), 1.0F);
/*    */     
/* 48 */     this.drawY += 250.0F * Settings.scale;
/*    */     
/* 50 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 51 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */     
/* 53 */     if (AbstractDungeon.ascensionLevel >= 18) {
/* 54 */       this.attackDmg = Integer.valueOf(6);
/* 55 */       this.secondaryEffect = Integer.valueOf(2);
/* 56 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/* 57 */       this.attackDmg = Integer.valueOf(6);
/* 58 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } else {
/* 60 */       this.attackDmg = Integer.valueOf(5);
/* 61 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } 
/* 63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg.intValue()));
/*    */     
/* 65 */     this.intentType = AbstractMonster.Intent.ATTACK_DEBUFF;
/*    */     
/* 67 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/* 68 */     this.totemLivingColor = totemColor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void totemAttack() {
/* 75 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/* 76 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 77 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GREEN)));
/* 78 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 79 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new TotemBeamEffect(this.hb.cX + beamOffsetX.floatValue(), this.hb.cY + beamOffsetY.floatValue(), AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.GOLD.cpy(), this.hb.cX + beamOffsetX2.floatValue(), this.hb.cY + beamOffsetY2.floatValue()), 0.1F));
/* 80 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
/* 81 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ExhaustEndOfTurnPower((AbstractCreature)AbstractDungeon.player), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void getUniqueTotemMove() {
/* 87 */     setMove((byte)1, this.intentType, this.attackDmg.intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\ForgetfulTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */