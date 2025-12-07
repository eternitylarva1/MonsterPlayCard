/*    */ package downfall.relics;
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.cards.KnowingSkullWish;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class KnowingSkull extends CustomRelic {
/* 13 */   public static final String ID = downfallMod.makeID("KnowingSkull");
/* 14 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/KnowingSkull.png"));
/* 15 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/KnowingSkull.png"));
/*    */   
/*    */   public KnowingSkull() {
/* 18 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 28 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 29 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new KnowingSkullWish()));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\KnowingSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */