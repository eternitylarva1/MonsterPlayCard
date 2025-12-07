/*     */ package downfall.actions;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import downfall.downfallMod;
/*     */ import downfall.powers.neowpowers.TrueNeowPower;
/*     */ import slimebound.SlimeboundMod;
/*     */ 
/*     */ public class NeowGainMinionPowersAction extends AbstractGameAction {
/*     */   private AbstractMonster owner;
/*     */   
/*     */   public NeowGainMinionPowersAction(AbstractMonster owner, int act) {
/*  18 */     this.owner = owner;
/*  19 */     this.num = act;
/*     */   }
/*     */   private int num;
/*     */   public boolean checkBossFaced(String id) {
/*  23 */     if (downfallMod.Act1BossFaced.equals(id)) return true; 
/*  24 */     if (downfallMod.Act2BossFaced.equals(id)) return true; 
/*  25 */     if (downfallMod.Act3BossFaced.equals(id)) return true; 
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  33 */     this.isDone = true;
/*  34 */     SlimeboundMod.logger.info(downfallMod.Act1BossFaced);
/*  35 */     SlimeboundMod.logger.info(downfallMod.Act2BossFaced);
/*  36 */     SlimeboundMod.logger.info(downfallMod.Act3BossFaced);
/*  37 */     SlimeboundMod.logger.info("downfall:Ironclad " + checkBossFaced("downfall:Ironclad"));
/*  38 */     SlimeboundMod.logger.info("downfall:Silent " + checkBossFaced("downfall:Silent"));
/*  39 */     SlimeboundMod.logger.info("downfall:Defect " + checkBossFaced("downfall:Defect"));
/*  40 */     SlimeboundMod.logger.info("downfall:Watcher " + checkBossFaced("downfall:Watcher"));
/*  41 */     SlimeboundMod.logger.info("downfall:Hermit " + checkBossFaced("downfall:Hermit"));
/*     */     
/*  43 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new TrueNeowPower((AbstractCreature)this.owner, 
/*  44 */             checkBossFaced("downfall:Ironclad"), 
/*  45 */             checkBossFaced("downfall:Silent"), 
/*  46 */             checkBossFaced("downfall:Defect"), 
/*  47 */             checkBossFaced("downfall:Watcher"), 
/*  48 */             checkBossFaced("downfall:Hermit"))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     for (int i = 0; i < 3; i++)
/* 142 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\NeowGainMinionPowersAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */