package EveryMonsterPlayCard.core.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 怪物意图数据结构
 */
public class MonsterIntent {
    private IntentType type;
    private int amount;
    private AbstractMonster source;
    private AbstractMonster target;
    private List<AbstractGameAction> componentActions;
    private IntentMetadata metadata;
    private Map<String, Object> properties;
    
    public MonsterIntent() {
        this.type = IntentType.UNKNOWN;
        this.amount = 0;
        this.componentActions = new ArrayList<>();
        this.metadata = new IntentMetadata();
        this.properties = new HashMap<>();
    }
    
    public MonsterIntent(IntentType type, int amount) {
        this();
        this.type = type;
        this.amount = amount;
    }
    
    public MonsterIntent(IntentType type, int amount, AbstractMonster source) {
        this(type, amount);
        this.source = source;
    }
    
    public MonsterIntent(IntentType type, int amount, AbstractMonster source, AbstractMonster target) {
        this(type, amount, source);
        this.target = target;
    }
    
    public IntentType getType() {
        return type;
    }
    
    public void setType(IntentType type) {
        this.type = type;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public AbstractMonster getSource() {
        return source;
    }
    
    public void setSource(AbstractMonster source) {
        this.source = source;
    }
    
    public AbstractMonster getTarget() {
        return target;
    }
    
    public void setTarget(AbstractMonster target) {
        this.target = target;
    }
    
    public List<AbstractGameAction> getComponentActions() {
        return componentActions;
    }
    
    public void setComponentActions(List<AbstractGameAction> componentActions) {
        this.componentActions = componentActions;
    }
    
    public void addComponentAction(AbstractGameAction action) {
        this.componentActions.add(action);
    }
    
    public IntentMetadata getMetadata() {
        return metadata;
    }
    
    public void setMetadata(IntentMetadata metadata) {
        this.metadata = metadata;
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
     * 检查是否为攻击意图
     */
    public boolean isAttack() {
        return type == IntentType.ATTACK || type == IntentType.STRONG;
    }
    
    /**
     * 检查是否为防御意图
     */
    public boolean isDefend() {
        return type == IntentType.DEFEND;
    }
    
    /**
     * 检查是否为增益意图
     */
    public boolean isBuff() {
        return type == IntentType.BUFF;
    }
    
    /**
     * 检查是否为减益意图
     */
    public boolean isDebuff() {
        return type == IntentType.DEBUFF;
    }
    
    /**
     * 检查是否为未知意图
     */
    public boolean isUnknown() {
        return type == IntentType.UNKNOWN;
    }
    
    @Override
    public String toString() {
        return "MonsterIntent{" +
                "type=" + type +
                ", amount=" + amount +
                ", source=" + (source != null ? source.name : "null") +
                ", target=" + (target != null ? target.name : "null") +
                '}';
    }
}