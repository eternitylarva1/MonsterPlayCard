/*    */ package downfall.patches;
/*    */ 
/*    */ import collector.patches.CollectiblesPatches.CollectibleCardColorEnumPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import sneckomod.SneckoMod;
/*    */ import theHexaghost.HexaMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardLibrary.class, method = "getAnyColorCard", paramtypez = {AbstractCard.CardType.class, AbstractCard.CardRarity.class})
/*    */ public class RestrictCardColorWithType
/*    */ {
/*    */   @SpireInsertPatch(rloc = 11, localvars = {"anyCard"})
/*    */   public static void Insert(AbstractCard.CardType type, AbstractCard.CardRarity rarity, CardGroup anyCard) {
/* 23 */     CardGroup cardsToRemove = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 24 */     for (AbstractCard card : anyCard.group) {
/* 25 */       boolean available = false;
/* 26 */       if (card.color == AbstractDungeon.player.getCardColor()) {
/* 27 */         available = true;
/*    */       }
/* 29 */       else if (!card.hasTag(SneckoMod.BANNEDFORSNECKO) && !card.hasTag(HexaMod.GHOSTWHEELCARD)) {
/* 30 */         available = true;
/*    */       } 
/*    */       
/* 33 */       if (card.color == CollectibleCardColorEnumPatch.CardColorPatch.COLLECTIBLE) {
/* 34 */         available = false;
/*    */       }
/*    */       
/* 37 */       if (!available) {
/* 38 */         cardsToRemove.addToBottom(card);
/*    */       }
/*    */     } 
/* 41 */     for (AbstractCard card : cardsToRemove.group)
/* 42 */       anyCard.removeCard(card); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RestrictCardColorWithType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */