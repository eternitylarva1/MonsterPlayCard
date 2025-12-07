/*     */ package charbosses.bosses.Merchant;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.colorless.EnGoodInstincts;
/*     */ import charbosses.cards.colorless.EnPanacea;
/*     */ import charbosses.cards.colorless.EnPanicButton;
/*     */ import charbosses.cards.colorless.EnSwiftStrike;
/*     */ import charbosses.cards.colorless.EnTheBomb;
/*     */ import charbosses.cards.colorless.EnTrip;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3MerchantBoss extends ArchetypeBaseMerchant {
/*     */   public ArchetypeAct3MerchantBoss() {
/*  17 */     super("ME_ARCHETYPE_MERCHANT", "Merchant");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  25 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     addRelic((AbstractCharbossRelic)new CBR_SmilingMask());
/*     */ 
/*     */ 
/*     */     
/*  40 */     addRelic((AbstractCharbossRelic)new CBR_Toolbox());
/*     */     
/*  42 */     addRelic((AbstractCharbossRelic)new CBR_TungstenRod());
/*  43 */     addRelic((AbstractCharbossRelic)new CBR_IceCream());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  52 */     super.addedPreBattle();
/*  53 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  54 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new MerchantStrengthPower((AbstractCreature)abstractCharBoss)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  60 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  61 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  62 */     if (!this.looped) {
/*  63 */       switch (this.turn) {
/*     */         case 0:
/*  65 */           addToList(cardsList, (AbstractCard)new EnPanacea());
/*  66 */           addToList(cardsList, (AbstractCard)new EnPanicButton());
/*  67 */           addToList(cardsList, (AbstractCard)new EnDramaticEntrance());
/*  68 */           addToList(cardsList, (AbstractCard)new EnTheBomb(), extraUpgrades);
/*  69 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  72 */           addToList(cardsList, (AbstractCard)new EnApotheosis(), true);
/*  73 */           addToList(cardsList, (AbstractCard)new EnPanacea(), true);
/*  74 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts(), true);
/*  75 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  79 */           addToList(cardsList, (AbstractCard)new EnPanacea(), true);
/*  80 */           addToList(cardsList, (AbstractCard)new EnPanicButton(), true);
/*  81 */           addToList(cardsList, (AbstractCard)new EnSadisticNature(), true);
/*  82 */           this.turn++;
/*     */           break;
/*     */         case 3:
/*  85 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike(), true);
/*  86 */           addToList(cardsList, (AbstractCard)new EnTrip(), true);
/*  87 */           addToList(cardsList, (AbstractCard)new EnBlind(), true);
/*     */           
/*  89 */           this.turn++;
/*     */           break;
/*     */         case 4:
/*  92 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts(), true);
/*  93 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike(), true);
/*  94 */           addToList(cardsList, (AbstractCard)new EnHandOfGreed(), true);
/*  95 */           this.turn = 0;
/*  96 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 101 */       switch (this.turn) {
/*     */         
/*     */         case 0:
/* 104 */           addToList(cardsList, (AbstractCard)new EnTheBomb(), true);
/* 105 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike(), true);
/* 106 */           addToList(cardsList, (AbstractCard)new EnTrip(), true);
/* 107 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 110 */           addToList(cardsList, (AbstractCard)new EnHandOfGreed(), true);
/* 111 */           addToList(cardsList, (AbstractCard)new EnTheBomb(), true);
/* 112 */           addToList(cardsList, (AbstractCard)new EnBlind(), true);
/* 113 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/* 117 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts(), true);
/* 118 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike(), true);
/* 119 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts(), true);
/*     */           
/* 121 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 126 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 131 */     addRelic((AbstractCharbossRelic)new CBR_SelfFormingClay());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Merchant\ArchetypeAct3MerchantBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */