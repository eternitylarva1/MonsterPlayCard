/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Defect.CharBossDefect;
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
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnClaw
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Gash";
/* 26 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Gash");
/*    */ 
/*    */   
/*    */   public EnClaw() {
/* 30 */     super("downfall_Charboss:Gash", cardStrings.NAME, "blue/attack/claw", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 31 */     this.baseMagicNumber = 2;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/* 33 */     this.baseDamage = 3;
/*    */   }
/*    */   
/*    */   public EnClaw(int bonusDamage) {
/* 37 */     this();
/* 38 */     this.baseDamage += bonusDamage;
/* 39 */     this.damage = this.baseDamage;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 43 */     if (m != null) {
/* 44 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(p.hb.cX, p.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
/*    */     }
/*    */     
/* 47 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
/* 48 */     CharBossDefect cB = (CharBossDefect)AbstractCharBoss.boss;
/* 49 */     cB.clawsPlayed++;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 54 */     return super.getPriority(hand) + 5;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 58 */     if (!this.upgraded) {
/* 59 */       upgradeName();
/* 60 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 65 */     return (AbstractCard)new EnClaw();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnClaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */