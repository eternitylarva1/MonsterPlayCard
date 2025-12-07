/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.actions.WaitForEscapeAction;
/*    */ import downfall.downfallMod;
/*    */ import gremlin.characters.GremlinCharacter;
/*    */ 
/*    */ public class HeartsMalice
/*    */   extends CustomRelic {
/* 19 */   public static final String ID = downfallMod.makeID("HeartsMalice");
/* 20 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/HeartsMalice.png"));
/* 21 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/HeartsMalice.png"));
/*    */   
/*    */   public HeartsMalice() {
/* 24 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/* 25 */     this.counter = 3;
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 29 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 33 */     if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom) && 
/* 34 */       this.counter > 0) {
/* 35 */       this.counter--;
/* 36 */       if (this.counter == 0) {
/* 37 */         setCounter(-2);
/* 38 */         this.description = this.DESCRIPTIONS[1];
/* 39 */         this.tips.clear();
/* 40 */         this.tips.add(new PowerTip(this.name, this.description));
/* 41 */         initializeTips();
/*    */       } 
/*    */       
/* 44 */       flash();
/*    */ 
/*    */       
/* 47 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitForEscapeAction());
/* 48 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 49 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EscapeAction(m));
/*    */       }
/*    */       
/* 52 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/*    */       
/* 54 */       if (AbstractDungeon.player instanceof GremlinCharacter) {
/* 55 */         ((GremlinCharacter)AbstractDungeon.player).mobState.startOfBattle((GremlinCharacter)AbstractDungeon.player);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 62 */     this.counter = setCounter;
/* 63 */     if (setCounter <= 0) {
/* 64 */       usedUp();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 70 */     return (AbstractRelic)new HeartsMalice();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\HeartsMalice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */