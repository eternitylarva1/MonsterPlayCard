/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FlameBarrierPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class EnemyFearNoEvilPower
/*    */   extends AbstractPower
/*    */ {
/* 21 */   public static final Logger logger = LogManager.getLogger(FlameBarrierPower.class.getName());
/*    */   public static final String POWER_ID = "Fear No Evil";
/*    */   private static final PowerStrings powerStrings;
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/*    */   public boolean isActive;
/*    */   public int theoreticalGain;
/* 28 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/WatcherFearNoEvil84.png"));
/* 29 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/WatcherFearNoEvil32.png"));
/*    */   
/*    */   public EnemyFearNoEvilPower(AbstractCreature owner) {
/* 32 */     this.name = NAME;
/* 33 */     this.ID = "Fear No Evil";
/* 34 */     this.owner = owner;
/* 35 */     this.isActive = false;
/* 36 */     updateDescription();
/*    */ 
/*    */     
/* 39 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 40 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 46 */     if (card instanceof charbosses.cards.AbstractBossCard) {
/*    */       return;
/*    */     }
/* 49 */     System.out.println("Fear no evil is active: " + this.isActive + "; " + this.description);
/* 50 */     if (card.type.equals(AbstractCard.CardType.ATTACK) && !this.isActive) {
/* 51 */       flash();
/* 52 */       this.isActive = true;
/* 53 */       updateDescription();
/*    */     } 
/* 55 */     System.out.println("Fear no evil desc: " + this.description);
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 59 */     if (!this.isActive) {
/* 60 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/*    */       
/* 63 */       this.description = DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */   
/*    */   static {
/* 68 */     powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:FearNoEvil");
/* 69 */     NAME = powerStrings.NAME;
/* 70 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyFearNoEvilPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */