package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.monstercards.MonsterCardConfig;

import EveryMonsterPlayCard.monstercards.cards.MonsterSkillCard;
import EveryMonsterPlayCard.utils.Hpr;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

/**
 * 怪物卡牌配置示例
 *
 * 这个类展示了如何为不同的怪物定义自定义卡牌
 * 用户可以参考这个示例来创建自己的怪物卡牌配置
 */
public class MonsterCardConfigExamples {

    /**
     * 为LouseNormal怪物设置自定义卡牌
     * 例子：给虫子更多攻击选项
     */


    /**
     * 为JawWorm设置自定义卡牌
     * 例子：给下颚蠕虫更强大的攻击
     */
    public static void setupJawWormCards() {
        try {
            ArrayList<AbstractCard> jawWormCards = new ArrayList<>();

            // 强力攻击
            AbstractCard chomp = createCard("STRIKE_R", 12, AbstractCard.CardType.ATTACK, "咀嚼");
            AbstractCard slam = createCard("BASH", 10, AbstractCard.CardType.ATTACK, "重击");

            // 防御
            AbstractCard defend = createCard("DEFEND_R", 8, AbstractCard.CardType.SKILL, "护甲");

            // 能力
            AbstractCard strength = createCard("RAGE", 3, AbstractCard.CardType.POWER, "力量提升");

            jawWormCards.add(chomp);
            jawWormCards.add(slam);
            jawWormCards.add(defend);
            jawWormCards.add(strength);
            jawWormCards.add(chomp); // 添加重复

            // 设置到配置中
            MonsterCardConfig.getInstance().addCustomConfig("JawWorm", jawWormCards);

            Hpr.info("已为JawWorm设置自定义卡牌：咀嚼、重击、护甲、力量提升");

        } catch (Exception e) {
            Hpr.info("设置JawWorm卡牌时出错: " + e.getMessage());
        }
    }

    /**
     * 创建一个自定义卡牌
     */
    private static AbstractCard createCard(String cardId, int amount, AbstractCard.CardType type, String displayName) {
        try {
            AbstractCard baseCard = CardLibrary.getCard(cardId);
            if (baseCard != null) {
                AbstractCard customCard;

                // 对于技能卡牌，创建怪物专用的MonsterSkillCard
                if (type == AbstractCard.CardType.SKILL) {
                    // 使用MonsterSkillCard，给怪物自己加格挡
                    customCard = new MonsterSkillCard("Monster:" + cardId, displayName,
                        baseCard.assetUrl, 0, amount, baseCard.target);
                } else {
                    // 其他类型使用原始卡牌
                    customCard = baseCard.makeCopy();
                }

                // 设置为无色，怪物无能量限制
                customCard.color = AbstractCard.CardColor.COLORLESS;
                customCard.freeToPlayOnce = true;
                customCard.cost = 0;
                customCard.costForTurn = 0;

                // 根据卡牌类型设置数值
                if (type == AbstractCard.CardType.ATTACK) {
                    customCard.baseDamage = amount;
                    customCard.damage = amount;
                    customCard.name = displayName; // 修改显示名称
                } else if (type == AbstractCard.CardType.SKILL) {
                    customCard.block = amount;
                    customCard.baseBlock = amount;
                    // name已经在MonsterSkillCard构造函数中设置了
                } else if (type == AbstractCard.CardType.POWER) {
                    customCard.name = displayName;
                    // Power卡牌可以根据需要设置其他属性
                }

                // 移除卡牌原有的限制条件
                customCard.isInAutoplay = true;

                return customCard;
            }
        } catch (Exception e) {
            Hpr.info("创建卡牌时出错: " + cardId + " - " + e.getMessage());
        }

        return null;
    }

    /**
     * 清除所有自定义配置，恢复默认
     */
    public static void clearAllCustomConfigs() {
        MonsterCardConfig.getInstance().clearAllConfigs();
        Hpr.info("已清除所有自定义怪物卡牌配置");
    }

    /**
     * 显示当前所有配置的怪物
     */
    public static void showConfiguredMonsters() {
        MonsterCardConfig config = MonsterCardConfig.getInstance();
        java.util.List<String> configuredMonsters = config.getConfiguredMonsterIds();

        if (configuredMonsters.isEmpty()) {
            Hpr.info("当前没有自定义配置的怪物");
        } else {
            Hpr.info("当前自定义配置的怪物有 " + configuredMonsters.size() + " 个:");
            for (String monsterId : configuredMonsters) {
                Hpr.info("  - " + monsterId);
            }
        }
    }
}