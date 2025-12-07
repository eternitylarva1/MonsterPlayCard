/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import hermit.cards.Strike_Hermit;
/*    */ import hermit.characters.hermit;
/*    */ import hermit.patches.EnumPatch;
/*    */ 
/*    */ 
/*    */ public class EnStrikeHermit
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Strike_Hermit";
/*    */   
/*    */   public EnStrikeHermit() {
/* 21 */     super("downfall_Charboss:Strike_Hermit", Strike_Hermit.NAME, "hermitResources/images/cards/strike.png", 1, Strike_Hermit.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 22 */     this.baseDamage = 6;
/* 23 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/* 24 */     this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), EnumPatch.HERMIT_GUN3));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return (AbstractCard)new EnStrikeHermit();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnStrikeHermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */