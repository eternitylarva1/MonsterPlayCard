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
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ public class OnDeathEveryoneStr extends AbstractPower {
/* 16 */   public static final String POWER_ID = downfallMod.makeID("OnDeathEveryoneStr");
/* 17 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 18 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 20 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
/* 21 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));
/*    */ 
/*    */   
/*    */   public OnDeathEveryoneStr(AbstractCreature owner, int amount) {
/* 25 */     this.ID = POWER_ID;
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 30 */     loadRegion("curiosity");
/*    */     
/* 32 */     this.name = NAME;
/*    */     
/* 34 */     updateDescription();
/*    */     
/* 36 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 41 */     flash();
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
/* 43 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 44 */       if (!m.isDying && !m.isDead) {
/* 45 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, this.owner, (AbstractPower)new StrengthPower((AbstractCreature)m, this.amount), this.amount));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 52 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\gauntletpowers\OnDeathEveryoneStr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */