package EveryMonsterPlayCard.downfall.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 移植配置
 * 管理Downfall卡牌移植的配置选项
 */
public class MigrationConfig {
    
    private static final String CONFIG_FILE = "downfall-migration.properties";
    private static MigrationConfig instance;
    
    // 配置项
    private boolean enableAutoMigration;
    private boolean enableBalanceAdjustment;
    private boolean enableResourceProcessing;
    private boolean enableValidation;
    private boolean enableAsyncProcessing;
    private boolean enableCaching;
    private int maxConcurrentMigrations;
    private String downfallModPath;
    private String outputPath;
    private List<String> excludedCardIds;
    private List<String> includedCardIds;
    private Map<String, String> customBalanceRules;
    private Map<String, String> templateMappings;
    
    private MigrationConfig() {
        loadDefaultValues();
    }
    
    /**
     * 获取配置实例（单例模式）
     */
    public static MigrationConfig getInstance() {
        if (instance == null) {
            instance = new MigrationConfig();
            instance.loadFromFile();
        }
        return instance;
    }
    
    /**
     * 加载默认值
     */
    private void loadDefaultValues() {
        this.enableAutoMigration = true;
        this.enableBalanceAdjustment = true;
        this.enableResourceProcessing = true;
        this.enableValidation = true;
        this.enableAsyncProcessing = false;
        this.enableCaching = true;
        this.maxConcurrentMigrations = 4;
        this.downfallModPath = ".SlayTheSpireLibrary/崩坠卡牌/";
        this.outputPath = "src/main/java/EveryMonsterPlayCard/cards/monster/";
        this.excludedCardIds = new ArrayList<>();
        this.includedCardIds = new ArrayList<>();
        this.customBalanceRules = new HashMap<>();
        this.templateMappings = new HashMap<>();
    }
    
    /**
     * 从文件加载配置
     */
    public void loadFromFile() {
        File configFile = new File(CONFIG_FILE);
        if (!configFile.exists()) {
            saveToFile(); // 创建默认配置文件
            return;
        }
        
        try (FileInputStream fis = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(fis);
            
            // 加载配置项
            enableAutoMigration = Boolean.parseBoolean(props.getProperty("enableAutoMigration", "true"));
            enableBalanceAdjustment = Boolean.parseBoolean(props.getProperty("enableBalanceAdjustment", "true"));
            enableResourceProcessing = Boolean.parseBoolean(props.getProperty("enableResourceProcessing", "true"));
            enableValidation = Boolean.parseBoolean(props.getProperty("enableValidation", "true"));
            enableAsyncProcessing = Boolean.parseBoolean(props.getProperty("enableAsyncProcessing", "false"));
            enableCaching = Boolean.parseBoolean(props.getProperty("enableCaching", "true"));
            maxConcurrentMigrations = Integer.parseInt(props.getProperty("maxConcurrentMigrations", "4"));
            downfallModPath = props.getProperty("downfallModPath", ".SlayTheSpireLibrary/崩坠卡牌/");
            outputPath = props.getProperty("outputPath", "src/main/java/EveryMonsterPlayCard/cards/monster/");
            
            // 加载排除列表
            String excluded = props.getProperty("excludedCardIds", "");
            excludedCardIds.clear();
            if (!excluded.isEmpty()) {
                String[] parts = excluded.split(",");
                for (String part : parts) {
                    excludedCardIds.add(part.trim());
                }
            }
            
            // 加载包含列表
            String included = props.getProperty("includedCardIds", "");
            includedCardIds.clear();
            if (!included.isEmpty()) {
                String[] parts = included.split(",");
                for (String part : parts) {
                    includedCardIds.add(part.trim());
                }
            }
            
        } catch (IOException e) {
            System.err.println("加载配置文件失败: " + e.getMessage());
        }
    }
    
    /**
     * 保存配置到文件
     */
    public void saveToFile() {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            Properties props = new Properties();
            
            // 保存配置项
            props.setProperty("enableAutoMigration", String.valueOf(enableAutoMigration));
            props.setProperty("enableBalanceAdjustment", String.valueOf(enableBalanceAdjustment));
            props.setProperty("enableResourceProcessing", String.valueOf(enableResourceProcessing));
            props.setProperty("enableValidation", String.valueOf(enableValidation));
            props.setProperty("enableAsyncProcessing", String.valueOf(enableAsyncProcessing));
            props.setProperty("enableCaching", String.valueOf(enableCaching));
            props.setProperty("maxConcurrentMigrations", String.valueOf(maxConcurrentMigrations));
            props.setProperty("downfallModPath", downfallModPath);
            props.setProperty("outputPath", outputPath);
            
            // 保存列表
            props.setProperty("excludedCardIds", String.join(",", excludedCardIds));
            props.setProperty("includedCardIds", String.join(",", includedCardIds));
            
            props.store(fos, "Downfall卡牌移植配置");
            
        } catch (IOException e) {
            System.err.println("保存配置文件失败: " + e.getMessage());
        }
    }
    
    /**
     * 重新加载配置
     */
    public void reload() {
        loadFromFile();
    }
    
    /**
     * 检查卡牌是否被排除
     */
    public boolean isCardExcluded(String cardId) {
        return excludedCardIds.contains(cardId);
    }
    
    /**
     * 检查卡牌是否被包含
     */
    public boolean isCardIncluded(String cardId) {
        // 如果包含列表为空，则包含所有卡牌
        if (includedCardIds.isEmpty()) {
            return true;
        }
        return includedCardIds.contains(cardId);
    }
    
    /**
     * 检查卡牌是否应该被处理
     */
    public boolean shouldProcessCard(String cardId) {
        return !isCardExcluded(cardId) && isCardIncluded(cardId);
    }
    
    /**
     * 获取模板映射
     */
    public String getTemplateMapping(String cardId) {
        return templateMappings.get(cardId);
    }
    
    /**
     * 设置模板映射
     */
    public void setTemplateMapping(String cardId, String template) {
        templateMappings.put(cardId, template);
    }
    
    /**
     * 获取自定义平衡规则
     */
    public String getCustomBalanceRule(String ruleName) {
        return customBalanceRules.get(ruleName);
    }
    
    /**
     * 设置自定义平衡规则
     */
    public void setCustomBalanceRule(String ruleName, String rule) {
        customBalanceRules.put(ruleName, rule);
    }
    
    /**
     * 重置为默认配置
     */
    public void resetToDefaults() {
        loadDefaultValues();
        saveToFile();
    }
    
    /**
     * 获取配置摘要
     */
    public String getConfigSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Downfall卡牌移植配置摘要:\n");
        sb.append("  自动移植: ").append(enableAutoMigration ? "启用" : "禁用").append("\n");
        sb.append("  平衡性调整: ").append(enableBalanceAdjustment ? "启用" : "禁用").append("\n");
        sb.append("  资源处理: ").append(enableResourceProcessing ? "启用" : "禁用").append("\n");
        sb.append("  验证: ").append(enableValidation ? "启用" : "禁用").append("\n");
        sb.append("  异步处理: ").append(enableAsyncProcessing ? "启用" : "禁用").append("\n");
        sb.append("  缓存: ").append(enableCaching ? "启用" : "禁用").append("\n");
        sb.append("  最大并发数: ").append(maxConcurrentMigrations).append("\n");
        sb.append("  Downfall模组路径: ").append(downfallModPath).append("\n");
        sb.append("  输出路径: ").append(outputPath).append("\n");
        sb.append("  排除卡牌数: ").append(excludedCardIds.size()).append("\n");
        sb.append("  包含卡牌数: ").append(includedCardIds.size()).append("\n");
        
        return sb.toString();
    }
    
    // Getters and setters
    public boolean isEnableAutoMigration() { return enableAutoMigration; }
    public void setEnableAutoMigration(boolean enableAutoMigration) { 
        this.enableAutoMigration = enableAutoMigration; 
    }
    
    public boolean isEnableBalanceAdjustment() { return enableBalanceAdjustment; }
    public void setEnableBalanceAdjustment(boolean enableBalanceAdjustment) { 
        this.enableBalanceAdjustment = enableBalanceAdjustment; 
    }
    
    public boolean isEnableResourceProcessing() { return enableResourceProcessing; }
    public void setEnableResourceProcessing(boolean enableResourceProcessing) { 
        this.enableResourceProcessing = enableResourceProcessing; 
    }
    
    public boolean isEnableValidation() { return enableValidation; }
    public void setEnableValidation(boolean enableValidation) { 
        this.enableValidation = enableValidation; 
    }
    
    public boolean isEnableAsyncProcessing() { return enableAsyncProcessing; }
    public void setEnableAsyncProcessing(boolean enableAsyncProcessing) { 
        this.enableAsyncProcessing = enableAsyncProcessing; 
    }
    
    public boolean isEnableCaching() { return enableCaching; }
    public void setEnableCaching(boolean enableCaching) { 
        this.enableCaching = enableCaching; 
    }
    
    public int getMaxConcurrentMigrations() { return maxConcurrentMigrations; }
    public void setMaxConcurrentMigrations(int maxConcurrentMigrations) { 
        this.maxConcurrentMigrations = maxConcurrentMigrations; 
    }
    
    public String getDownfallModPath() { return downfallModPath; }
    public void setDownfallModPath(String downfallModPath) { 
        this.downfallModPath = downfallModPath; 
    }
    
    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { 
        this.outputPath = outputPath; 
    }
    
    public List<String> getExcludedCardIds() { return new ArrayList<>(excludedCardIds); }
    public void setExcludedCardIds(List<String> excludedCardIds) { 
        this.excludedCardIds = new ArrayList<>(excludedCardIds); 
    }
    
    public List<String> getIncludedCardIds() { return new ArrayList<>(includedCardIds); }
    public void setIncludedCardIds(List<String> includedCardIds) { 
        this.includedCardIds = new ArrayList<>(includedCardIds); 
    }
    
    public Map<String, String> getCustomBalanceRules() { return new HashMap<>(customBalanceRules); }
    public void setCustomBalanceRules(Map<String, String> customBalanceRules) { 
        this.customBalanceRules = new HashMap<>(customBalanceRules); 
    }
    
    public Map<String, String> getTemplateMappings() { return new HashMap<>(templateMappings); }
    public void setTemplateMappings(Map<String, String> templateMappings) { 
        this.templateMappings = new HashMap<>(templateMappings); 
    }
}