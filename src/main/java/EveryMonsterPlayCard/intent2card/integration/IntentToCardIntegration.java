package EveryMonsterPlayCard.intent2card.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.interfaces.IIntentToCardConverter;
import EveryMonsterPlayCard.intent2card.service.IntentToCardService;
import EveryMonsterPlayCard.monstercards.MonsterCardConfig;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 意图-卡牌转换系统集成类
 * 负责将意图-卡牌转换系统集成到现有的MonsterPlayCard系统中
 */
public class IntentToCardIntegration {
    
    // 单例实例
    private static IntentToCardIntegration instance;
    
    // 意图-卡牌转换服务
    private IntentToCardService intentToCardService;
    
    // 怪物意图缓存
    private Map<String, MonsterIntent> monsterIntents;
    
    // 生成的卡牌缓存
    private Map<String, List<AbstractCard>> generatedCards;
    
    // 集成配置
    private boolean enableIntegration = true;
    private boolean enableDynamicGeneration = true;
    private boolean enableFallbackToConfig = true;
    
    private IntentToCardIntegration() {
        this.intentToCardService = IntentToCardService.getInstance();
        this.monsterIntents = new HashMap<>();
        this.generatedCards = new HashMap<>();
    }
    
    /**
     * 获取集成实例
     */
    public static synchronized IntentToCardIntegration getInstance() {
        if (instance == null) {
            instance = new IntentToCardIntegration();
        }
        return instance;
    }
    
    /**
     * 初始化集成系统
     */
    public void initialize() {
        if (!enableIntegration) {
            Hpr.info("意图-卡牌转换系统集成已禁用");
            return;
        }
        
        // 配置意图-卡牌转换服务
        configureIntentToCardService();
        
        // 预热缓存
        warmupCache();
        
        Hpr.info("意图-卡牌转换系统集成已初始化");
    }
    
    /**
     * 配置意图-卡牌转换服务
     */
    private void configureIntentToCardService() {
        IntentToCardService.ServiceConfig config = new IntentToCardService.ServiceConfig();
        
        // 启用所有功能
        config.enableCaching = true;
        config.enableValidation = true;
        config.enableOptimization = true;
        config.enableFallbackGeneration = true;
        
        // 配置缓存
        // 配置缓存
        config.cacheConfig = new EveryMonsterPlayCard.intent2card.service.CardCacheService.CacheConfig();
        config.cacheConfig.enabled = true;
        config.cacheConfig.maxSize = 50;
        config.cacheConfig.enableTTL = true;
        config.cacheConfig.ttlMillis = 10 * 60 * 1000; // 10分钟
        
        // 配置验证
        config.validationConfig = new EveryMonsterPlayCard.intent2card.service.CardValidationService.ValidationConfig();
        
        // 配置优化
        config.optimizationConfig = new EveryMonsterPlayCard.intent2card.service.CardOptimizationService.OptimizationConfig();
        config.optimizationConfig.enableDescriptionOptimization = true;
        config.optimizationConfig.enableNameOptimization = true;
        config.optimizationConfig.enableCostOptimization = true;
        config.optimizationConfig.enableRarityOptimization = true;
        
        // 应用配置
        intentToCardService.configureService(config);
    }
    
    /**
     * 预热缓存
     */
    private void warmupCache() {
        List<MonsterIntent> commonIntents = createCommonIntents();
        intentToCardService.warmupCache(commonIntents);
        Hpr.info("意图-卡牌转换服务缓存已预热，包含 " + commonIntents.size() + " 个常见意图");
    }
    
    /**
     * 创建常见意图列表
     */
    private List<MonsterIntent> createCommonIntents() {
        List<MonsterIntent> intents = new ArrayList<>();
        
        // 常见攻击意图
        intents.add(new MonsterIntent(IntentType.ATTACK, 5));
        intents.add(new MonsterIntent(IntentType.ATTACK, 8));
        intents.add(new MonsterIntent(IntentType.ATTACK, 12));
        intents.add(new MonsterIntent(IntentType.STRONG, 15));
        intents.add(new MonsterIntent(IntentType.STRONG, 20));
        
        // 常见防御意图
        intents.add(new MonsterIntent(IntentType.DEFEND, 5));
        intents.add(new MonsterIntent(IntentType.DEFEND, 8));
        intents.add(new MonsterIntent(IntentType.DEFEND, 12));
        
        // 常见增益意图
        intents.add(new MonsterIntent(IntentType.BUFF, 2));
        intents.add(new MonsterIntent(IntentType.BUFF, 3));
        intents.add(new MonsterIntent(IntentType.BUFF, 5));
        
        // 常见减益意图
        intents.add(new MonsterIntent(IntentType.DEBUFF, 2));
        intents.add(new MonsterIntent(IntentType.DEBUFF, 3));
        intents.add(new MonsterIntent(IntentType.DEBUFF, 5));
        
        return intents;
    }
    
    /**
     * 为怪物生成卡牌
     */
    public List<AbstractCard> generateCardsForMonster(AbstractMonster monster) {
        if (!enableIntegration || monster == null) {
            return getFallbackCards(monster);
        }
        
        String monsterId = monster.id;
        
        // 检查是否已经生成过卡牌
        if (generatedCards.containsKey(monsterId)) {
            return generatedCards.get(monsterId);
        }
        
        // 获取怪物的意图
        MonsterIntent intent = getMonsterIntent(monster);
        if (intent == null) {
            Hpr.info("无法获取怪物 " + monster.name + " 的意图，使用回退卡牌");
            return getFallbackCards(monster);
        }
        
        // 生成卡牌
        List<AbstractCard> cards = generateCardsFromIntent(intent, monster);
        
        // 缓存生成的卡牌
        generatedCards.put(monsterId, cards);
        
        Hpr.info("为怪物 " + monster.name + " 生成了 " + cards.size() + " 张卡牌");
        
        return cards;
    }
    
    /**
     * 获取怪物的意图
     */
    private MonsterIntent getMonsterIntent(AbstractMonster monster) {
        if (monster == null) {
            return null;
        }
        
        String monsterId = monster.id;
        
        // 检查缓存
        if (monsterIntents.containsKey(monsterId)) {
            return monsterIntents.get(monsterId);
        }
        
        // 从怪物的当前意图创建意图对象
        MonsterIntent intent = createIntentFromMonster(monster);
        
        // 缓存意图
        monsterIntents.put(monsterId, intent);
        
        return intent;
    }
    
    /**
     * 从怪物创建意图对象
     */
    private MonsterIntent createIntentFromMonster(AbstractMonster monster) {
        IntentType intentType = IntentType.UNKNOWN;
        int amount = 0;
        
        // 根据怪物的意图类型映射
        if (monster.intent != null) {
            String intentName = monster.intent.name();
            switch (intentName) {
                case "ATTACK":
                case "ATTACK_BUFF":
                case "ATTACK_DEBUFF":
                case "ATTACK_DEFEND":
                    intentType = IntentType.ATTACK;
                    break;
                case "DEFEND":
                case "DEFEND_BUFF":
                case "DEFEND_DEBUFF":
                    intentType = IntentType.DEFEND;
                    break;
                case "BUFF":
                    intentType = IntentType.BUFF;
                    break;
                case "DEBUFF":
                    intentType = IntentType.DEBUFF;
                    break;
                case "STRONG":
                    intentType = IntentType.STRONG;
                    break;
                case "ESCAPE":
                    intentType = IntentType.ESCAPE;
                    break;
                default:
                    intentType = IntentType.UNKNOWN;
                    break;
            }
        }
        
        // 获取意图数量
        // 使用反射获取私有字段
        try {
            java.lang.reflect.Field intentDmgField = AbstractMonster.class.getDeclaredField("intentDmg");
            intentDmgField.setAccessible(true);
            int intentDmg = intentDmgField.getInt(monster);
            if (intentDmg > 0) {
                amount = intentDmg;
            }
        } catch (Exception e) {
            // 忽略错误，使用默认值
        }
        
        try {
            java.lang.reflect.Field intentBaseDmgField = AbstractMonster.class.getDeclaredField("intentBaseDmg");
            intentBaseDmgField.setAccessible(true);
            int intentBaseDmg = intentBaseDmgField.getInt(monster);
            if (intentBaseDmg > 0 && amount == 0) {
                amount = intentBaseDmg;
            }
        } catch (Exception e) {
            // 忽略错误，使用默认值
        }
        
        // 创建意图对象
        MonsterIntent intent = new MonsterIntent(intentType, amount, monster);
        
        // 添加额外属性
        if (monster.intent != null) {
            intent.setProperty("originalIntent", monster.intent.name());
        }
        
        if (monster.moveName != null) {
            intent.setProperty("moveName", monster.moveName);
        }
        
        return intent;
    }
    
    /**
     * 从意图生成卡牌
     */
    private List<AbstractCard> generateCardsFromIntent(MonsterIntent intent, AbstractMonster monster) {
        List<AbstractCard> cards = new ArrayList<>();
        
        // 生成主要卡牌
        AbstractCard mainCard = intentToCardService.convertIntentToCard(intent);
        if (mainCard != null) {
            cards.add(mainCard);
        }
        
        // 生成辅助卡牌
        List<AbstractCard> supportCards = generateSupportCards(intent, monster);
        cards.addAll(supportCards);
        
        // 确保至少有一定数量的卡牌
        while (cards.size() < 5) {
            AbstractCard fillerCard = generateFillerCard(intent, monster);
            if (fillerCard != null) {
                cards.add(fillerCard);
            } else {
                break;
            }
        }
        
        return cards;
    }
    
    /**
     * 生成辅助卡牌
     */
    private List<AbstractCard> generateSupportCards(MonsterIntent intent, AbstractMonster monster) {
        List<AbstractCard> supportCards = new ArrayList<>();
        
        // 根据主要意图类型生成辅助卡牌
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                // 攻击意图添加防御卡牌
                MonsterIntent defendIntent = new MonsterIntent(IntentType.DEFEND, intent.getAmount() / 2, monster);
                AbstractCard defendCard = intentToCardService.convertIntentToCard(defendIntent);
                if (defendCard != null) {
                    supportCards.add(defendCard);
                }
                break;
                
            case DEFEND:
                // 防御意图添加攻击卡牌
                MonsterIntent attackIntent = new MonsterIntent(IntentType.ATTACK, intent.getAmount() / 2, monster);
                AbstractCard attackCard = intentToCardService.convertIntentToCard(attackIntent);
                if (attackCard != null) {
                    supportCards.add(attackCard);
                }
                break;
                
            case BUFF:
            case DEBUFF:
                // 增益/减益意图添加攻击和防御卡牌
                MonsterIntent mixedIntent1 = new MonsterIntent(IntentType.ATTACK, intent.getAmount(), monster);
                AbstractCard mixedCard1 = intentToCardService.convertIntentToCard(mixedIntent1);
                if (mixedCard1 != null) {
                    supportCards.add(mixedCard1);
                }
                
                MonsterIntent mixedIntent2 = new MonsterIntent(IntentType.DEFEND, intent.getAmount(), monster);
                AbstractCard mixedCard2 = intentToCardService.convertIntentToCard(mixedIntent2);
                if (mixedCard2 != null) {
                    supportCards.add(mixedCard2);
                }
                break;
        }
        
        return supportCards;
    }
    
    /**
     * 生成填充卡牌
     */
    private AbstractCard generateFillerCard(MonsterIntent intent, AbstractMonster monster) {
        // 生成随机类型的意图
        IntentType[] randomTypes = {IntentType.ATTACK, IntentType.DEFEND, IntentType.BUFF, IntentType.DEBUFF};
        IntentType randomType = randomTypes[(int) (Math.random() * randomTypes.length)];
        
        // 生成随机数量
        int randomAmount = 3 + (int) (Math.random() * 7); // 3-9
        
        MonsterIntent randomIntent = new MonsterIntent(randomType, randomAmount, monster);
        return intentToCardService.convertIntentToCard(randomIntent);
    }
    
    /**
     * 获取回退卡牌
     */
    private List<AbstractCard> getFallbackCards(AbstractMonster monster) {
        if (!enableFallbackToConfig) {
            return new ArrayList<>();
        }
        
        // 使用现有的MonsterCardConfig系统
        MonsterCardConfig config = MonsterCardConfig.getInstance();
        return config.getMonsterCardConfig(monster.id);
    }
    
    /**
     * 更新怪物意图
     */
    public void updateMonsterIntent(AbstractMonster monster) {
        if (!enableIntegration || monster == null) {
            return;
        }
        
        // 创建新的意图
        MonsterIntent newIntent = createIntentFromMonster(monster);
        
        // 更新缓存
        monsterIntents.put(monster.id, newIntent);
        
        // 清除生成的卡牌缓存，强制重新生成
        generatedCards.remove(monster.id);
        
        Hpr.info("更新怪物 " + monster.name + " 的意图: " + newIntent);
    }
    
    /**
     * 清除怪物缓存
     */
    public void clearMonsterCache(AbstractMonster monster) {
        if (monster == null) {
            return;
        }
        
        String monsterId = monster.id;
        monsterIntents.remove(monsterId);
        generatedCards.remove(monsterId);
        
        Hpr.info("清除怪物 " + monster.name + " 的缓存");
    }
    
    /**
     * 清除所有缓存
     */
    public void clearAllCache() {
        monsterIntents.clear();
        generatedCards.clear();
        intentToCardService.clearCache();
        
        Hpr.info("清除所有意图-卡牌转换缓存");
    }
    
    /**
     * 获取集成统计信息
     */
    public Map<String, Object> getIntegrationStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("enableIntegration", enableIntegration);
        stats.put("enableDynamicGeneration", enableDynamicGeneration);
        stats.put("enableFallbackToConfig", enableFallbackToConfig);
        
        stats.put("cachedMonsterIntents", monsterIntents.size());
        stats.put("cachedGeneratedCards", generatedCards.size());
        
        // 获取服务统计信息
        stats.put("intentToCardServiceStats", intentToCardService.getServiceStatus());
        
        return stats;
    }
    
    /**
     * 配置集成
     */
    public void configureIntegration(boolean enableIntegration, boolean enableDynamicGeneration, boolean enableFallbackToConfig) {
        this.enableIntegration = enableIntegration;
        this.enableDynamicGeneration = enableDynamicGeneration;
        this.enableFallbackToConfig = enableFallbackToConfig;
        
        Hpr.info("意图-卡牌转换系统集成配置已更新: " +
                "enableIntegration=" + enableIntegration +
                ", enableDynamicGeneration=" + enableDynamicGeneration +
                ", enableFallbackToConfig=" + enableFallbackToConfig);
    }
    
    /**
     * 检查集成是否启用
     */
    public boolean isIntegrationEnabled() {
        return enableIntegration;
    }
    
    /**
     * 检查动态生成是否启用
     */
    public boolean isDynamicGenerationEnabled() {
        return enableDynamicGeneration;
    }
    
    /**
     * 检查回退到配置是否启用
     */
    public boolean isFallbackToConfigEnabled() {
        return enableFallbackToConfig;
    }
}