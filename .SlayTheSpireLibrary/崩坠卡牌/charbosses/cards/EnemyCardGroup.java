/*     */ package charbosses.cards;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class EnemyCardGroup extends CardGroup {
/*     */   public static final int HAND_ROW_LENGTH = 10;
/*     */   public static final float HAND_HEIGHT_OFFSET = 0.56F;
/*     */   public AbstractCharBoss owner;
/*  19 */   public static AbstractBossCard hov2holder = null;
/*     */   
/*     */   public EnemyCardGroup(CardGroup.CardGroupType type) {
/*  22 */     super(type);
/*  23 */     this.owner = AbstractCharBoss.boss;
/*     */   }
/*     */   
/*     */   public EnemyCardGroup(CardGroup.CardGroupType type, AbstractCharBoss owner) {
/*  27 */     super(type);
/*  28 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public EnemyCardGroup(CardGroup group, CardGroup.CardGroupType type) {
/*  32 */     super(group, type);
/*  33 */     this.owner = AbstractCharBoss.boss;
/*     */   }
/*     */   
/*     */   public EnemyCardGroup(CardGroup group, CardGroup.CardGroupType type, AbstractCharBoss owner) {
/*  37 */     super(group, type);
/*  38 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public void moveToDiscardPile(AbstractCard c) {
/*  42 */     resetCardBeforeMoving(c);
/*     */     
/*  44 */     System.out.printf(c.name + " DARKEN-CANCELED", new Object[0]);
/*     */     
/*  46 */     this.owner.onCardDrawOrDiscard();
/*     */   }
/*     */   
/*     */   public void moveToExhaustPile(AbstractCard c) {
/*  50 */     for (AbstractRelic r : this.owner.relics) {
/*  51 */       r.onExhaust(c);
/*     */     }
/*  53 */     for (AbstractPower p : this.owner.powers) {
/*  54 */       p.onExhaust(c);
/*     */     }
/*  56 */     c.triggerOnExhaust();
/*  57 */     resetCardBeforeMoving(c);
/*  58 */     AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/*     */     
/*  60 */     this.owner.onCardDrawOrDiscard();
/*     */   }
/*     */   
/*     */   public void moveToHand(AbstractCard c, CardGroup group) {
/*  64 */     c.unhover();
/*  65 */     c.lighten(true);
/*  66 */     c.setAngle(0.0F);
/*  67 */     c.drawScale = 0.12F;
/*  68 */     c.targetDrawScale = 0.35F;
/*  69 */     c.current_x = CardGroup.DRAW_PILE_X;
/*  70 */     c.current_y = CardGroup.DRAW_PILE_Y;
/*  71 */     group.removeCard(c);
/*  72 */     this.owner.hand.addToTop(c);
/*  73 */     this.owner.hand.refreshHandLayout();
/*  74 */     this.owner.hand.applyPowers();
/*     */   }
/*     */ 
/*     */   
/*     */   public void moveToHand(AbstractCard c) {
/*  79 */     resetCardBeforeMoving(c);
/*  80 */     c.unhover();
/*  81 */     c.lighten(true);
/*  82 */     c.setAngle(0.0F);
/*  83 */     c.drawScale = 0.12F;
/*  84 */     c.targetDrawScale = 0.35F;
/*  85 */     c.current_x = CardGroup.DRAW_PILE_X;
/*  86 */     c.current_y = CardGroup.DRAW_PILE_Y;
/*  87 */     this.owner.hand.addToTop(c);
/*  88 */     this.owner.hand.refreshHandLayout();
/*  89 */     this.owner.hand.applyPowers();
/*     */   }
/*     */   
/*     */   public void moveToDeck(AbstractCard c, boolean randomSpot) {
/*  93 */     resetCardBeforeMoving(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveToBottomOfDeck(AbstractCard c) {
/* 105 */     resetCardBeforeMoving(c);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetCardBeforeMoving(AbstractCard c) {
/* 110 */     if (AbstractDungeon.player.hoveredCard == c) {
/* 111 */       AbstractDungeon.player.releaseCard();
/*     */     }
/* 113 */     AbstractDungeon.actionManager.removeFromQueue(c);
/* 114 */     c.unhover();
/* 115 */     c.untip();
/* 116 */     c.stopGlowing();
/* 117 */     this.group.remove(c);
/*     */   }
/*     */   
/*     */   public void initializeDeck(CardGroup masterDeck) {
/* 121 */     clear();
/* 122 */     CardGroup copy = new CardGroup(masterDeck, CardGroup.CardGroupType.DRAW_PILE);
/* 123 */     copy.shuffle(AbstractDungeon.shuffleRng);
/* 124 */     ArrayList<AbstractCard> placeOnTop = new ArrayList<>();
/* 125 */     for (AbstractCard c : copy.group) {
/* 126 */       if (c.isInnate) {
/* 127 */         placeOnTop.add(c); continue;
/* 128 */       }  if (c.inBottleFlame || c.inBottleLightning || c.inBottleTornado) {
/* 129 */         placeOnTop.add(c); continue;
/*     */       } 
/* 131 */       c.target_x = CardGroup.DRAW_PILE_X;
/* 132 */       c.target_y = CardGroup.DRAW_PILE_Y;
/* 133 */       c.current_x = CardGroup.DRAW_PILE_X;
/* 134 */       c.current_y = CardGroup.DRAW_PILE_Y;
/* 135 */       addToTop(c);
/*     */     } 
/*     */     
/* 138 */     for (AbstractCard c : placeOnTop) {
/* 139 */       addToTop(c);
/*     */     }
/* 141 */     if (placeOnTop.size() > AbstractDungeon.player.masterHandSize);
/*     */ 
/*     */     
/* 144 */     placeOnTop.clear();
/*     */   }
/*     */   
/*     */   public void triggerOnOtherCardPlayed(AbstractCard usedCard) {
/* 148 */     for (AbstractCard c : this.group) {
/* 149 */       if (c != usedCard) {
/* 150 */         c.triggerOnOtherCardPlayed(usedCard);
/*     */       }
/*     */     } 
/* 153 */     for (AbstractPower p : AbstractCharBoss.boss.powers) {
/* 154 */       p.onAfterCardPlayed(usedCard);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeCard(AbstractCard c) {
/* 159 */     this.group.remove(c);
/* 160 */     if (this.type == CardGroup.CardGroupType.MASTER_DECK) {
/* 161 */       c.onRemoveFromMasterDeck();
/*     */     }
/*     */   }
/*     */   
/*     */   public void refreshHandLayout() {
/* 166 */     if ((AbstractDungeon.getCurrRoom()).monsters != null && (AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/*     */       return;
/*     */     }
/* 169 */     for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/* 170 */       o.hideEvokeValues();
/*     */     }
/* 172 */     for (AbstractRelic r : AbstractCharBoss.boss.relics) {
/* 173 */       r.onRefreshHand();
/*     */     }
/* 175 */     AbstractCard hoveredcard = null;
/* 176 */     for (int i = 0; i < this.group.size(); i++) {
/* 177 */       AbstractCard c = this.group.get(i);
/* 178 */       if (hov2holder != c) {
/* 179 */         c.targetDrawScale = 0.35F;
/*     */       }
/* 181 */       int cardsinrow = Math.min(this.group.size() - 10 * (int)Math.floor((i / 10.0F)), 10);
/* 182 */       float widthspacing = AbstractCard.IMG_WIDTH_S + 100.0F * Settings.scale;
/* 183 */       c.target_x = Settings.WIDTH * 0.9F - (cardsinrow + 0.5F) * widthspacing * 0.35F + widthspacing * 0.35F * (i % 10);
/* 184 */       c.target_y = Settings.HEIGHT * 0.56F + AbstractCard.IMG_HEIGHT_S * 0.35F * ((float)Math.floor((i / 10.0F)) + ((this.group.size() > 10) ? 0.0F : 1.0F));
/* 185 */       if (((AbstractBossCard)c).hov2 && c.hb.hovered && (
/* 186 */         hoveredcard == null || hov2holder == c)) {
/* 187 */         hoveredcard = c;
/*     */       }
/*     */     } 
/*     */     
/* 191 */     if (hoveredcard != null) {
/* 192 */       hoverCardPush(hoveredcard);
/*     */     }
/*     */   }
/*     */   
/*     */   public void glowCheck() {
/* 197 */     for (AbstractCard c : this.group) {
/* 198 */       if (c.canUse(AbstractDungeon.player, (AbstractMonster)AbstractCharBoss.boss) && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.HAND_SELECT) {
/* 199 */         c.beginGlowing();
/*     */       } else {
/* 201 */         c.stopGlowing();
/*     */       } 
/* 203 */       c.triggerOnGlowCheck();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCardNumber(AbstractCard c) {
/* 208 */     for (int i = 0; i < this.group.size(); i++) {
/* 209 */       if (c.equals(this.group.get(i))) {
/* 210 */         return i;
/*     */       }
/*     */     } 
/* 213 */     return -1;
/*     */   }
/*     */   
/*     */   public ArrayList<AbstractCard> getCardRow(AbstractCard c) {
/* 217 */     int cardNum = getCardNumber(c);
/* 218 */     ArrayList<AbstractCard> cardrow = new ArrayList<>();
/* 219 */     for (int i = 10 * (int)Math.floor((cardNum / 10.0F)); i < this.group.size() && i < 10 * (1 + (int)Math.floor((i / 10.0F))); i++) {
/* 220 */       cardrow.add(this.group.get(i));
/*     */     }
/* 222 */     return cardrow;
/*     */   }
/*     */   
/*     */   public void hoverCardPush(AbstractCard c) {
/* 226 */     int cardNum = getCardNumber(c) % 10;
/* 227 */     ArrayList<AbstractCard> cardrow = getCardRow(c);
/* 228 */     if (cardrow.size() > 1) {
/* 229 */       float pushAmt = 0.4F;
/* 230 */       if (cardrow.size() == 2) {
/* 231 */         pushAmt = 0.2F;
/* 232 */       } else if (cardrow.size() == 3 || cardrow.size() == 4) {
/* 233 */         pushAmt = 0.27F;
/*     */       } 
/* 235 */       pushAmt *= 0.46666667F; int currentSlot;
/* 236 */       for (currentSlot = cardNum + 1; currentSlot < cardrow.size(); currentSlot++) {
/* 237 */         AbstractCard abstractCard = cardrow.get(currentSlot);
/* 238 */         abstractCard.target_x += AbstractCard.IMG_WIDTH_S * pushAmt;
/* 239 */         pushAmt *= 0.25F;
/*     */       } 
/* 241 */       pushAmt = 0.4F;
/* 242 */       if (cardrow.size() == 2) {
/* 243 */         pushAmt = 0.2F;
/* 244 */       } else if (cardrow.size() == 3 || cardrow.size() == 4) {
/* 245 */         pushAmt = 0.27F;
/*     */       } 
/* 247 */       pushAmt *= 0.46666667F;
/* 248 */       for (currentSlot = cardNum - 1; currentSlot > -1 && currentSlot < cardrow.size(); currentSlot--) {
/* 249 */         AbstractCard abstractCard2 = cardrow.get(currentSlot);
/* 250 */         abstractCard2.target_x -= AbstractCard.IMG_WIDTH_S * pushAmt;
/* 251 */         pushAmt *= 0.25F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbstractBossCard getHighestValueCard() {
/* 257 */     AbstractBossCard r = null;
/* 258 */     int record = -99;
/* 259 */     for (AbstractCard c : this.group) {
/* 260 */       AbstractBossCard cc = (AbstractBossCard)c;
/* 261 */       if (cc.getValue() > record) {
/* 262 */         r = cc;
/* 263 */         record = cc.getValue();
/*     */       } 
/*     */     } 
/* 266 */     return r;
/*     */   }
/*     */   
/*     */   public AbstractBossCard getHighestValueCard(AbstractCard.CardType type) {
/* 270 */     AbstractBossCard r = null;
/* 271 */     int record = -99;
/* 272 */     for (AbstractCard c : this.group) {
/* 273 */       if (c.type == type) {
/* 274 */         AbstractBossCard cc = (AbstractBossCard)c;
/* 275 */         if (cc.getValue() > record) {
/* 276 */           r = cc;
/* 277 */           record = cc.getValue();
/*     */         } 
/*     */       } 
/*     */     } 
/* 281 */     return r;
/*     */   }
/*     */   
/*     */   public AbstractBossCard getHighestUpgradeValueCard() {
/* 285 */     AbstractBossCard r = null;
/* 286 */     int record = -99;
/* 287 */     for (AbstractCard c : this.group) {
/* 288 */       AbstractBossCard cc = (AbstractBossCard)c;
/* 289 */       if (cc.getUpgradeValue() > record) {
/* 290 */         r = cc;
/* 291 */         record = cc.getValue();
/*     */       } 
/*     */     } 
/* 294 */     return r;
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\EnemyCardGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */