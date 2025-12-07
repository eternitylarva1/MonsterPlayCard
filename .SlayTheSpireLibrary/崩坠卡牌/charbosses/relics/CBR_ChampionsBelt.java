/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ChampionsBelt;
/*    */ 
/*    */ public class CBR_ChampionsBelt extends AbstractCharbossRelic {
/*    */   public static final String ID = "Champion Belt";
/*    */   
/*    */   public CBR_ChampionsBelt() {
/* 18 */     super((AbstractRelic)new ChampionsBelt());
/*    */   }
/*    */   public static final int EFFECT = 1;
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onTrigger() {
/* 26 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_ChampionsBelt();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ChampionsBelt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */