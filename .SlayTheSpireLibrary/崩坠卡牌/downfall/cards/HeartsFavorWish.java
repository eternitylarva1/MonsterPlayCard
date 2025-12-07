/*    */ package downfall.cards;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.blue.EchoForm;
/*    */ import com.megacrit.cardcrawl.cards.green.WraithForm;
/*    */ import com.megacrit.cardcrawl.cards.purple.DevaForm;
/*    */ import com.megacrit.cardcrawl.cards.red.DemonForm;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.actions.FlexibleDiscoveryAction;
/*    */ import downfall.cards.curses.Aged;
/*    */ import downfall.cards.curses.Bewildered;
/*    */ import downfall.cards.curses.Icky;
/*    */ import downfall.cards.curses.Malfunctioning;
/*    */ import downfall.cards.curses.Scatterbrained;
/*    */ import downfall.downfallMod;
/*    */ import expansioncontent.cards.AbstractDownfallCard;
/*    */ import expansioncontent.cards.AbstractExpansionCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import hermit.cards.EternalForm;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class HeartsFavorWish
/*    */   extends AbstractDownfallCard
/*    */ {
/* 36 */   public static final String ID = AbstractExpansionCard.makeID("HeartsFavorWish");
/*    */   private static final CardStrings cardStrings;
/* 38 */   public static final String IMG_PATH = expansionContentMod.makeCardPath("BloodySacrifice.png");
/*    */   public HeartsFavorWish() {
/* 40 */     super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
/* 41 */     this.isEthereal = true;
/* 42 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public boolean checkBossFaced(String id) {
/* 46 */     if (downfallMod.Act1BossFaced.equals(id)) return true; 
/* 47 */     if (downfallMod.Act2BossFaced.equals(id)) return true; 
/* 48 */     if (downfallMod.Act3BossFaced.equals(id)) return true; 
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 55 */     ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
/*    */     
/* 57 */     if (checkBossFaced("downfall:Ironclad")) stanceChoices.add(new DemonForm()); 
/* 58 */     if (checkBossFaced("downfall:Silent")) stanceChoices.add(new WraithForm()); 
/* 59 */     if (checkBossFaced("downfall:Defect")) stanceChoices.add(new EchoForm()); 
/* 60 */     if (checkBossFaced("downfall:Watcher")) stanceChoices.add(new DevaForm()); 
/* 61 */     if (checkBossFaced("downfall:Hermit")) stanceChoices.add(new EternalForm());
/*    */     
/* 63 */     if (stanceChoices.size() < 3) {
/*    */       
/* 65 */       stanceChoices.clear();
/* 66 */       stanceChoices.add(new WraithForm());
/* 67 */       stanceChoices.add(new EchoForm());
/* 68 */       stanceChoices.add(new EternalForm());
/*    */     } 
/*    */     
/* 71 */     addToBot((AbstractGameAction)new FlexibleDiscoveryAction(stanceChoices, true));
/*    */ 
/*    */     
/* 74 */     for (int i = 0; i < 3; i++) {
/* 75 */       int choice = AbstractDungeon.cardRandomRng.random(0, 4);
/* 76 */       switch (choice) { case 0:
/* 77 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Aged(), 1)); break;
/* 78 */         case 1: AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Bewildered(), 1)); break;
/* 79 */         case 2: AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Scatterbrained(), 1)); break;
/* 80 */         case 3: AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Icky(), 1)); break;
/* 81 */         case 4: AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Malfunctioning(), 1));
/*    */           break; }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 90 */     if (!this.upgraded)
/* 91 */       upgradeName(); 
/*    */   }
/*    */   
/*    */   static {
/* 95 */     cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\HeartsFavorWish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */