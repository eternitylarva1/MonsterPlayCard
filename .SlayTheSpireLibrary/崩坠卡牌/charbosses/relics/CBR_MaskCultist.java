/*    */ package charbosses.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CultistMask;
/*    */ 
/*    */ public class CBR_MaskCultist extends AbstractCharbossRelic {
/*    */   public CBR_MaskCultist() {
/* 12 */     super((AbstractRelic)new CultistMask());
/*    */   }
/*    */   public static final String ID = "CBRCultistMask";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 21 */     flash();
/* 22 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 23 */     addToBot((AbstractGameAction)new SFXAction("VO_CULTIST_1A"));
/* 24 */     addToBot((AbstractGameAction)new TalkAction(false, this.DESCRIPTIONS[1], 1.0F, 2.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_MaskCultist();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MaskCultist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */