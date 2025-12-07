/*    */ package charbosses.cards.hermit;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import hermit.actions.ReduceCostActionFixed;
/*    */ import hermit.characters.hermit;
/*    */ import hermit.powers.MaintenanceStrikePower;
/*    */ 
/*    */ public class EnMaintenance extends AbstractHermitBossCard {
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("downfall:BossMaintenance");
/*    */   public static final String ID = "downfall_Charboss:Maintenance";
/*    */   private int dexterity;
/*    */   
/*    */   public EnMaintenance() {
/* 23 */     super("downfall_Charboss:Maintenance", cardStrings.NAME, "hermitResources/images/cards/maintenance.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 24 */     this.baseMagicNumber = this.magicNumber = 3;
/* 25 */     this.dexterity = 1;
/* 26 */     this.baseBlock = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MaintenanceStrikePower((AbstractCreature)m, this.magicNumber)));
/* 32 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new DexterityPower((AbstractCreature)m, this.dexterity)));
/* 33 */     if (!(AbstractCharBoss.boss.chosenArchetype instanceof charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge)) {
/* 34 */       addToBot((AbstractGameAction)new ReduceCostActionFixed(this.uuid, 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(1);
/* 42 */       upgradeBlock(1);
/* 43 */       this.dexterity++;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyPowersToBlock() {
/* 49 */     this.block = this.dexterity;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new EnMaintenance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnMaintenance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */