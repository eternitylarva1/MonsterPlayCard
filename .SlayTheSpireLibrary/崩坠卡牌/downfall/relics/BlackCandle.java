/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlackCandle
/*    */   extends CustomRelic
/*    */ {
/* 19 */   public static final String ID = downfallMod.makeID("BlackCandle");
/* 20 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/BlackCandle.png"));
/* 21 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/BlackCandleOutline.png"));
/*    */   
/*    */   public BlackCandle() {
/* 24 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 29 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 33 */     if (card.type == AbstractCard.CardType.CURSE && 
/* 34 */       card.cost == -2) {
/* 35 */       flash();
/* 36 */       addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 1, AbstractGameAction.AttackEffect.FIRE));
/* 37 */       card.exhaust = true;
/* 38 */       action.exhaustCard = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlay(AbstractCard card) {
/* 44 */     if (card.type == AbstractCard.CardType.CURSE) {
/* 45 */       return true;
/*    */     }
/* 47 */     return card.canPlay(card);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 57 */     return (AbstractRelic)new BlackCandle();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\BlackCandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */