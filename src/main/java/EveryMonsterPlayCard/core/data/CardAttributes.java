package EveryMonsterPlayCard.core.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 卡牌属性
 */
public class CardAttributes {
    private String name;
    private String description;
    private int cost;
    private AbstractCard.CardType type;
    private AbstractCard.CardColor color;
    private AbstractCard.CardRarity rarity;
    private AbstractCard.CardTarget target;
    private int damage;
    private int block;
    private int magicNumber;
    private List<String> tags;
    private Map<String, Object> properties;
    
    public CardAttributes() {
        this.cost = -1;
        this.damage = -1;
        this.block = -1;
        this.magicNumber = -1;
        this.tags = new ArrayList<>();
        this.properties = new HashMap<>();
    }
    
    public CardAttributes(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getCost() {
        return cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public AbstractCard.CardType getType() {
        return type;
    }
    
    public void setType(AbstractCard.CardType type) {
        this.type = type;
    }
    
    public AbstractCard.CardColor getColor() {
        return color;
    }
    
    public void setColor(AbstractCard.CardColor color) {
        this.color = color;
    }
    
    public AbstractCard.CardRarity getRarity() {
        return rarity;
    }
    
    public void setRarity(AbstractCard.CardRarity rarity) {
        this.rarity = rarity;
    }
    
    public AbstractCard.CardTarget getTarget() {
        return target;
    }
    
    public void setTarget(AbstractCard.CardTarget target) {
        this.target = target;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public int getBlock() {
        return block;
    }
    
    public void setBlock(int block) {
        this.block = block;
    }
    
    public int getMagicNumber() {
        return magicNumber;
    }
    
    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    public void addTag(String tag) {
        this.tags.add(tag);
    }
    
    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }
    
    public Map<String, Object> getProperties() {
        return properties;
    }
    
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }
    
    public Object getProperty(String key) {
        return this.properties.get(key);
    }
    
    public boolean hasProperty(String key) {
        return this.properties.containsKey(key);
    }
    
    /**
     * 检查是否为攻击卡牌
     */
    public boolean isAttack() {
        return type == AbstractCard.CardType.ATTACK;
    }
    
    /**
     * 检查是否为技能卡牌
     */
    public boolean isSkill() {
        return type == AbstractCard.CardType.SKILL;
    }
    
    /**
     * 检查是否为能力卡牌
     */
    public boolean isPower() {
        return type == AbstractCard.CardType.POWER;
    }
    
    /**
     * 检查是否为诅咒卡牌
     */
    public boolean isCurse() {
        return type == AbstractCard.CardType.CURSE;
    }
    
    /**
     * 检查是否为状态卡牌
     */
    public boolean isStatus() {
        return type == AbstractCard.CardType.STATUS;
    }
    
    /**
     * 检查是否有伤害
     */
    public boolean hasDamage() {
        return damage > 0;
    }
    
    /**
     * 检查是否有格挡
     */
    public boolean hasBlock() {
        return block > 0;
    }
    
    /**
     * 检查是否有魔法数字
     */
    public boolean hasMagicNumber() {
        return magicNumber > 0;
    }
    
    @Override
    public String toString() {
        return "CardAttributes{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", type=" + type +
                ", damage=" + damage +
                ", block=" + block +
                ", magicNumber=" + magicNumber +
                '}';
    }
}