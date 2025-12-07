/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.FaceOfCleric;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_MaskCleric extends AbstractCharbossRelic {
/*    */   public static final String ID = "CBRFaceOfCleric";
/* 11 */   private int HP = 0;
/*    */   
/*    */   public CBR_MaskCleric() {
/* 14 */     super((AbstractRelic)new FaceOfCleric());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("CBRFaceOfCleric"))).DESCRIPTIONS[0] + this.HP + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("CBRFaceOfCleric"))).DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onEquip() {
/* 23 */     int random = AbstractDungeon.cardRng.random(6, 8);
/* 24 */     this.HP = random * AbstractDungeon.actNum;
/* 25 */     this.owner.increaseMaxHp(this.HP, true);
/*    */     
/* 27 */     this.description = getUpdatedDescription();
/* 28 */     refreshDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_MaskCleric();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MaskCleric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */