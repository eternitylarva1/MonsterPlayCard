package EveryMonsterPlayCard.downfall.analyzer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * Downfall卡牌分析器
 * 分析Downfall卡牌的类结构和方法，提取核心逻辑和属性
 */
public class DownfallCardAnalyzer {
    
    /**
     * 分析卡牌属性
     */
    public CardAnalysisResult analyzeCard(Class<? extends AbstractCard> cardClass) {
        CardAnalysisResult result = new CardAnalysisResult();
        try {
            // 通过反射获取卡牌属性
            AbstractCard instance = cardClass.newInstance();
            result.setCardId(instance.cardID);
            result.setCardName(instance.name);
            result.setCardType(instance.type);
            result.setCardColor(instance.color);
            result.setCardRarity(instance.rarity);
            result.setCardTarget(instance.target);
            result.setCost(instance.cost);
            result.setBaseDamage(instance.baseDamage);
            result.setBaseBlock(instance.baseBlock);
            result.setBaseMagicNumber(instance.magicNumber);
            result.setDescription(instance.rawDescription);
            result.setUpgradable(instance.canUpgrade());
            
            // 尝试获取升级增量
            if (instance.canUpgrade()) {
                // 通过反射获取upgradeDamage等字段
                try {
                    Field upgradeDamageField = cardClass.getDeclaredField("upgradeDamage");
                    upgradeDamageField.setAccessible(true);
                    result.setUpgradeDamage(upgradeDamageField.getInt(instance));
                } catch (Exception e) {
                    // 忽略
                }
                try {
                    Field upgradeBlockField = cardClass.getDeclaredField("upgradeBlock");
                    upgradeBlockField.setAccessible(true);
                    result.setUpgradeBlock(upgradeBlockField.getInt(instance));
                } catch (Exception e) {
                    // 忽略
                }
                try {
                    Field upgradeMagicNumberField = cardClass.getDeclaredField("upgradeMagicNumber");
                    upgradeMagicNumberField.setAccessible(true);
                    result.setUpgradeMagicNumber(upgradeMagicNumberField.getInt(instance));
                } catch (Exception e) {
                    // 忽略
                }
            }
            
            // 分析关键词（简单实现）
            List<String> keywords = new ArrayList<>();
            if (instance.rawDescription != null) {
                // 这里可以添加关键词提取逻辑
            }
            result.setKeywords(keywords);
            
            // 记录原始源代码（类名）
            result.setRawSource(cardClass.getName());
            
        } catch (Exception e) {
            // 如果无法实例化，则使用类名等基本信息
            result.setCardId(cardClass.getSimpleName());
            result.setCardName(cardClass.getSimpleName());
            result.setRawSource(cardClass.getName());
        }
        return result;
    }
    
    /**
     * 分析卡牌使用逻辑
     */
    public LogicAnalysisResult analyzeCardLogic(Class<? extends AbstractCard> cardClass) {
        LogicAnalysisResult result = new LogicAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        try {
            // 反射分析use方法
            Method useMethod = cardClass.getMethod("use", com.megacrit.cardcrawl.characters.AbstractPlayer.class, 
                                                   com.megacrit.cardcrawl.monsters.AbstractMonster.class);
            // 获取方法体内容（无法直接获取源代码，这里记录方法名）
            result.setUseMethodBody(useMethod.toString());
            result.setHasOnUse(true);
            
            // 检查其他方法
            Method[] methods = cardClass.getDeclaredMethods();
            for (Method m : methods) {
                String methodName = m.getName();
                if (methodName.equals("upgrade")) {
                    result.setHasOnUpgrade(true);
                    result.setUpgradeMethodBody(m.toString());
                } else if (methodName.equals("onDraw") || methodName.equals("triggerOnCardDrawn")) {
                    result.setHasOnDraw(true);
                } else if (methodName.equals("onDiscard") || methodName.equals("triggerOnCardDiscarded")) {
                    result.setHasOnDiscard(true);
                } else if (methodName.equals("onExhaust") || methodName.equals("triggerOnExhaust")) {
                    result.setHasOnExhaust(true);
                } else if (methodName.equals("onRetain") || methodName.equals("triggerOnRetain")) {
                    result.setHasOnRetain(true);
                }
            }
            
            // 分析动作类依赖（通过use方法中的代码？这里简化）
            // 可以尝试解析字节码，但这里跳过
            
        } catch (Exception e) {
            // 方法不存在或其他错误
        }
        
        return result;
    }
    
    /**
     * 分析卡牌依赖
     */
    public DependencyAnalysisResult analyzeDependencies(Class<? extends AbstractCard> cardClass) {
        DependencyAnalysisResult result = new DependencyAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        // 分析类依赖（通过反射获取字段类型）
        Field[] fields = cardClass.getDeclaredFields();
        for (Field f : fields) {
            Class<?> fieldType = f.getType();
            String typeName = fieldType.getName();
            if (typeName.contains("Power")) {
                result.getRequiredPowers().add(typeName);
            } else if (typeName.contains("Action")) {
                result.getRequiredActions().add(typeName);
            } else if (typeName.contains("Relic")) {
                result.getRequiredRelics().add(typeName);
            } else if (typeName.contains("AbstractCard")) {
                result.getRequiredCards().add(typeName);
            }
        }
        
        // 检查是否使用自定义渲染器
        try {
            Method renderMethod = cardClass.getMethod("render", com.badlogic.gdx.graphics.g2d.SpriteBatch.class);
            // 如果有自定义render方法，则标记
            result.setUsesCustomRenderer(true);
        } catch (Exception e) {
            // 没有自定义render
        }
        
        // 检查是否使用自定义动画
        try {
            Method updateMethod = cardClass.getMethod("update");
            // 如果有自定义update方法，可能涉及动画
            result.setUsesCustomAnimation(true);
        } catch (Exception e) {
            // 忽略
        }
        
        return result;
    }
    
    /**
     * 生成移植报告
     */
    public MigrationReport generateMigrationReport(Class<? extends AbstractCard> cardClass) {
        CardAnalysisResult cardResult = analyzeCard(cardClass);
        LogicAnalysisResult logicResult = analyzeCardLogic(cardClass);
        DependencyAnalysisResult depResult = analyzeDependencies(cardClass);
        
        MigrationReport report = new MigrationReport();
        report.setCardAnalysisResult(cardResult);
        report.setLogicAnalysisResult(logicResult);
        report.setDependencyAnalysisResult(depResult);
        report.setCardClass(cardClass);
        report.setGeneratedTime(System.currentTimeMillis());
        
        // 评估移植难度
        int difficulty = calculateMigrationDifficulty(cardResult, logicResult, depResult);
        report.setMigrationDifficulty(difficulty);
        
        return report;
    }
    
    private int calculateMigrationDifficulty(CardAnalysisResult card, LogicAnalysisResult logic, DependencyAnalysisResult dep) {
        int score = 0;
        // 简单规则：依赖越多难度越高
        score += dep.getRequiredPowers().size() * 2;
        score += dep.getRequiredActions().size();
        score += dep.getRequiredRelics().size() * 3;
        score += dep.getRequiredCards().size();
        if (dep.usesCustomRenderer()) score += 5;
        if (dep.usesCustomAnimation()) score += 5;
        if (logic.hasOnDraw() || logic.hasOnDiscard() || logic.hasOnExhaust() || logic.hasOnRetain()) score += 3;
        return score;
    }
}