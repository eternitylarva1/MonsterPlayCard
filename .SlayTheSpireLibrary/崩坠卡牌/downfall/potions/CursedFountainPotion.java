/*    */ package downfall.potions;
/*    */ import basemod.abstracts.CustomPotion;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class CursedFountainPotion extends CustomPotion {
/* 19 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("downfall:CursedFountainPotion"); public static final String POTION_ID = "downfall:CursedFountainPotion";
/* 20 */   public static final String NAME = potionStrings.NAME;
/* 21 */   public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
/*    */ 
/*    */   
/*    */   public CursedFountainPotion() {
/* 25 */     super(NAME, "downfall:CursedFountainPotion", AbstractPotion.PotionRarity.PLACEHOLDER, AbstractPotion.PotionSize.H, AbstractPotion.PotionColor.FRUIT);
/* 26 */     this.isThrown = true;
/* 27 */     this.targetRequired = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 32 */     this.potency = getPotency();
/* 33 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2] + this.potency + potionStrings.DESCRIPTIONS[3];
/* 34 */     this.tips.clear();
/* 35 */     this.tips.add(new PowerTip(this.name, this.description));
/* 36 */     this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
/* 37 */     this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.VULNERABLE.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.VULNERABLE.NAMES[0])));
/* 38 */     this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 43 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower(target, this.potency, false), this.potency));
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower(target, this.potency, false), this.potency));
/* 45 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower(target, -this.potency), -this.potency));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomPotion makeCopy() {
/* 52 */     return new CursedFountainPotion();
/*    */   }
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 56 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\potions\CursedFountainPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */