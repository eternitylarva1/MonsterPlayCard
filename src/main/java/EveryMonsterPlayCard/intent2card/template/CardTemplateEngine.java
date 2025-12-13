package EveryMonsterPlayCard.intent2card.template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 卡牌模板引擎
 * 管理所有卡牌模板，并根据意图选择最合适的模板
 */
public class CardTemplateEngine {
    
    private static CardTemplateEngine instance;
    private Map<String, CardTemplate> templates;
    private List<CardTemplate> sortedTemplates;
    
    private CardTemplateEngine() {
        this.templates = new ConcurrentHashMap<>();
        this.sortedTemplates = new ArrayList<>();
        initializeDefaultTemplates();
    }
    
    /**
     * 获取单例实例
     */
    public static CardTemplateEngine getInstance() {
        if (instance == null) {
            synchronized (CardTemplateEngine.class) {
                if (instance == null) {
                    instance = new CardTemplateEngine();
                }
            }
        }
        return instance;
    }
    
    /**
     * 初始化默认模板
     */
    private void initializeDefaultTemplates() {
        // 注册默认模板
        registerTemplate(new StandardCardTemplate());
        registerTemplate(new AttackCardTemplate());
        registerTemplate(new SkillCardTemplate());
        registerTemplate(new PowerCardTemplate());
        registerTemplate(new CustomCardTemplate());
        
        // 重新排序模板
        resortTemplates();
    }
    
    /**
     * 注册卡牌模板
     */
    public void registerTemplate(CardTemplate template) {
        if (template != null && template.getTemplateName() != null) {
            templates.put(template.getTemplateName(), template);
            
            // 检查是否已存在
            boolean exists = false;
            for (CardTemplate existing : sortedTemplates) {
                if (existing.getTemplateName().equals(template.getTemplateName())) {
                    exists = true;
                    break;
                }
            }
            
            if (!exists) {
                sortedTemplates.add(template);
                resortTemplates();
            }
        }
    }
    
    /**
     * 注销卡牌模板
     */
    public void unregisterTemplate(String templateName) {
        if (templateName != null) {
            templates.remove(templateName);
            sortedTemplates.removeIf(template -> template.getTemplateName().equals(templateName));
        }
    }
    
    /**
     * 获取卡牌模板
     */
    public CardTemplate getTemplate(String templateName) {
        return templates.get(templateName);
    }
    
    /**
     * 获取所有模板
     */
    public List<CardTemplate> getAllTemplates() {
        return new ArrayList<>(sortedTemplates);
    }
    
    /**
     * 根据意图选择最佳模板
     */
    public CardTemplate selectBestTemplate(MonsterIntent intent, IntentParameters parameters) {
        if (intent == null) {
            return null;
        }
        
        List<TemplateMatch> matches = new ArrayList<>();
        
        // 计算每个模板的匹配度
        for (CardTemplate template : sortedTemplates) {
            if (template.canHandle(intent)) {
                double matchScore = template.calculateMatchScore(intent, parameters);
                matches.add(new TemplateMatch(template, matchScore));
            }
        }
        
        // 如果没有匹配的模板，返回null
        if (matches.isEmpty()) {
            return null;
        }
        
        // 按匹配度排序，选择最佳匹配
        matches.sort(Comparator.comparingDouble(TemplateMatch::getScore).reversed());
        
        return matches.get(0).getTemplate();
    }
    
    /**
     * 根据意图类型获取模板
     */
    public List<CardTemplate> getTemplatesForIntentType(IntentType intentType) {
        List<CardTemplate> result = new ArrayList<>();
        
        for (CardTemplate template : sortedTemplates) {
            IntentType[] supportedTypes = template.getSupportedIntentTypes();
            for (IntentType supportedType : supportedTypes) {
                if (supportedType == intentType) {
                    result.add(template);
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * 使用指定模板创建卡牌
     */
    public AbstractCard createCardWithTemplate(String templateName, MonsterIntent intent, IntentParameters parameters) {
        CardTemplate template = getTemplate(templateName);
        if (template == null) {
            return null;
        }
        
        if (!template.canHandle(intent)) {
            return null;
        }
        
        return template.createCard(intent, parameters);
    }
    
    /**
     * 使用最佳模板创建卡牌
     */
    public AbstractCard createCardWithBestTemplate(MonsterIntent intent, IntentParameters parameters) {
        CardTemplate template = selectBestTemplate(intent, parameters);
        if (template == null) {
            return null;
        }
        
        return template.createCard(intent, parameters);
    }
    
    /**
     * 创建多个卡牌选项
     */
    public List<CardCreationOption> createCardOptions(MonsterIntent intent, IntentParameters parameters, int maxOptions) {
        List<CardCreationOption> options = new ArrayList<>();
        
        // 获取所有可以处理该意图的模板
        List<TemplateMatch> matches = new ArrayList<>();
        for (CardTemplate template : sortedTemplates) {
            if (template.canHandle(intent)) {
                double matchScore = template.calculateMatchScore(intent, parameters);
                matches.add(new TemplateMatch(template, matchScore));
            }
        }
        
        // 按匹配度排序
        matches.sort(Comparator.comparingDouble(TemplateMatch::getScore).reversed());
        
        // 创建卡牌选项
        int optionCount = Math.min(maxOptions, matches.size());
        for (int i = 0; i < optionCount; i++) {
            TemplateMatch match = matches.get(i);
            AbstractCard card = match.getTemplate().createCard(intent, parameters);
            if (card != null) {
                options.add(new CardCreationOption(card, match.getTemplate(), match.getScore()));
            }
        }
        
        return options;
    }
    
    /**
     * 重新排序模板
     */
    private void resortTemplates() {
        sortedTemplates.sort(Comparator.comparingInt(CardTemplate::getTemplatePriority));
    }
    
    /**
     * 清空所有模板
     */
    public void clearTemplates() {
        templates.clear();
        sortedTemplates.clear();
    }
    
    /**
     * 重置为默认模板
     */
    public void resetToDefaults() {
        clearTemplates();
        initializeDefaultTemplates();
    }
    
    /**
     * 获取模板统计信息
     */
    public TemplateStatistics getStatistics() {
        TemplateStatistics stats = new TemplateStatistics();
        
        stats.totalTemplates = templates.size();
        stats.templateNames = new ArrayList<>(templates.keySet());
        
        // 统计各意图类型的支持情况
        for (IntentType type : IntentType.values()) {
            int count = 0;
            for (CardTemplate template : sortedTemplates) {
                for (IntentType supportedType : template.getSupportedIntentTypes()) {
                    if (supportedType == type) {
                        count++;
                        break;
                    }
                }
            }
            stats.supportCountByType.put(type, count);
        }
        
        return stats;
    }
    
    /**
     * 模板匹配结果
     */
    private static class TemplateMatch {
        private final CardTemplate template;
        private final double score;
        
        public TemplateMatch(CardTemplate template, double score) {
            this.template = template;
            this.score = score;
        }
        
        public CardTemplate getTemplate() {
            return template;
        }
        
        public double getScore() {
            return score;
        }
    }
    
    /**
     * 卡牌创建选项
     */
    public static class CardCreationOption {
        private final AbstractCard card;
        private final CardTemplate template;
        private final double matchScore;
        
        public CardCreationOption(AbstractCard card, CardTemplate template, double matchScore) {
            this.card = card;
            this.template = template;
            this.matchScore = matchScore;
        }
        
        public AbstractCard getCard() {
            return card;
        }
        
        public CardTemplate getTemplate() {
            return template;
        }
        
        public double getMatchScore() {
            return matchScore;
        }
    }
    
    /**
     * 模板统计信息
     */
    public static class TemplateStatistics {
        public int totalTemplates;
        public List<String> templateNames;
        public Map<IntentType, Integer> supportCountByType = new ConcurrentHashMap<>();
        
        @Override
        public String toString() {
            return "TemplateStatistics{" +
                    "totalTemplates=" + totalTemplates +
                    ", templateNames=" + templateNames +
                    ", supportCountByType=" + supportCountByType +
                    '}';
        }
    }
}