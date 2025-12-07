/*    */ package downfall.cards;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import expansioncontent.cards.AbstractExpansionCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import guardian.vfx.SmallLaserEffectColored;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MajorBeam
/*    */   extends AbstractExpansionCard
/*    */ {
/*    */   private static final int DAMAGE = 5;
/* 32 */   public static final String ID = makeID("MajorBeam");
/* 33 */   public static final String IMG_PATH = expansionContentMod.makeCardPath("MajorBeam.png");
/*    */ 
/*    */   
/*    */   public MajorBeam() {
/* 37 */     super(ID, 0, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractCard.CardColor.COLORLESS);
/* 38 */     this.baseDamage = 5;
/* 39 */     expansionContentMod.loadJokeCardImage((AbstractCard)this, "MajorBeam.png");
/* 40 */     this.baseMagicNumber = this.magicNumber = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 45 */     if (m != null) {
/* 46 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 47 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffectColored(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, Color.WHITE), 0.2F));
/*    */     } 
/* 49 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 50 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeDamage(2);
/* 58 */       upgradeMagicNumber(1);
/* 59 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 60 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 65 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\MajorBeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */