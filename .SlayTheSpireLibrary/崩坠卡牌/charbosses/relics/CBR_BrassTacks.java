/*    */ package charbosses.relics;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.MetallicizePower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import hermit.relics.BrassTacks;
/*    */ 
/*    */ public class CBR_BrassTacks extends AbstractCharbossRelic {
/*    */   public CBR_BrassTacks() {
/* 14 */     super((AbstractRelic)new BrassTacks());
/* 15 */     setTextureOutline(BrassTacks.IMG, BrassTacks.OUTLINE);
/*    */   }
/*    */   public static final String ID = "BrassTacks";
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new MetallicizePower((AbstractCreature)this.owner, 2)));
/* 26 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTexture(Texture t) {
/* 31 */     this.img = t;
/* 32 */     this.outlineImg = t;
/*    */   }
/*    */   
/*    */   public void setTextureOutline(Texture t, Texture o) {
/* 36 */     this.img = t;
/* 37 */     this.outlineImg = o;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new CBR_BrassTacks();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BrassTacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */