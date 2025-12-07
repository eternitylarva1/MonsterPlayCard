/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import collector.CollectorMod;
/*    */ import collector.cards.AbstractCollectorCard;
/*    */ import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ public class Sapped
/*    */   extends AbstractCollectorCard
/*    */ {
/* 16 */   public static final String ID = CollectorMod.makeID(Sapped.class.getSimpleName());
/*    */   
/*    */   public Sapped() {
/* 19 */     super(ID, 0, AbstractCard.CardType.CURSE, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractCard.CardColor.CURSE);
/* 20 */     this.baseMagicNumber = this.magicNumber = 1;
/* 21 */     isPyre();
/* 22 */     this.exhaust = true;
/* 23 */     SoulboundField.soulbound.set(this, Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 30 */     addToBot((AbstractGameAction)new LoseEnergyAction(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUpgrade() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public void upp() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\Sapped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */