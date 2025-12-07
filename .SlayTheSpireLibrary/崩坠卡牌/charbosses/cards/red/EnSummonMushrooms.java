/*     */ package charbosses.cards.red;
/*     */ 
/*     */ import basemod.ReflectionHacks;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.monsters.MushroomPurple;
/*     */ import charbosses.monsters.MushroomRed;
/*     */ import charbosses.monsters.MushroomWhite;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*     */ import downfall.util.TextureLoader;
/*     */ import expansioncontent.expansionContentMod;
/*     */ import hermit.util.TextureLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ 
/*     */ public class EnSummonMushrooms
/*     */   extends AbstractBossCard {
/*     */   public static final String ID = "downfall:SummonMushrooms";
/*  40 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("downfall:SummonMushrooms");
/*     */ 
/*     */   
/*     */   public EnSummonMushrooms() {
/*  44 */     super("downfall:SummonMushrooms", cardStrings.NAME, expansionContentMod.makeCardPath("SummonMushrooms.png"), 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/*  45 */     this.portrait = TextureLoader.getTextureAsAtlasRegion(expansionContentMod.makeCardPath("SummonMushrooms.png"));
/*  46 */     this.portraitImg = TextureLoader.getTexture(expansionContentMod.makeCardPath("SummonMushrooms.png"));
/*  47 */     loadJokeCardImage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void use(AbstractPlayer p, AbstractMonster m) {
/*  52 */     for (AbstractMonster m2 : (AbstractDungeon.getMonsters()).monsters) {
/*  53 */       if (!m2.isDead && !m2.isDying && !(m2 instanceof charbosses.bosses.AbstractCharBoss)) {
/*  54 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m2.hb.cX, m2.hb.cY - 20.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
/*  55 */         addToBot((AbstractGameAction)new HealAction((AbstractCreature)m, (AbstractCreature)m, m2.currentHealth));
/*  56 */         addToBot((AbstractGameAction)new WaitAction(0.1F));
/*  57 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m2, new DamageInfo((AbstractCreature)m, m.currentHealth, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*  58 */         addToBot((AbstractGameAction)new WaitAction(0.1F));
/*  59 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, 1), 1));
/*  60 */         addToBot((AbstractGameAction)new WaitAction(0.1F));
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     ArrayList<AbstractMonster> mushroomList = new ArrayList<>();
/*  65 */     mushroomList.add(new MushroomPurple(-400.0F, 0.0F));
/*  66 */     mushroomList.add(new MushroomRed(-400.0F, 0.0F));
/*  67 */     mushroomList.add(new MushroomWhite(-400.0F, 0.0F));
/*  68 */     Collections.shuffle(mushroomList);
/*     */     
/*  70 */     ArrayList<AbstractMonster> mushroomList2 = new ArrayList<>();
/*  71 */     mushroomList2.add(new MushroomPurple(-600.0F, 0.0F));
/*  72 */     mushroomList2.add(new MushroomRed(-600.0F, 0.0F));
/*  73 */     mushroomList2.add(new MushroomWhite(-600.0F, 0.0F));
/*  74 */     Collections.shuffle(mushroomList2);
/*     */     
/*  76 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(mushroomList.get(0), true));
/*  77 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(mushroomList2.get(0), true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade() {
/*  83 */     if (!this.upgraded) {
/*  84 */       upgradeName();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard makeCopy() {
/*  90 */     return (AbstractCard)new EnSummonMushrooms();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadJokeCardImage() {
/*  95 */     Texture cardTexture = TextureLoader.getTexture(this.assetUrl.replace("cards", "betacards"));
/*  96 */     cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/*  97 */     int tw = cardTexture.getWidth();
/*  98 */     int th = cardTexture.getHeight();
/*  99 */     TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
/* 100 */     ReflectionHacks.setPrivate(this, AbstractCard.class, "jokePortrait", cardImg);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnSummonMushrooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */