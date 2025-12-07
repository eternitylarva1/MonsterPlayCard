/*    */ package downfall.relics;
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import basemod.helpers.CardPowerTip;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TestRelic extends CustomRelic implements ClickableRelic {
/* 19 */   public static final String ID = downfallMod.makeID("TestRelic");
/* 20 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/WingStatue.png"));
/* 21 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/WingStatue.png"));
/*    */   
/*    */   public TestRelic() {
/* 24 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/* 25 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   private ArrayList<AbstractCard> allCardsSortedAlphabet() {
/* 29 */     ArrayList<AbstractCard> newCardsList = new ArrayList<>(CardLibrary.getAllCards());
/* 30 */     newCardsList.removeIf(c -> (c.rarity == AbstractCard.CardRarity.SPECIAL || c.color != AbstractDungeon.player.getCardColor()));
/* 31 */     newCardsList.sort((o1, o2) -> o1.name.compareToIgnoreCase(o2.name));
/* 32 */     return newCardsList;
/*    */   }
/*    */   
/*    */   private void setNextCard() {
/* 36 */     if (this.counter < allCardsSortedAlphabet().size() - 1) {
/* 37 */       this.counter++;
/* 38 */       this.tips.clear();
/* 39 */       this.tips.add(new PowerTip(this.name, this.description));
/* 40 */       this.tips.add(new CardPowerTip(allCardsSortedAlphabet().get(this.counter)));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 46 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRightClick() {
/* 51 */     if (((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(0)).maxHealth < 1000) {
/* 52 */       ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(0)).increaseMaxHp(12345, true);
/*    */     }
/* 54 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 55 */       addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
/*    */     }
/* 57 */     flash();
/* 58 */     AbstractDungeon.player.gainEnergy(((AbstractCard)allCardsSortedAlphabet().get(this.counter)).cost + 2);
/* 59 */     addToTop((AbstractGameAction)new MakeTempCardInHandAction(allCardsSortedAlphabet().get(this.counter)));
/* 60 */     setNextCard();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\TestRelic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */