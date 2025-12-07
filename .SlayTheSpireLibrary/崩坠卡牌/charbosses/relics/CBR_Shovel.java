/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Shovel;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Shovel extends AbstractCharbossRelic {
/*    */   public static final String ID = "Shovel";
/*    */   
/*    */   public CBR_Shovel() {
/* 15 */     super((AbstractRelic)new Shovel());
/*    */   }
/*    */   private int numRelics;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Shovel"))).DESCRIPTIONS[0] + this.numRelics + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Shovel"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     this.owner.damage(new DamageInfo((AbstractCreature)this.owner, MathUtils.floor(this.owner.maxHealth * 0.15F), DamageInfo.DamageType.HP_LOSS));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 31 */     return new CBR_Shovel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Shovel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */