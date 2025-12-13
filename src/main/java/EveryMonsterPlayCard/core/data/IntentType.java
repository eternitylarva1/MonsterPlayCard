package EveryMonsterPlayCard.core.data;

/**
 * 意图类型枚举
 */
public enum IntentType {
    ATTACK("攻击意图", "ATTACK"),
    DEFEND("防御意图", "DEFEND"),
    BUFF("增益意图", "BUFF"),
    DEBUFF("减益意图", "DEBUFF"),
    STRONG("强力攻击意图", "STRONG"),
    ESCAPE("逃跑意图", "ESCAPE"),
    UNKNOWN("未知意图", "UNKNOWN");
    
    private final String displayName;
    private final String code;
    
    IntentType(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getCode() {
        return code;
    }
    
    /**
     * 根据代码获取意图类型
     * @param code 代码
     * @return 意图类型
     */
    public static IntentType fromCode(String code) {
        for (IntentType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return UNKNOWN;
    }
    
    /**
     * 根据显示名称获取意图类型
     * @param displayName 显示名称
     * @return 意图类型
     */
    public static IntentType fromDisplayName(String displayName) {
        for (IntentType type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}