/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ import downfall.monsters.NeowBossFinal;
/*    */ import downfall.powers.NeowInvulnerablePower;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeartsFavorPower
/*    */   extends AbstractPower
/*    */ {
/* 41 */   public static final String POWER_ID = downfallMod.makeID("HeartsFavorPower");
/* 42 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 43 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 45 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect284.png"));
/* 46 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowDefect232.png"));
/*    */ 
/*    */   
/*    */   public HeartsFavorPower(AbstractCreature owner, int amount) {
/* 50 */     this.ID = POWER_ID;
/* 51 */     this.owner = owner;
/* 52 */     this.amount = amount;
/* 53 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 55 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 56 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 58 */     this.name = NAME;
/*    */     
/* 60 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 65 */     AbstractPlayer p = AbstractDungeon.player;
/* 66 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 67 */       if (m != null && !m.isDead && !m.isDying && !m.halfDead && m.id == NeowBossFinal.ID && 
/* 68 */         m.hasPower(NeowInvulnerablePower.POWER_ID)) {
/* 69 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new NeowInvulnerablePower((AbstractCreature)m, -this.amount), -this.amount));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 77 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\HeartsFavorPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */