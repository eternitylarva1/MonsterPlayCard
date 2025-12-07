/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ArtOfWar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_ArtOfWar
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Art of War";
/*    */   private boolean gainEnergyNext = false;
/*    */   private boolean firstTurn = false;
/*    */   
/*    */   public CBR_ArtOfWar() {
/* 25 */     super((AbstractRelic)new ArtOfWar());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 29 */     return (this.owner != null) ? setDescription(this.owner.chosenClass) : setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 33 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 37 */     this.description = setDescription(c);
/* 38 */     this.tips.clear();
/* 39 */     this.tips.add(new PowerTip(this.name, this.description));
/* 40 */     initializeTips();
/*    */   }
/*    */   
/*    */   public void atPreBattle() {
/* 44 */     flash();
/* 45 */     this.firstTurn = true;
/* 46 */     this.gainEnergyNext = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 52 */     if (this.gainEnergyNext && !this.firstTurn) {
/* 53 */       flash();
/* 54 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 55 */       addToBot((AbstractGameAction)new EnemyGainEnergyAction(1));
/*    */     } 
/*    */     
/* 58 */     this.firstTurn = false;
/* 59 */     this.gainEnergyNext = true;
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 63 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 64 */       this.gainEnergyNext = false;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 70 */     return new CBR_ArtOfWar();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ArtOfWar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */