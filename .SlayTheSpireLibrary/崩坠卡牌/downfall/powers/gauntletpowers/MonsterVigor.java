/*    */ package downfall.powers.gauntletpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonsterVigor
/*    */   extends AbstractPower
/*    */ {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("MonsterVigor");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */ 
/*    */   
/*    */   public MonsterVigor(AbstractCreature owner, int amount) {
/* 23 */     this.ID = POWER_ID;
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 28 */     loadRegion("vigor");
/*    */     
/* 30 */     this.name = NAME;
/*    */     
/* 32 */     updateDescription();
/*    */     
/* 34 */     this.canGoNegative = false;
/*    */   }
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 38 */     return (type == DamageInfo.DamageType.NORMAL) ? (damage + this.amount) : damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 43 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\gauntletpowers\MonsterVigor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */