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
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import downfall.downfallMod;
/*    */ import downfall.vfx.TotemBeamEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class GrowingTotem extends AbstractTotemMonster {
/* 27 */   public static final String ID = downfallMod.makeID("GrowingTotem");
/*    */   public static final String NAME;
/*    */   public static final String[] MOVES;
/*    */   public static final String[] DIALOG;
/*    */   private static final MonsterStrings monsterStrings;
/* 32 */   public static Color totemColor = Color.GREEN; public Integer attackDmg; public Integer secondaryEffect;
/*    */   
/*    */   static {
/* 35 */     monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
/* 36 */     NAME = monsterStrings.NAME;
/* 37 */     MOVES = monsterStrings.MOVES;
/* 38 */     DIALOG = monsterStrings.DIALOG;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GrowingTotem() {
/* 47 */     super(NAME, ID, downfallMod.assetPath("images/monsters/livingwall/livingwall.png"));
/* 48 */     loadAnimation(downfallMod.assetPath("images/monsters/livingwall/livingwall2.atlas"), downfallMod.assetPath("images/monsters/livingwall/livingwall2.json"), 1.0F);
/*    */     
/* 50 */     this.drawY += 500.0F * Settings.scale;
/*    */     
/* 52 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 53 */     e.setTime(e.getEndTime() * MathUtils.random());
/*    */     
/* 55 */     if (AbstractDungeon.ascensionLevel >= 18) {
/* 56 */       this.attackDmg = Integer.valueOf(6);
/* 57 */       this.secondaryEffect = Integer.valueOf(2);
/* 58 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/* 59 */       this.attackDmg = Integer.valueOf(6);
/* 60 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } else {
/* 62 */       this.attackDmg = Integer.valueOf(5);
/* 63 */       this.secondaryEffect = Integer.valueOf(1);
/*    */     } 
/* 65 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg.intValue()));
/*    */     
/* 67 */     this.intentType = AbstractMonster.Intent.ATTACK_BUFF;
/*    */     
/* 69 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/* 70 */     this.totemLivingColor = totemColor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void totemAttack() {
/* 77 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/* 78 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*    */     
/* 80 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GREEN)));
/* 81 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 82 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new TotemBeamEffect(this.hb.cX + beamOffsetX.floatValue(), this.hb.cY + beamOffsetY.floatValue(), AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.GOLD.cpy(), this.hb.cX + beamOffsetX2.floatValue(), this.hb.cY + beamOffsetY2.floatValue()), 0.1F));
/* 83 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
/*    */     
/* 85 */     ArrayList<AbstractMonster> targets = new ArrayList<>();
/* 86 */     targets.addAll((AbstractDungeon.getMonsters()).monsters);
/*    */ 
/*    */     
/* 89 */     for (AbstractMonster m : targets) {
/* 90 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.secondaryEffect.intValue()), this.secondaryEffect.intValue()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void getUniqueTotemMove() {
/* 97 */     setMove((byte)1, this.intentType, this.attackDmg.intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\monsters\GrowingTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */