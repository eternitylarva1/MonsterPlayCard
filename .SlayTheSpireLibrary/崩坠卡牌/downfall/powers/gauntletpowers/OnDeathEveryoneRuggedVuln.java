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
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import hermit.powers.Rugged;
/*    */ 
/*    */ public class OnDeathEveryoneRuggedVuln
/*    */   extends AbstractPower {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("OnDeathEveryoneRuggedVuln");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));
/*    */ 
/*    */   
/*    */   public OnDeathEveryoneRuggedVuln(AbstractCreature owner, int amount) {
/* 27 */     this.ID = POWER_ID;
/* 28 */     this.owner = owner;
/* 29 */     this.amount = amount;
/* 30 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 32 */     loadRegion("curiosity");
/*    */     
/* 34 */     this.name = NAME;
/*    */     
/* 36 */     updateDescription();
/*    */     
/* 38 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 43 */     flash();
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new Rugged((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
/* 45 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, this.owner, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.amount, true), this.amount));
/* 46 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 47 */       if (!m.isDying && !m.isDead) {
/* 48 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, this.owner, (AbstractPower)new Rugged((AbstractCreature)m, this.amount), this.amount));
/* 49 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, this.owner, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.amount, true), this.amount));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 56 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\gauntletpowers\OnDeathEveryoneRuggedVuln.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */