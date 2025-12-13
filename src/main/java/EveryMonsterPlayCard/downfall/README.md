# Downfall卡牌移植系统

## 概述

Downfall卡牌移植系统是一个自动化的工具链，用于将Downfall模组的卡牌移植到MonsterPlayCard项目中，使其成为原生卡牌而不依赖Downfall模组。

## 系统架构

```
src/main/java/EveryMonsterPlayCard/downfall/
├── analyzer/          # 卡牌分析器
│   ├── DownfallCardAnalyzer.java      # 主分析器
│   ├── CardAnalysisResult.java         # 卡牌分析结果
│   ├── LogicAnalysisResult.java        # 逻辑分析结果
│   ├── DependencyAnalysisResult.java   # 依赖分析结果
│   ├── MigrationReport.java            # 移植报告
│   ├── CardLogicExtractor.java         # 卡牌逻辑提取器
│   └── DependencyAnalyzer.java         # 依赖分析器
├── template/          # 移植模板
│   ├── DownfallCardTemplate.java       # 基础模板
│   ├── MonsterCardTemplate.java        # 怪物卡牌模板
│   └── UniversalCardTemplate.java      # 通用模板
├── migrator/         # 移植工具
│   ├── AutoCardMigrator.java           # 自动移植器
│   ├── CardCodeGenerator.java          # 代码生成器
│   └── MigrationValidator.java         # 移植验证器
├── service/          # 服务层
│   ├── CardMigrationService.java       # 卡牌移植服务
│   ├── ResourceProcessor.java          # 资源处理器
│   └── BalanceAdjuster.java            # 平衡性调整器
├── config/           # 配置管理
│   ├── MigrationConfig.java            # 移植配置
│   └── DownfallCardList.java           # Downfall卡牌列表
└── DownfallMigrationSystemTest.java    # 测试类
```

## 核心功能

### 1. 卡牌分析器 (DownfallCardAnalyzer)

负责分析Downfall卡牌的属性、逻辑和依赖关系：

- **analyzeCardAttributes()**: 分析卡牌基本属性
- **analyzeCardLogic()**: 分析卡牌逻辑
- **analyzeDependencies()**: 分析依赖关系
- **generateMigrationReport()**: 生成移植报告

### 2. 移植模板系统

提供多种模板用于生成移植后的卡牌代码：

- **DownfallCardTemplate**: 基础模板，提供通用代码生成方法
- **MonsterCardTemplate**: 专门用于怪物攻击和技能卡牌
- **UniversalCardTemplate**: 通用模板，支持复杂卡牌和多种触发器

### 3. 自动移植工具

实现ICardMigrator接口，提供完整的移植功能：

- **migrateCard()**: 移植单张卡牌
- **migrateCards()**: 批量移植卡牌
- **adjustCardBalance()**: 调整卡牌平衡性
- **validateMigration()**: 验证移植结果

### 4. 服务层

提供高级服务和管理功能：

- **CardMigrationService**: 主要服务类，支持异步处理和缓存
- **ResourceProcessor**: 处理图片、本地化、音频等资源
- **BalanceAdjuster**: 根据规则调整卡牌平衡性

### 5. 配置管理

- **MigrationConfig**: 管理移植配置，支持属性文件和动态修改
- **DownfallCardList**: 管理已知的Downfall卡牌列表

## 使用方法

### 基本使用

```java
// 创建移植服务
CardMigrationService service = new CardMigrationService();

// 准备要移植的卡牌列表
List<AbstractCard> cardsToMigrate = new ArrayList<>();
cardsToMigrate.add(new DownfallCard1());
cardsToMigrate.add(new DownfallCard2());

// 执行移植
List<MigrationResult> results = service.migrateCards(cardsToMigrate);

// 处理结果
for (MigrationResult result : results) {
    if (result.isSuccess()) {
        System.out.println("卡牌 " + result.getOriginalCard().cardID + " 移植成功");
        // 获取移植后的卡牌
        AbstractCard migratedCard = result.getMigratedCard();
    } else {
        System.out.println("卡牌 " + result.getOriginalCard().cardID + " 移植失败: " + result.getMessage());
    }
}
```

### 异步处理

```java
// 启用异步处理
service.setEnableAsync(true);

// 移植将在后台线程执行
List<MigrationResult> results = service.migrateCards(cardsToMigrate);
```

### 配置管理

```java
// 获取配置实例
MigrationConfig config = MigrationConfig.getInstance();

// 修改配置
config.setEnableAutoMigration(true);
config.setEnableBalanceAdjustment(true);
config.setEnableResourceProcessing(true);

// 保存配置到文件
config.saveToFile();

// 重置为默认配置
config.resetToDefaults();
```

### 卡牌分析

```java
// 创建分析器
DownfallCardAnalyzer analyzer = new DownfallCardAnalyzer();

// 分析卡牌
MigrationReport report = analyzer.generateMigrationReport(downfallCardClass);

// 获取分析结果
CardAnalysisResult cardResult = report.getCardAnalysisResult();
LogicAnalysisResult logicResult = report.getLogicAnalysisResult();
DependencyAnalysisResult depResult = report.getDependencyAnalysisResult();

// 检查移植难度
int difficulty = report.getMigrationDifficulty();
```

## 配置选项

### MigrationConfig 配置项

- `enableAutoMigration`: 是否启用自动移植
- `enableBalanceAdjustment`: 是否启用平衡性调整
- `enableResourceProcessing`: 是否启用资源处理
- `enableValidation`: 是否启用验证
- `outputDirectory`: 输出目录
- `templateType`: 默认模板类型
- `balanceAdjustmentStrategy`: 平衡性调整策略

### 平衡性调整策略

- `CONSERVATIVE`: 保守策略，最小化调整
- `MODERATE`: 适度策略，平衡调整
- `AGGRESSIVE`: 激进策略，大幅调整

## 测试

运行测试类验证系统功能：

```bash
java EveryMonsterPlayCard.downfall.DownfallMigrationSystemTest
```

测试包括：
- 配置系统测试
- 卡牌列表测试
- 分析器测试
- 移植器测试
- 服务层测试
- 完整流程测试

## 扩展开发

### 添加新的移植模板

1. 继承 `DownfallCardTemplate` 类
2. 实现 `generateCardCode()` 方法
3. 在 `CardCodeGenerator` 中注册新模板

### 添加新的平衡性调整规则

1. 在 `BalanceAdjuster` 中添加新的调整方法
2. 在配置中添加新的策略选项
3. 更新平衡性调整逻辑

### 添加新的资源处理器

1. 实现 `ResourceProcessor` 的扩展方法
2. 在 `CardMigrationService` 中集成新的处理器
3. 更新配置选项

## 注意事项

1. **依赖关系**: 确保所有必要的依赖都已正确配置
2. **资源文件**: 移植过程中会处理图片、本地化等资源文件
3. **平衡性**: 移植后的卡牌可能需要平衡性调整
4. **测试验证**: 建议在移植后进行充分的测试验证
5. **备份**: 在执行大规模移植前建议备份原始代码

## 故障排除

### 常见问题

1. **编译错误**: 检查导入语句和类路径
2. **运行时错误**: 检查配置文件和资源路径
3. **移植失败**: 查看错误日志和验证结果
4. **性能问题**: 考虑启用缓存和异步处理

### 调试技巧

1. 启用详细日志记录
2. 使用测试类验证各个组件
3. 检查生成的代码是否符合预期
4. 验证资源文件是否正确复制

## 版本历史

- v1.0.0: 初始版本，实现基本的移植功能
- 支持卡牌分析、代码生成、资源处理
- 提供完整的测试套件

## 贡献

欢迎提交问题报告和功能请求。在提交代码前请确保：

1. 代码通过所有测试
2. 遵循项目的编码规范
3. 添加必要的文档和注释
4. 更新相关的配置文件

## 许可证

本项目遵循与MonsterPlayCard项目相同的许可证。