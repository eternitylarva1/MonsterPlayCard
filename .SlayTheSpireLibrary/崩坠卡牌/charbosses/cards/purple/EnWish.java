/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnWish
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Wish";
/*    */   
/*    */   public EnWish() {
/* 27 */     super("downfall_Charboss:Wish", cardStrings.NAME, "purple/skill/wish", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 28 */     this.baseMagicNumber = 3;
/* 29 */     this.magicNumber = this.baseMagicNumber;
/* 30 */     this.strengthGeneratedIfPlayed = 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     AbstractCharBoss n = AbstractCharBoss.boss;
/* 36 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderLongFlashEffect(Color.FIREBRICK, true)));
/* 37 */     addToBot((AbstractGameAction)new VFXAction((AbstractCreature)n, (AbstractGameEffect)new InflameEffect((AbstractCreature)n), 1.0F));
/* 38 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)n, (AbstractCreature)n, (AbstractPower)new StrengthPower((AbstractCreature)n, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       upgradeMagicNumber(1);
/* 45 */       this.strengthGeneratedIfPlayed++;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 52 */     return autoPriority() * 2;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnWish();
/*    */   }
/*    */ 
/*    */   
/* 60 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BecomeAlmighty");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnWish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */