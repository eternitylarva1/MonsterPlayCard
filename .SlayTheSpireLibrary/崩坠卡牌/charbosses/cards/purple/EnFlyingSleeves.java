/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnFlyingSleeves
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:FlyingSleeves";
/*    */   
/*    */   public EnFlyingSleeves() {
/* 27 */     super("downfall_Charboss:FlyingSleeves", cardStrings.NAME, "purple/attack/flying_sleeves", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 28 */     this.baseDamage = 4;
/* 29 */     this.selfRetain = true;
/*    */     
/* 31 */     this.baseMagicNumber = 2;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/* 33 */     this.isMultiDamage = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     if (m != null) {
/* 38 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIFF_2", 0.3F));
/* 39 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_FAST", 0.2F));
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new AnimatedSlashEffect(p.hb.cX, p.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
/*    */     } 
/*    */     
/* 43 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 44 */     if (m != null) {
/* 45 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIFF_1", 0.2F));
/* 46 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_FAST", 0.2F));
/* 47 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new AnimatedSlashEffect(p.hb.cX, p.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
/*    */     } 
/*    */     
/* 50 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new EnFlyingSleeves();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 58 */     if (!this.upgraded) {
/* 59 */       upgradeName();
/* 60 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 67 */     return autoPriority() * 2;
/*    */   }
/*    */ 
/*    */   
/* 71 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FlyingSleeves");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnFlyingSleeves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */