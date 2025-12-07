/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.vfx.EnemyReaperEffect;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import downfall.monsters.NeowBoss;
/*    */ 
/*    */ 
/*    */ public class EnReaper
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Reaper";
/* 28 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reaper");
/*    */ 
/*    */   
/*    */   public EnReaper() {
/* 32 */     super("downfall_Charboss:Reaper", cardStrings.NAME, "red/attack/reaper", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 33 */     this.baseDamage = 4;
/*    */ 
/*    */     
/* 36 */     this.tags.add(AbstractCard.CardTags.HEALING);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 42 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new EnemyReaperEffect()));
/* 43 */     for (int x = (AbstractDungeon.getCurrRoom()).monsters.monsters.size() - 1; x >= 0; x--) {
/* 44 */       AbstractMonster q = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(x);
/* 45 */       if (!q.isDead && !q.isDying && !q.id.equals(m.id) && !q.id.equals(NeowBoss.ID)) {
/* 46 */         calculateCardDamage(q);
/* 47 */         addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)q, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 52 */         if (q.currentHealth <= this.damage) {
/* 53 */           addToBot((AbstractGameAction)new WaitAction(0.1F));
/* 54 */           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, 1), 1));
/*    */         } 
/*    */       } 
/*    */     } 
/* 58 */     calculateCardDamage(null);
/* 59 */     addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 64 */     if (!this.upgraded) {
/* 65 */       upgradeName();
/* 66 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 72 */     return (AbstractCard)new EnReaper();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnReaper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */