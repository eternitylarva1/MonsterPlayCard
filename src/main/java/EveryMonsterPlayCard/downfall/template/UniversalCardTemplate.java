package EveryMonsterPlayCard.downfall.template;

import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.LogicAnalysisResult;

/**
 * 通用卡牌移植模板
 * 适用于各种类型的卡牌移植，提供更灵活的移植选项
 */
public class UniversalCardTemplate extends DownfallCardTemplate {
    
    private String customTemplate;
    private boolean useAdvancedLogic;
    
    public UniversalCardTemplate(CardAnalysisResult cardAnalysis, 
                              LogicAnalysisResult logicAnalysis,
                              DependencyAnalysisResult dependencyAnalysis) {
        super(cardAnalysis, logicAnalysis, dependencyAnalysis);
        this.useAdvancedLogic = false;
    }
    
    public UniversalCardTemplate(CardAnalysisResult cardAnalysis, 
                              LogicAnalysisResult logicAnalysis,
                              DependencyAnalysisResult dependencyAnalysis,
                              boolean useAdvancedLogic) {
        super(cardAnalysis, logicAnalysis, dependencyAnalysis);
        this.useAdvancedLogic = useAdvancedLogic;
    }
    
    @Override
    public String generateCardClass() {
        if (customTemplate != null && !customTemplate.isEmpty()) {
            return generateFromCustomTemplate();
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 类注释
        sb.append(generateClassComment());
        
        // 包声明和导入
        sb.append(generatePackageAndImports());
        
        // 类声明
        sb.append(generateClassDeclaration());
        
        // 字段
        sb.append(generateFields());
        
        // 构造函数
        sb.append(generateConstructor());
        
        // use方法
        sb.append(generateUseMethod());
        
        // upgrade方法
        sb.append(generateUpgradeMethod());
        
        // makeCopy方法
        sb.append(generateMakeCopyMethod());
        
        // 额外方法（如果需要）
        sb.append(generateAdditionalMethods());
        
        // 类结束
        sb.append("}\n");
        
        return sb.toString();
    }
    
    @Override
    protected String generateUseMethod() {
        if (useAdvancedLogic) {
            return generateAdvancedUseMethod();
        } else {
            return generateBasicUseMethod();
        }
    }
    
    /**
     * 生成基础use方法
     */
    private String generateBasicUseMethod() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("    public void use(AbstractPlayer p, AbstractMonster m) {\n");
        sb.append("        // 基础卡牌逻辑\n");
        
        // 根据卡牌属性生成基础逻辑
        if (cardAnalysis.getBaseDamage() > 0) {
            sb.append("        // 造成伤害\n");
            sb.append("        addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));\n");
        }
        
        if (cardAnalysis.getBaseBlock() > 0) {
            sb.append("        // 获得格挡\n");
            sb.append("        // TODO: 实现格挡逻辑\n");
        }
        
        if (cardAnalysis.getBaseMagicNumber() > 0) {
            sb.append("        // 魔法效果\n");
            sb.append("        // TODO: 实现魔法逻辑\n");
        }
        
        sb.append("    }\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成高级use方法
     */
    private String generateAdvancedUseMethod() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("    public void use(AbstractPlayer p, AbstractMonster m) {\n");
        sb.append("        // 高级卡牌逻辑\n");
        
        // 根据逻辑分析结果生成更精确的逻辑
        if (logicAnalysis != null) {
            if (logicAnalysis.getActionClasses() != null && !logicAnalysis.getActionClasses().isEmpty()) {
                sb.append("        // 执行动作\n");
                for (String actionClass : logicAnalysis.getActionClasses()) {
                    sb.append("        // TODO: 实现动作: ").append(actionClass).append("\n");
                }
            }
            
            if (logicAnalysis.getPowerClasses() != null && !logicAnalysis.getPowerClasses().isEmpty()) {
                sb.append("        // 施加能力\n");
                for (String powerClass : logicAnalysis.getPowerClasses()) {
                    sb.append("        // TODO: 实现能力: ").append(powerClass).append("\n");
                }
            }
            
            if (logicAnalysis.getEffectDescriptions() != null && !logicAnalysis.getEffectDescriptions().isEmpty()) {
                sb.append("        // 特殊效果\n");
                for (String effect : logicAnalysis.getEffectDescriptions()) {
                    sb.append("        // ").append(effect).append("\n");
                }
            }
        }
        
        sb.append("    }\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成额外方法
     */
    protected String generateAdditionalMethods() {
        StringBuilder sb = new StringBuilder();
        
        // 根据逻辑分析结果生成额外方法
        if (logicAnalysis != null) {
            if (logicAnalysis.hasOnDraw()) {
                sb.append(generateOnDrawMethod());
            }
            
            if (logicAnalysis.hasOnDiscard()) {
                sb.append(generateOnDiscardMethod());
            }
            
            if (logicAnalysis.hasOnExhaust()) {
                sb.append(generateOnExhaustMethod());
            }
            
            if (logicAnalysis.hasOnRetain()) {
                sb.append(generateOnRetainMethod());
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 生成onDraw方法
     */
    private String generateOnDrawMethod() {
        return "    public void onDraw() {\n" +
               "        // 抽牌时触发\n" +
               "        // TODO: 实现抽牌逻辑\n" +
               "    }\n\n";
    }
    
    /**
     * 生成onDiscard方法
     */
    private String generateOnDiscardMethod() {
        return "    public void onDiscard() {\n" +
               "        // 弃牌时触发\n" +
               "        // TODO: 实现弃牌逻辑\n" +
               "    }\n\n";
    }
    
    /**
     * 生成onExhaust方法
     */
    private String generateOnExhaustMethod() {
        return "    public void onExhaust() {\n" +
               "        // 消耗时触发\n" +
               "        // TODO: 实现消耗逻辑\n" +
               "    }\n\n";
    }
    
    /**
     * 生成onRetain方法
     */
    private String generateOnRetainMethod() {
        return "    public void onRetain() {\n" +
               "        // 保留时触发\n" +
               "        // TODO: 实现保留逻辑\n" +
               "    }\n\n";
    }
    
    /**
     * 从自定义模板生成
     */
    private String generateFromCustomTemplate() {
        String result = customTemplate;
        
        // 替换模板变量
        if (cardAnalysis != null) {
            result = result.replace("${CARD_ID}", cardAnalysis.getCardId());
            result = result.replace("${CARD_NAME}", cardAnalysis.getCardName());
            result = result.replace("${COST}", String.valueOf(cardAnalysis.getCost()));
            result = result.replace("${BASE_DAMAGE}", String.valueOf(cardAnalysis.getBaseDamage()));
            result = result.replace("${BASE_BLOCK}", String.valueOf(cardAnalysis.getBaseBlock()));
            result = result.replace("${BASE_MAGIC}", String.valueOf(cardAnalysis.getBaseMagicNumber()));
            result = result.replace("${DESCRIPTION}", cardAnalysis.getDescription());
        }
        
        return result;
    }
    
    /**
     * 设置自定义模板
     */
    public void setCustomTemplate(String template) {
        this.customTemplate = template;
    }
    
    /**
     * 设置是否使用高级逻辑
     */
    public void setUseAdvancedLogic(boolean useAdvancedLogic) {
        this.useAdvancedLogic = useAdvancedLogic;
    }
    
    /**
     * 生成更详细的类注释
     */
    @Override
    protected String generateClassComment() {
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * ").append(cardAnalysis.getCardName()).append(" - 通用移植版\n");
        sb.append(" * 从Downfall模组移植的卡牌\n");
        sb.append(" * 原始ID: ").append(cardAnalysis.getCardId()).append("\n");
        sb.append(" * 卡牌类型: ").append(cardAnalysis.getCardType()).append("\n");
        sb.append(" * 卡牌颜色: ").append(cardAnalysis.getCardColor()).append("\n");
        sb.append(" * 移植模式: ").append(useAdvancedLogic ? "高级" : "基础").append("\n");
        
        if (dependencyAnalysis != null) {
            sb.append(" * 依赖数量: ").append(dependencyAnalysis.getRequiredClasses().size()).append("\n");
        }
        
        sb.append(" */\n");
        return sb.toString();
    }
    
    @Override
    protected void generateAdditionalImports(StringBuilder sb) {
        // 调用父类方法
        super.generateAdditionalImports(sb);
        
        // 根据需要添加额外导入
        if (useAdvancedLogic) {
            sb.append("import java.util.ArrayList;\n");
            sb.append("import java.util.List;\n");
        }
        
        // 根据逻辑分析添加特定导入
        if (logicAnalysis != null) {
            if (logicAnalysis.hasOnDraw() || logicAnalysis.hasOnDiscard() || 
                logicAnalysis.hasOnExhaust() || logicAnalysis.hasOnRetain()) {
                sb.append("import com.megacrit.cardcrawl.actions.utility.DrawCardAction;\n");
                sb.append("import com.megacrit.cardcrawl.actions.common.ExhaustAction;\n");
            }
        }
    }
}