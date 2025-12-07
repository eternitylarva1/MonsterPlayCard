/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyDevotionPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.DevotionEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnDevotion
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Devotion";
/*    */   
/*    */   public EnDevotion() {
/* 27 */     super("downfall_Charboss:Devotion", cardStrings.NAME, "purple/power/devotion", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 28 */     this.baseMagicNumber = 2;
/* 29 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new SFXAction("HEAL_2", -0.4F, true));
/* 34 */     float doop = 0.8F;
/* 35 */     if (Settings.FAST_MODE) {
/* 36 */       doop = 0.0F;
/*    */     }
/*    */     
/* 39 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DevotionEffect(), doop));
/* 40 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyDevotionPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new EnDevotion();
/*    */   }
/*    */   
/*    */   public void initializeDescription() {
/* 48 */     super.initializeDescription();
/* 49 */     this.keywords.add(GameDictionary.ENLIGHTENMENT.NAMES[0].toLowerCase());
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 62 */     return 100;
/*    */   }
/*    */ 
/*    */   
/* 66 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Devotion");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnDevotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */