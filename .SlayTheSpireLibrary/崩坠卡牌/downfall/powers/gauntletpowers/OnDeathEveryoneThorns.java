/*    */ package downfall.powers.gauntletpowers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ThornsPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class OnDeathEveryoneThorns
/*    */   extends AbstractPower {
/* 17 */   public static final String POWER_ID = downfallMod.makeID("OnDeathEveryoneThorns");
/* 18 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 19 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 21 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
/* 22 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));
/*    */ 
/*    */   
/*    */   public OnDeathEveryoneThorns(AbstractCreature owner, int amount) {
/* 26 */     this.ID = POWER_ID;
/* 27 */     this.owner = owner;
/* 28 */     this.amount = amount;
/* 29 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 31 */     loadRegion("curiosity");
/*    */     
/* 33 */     this.name = NAME;
/*    */     
/* 35 */     updateDescription();
/*    */     
/* 37 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 42 */     flash();
/* 43 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new ThornsPower((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
/* 44 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 45 */       if (!m.isDying && !m.isDead) {
/* 46 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, this.owner, (AbstractPower)new ThornsPower((AbstractCreature)m, this.amount), this.amount));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\gauntletpowers\OnDeathEveryoneThorns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */