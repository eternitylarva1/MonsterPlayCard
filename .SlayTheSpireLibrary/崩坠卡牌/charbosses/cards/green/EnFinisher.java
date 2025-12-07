/*     */ package charbosses.cards.green;
/*     */ 
/*     */ import charbosses.actions.unique.EnemyDamagePerAttackPlayedAction;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnFinisher
/*     */   extends AbstractBossCard
/*     */ {
/*     */   public static final String ID = "downfall_Charboss:Finisher";
/*  26 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Finisher");
/*     */ 
/*     */   
/*     */   public EnFinisher() {
/*  30 */     this(0);
/*     */   }
/*     */   
/*     */   public EnFinisher(int hitCount) {
/*  34 */     super("downfall_Charboss:Finisher", cardStrings.NAME, "green/attack/finisher", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/*  35 */     this.baseDamage = 6;
/*  36 */     this.isMultiDamage = true;
/*  37 */     this.magicNumber = hitCount;
/*  38 */     this.intentMultiAmt = this.magicNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public void use(AbstractPlayer p, AbstractMonster m) {
/*  43 */     addToBot((AbstractGameAction)new EnemyDamagePerAttackPlayedAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPowers() {
/*  48 */     super.applyPowers();
/*  49 */     int count = this.magicNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     this.rawDescription = cardStrings.DESCRIPTION;
/*  66 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/*     */     
/*  68 */     if (count == 1) {
/*  69 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/*     */     } else {
/*     */       
/*  72 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
/*     */     } 
/*     */     
/*  75 */     initializeDescription();
/*     */   }
/*     */   
/*     */   public void increaseHits(int amount) {
/*  79 */     this.magicNumber += amount;
/*  80 */     this.intentMultiAmt = this.magicNumber;
/*  81 */     this.lockIntentValues = false;
/*  82 */     createIntent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onMoveToDiscard() {
/*  90 */     this.rawDescription = cardStrings.DESCRIPTION;
/*  91 */     initializeDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public void upgrade() {
/*  96 */     if (!this.upgraded) {
/*  97 */       upgradeName();
/*  98 */       upgradeDamage(2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/* 104 */     return (AbstractCard)new EnFinisher();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnFinisher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */