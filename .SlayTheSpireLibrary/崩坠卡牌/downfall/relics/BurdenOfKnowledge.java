/*     */ package downfall.relics;
/*     */ 
/*     */ import basemod.abstracts.CustomRelic;
/*     */ import champ.relics.Barbells;
/*     */ import collector.CollectorCollection;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*     */ import downfall.downfallMod;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import sneckomod.relics.UnknownEgg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BurdenOfKnowledge
/*     */   extends CustomRelic
/*     */ {
/*  37 */   public static final String ID = downfallMod.makeID("BurdenOfKnowledge");
/*  38 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/Burden.png"));
/*  39 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/Burden.png"));
/*  40 */   int effectCount = 0;
/*     */   
/*     */   public BurdenOfKnowledge() {
/*  43 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.MAGICAL);
/*     */   }
/*     */   
/*     */   public String getUpdatedDescription() {
/*  47 */     return this.DESCRIPTIONS[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquip() {
/*  52 */     downfallMod.removeAnyRelicFromPools("Toxic Egg 2");
/*  53 */     downfallMod.removeAnyRelicFromPools("Frozen Egg 2");
/*  54 */     downfallMod.removeAnyRelicFromPools("Molten Egg 2");
/*  55 */     downfallMod.removeAnyRelicFromPools(UnknownEgg.ID);
/*  56 */     downfallMod.removeAnyRelicFromPools("Guardian:StasisUpgradeRelic");
/*  57 */     downfallMod.removeAnyRelicFromPools(Barbells.ID);
/*     */     
/*  59 */     AbstractDungeon.player.decreaseMaxHealth(10);
/*  60 */     List<String> upgradedCards = new ArrayList<>();
/*     */     
/*  62 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  63 */       if (c.canUpgrade()) {
/*  64 */         this.effectCount++;
/*  65 */         if (this.effectCount <= 20) {
/*  66 */           float x = MathUtils.random(0.1F, 0.9F) * Settings.WIDTH;
/*  67 */           float y = MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT;
/*  68 */           AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
/*  69 */           AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
/*     */         } 
/*     */         
/*  72 */         upgradedCards.add(c.cardID);
/*  73 */         c.upgrade();
/*  74 */         AbstractDungeon.player.bottledCardUpgradeCheck(c);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  80 */     CardGroup group = CardGroup.getGroupWithoutBottledCards(CollectorCollection.collection);
/*  81 */     for (AbstractCard c : group.group) {
/*  82 */       if (c.canUpgrade()) {
/*  83 */         this.effectCount++;
/*  84 */         if (this.effectCount <= 20) {
/*  85 */           float x = MathUtils.random(0.1F, 0.9F) * Settings.WIDTH;
/*  86 */           float y = MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT;
/*  87 */           AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
/*  88 */           AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
/*     */         } 
/*  90 */         upgradedCards.add(c.cardID);
/*  91 */         c.upgrade();
/*  92 */         AbstractDungeon.player.bottledCardUpgradeCheck(c);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  97 */     AbstractDungeon.player.loseRelic(HeartBlessingRed.ID);
/*  98 */     AbstractDungeon.player.loseRelic(HeartBlessingBlue.ID);
/*  99 */     AbstractDungeon.player.loseRelic(HeartBlessingGreen.ID);
/*     */   }
/*     */   
/*     */   public void onPreviewObtainCard(AbstractCard c) {
/* 103 */     onObtainCard(c);
/*     */   }
/*     */   
/*     */   public void onObtainCard(AbstractCard c) {
/* 107 */     if (!c.upgraded) {
/* 108 */       c.upgrade();
/*     */     }
/*     */   }
/*     */   
/*     */   public void atBattleStart() {
/* 113 */     flash();
/* 114 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new MasterRealityPower((AbstractCreature)AbstractDungeon.player)));
/* 115 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -1), -1));
/* 116 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player, -1), -1));
/* 117 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 118 */     AbstractDungeon.player.hasRelic("Enchiridion");
/*     */     
/* 120 */     addToBot((AbstractGameAction)new ArmamentsAction(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractRelic makeCopy() {
/* 125 */     return (AbstractRelic)new BurdenOfKnowledge();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\BurdenOfKnowledge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */