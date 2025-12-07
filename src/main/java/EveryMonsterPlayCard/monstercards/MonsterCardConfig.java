package EveryMonsterPlayCard.monstercards;

import EveryMonsterPlayCard.monstercards.cards.MonsterAttackCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterSkillCard;
import EveryMonsterPlayCard.monstercards.cards.MonsterPowerCard;
import EveryMonsterPlayCard.utils.Hpr;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

// 新导入的怪物卡牌
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard1;
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard2;
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard3;
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard4;
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard5;
import EveryMonsterPlayCard.cards.monster.MonsterAttackCard6;
import EveryMonsterPlayCard.cards.monster.MonsterSkillCard1;
import EveryMonsterPlayCard.cards.monster.MonsterSkillCard2;
import EveryMonsterPlayCard.cards.monster.MonsterSkillCard3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 怪物卡牌配置管理器
 * 所有怪物使用统一的卡牌池（使用游戏原版卡牌）
 */
public class MonsterCardConfig {

    // 单例模式
    private static MonsterCardConfig instance = null;

    // 统一怪物卡牌池（所有怪物都使用相同的卡牌配置）
    private static List<AbstractCard> universalMonsterCards;

    private MonsterCardConfig() {
        initializeUniversalCardPool();
    }

    public static MonsterCardConfig getInstance() {
        if (instance == null) {
            instance = new MonsterCardConfig();
        }
        return instance;
    }

    /**
     * 初始化统一怪物卡牌池（使用我们自定义的怪物卡牌）
     * 所有怪物都使用这套卡牌
     */
    private void initializeUniversalCardPool() {
        universalMonsterCards = new ArrayList<>();

        // 添加从崩坠卡牌搬运过来的10张怪物卡牌（简化版本）

        // 1. MonsterAttackCard1 - 红色攻击卡 (2费，8伤害)
        universalMonsterCards.add(new MonsterAttackCard1());

        // 2. MonsterAttackCard2 - 红色攻击卡 (1费，基于当前格挡伤害)
        universalMonsterCards.add(new MonsterAttackCard2());

        // 3. MonsterAttackCard3 - 绿色攻击卡 (0费，11伤害，内在，消耗)
        universalMonsterCards.add(new MonsterAttackCard3());

        // 4. MonsterAttackCard4 - 红色攻击卡 (2费，20伤害，内在)
        universalMonsterCards.add(new MonsterAttackCard4());

        // 5. MonsterAttackCard5 - 红色攻击卡 (1费，8伤害，全体)
        universalMonsterCards.add(new MonsterAttackCard5());

        // 6. MonsterAttackCard6 - 红色攻击卡 (2费，12伤害+虚弱)
        universalMonsterCards.add(new MonsterAttackCard6());

        // 7. MonsterSkillCard1 - 蓝色技能卡 (1费，11格挡，如果当前无格挡)
        universalMonsterCards.add(new MonsterSkillCard1());

        // 8. MonsterSkillCard2 - 绿色技能卡 (1费，5格挡)
        universalMonsterCards.add(new MonsterSkillCard2());

        // 9. MonsterSkillCard3 - 红色技能卡 (1费，5格挡)
        universalMonsterCards.add(new MonsterSkillCard3());

        // 清理掉null值，确保只添加有效的卡牌
        universalMonsterCards.removeIf(card -> card == null);

        // 洗牌以确保随机性
        Collections.shuffle(universalMonsterCards);

        Hpr.info("统一怪物卡牌池初始化完成，包含 " + universalMonsterCards.size() + " 张卡牌");

        // 调试信息：显示成功创建的卡牌
        for (AbstractCard card : universalMonsterCards) {
            Hpr.info("成功创建卡牌: " + card.name);
        }
    }

    /**
     * 创建怪物卡牌（使用游戏原版卡牌）
     */

    /**
     * 获取指定怪物的卡牌配置（返回统一卡牌池的副本）
     */
    public List<AbstractCard> getMonsterCardConfig(String monsterId) {
        if (universalMonsterCards == null || universalMonsterCards.isEmpty()) {
            Hpr.info("警告：统一卡牌池为空，重新初始化");
            initializeUniversalCardPool();
        }

        // 返回统一卡牌池的副本，确保每个怪物有独立的牌组
        List<AbstractCard> monsterCards = new ArrayList<>();
        for (AbstractCard card : universalMonsterCards) {
            if (card != null) {
                monsterCards.add(card.makeCopy());
            } else {
                Hpr.info("发现null卡牌，跳过");
            }
        }

        Hpr.info("为怪物 " + monsterId + " 配置了统一卡牌池，包含 " + monsterCards.size() + " 张卡牌");
        return monsterCards;
    }

    /**
     * 总是返回true，因为所有怪物都使用统一配置
     */
    public boolean hasCustomConfig(String monsterId) {
        return true;
    }

    /**
     * 获取统一卡牌池中的所有怪物ID（返回空列表，因为没有特定配置）
     */
    public List<String> getConfiguredMonsterIds() {
        return new ArrayList<>();
    }

    /**
     * 清空所有配置（重置统一卡牌池）
     */
    public void clearAllConfigs() {
        if (universalMonsterCards != null) {
            universalMonsterCards.clear();
        }
    }

    /**
     * 为指定怪物添加自定义卡牌配置（已废弃，统一使用卡牌池）
     */
    public void addCustomConfig(String monsterId, List<AbstractCard> cards) {
        System.out.println("警告：此方法已废弃，所有怪物都使用统一卡牌池");
    }

    /**
     * 移除指定怪物的配置（已废弃，统一使用卡牌池）
     */
    public void removeConfig(String monsterId) {
        System.out.println("警告：此方法已废弃，所有怪物都使用统一卡牌池");
    }
}
