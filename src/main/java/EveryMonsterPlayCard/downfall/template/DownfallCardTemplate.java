package EveryMonsterPlayCard.downfall.template;

import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.LogicAnalysisResult;

/**
 * Downfall卡牌移植模板基类
 * 提供Downfall卡牌移植的基础框架和通用方法
 */
public abstract class DownfallCardTemplate {
    
    protected CardAnalysisResult cardAnalysis;
    protected LogicAnalysisResult logicAnalysis;
    protected DependencyAnalysisResult dependencyAnalysis;
    
    public DownfallCardTemplate(CardAnalysisResult cardAnalysis, 
                               LogicAnalysisResult logicAnalysis,
                               DependencyAnalysisResult dependencyAnalysis) {
        this.cardAnalysis = cardAnalysis;
        this.logicAnalysis = logicAnalysis;
        this.dependencyAnalysis = dependencyAnalysis;
    }
    
    /**
     * 生成卡牌类的基本结构
     */
    public abstract String generateCardClass();
    
    /**
     * 生成包声明和导入语句
     */
    protected String generatePackageAndImports() {
        StringBuilder sb = new StringBuilder();
        
        // 包声明
        sb.append("package EveryMonsterPlayCard.cards.monster;\n\n");
        
        // 基本导入
        sb.append("import com.megacrit.cardcrawl.actions.AbstractGameAction;\n");
        sb.append("import com.megacrit.cardcrawl.actions.common.DamageAction;\n");
        sb.append("import com.megacrit.cardcrawl.cards.AbstractCard;\n");
        sb.append("import com.megacrit.cardcrawl.cards.DamageInfo;\n");
        sb.append("import com.megacrit.cardcrawl.characters.AbstractPlayer;\n");
        sb.append("import com.megacrit.cardcrawl.core.AbstractCreature;\n");
        sb.append("import com.megacrit.cardcrawl.core.CardCrawlGame;\n");
        sb.append("import com.megacrit.cardcrawl.dungeons.AbstractDungeon;\n");
        sb.append("import com.megacrit.cardcrawl.localization.CardStrings;\n");
        sb.append("import com.megacrit.cardcrawl.monsters.AbstractMonster;\n");
        
        // 根据依赖添加额外导入
        generateAdditionalImports(sb);
        
        sb.append("\n");
        
        return sb.toString();
    }
    
    /**
     * 生成额外的导入语句（子类可重写）
     */
    protected void generateAdditionalImports(StringBuilder sb) {
        // 根据依赖分析结果添加必要的导入
        if (dependencyAnalysis != null) {
            for (String powerClass : dependencyAnalysis.getRequiredPowers()) {
                sb.append("import ").append(powerClass).append(";\n");
            }
            for (String actionClass : dependencyAnalysis.getRequiredActions()) {
                sb.append("import ").append(actionClass).append(";\n");
            }
        }
    }
    
    /**
     * 生成类声明
     */
    protected String generateClassDeclaration() {
        String className = generateClassName();
        return "public class " + className + " extends AbstractMonsterCard {\n\n";
    }
    
    /**
     * 生成类名
     */
    protected String generateClassName() {
        if (cardAnalysis != null && cardAnalysis.getCardId() != null) {
            String originalId = cardAnalysis.getCardId();
            // 移除可能的命名空间前缀
            if (originalId.contains(":")) {
                originalId = originalId.substring(originalId.indexOf(":") + 1);
            }
            // 添加En前缀表示怪物版本
            return "En" + originalId;
        }
        return "EnUnknownCard";
    }
    
    /**
     * 生成字段声明
     */
    protected String generateFields() {
        StringBuilder sb = new StringBuilder();
        
        // CardStrings字段
        String cardId = cardAnalysis != null ? cardAnalysis.getCardId() : "Unknown";
        sb.append("    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(\"").append(cardId).append("\");\n");
        sb.append("    public static final String ID = \"downfall_Charboss:").append(cardId).append("\";\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成构造函数
     */
    protected String generateConstructor() {
        StringBuilder sb = new StringBuilder();
        String className = generateClassName();
        
        sb.append("    public ").append(className).append("() {\n");
        sb.append("        super(\"downfall_Charboss:").append(cardAnalysis.getCardId()).append("\", ");
        sb.append("cardStrings.NAME, ");
        
        // 图像路径
        String imagePath = generateImagePath();
        sb.append("\"").append(imagePath).append("\", ");
        
        // 费用
        sb.append(cardAnalysis.getCost()).append(", ");
        sb.append("cardStrings.DESCRIPTION, ");
        
        // 卡牌类型
        sb.append("CardType.").append(cardAnalysis.getCardType()).append(", ");
        
        // 颜色
        sb.append("CardColor.").append(cardAnalysis.getCardColor()).append(", ");
        
        // 稀有度
        sb.append("CardRarity.").append(cardAnalysis.getCardRarity()).append(", ");
        
        // 目标
        sb.append("CardTarget.").append(cardAnalysis.getCardTarget()).append(");\n\n");
        
        // 设置数值
        if (cardAnalysis.getBaseDamage() > 0) {
            sb.append("        this.baseDamage = ").append(cardAnalysis.getBaseDamage()).append(";\n");
            sb.append("        this.damage = this.baseDamage;\n");
        }
        
        if (cardAnalysis.getBaseBlock() > 0) {
            sb.append("        this.baseBlock = ").append(cardAnalysis.getBaseBlock()).append(";\n");
            sb.append("        this.block = this.baseBlock;\n");
        }
        
        if (cardAnalysis.getBaseMagicNumber() > 0) {
            sb.append("        this.baseMagicNumber = ").append(cardAnalysis.getBaseMagicNumber()).append(";\n");
            sb.append("        this.magicNumber = this.baseMagicNumber;\n");
        }
        
        sb.append("\n    }\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成图像路径
     */
    protected String generateImagePath() {
        // 默认使用red/attack/卡牌名
        String cardName = cardAnalysis != null ? cardAnalysis.getCardId() : "unknown";
        if (cardName.contains(":")) {
            cardName = cardName.substring(cardName.indexOf(":") + 1);
        }
        
        String cardType = cardAnalysis != null ? cardAnalysis.getCardType().toString().toLowerCase() : "skill";
        return "red/" + cardType + "/" + cardName.toLowerCase();
    }
    
    /**
     * 生成use方法
     */
    protected abstract String generateUseMethod();
    
    /**
     * 生成upgrade方法
     */
    protected String generateUpgradeMethod() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("    public void upgrade() {\n");
        sb.append("        if (!this.upgraded) {\n");
        sb.append("            upgradeName();\n");
        
        // 根据升级增量生成升级逻辑
        if (cardAnalysis.getUpgradeDamage() > 0) {
            sb.append("            upgradeDamage(").append(cardAnalysis.getUpgradeDamage()).append(");\n");
        }
        
        if (cardAnalysis.getUpgradeBlock() > 0) {
            sb.append("            upgradeBlock(").append(cardAnalysis.getUpgradeBlock()).append(");\n");
        }
        
        if (cardAnalysis.getUpgradeMagicNumber() > 0) {
            sb.append("            upgradeMagicNumber(").append(cardAnalysis.getUpgradeMagicNumber()).append(");\n");
        }
        
        sb.append("            initializeDescription();\n");
        sb.append("        }\n");
        sb.append("    }\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成makeCopy方法
     */
    protected String generateMakeCopyMethod() {
        String className = generateClassName();
        return "    public AbstractCard makeCopy() {\n" +
               "        return new " + className + "();\n" +
               "    }\n";
    }
    
    /**
     * 生成类注释
     */
    protected String generateClassComment() {
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * ").append(cardAnalysis.getCardName()).append(" - 怪物版\n");
        sb.append(" * 从Downfall模组移植的卡牌\n");
        sb.append(" * 原始ID: ").append(cardAnalysis.getCardId()).append("\n");
        sb.append(" */\n");
        return sb.toString();
    }
    
    /**
     * 获取卡牌分析结果
     */
    public CardAnalysisResult getCardAnalysis() {
        return cardAnalysis;
    }
    
    /**
     * 获取逻辑分析结果
     */
    public LogicAnalysisResult getLogicAnalysis() {
        return logicAnalysis;
    }
    
    /**
     * 获取依赖分析结果
     */
    public DependencyAnalysisResult getDependencyAnalysis() {
        return dependencyAnalysis;
    }
}