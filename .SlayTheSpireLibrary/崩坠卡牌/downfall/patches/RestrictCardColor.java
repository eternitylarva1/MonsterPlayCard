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
/*    */ 
/*    */ @SpirePatch(clz = CardLibrary.class, method = "getAnyColorCard", paramtypez = {AbstractCard.CardRarity.class})
/*    */ public class RestrictCardColor
/*    */ {
/*    */   @SpireInsertPatch(rloc = 10, localvars = {"anyCard"})
/*    */   public static void Insert(AbstractCard.CardRarity rarity, CardGroup anyCard) {
/* 24 */     CardGroup cardsToRemove = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */     
/* 26 */     for (AbstractCard card : anyCard.group) {
/* 27 */       boolean available = false;
/* 28 */       if (card.color == AbstractDungeon.player.getCardColor()) {
/* 29 */         available = true;
/*    */       }
/* 31 */       else if (!card.hasTag(SneckoMod.BANNEDFORSNECKO) && !card.hasTag(HexaMod.GHOSTWHEELCARD)) {
/* 32 */         available = true;
/*    */       } 
/*    */ 
/*    */       
/* 36 */       if (card.color == CollectibleCardColorEnumPatch.CardColorPatch.COLLECTIBLE) {
/* 37 */         available = false;
/*    */       }
/*    */       
/* 40 */       if (!available) {
/* 41 */         cardsToRemove.addToBottom(card);
/*    */       }
/*    */     } 
/* 44 */     for (AbstractCard card : cardsToRemove.group)
/* 45 */       anyCard.removeCard(card); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RestrictCardColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */