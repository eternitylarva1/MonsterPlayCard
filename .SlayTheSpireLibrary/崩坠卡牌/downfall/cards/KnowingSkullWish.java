/*    */ package downfall.cards;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PotionHelper;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.actions.OctoChoiceAction;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.OctopusCard;
/*    */ import expansioncontent.cards.AbstractExpansionCard;
/*    */ import expansioncontent.expansionContentMod;
/*    */ import java.util.ArrayList;
/*    */ import sneckomod.actions.ChangeGoldAction;
/*    */ 
/*    */ public class KnowingSkullWish
/*    */   extends AbstractExpansionCard
/*    */   implements OctopusCard {
/* 25 */   public static final String ID = makeID("KnowingSkullWish");
/* 26 */   public static final String[] NAMES = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("OctoChoiceCards"))).NAMES;
/* 27 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("OctoChoiceCards"))).TEXT;
/*    */   
/*    */   public KnowingSkullWish() {
/* 30 */     super(ID, 0, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, AbstractCard.CardColor.COLORLESS);
/* 31 */     this.isEthereal = true;
/* 32 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     atb((AbstractGameAction)new OctoChoiceAction(m, this));
/*    */   }
/*    */   
/*    */   public ArrayList<OctoChoiceCard> choiceList() { OctoChoiceCard goldCard, colorlessCard, potionCard;
/* 40 */     ArrayList<OctoChoiceCard> cardList = new ArrayList<>();
/*    */     
/* 42 */     if (!this.upgraded) {
/* 43 */       goldCard = new OctoChoiceCard("ks:0", NAMES[0], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[0]);
/* 44 */       colorlessCard = new OctoChoiceCard("ks:1", NAMES[1], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[1]);
/* 45 */       potionCard = new OctoChoiceCard("ks:2", NAMES[2], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[2]);
/*    */     } else {
/*    */       
/* 48 */       goldCard = new OctoChoiceCard("ks:0", NAMES[0], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[24]);
/* 49 */       colorlessCard = new OctoChoiceCard("ks:1", NAMES[1], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[25]);
/* 50 */       potionCard = new OctoChoiceCard("ks:2", NAMES[2], expansionContentMod.makeCardPath("KnowingSkullWish.png"), TEXT[26]);
/* 51 */       goldCard.upgrade();
/* 52 */       colorlessCard.upgrade();
/* 53 */       potionCard.upgrade();
/*    */     } 
/* 55 */     cardList.add(goldCard);
/* 56 */     cardList.add(colorlessCard);
/* 57 */     cardList.add(potionCard);
/* 58 */     return cardList; } public void doChoiceStuff(AbstractMonster m, OctoChoiceCard card) { int gold;
/*    */     ArrayList<AbstractCard> list;
/*    */     int damage;
/*    */     AbstractCard q;
/* 62 */     switch (card.cardID) {
/*    */       case "ks:0":
/* 64 */         gold = this.upgraded ? 20 : 15;
/* 65 */         atb((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 3));
/* 66 */         atb((AbstractGameAction)new ChangeGoldAction(gold));
/*    */         break;
/*    */       
/*    */       case "ks:1":
/* 70 */         atb((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 3));
/* 71 */         list = new ArrayList<>(AbstractDungeon.colorlessCardPool.group);
/* 72 */         q = list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
/* 73 */         if (this.upgraded)
/* 74 */           q.upgrade(); 
/* 75 */         atb((AbstractGameAction)new MakeTempCardInHandAction(q));
/*    */         break;
/*    */       
/*    */       case "ks:2":
/* 79 */         damage = this.upgraded ? 5 : 7;
/* 80 */         atb((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, damage));
/* 81 */         atb((AbstractGameAction)new ObtainPotionAction(PotionHelper.getRandomPotion()));
/*    */         break;
/*    */     }  }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 88 */     if (!this.upgraded)
/* 89 */       upgradeName(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\KnowingSkullWish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */