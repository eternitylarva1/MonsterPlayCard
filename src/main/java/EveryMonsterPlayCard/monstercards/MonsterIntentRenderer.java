package EveryMonsterPlayCard.monstercards;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.DebuffParticleEffect;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;

import EveryMonsterPlayCard.ui.BattleUI.CardBox;

/**
 * MonsterIntentRenderer - 怪物额外意图渲染器
 * 负责在怪物原有意图旁边渲染额外意图，显示怪物即将打出的卡牌信息
 * 支持动态效果，与游戏原生意图系统保持一致
 */
public class MonsterIntentRenderer {
    
    // 存储每个怪物的动态效果状态
    private static HashMap<AbstractMonster, MonsterIntentEffectState> effectStates = new HashMap<>();
    
    /**
     * 渲染额外意图图标（支持动态效果）
     * @param sb SpriteBatch
     * @param monster 怪物实例
     */
    public static void renderExtraIntent(SpriteBatch sb, AbstractMonster monster) {
        // 检查配置是否启用额外意图
        if (!MonsterPlayCardConfig.showExtraIntent) {
            return;
        }
        
        // 获取怪物的卡牌信息
        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
        if (cardPlayer == null || !cardPlayer.isEnabled()) {
            return;
        }
        
        // 获取怪物手牌（使用公共getter方法）
        if (cardPlayer.getMonsterHand() == null || cardPlayer.getMonsterHand().isEmpty()) {
            return;
        }
        
        // 获取或创建该怪物的效果状态
        MonsterIntentEffectState state = effectStates.computeIfAbsent(monster, k -> new MonsterIntentEffectState());
        
        // 更新动态效果状态
        state.update(monster);
        
        // 获取本回合会打出的卡牌（基于能量和优先级）
        ArrayList<AbstractCard> cardsToPlay = getCardsToPlayThisTurn(cardPlayer); 
        if (cardsToPlay.isEmpty()) {
            return;
        }
        
        // 获取卡牌显示位置信息
        CardBox cardBox = getCardBoxForMonster(monster);
        if (cardBox == null || cardBox.shownCards == null) {
            return;
        }
        
        // 获取当前显示的卡牌列表
        ArrayList<AbstractCard> displayedCards = cardBox.shownCards.cardList;
        
        // 为每张将要打出的卡牌渲染意图
        for (int i = 0; i < cardsToPlay.size() && i < displayedCards.size(); i++) {
            AbstractCard cardToPlay = cardsToPlay.get(i);
            AbstractCard displayedCard = displayedCards.get(i);
            
            if (displayedCard != null) {
                // 在卡牌正下方渲染意图
                float intentX = displayedCard.current_x;
                float intentY = displayedCard.current_y - displayedCard.hb.height * CardBox.SHOW_SCALE / 2.0f - 30.0F * Settings.scale;
                
                // 应用BobEffect的垂直偏移
                float bobY = intentY + state.bobEffect.y;
                
                // 渲染卡牌类型图标（带动态效果）
                renderCardTypeIcon(sb, cardToPlay, intentX, bobY, state, i);
               
                // 渲染卡牌伤害值（带动态效果）
                renderCardDamage(sb, cardToPlay, intentX, bobY, state, i);
            }
        }
        
        // 渲染粒子效果
        renderParticleEffects(sb, monster, state);
    }
    
    /**
     * 获取本回合会打出的卡牌列表（基于能量和优先级）
     * @param cardPlayer 怪物卡牌玩家
     * @return 本回合会打出的卡牌列表
     */
    private static ArrayList<AbstractCard> getCardsToPlayThisTurn(MonsterCardPlayer cardPlayer) {
        ArrayList<AbstractCard> cardsToPlay = new ArrayList<>();
        if (cardPlayer == null || cardPlayer.getMonsterHand() == null || cardPlayer.getMonsterHand().isEmpty()) {
            return cardsToPlay;
        }
        
        int availableEnergy = cardPlayer.getCurrentEnergy();
        
        // 按照出牌顺序模拟，获取本回合会打出的卡牌
        for (AbstractCard card : cardPlayer.getMonsterHand().group) {
            if (card == null) continue;
            
            int cardCost = Math.max(0, card.cost);
            if (cardCost <= availableEnergy) {
                cardsToPlay.add(card.makeStatEquivalentCopy());
                availableEnergy -= cardCost;
                
                // 限制最多显示3张卡牌的意图，避免过于拥挤
                if (cardsToPlay.size() >= 3) {
                    break;
                }
            } else {
                // 能量不足，停止添加更多卡牌
                break;
            }
        }
        
        return cardsToPlay;
    }
    
    /**
     * 渲染卡牌类型图标（带动态效果）
     * @param sb SpriteBatch
     * @param card 卡牌实例
     * @param x X坐标
     * @param y Y坐标
     * @param state 效果状态
     * @param cardIndex 卡牌索引
     */
    private static void renderCardTypeIcon(SpriteBatch sb, AbstractCard card, float x, float y,
                                         MonsterIntentEffectState state, int cardIndex) {
        // 根据卡牌类型和伤害值选择不同的图标
        Texture icon = getDynamicIntentIcon(card);
        
        if (icon != null) {
            // 应用透明度动画，使用与原版相同的颜色
            Color color = Color.WHITE.cpy();
            color.a = state.alpha;
            sb.setColor(color);
            
            // 应用旋转效果（仅对DEBUFF类型）
            float rotation = 0.0F;
            if (card.type == AbstractCard.CardType.SKILL && isDebuffCard(card)) {
                rotation = state.rotationAngle;
            }
            
            // 渲染图标，使用与原版相同的方式但缩小一些
            float scale = 0.7F * Settings.scale;
            sb.draw(icon, x - 64.0F, y - 64.0F + state.bobEffect.y, 64.0F, 64.0F, 128.0F, 128.0F, scale, scale, rotation, 0, 0, 128, 128, false, false);
        }
    }
    
    /**
     * 根据卡牌类型和伤害值动态选择意图图标
     * @param card 卡牌实例
     * @return 对应的意图图标
     */
    private static Texture getDynamicIntentIcon(AbstractCard card) {
        switch (card.type) {
            case ATTACK:
                // 根据伤害值选择不同的攻击图标
                int damage = card.damage;
                if (damage < 5) {
                    return ImageMaster.INTENT_ATK_1;
                } else if (damage < 10) {
                    return ImageMaster.INTENT_ATK_2;
                } else if (damage < 15) {
                    return ImageMaster.INTENT_ATK_3;
                } else if (damage < 20) {
                    return ImageMaster.INTENT_ATK_4;
                } else if (damage < 25) {
                    return ImageMaster.INTENT_ATK_5;
                } else if (damage < 30) {
                    return ImageMaster.INTENT_ATK_6;
                } else {
                    return ImageMaster.INTENT_ATK_7;
                }
            case SKILL:
                // 使用字节码分析卡牌行为
                CardBehavior behavior = analyzeCardBehavior(card);
                switch (behavior) {
                    case DEFEND:
                        return ImageMaster.INTENT_DEFEND_L;
                    case DEBUFF:
                        return ImageMaster.INTENT_DEBUFF_L;
                    case BUFF:
                        return ImageMaster.INTENT_BUFF_L;
                    default:
                        return ImageMaster.INTENT_DEFEND_L; // 默认使用防御图标
                }
            case POWER:
                return ImageMaster.INTENT_BUFF_L;
            default:
                return ImageMaster.INTENT_UNKNOWN_L;
        }
    }
    
    /**
     * 卡牌行为枚举
     */
    private enum CardBehavior {
        ATTACK, DEFEND, BUFF, DEBUFF, UNKNOWN
    }
    
    /**
     * 通过字节码分析卡牌行为
     * @param card 卡牌实例
     * @return 卡牌行为类型
     */
    private static CardBehavior analyzeCardBehavior(AbstractCard card) {
        try {
            // 检查卡牌的use方法中是否包含特定Action
            if (containsGainBlockAction(card)) {
                return CardBehavior.DEFEND;
            } else if (containsApplyPowerAction(card)) {
                // 进一步分析ApplyPowerAction的目标
                if (isTargetingPlayer(card)) {
                    return CardBehavior.DEBUFF;
                } else {
                    return CardBehavior.BUFF;
                }
            }
            
            // 默认返回未知行为
            return CardBehavior.UNKNOWN;
        } catch (Exception e) {
            // 如果分析失败，回退到基于名称的判断
            if (isDefendCard(card)) {
                return CardBehavior.DEFEND;
            } else if (isDebuffCard(card)) {
                return CardBehavior.DEBUFF;
            } else {
                return CardBehavior.UNKNOWN;
            }
        }
    }
    
    /**
     * 检查卡牌是否包含GainBlockAction
     */
    private static boolean containsGainBlockAction(AbstractCard card) {
        try {
            // 通过反射获取卡牌的类
            Class<?> cardClass = card.getClass();
            
            // 检查use方法的字节码中是否包含GainBlockAction
            java.lang.reflect.Method useMethod = cardClass.getMethod("use",
                AbstractCreature.class, AbstractMonster.class);
            
            // 简单检查：检查方法名和类引用
            String methodSource = useMethod.toString();
            return methodSource.contains("GainBlockAction") ||
                   methodSource.contains("gainBlock") ||
                   methodSource.contains("block");
            
        } catch (Exception e) {
            // 如果反射失败，尝试通过卡牌属性判断
            return card.baseBlock > 0 || card.block > 0;
        }
    }
    
    /**
     * 检查卡牌是否包含ApplyPowerAction
     */
    private static boolean containsApplyPowerAction(AbstractCard card) {
        try {
            // 通过反射获取卡牌的类
            Class<?> cardClass = card.getClass();
            
            // 检查use方法的字节码中是否包含ApplyPowerAction
            java.lang.reflect.Method useMethod = cardClass.getMethod("use",
                AbstractCreature.class, AbstractMonster.class);
            
            // 简单检查：检查方法名和类引用
            String methodSource = useMethod.toString();
            return methodSource.contains("ApplyPowerAction") ||
                   methodSource.contains("applyPower") ||
                   methodSource.contains("Power");
            
        } catch (Exception e) {
            // 如果反射失败，返回false
            return false;
        }
    }
    
    /**
     * 检查卡牌是否以玩家为目标
     */
    private static boolean isTargetingPlayer(AbstractCard card) {
        // 检查卡牌的目标类型
        if (card.target == AbstractCard.CardTarget.ENEMY) {
            return true; // 目标是敌人（玩家）
        } else if (card.target == AbstractCard.CardTarget.SELF) {
            return false; // 目标是自己
        } else if (card.target == AbstractCard.CardTarget.ALL) {
            // 对于ALL目标，需要进一步分析
            // 这里简化处理：如果是技能牌且包含ApplyPowerAction，认为是debuff
            return containsApplyPowerAction(card);
        }
        
        // 默认情况下，认为是目标玩家
        return true;
    }
    
    /**
     * 渲染卡牌伤害值（带动态效果）
     * @param sb SpriteBatch
     * @param card 卡牌实例
     * @param x X坐标
     * @param y Y坐标
     * @param state 效果状态
     * @param cardIndex 卡牌索引
     */
    private static void renderCardDamage(SpriteBatch sb, AbstractCard card, float x, float y,
                                      MonsterIntentEffectState state, int cardIndex) {
        // 只有攻击卡牌才显示伤害值
        if (card.type != AbstractCard.CardType.ATTACK) {
            return;
        }
        
        // 确保卡牌的damage值已计算（调用applyPowers）
        try {
            card.applyPowers();
        } catch (Exception e) {
            // 如果applyPowers失败，使用原始damage值
        }
        
        // 使用卡牌的damage属性
        int damage = card.damage;
        
        // 在图标下方渲染伤害值
        if (damage > 0) {
            // 应用透明度动画
            Color color = Color.RED.cpy();
            color.a = state.alpha;
            
            // 使用与原版相同的左上对齐方式（参考第965-968行）
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont,
                Integer.toString(damage),
                x - 30.0F * Settings.scale, y + state.bobEffect.y - 12.0F * Settings.scale,
                color);
        }
    }
    
    /**
     * 渲染粒子效果
     * @param sb SpriteBatch
     * @param monster 怪物实例
     * @param state 效果状态
     */
    private static void renderParticleEffects(SpriteBatch sb, AbstractMonster monster, MonsterIntentEffectState state) {
        // 渲染所有粒子效果
        for (AbstractGameEffect effect : state.particleEffects) {
            if (!effect.isDone) {
                effect.render(sb);
            }
        }
    }
    
    /**
     * 清理指定怪物的效果状态
     * @param monster 怪物实例
     */
    public static void cleanupMonsterEffects(AbstractMonster monster) {
        effectStates.remove(monster);
    }
    
    /**
     * 清理所有效果状态
     */
    public static void cleanupAllEffects() {
        effectStates.clear();
    }
    
    /**
     * 怪物意图效果状态类
     * 存储每个怪物的动态效果状态
     */
    private static class MonsterIntentEffectState {
        // 透明度动画 - 初始设为完全可见
        float alpha = 1.0F;
        float alphaTarget = 1.0F;
        
        // 上下浮动效果
        BobEffect bobEffect = new BobEffect();
        
        // 旋转效果（用于DEBUFF类型）
        float rotationAngle = 0.0F;
        
        // 粒子效果
        ArrayList<AbstractGameEffect> particleEffects = new ArrayList<>();
        
        // 粒子计时器
        float particleTimer = 0.0F;
        
        /**
         * 更新效果状态
         * @param monster 怪物实例
         */
        void update(AbstractMonster monster) {
            // 更新透明度动画
            if (alpha != alphaTarget) {
                if (alphaTarget == 1.0F) {
                    alpha += Gdx.graphics.getDeltaTime();
                    if (alpha > alphaTarget) {
                        alpha = alphaTarget;
                    }
                } else {
                    alpha -= Gdx.graphics.getDeltaTime() / 1.5F;
                    if (alpha < 0.0F) {
                        alpha = 0.0F;
                    }
                }
            }
            
            // 更新BobEffect
            bobEffect.update();
            
            // 更新旋转角度
            rotationAngle += Gdx.graphics.getDeltaTime() * 150.0F;
            
            // 更新粒子效果
            updateParticleEffects(monster);
            
            // 清理已完成的效果
            particleEffects.removeIf(effect -> effect.isDone);
        }
        
        /**
         * 更新粒子效果
         * @param monster 怪物实例
         */
        void updateParticleEffects(AbstractMonster monster) {
            // 获取怪物的卡牌信息
            MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
            if (cardPlayer == null || !cardPlayer.isEnabled()) {
                return;
            }
            
            if (cardPlayer.getMonsterHand() == null || cardPlayer.getMonsterHand().isEmpty()) {
                return;
            }
            
            // 只有在透明度大于0时才生成粒子
            if (alpha <= 0.0F) {
                return;
            }
            
            // 更新粒子计时器
            particleTimer -= Gdx.graphics.getDeltaTime();
            
            // 根据卡牌类型生成不同的粒子效果
            for (AbstractCard card : cardPlayer.getMonsterHand().group) {
                if (card == null) continue;
                
                if (particleTimer < 0.0F) {
                    // 根据卡牌类型生成不同的粒子
                    if (card.type == AbstractCard.CardType.SKILL && isDebuffCard(card)) {
                        // DEBUFF类型：紫色粒子
                        particleTimer = 1.0F;
                        particleEffects.add(new DebuffParticleEffect(monster.intentHb.cX, monster.intentHb.cY));
                    } else if (card.type == AbstractCard.CardType.POWER) {
                        // POWER类型：黄色粒子
                        particleTimer = 0.1F;
                        particleEffects.add(new BuffParticleEffect(monster.intentHb.cX, monster.intentHb.cY));
                    } else if (card.type == AbstractCard.CardType.SKILL && isDefendCard(card)) {
                        // 防御类型：蓝色粒子
                        particleTimer = 0.5F;
                        particleEffects.add(new ShieldParticleEffect(monster.intentHb.cX, monster.intentHb.cY));
                    }
                }
            }
        }
    }
    
    /**
     * 获取怪物对应的CardBox
     */
    private static CardBox getCardBoxForMonster(AbstractMonster monster) {
        // 通过MonsterCardPlayer获取CardBox
        MonsterCardPlayer cardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(monster);
        if (cardPlayer != null && cardPlayer.battleCardPanel != null) {
            return cardPlayer.battleCardPanel.cardBox;
        }
        return null;
    }
    
    /**
     * 检查卡牌是否是防御类型
     * @param card 卡牌实例
     * @return 是否是防御类型
     */
    private static boolean isDefendCard(AbstractCard card) {
        // 通过卡牌名称判断是否是防御类型
        String cardName = card.name.toLowerCase();
        return cardName.contains("防御") || cardName.contains("defend") ||
               cardName.contains("格挡") || cardName.contains("block") ||
               cardName.contains("护甲") || cardName.contains("armor");
    }
    
    /**
     * 检查卡牌是否是减益类型
     * @param card 卡牌实例
     * @return 是否是减益类型
     */
    private static boolean isDebuffCard(AbstractCard card) {
        // 通过卡牌名称判断是否是减益类型
        String cardName = card.name.toLowerCase();
        return cardName.contains("虚弱") || cardName.contains("weak") ||
               cardName.contains("易伤") || cardName.contains("vulnerable") ||
               cardName.contains("中毒") || cardName.contains("poison") ||
               cardName.contains("减益") || cardName.contains("debuff");
    }
}