package EveryMonsterPlayCard.downfall.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Downfall卡牌列表
 * 管理Downfall模组中的所有卡牌信息
 */
public class DownfallCardList {
    
    private static final String CARDS_FILE = ".SlayTheSpireLibrary/崩坠卡牌/downfall-cards.txt";
    private static DownfallCardList instance;
    
    private Map<String, CardInfo> cardMap;
    private List<String> attackCards;
    private List<String> skillCards;
    private List<String> powerCards;
    private List<String> curseCards;
    private List<String> specialCards;
    
    private DownfallCardList() {
        this.cardMap = new HashMap<>();
        this.attackCards = new ArrayList<>();
        this.skillCards = new ArrayList<>();
        this.powerCards = new ArrayList<>();
        this.curseCards = new ArrayList<>();
        this.specialCards = new ArrayList<>();
        loadCardList();
    }
    
    /**
     * 获取实例（单例模式）
     */
    public static DownfallCardList getInstance() {
        if (instance == null) {
            instance = new DownfallCardList();
        }
        return instance;
    }
    
    /**
     * 加载卡牌列表
     */
    private void loadCardList() {
        // 尝试从文件加载
        if (!loadFromFile()) {
            // 如果文件不存在，使用硬编码的列表
            loadHardcodedList();
        }
    }
    
    /**
     * 从文件加载卡牌列表
     */
    private boolean loadFromFile() {
        File cardsFile = new File(CARDS_FILE);
        if (!cardsFile.exists()) {
            return false;
        }
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(CARDS_FILE));
            
            for (String line : lines) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue; // 跳过空行和注释
                }
                
                String[] parts = line.split(":", 3);
                if (parts.length >= 2) {
                    String cardId = parts[0].trim();
                    String cardType = parts[1].trim();
                    String cardName = parts.length > 2 ? parts[2].trim() : cardId;
                    
                    CardInfo cardInfo = new CardInfo(cardId, cardName, cardType);
                    cardMap.put(cardId, cardInfo);
                    
                    // 按类型分类
                    categorizeCard(cardId, cardType);
                }
            }
            
            return true;
            
        } catch (IOException e) {
            System.err.println("加载卡牌列表文件失败: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 加载硬编码的卡牌列表
     */
    private void loadHardcodedList() {
        // 基于已知Downfall卡牌的硬编码列表
        // 这里可以根据实际Downfall模组内容进行更新
        
        // 攻击卡牌
        addHardcodedCard("CharbossAnger", "愤怒", "ATTACK");
        addHardcodedCard("CharbossBash", "重击", "ATTACK");
        addHardcodedCard("CharbossCleave", "横扫", "ATTACK");
        addHardcodedCard("CharbossClothesline", "锁喉", "ATTACK");
        addHardcodedCard("CharbossPommelStrike", "酒鬼重击", "ATTACK");
        addHardcodedCard("CharbossThunderClap", "雷鸣掌击", "ATTACK");
        addHardcodedCard("CharbossWarcry", "战吼", "ATTACK");
        
        // 技能卡牌
        addHardcodedCard("CharbossDefend", "防御", "SKILL");
        addHardcodedCard("CharbossArmaments", "武装", "SKILL");
        addHardcodedCard("CharbossBodySlam", "猛击", "SKILL");
        addHardcodedCard("CharbossFlameBarrier", "火焰屏障", "SKILL");
        addHardcodedCard("CharbossGhostlyArmor", "幽灵护甲", "SKILL");
        addHardcodedCard("CharbossInfernalBlade", "地狱之刃", "SKILL");
        addHardcodedCard("CharbossIntimidate", "威吓", "SKILL");
        addHardcodedCard("CharbossRage", "狂怒", "SKILL");
        addHardcodedCard("CharbossRampage", "暴走", "SKILL");
        addHardcodedCard("CharbossRecklessCharge", "鲁莽冲锋", "SKILL");
        addHardcodedCard("CharbossShockwave", "冲击波", "SKILL");
        addHardcodedCard("CharbossTrueGrit", "真 grit", "SKILL");
        addHardcodedCard("CharbossVengeance", "复仇", "SKILL");
        
        // 能力卡牌
        addHardcodedCard("CharbossBerserk", "狂暴", "POWER");
        addHardcodedCard("CharbossCombust", "燃烧", "POWER");
        addHardcodedCard("CharbossDemonForm", "恶魔形态", "POWER");
        addHardcodedCard("CharbossFeelNoPain", "无痛觉", "POWER");
        addHardcodedCard("CharbossInflame", "燃烧", "POWER");
        addHardcodedCard("CharbossJuggernaut", "主宰", "POWER");
        addHardcodedCard("CharbossLimitBreak", "突破极限", "POWER");
        addHardcodedCard("CharbossRage", "愤怒", "POWER");
        addHardcodedCard("CharbossSentinel", "哨兵", "POWER");
        addHardcodedCard("CharbossSpotWeakness", "弱点洞察", "POWER");
        addHardcodedCard("CharbossVigor", "活力", "POWER");
        
        // 诅咒卡牌
        addHardcodedCard("CharbossAscendersBane", "飞升者之祸", "CURSE");
        addHardcodedCard("CharbossDoubt", "怀疑", "CURSE");
        addHardcodedCard("CharbossEnvy", "嫉妒", "CURSE");
        addHardcodedCard("CharbossGluttony", "暴食", "CURSE");
        addHardcodedCard("CharbossPride", "骄傲", "CURSE");
        addHardcodedCard("CharbossShame", "羞耻", "CURSE");
        addHardcodedCard("CharbossSloth", "懒惰", "CURSE");
        addHardcodedCard("CharbossWrath", "愤怒", "CURSE");
        
        // 特殊卡牌
        addHardcodedCard("CharbossBite", "啃咬", "SPECIAL");
        addHardcodedCard("CharbossConsume", "吞噬", "SPECIAL");
        addHardcodedCard("CharbossEruption", "爆发", "SPECIAL");
        addHardcodedCard("CharbossFeed", "喂食", "SPECIAL");
        addHardcodedCard("CharbossFlee", "逃跑", "SPECIAL");
        addHardcodedCard("CharbossGather", "聚集", "SPECIAL");
        addHardcodedCard("CharbossGrow", "成长", "SPECIAL");
        addHardcodedCard("CharbossHatch", "孵化", "SPECIAL");
        addHardcodedCard("CharbossReap", "收割", "SPECIAL");
        addHardcodedCard("CharbossRegret", "后悔", "SPECIAL");
        addHardcodedCard("CharbossRevel", "启示", "SPECIAL");
        addHardcodedCard("CharbossSadisticNature", "虐待本性", "SPECIAL");
        addHardcodedCard("CharbossSelfImproving", "自我提升", "SPECIAL");
        addHardcodedCard("CharbossShed", "蜕皮", "SPECIAL");
        addHardcodedCard("CharbossSkullBash", "骷髅重击", "SPECIAL");
        addHardcodedCard("CharbossTalk", "交谈", "SPECIAL");
        addHardcodedCard("CharbossTransfusion", "输血", "SPECIAL");
        addHardcodedCard("CharbossUnceasingTop", "无休止", "SPECIAL");
        addHardcodedCard("CharbossVacuum", "真空", "SPECIAL");
        addHardcodedCard("CharbossVengeance", "复仇", "SPECIAL");
    }
    
    /**
     * 添加硬编码卡牌
     */
    private void addHardcodedCard(String cardId, String cardName, String cardType) {
        CardInfo cardInfo = new CardInfo(cardId, cardName, cardType);
        cardMap.put(cardId, cardInfo);
        categorizeCard(cardId, cardType);
    }
    
    /**
     * 按类型分类卡牌
     */
    private void categorizeCard(String cardId, String cardType) {
        switch (cardType.toUpperCase()) {
            case "ATTACK":
                attackCards.add(cardId);
                break;
            case "SKILL":
                skillCards.add(cardId);
                break;
            case "POWER":
                powerCards.add(cardId);
                break;
            case "CURSE":
                curseCards.add(cardId);
                break;
            case "SPECIAL":
                specialCards.add(cardId);
                break;
            default:
                // 未知类型，默认为技能
                skillCards.add(cardId);
                break;
        }
    }
    
    /**
     * 获取所有卡牌
     */
    public Map<String, CardInfo> getAllCards() {
        return new HashMap<>(cardMap);
    }
    
    /**
     * 获取攻击卡牌
     */
    public List<String> getAttackCards() {
        return new ArrayList<>(attackCards);
    }
    
    /**
     * 获取技能卡牌
     */
    public List<String> getSkillCards() {
        return new ArrayList<>(skillCards);
    }
    
    /**
     * 获取能力卡牌
     */
    public List<String> getPowerCards() {
        return new ArrayList<>(powerCards);
    }
    
    /**
     * 获取诅咒卡牌
     */
    public List<String> getCurseCards() {
        return new ArrayList<>(curseCards);
    }
    
    /**
     * 获取特殊卡牌
     */
    public List<String> getSpecialCards() {
        return new ArrayList<>(specialCards);
    }
    
    /**
     * 获取卡牌信息
     */
    public CardInfo getCardInfo(String cardId) {
        return cardMap.get(cardId);
    }
    
    /**
     * 检查卡牌是否存在
     */
    public boolean hasCard(String cardId) {
        return cardMap.containsKey(cardId);
    }
    
    /**
     * 获取卡牌总数
     */
    public int getTotalCardCount() {
        return cardMap.size();
    }
    
    /**
     * 获取类型统计
     */
    public CardTypeStatistics getStatistics() {
        return new CardTypeStatistics(
            attackCards.size(),
            skillCards.size(),
            powerCards.size(),
            curseCards.size(),
            specialCards.size()
        );
    }
    
    /**
     * 重新加载卡牌列表
     */
    public void reload() {
        cardMap.clear();
        attackCards.clear();
        skillCards.clear();
        powerCards.clear();
        curseCards.clear();
        specialCards.clear();
        loadCardList();
    }
    
    /**
     * 卡牌信息
     */
    public static class CardInfo {
        private final String cardId;
        private final String cardName;
        private final String cardType;
        
        public CardInfo(String cardId, String cardName, String cardType) {
            this.cardId = cardId;
            this.cardName = cardName;
            this.cardType = cardType;
        }
        
        public String getCardId() { return cardId; }
        public String getCardName() { return cardName; }
        public String getCardType() { return cardType; }
        
        @Override
        public String toString() {
            return String.format("CardInfo{id='%s', name='%s', type='%s'}", 
                               cardId, cardName, cardType);
        }
    }
    
    /**
     * 卡牌类型统计
     */
    public static class CardTypeStatistics {
        private final int attackCount;
        private final int skillCount;
        private final int powerCount;
        private final int curseCount;
        private final int specialCount;
        
        public CardTypeStatistics(int attackCount, int skillCount, int powerCount, 
                              int curseCount, int specialCount) {
            this.attackCount = attackCount;
            this.skillCount = skillCount;
            this.powerCount = powerCount;
            this.curseCount = curseCount;
            this.specialCount = specialCount;
        }
        
        public int getAttackCount() { return attackCount; }
        public int getSkillCount() { return skillCount; }
        public int getPowerCount() { return powerCount; }
        public int getCurseCount() { return curseCount; }
        public int getSpecialCount() { return specialCount; }
        public int getTotalCount() { 
            return attackCount + skillCount + powerCount + curseCount + specialCount; 
        }
        
        @Override
        public String toString() {
            return String.format("CardTypeStatistics{attack=%d, skill=%d, power=%d, curse=%d, special=%d, total=%d}", 
                               attackCount, skillCount, powerCount, curseCount, specialCount, getTotalCount());
        }
    }
}