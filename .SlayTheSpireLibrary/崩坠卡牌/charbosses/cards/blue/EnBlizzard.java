/*     */ package charbosses.cards.blue;
/*     */ 
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnBlizzard
/*     */   extends AbstractBossCard
/*     */ {
/*     */   public static final String ID = "downfall_Charboss:Blizzard";
/*     */   
/*     */   public EnBlizzard() {
/*  31 */     super("downfall_Charboss:Blizzard", cardStrings.NAME, "blue/attack/blizzard", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY, AbstractMonster.Intent.ATTACK);
/*  32 */     this.baseDamage = 0;
/*  33 */     this.baseMagicNumber = 2;
/*  34 */     this.magicNumber = this.baseMagicNumber;
/*     */   }
/*     */   
/*     */   public void use(AbstractPlayer p, AbstractMonster m) {
/*  38 */     int frostCount = 0;
/*     */     
/*  40 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/*  41 */       if (o instanceof charbosses.orbs.EnemyFrost) {
/*  42 */         frostCount++;
/*     */       }
/*     */     } 
/*     */     
/*  46 */     this.baseDamage = frostCount * this.magicNumber;
/*  47 */     calculateCardDamage((AbstractMonster)null);
/*  48 */     if (Settings.FAST_MODE) {
/*  49 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(frostCount, true), 0.25F));
/*     */     } else {
/*  51 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(frostCount, true), 1.0F));
/*     */     } 
/*  53 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPowers() {
/*  58 */     int frostCount = 0;
/*     */     
/*  60 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/*  61 */       if (o instanceof charbosses.orbs.EnemyFrost) {
/*  62 */         frostCount++;
/*     */       }
/*     */     } 
/*     */     
/*  66 */     if (frostCount > 0) {
/*  67 */       this.intentMultiAmt = frostCount;
/*  68 */       this.baseDamage = frostCount * this.magicNumber;
/*  69 */       super.applyPowers();
/*  70 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/*  71 */       initializeDescription();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onMoveToDiscard() {
/*  77 */     this.rawDescription = cardStrings.DESCRIPTION;
/*  78 */     initializeDescription();
/*     */   }
/*     */   
/*     */   public void calculateCardDamage(AbstractMonster mo) {
/*  82 */     int frostCount = 0;
/*     */     
/*  84 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/*  85 */       if (o instanceof charbosses.orbs.EnemyFrost) {
/*  86 */         frostCount++;
/*     */       }
/*     */     } 
/*     */     
/*  90 */     if (frostCount > 0) {
/*  91 */       this.intentMultiAmt = frostCount;
/*  92 */       this.baseDamage = frostCount * this.magicNumber;
/*  93 */       super.calculateCardDamage(mo);
/*  94 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/*  95 */       initializeDescription();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void upgrade() {
/* 100 */     if (!this.upgraded) {
/* 101 */       upgradeName();
/* 102 */       upgradeMagicNumber(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/* 108 */     return (AbstractCard)new EnBlizzard();
/*     */   }
/*     */ 
/*     */   
/* 112 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blizzard");
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnBlizzard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */