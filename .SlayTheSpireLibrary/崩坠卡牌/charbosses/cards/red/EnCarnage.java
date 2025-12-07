/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.StarBounceEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
/*    */ 
/*    */ public class EnCarnage
/*    */   extends AbstractBossCard {
/*    */   public EnCarnage() {
/* 23 */     super("downfall_Charboss:Carnage", cardStrings.NAME, "red/attack/carnage", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 24 */     this.baseDamage = 20;
/* 25 */     this.isEthereal = true;
/*    */   }
/*    */   public static final String ID = "downfall_Charboss:Carnage";
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     if (Settings.FAST_MODE) {
/* 31 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.RED.cpy())));
/*    */       
/* 33 */       for (int i = 0; i < 5; i++) {
/* 34 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(p.hb.cX, p.hb.cY)));
/*    */       }
/*    */     } else {
/* 37 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.RED.cpy()), 0.4F));
/*    */       
/* 39 */       for (int i = 0; i < 5; i++) {
/* 40 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(p.hb.cX, m.hb.cY)));
/*    */       }
/*    */     } 
/*    */     
/* 44 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeDamage(8);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnCarnage();
/*    */   }
/*    */ 
/*    */   
/* 60 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Carnage");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnCarnage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */