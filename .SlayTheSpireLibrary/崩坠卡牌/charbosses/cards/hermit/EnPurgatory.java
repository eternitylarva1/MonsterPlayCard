/*    */ package charbosses.cards.hermit;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SuicideAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*    */ import hermit.cards.Purgatory;
/*    */ import hermit.characters.hermit;
/*    */ import hermit.vfx.ShortScreenFire;
/*    */ 
/*    */ public class EnPurgatory extends AbstractHermitBossCard {
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Purgatory.ID); public static final String ID = "downfall_Charboss:Purgatory";
/*    */   
/*    */   public EnPurgatory() {
/* 26 */     super("downfall_Charboss:Purgatory", cardStrings.NAME, "hermitResources/images/cards/purgatory.png", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 27 */     this.baseDamage = 24;
/* 28 */     this.isEthereal = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShortScreenFire(), 0.5F));
/* 34 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
/*    */     
/* 36 */     for (AbstractMonster m2 : (AbstractDungeon.getMonsters()).monsters) {
/* 37 */       if (!m2.isDead && !m2.isDying && m2 instanceof com.megacrit.cardcrawl.monsters.beyond.SnakeDagger) {
/* 38 */         addToBot((AbstractGameAction)new VFXAction((AbstractCreature)m2, (AbstractGameEffect)new InflameEffect((AbstractCreature)m), 0.2F));
/* 39 */         addToBot((AbstractGameAction)new SuicideAction(m2));
/* 40 */         addToBot((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m2));
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     addToBot((AbstractGameAction)new SpawnMonsterAction(ArchetypeAct3DoomsdayNewAge.getDoomedSnake(), true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return (AbstractCard)new EnPurgatory();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnPurgatory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */