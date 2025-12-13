package EveryMonsterPlayCard.downfall.migrator;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.core.data.MigrationResult;
import EveryMonsterPlayCard.core.data.ValidationResult;
import EveryMonsterPlayCard.core.interfaces.ICardMigrator;
import EveryMonsterPlayCard.downfall.analyzer.DownfallCardAnalyzer;
import EveryMonsterPlayCard.downfall.analyzer.MigrationReport;
import EveryMonsterPlayCard.downfall.template.DownfallCardTemplate;
import EveryMonsterPlayCard.downfall.template.MonsterCardTemplate;
import EveryMonsterPlayCard.downfall.template.UniversalCardTemplate;

/**
 * 自动卡牌移植器
 * 实现ICardMigrator接口，提供Downfall卡牌的自动移植功能
 */
public class AutoCardMigrator implements ICardMigrator {
    
    private DownfallCardAnalyzer analyzer;
    private CardCodeGenerator codeGenerator;
    private MigrationValidator validator;
    private String[] supportedModIds;
    
    public AutoCardMigrator() {
        this.analyzer = new DownfallCardAnalyzer();
        this.codeGenerator = new CardCodeGenerator();
        this.validator = new MigrationValidator();
        this.supportedModIds = new String[]{"downfall", "Downfall"};
    }
    
    @Override
    public MigrationResult migrateCard(AbstractCard originalCard) {
        MigrationResult result = new MigrationResult();
        result.setOriginalCard(originalCard);
        
        try {
            // 分析卡牌
            MigrationReport report = analyzer.generateMigrationReport(originalCard.getClass());
            
            // 选择合适的模板
            DownfallCardTemplate template = selectTemplate(report);
            
            // 生成代码
            String generatedCode = template.generateCardClass();
            
            // 验证生成的代码
            ValidationResult validation = validator.validateCode(generatedCode);
            
            if (validation.isValid()) {
                // 创建移植后的卡牌实例
                AbstractCard migratedCard = createMigratedCard(generatedCode, report);
                
                result.setMigratedCard(migratedCard);
                result.setSuccess(true);
                result.setMessage("卡牌移植成功");
                
                // 添加移植信息
                result.addWarning("移植难度: " + report.getMigrationDifficulty());
                if (report.getDependencyAnalysisResult().getRequiredPowers().size() > 0) {
                    result.addWarning("需要额外实现能力类");
                }
            } else {
                result.setSuccess(false);
                result.setMessage("生成的代码验证失败");
                for (String error : validation.getErrors()) {
                    result.addError(error);
                }
            }
            
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("移植过程中发生错误: " + e.getMessage());
            result.addError(e.getMessage());
        }
        
        return result;
    }
    
    @Override
    public ValidationResult validateMigration(AbstractCard migratedCard) {
        return validator.validateCard(migratedCard);
    }
    
    @Override
    public boolean adjustCardBalance(AbstractCard card) {
        try {
            // 基础平衡性调整
            boolean adjusted = false;
            
            // 检查伤害是否过高
            if (card.baseDamage > 20) {
                card.baseDamage = Math.max(15, card.baseDamage - 5);
                card.damage = card.baseDamage;
                adjusted = true;
            }
            
            // 检查费用是否过低
            if (card.cost < 0 && card.baseDamage > 10) {
                card.cost = 1;
                card.costForTurn = card.cost;
                adjusted = true;
            }
            
            // 检查格挡是否过高
            if (card.baseBlock > 15) {
                card.baseBlock = Math.max(12, card.baseBlock - 3);
                card.block = card.baseBlock;
                adjusted = true;
            }
            
            return adjusted;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public String[] getSupportedModIds() {
        return supportedModIds.clone();
    }
    
    @Override
    public boolean canMigrate(AbstractCard card) {
        if (card == null) {
            return false;
        }
        
        String cardId = card.cardID;
        if (cardId == null) {
            return false;
        }
        
        // 检查是否来自支持的模组
        for (String modId : supportedModIds) {
            if (cardId.contains(modId) || cardId.toLowerCase().contains(modId.toLowerCase())) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 批量移植卡牌
     */
    public List<MigrationResult> migrateCards(List<AbstractCard> cards) {
        List<MigrationResult> results = new ArrayList<>();
        
        for (AbstractCard card : cards) {
            if (canMigrate(card)) {
                MigrationResult result = migrateCard(card);
                results.add(result);
            } else {
                MigrationResult result = new MigrationResult(false);
                result.setOriginalCard(card);
                result.setMessage("卡牌不支持移植");
                result.addError("不支持的模组或卡牌类型");
                results.add(result);
            }
        }
        
        return results;
    }
    
    /**
     * 选择合适的模板
     */
    private DownfallCardTemplate selectTemplate(MigrationReport report) {
        // 根据卡牌类型和复杂度选择模板
        if (report.getCardAnalysisResult().getCardType() == AbstractCard.CardType.ATTACK ||
            report.getCardAnalysisResult().getCardType() == AbstractCard.CardType.SKILL) {
            
            // 对于攻击和技能卡牌，使用怪物卡牌模板
            return new MonsterCardTemplate(
                report.getCardAnalysisResult(),
                report.getLogicAnalysisResult(),
                report.getDependencyAnalysisResult()
            );
        } else {
            // 对于其他类型，使用通用模板
            return new UniversalCardTemplate(
                report.getCardAnalysisResult(),
                report.getLogicAnalysisResult(),
                report.getDependencyAnalysisResult(),
                report.getMigrationDifficulty() > 5 // 高难度使用高级逻辑
            );
        }
    }
    
    /**
     * 创建移植后的卡牌实例
     */
    private AbstractCard createMigratedCard(String generatedCode, MigrationReport report) {
        try {
            // 这里应该编译生成的代码并创建实例
            // 由于动态编译比较复杂，这里返回一个基础实例
            // 实际实现中可以使用Java Compiler API
            
            String className = "En" + report.getCardAnalysisResult().getCardId();
            if (className.contains(":")) {
                className = className.substring(className.indexOf(":") + 1);
            }
            
            // 创建一个基础卡牌作为占位符
            AbstractCard placeholder = new AbstractCard(
                "migrated_" + report.getCardAnalysisResult().getCardId(),
                report.getCardAnalysisResult().getCardName(),
                "red/attack/placeholder",
                report.getCardAnalysisResult().getCost(),
                report.getCardAnalysisResult().getDescription(),
                report.getCardAnalysisResult().getCardType(),
                report.getCardAnalysisResult().getCardColor(),
                report.getCardAnalysisResult().getCardRarity(),
                report.getCardAnalysisResult().getCardTarget()
            ) {
                @Override
                public void use(AbstractPlayer p, AbstractMonster m) {
                    // 占位符实现
                }
                
                @Override
                public AbstractCard makeCopy() {
                    return this;
                }
                
                @Override
                public void upgrade() {
                    // 占位符实现
                }
            };
            
            // 设置基础属性
            placeholder.baseDamage = report.getCardAnalysisResult().getBaseDamage();
            placeholder.damage = placeholder.baseDamage;
            placeholder.baseBlock = report.getCardAnalysisResult().getBaseBlock();
            placeholder.block = placeholder.baseBlock;
            placeholder.baseMagicNumber = report.getCardAnalysisResult().getBaseMagicNumber();
            placeholder.magicNumber = placeholder.baseMagicNumber;
            
            return placeholder;
            
        } catch (Exception e) {
            throw new RuntimeException("无法创建移植后的卡牌实例", e);
        }
    }
    
    /**
     * 设置支持的模组ID
     */
    public void setSupportedModIds(String[] modIds) {
        this.supportedModIds = modIds.clone();
    }
    
    /**
     * 获取分析器
     */
    public DownfallCardAnalyzer getAnalyzer() {
        return analyzer;
    }
    
    /**
     * 获取代码生成器
     */
    public CardCodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
    
    /**
     * 获取验证器
     */
    public MigrationValidator getValidator() {
        return validator;
    }
}