# Downfall卡牌移植方案

## 概述

本文档详细说明了如何将Downfall模组的卡牌移植到MonsterPlayCard系统中，实现预处理而非实时处理，确保系统不依赖Downfall模组运行。

## 移植策略

### 1. 预处理方式

**目标**：将Downfall卡牌完全移植到我们的系统中，作为原生卡牌使用。

**方法**：
- 分析Downfall卡牌的源代码
- 提取卡牌的核心逻辑和属性
- 重新实现为我们的卡牌类
- 集成到现有的卡牌系统中

### 2. 卡牌分类移植

根据Downfall卡牌的特点，分为以下几类进行移植：

#### 2.1 怪物专用卡牌
- **特点**：只有怪物可以使用的卡牌
- **移植重点**：保持原有效果，适配我们的怪物系统
- **示例**：各种怪物特有的攻击、技能、能力卡牌

#### 2.2 通用卡牌
- **特点**：玩家和怪物都可以使用的卡牌
- **移植重点**：确保在双方使用时效果一致
- **示例**：基础攻击、防御、技能卡牌

#### 2.3 特殊机制卡牌
- **特点**：具有独特机制的卡牌
- **移植重点**：重新实现特殊机制，确保兼容性
- **示例**：与Downfall特定机制相关的卡牌

## 技术实现方案

### 1. 卡牌分析工具

创建一个卡牌分析工具，用于分析Downfall卡牌的结构和逻辑：

```java
package EveryMonsterPlayCard.downfall.analyzer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Downfall卡牌分析器
 * 用于分析Downfall卡牌的结构和逻辑
 */
public class DownfallCardAnalyzer {
    
    /**
     * 分析卡牌属性
     * @param cardClass 卡牌类
     * @return 卡牌属性映射
     */
    public Map<String, Object> analyzeCardProperties(Class<? extends AbstractCard> cardClass) {
        Map<String, Object> properties = new HashMap<>();
        
        // 分析基本属性
        analyzeBasicProperties(cardClass, properties);
        
        // 分析方法
        analyzeMethods(cardClass, properties);
        
        // 分析字段
        analyzeFields(cardClass, properties);
        
        // 分析注解
        analyzeAnnotations(cardClass, properties);
        
        return properties;
    }
    
    /**
     * 分析卡牌使用逻辑
     * @param cardClass 卡牌类
     * @return 使用逻辑描述
     */
    public String analyzeCardLogic(Class<? extends AbstractCard> cardClass) {
        StringBuilder logic = new StringBuilder();
        
        // 分析use方法
        analyzeUseMethod(cardClass, logic);
        
        // 分析upgrade方法
        analyzeUpgradeMethod(cardClass, logic);
        
        // 分析其他关键方法
        analyzeOtherMethods(cardClass, logic);
        
        return logic.toString();
    }
    
    /**
     * 生成移植报告
     * @param cardClass 卡牌类
     * @return 移植报告
     */
    public MigrationReport generateMigrationReport(Class<? extends AbstractCard> cardClass) {
        MigrationReport report = new MigrationReport();
        
        // 基本信息
        report.setCardName(getCardName(cardClass));
        report.setCardId(getCardId(cardClass));
        
        // 属性分析
        report.setProperties(analyzeCardProperties(cardClass));
        
        // 逻辑分析
        report.setLogic(analyzeCardLogic(cardClass));
        
        // 依赖分析
        report.setDependencies(analyzeDependencies(cardClass));
        
        // 移植复杂度评估
        report.setComplexity(assessComplexity(cardClass));
        
        return report;
    }
}
```

### 2. 卡牌移植模板

创建标准化的卡牌移植模板：

```java
package EveryMonsterPlayCard.downfall.template;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import EveryMonsterPlayCard.monstercards.cards.AbstractMonsterCard;

/**
 * Downfall卡牌移植模板
 * 提供标准化的移植基础类
 */
public abstract class DownfallCardTemplate extends AbstractMonsterCard {
    
    // 原始Downfall卡牌信息
    protected String originalCardId;
    protected String originalModId;
    protected String originalDescription;
    
    public DownfallCardTemplate(String id, String name, String cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, cost, description, type, color, rarity, target);
    }
    
    /**
     * 初始化原始卡牌信息
     * @param originalId 原始卡牌ID
     * @param modId 原始模组ID
     * @param description 原始描述
     */
    protected void initOriginalInfo(String originalId, String modId, String description) {
        this.originalCardId = originalId;
        this.originalModId = modId;
        this.originalDescription = description;
    }
    
    /**
     * 移植卡牌效果
     * 子类实现具体的移植逻辑
     */
    protected abstract void migrateCardEffect();
    
    /**
     * 验证移植结果
     * @return 验证是否成功
     */
    protected abstract boolean validateMigration();
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 调用移植后的效果
        useMigratedEffect(p, m);
    }
    
    /**
     * 使用移植后的效果
     * 子类实现具体的使用逻辑
     */
    protected abstract void useMigratedEffect(AbstractPlayer p, AbstractMonster m);
    
    @Override
    public void upgrade() {
        // 调用移植后的升级逻辑
        upgradeMigrated();
    }
    
    /**
     * 移植后的升级逻辑
     * 子类实现具体的升级逻辑
     */
    protected abstract void upgradeMigrated();
    
    /**
     * 获取移植信息
     * @return 移植信息
     */
    public MigrationInfo getMigrationInfo() {
        MigrationInfo info = new MigrationInfo();
        info.setOriginalCardId(originalCardId);
        info.setOriginalModId(originalModId);
        info.setMigrationDate(System.currentTimeMillis());
        info.setMigrationVersion("1.0.0");
        return info;
    }
}
```

### 3. 自动化移植工具

创建自动化移植工具，提高移植效率：

```java
package EveryMonsterPlayCard.downfall.migrator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 自动化卡牌移植工具
 */
public class AutoCardMigrator {
    
    private DownfallCardAnalyzer analyzer;
    private CardCodeGenerator codeGenerator;
    private String outputDirectory;
    
    public AutoCardMigrator(String outputDirectory) {
        this.analyzer = new DownfallCardAnalyzer();
        this.codeGenerator = new CardCodeGenerator();
        this.outputDirectory = outputDirectory;
    }
    
    /**
     * 批量移植卡牌
     * @param cardClasses 卡牌类列表
     */
    public void migrateCards(List<Class<? extends AbstractCard>> cardClasses) {
        for (Class<? extends AbstractCard> cardClass : cardClasses) {
            try {
                migrateCard(cardClass);
            } catch (Exception e) {
                System.err.println("Failed to migrate card: " + cardClass.getSimpleName());
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 移植单个卡牌
     * @param cardClass 卡牌类
     */
    public void migrateCard(Class<? extends AbstractCard> cardClass) {
        // 分析卡牌
        MigrationReport report = analyzer.generateMigrationReport(cardClass);
        
        // 生成移植代码
        String migratedCode = codeGenerator.generateMigratedCode(report);
        
        // 保存移植后的代码
        saveMigratedCode(report.getCardId(), migratedCode);
        
        // 生成移植报告
        saveMigrationReport(report.getCardId(), report);
        
        System.out.println("Successfully migrated card: " + report.getCardName());
    }
    
    /**
     * 保存移植后的代码
     * @param cardId 卡牌ID
     * @param code 移植代码
     */
    private void saveMigratedCode(String cardId, String code) {
        try {
            String fileName = "Migrated" + cardId + ".java";
            Path filePath = Paths.get(outputDirectory, fileName);
            Files.write(filePath, code.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save migrated code for card: " + cardId);
            e.printStackTrace();
        }
    }
    
    /**
     * 保存移植报告
     * @param cardId 卡牌ID
     * @param report 移植报告
     */
    private void saveMigrationReport(String cardId, MigrationReport report) {
        try {
            String fileName = cardId + "_MigrationReport.json";
            Path filePath = Paths.get(outputDirectory, "reports", fileName);
            Files.createDirectories(filePath.getParent());
            
            String jsonReport = convertToJson(report);
            Files.write(filePath, jsonReport.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save migration report for card: " + cardId);
            e.printStackTrace();
        }
    }
}
```

### 4. 卡牌验证系统

创建卡牌验证系统，确保移植后的卡牌功能正确：

```java
package EveryMonsterPlayCard.downfall.validator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.List;

/**
 * 卡牌验证器
 * 验证移植后的卡牌功能是否正确
 */
public class CardValidator {
    
    /**
     * 验证卡牌基本属性
     * @param card 卡牌对象
     * @return 验证结果
     */
    public ValidationResult validateBasicProperties(AbstractCard card) {
        ValidationResult result = new ValidationResult();
        
        // 验证卡牌ID
        if (card.cardID == null || card.cardID.isEmpty()) {
            result.addError("Card ID is null or empty");
        }
        
        // 验证卡牌名称
        if (card.name == null || card.name.isEmpty()) {
            result.addError("Card name is null or empty");
        }
        
        // 验证卡牌费用
        if (card.cost < 0 && !card.freeToPlay()) {
            result.addError("Invalid card cost: " + card.cost);
        }
        
        // 验证卡牌类型
        if (card.type == null) {
            result.addError("Card type is null");
        }
        
        return result;
    }
    
    /**
     * 验证卡牌使用逻辑
     * @param card 卡牌对象
     * @return 验证结果
     */
    public ValidationResult validateUseLogic(AbstractCard card) {
        ValidationResult result = new ValidationResult();
        
        try {
            // 创建测试环境
            TestEnvironment env = createTestEnvironment();
            AbstractPlayer player = env.getPlayer();
            AbstractMonster monster = env.getMonster();
            
            // 测试卡牌使用
            card.use(player, monster);
            
            // 验证使用结果
            validateUseResult(card, env, result);
            
        } catch (Exception e) {
            result.addError("Exception during card use: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 验证卡牌升级逻辑
     * @param card 卡牌对象
     * @return 验证结果
     */
    public ValidationResult validateUpgradeLogic(AbstractCard card) {
        ValidationResult result = new ValidationResult();
        
        try {
            // 记录升级前的状态
            CardState beforeUpgrade = captureCardState(card);
            
            // 升级卡牌
            if (!card.upgraded) {
                card.upgrade();
            }
            
            // 记录升级后的状态
            CardState afterUpgrade = captureCardState(card);
            
            // 验证升级效果
            validateUpgradeResult(beforeUpgrade, afterUpgrade, result);
            
        } catch (Exception e) {
            result.addError("Exception during card upgrade: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 综合验证卡牌
     * @param card 卡牌对象
     * @return 综合验证结果
     */
    public ComprehensiveValidationResult validateCard(AbstractCard card) {
        ComprehensiveValidationResult result = new ComprehensiveValidationResult();
        
        // 验证基本属性
        result.setBasicPropertiesValidation(validateBasicProperties(card));
        
        // 验证使用逻辑
        result.setUseLogicValidation(validateUseLogic(card));
        
        // 验证升级逻辑
        result.setUpgradeLogicValidation(validateUpgradeLogic(card));
        
        // 计算综合评分
        result.calculateOverallScore();
        
        return result;
    }
}
```

## 移植流程

### 1. 准备阶段

1. **获取Downfall源代码**
   - 下载Downfall模组的源代码
   - 分析项目结构和依赖关系

2. **环境搭建**
   - 设置Downfall开发环境
   - 配置必要的依赖和工具

3. **卡牌清单整理**
   - 列出所有需要移植的卡牌
   - 按照类型和复杂度分类

### 2. 分析阶段

1. **卡牌分析**
   - 使用分析工具分析每个卡牌
   - 生成分析报告

2. **依赖分析**
   - 识别卡牌的外部依赖
   - 确定需要重新实现的组件

3. **复杂度评估**
   - 评估每个卡牌的移植复杂度
   - 制定移植优先级

### 3. 移植阶段

1. **批量移植**
   - 使用自动化工具进行初步移植
   - 生成基础代码框架

2. **手动调整**
   - 对复杂卡牌进行手动调整
   - 修复自动化工具无法处理的问题

3. **集成测试**
   - 将移植后的卡牌集成到系统中
   - 进行基本功能测试

### 4. 验证阶段

1. **功能验证**
   - 验证每个卡牌的功能正确性
   - 确保与原始卡牌效果一致

2. **平衡性调整**
   - 根据我们的系统调整卡牌平衡性
   - 确保卡牌在怪物系统中的合理性

3. **性能测试**
   - 测试移植后卡牌的性能表现
   - 优化性能瓶颈

## 质量保证

### 1. 代码审查

- 所有移植后的卡牌代码都需要经过审查
- 确保代码质量和一致性

### 2. 自动化测试

- 为每个移植的卡牌创建单元测试
- 建立自动化测试流程

### 3. 持续集成

- 将移植过程集成到CI/CD流程中
- 确保移植质量的可追溯性

## 维护策略

### 1. 版本管理

- 建立移植卡牌的版本管理机制
- 跟踪原始Downfall卡牌的更新

### 2. 更新机制

- 定期检查Downfall模组的更新
- 及时更新移植的卡牌

### 3. 反馈收集

- 收集玩家对移植卡牌的反馈
- 持续改进移植质量

## 总结

通过这个预处理移植方案，我们可以将Downfall卡牌完全集成到我们的系统中，而不依赖Downfall模组运行。自动化工具和验证系统确保了移植的效率和质量，为后续的功能扩展提供了坚实的基础。