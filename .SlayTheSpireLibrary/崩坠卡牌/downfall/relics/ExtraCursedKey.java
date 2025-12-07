/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.curses.Injury;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class ExtraCursedKey
/*    */   extends CustomRelic
/*    */ {
/* 21 */   public static final String ID = downfallMod.makeID("ExtraCursedKey");
/* 22 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/ExtraCursedKey.png"));
/* 23 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/ExtraCursedKey.png"));
/*    */   private boolean triggeredThisTurn = false;
/*    */   
/*    */   public ExtraCursedKey() {
/* 27 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 32 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 36 */     if (room instanceof com.megacrit.cardcrawl.rooms.TreasureRoom) {
/* 37 */       flash();
/* 38 */       this.pulse = true;
/*    */     } else {
/* 40 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChestOpen(boolean bossChest) {
/* 46 */     if (!bossChest) {
/* 47 */       for (int i = 0; i < 1; i++) {
/* 48 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Injury(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 49 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void atTurnStart() {
/* 55 */     this.triggeredThisTurn = false;
/*    */   }
/*    */   
/*    */   public void onCardDraw(AbstractCard card) {
/* 59 */     if (card.color == AbstractCard.CardColor.CURSE && 
/* 60 */       !this.triggeredThisTurn) {
/* 61 */       this.triggeredThisTurn = true;
/* 62 */       flash();
/* 63 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 64 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\ExtraCursedKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */