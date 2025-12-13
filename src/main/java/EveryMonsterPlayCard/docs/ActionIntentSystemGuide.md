# 动作-意图转换系统使用指南

## 概述

动作-意图转换系统是MonsterPlayCard项目的核心组件，负责将游戏中的动作转换为怪物的意图表示。该系统提供了完整的动作分析、意图映射、转换验证和性能优化功能。

## 系统架构

### 核心组件

1. **动作分析器** (`conversion.analyzer`)
   - `ActionAnalyzer` - 主要的动作分析器
   - `CardActionExtractor` - 从卡牌中提取动作
   - `ActionEffectAnalyzer` - 分析动作效果
   - `ActionParameterExtractor` - 提取动作参数
   - `Simulated*Action` - 模拟动作类

2. **意图映射器** (`conversion.mapper`)
   - `ActionToIntentMapper` - 动作到意图的映射器
   - `IntentMappingRule` - 映射规则定义
   - `IntentMappingRules` - 映射规则管理
   - `ComplexActionHandler` - 复杂动作处理器
   - `IntentPriorityResolver` - 意图优先级解析器

3. **通用转换器** (`converter`)
   - `UniversalActionConverter` - 实现了`IActionToIntentConverter`接口的通用转换器

4. **转换验证系统** (`validation`)
   - `ConversionValidator` - 验证转换结果的正确性

5. **服务层** (`service`)
   - `ActionConversionService` - 提供统一的转换服务接口
   - `ActionConversionServiceFactory` - 管理服务的创建和配置

6. **配置系统** (`config`)
   - `ConversionConfigManager` - 管理转换系统的各种配置选项

7. **集成系统** (`integration`)
   - `ActionIntentIntegrationManager` - 集成管理器
   - `ActionIntentIntegrationPatch` - 集成补丁
   - `ActionIntentConfig` - 集成配置

8. **性能优化** (`optimization`)
   - `PerformanceOptimizer` - 性能优化器

9. **测试系统** (`tests`)
   - `ActionConversionTest` - 功能测试
   - `PerformanceTest` - 性能测试
   - `IntegrationTest` - 集成测试
   - `TestSuite` - 测试套件

## 使用方法

### 基本转换

```java
// 创建转换器
UniversalActionConverter converter = new UniversalActionConverter();

// 转换单个动作
SimulatedDamageAction damageAction = new SimulatedDamageAction(10);
MonsterIntent intent = converter.convertAction(damageAction);

// 转换动作序列
List<AbstractGameAction> actions = new ArrayList<>();
actions.add(new SimulatedDamageAction(8));
actions.add(new SimulatedBlockAction(5));
List<MonsterIntent> intents = converter.convertActionSequence(actions);

// 转换卡牌动作
AbstractCard card = new Strike_Red();
List<MonsterIntent> cardIntents = converter.convertCardActions(card);
```

### 使用服务层

```java
// 获取默认服务
ActionConversionService service = ActionConversionServiceFactory.getDefaultService();

// 转换动作
ActionConversionService.ServiceResult<MonsterIntent> result = service.convertAction(action);
if (result.isSuccess()) {
    MonsterIntent intent = result.getResult();
} else {
    String error = result.getErrorMessage();
}

// 获取统计信息
ActionConversionService.ServiceStatistics stats = service.getStatistics();
```

### 验证转换结果

```java
// 创建验证器
ConversionValidator validator = new ConversionValidator();

// 验证转换
ValidationResult result = validator.validateConversion(action, intent);
if (result.isValid()) {
    // 转换有效
} else {
    // 处理错误
    for (String error : result.getErrors()) {
        System.out.println("错误: " + error);
    }
}
```

### 配置系统

```java
// 获取配置管理器
ConversionConfigManager config = ConversionConfigManager.getDefault();

// 设置配置
config.setConfig("analyzer.enableDetailedAnalysis", true);
config.setConfig("mapper.strictMode", false);

// 获取配置
boolean detailedAnalysis = config.getConfig("analyzer.enableDetailedAnalysis", false);

// 保存配置
config.saveConfiguration();
```

### 性能优化

```java
// 获取性能优化器
PerformanceOptimizer optimizer = PerformanceOptimizer.getInstance();

// 优化的转换
MonsterIntent intent = optimizer.optimizedConvertAction(action);

// 获取性能统计
String stats = optimizer.getPerformanceStatistics();
System.out.println(stats);
```

## 集成到现有系统

### 自动集成

系统已经通过补丁自动集成到MonsterPlayCard项目中：

1. **模块初始化** - 在`everyMonsterPlayCard`类初始化时自动启用
2. **卡牌执行** - 在怪物执行卡牌时自动转换动作为意图
3. **回合开始** - 在怪物回合开始时预测意图
4. **手牌更新** - 在怪物手牌更新时分析卡牌行为

### 手动集成

如果需要手动控制集成过程：

```java
// 获取集成管理器
ActionIntentIntegrationManager integration = ActionIntentIntegrationManager.getInstance();

// 处理怪物卡牌
integration.processMonsterCard(monster, card);

// 预测下一步意图
MonsterIntent nextIntent = integration.predictNextIntent(monster);

// 分析卡牌行为
String behavior = integration.analyzeCardBehavior(card);

// 获取转换统计
String stats = integration.getConversionStatistics();
```

## 配置选项

### 转换系统配置

- `enableActionIntentConversion` - 启用动作-意图转换
- `enableIntentPrediction` - 启用意图预测
- `enableDetailedLogging` - 启用详细日志
- `enablePerformanceMonitoring` - 启用性能监控
- `enableAutoValidation` - 启用自动验证
- `maxIntentDisplayCount` - 最大意图显示数量
- `intentUpdateInterval` - 意图更新间隔

### 性能优化配置

- `enableCaching` - 启用缓存
- `enableBatchProcessing` - 启用批量处理
- `enableAsyncProcessing` - 启用异步处理
- `maxCacheSize` - 最大缓存大小
- `cacheExpirationTime` - 缓存过期时间

## 测试

### 运行测试

```bash
# 运行所有测试
java EveryMonsterPlayCard.tests.TestSuite

# 运行功能测试
java EveryMonsterPlayCard.tests.TestSuite functional

# 运行性能测试
java EveryMonsterPlayCard.tests.TestSuite performance

# 运行集成测试
java EveryMonsterPlayCard.tests.TestSuite integration
```

### 测试内容

1. **功能测试**
   - 单个动作转换
   - 动作序列转换
   - 卡牌动作转换
   - 转换验证
   - 服务层功能

2. **性能测试**
   - 转换速度测试
   - 序列转换测试
   - 卡牌转换测试
   - 内存使用测试
   - 服务层性能

3. **集成测试**
   - 完整流程测试
   - 复杂卡牌测试
   - 多动作序列测试
   - 配置系统测试
   - 事件系统测试
   - 错误处理测试

## 最佳实践

### 性能优化

1. **启用缓存** - 对于重复的动作，使用缓存可以显著提高性能
2. **批量处理** - 对于多个动作，使用批量处理而不是逐个处理
3. **异步处理** - 对于大量转换，考虑使用异步处理
4. **定期清理** - 定期清理过期缓存，避免内存泄漏

### 错误处理

1. **验证输入** - 在转换前验证动作的有效性
2. **处理异常** - 捕获并处理转换过程中的异常
3. **提供回退** - 当转换失败时，提供合理的回退机制
4. **记录错误** - 记录详细的错误信息，便于调试

### 扩展性

1. **自定义规则** - 通过`IntentMappingRule`添加自定义映射规则
2. **自定义分析器** - 实现`IActionAnalyzer`接口添加自定义分析器
3. **自定义验证器** - 实现`IConversionValidator`接口添加自定义验证器
4. **事件监听** - 通过事件系统监听转换事件，添加自定义处理

## 故障排除

### 常见问题

1. **转换失败**
   - 检查动作是否有效
   - 确认转换器是否正确初始化
   - 查看错误日志获取详细信息

2. **性能问题**
   - 启用缓存
   - 减少不必要的转换
   - 使用批量处理

3. **集成问题**
   - 确认补丁是否正确加载
   - 检查配置是否正确设置
   - 验证事件系统是否正常工作

### 调试技巧

1. **启用详细日志** - 设置`enableDetailedLogging`为true
2. **使用测试套件** - 运行完整的测试套件
3. **检查性能统计** - 查看性能优化器的统计信息
4. **验证配置** - 确认所有配置选项都正确设置

## 版本历史

- v1.0 - 初始版本，包含基本的动作转换功能
- v1.1 - 添加性能优化和缓存系统
- v1.2 - 添加完整的测试套件
- v1.3 - 添加集成系统和自动补丁
- v1.4 - 添加配置管理和性能监控

## 贡献指南

如果您想为动作-意图转换系统做出贡献：

1. **代码风格** - 遵循项目的代码风格约定
2. **测试覆盖** - 为新功能添加相应的测试
3. **文档更新** - 更新相关文档
4. **性能考虑** - 考虑新功能对性能的影响
5. **向后兼容** - 确保新功能不会破坏现有功能

## 许可证

本系统遵循与MonsterPlayCard项目相同的许可证。