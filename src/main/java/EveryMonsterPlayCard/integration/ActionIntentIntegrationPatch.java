package EveryMonsterPlayCard.integration;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 动作-意图集成补丁
 * 在怪物出牌时自动调用集成管理器进行动作转换
 */
public class ActionIntentIntegrationPatch {
    
    /**
     * 怪物卡牌执行补丁
     * 在怪物执行卡牌时自动转换动作为意图
     */
    @SpirePatch(
        clz = EveryMonsterPlayCard.monstercards.actions.executeMonsterCardAction.class,
        method = "update"
    )
    public static class ExecuteMonsterCardActionPatch {
        
        public static void Postfix(EveryMonsterPlayCard.monstercards.actions.executeMonsterCardAction __instance) {
            try {
                // 获取卡牌和怪物信息
                AbstractCard card = getCardFromAction(__instance);
                AbstractMonster monster = getMonsterFromAction(__instance);
                
                if (card != null && monster != null) {
                    // 调用集成管理器处理卡牌转换
                    ActionIntentIntegrationManager.getInstance().processMonsterCard(monster, card);
                    
                    Hpr.info("补丁触发：怪物 " + monster.name + " 执行卡牌 " + card.name);
                }
                
            } catch (Exception e) {
                Hpr.info("ExecuteMonsterCardAction补丁出错: " + e.getMessage());
            }
        }
        
        /**
         * 从动作中获取卡牌
         */
        private static AbstractCard getCardFromAction(EveryMonsterPlayCard.monstercards.actions.executeMonsterCardAction action) {
            try {
                // 使用反射获取卡牌字段
                java.lang.reflect.Field cardField = action.getClass().getDeclaredField("card");
                cardField.setAccessible(true);
                return (AbstractCard) cardField.get(action);
            } catch (Exception e) {
                Hpr.info("获取卡牌失败: " + e.getMessage());
                return null;
            }
        }
        
        /**
         * 从动作中获取怪物
         */
        private static AbstractMonster getMonsterFromAction(EveryMonsterPlayCard.monstercards.actions.executeMonsterCardAction action) {
            try {
                // 使用反射获取怪物字段
                java.lang.reflect.Field monsterField = action.getClass().getDeclaredField("monster");
                monsterField.setAccessible(true);
                return (AbstractMonster) monsterField.get(action);
            } catch (Exception e) {
                Hpr.info("获取怪物失败: " + e.getMessage());
                return null;
            }
        }
    }
    
    /**
     * 怪物回合开始补丁
     * 在怪物回合开始时预测意图
     */
    @SpirePatch(
        clz = EveryMonsterPlayCard.monstercards.MonsterCardPlayer.class,
        method = "onTurnStart"
    )
    public static class MonsterTurnStartPatch {
        
        public static void Postfix(MonsterCardPlayer __instance) {
            try {
                // 获取怪物信息
                AbstractMonster monster = getMonsterFromCardPlayer(__instance);
                
                if (monster != null) {
                    // 预测怪物下一步意图
                    ActionIntentIntegrationManager.getInstance().predictNextIntent(monster);
                    
                    Hpr.info("补丁触发：怪物 " + monster.name + " 回合开始，预测意图");
                }
                
            } catch (Exception e) {
                Hpr.info("MonsterTurnStart补丁出错: " + e.getMessage());
            }
        }
        
        /**
         * 从CardPlayer中获取怪物
         */
        private static AbstractMonster getMonsterFromCardPlayer(MonsterCardPlayer cardPlayer) {
            try {
                // 使用反射获取怪物字段
                java.lang.reflect.Field monsterField = cardPlayer.getClass().getDeclaredField("monster");
                monsterField.setAccessible(true);
                return (AbstractMonster) monsterField.get(cardPlayer);
            } catch (Exception e) {
                Hpr.info("获取怪物失败: " + e.getMessage());
                return null;
            }
        }
    }
    
    /**
     * 怪物手牌更新补丁
     * 在怪物手牌更新时分析卡牌行为
     */
    @SpirePatch(
        clz = EveryMonsterPlayCard.monstercards.MonsterCardPlayer.class,
        method = "refreshDisplayedCards"
    )
    public static class MonsterHandUpdatePatch {
        
        public static void Postfix(MonsterCardPlayer __instance) {
            try {
                // 获取怪物信息
                AbstractMonster monster = getMonsterFromCardPlayer(__instance);
                
                if (monster != null) {
                    // 分析怪物手牌中的卡牌行为
                    analyzeMonsterHand(__instance, monster);
                    
                    Hpr.info("补丁触发：怪物 " + monster.name + " 手牌更新，分析卡牌行为");
                }
                
            } catch (Exception e) {
                Hpr.info("MonsterHandUpdate补丁出错: " + e.getMessage());
            }
        }
        
        /**
         * 分析怪物手牌
         */
        private static void analyzeMonsterHand(MonsterCardPlayer cardPlayer, AbstractMonster monster) {
            try {
                if (cardPlayer.getMonsterHand() != null && !cardPlayer.getMonsterHand().isEmpty()) {
                    // 分析第一张卡牌的行为
                    AbstractCard firstCard = cardPlayer.getMonsterHand().group.get(0);
                    
                    if (firstCard != null) {
                        String behavior = ActionIntentIntegrationManager.getInstance().analyzeCardBehavior(firstCard);
                        Hpr.info("怪物 " + monster.name + " 第一张卡牌 " + firstCard.name + " 行为分析: " + behavior);
                    }
                }
            } catch (Exception e) {
                Hpr.info("分析怪物手牌失败: " + e.getMessage());
            }
        }
        
        /**
         * 从CardPlayer中获取怪物
         */
        private static AbstractMonster getMonsterFromCardPlayer(MonsterCardPlayer cardPlayer) {
            try {
                // 使用反射获取怪物字段
                java.lang.reflect.Field monsterField = cardPlayer.getClass().getDeclaredField("monster");
                monsterField.setAccessible(true);
                return (AbstractMonster) monsterField.get(cardPlayer);
            } catch (Exception e) {
                Hpr.info("获取怪物失败: " + e.getMessage());
                return null;
            }
        }
    }
    
    /**
     * 模块初始化补丁
     * 在模块初始化时启用集成系统
     */
    @SpirePatch(
        clz = EveryMonsterPlayCard.modcore.everyMonsterPlayCard.class,
        method = "initialize"
    )
    public static class ModInitializationPatch {
        
        public static void Postfix() {
            try {
                // 启用动作-意图集成系统
                ActionIntentIntegrationManager.getInstance().enableIntegration();
                
                Hpr.info("动作-意图集成系统已启用");
                
            } catch (Exception e) {
                Hpr.info("ModInitialization补丁出错: " + e.getMessage());
            }
        }
    }
}