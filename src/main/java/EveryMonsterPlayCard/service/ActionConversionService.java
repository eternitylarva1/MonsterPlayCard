package EveryMonsterPlayCard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.data.ValidationResult;
import EveryMonsterPlayCard.validation.ConversionValidator;

/**
 * 动作转换服务
 * 提供统一的动作到意图转换服务接口
 */
public class ActionConversionService {
    private final ActionAnalyzer actionAnalyzer;
    private final UniversalActionConverter converter;
    private final ConversionValidator validator;
    private final Map<String, Object> serviceConfig;
    private final ServiceStatistics statistics;
    
    public ActionConversionService() {
        this.actionAnalyzer = new ActionAnalyzer();
        this.converter = new UniversalActionConverter();
        this.validator = new ConversionValidator();
        this.serviceConfig = new HashMap<>();
        this.statistics = new ServiceStatistics();
        initializeDefaultConfig();
    }
    
    /**
     * 初始化默认配置
     */
    private void initializeDefaultConfig() {
        serviceConfig.put("enableValidation", true);
        serviceConfig.put("enableStatistics", true);
        serviceConfig.put("enableLogging", false);
        serviceConfig.put("maxRetries", 3);
        serviceConfig.put("timeoutMs", 5000);
    }
    
    /**
     * 转换单个动作为意图
     * @param action 游戏动作
     * @return 转换结果
     */
    public ServiceResult<MonsterIntent> convertAction(AbstractGameAction action) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 检查输入
            if (action == null) {
                return ServiceResult.failure("动作不能为空");
            }
            
            // 检查是否支持该动作类型
            if (!converter.canHandle(action)) {
                return ServiceResult.failure("不支持的动作类型: " + action.getClass().getSimpleName());
            }
            
            // 执行转换
            MonsterIntent intent = converter.convertAction(action);
            
            if (intent == null) {
                return ServiceResult.failure("转换失败，无法生成意图");
            }
            
            // 验证转换结果
            if (isValidationEnabled()) {
                ValidationResult validationResult = validator.validateConversion(action, intent);
                if (!validationResult.isValid()) {
                    return ServiceResult.failure("转换验证失败: " + String.join(", ", validationResult.getErrors()));
                }
            }
            
            // 记录统计信息
            if (isStatisticsEnabled()) {
                statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.success(intent);
            
        } catch (Exception e) {
            if (isStatisticsEnabled()) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.failure("转换过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 转换动作序列为意图列表
     * @param actions 动作序列
     * @return 转换结果
     */
    public ServiceResult<List<MonsterIntent>> convertActionSequence(List<AbstractGameAction> actions) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 检查输入
            if (actions == null || actions.isEmpty()) {
                return ServiceResult.failure("动作序列不能为空");
            }
            
            // 检查是否支持所有动作类型
            for (AbstractGameAction action : actions) {
                if (!converter.canHandle(action)) {
                    return ServiceResult.failure("不支持的动作类型: " + action.getClass().getSimpleName());
                }
            }
            
            // 执行转换
            List<MonsterIntent> intents = converter.convertActionSequence(actions);
            
            if (intents == null || intents.isEmpty()) {
                return ServiceResult.failure("转换失败，无法生成意图列表");
            }
            
            // 验证转换结果
            if (isValidationEnabled()) {
                ValidationResult validationResult = validator.validateConversions(actions, intents);
                if (!validationResult.isValid()) {
                    return ServiceResult.failure("转换验证失败: " + String.join(", ", validationResult.getErrors()));
                }
            }
            
            // 记录统计信息
            if (isStatisticsEnabled()) {
                statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.success(intents);
            
        } catch (Exception e) {
            if (isStatisticsEnabled()) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.failure("转换过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 转换卡牌动作为意图列表
     * @param card 卡牌
     * @return 转换结果
     */
    public ServiceResult<List<MonsterIntent>> convertCardActions(AbstractCard card) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 检查输入
            if (card == null) {
                return ServiceResult.failure("卡牌不能为空");
            }
            
            // 执行转换
            List<MonsterIntent> intents = converter.convertCardActions(card);
            
            if (intents == null || intents.isEmpty()) {
                return ServiceResult.failure("转换失败，无法从卡牌生成意图列表");
            }
            
            // 记录统计信息
            if (isStatisticsEnabled()) {
                statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.success(intents);
            
        } catch (Exception e) {
            if (isStatisticsEnabled()) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.failure("转换过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 获取最高优先级意图
     * @param actions 动作序列
     * @return 转换结果
     */
    public ServiceResult<MonsterIntent> getHighestPriorityIntent(List<AbstractGameAction> actions) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 检查输入
            if (actions == null || actions.isEmpty()) {
                return ServiceResult.failure("动作序列不能为空");
            }
            
            // 执行转换
            MonsterIntent intent = converter.getHighestPriorityIntent(actions);
            
            if (intent == null) {
                return ServiceResult.failure("转换失败，无法确定最高优先级意图");
            }
            
            // 记录统计信息
            if (isStatisticsEnabled()) {
                statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.success(intent);
            
        } catch (Exception e) {
            if (isStatisticsEnabled()) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            }
            
            return ServiceResult.failure("转换过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 验证转换结果
     * @param action 原始动作
     * @param intent 转换后的意图
     * @return 验证结果
     */
    public ServiceResult<Boolean> validateConversion(AbstractGameAction action, MonsterIntent intent) {
        try {
            // 检查输入
            if (action == null || intent == null) {
                return ServiceResult.failure("动作或意图不能为空");
            }
            
            // 执行验证
            ValidationResult validationResult = validator.validateConversion(action, intent);
            
            return ServiceResult.success(validationResult.isValid());
            
        } catch (Exception e) {
            return ServiceResult.failure("验证过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 验证批量转换结果
     * @param actions 原始动作列表
     * @param intents 转换后的意图列表
     * @return 验证结果
     */
    public ServiceResult<Boolean> validateConversions(List<AbstractGameAction> actions, List<MonsterIntent> intents) {
        try {
            // 检查输入
            if (actions == null || intents == null) {
                return ServiceResult.failure("动作列表或意图列表不能为空");
            }
            
            // 执行验证
            ValidationResult validationResult = validator.validateConversions(actions, intents);
            
            return ServiceResult.success(validationResult.isValid());
            
        } catch (Exception e) {
            return ServiceResult.failure("验证过程中发生异常: " + e.getMessage());
        }
    }
    
    /**
     * 获取服务配置
     * @param key 配置键
     * @return 配置值
     */
    @SuppressWarnings("unchecked")
    public <T> T getConfig(String key) {
        return (T) serviceConfig.get(key);
    }
    
    /**
     * 设置服务配置
     * @param key 配置键
     * @param value 配置值
     */
    public void setConfig(String key, Object value) {
        serviceConfig.put(key, value);
    }
    
    /**
     * 获取所有配置
     * @return 配置映射
     */
    public Map<String, Object> getAllConfig() {
        return new HashMap<>(serviceConfig);
    }
    
    /**
     * 重置配置为默认值
     */
    public void resetConfig() {
        serviceConfig.clear();
        initializeDefaultConfig();
    }
    
    /**
     * 检查是否启用验证
     * @return 是否启用验证
     */
    private boolean isValidationEnabled() {
        Boolean enabled = getConfig("enableValidation");
        return enabled != null ? enabled : true;
    }
    
    /**
     * 检查是否启用统计
     * @return 是否启用统计
     */
    private boolean isStatisticsEnabled() {
        Boolean enabled = getConfig("enableStatistics");
        return enabled != null ? enabled : true;
    }
    
    /**
     * 检查是否启用日志
     * @return 是否启用日志
     */
    private boolean isLoggingEnabled() {
        Boolean enabled = getConfig("enableLogging");
        return enabled != null ? enabled : false;
    }
    
    /**
     * 获取最大重试次数
     * @return 最大重试次数
     */
    private int getMaxRetries() {
        Integer retries = getConfig("maxRetries");
        return retries != null ? retries : 3;
    }
    
    /**
     * 获取超时时间
     * @return 超时时间（毫秒）
     */
    private long getTimeoutMs() {
        Long timeout = getConfig("timeoutMs");
        return timeout != null ? timeout : 5000L;
    }
    
    /**
     * 获取服务统计信息
     * @return 服务统计信息
     */
    public ServiceStatistics getStatistics() {
        return statistics;
    }
    
    /**
     * 重置服务状态
     */
    public void reset() {
        statistics.reset();
        converter.reset();
        validator.reset();
    }
    
    /**
     * 服务结果
     * @param <T> 结果类型
     */
    public static class ServiceResult<T> {
        private boolean success;
        private T result;
        private String errorMessage;
        private long timestamp;
        
        private ServiceResult(boolean success) {
            this.success = success;
            this.timestamp = System.currentTimeMillis();
        }
        
        public static <T> ServiceResult<T> success(T result) {
            ServiceResult<T> serviceResult = new ServiceResult<>(true);
            serviceResult.result = result;
            return serviceResult;
        }
        
        public static <T> ServiceResult<T> failure(String errorMessage) {
            ServiceResult<T> serviceResult = new ServiceResult<>(false);
            serviceResult.errorMessage = errorMessage;
            return serviceResult;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public T getResult() {
            return result;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public long getTimestamp() {
            return timestamp;
        }
        
        @Override
        public String toString() {
            if (success) {
                return "ServiceResult{success=true, result=" + result + "}";
            } else {
                return "ServiceResult{success=false, error='" + errorMessage + "'}";
            }
        }
    }
    
    /**
     * 服务统计信息
     */
    public static class ServiceStatistics {
        private int totalRequests;
        private int successfulRequests;
        private int failedRequests;
        private long totalProcessingTime;
        
        public ServiceStatistics() {
            this.totalRequests = 0;
            this.successfulRequests = 0;
            this.failedRequests = 0;
            this.totalProcessingTime = 0;
        }
        
        public int getTotalRequests() {
            return totalRequests;
        }
        
        public int getSuccessfulRequests() {
            return successfulRequests;
        }
        
        public int getFailedRequests() {
            return failedRequests;
        }
        
        public long getTotalProcessingTime() {
            return totalProcessingTime;
        }
        
        public double getSuccessRate() {
            return totalRequests > 0 ? (double) successfulRequests / totalRequests : 0.0;
        }
        
        public double getAverageProcessingTime() {
            return totalRequests > 0 ? (double) totalProcessingTime / totalRequests : 0.0;
        }
        
        public void recordConversion(boolean success, long processingTime) {
            totalRequests++;
            if (success) {
                successfulRequests++;
            } else {
                failedRequests++;
            }
            totalProcessingTime += processingTime;
        }
        
        public void reset() {
            totalRequests = 0;
            successfulRequests = 0;
            failedRequests = 0;
            totalProcessingTime = 0;
        }
        
        @Override
        public String toString() {
            return "ServiceStatistics{" +
                    "totalRequests=" + totalRequests +
                    ", successfulRequests=" + successfulRequests +
                    ", failedRequests=" + failedRequests +
                    ", successRate=" + String.format("%.2f%%", getSuccessRate() * 100) +
                    ", averageProcessingTime=" + String.format("%.2fms", getAverageProcessingTime()) +
                    '}';
        }
    }
}