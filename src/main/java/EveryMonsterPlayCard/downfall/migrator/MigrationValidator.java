package EveryMonsterPlayCard.downfall.migrator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.cards.monster.AbstractMonsterCard;
import EveryMonsterPlayCard.core.data.ValidationResult;
import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.LogicAnalysisResult;

/**
 * 移植验证器
 * 验证移植后的卡牌和生成的代码
 */
public class MigrationValidator {
    
    private List<String> validationRules;
    private boolean strictMode;
    
    public MigrationValidator() {
        this.validationRules = new ArrayList<>();
        this.strictMode = false;
        initializeValidationRules();
    }
    
    /**
     * 初始化验证规则
     */
    private void initializeValidationRules() {
        // 基础语法验证规则
        validationRules.add("class_declaration");
        validationRules.add("method_declaration");
        validationRules.add("import_statement");
        validationRules.add("package_declaration");
        
        // 卡牌特定验证规则
        validationRules.add("required_methods");
        validationRules.add("card_attributes");
        validationRules.add("inheritance_check");
        
        // 逻辑验证规则
        validationRules.add("use_method_logic");
        validationRules.add("upgrade_method_logic");
        validationRules.add("balance_check");
    }
    
    /**
     * 验证生成的代码
     */
    public ValidationResult validateCode(String code) {
        ValidationResult result = new ValidationResult();
        result.setValid(true);
        
        try {
            // 基础语法验证
            validateBasicSyntax(code, result);
            
            // 卡牌结构验证
            validateCardStructure(code, result);
            
            // 逻辑验证
            validateCardLogic(code, result);
            
            // 平衡性验证
            validateCardBalance(code, result);
            
            // 依赖验证
            validateDependencies(code, result);
            
        } catch (Exception e) {
            result.setValid(false);
            result.addError("验证过程中发生错误: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 验证卡牌
     */
    public ValidationResult validateCard(AbstractCard card) {
        ValidationResult result = new ValidationResult();
        result.setCard(card);
        result.setValid(true);
        
        try {
            // 基础属性验证
            validateBasicAttributes(card, result);
            
            // 方法验证
            validateRequiredMethods(card, result);
            
            // 数值验证
            validateCardValues(card, result);
            
            // 兼容性验证
            validateCompatibility(card, result);
            
        } catch (Exception e) {
            result.setValid(false);
            result.addError("卡牌验证过程中发生错误: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 验证分析结果
     */
    public ValidationResult validateAnalysisResults(CardAnalysisResult cardAnalysis,
                                             LogicAnalysisResult logicAnalysis,
                                             DependencyAnalysisResult dependencyAnalysis) {
        ValidationResult result = new ValidationResult();
        result.setValid(true);
        
        // 验证卡牌分析结果
        if (cardAnalysis != null) {
            validateCardAnalysisResult(cardAnalysis, result);
        } else {
            result.addError("卡牌分析结果为空");
            result.setValid(false);
        }
        
        // 验证逻辑分析结果
        if (logicAnalysis != null) {
            validateLogicAnalysisResult(logicAnalysis, result);
        }
        
        // 验证依赖分析结果
        if (dependencyAnalysis != null) {
            validateDependencyAnalysisResult(dependencyAnalysis, result);
        }
        
        return result;
    }
    
    /**
     * 基础语法验证
     */
    private void validateBasicSyntax(String code, ValidationResult result) {
        // 检查包声明
        if (!code.contains("package ")) {
            result.addError("缺少包声明");
            result.setValid(false);
        }
        
        // 检查类声明
        Pattern classPattern = Pattern.compile("public\\s+class\\s+\\w+\\s*extends\\s+\\w+");
        Matcher classMatcher = classPattern.matcher(code);
        if (!classMatcher.find()) {
            result.addError("缺少正确的类声明");
            result.setValid(false);
        }
        
        // 检查导入语句
        if (!code.contains("import ")) {
            result.addWarning("缺少导入语句");
        }
        
        // 检查大括号匹配
        if (!validateBraceMatching(code)) {
            result.addError("大括号不匹配");
            result.setValid(false);
        }
    }
    
    /**
     * 验证卡牌结构
     */
    private void validateCardStructure(String code, ValidationResult result) {
        // 检查必需的方法
        String[] requiredMethods = {"use", "upgrade", "makeCopy"};
        for (String method : requiredMethods) {
            if (!code.contains("public void " + method + "(") && 
                !code.contains("public AbstractCard " + method + "(")) {
                result.addError("缺少必需的方法: " + method);
                result.setValid(false);
            }
        }
        
        // 检查继承关系
        if (!code.contains("extends AbstractMonsterCard")) {
            result.addWarning("建议继承AbstractMonsterCard");
        }
        
        // 检查字段声明
        if (!code.contains("CardStrings")) {
            result.addWarning("缺少CardStrings字段");
        }
    }
    
    /**
     * 验证卡牌逻辑
     */
    private void validateCardLogic(String code, ValidationResult result) {
        // 检查use方法中的常见错误
        if (code.contains("use(AbstractPlayer p, AbstractMonster m)")) {
            // 检查是否有空方法体
            Pattern usePattern = Pattern.compile("public\\s+void\\s+use\\([^)]*\\)\\s*\\{\\s*\\}");
            Matcher useMatcher = usePattern.matcher(code);
            if (useMatcher.find()) {
                result.addWarning("use方法体为空");
            }
            
            // 检查是否有TODO注释
            if (code.contains("TODO")) {
                result.addWarning("代码中包含TODO注释，需要完善实现");
            }
        }
        
        // 检查upgrade方法逻辑
        if (code.contains("upgrade()")) {
            if (!code.contains("upgradeName()")) {
                result.addError("upgrade方法缺少upgradeName()调用");
                result.setValid(false);
            }
        }
    }
    
    /**
     * 验证卡牌平衡性
     */
    private void validateCardBalance(String code, ValidationResult result) {
        // 检查伤害值
        Pattern damagePattern = Pattern.compile("baseDamage\\s*=\\s*(\\d+)");
        Matcher damageMatcher = damagePattern.matcher(code);
        if (damageMatcher.find()) {
            int damage = Integer.parseInt(damageMatcher.group(1));
            if (damage > 30) {
                result.addWarning("伤害值过高: " + damage);
            }
            if (damage < 0) {
                result.addError("伤害值不能为负数");
                result.setValid(false);
            }
        }
        
        // 检查费用
        Pattern costPattern = Pattern.compile("cost\\s*=\\s*(-?\\d+)");
        Matcher costMatcher = costPattern.matcher(code);
        if (costMatcher.find()) {
            int cost = Integer.parseInt(costMatcher.group(1));
            if (cost < -2) {
                result.addError("费用过低: " + cost);
                result.setValid(false);
            }
            if (cost > 10) {
                result.addWarning("费用过高: " + cost);
            }
        }
    }
    
    /**
     * 验证依赖
     */
    private void validateDependencies(String code, ValidationResult result) {
        // 检查是否有未解析的导入
        Pattern importPattern = Pattern.compile("import\\s+([^;]+);");
        Matcher importMatcher = importPattern.matcher(code);
        
        while (importMatcher.find()) {
            String importClass = importMatcher.group(1);
            if (importClass.contains("downfall") && !importClass.contains("Charboss")) {
                result.addWarning("可能包含未移植的Downfall依赖: " + importClass);
            }
        }
    }
    
    /**
     * 验证基础属性
     */
    private void validateBasicAttributes(AbstractCard card, ValidationResult result) {
        if (card.cardID == null || card.cardID.isEmpty()) {
            result.addError("卡牌ID为空");
            result.setValid(false);
        }
        
        if (card.name == null || card.name.isEmpty()) {
            result.addError("卡牌名称为空");
            result.setValid(false);
        }
        
        if (card.type == null) {
            result.addError("卡牌类型为空");
            result.setValid(false);
        }
        
        if (card.color == null) {
            result.addError("卡牌颜色为空");
            result.setValid(false);
        }
    }
    
    /**
     * 验证必需方法
     */
    private void validateRequiredMethods(AbstractCard card, ValidationResult result) {
        try {
            // 检查use方法
            card.getClass().getMethod("use", 
                com.megacrit.cardcrawl.characters.AbstractPlayer.class,
                com.megacrit.cardcrawl.monsters.AbstractMonster.class);
        } catch (NoSuchMethodException e) {
            result.addError("缺少use方法");
            result.setValid(false);
        }
        
        try {
            // 检查upgrade方法
            card.getClass().getMethod("upgrade");
        } catch (NoSuchMethodException e) {
            result.addError("缺少upgrade方法");
            result.setValid(false);
        }
        
        try {
            // 检查makeCopy方法
            card.getClass().getMethod("makeCopy");
        } catch (NoSuchMethodException e) {
            result.addError("缺少makeCopy方法");
            result.setValid(false);
        }
    }
    
    /**
     * 验证卡牌数值
     */
    private void validateCardValues(AbstractCard card, ValidationResult result) {
        if (card.baseDamage < 0) {
            result.addError("基础伤害不能为负数");
            result.setValid(false);
        }
        
        if (card.baseBlock < 0) {
            result.addError("基础格挡不能为负数");
            result.setValid(false);
        }
        
        if (card.cost < -2) {
            result.addError("费用不能小于-2");
            result.setValid(false);
        }
        
        if (card.baseMagicNumber < 0) {
            result.addWarning("魔法数值为负数");
        }
    }
    
    /**
     * 验证兼容性
     */
    private void validateCompatibility(AbstractCard card, ValidationResult result) {
        // 检查是否继承自正确的基类
        if (!AbstractMonsterCard.class.isAssignableFrom(card.getClass())) {
            result.addWarning("建议继承AbstractMonsterCard");
        }
        
        // 检查是否有未实现的依赖
        if (card.cardID != null && card.cardID.contains("downfall")) {
            result.addWarning("可能包含未移植的Downfall依赖");
        }
    }
    
    /**
     * 验证卡牌分析结果
     */
    private void validateCardAnalysisResult(CardAnalysisResult cardAnalysis, ValidationResult result) {
        if (cardAnalysis.getCardId() == null || cardAnalysis.getCardId().isEmpty()) {
            result.addError("卡牌分析结果中ID为空");
            result.setValid(false);
        }
        
        if (cardAnalysis.getCardType() == null) {
            result.addError("卡牌分析结果中类型为空");
            result.setValid(false);
        }
    }
    
    /**
     * 验证逻辑分析结果
     */
    private void validateLogicAnalysisResult(LogicAnalysisResult logicAnalysis, ValidationResult result) {
        // 逻辑分析结果的验证逻辑
        if (logicAnalysis.getActionClasses() != null && logicAnalysis.getActionClasses().size() > 10) {
            result.addWarning("动作类依赖过多");
        }
    }
    
    /**
     * 验证依赖分析结果
     */
    private void validateDependencyAnalysisResult(DependencyAnalysisResult dependencyAnalysis, ValidationResult result) {
        if (dependencyAnalysis.getRequiredPowers() != null && 
            dependencyAnalysis.getRequiredPowers().size() > 5) {
            result.addWarning("能力类依赖过多");
        }
    }
    
    /**
     * 验证大括号匹配
     */
    private boolean validateBraceMatching(String code) {
        int braceCount = 0;
        for (char c : code.toCharArray()) {
            if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
                if (braceCount < 0) {
                    return false;
                }
            }
        }
        return braceCount == 0;
    }
    
    /**
     * 设置严格模式
     */
    public void setStrictMode(boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    /**
     * 添加验证规则
     */
    public void addValidationRule(String rule) {
        validationRules.add(rule);
    }
    
    /**
     * 移除验证规则
     */
    public void removeValidationRule(String rule) {
        validationRules.remove(rule);
    }
    
    /**
     * 获取验证规则列表
     */
    public List<String> getValidationRules() {
        return new ArrayList<>(validationRules);
    }
}