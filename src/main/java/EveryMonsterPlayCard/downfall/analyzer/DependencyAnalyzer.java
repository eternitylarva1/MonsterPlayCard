package EveryMonsterPlayCard.downfall.analyzer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 依赖分析器
 * 分析卡牌的依赖关系，包括类、资源、模组等
 */
public class DependencyAnalyzer {
    
    /**
     * 分析卡牌的所有依赖
     */
    public DependencyAnalysisResult analyzeDependencies(Class<? extends AbstractCard> cardClass) {
        DependencyAnalysisResult result = new DependencyAnalysisResult();
        result.setCardId(cardClass.getSimpleName());
        
        // 分析类依赖
        analyzeClassDependencies(cardClass, result);
        
        // 分析资源依赖
        analyzeResourceDependencies(cardClass, result);
        
        // 分析特殊功能依赖
        analyzeSpecialDependencies(cardClass, result);
        
        return result;
    }
    
    /**
     * 分析类依赖
     */
    private void analyzeClassDependencies(Class<? extends AbstractCard> cardClass, DependencyAnalysisResult result) {
        Set<String> analyzedClasses = new HashSet<>();
        
        // 分析字段类型
        analyzeFieldDependencies(cardClass, result, analyzedClasses);
        
        // 分析方法返回类型和参数类型
        analyzeMethodDependencies(cardClass, result, analyzedClasses);
        
        // 分析父类和接口
        analyzeInheritanceDependencies(cardClass, result, analyzedClasses);
    }
    
    /**
     * 分析字段依赖
     */
    private void analyzeFieldDependencies(Class<? extends AbstractCard> cardClass, 
                                        DependencyAnalysisResult result, 
                                        Set<String> analyzedClasses) {
        Field[] fields = cardClass.getDeclaredFields();
        
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            String typeName = fieldType.getName();
            
            if (analyzedClasses.contains(typeName)) {
                continue;
            }
            analyzedClasses.add(typeName);
            
            // 根据类型分类
            if (typeName.contains("Power")) {
                result.getRequiredPowers().add(typeName);
            } else if (typeName.contains("Action")) {
                result.getRequiredActions().add(typeName);
            } else if (typeName.contains("Relic")) {
                result.getRequiredRelics().add(typeName);
            } else if (typeName.contains("AbstractCard")) {
                result.getRequiredCards().add(typeName);
            } else if (typeName.contains("Keyword")) {
                result.getRequiredKeywords().add(typeName);
            } else if (!isStandardJavaType(typeName)) {
                result.getRequiredClasses().add(typeName);
            }
        }
    }
    
    /**
     * 分析方法依赖
     */
    private void analyzeMethodDependencies(Class<? extends AbstractCard> cardClass, 
                                         DependencyAnalysisResult result, 
                                         Set<String> analyzedClasses) {
        Method[] methods = cardClass.getDeclaredMethods();
        
        for (Method method : methods) {
            // 分析返回类型
            Class<?> returnType = method.getReturnType();
            String returnTypeName = returnType.getName();
            
            if (!analyzedClasses.contains(returnTypeName) && !isStandardJavaType(returnTypeName)) {
                analyzedClasses.add(returnTypeName);
                categorizeDependency(returnTypeName, result);
            }
            
            // 分析参数类型
            Class<?>[] paramTypes = method.getParameterTypes();
            for (Class<?> paramType : paramTypes) {
                String paramTypeName = paramType.getName();
                
                if (!analyzedClasses.contains(paramTypeName) && !isStandardJavaType(paramType)) {
                    analyzedClasses.add(paramTypeName);
                    categorizeDependency(paramTypeName, result);
                }
            }
        }
    }
    
    /**
     * 分析继承依赖
     */
    private void analyzeInheritanceDependencies(Class<? extends AbstractCard> cardClass, 
                                              DependencyAnalysisResult result, 
                                              Set<String> analyzedClasses) {
        // 分析父类
        Class<?> superClass = cardClass.getSuperclass();
        while (superClass != null && !superClass.equals(Object.class)) {
            String superClassName = superClass.getName();
            if (!analyzedClasses.contains(superClassName)) {
                analyzedClasses.add(superClassName);
                categorizeDependency(superClassName, result);
            }
            superClass = superClass.getSuperclass();
        }
        
        // 分析接口
        Class<?>[] interfaces = cardClass.getInterfaces();
        for (Class<?> iface : interfaces) {
            String interfaceName = iface.getName();
            if (!analyzedClasses.contains(interfaceName)) {
                analyzedClasses.add(interfaceName);
                categorizeDependency(interfaceName, result);
            }
        }
    }
    
    /**
     * 分类依赖
     */
    private void categorizeDependency(String typeName, DependencyAnalysisResult result) {
        if (typeName.contains("Power")) {
            result.getRequiredPowers().add(typeName);
        } else if (typeName.contains("Action")) {
            result.getRequiredActions().add(typeName);
        } else if (typeName.contains("Relic")) {
            result.getRequiredRelics().add(typeName);
        } else if (typeName.contains("AbstractCard")) {
            result.getRequiredCards().add(typeName);
        } else if (typeName.contains("Keyword")) {
            result.getRequiredKeywords().add(typeName);
        } else {
            result.getRequiredClasses().add(typeName);
        }
    }
    
    /**
     * 分析资源依赖
     */
    private void analyzeResourceDependencies(Class<? extends AbstractCard> cardClass, DependencyAnalysisResult result) {
        try {
            AbstractCard card = cardClass.newInstance();
            
            // 分析图像资源 - AbstractCard类可能有不同的字段名
            // 尝试常见的字段名
            try {
                java.lang.reflect.Field imgField = AbstractCard.class.getDeclaredField("assetUrl");
                imgField.setAccessible(true);
                String imgPath = (String) imgField.get(card);
                if (imgPath != null && !imgPath.isEmpty()) {
                    result.getRequiredImages().add(imgPath);
                }
            } catch (Exception e1) {
                try {
                    java.lang.reflect.Field imgField = AbstractCard.class.getDeclaredField("textureImg");
                    imgField.setAccessible(true);
                    String imgPath = (String) imgField.get(card);
                    if (imgPath != null && !imgPath.isEmpty()) {
                        result.getRequiredImages().add(imgPath);
                    }
                } catch (Exception e2) {
                    // 如果无法获取图像路径，则跳过
                }
            }
            
            // 分析其他可能的资源路径
            // 这里可以扩展分析更多资源类型
            
        } catch (Exception e) {
            // 无法实例化卡牌
        }
    }
    
    /**
     * 分析特殊功能依赖
     */
    private void analyzeSpecialDependencies(Class<? extends AbstractCard> cardClass, DependencyAnalysisResult result) {
        // 检查是否有自定义渲染
        try {
            Method renderMethod = cardClass.getMethod("render", com.badlogic.gdx.graphics.g2d.SpriteBatch.class);
            if (!renderMethod.getDeclaringClass().equals(AbstractCard.class)) {
                result.setUsesCustomRenderer(true);
            }
        } catch (NoSuchMethodException e) {
            // 没有自定义render方法
        }
        
        // 检查是否有自定义动画
        try {
            Method updateMethod = cardClass.getMethod("update");
            if (!updateMethod.getDeclaringClass().equals(AbstractCard.class)) {
                result.setUsesCustomAnimation(true);
            }
        } catch (NoSuchMethodException e) {
            // 没有自定义update方法
        }
        
        // 检查是否有自定义能力
        try {
            Method[] methods = cardClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().contains("Power") && 
                    !Modifier.isAbstract(method.getModifiers())) {
                    result.setUsesCustomPower(true);
                    break;
                }
            }
        } catch (Exception e) {
            // 忽略
        }
        
        // 检查是否有自定义动作
        try {
            Method[] methods = cardClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().contains("Action") && 
                    !Modifier.isAbstract(method.getModifiers())) {
                    result.setUsesCustomAction(true);
                    break;
                }
            }
        } catch (Exception e) {
            // 忽略
        }
    }
    
    /**
     * 检查是否为标准Java类型
     */
    private boolean isStandardJavaType(Class<?> type) {
        String typeName = type.getName();
        return typeName.startsWith("java.") || 
               typeName.startsWith("javax.") ||
               typeName.startsWith("com.badlogic.gdx.") ||
               typeName.equals("int") ||
               typeName.equals("float") ||
               typeName.equals("double") ||
               typeName.equals("boolean") ||
               typeName.equals("char") ||
               typeName.equals("byte") ||
               typeName.equals("short") ||
               typeName.equals("long") ||
               typeName.equals("void");
    }
    
    /**
     * 检查是否为标准Java类型（重载方法）
     */
    private boolean isStandardJavaType(String typeName) {
        return typeName.startsWith("java.") || 
               typeName.startsWith("javax.") ||
               typeName.startsWith("com.badlogic.gdx.") ||
               typeName.equals("int") ||
               typeName.equals("float") ||
               typeName.equals("double") ||
               typeName.equals("boolean") ||
               typeName.equals("char") ||
               typeName.equals("byte") ||
               typeName.equals("short") ||
               typeName.equals("long") ||
               typeName.equals("void");
    }
    
    /**
     * 分析模组依赖
     */
    public List<String> analyzeModDependencies(Class<? extends AbstractCard> cardClass) {
        List<String> modDependencies = new ArrayList<>();
        
        // 通过包名分析模组依赖
        String packageName = cardClass.getPackage().getName();
        if (packageName.contains("downfall")) {
            modDependencies.add("Downfall");
        }
        
        // 通过类名分析模组依赖
        String className = cardClass.getSimpleName();
        if (className.startsWith("Charboss")) {
            modDependencies.add("Downfall");
        }
        
        return modDependencies;
    }
}