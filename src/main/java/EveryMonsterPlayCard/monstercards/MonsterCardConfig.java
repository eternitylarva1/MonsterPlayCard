package EveryMonsterPlayCard.monstercards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.cards.monster.*;
import EveryMonsterPlayCard.utils.Hpr;

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

        // 添加从崩坠卡牌搬运过来的所有怪物卡牌

        // 第一批卡牌 (1-13)
        universalMonsterCards.add(new EnBash());
        universalMonsterCards.add(new EnBodySlam());
        universalMonsterCards.add(new EnBackstab());
        universalMonsterCards.add(new EnCarnage());
        universalMonsterCards.add(new EnCleave());
        universalMonsterCards.add(new EnClothesline());
        universalMonsterCards.add(new EnFeed());
        universalMonsterCards.add(new EnDefendRed());
        universalMonsterCards.add(new EnBlur());
        universalMonsterCards.add(new EnDisarm());
        universalMonsterCards.add(new EnEntrench());
        universalMonsterCards.add(new EnDemonForm());
        universalMonsterCards.add(new EnAnger());
        universalMonsterCards.add(new EnArmaments());
        universalMonsterCards.add(new EnBarricade());
        universalMonsterCards.add(new EnBloodletting());

        // 第二批卡牌 (16-20)
        universalMonsterCards.add(new EnDoubleTap());
        universalMonsterCards.add(new EnFlameBarrier());
        universalMonsterCards.add(new EnInflame());
        universalMonsterCards.add(new EnReaper());
        universalMonsterCards.add(new EnUppercut());

        // 第三批卡牌 (21-25)
        universalMonsterCards.add(new EnFeelNoPain());
        universalMonsterCards.add(new EnGhostlyArmor());
        universalMonsterCards.add(new EnHeadbutt());
        universalMonsterCards.add(new EnHeavyBlade());
        universalMonsterCards.add(new EnHemokinesis());

        // 第四批卡牌 (26-30)
        universalMonsterCards.add(new EnImmolate());
        universalMonsterCards.add(new EnImpervious());
        universalMonsterCards.add(new EnIntimidate());
        universalMonsterCards.add(new EnIronWave());
        universalMonsterCards.add(new EnLimitBreak());

        // 第五批卡牌 (31-35)
        universalMonsterCards.add(new EnMetallicize());
        universalMonsterCards.add(new EnPerfectedStrike());
        universalMonsterCards.add(new EnPowerThrough());
        universalMonsterCards.add(new EnPummel());
        universalMonsterCards.add(new EnFlex());

        // 第六批卡牌 (36-40)
        universalMonsterCards.add(new EnRampage());
        universalMonsterCards.add(new EnRecklessCharge());
        universalMonsterCards.add(new EnSecondWind());
        universalMonsterCards.add(new EnSeeingRed());
        universalMonsterCards.add(new EnSentinel());

        // 额外卡牌
        universalMonsterCards.add(new EnWound());

        // 清理掉null值，确保只添加有效的卡牌
        universalMonsterCards.removeIf(card -> card == null);

        // 洗牌以确保随机性
        Collections.shuffle(universalMonsterCards);

        Hpr.info("统一怪物卡牌池初始化完成，包含 " + universalMonsterCards.size() + " 张卡牌");

        // 调试信息：显示成功创建的卡牌
        Hpr.info("统一卡牌池包含的卡牌：");
        for (int i = 0; i < universalMonsterCards.size(); i++) {
            AbstractCard card = universalMonsterCards.get(i);
            Hpr.info("  [" + i + "] " + card.name + " (ID: " + card.cardID + ")");
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
        
        // 调试：打印配置的卡牌
        Hpr.info("怪物 " + monsterId + " 配置的卡牌列表：");
        for (int i = 0; i < monsterCards.size(); i++) {
            AbstractCard card = monsterCards.get(i);
            Hpr.info("  [" + i + "] " + card.name + " (ID: " + card.cardID + ")");
        }
        
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
