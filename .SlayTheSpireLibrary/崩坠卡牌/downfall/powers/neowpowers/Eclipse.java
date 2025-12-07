/*    */ package downfall.powers.neowpowers;
/*    */ import basemod.abstracts.AbstractCardModifier;
/*    */ import basemod.helpers.CardModifierManager;
/*    */ import charbosses.powers.bossmechanicpowers.AbstractBossMechanicPower;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import expansioncontent.cardmods.PropertiesMod;
/*    */ import hermit.cards.ImpendingDoom;
/*    */ 
/*    */ public class Eclipse extends AbstractBossMechanicPower {
/* 18 */   public static final String POWER_ID = downfallMod.makeID("NeowEclipse");
/* 19 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
/* 20 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
/*    */   
/* 22 */   private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit384.png"));
/* 23 */   private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowHermit332.png"));
/*    */   
/*    */   public Eclipse(AbstractCreature owner) {
/* 26 */     this.ID = POWER_ID;
/* 27 */     this.owner = owner;
/* 28 */     this.type = AbstractPower.PowerType.BUFF;
/*    */     
/* 30 */     this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
/* 31 */     this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
/*    */     
/* 33 */     this.name = NAME;
/*    */     
/* 35 */     updateDescription();
/*    */     
/* 37 */     this.canGoNegative = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 42 */     flash();
/* 43 */     ImpendingDoom impendingDoom = new ImpendingDoom();
/* 44 */     CardModifierManager.addModifier((AbstractCard)impendingDoom, (AbstractCardModifier)new PropertiesMod(PropertiesMod.supportedProperties.ETHEREAL, false));
/* 45 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)impendingDoom));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 50 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\Eclipse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */