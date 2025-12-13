# 意图-卡牌转换系统

## 概述

意图-卡牌转换系统是MonsterPlayCard项目的核心组件，负责将怪物的意图转换为相应的卡牌。该系统提供了灵活、可配置的转换机制，支持各种意图类型和复合意图，并包含验证、优化和缓存功能。

## 系统架构

### 1. 意图分析器 (analyzer)
- **IntentAnalyzer**: 主要分析器，协调分析过程
- **IntentParameterExtractor**: 提取意图参数
- **IntentComplexityAnalyzer**: 分析意图复杂度
- **IntentPriorityAnalyzer**: 分析意图优先级
- **IntentAnalysisResult**: 存储分析结果
- **IntentParameters**: 管理提取的参数

### 2. 卡牌模板系统 (template)
- **CardTemplate**: 卡牌模板接口
- **AbstractCardTemplate**: 抽象模板基类
- **StandardCardTemplate**: 标准卡牌模板
- **AttackCardTemplate**: 攻击卡牌模板
- **SkillCardTemplate**: 技能卡牌模板
- **PowerCardTemplate**: 能力卡牌模板
- **CustomCardTemplate**: 自定义卡牌模板
- **CardTemplateEngine**: 模板引擎，管理所有模板

### 3. 卡牌生成引擎 (generator)
- **CardGenerator**: 主要生成器，实现IIntentToCardConverter
- **DynamicCardBuilder**: 动态构建卡牌
- **CardAttributeCalculator**: 计算卡牌属性
- **ValidationResult**: 存储验证结果

### 4. 映射规则系统 (mapper)
- **IntentToCardMapper**: 主要映射器
- **MappingRulesEngine**: 映射规则引擎
- **MappingRule**: 映射规则接口
- **DefaultMappingRules**: 默认映射规则
- **SpecialIntentHandler**: 处理特殊意图
- **CompositeIntentResolver**: 解析复合意图

### 5. 服务层 (service)
- **IntentToCardService**: 主要服务，提供统一接口
- **CardValidationService**: 卡牌验证服务
- **CardOptimizationService**: 卡牌优化服务
- **CardCacheService**: 卡牌缓存服务

### 6. 配置系统 (config)
- **CardGenerationConfig**: 卡牌生成配置
- **MappingRulesConfig**: 映射规则配置
- **TemplateConfig**: 模板配置

### 7. 集成系统 (integration)
- **IntentToCardIntegration**: 与现有系统的集成

### 8. 测试系统 (testing)
- **IntentToCardSystemTest**: 系统测试类

## 使用方法

### 基本使用

```java
// 获取意图-卡牌转换服务
IntentToCardService service = IntentToCardService.getInstance();

// 创建意图
MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, 8);

// 转换为卡牌
AbstractCard card = service.convertIntentToCard(intent);
```

### 高级使用

```java
// 配置服务
IntentToCardService.ServiceConfig config = new IntentToCardService.ServiceConfig();
config.enableCaching = true;
config.enableValidation = true;
config.enableOptimization = true;
service.configureService(config);

// 批量转换
List<MonsterIntent> intents = Arrays.asList(
    new MonsterIntent(IntentType.ATTACK, 8),
    new MonsterIntent(IntentType.DEFEND, 5),
    new MonsterIntent(IntentType.BUFF, 2)
);
Map<MonsterIntent, AbstractCard> results = service.convertIntentsToCards(intents);
```

### 复合意图使用

```java
// 创建复合意图
MonsterIntent compositeIntent = new MonsterIntent(IntentType.ATTACK, 8);
compositeIntent.setProperty("isComposite", true);
compositeIntent.setProperty("secondaryIntent", "DEFEND");
compositeIntent.setProperty("secondaryAmount", 5);

// 转换为卡牌
AbstractCard card = service.convertIntentToCard(compositeIntent);
```

### 集成使用

```java
// 获取集成实例
IntentToCardIntegration integration = IntentToCardIntegration.getInstance();

// 初始化集成
integration.initialize();

// 为怪物生成卡牌
AbstractMonster monster = ...; // 获取怪物实例
List<AbstractCard> cards = integration.generateCardsForMonster(monster);
```

## 配置选项

### 卡牌生成配置

```java
CardGenerationConfig config = CardGenerationConfig.getInstance();

// 基础配置
config.setEnableGeneration(true);
config.setEnableFallbackGeneration(true);
config.setEnableDynamicGeneration(true);

// 数值配置
config.setMinDamage(1);
config.setMaxDamage(50);
config.setMinBlock(1);
config.setMaxBlock(30);
config.setMinMagicNumber(1);
config.setMaxMagicNumber(20);

// 费用配置
config.setMinCost(0);
config.setMaxCost(3);
config.setCostEfficiencyRatio(0.8);

// 稀有度配置
config.setCommonRarityChance(0.6);
config.setUncommonRarityChance(0.3);
config.setRareRarityChance(0.1);
```

### 映射规则配置

```java
MappingRulesConfig config = MappingRulesConfig.getInstance();

// 基础配置
config.setEnableMapping(true);
config.setEnablePriorityMapping(true);
config.setEnableComplexityMapping(true);
config.setEnableSpecialMapping(true);

// 复合意图配置
config.setEnableCompositeMapping(true);
config.setCompositeMappingThreshold(0.7);

// 数值映射配置
config.setDamageMappingRatio(1.0);
config.setBlockMappingRatio(1.0);
config.setMagicNumberMappingRatio(1.0);
```

### 模板配置

```java
TemplateConfig config = TemplateConfig.getInstance();

// 基础配置
config.setEnableTemplates(true);
config.setEnableTemplateSelection(true);
config.setEnableTemplateCaching(true);

// 模板匹配配置
config.setTemplateMatchThreshold(0.6);
config.setEnableFuzzyMatching(true);
config.setFuzzyMatchThreshold(0.4);
```

## 测试

### 运行所有测试

```java
IntentToCardSystemTest.runAllTests();
```

### 测试特定怪物

```java
AbstractMonster monster = ...; // 获取怪物实例
IntentToCardSystemTest.testMonsterCardGeneration(monster);
```

### 性能测试

```java
IntentToCardSystemTest.testSystemPerformance();
```

## 扩展

### 添加新的卡牌模板

1. 实现`CardTemplate`接口或继承`AbstractCardTemplate`
2. 在`CardTemplateEngine`中注册新模板
3. 在`TemplateConfig`中添加配置

### 添加新的映射规则

1. 实现`MappingRule`接口
2. 在`MappingRulesEngine`中注册新规则
3. 在`MappingRulesConfig`中添加配置

### 添加新的意图类型

1. 在`IntentType`枚举中添加新类型
2. 在各个分析器中添加处理逻辑
3. 在模板中添加相应的处理

## 性能优化

### 缓存策略

系统使用多级缓存来提高性能：
1. 意图分析结果缓存
2. 卡牌生成结果缓存
3. 模板匹配结果缓存

### 批量处理

对于大量意图的转换，建议使用批量处理方法：
```java
Map<MonsterIntent, AbstractCard> results = service.convertIntentsToCards(intents);
```

### 预热缓存

在系统启动时预热缓存：
```java
List<MonsterIntent> commonIntents = ...; // 常见意图列表
service.warmupCache(commonIntents);
```

## 故障排除

### 常见问题

1. **卡牌生成失败**
   - 检查意图是否有效
   - 检查是否有合适的模板
   - 检查映射规则是否正确

2. **性能问题**
   - 启用缓存
   - 使用批量处理
   - 优化配置参数

3. **卡牌不平衡**
   - 启用验证和优化
   - 调整配置参数
   - 检查映射规则

### 调试

启用详细日志：
```java
// 在配置中启用详细日志
config.setEnableDebugLogging(true);
```

获取统计信息：
```java
Map<String, Object> stats = service.getServiceStatus();
Map<String, Object> cacheStats = service.getCacheStats();
Map<String, Object> validationStats = service.getValidationStats();
Map<String, Object> optimizationStats = service.getOptimizationStats();
```

## 版本历史

- v1.0.0: 初始版本，包含基本功能
- v1.1.0: 添加复合意图支持
- v1.2.0: 添加验证和优化系统
- v1.3.0: 添加缓存和性能优化
- v1.4.0: 添加集成系统和测试框架

## 贡献

欢迎贡献代码和反馈！请遵循以下步骤：

1. Fork项目
2. 创建功能分支
3. 提交更改
4. 创建Pull Request

## 许可证

本项目遵循MIT许可证。