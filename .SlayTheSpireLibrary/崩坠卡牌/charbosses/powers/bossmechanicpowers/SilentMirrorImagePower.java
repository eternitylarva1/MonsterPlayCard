/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class SilentMirrorImagePower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:SilentMirrorImagePower";
/*    */   
/*    */   public SilentMirrorImagePower(AbstractCreature owner) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "downfall:SilentMirrorImagePower";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = 0;
/* 19 */     updateDescription();
/* 20 */     loadRegion("curiosity");
/* 21 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/* 29 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:SilentMirrorImagePower");
/* 30 */   public static final String NAME = powerStrings.NAME;
/* 31 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\SilentMirrorImagePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */