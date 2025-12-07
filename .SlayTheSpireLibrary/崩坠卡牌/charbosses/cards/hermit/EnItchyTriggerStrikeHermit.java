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
/*    */ public class EnItchyTriggerStrikeHermit extends AbstractHermitBossCard {
/*    */   public EnItchyTriggerStrikeHermit() {
/* 16 */     super("downfall_Charboss:Strike_Hermit", Strike_Hermit.NAME, "hermitResources/images/cards/strike.png", 1, Strike_Hermit.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 17 */     this.baseDamage = 6;
/* 18 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/* 19 */     this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
/* 20 */     setCostForTurn(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 25 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), EnumPatch.HERMIT_GUN3));
/*    */   }
/*    */   public static final String ID = "downfall_Charboss:Strike_Hermit";
/*    */   
/*    */   public void upgrade() {
/* 30 */     if (!this.upgraded) {
/* 31 */       upgradeName();
/* 32 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 38 */     setCostForTurn(1);
/* 39 */     bossDarken();
/* 40 */     this.isCostModified = false;
/* 41 */     this.isCostModifiedForTurn = false;
/* 42 */     destroyIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new EnItchyTriggerStrikeHermit();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnItchyTriggerStrikeHermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */