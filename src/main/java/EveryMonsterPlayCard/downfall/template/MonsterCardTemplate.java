package EveryMonsterPlayCard.downfall.template;

import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.LogicAnalysisResult;

/**
 * 怪物卡牌移植模板
 * 专门用于将Downfall怪物卡牌移植到我们的系统
 */
public class MonsterCardTemplate extends DownfallCardTemplate {
    
    public MonsterCardTemplate(CardAnalysisResult cardAnalysis, 
                            LogicAnalysisResult logicAnalysis,
                            DependencyAnalysisResult dependencyAnalysis) {
        super(cardAnalysis, logicAnalysis, dependencyAnalysis);
    }
    
    @Override
    public String generateCardClass() {
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
        
        // 类结束
        sb.append("}\n");
        
        return sb.toString();
    }
    
    @Override
    protected String generateUseMethod() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("    public void use(AbstractPlayer p, AbstractMonster m) {\n");
        
        // 根据卡牌类型生成不同的逻辑
        if (cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK) {
            sb.append(generateAttackLogic());
        } else if (cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL) {
            sb.append(generateSkillLogic());
        } else if (cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER) {
            sb.append(generatePowerLogic());
        } else {
            sb.append(generateDefaultLogic());
        }
        
        sb.append("    }\n\n");
        
        return sb.toString();
    }
    
    /**
     * 生成攻击卡牌逻辑
     */
    private String generateAttackLogic() {
        StringBuilder sb = new StringBuilder();
        
        if (cardAnalysis.getBaseDamage() > 0) {
            sb.append("        // 造成伤害\n");
            sb.append("        addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));\n");
        }
        
        // 根据逻辑分析添加特殊效果
        if (logicAnalysis != null && logicAnalysis.getEffectDescriptions() != null) {
            for (String effect : logicAnalysis.getEffectDescriptions()) {
                if (effect.toLowerCase().contains("draw")) {
                    sb.append("        // 抽牌效果\n");
                    sb.append("        // TODO: 实现抽牌逻辑\n");
                } else if (effect.toLowerCase().contains("block")) {
                    sb.append("        // 格挡效果\n");
                    sb.append("        // TODO: 实现格挡逻辑\n");
                } else if (effect.toLowerCase().contains("vulnerable") || effect.toLowerCase().contains("weak")) {
                    sb.append("        // 施加减益效果\n");
                    sb.append("        // TODO: 实现减益逻辑\n");
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 生成技能卡牌逻辑
     */
    private String generateSkillLogic() {
        StringBuilder sb = new StringBuilder();
        
        if (cardAnalysis.getBaseBlock() > 0) {
            sb.append("        // 获得格挡\n");
            sb.append("        // TODO: 实现格挡逻辑\n");
        }
        
        if (cardAnalysis.getBaseMagicNumber() > 0) {
            sb.append("        // 技能效果\n");
            sb.append("        // TODO: 实现技能逻辑\n");
        }
        
        // 根据逻辑分析添加特殊效果
        if (logicAnalysis != null && logicAnalysis.getEffectDescriptions() != null) {
            for (String effect : logicAnalysis.getEffectDescriptions()) {
                if (effect.toLowerCase().contains("draw")) {
                    sb.append("        // 抽牌效果\n");
                    sb.append("        // TODO: 实现抽牌逻辑\n");
                } else if (effect.toLowerCase().contains("energy")) {
                    sb.append("        // 能量效果\n");
                    sb.append("        // TODO: 实现能量逻辑\n");
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 生成能力卡牌逻辑
     */
    private String generatePowerLogic() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("        // 施加能力\n");
        sb.append("        // TODO: 实现能力逻辑\n");
        
        if (cardAnalysis.getBaseMagicNumber() > 0) {
            sb.append("        // 能量数值: ").append(cardAnalysis.getBaseMagicNumber()).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * 生成默认逻辑
     */
    private String generateDefaultLogic() {
        StringBuilder sb = new StringBuilder();
        sb.append("        // 默认卡牌逻辑\n");
        sb.append("        // TODO: 实现具体逻辑\n");
        return sb.toString();
    }
    
    @Override
    protected void generateAdditionalImports(StringBuilder sb) {
        // 调用父类方法
        super.generateAdditionalImports(sb);
        
        // 根据卡牌类型添加特定导入
        if (cardAnalysis.getCardType() == com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK) {
            sb.append("import com.badlogic.gdx.graphics.Color;\n");
            sb.append("import com.megacrit.cardcrawl.actions.animations.VFXAction;\n");
            sb.append("import com.megacrit.cardcrawl.vfx.AbstractGameEffect;\n");
            sb.append("import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;\n");
        }
    }
    
    @Override
    protected String generateImagePath() {
        // 根据卡牌类型和颜色生成图像路径
        String cardName = cardAnalysis != null ? cardAnalysis.getCardId() : "unknown";
        if (cardName.contains(":")) {
            cardName = cardName.substring(cardName.indexOf(":") + 1);
        }
        
        String cardType = cardAnalysis != null ? cardAnalysis.getCardType().toString().toLowerCase() : "skill";
        String cardColor = cardAnalysis != null ? cardAnalysis.getCardColor().toString().toLowerCase() : "red";
        
        return cardColor + "/" + cardType + "/" + cardName.toLowerCase();
    }
    
    /**
     * 生成怪物特定的注释
     */
    @Override
    protected String generateClassComment() {
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * ").append(cardAnalysis.getCardName()).append(" - 怪物版\n");
        sb.append(" * 从Downfall模组移植的怪物卡牌\n");
        sb.append(" * 原始ID: ").append(cardAnalysis.getCardId()).append("\n");
        sb.append(" * 卡牌类型: ").append(cardAnalysis.getCardType()).append("\n");
        sb.append(" * 卡牌颜色: ").append(cardAnalysis.getCardColor()).append("\n");
        sb.append(" * 移植难度: ").append(calculateMigrationDifficulty()).append("\n");
        sb.append(" */\n");
        return sb.toString();
    }
    
    /**
     * 计算移植难度
     */
    private int calculateMigrationDifficulty() {
        int difficulty = 1; // 基础难度
        
        // 根据依赖数量增加难度
        if (dependencyAnalysis != null) {
            difficulty += dependencyAnalysis.getRequiredPowers().size() * 2;
            difficulty += dependencyAnalysis.getRequiredActions().size();
            difficulty += dependencyAnalysis.getRequiredRelics().size() * 3;
        }
        
        // 根据逻辑复杂度增加难度
        if (logicAnalysis != null) {
            if (logicAnalysis.hasOnDraw()) difficulty += 2;
            if (logicAnalysis.hasOnDiscard()) difficulty += 2;
            if (logicAnalysis.hasOnExhaust()) difficulty += 2;
            if (logicAnalysis.hasOnRetain()) difficulty += 2;
        }
        
        return difficulty;
    }
}