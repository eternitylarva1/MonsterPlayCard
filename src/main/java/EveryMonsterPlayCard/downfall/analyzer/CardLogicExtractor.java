package EveryMonsterPlayCard.downfall.analyzer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 卡牌逻辑提取器
 * 从卡牌类中提取核心逻辑和效果
 */
public class CardLogicExtractor {
    
    /**
     * 提取卡牌使用逻辑
     */
    public LogicAnalysisResult extractUseLogic(Class<? extends AbstractCard> cardClass) {
        LogicAnalysisResult result = new LogicAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        try {
            // 获取use方法
            Method useMethod = cardClass.getMethod("use", 
                com.megacrit.cardcrawl.characters.AbstractPlayer.class, 
                com.megacrit.cardcrawl.monsters.AbstractMonster.class);
            
            // 这里我们无法直接获取方法体，但可以通过其他方式分析
            // 例如：通过字节码分析或源代码解析
            // 这里提供一个框架，实际实现可能需要更复杂的分析
            
            result.setHasOnUse(true);
            result.setUseMethodBody("Method: " + useMethod.toString());
            
            // 分析方法参数和返回类型
            Class<?>[] paramTypes = useMethod.getParameterTypes();
            for (Class<?> paramType : paramTypes) {
                // 记录参数类型
            }
            
        } catch (NoSuchMethodException e) {
            result.setHasOnUse(false);
        }
        
        // 分析其他方法
        analyzeOtherMethods(cardClass, result);
        
        return result;
    }
    
    /**
     * 提取升级逻辑
     */
    public LogicAnalysisResult extractUpgradeLogic(Class<? extends AbstractCard> cardClass) {
        LogicAnalysisResult result = new LogicAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        try {
            Method upgradeMethod = cardClass.getMethod("upgrade");
            result.setHasOnUpgrade(true);
            result.setUpgradeMethodBody("Method: " + upgradeMethod.toString());
            
            // 分析升级逻辑（简化）
            List<String> upgradeChanges = new ArrayList<>();
            // 这里可以添加更复杂的升级逻辑分析
            result.setUpgradeChanges(upgradeChanges);
            
        } catch (NoSuchMethodException e) {
            result.setHasOnUpgrade(false);
        }
        
        return result;
    }
    
    /**
     * 提取触发器逻辑
     */
    public LogicAnalysisResult extractTriggerLogic(Class<? extends AbstractCard> cardClass) {
        LogicAnalysisResult result = new LogicAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        // 检查各种触发器方法
        String[] triggerMethods = {
            "onDraw", "triggerOnCardDrawn",
            "onDiscard", "triggerOnCardDiscarded", 
            "onExhaust", "triggerOnExhaust",
            "onRetain", "triggerOnRetain",
            "onPlayCard", "triggerOnPlayCard",
            "onMoveToDiscard", "triggerOnMoveToDiscard"
        };
        
        for (String methodName : triggerMethods) {
            try {
                Method method = cardClass.getMethod(methodName);
                // 如果方法存在，标记相应的触发器
                switch (methodName) {
                    case "onDraw":
                    case "triggerOnCardDrawn":
                        result.setHasOnDraw(true);
                        break;
                    case "onDiscard":
                    case "triggerOnCardDiscarded":
                        result.setHasOnDiscard(true);
                        break;
                    case "onExhaust":
                    case "triggerOnExhaust":
                        result.setHasOnExhaust(true);
                        break;
                    case "onRetain":
                    case "triggerOnRetain":
                        result.setHasOnRetain(true);
                        break;
                }
            } catch (NoSuchMethodException e) {
                // 方法不存在，继续检查下一个
            }
        }
        
        return result;
    }
    
    /**
     * 分析卡牌效果描述
     */
    public List<String> extractEffectDescriptions(Class<? extends AbstractCard> cardClass) {
        List<String> effects = new ArrayList<>();
        
        try {
            AbstractCard card = cardClass.newInstance();
            String description = card.rawDescription;
            
            // 使用正则表达式提取效果关键词
            Pattern pattern = Pattern.compile("\\b(Deal|Gain|Apply|Remove|Draw|Discard|Exhaust|Retain)\\b.*?[.!]");
            Matcher matcher = pattern.matcher(description);
            
            while (matcher.find()) {
                effects.add(matcher.group().trim());
            }
            
        } catch (Exception e) {
            // 无法实例化卡牌
        }
        
        return effects;
    }
    
    /**
     * 分析其他方法
     */
    private void analyzeOtherMethods(Class<? extends AbstractCard> cardClass, LogicAnalysisResult result) {
        Method[] methods = cardClass.getDeclaredMethods();
        
        for (Method method : methods) {
            String methodName = method.getName();
            
            // 分析动作类依赖
            if (methodName.contains("Action")) {
                result.getActionClasses().add(method.getReturnType().getName());
            }
            
            // 分析能力类依赖
            if (methodName.contains("Power")) {
                result.getPowerClasses().add(method.getReturnType().getName());
            }
        }
    }
    
    /**
     * 提取卡牌的所有逻辑
     */
    public LogicAnalysisResult extractAllLogic(Class<? extends AbstractCard> cardClass) {
        LogicAnalysisResult result = extractUseLogic(cardClass);
        
        // 合并升级逻辑
        LogicAnalysisResult upgradeLogic = extractUpgradeLogic(cardClass);
        result.setHasOnUpgrade(upgradeLogic.hasOnUpgrade());
        result.setUpgradeMethodBody(upgradeLogic.getUpgradeMethodBody());
        result.setUpgradeChanges(upgradeLogic.getUpgradeChanges());
        
        // 合并触发器逻辑
        LogicAnalysisResult triggerLogic = extractTriggerLogic(cardClass);
        result.setHasOnDraw(triggerLogic.hasOnDraw());
        result.setHasOnDiscard(triggerLogic.hasOnDiscard());
        result.setHasOnExhaust(triggerLogic.hasOnExhaust());
        result.setHasOnRetain(triggerLogic.hasOnRetain());
        
        // 提取效果描述
        result.setEffectDescriptions(extractEffectDescriptions(cardClass));
        
        return result;
    }
}