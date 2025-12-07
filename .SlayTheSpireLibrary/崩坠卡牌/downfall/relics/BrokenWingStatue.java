/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.actions.LoseRelicAction;
/*    */ import downfall.actions.SpeechBubbleAction;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class BrokenWingStatue
/*    */   extends CustomRelic
/*    */ {
/* 21 */   public static final String ID = downfallMod.makeID("BrokenWingStatue");
/* 22 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/WingStatue.png"));
/* 23 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/WingStatue.png"));
/*    */   
/* 25 */   private static final String[] DIALOG = (CardCrawlGame.languagePack.getEventString("downfall:WingStatue")).DESCRIPTIONS;
/*    */   
/*    */   public static boolean GIVEN = false;
/*    */   
/*    */   private AbstractMonster receiver;
/*    */   
/*    */   public BrokenWingStatue() {
/* 32 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 37 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 42 */     this.receiver = null;
/* 43 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 44 */       if (m instanceof com.megacrit.cardcrawl.monsters.exordium.Cultist || m instanceof com.megacrit.cardcrawl.monsters.city.Chosen) {
/* 45 */         this.receiver = m;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 50 */     if (this.receiver != null) {
/* 51 */       int DialogIndex; GIVEN = true;
/*    */       
/* 53 */       if (this.receiver instanceof com.megacrit.cardcrawl.monsters.exordium.Cultist) {
/* 54 */         DialogIndex = 4;
/*    */       } else {
/*    */         
/* 57 */         DialogIndex = 6;
/*    */       } 
/*    */       
/* 60 */       flash();
/* 61 */       forceWait(5);
/* 62 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.receiver, (AbstractRelic)this));
/* 63 */       addToBot((AbstractGameAction)new SpeechBubbleAction(DIALOG[DialogIndex], this.receiver, 2.0F));
/* 64 */       forceWait(12);
/* 65 */       flash();
/* 66 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.receiver, (AbstractRelic)this));
/* 67 */       addToBot((AbstractGameAction)new SpeechBubbleAction(DIALOG[DialogIndex + 1], this.receiver, 2.0F));
/* 68 */       forceWait(7);
/* 69 */       flash();
/* 70 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.receiver, (AbstractRelic)this));
/* 71 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LoseRelicAction(this.relicId));
/* 72 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this.receiver));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void forceWait(int num) {
/* 78 */     for (int i = 0; i < num; i++)
/*    */     {
/* 80 */       addToBot((AbstractGameAction)new WaitAction(0.1F));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\BrokenWingStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */