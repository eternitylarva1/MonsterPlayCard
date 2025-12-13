package EveryMonsterPlayCard.downfall.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 卡牌分析结果
 */
public class CardAnalysisResult {
    private String cardId;
    private String cardName;
    private AbstractCard.CardType cardType;
    private AbstractCard.CardColor cardColor;
    private AbstractCard.CardRarity cardRarity;
    private AbstractCard.CardTarget cardTarget;
    private int cost;
    private int baseDamage;
    private int baseBlock;
    private int baseMagicNumber;
    private List<String> keywords;
    private List<String> actions;
    private List<String> powers;
    private List<String> dependencies;
    private String description;
    private String rawSource;
    private boolean isUpgradable;
    private int upgradeDamage;
    private int upgradeBlock;
    private int upgradeMagicNumber;
    
    public CardAnalysisResult() {
        keywords = new ArrayList<>();
        actions = new ArrayList<>();
        powers = new ArrayList<>();
        dependencies = new ArrayList<>();
    }
    
    // Getters and setters
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    
    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    
    public AbstractCard.CardType getCardType() { return cardType; }
    public void setCardType(AbstractCard.CardType cardType) { this.cardType = cardType; }
    
    public AbstractCard.CardColor getCardColor() { return cardColor; }
    public void setCardColor(AbstractCard.CardColor cardColor) { this.cardColor = cardColor; }
    
    public AbstractCard.CardRarity getCardRarity() { return cardRarity; }
    public void setCardRarity(AbstractCard.CardRarity cardRarity) { this.cardRarity = cardRarity; }
    
    public AbstractCard.CardTarget getCardTarget() { return cardTarget; }
    public void setCardTarget(AbstractCard.CardTarget cardTarget) { this.cardTarget = cardTarget; }
    
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
    
    public int getBaseDamage() { return baseDamage; }
    public void setBaseDamage(int baseDamage) { this.baseDamage = baseDamage; }
    
    public int getBaseBlock() { return baseBlock; }
    public void setBaseBlock(int baseBlock) { this.baseBlock = baseBlock; }
    
    public int getBaseMagicNumber() { return baseMagicNumber; }
    public void setBaseMagicNumber(int baseMagicNumber) { this.baseMagicNumber = baseMagicNumber; }
    
    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }
    
    public List<String> getActions() { return actions; }
    public void setActions(List<String> actions) { this.actions = actions; }
    
    public List<String> getPowers() { return powers; }
    public void setPowers(List<String> powers) { this.powers = powers; }
    
    public List<String> getDependencies() { return dependencies; }
    public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getRawSource() { return rawSource; }
    public void setRawSource(String rawSource) { this.rawSource = rawSource; }
    
    public boolean isUpgradable() { return isUpgradable; }
    public void setUpgradable(boolean upgradable) { isUpgradable = upgradable; }
    
    public int getUpgradeDamage() { return upgradeDamage; }
    public void setUpgradeDamage(int upgradeDamage) { this.upgradeDamage = upgradeDamage; }
    
    public int getUpgradeBlock() { return upgradeBlock; }
    public void setUpgradeBlock(int upgradeBlock) { this.upgradeBlock = upgradeBlock; }
    
    public int getUpgradeMagicNumber() { return upgradeMagicNumber; }
    public void setUpgradeMagicNumber(int upgradeMagicNumber) { this.upgradeMagicNumber = upgradeMagicNumber; }
}