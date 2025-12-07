/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class GremlinSack
/*    */   extends CustomRelic
/*    */ {
/* 18 */   public static final String ID = downfallMod.makeID("GremlinSack");
/* 19 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/GremlinSack.png"));
/* 20 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/GremlinSack.png"));
/*    */   
/*    */   public ArrayList<AbstractCard> sackCards;
/*    */   
/*    */   public GremlinSack() {
/* 25 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 30 */     this.sackCards = new ArrayList<>();
/* 31 */     if (AbstractDungeon.isPlayerInDungeon() && 
/* 32 */       AbstractDungeon.player != null) {
/* 33 */       if (AbstractDungeon.player.getStartCardForEvent() != null) {
/* 34 */         this.sackCards.add(AbstractDungeon.player.getStartCardForEvent().makeStatEquivalentCopy());
/*    */       }
/*    */ 
/*    */       
/* 38 */       this.sackCards.add(AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON).makeCopy());
/* 39 */       this.sackCards.add(AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/* 40 */       this.sackCards.add(AbstractDungeon.getCard(AbstractCard.CardRarity.RARE).makeCopy());
/* 41 */       this.sackCards.add(AbstractDungeon.returnColorlessCard(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/* 42 */       this.sackCards.add(AbstractDungeon.returnRandomCurse());
/*    */       
/* 44 */       this.description = getUpdatedDescription();
/* 45 */       this.tips.clear();
/* 46 */       this.tips.add(new PowerTip(this.name, this.description));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 54 */     super.update();
/* 55 */     if (this.sackCards == null) onTrigger();
/*    */   
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 60 */     if (AbstractDungeon.player != null) {
/*    */       
/* 62 */       if (this.sackCards != null) {
/*    */         
/* 64 */         if (this.sackCards.size() >= 5) {
/*    */           
/* 66 */           String fullDesc = this.DESCRIPTIONS[0];
/* 67 */           fullDesc = fullDesc + this.DESCRIPTIONS[2] + ((AbstractCard)this.sackCards.get(0)).name + " NL ";
/* 68 */           fullDesc = fullDesc + this.DESCRIPTIONS[3] + ((AbstractCard)this.sackCards.get(1)).name + " NL ";
/* 69 */           fullDesc = fullDesc + this.DESCRIPTIONS[4] + ((AbstractCard)this.sackCards.get(2)).name + " NL ";
/* 70 */           fullDesc = fullDesc + this.DESCRIPTIONS[5] + ((AbstractCard)this.sackCards.get(3)).name + " NL ";
/* 71 */           fullDesc = fullDesc + this.DESCRIPTIONS[6] + ((AbstractCard)this.sackCards.get(4)).name + " NL ";
/* 72 */           fullDesc = fullDesc + this.DESCRIPTIONS[7] + ((AbstractCard)this.sackCards.get(5)).name;
/* 73 */           return fullDesc;
/*    */         } 
/*    */       } else {
/* 76 */         return this.DESCRIPTIONS[1];
/*    */       } 
/*    */     } else {
/* 79 */       return this.DESCRIPTIONS[1];
/*    */     } 
/* 81 */     return this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 86 */     if (this.sackCards == null) onTrigger(); 
/* 87 */     for (AbstractCard c : this.sackCards)
/* 88 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F), MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\GremlinSack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */