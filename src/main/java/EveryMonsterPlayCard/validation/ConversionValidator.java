package EveryMonsterPlayCard.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.data.ValidationResult;

/**
 * 转换验证器
 * 负责验证动作到意图的转换结果
 */
public class ConversionValidator {
    private final ActionAnalyzer actionAnalyzer;
    private final UniversalActionConverter converter;
    private final Map<String, ValidationRule> validationRules;
    private final ValidationStatistics statistics;
    
    public ConversionValidator() {
        this.actionAnalyzer = new ActionAnalyzer();
        this.converter = new UniversalActionConverter();
        this.validationRules = new HashMap<>();
        this.statistics = new ValidationStatistics();
        initializeDefaultRules();
    }
    
    /**
     * 初始化默认验证规则
     */
    private void initializeDefaultRules() {
        // 基本验证规则
        addValidationRule("BASIC_VALIDITY", new BasicValidityRule());
        
        // 类型一致性验证规则
        addValidationRule("TYPE_CONSISTENCY", new TypeConsistencyRule());
        
        // 强度合理性验证规则
        addValidationRule("STRENGTH_REASONABILITY", new StrengthReasonabilityRule());
        
        // 源目标一致性验证规则
        addValidationRule("SOURCE_TARGET_CONSISTENCY", new SourceTargetConsistencyRule());
        
        // 参数完整性验证规则
        addValidationRule("PARAMETER_COMPLETENESS", new ParameterCompletenessRule());
        
        // 优先级合理性验证规则
        addValidationRule("PRIORITY_REASONABILITY", new PriorityReasonabilityRule());
    }
    
    /**
     * 验证单个转换结果
     * @param action 原始动作
     * @param intent 转换后的意图
     * @return 验证结果
     */
    public ValidationResult validateConversion(AbstractGameAction action, MonsterIntent intent) {
        if (action == null || intent == null) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("动作或意图为空");
            statistics.recordValidation(false, 0);
            return result;
        }
        
        long startTime = System.currentTimeMillis();
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        
        try {
            // 应用所有验证规则
            for (ValidationRule rule : validationRules.values()) {
                RuleResult ruleResult = rule.validate(action, intent);
                
                if (!ruleResult.isValid()) {
                    errors.addAll(ruleResult.getErrors());
                }
                warnings.addAll(ruleResult.getWarnings());
            }
            
            // 创建验证结果
            ValidationResult result;
            if (errors.isEmpty()) {
                result = new ValidationResult(true);
                result.setMessage("转换验证通过");
            } else {
                result = new ValidationResult(false);
                result.setMessage("转换验证失败");
                for (String error : errors) {
                    result.addError(error);
                }
            }
            
            // 添加警告
            for (String warning : warnings) {
                result.addWarning(warning);
            }
            
            // 设置验证时间
            result.setValidationTime(System.currentTimeMillis() - startTime);
            
            // 记录统计信息
            statistics.recordValidation(errors.isEmpty(), System.currentTimeMillis() - startTime);
            
            return result;
            
        } catch (Exception e) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("验证过程中发生异常: " + e.getMessage());
            result.setValidationTime(System.currentTimeMillis() - startTime);
            statistics.recordValidation(false, System.currentTimeMillis() - startTime);
            return result;
        }
    }
    
    /**
     * 验证多个转换结果
     * @param actions 原始动作列表
     * @param intents 转换后的意图列表
     * @return 验证结果
     */
    public ValidationResult validateConversions(List<AbstractGameAction> actions, List<MonsterIntent> intents) {
        if (actions == null || intents == null) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("动作列表或意图列表为空");
            statistics.recordValidation(false, 0);
            return result;
        }
        
        if (actions.size() != intents.size()) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("动作数量与意图数量不匹配");
            statistics.recordValidation(false, 0);
            return result;
        }
        
        long startTime = System.currentTimeMillis();
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        
        try {
            // 验证每个转换
            for (int i = 0; i < actions.size(); i++) {
                ValidationResult singleResult = validateConversion(actions.get(i), intents.get(i));
                
                if (!singleResult.isValid()) {
                    errors.add("动作 " + i + " 转换验证失败: " + String.join(", ", singleResult.getErrors()));
                }
                
                warnings.addAll(singleResult.getWarnings());
            }
            
            // 验证整体一致性
            ValidationResult consistencyResult = validateOverallConsistency(actions, intents);
            if (!consistencyResult.isValid()) {
                errors.addAll(consistencyResult.getErrors());
            }
            warnings.addAll(consistencyResult.getWarnings());
            
            // 创建验证结果
            ValidationResult result;
            if (errors.isEmpty()) {
                result = new ValidationResult(true);
                result.setMessage("批量转换验证通过");
            } else {
                result = new ValidationResult(false);
                result.setMessage("批量转换验证失败");
                for (String error : errors) {
                    result.addError(error);
                }
            }
            
            // 添加警告
            for (String warning : warnings) {
                result.addWarning(warning);
            }
            
            // 设置验证时间
            result.setValidationTime(System.currentTimeMillis() - startTime);
            
            // 记录统计信息
            statistics.recordValidation(errors.isEmpty(), System.currentTimeMillis() - startTime);
            
            return result;
            
        } catch (Exception e) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("批量验证过程中发生异常: " + e.getMessage());
            result.setValidationTime(System.currentTimeMillis() - startTime);
            statistics.recordValidation(false, System.currentTimeMillis() - startTime);
            return result;
        }
    }
    
    /**
     * 验证整体一致性
     * @param actions 动作列表
     * @param intents 意图列表
     * @return 验证结果
     */
    private ValidationResult validateOverallConsistency(List<AbstractGameAction> actions, List<MonsterIntent> intents) {
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        
        try {
            // 检查优先级一致性
            boolean hasPriorityInconsistency = false;
            for (int i = 0; i < intents.size() - 1; i++) {
                for (int j = i + 1; j < intents.size(); j++) {
                    MonsterIntent intent1 = intents.get(i);
                    MonsterIntent intent2 = intents.get(j);
                    
                    // 检查相同类型意图的优先级是否合理
                    if (intent1.getType() == intent2.getType() && 
                        Math.abs(intent1.getMetadata().getPriority() - intent2.getMetadata().getPriority()) > 2) {
                        hasPriorityInconsistency = true;
                        warnings.add("相同类型意图的优先级差异过大: " + 
                                   intent1.getType() + " (" + intent1.getMetadata().getPriority() + 
                                   ") vs " + intent2.getMetadata().getPriority());
                    }
                }
            }
            
            if (hasPriorityInconsistency) {
                warnings.add("检测到优先级不一致，建议检查优先级分配逻辑");
            }
            
            // 检查源目标一致性
            boolean hasSourceTargetInconsistency = false;
            for (int i = 0; i < actions.size(); i++) {
                AbstractGameAction action = actions.get(i);
                MonsterIntent intent = intents.get(i);
                
                if (action.source != null && intent.getSource() != null && 
                    !action.source.equals(intent.getSource())) {
                    hasSourceTargetInconsistency = true;
                    errors.add("动作 " + i + " 的源与意图的源不匹配");
                }
                
                if (action.target != null && intent.getTarget() != null && 
                    !action.target.equals(intent.getTarget())) {
                    hasSourceTargetInconsistency = true;
                    errors.add("动作 " + i + " 的目标与意图的目标不匹配");
                }
            }
            
            if (hasSourceTargetInconsistency) {
                errors.add("检测到源目标不一致");
            }
            
            // 创建验证结果
            ValidationResult result;
            if (errors.isEmpty()) {
                result = new ValidationResult(true);
                result.setMessage("整体一致性验证通过");
            } else {
                result = new ValidationResult(false);
                result.setMessage("整体一致性验证失败");
                for (String error : errors) {
                    result.addError(error);
                }
            }
            
            // 添加警告
            for (String warning : warnings) {
                result.addWarning(warning);
            }
            
            return result;
            
        } catch (Exception e) {
            ValidationResult result = new ValidationResult(false);
            result.setMessage("整体一致性验证过程中发生异常: " + e.getMessage());
            return result;
        }
    }
    
    /**
     * 添加验证规则
     * @param ruleId 规则ID
     * @param rule 验证规则
     */
    public void addValidationRule(String ruleId, ValidationRule rule) {
        validationRules.put(ruleId, rule);
    }
    
    /**
     * 移除验证规则
     * @param ruleId 规则ID
     * @return 是否成功移除
     */
    public boolean removeValidationRule(String ruleId) {
        return validationRules.remove(ruleId) != null;
    }
    
    /**
     * 清空所有验证规则
     */
    public void clearValidationRules() {
        validationRules.clear();
    }
    
    /**
     * 获取验证统计信息
     * @return 验证统计信息
     */
    public ValidationStatistics getStatistics() {
        return statistics;
    }
    
    /**
     * 重置验证器状态
     */
    public void reset() {
        statistics.reset();
    }
    
    /**
     * 验证规则接口
     */
    public interface ValidationRule {
        /**
         * 验证规则
         * @param action 原始动作
         * @param intent 转换后的意图
         * @return 规则验证结果
         */
        RuleResult validate(AbstractGameAction action, MonsterIntent intent);
        
        /**
         * 获取规则名称
         * @return 规则名称
         */
        String getRuleName();
        
        /**
         * 获取规则描述
         * @return 规则描述
         */
        String getRuleDescription();
    }
    
    /**
     * 规则验证结果
     */
    public static class RuleResult {
        private boolean valid;
        private List<String> errors;
        private List<String> warnings;
        
        public RuleResult(boolean valid) {
            this.valid = valid;
            this.errors = new ArrayList<>();
            this.warnings = new ArrayList<>();
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public void setValid(boolean valid) {
            this.valid = valid;
        }
        
        public List<String> getErrors() {
            return errors;
        }
        
        public void addError(String error) {
            this.errors.add(error);
            this.valid = false;
        }
        
        public List<String> getWarnings() {
            return warnings;
        }
        
        public void addWarning(String warning) {
            this.warnings.add(warning);
        }
    }
    
    /**
     * 基本有效性验证规则
     */
    private static class BasicValidityRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 检查意图基本属性
            if (intent.getType() == null) {
                result.addError("意图类型为空");
            }
            
            if (intent.getAmount() < 0) {
                result.addError("意图数量为负数");
            }
            
            // 检查元数据
            if (intent.getMetadata() == null) {
                result.addError("意图元数据为空");
            } else {
                if (intent.getMetadata().getIntentId() == null || intent.getMetadata().getIntentId().isEmpty()) {
                    result.addWarning("意图ID为空");
                }
                
                if (intent.getMetadata().getDescription() == null || intent.getMetadata().getDescription().isEmpty()) {
                    result.addWarning("意图描述为空");
                }
            }
            
            return result;
        }
        
        @Override
        public String getRuleName() {
            return "基本有效性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证意图的基本属性是否有效";
        }
    }
    
    /**
     * 类型一致性验证规则
     */
    private static class TypeConsistencyRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 预测动作的意图类型
            IntentType expectedType = new ActionAnalyzer().predictIntentType(action);
            
            // 检查类型一致性
            if (expectedType != IntentType.UNKNOWN && intent.getType() != expectedType) {
                result.addWarning("意图类型与动作预测类型不一致: 预期 " + expectedType + ", 实际 " + intent.getType());
            }
            
            return result;
        }
        
        @Override
        public String getRuleName() {
            return "类型一致性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证意图类型与动作类型的一致性";
        }
    }
    
    /**
     * 强度合理性验证规则
     */
    private static class StrengthReasonabilityRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 计算预期强度
            int expectedStrength = new ActionAnalyzer().calculateActionStrength(action);
            int actualStrength = intent.getAmount();
            
            // 检查强度差异
            double differenceRatio = Math.abs(expectedStrength - actualStrength) / (double) Math.max(1, expectedStrength);
            
            if (differenceRatio > 0.5) {
                result.addError("意图强度与动作强度差异过大: 预期 " + expectedStrength + ", 实际 " + actualStrength);
            } else if (differenceRatio > 0.3) {
                result.addWarning("意图强度与动作强度差异较大: 预期 " + expectedStrength + ", 实际 " + actualStrength);
            }
            
            return result;
        }
        
        @Override
        public String getRuleName() {
            return "强度合理性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证意图强度与动作强度的合理性";
        }
    }
    
    /**
     * 源目标一致性验证规则
     */
    private static class SourceTargetConsistencyRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 检查源一致性
            if (action.source != null && intent.getSource() != null && 
                !action.source.equals(intent.getSource())) {
                result.addError("动作源与意图源不匹配");
            }
            
            // 检查目标一致性
            if (action.target != null && intent.getTarget() != null && 
                !action.target.equals(intent.getTarget())) {
                result.addError("动作目标与意图目标不匹配");
            }
            
            return result;
        }
        
        @Override
        public String getRuleName() {
            return "源目标一致性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证动作的源目标与意图的源目标一致性";
        }
    }
    
    /**
     * 参数完整性验证规则
     */
    private static class ParameterCompletenessRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 检查意图属性
            if (intent.getProperties() == null || intent.getProperties().isEmpty()) {
                result.addWarning("意图缺少属性信息");
            }
            
            // 检查组件动作
            if (intent.getComponentActions() == null || intent.getComponentActions().isEmpty()) {
                result.addWarning("意图缺少组件动作信息");
            }
            
            return result;
        }
        
        @Override
        public String getRuleName() {
            return "参数完整性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证意图参数的完整性";
        }
    }
    
    /**
     * 优先级合理性验证规则
     */
    private static class PriorityReasonabilityRule implements ValidationRule {
        @Override
        public RuleResult validate(AbstractGameAction action, MonsterIntent intent) {
            RuleResult result = new RuleResult(true);
            
            // 检查优先级范围
            int priority = intent.getMetadata().getPriority();
            if (priority < 0 || priority > 10) {
                result.addWarning("意图优先级超出合理范围 (0-10): " + priority);
            }
            
            // 检查优先级与意图类型的一致性
            IntentType type = intent.getType();
            int expectedPriority = getExpectedPriority(type);
            
            if (Math.abs(priority - expectedPriority) > 3) {
                result.addWarning("意图优先级与类型预期差异较大: 预期 " + expectedPriority + ", 实际 " + priority);
            }
            
            return result;
        }
        
        private int getExpectedPriority(IntentType type) {
            switch (type) {
                case ATTACK:
                case STRONG:
                    return 5;
                case DEFEND:
                    return 3;
                case BUFF:
                case DEBUFF:
                    return 4;
                default:
                    return 2;
            }
        }
        
        @Override
        public String getRuleName() {
            return "优先级合理性验证";
        }
        
        @Override
        public String getRuleDescription() {
            return "验证意图优先级的合理性";
        }
    }
    
    /**
     * 验证统计信息
     */
    public static class ValidationStatistics {
        private int totalValidations;
        private int successfulValidations;
        private int failedValidations;
        private long totalValidationTime;
        
        public ValidationStatistics() {
            this.totalValidations = 0;
            this.successfulValidations = 0;
            this.failedValidations = 0;
            this.totalValidationTime = 0;
        }
        
        public int getTotalValidations() {
            return totalValidations;
        }
        
        public int getSuccessfulValidations() {
            return successfulValidations;
        }
        
        public int getFailedValidations() {
            return failedValidations;
        }
        
        public long getTotalValidationTime() {
            return totalValidationTime;
        }
        
        public double getSuccessRate() {
            return totalValidations > 0 ? (double) successfulValidations / totalValidations : 0.0;
        }
        
        public double getAverageValidationTime() {
            return totalValidations > 0 ? (double) totalValidationTime / totalValidations : 0.0;
        }
        
        public void recordValidation(boolean success, long validationTime) {
            totalValidations++;
            if (success) {
                successfulValidations++;
            } else {
                failedValidations++;
            }
            totalValidationTime += validationTime;
        }
        
        public void reset() {
            totalValidations = 0;
            successfulValidations = 0;
            failedValidations = 0;
            totalValidationTime = 0;
        }
        
        @Override
        public String toString() {
            return "ValidationStatistics{" +
                    "totalValidations=" + totalValidations +
                    ", successfulValidations=" + successfulValidations +
                    ", failedValidations=" + failedValidations +
                    ", successRate=" + String.format("%.2f%%", getSuccessRate() * 100) +
                    ", averageValidationTime=" + String.format("%.2fms", getAverageValidationTime()) +
                    '}';
        }
    }
}