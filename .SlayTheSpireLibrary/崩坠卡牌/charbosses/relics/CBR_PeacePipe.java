/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.PeacePipe;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_PeacePipe extends AbstractCharbossRelic {
/*    */   public static final String ID = "PeacePipe";
/*    */   
/*    */   public CBR_PeacePipe() {
/* 15 */     super((AbstractRelic)new PeacePipe());
/*    */   }
/*    */   private int numCards;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("PeacePipe"))).DESCRIPTIONS[0] + this.numCards + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("PeacePipe"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     this.owner.damage(new DamageInfo((AbstractCreature)this.owner, MathUtils.floor(this.owner.maxHealth * 0.1F), DamageInfo.DamageType.HP_LOSS));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 31 */     return new CBR_PeacePipe();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_PeacePipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */