/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ import hermit.util.Wiz;
/*    */ import java.util.Iterator;
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
/*    */ public class ShatteredFragment
/*    */   extends CustomRelic
/*    */ {
/* 32 */   public static final String ID = downfallMod.makeID("ShatteredFragment");
/* 33 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/WingShiv.png"));
/* 34 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/WingShiv.png"));
/*    */   
/*    */   public ShatteredFragment() {
/* 37 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 43 */     return this.DESCRIPTIONS[0];
/*    */   }
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
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 59 */     if (card.type == AbstractCard.CardType.POWER) {
/* 60 */       boolean isEliteOrBoss = (AbstractDungeon.getCurrRoom()).eliteTrigger;
/* 61 */       Iterator<AbstractMonster> var2 = (AbstractDungeon.getMonsters()).monsters.iterator();
/* 62 */       while (var2.hasNext()) {
/* 63 */         AbstractMonster m = var2.next();
/* 64 */         if (m.type == AbstractMonster.EnemyType.BOSS) {
/* 65 */           isEliteOrBoss = true;
/*    */         }
/*    */       } 
/*    */       
/* 69 */       if (isEliteOrBoss) {
/* 70 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 71 */         Wiz.atb((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\ShatteredFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */