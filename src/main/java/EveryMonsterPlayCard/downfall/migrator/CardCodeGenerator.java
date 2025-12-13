package EveryMonsterPlayCard.downfall.migrator;

import java.util.HashMap;
import java.util.Map;

import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.LogicAnalysisResult;
import EveryMonsterPlayCard.downfall.template.DownfallCardTemplate;
import EveryMonsterPlayCard.downfall.template.MonsterCardTemplate;
import EveryMonsterPlayCard.downfall.template.UniversalCardTemplate;

/**
 * 卡牌代码生成器
 * 根据分析结果生成卡牌代码
 */
public class CardCodeGenerator {
    
    private Map<String, String> templates;
    private boolean useAdvancedGeneration;
    
    public CardCodeGenerator() {
        this.templates = new HashMap<>();
        this.useAdvancedGeneration = false;
        initializeTemplates();
    }
    
    /**
     * 初始化模板
     */
    private void initializeTemplates() {
        // 基础攻击卡牌模板
        templates.put("ATTACK_BASIC", 
            "public void use(AbstractPlayer p, AbstractMonster m) {\n" +
            "    addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));\n" +
            "}");
        
        // 基础技能卡牌模板
        templates.put("SKILL_BASIC",
            "public void use(AbstractPlayer p, AbstractMonster m) {\n" +
            "    // TODO: 实现技能逻辑\n" +
            "}");
        
        // 基础能力卡牌模板
        templates.put("POWER_BASIC",
            "public void use(AbstractPlayer p, AbstractMonster m) {\n" +
            "    addToBot(new ApplyPowerAction(m, m, new ${POWER_CLASS}(m, this.magicNumber)));\n" +
            "}");
    }
    
    /**
     * 生成卡牌代码
     */
    public String generateCode(CardAnalysisResult cardAnalysis, 
                           LogicAnalysisResult logicAnalysis,
                           DependencyAnalysisResult dependencyAnalysis) {
        
        DownfallCardTemplate template = createTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis);
        return template.generateCardClass();
    }
    
    /**
     * 生成卡牌代码（使用指定模板类型）
     */
    public String generateCode(CardAnalysisResult cardAnalysis, 
                           LogicAnalysisResult logicAnalysis,
                           DependencyAnalysisResult dependencyAnalysis,
                           String templateType) {
        
        DownfallCardTemplate template = createTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis, templateType);
        return template.generateCardClass();
    }
    
    /**
     * 创建模板
     */
    private DownfallCardTemplate createTemplate(CardAnalysisResult cardAnalysis, 
                                            LogicAnalysisResult logicAnalysis,
                                            DependencyAnalysisResult dependencyAnalysis) {
        
        // 根据卡牌类型和复杂度选择模板
        if (shouldUseMonsterTemplate(cardAnalysis, dependencyAnalysis)) {
            return new MonsterCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis);
        } else {
            return new UniversalCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis, useAdvancedGeneration);
        }
    }
    
    /**
     * 创建指定类型的模板
     */
    private DownfallCardTemplate createTemplate(CardAnalysisResult cardAnalysis, 
                                            LogicAnalysisResult logicAnalysis,
                                            DependencyAnalysisResult dependencyAnalysis,
                                            String templateType) {
        
        switch (templateType.toUpperCase()) {
            case "MONSTER":
                return new MonsterCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis);
            case "UNIVERSAL":
                return new UniversalCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis, useAdvancedGeneration);
            case "UNIVERSAL_ADVANCED":
                return new UniversalCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis, true);
            case "UNIVERSAL_BASIC":
                return new UniversalCardTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis, false);
            default:
                return createTemplate(cardAnalysis, logicAnalysis, dependencyAnalysis);
        }
    }
    
    /**
     * 判断是否应该使用怪物模板
     */
    private boolean shouldUseMonsterTemplate(CardAnalysisResult cardAnalysis, 
                                        DependencyAnalysisResult dependencyAnalysis) {
        
        // 如果是攻击或技能卡牌，且依赖较少，使用怪物模板
        if (cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK ||
            cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL) {
            
            // 检查依赖复杂度
            int complexity = dependencyAnalysis.getRequiredPowers().size() +
                           dependencyAnalysis.getRequiredActions().size() +
                           dependencyAnalysis.getRequiredRelics().size();
            
            return complexity <= 3; // 依赖较少时使用怪物模板
        }
        
        return false;
    }
    
    /**
     * 生成use方法代码
     */
    public String generateUseMethod(CardAnalysisResult cardAnalysis, LogicAnalysisResult logicAnalysis) {
        String templateKey = getTemplateKey(cardAnalysis);
        String template = templates.get(templateKey);
        
        if (template == null) {
            template = templates.get("SKILL_BASIC");
        }
        
        // 替换模板变量
        template = replaceTemplateVariables(template, cardAnalysis, logicAnalysis);
        
        return template;
    }
    
    /**
     * 获取模板键
     */
    private String getTemplateKey(CardAnalysisResult cardAnalysis) {
        String cardType = cardAnalysis.getCardType().toString();
        String baseKey = cardType + "_BASIC";
        
        // 根据卡牌属性调整模板键
        if (cardAnalysis.getBaseDamage() > 0 && cardAnalysis.getBaseBlock() > 0) {
            return cardType + "_HYBRID";
        }
        
        return baseKey;
    }
    
    /**
     * 替换模板变量
     */
    private String replaceTemplateVariables(String template, 
                                     CardAnalysisResult cardAnalysis, 
                                     LogicAnalysisResult logicAnalysis) {
        
        // 替换基础变量
        template = template.replace("${DAMAGE}", String.valueOf(cardAnalysis.getBaseDamage()));
        template = template.replace("${BLOCK}", String.valueOf(cardAnalysis.getBaseBlock()));
        template = template.replace("${MAGIC}", String.valueOf(cardAnalysis.getBaseMagicNumber()));
        
        // 替换能力类名
        if (logicAnalysis != null && logicAnalysis.getPowerClasses() != null && 
            !logicAnalysis.getPowerClasses().isEmpty()) {
            String powerClass = logicAnalysis.getPowerClasses().get(0);
            template = template.replace("${POWER_CLASS}", powerClass);
        }
        
        return template;
    }
    
    /**
     * 生成升级方法代码
     */
    public String generateUpgradeMethod(CardAnalysisResult cardAnalysis) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public void upgrade() {\n");
        sb.append("    if (!this.upgraded) {\n");
        sb.append("        upgradeName();\n");
        
        if (cardAnalysis.getUpgradeDamage() > 0) {
            sb.append("        upgradeDamage(").append(cardAnalysis.getUpgradeDamage()).append(");\n");
        }
        
        if (cardAnalysis.getUpgradeBlock() > 0) {
            sb.append("        upgradeBlock(").append(cardAnalysis.getUpgradeBlock()).append(");\n");
        }
        
        if (cardAnalysis.getUpgradeMagicNumber() > 0) {
            sb.append("        upgradeMagicNumber(").append(cardAnalysis.getUpgradeMagicNumber()).append(");\n");
        }
        
        sb.append("        initializeDescription();\n");
        sb.append("    }\n");
        sb.append("}");
        
        return sb.toString();
    }
    
    /**
     * 生成构造函数代码
     */
    public String generateConstructor(CardAnalysisResult cardAnalysis) {
        StringBuilder sb = new StringBuilder();
        
        String className = "En" + cardAnalysis.getCardId();
        if (className.contains(":")) {
            className = className.substring(className.indexOf(":") + 1);
        }
        
        sb.append("public ").append(className).append("() {\n");
        sb.append("    super(\"downfall_Charboss:").append(cardAnalysis.getCardId()).append("\", ");
        sb.append("cardStrings.NAME, ");
        sb.append("\"").append(generateImagePath(cardAnalysis)).append("\", ");
        sb.append(cardAnalysis.getCost()).append(", ");
        sb.append("cardStrings.DESCRIPTION, ");
        sb.append("CardType.").append(cardAnalysis.getCardType()).append(", ");
        sb.append("CardColor.").append(cardAnalysis.getCardColor()).append(", ");
        sb.append("CardRarity.").append(cardAnalysis.getCardRarity()).append(", ");
        sb.append("CardTarget.").append(cardAnalysis.getCardTarget()).append(");\n");
        
        if (cardAnalysis.getBaseDamage() > 0) {
            sb.append("    this.baseDamage = ").append(cardAnalysis.getBaseDamage()).append(";\n");
            sb.append("    this.damage = this.baseDamage;\n");
        }
        
        if (cardAnalysis.getBaseBlock() > 0) {
            sb.append("    this.baseBlock = ").append(cardAnalysis.getBaseBlock()).append(";\n");
            sb.append("    this.block = this.baseBlock;\n");
        }
        
        if (cardAnalysis.getBaseMagicNumber() > 0) {
            sb.append("    this.baseMagicNumber = ").append(cardAnalysis.getBaseMagicNumber()).append(";\n");
            sb.append("    this.magicNumber = this.baseMagicNumber;\n");
        }
        
        sb.append("}");
        
        return sb.toString();
    }
    
    /**
     * 生成图像路径
     */
    private String generateImagePath(CardAnalysisResult cardAnalysis) {
        String cardName = cardAnalysis.getCardId();
        if (cardName.contains(":")) {
            cardName = cardName.substring(cardName.indexOf(":") + 1);
        }
        
        String cardType = cardAnalysis.getCardType().toString().toLowerCase();
        String cardColor = cardAnalysis.getCardColor().toString().toLowerCase();
        
        return cardColor + "/" + cardType + "/" + cardName.toLowerCase();
    }
    
    /**
     * 设置是否使用高级生成
     */
    public void setUseAdvancedGeneration(boolean useAdvancedGeneration) {
        this.useAdvancedGeneration = useAdvancedGeneration;
    }
    
    /**
     * 添加自定义模板
     */
    public void addTemplate(String key, String template) {
        templates.put(key, template);
    }
    
    /**
     * 获取模板
     */
    public String getTemplate(String key) {
        return templates.get(key);
    }
    
    /**
     * 移除模板
     */
    public void removeTemplate(String key) {
        templates.remove(key);
    }
    
    /**
     * 获取所有模板键
     */
    public java.util.Set<String> getTemplateKeys() {
        return templates.keySet();
    }
}