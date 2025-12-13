package EveryMonsterPlayCard.downfall.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MigrationResult;

/**
 * 资源处理器
 * 处理卡牌移植过程中的资源相关操作
 */
public class ResourceProcessor {
    
    private String resourceBasePath;
    private String outputBasePath;
    private boolean enableImageProcessing;
    private boolean enableLocalizationProcessing;
    private boolean enableAudioProcessing;
    
    public ResourceProcessor() {
        this.resourceBasePath = ".SlayTheSpireLibrary/崩坠卡牌/";
        this.outputBasePath = "src/main/resources/everyMonsterPlayCardResources/";
        this.enableImageProcessing = true;
        this.enableLocalizationProcessing = true;
        this.enableAudioProcessing = true;
    }
    
    /**
     * 处理图像资源
     */
    public void processImageResources(AbstractCard originalCard, MigrationResult result) {
        if (!enableImageProcessing) {
            return;
        }
        
        try {
            // 查找原始图像资源
            String originalImagePath = findOriginalImagePath(originalCard);
            if (originalImagePath == null) {
                result.addWarning("未找到原始图像资源");
                return;
            }
            
            // 生成目标路径
            String targetImagePath = generateTargetImagePath(originalCard);
            
            // 复制图像文件
            copyImageResource(originalImagePath, targetImagePath);
            
            result.addWarning("图像资源已处理: " + targetImagePath);
            
        } catch (Exception e) {
            result.addError("图像资源处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 处理本地化资源
     */
    public void processLocalizationResources(AbstractCard originalCard, MigrationResult result) {
        if (!enableLocalizationProcessing) {
            return;
        }
        
        try {
            // 处理中文本本化
            processChineseLocalization(originalCard, result);
            
            // 处理英文本本化
            processEnglishLocalization(originalCard, result);
            
        } catch (Exception e) {
            result.addError("本地化资源处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 处理音频资源
     */
    public void processAudioResources(AbstractCard originalCard, MigrationResult result) {
        if (!enableAudioProcessing) {
            return;
        }
        
        try {
            // 查找原始音频资源
            String originalAudioPath = findOriginalAudioPath(originalCard);
            if (originalAudioPath == null) {
                // 大多数卡牌没有特殊音频，这是正常的
                return;
            }
            
            // 生成目标路径
            String targetAudioPath = generateTargetAudioPath(originalCard);
            
            // 复制音频文件
            copyAudioResource(originalAudioPath, targetAudioPath);
            
            result.addWarning("音频资源已处理: " + targetAudioPath);
            
        } catch (Exception e) {
            result.addError("音频资源处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 查找原始图像路径
     */
    private String findOriginalImagePath(AbstractCard card) {
        // 尝试多种可能的路径
        List<String> possiblePaths = new ArrayList<>();
        
        String cardId = card.cardID;
        if (cardId.contains(":")) {
            cardId = cardId.substring(cardId.indexOf(":") + 1);
        }
        
        // 基于卡牌ID的路径
        possiblePaths.add(resourceBasePath + "cards/images/" + cardId + ".png");
        possiblePaths.add(resourceBasePath + "cards/images/" + cardId + ".jpg");
        
        // 基于卡牌类型的路径
        String cardType = card.type.toString().toLowerCase();
        possiblePaths.add(resourceBasePath + "cards/images/" + cardType + "/" + cardId + ".png");
        possiblePaths.add(resourceBasePath + "cards/images/" + cardType + "/" + cardId + ".jpg");
        
        // 查找第一个存在的文件
        for (String path : possiblePaths) {
            if (Files.exists(Paths.get(path))) {
                return path;
            }
        }
        
        return null;
    }
    
    /**
     * 生成目标图像路径
     */
    private String generateTargetImagePath(AbstractCard card) {
        String cardId = card.cardID;
        if (cardId.contains(":")) {
            cardId = cardId.substring(cardId.indexOf(":") + 1);
        }
        
        String cardType = card.type.toString().toLowerCase();
        String cardColor = card.color.toString().toLowerCase();
        
        return outputBasePath + "images/cards/" + cardColor + "/" + cardType + "/" + cardId + ".png";
    }
    
    /**
     * 复制图像资源
     */
    private void copyImageResource(String sourcePath, String targetPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);
        
        // 创建目标目录
        Files.createDirectories(target.getParent());
        
        // 复制文件
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
    
    /**
     * 查找原始音频路径
     */
    private String findOriginalAudioPath(AbstractCard card) {
        String cardId = card.cardID;
        if (cardId.contains(":")) {
            cardId = cardId.substring(cardId.indexOf(":") + 1);
        }
        
        List<String> possiblePaths = new ArrayList<>();
        possiblePaths.add(resourceBasePath + "audio/" + cardId + ".ogg");
        possiblePaths.add(resourceBasePath + "audio/" + cardId + ".wav");
        possiblePaths.add(resourceBasePath + "audio/" + cardId + ".mp3");
        
        for (String path : possiblePaths) {
            if (Files.exists(Paths.get(path))) {
                return path;
            }
        }
        
        return null;
    }
    
    /**
     * 生成目标音频路径
     */
    private String generateTargetAudioPath(AbstractCard card) {
        String cardId = card.cardID;
        if (cardId.contains(":")) {
            cardId = cardId.substring(cardId.indexOf(":") + 1);
        }
        
        return outputBasePath + "audio/sounds/" + cardId + ".ogg";
    }
    
    /**
     * 复制音频资源
     */
    private void copyAudioResource(String sourcePath, String targetPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);
        
        // 创建目标目录
        Files.createDirectories(target.getParent());
        
        // 复制文件
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
    
    /**
     * 处理中文本本化
     */
    private void processChineseLocalization(AbstractCard card, MigrationResult result) {
        try {
            String localizationPath = outputBasePath + "localization/ZHS/cards.json";
            Path path = Paths.get(localizationPath);
            
            // 这里应该读取现有的本地化文件，添加新的卡牌条目
            // 简化实现，只记录警告
            result.addWarning("需要手动添加中文本本化: " + card.cardID);
            
        } catch (Exception e) {
            result.addError("中文本本化处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 处理英文本本化
     */
    private void processEnglishLocalization(AbstractCard card, MigrationResult result) {
        try {
            String localizationPath = outputBasePath + "localization/ENG/cards.json";
            Path path = Paths.get(localizationPath);
            
            // 简化实现，只记录警告
            result.addWarning("需要手动添加英文本本化: " + card.cardID);
            
        } catch (Exception e) {
            result.addError("英文本本化处理失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证资源完整性
     */
    public boolean validateResourceIntegrity(AbstractCard card) {
        // 检查必需的资源是否存在
        String imagePath = findOriginalImagePath(card);
        if (imagePath == null) {
            return false;
        }
        
        return Files.exists(Paths.get(imagePath));
    }
    
    /**
     * 获取资源处理统计
     */
    public ResourceProcessingStatistics getStatistics() {
        // 这里可以实现统计功能
        return new ResourceProcessingStatistics(0, 0, 0);
    }
    
    // Getters and setters
    public String getResourceBasePath() {
        return resourceBasePath;
    }
    
    public void setResourceBasePath(String resourceBasePath) {
        this.resourceBasePath = resourceBasePath;
    }
    
    public String getOutputBasePath() {
        return outputBasePath;
    }
    
    public void setOutputBasePath(String outputBasePath) {
        this.outputBasePath = outputBasePath;
    }
    
    public boolean isEnableImageProcessing() {
        return enableImageProcessing;
    }
    
    public void setEnableImageProcessing(boolean enableImageProcessing) {
        this.enableImageProcessing = enableImageProcessing;
    }
    
    public boolean isEnableLocalizationProcessing() {
        return enableLocalizationProcessing;
    }
    
    public void setEnableLocalizationProcessing(boolean enableLocalizationProcessing) {
        this.enableLocalizationProcessing = enableLocalizationProcessing;
    }
    
    public boolean isEnableAudioProcessing() {
        return enableAudioProcessing;
    }
    
    public void setEnableAudioProcessing(boolean enableAudioProcessing) {
        this.enableAudioProcessing = enableAudioProcessing;
    }
    
    /**
     * 资源处理统计信息
     */
    public static class ResourceProcessingStatistics {
        private final int imagesProcessed;
        private final int localizationsProcessed;
        private final int audioProcessed;
        
        public ResourceProcessingStatistics(int imagesProcessed, int localizationsProcessed, int audioProcessed) {
            this.imagesProcessed = imagesProcessed;
            this.localizationsProcessed = localizationsProcessed;
            this.audioProcessed = audioProcessed;
        }
        
        public int getImagesProcessed() { return imagesProcessed; }
        public int getLocalizationsProcessed() { return localizationsProcessed; }
        public int getAudioProcessed() { return audioProcessed; }
        
        @Override
        public String toString() {
            return String.format("ResourceProcessingStatistics{images=%d, localizations=%d, audio=%d}", 
                               imagesProcessed, localizationsProcessed, audioProcessed);
        }
    }
}