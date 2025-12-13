package EveryMonsterPlayCard.core.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MigrationResult;
import EveryMonsterPlayCard.core.data.ValidationResult;

/**
 * 卡牌移植器接口
 * 负责将外部模组的卡牌移植到我们的系统中
 */
public interface ICardMigrator {
    /**
     * 移植卡牌
     * @param originalCard 原始卡牌
     * @return 移植结果
     */
    MigrationResult migrateCard(AbstractCard originalCard);
    
    /**
     * 验证移植结果
     * @param migratedCard 移植后的卡牌
     * @return 验证结果
     */
    ValidationResult validateMigration(AbstractCard migratedCard);
    
    /**
     * 调整卡牌平衡性
     * @param card 卡牌对象
     * @return 是否调整成功
     */
    boolean adjustCardBalance(AbstractCard card);
    
    /**
     * 获取支持的模组ID列表
     * @return 支持的模组ID列表
     */
    String[] getSupportedModIds();
    
    /**
     * 检查卡牌是否可以移植
     * @param card 卡牌对象
     * @return 是否可以移植
     */
    boolean canMigrate(AbstractCard card);
}