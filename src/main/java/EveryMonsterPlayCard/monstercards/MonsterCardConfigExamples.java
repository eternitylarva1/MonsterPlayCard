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