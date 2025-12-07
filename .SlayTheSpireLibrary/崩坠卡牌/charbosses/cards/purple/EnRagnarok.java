/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnRagnarok extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Ragnarok";
/*    */   
/*    */   public EnRagnarok() {
/* 23 */     super("downfall_Charboss:Ragnarok", cardStrings.NAME, "purple/attack/ragnarok", 3, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY, AbstractMonster.Intent.ATTACK);
/* 24 */     this.baseDamage = 5;
/* 25 */     this.baseMagicNumber = 5;
/* 26 */     this.magicNumber = this.baseMagicNumber;
/*    */     
/* 28 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 33 */     return autoPriority() * 5;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     for (int i = 0; i < this.magicNumber; i++) {
/* 38 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(p.hb.cX, p.hb.cY)));
/* 39 */       addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05F));
/* 40 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeDamage(1);
/* 50 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnRagnarok();
/*    */   }
/*    */ 
/*    */   
/* 60 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ragnarok");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnRagnarok.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */