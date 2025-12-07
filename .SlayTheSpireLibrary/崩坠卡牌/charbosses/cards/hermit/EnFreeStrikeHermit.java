/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ public class EnFreeStrikeHermit extends AbstractHermitBossCard {
/*    */   public EnFreeStrikeHermit() {
/* 17 */     super("downfall_Charboss:Strike_Hermit", Strike_Hermit.NAME, "hermitResources/images/cards/strike.png", 1, Strike_Hermit.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 18 */     this.baseDamage = 6;
/* 19 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/* 20 */     this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
/* 21 */     setCostForTurn(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 26 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), EnumPatch.HERMIT_GUN3));
/*    */   }
/*    */   public static final String ID = "downfall_Charboss:Strike_Hermit";
/*    */   
/*    */   public void onSpecificTrigger() {
/* 31 */     setCostForTurn(1);
/* 32 */     this.isCostModified = false;
/* 33 */     this.isCostModifiedForTurn = false;
/* 34 */     if (AbstractCharBoss.boss.hand.group.indexOf(this) == 2) {
/* 35 */       bossDarken();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnFreeStrikeHermit();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnFreeStrikeHermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */