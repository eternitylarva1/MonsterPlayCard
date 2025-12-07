/*     */ package downfall.actions;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.daily.mods.Careless;
/*     */ import com.megacrit.cardcrawl.daily.mods.ControlledChaos;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.relics.SlaversCollar;
/*     */ import com.megacrit.cardcrawl.ui.FtueTip;
/*     */ import com.megacrit.cardcrawl.ui.MultiPageFtue;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
/*     */ import downfall.monsters.NeowBoss;
/*     */ 
/*     */ public class NeowResetFightAction
/*     */   extends AbstractGameAction {
/*     */   private NeowBoss owner;
/*     */   
/*     */   public void update() {
/*  30 */     AbstractDungeon.resetPlayer();
/*  31 */     AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
/*     */ 
/*     */ 
/*     */     
/*  35 */     if (!((Boolean)TipTracker.tips.get("COMBAT_TIP")).booleanValue()) {
/*  36 */       AbstractDungeon.ftue = (FtueTip)new MultiPageFtue();
/*  37 */       TipTracker.neverShowAgain("COMBAT_TIP");
/*     */     } 
/*     */     
/*  40 */     AbstractDungeon.player.damagedThisCombat = 0;
/*  41 */     AbstractDungeon.player.cardsPlayedThisTurn = 0;
/*  42 */     AbstractDungeon.player.maxOrbs = 0;
/*  43 */     AbstractDungeon.player.orbs.clear();
/*  44 */     AbstractDungeon.player.increaseMaxOrbSlots(AbstractDungeon.player.masterMaxOrbs, false);
/*  45 */     AbstractDungeon.player.isBloodied = (AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth / 2);
/*  46 */     AbstractPlayer.poisonKillCount = 0;
/*  47 */     GameActionManager.playerHpLastTurn = AbstractDungeon.player.currentHealth;
/*  48 */     AbstractDungeon.player.endTurnQueued = false;
/*  49 */     AbstractDungeon.player.gameHandSize = AbstractDungeon.player.masterHandSize;
/*  50 */     AbstractDungeon.player.isDraggingCard = false;
/*  51 */     AbstractDungeon.player.isHoveringDropZone = false;
/*  52 */     AbstractDungeon.player.hoveredCard = null;
/*  53 */     AbstractDungeon.player.cardInUse = null;
/*  54 */     AbstractDungeon.player.drawPile.initializeDeck(AbstractDungeon.player.masterDeck);
/*  55 */     AbstractDungeon.overlayMenu.endTurnButton.enabled = false;
/*  56 */     AbstractDungeon.player.hand.clear();
/*  57 */     AbstractDungeon.player.discardPile.clear();
/*  58 */     AbstractDungeon.player.exhaustPile.clear();
/*     */     
/*  60 */     if (AbstractDungeon.player.hasRelic("SlaversCollar")) {
/*  61 */       ((SlaversCollar)AbstractDungeon.player.getRelic("SlaversCollar")).beforeEnergyPrep();
/*     */     }
/*     */     
/*  64 */     AbstractDungeon.player.energy.prep();
/*  65 */     AbstractDungeon.player.powers.clear();
/*  66 */     AbstractDungeon.player.isEndingTurn = false;
/*  67 */     AbstractDungeon.player.healthBarUpdatedEvent();
/*  68 */     if (ModHelper.isModEnabled("Lethality")) {
/*  69 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 3), 3));
/*     */     }
/*     */     
/*  72 */     if (ModHelper.isModEnabled("Terminal")) {
/*  73 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PlatedArmorPower((AbstractCreature)AbstractDungeon.player, 5), 5));
/*     */     }
/*     */     
/*  76 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(1.0F));
/*  77 */     AbstractDungeon.player.applyPreCombatLogic();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
/*  87 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.gameHandSize));
/*     */     
/*  89 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnableEndTurnButtonAction());
/*  90 */     AbstractDungeon.overlayMenu.showCombatPanels();
/*  91 */     AbstractDungeon.player.applyStartOfCombatLogic();
/*     */ 
/*     */     
/*  94 */     if (ModHelper.isModEnabled("Careless")) {
/*  95 */       Careless.modAction();
/*     */     }
/*  97 */     if (ModHelper.isModEnabled("ControlledChaos")) {
/*  98 */       ControlledChaos.modAction();
/*     */     }
/*     */ 
/*     */     
/* 102 */     (AbstractDungeon.getCurrRoom()).skipMonsterTurn = false;
/* 103 */     AbstractDungeon.player.applyStartOfTurnRelics();
/* 104 */     AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
/* 105 */     AbstractDungeon.player.applyStartOfTurnCards();
/* 106 */     AbstractDungeon.player.applyStartOfTurnPowers();
/* 107 */     AbstractDungeon.player.applyStartOfTurnOrbs();
/* 108 */     AbstractDungeon.actionManager.useNextCombatActions();
/*     */     
/* 110 */     this.isDone = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\NeowResetFightAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */