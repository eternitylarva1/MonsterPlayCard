package EveryMonsterPlayCard.core.events;

/**
 * 能量更新事件
 * 当怪物的能量发生变化时触发
 */
public class EnergyUpdateEvent {

    // 怪物ID
    public final int monsterId;
    // 玩家ID
    public final int playerId;
    // 之前的能量
    public final int previousEnergy;
    // 新的能量
    public final int currentEnergy;
    // 能量变化量
    public final int energyChange;
    // 事件时间戳
    public final long timestamp;

    public EnergyUpdateEvent(int monsterId, int playerId, int previousEnergy, int currentEnergy) {
        this.monsterId = monsterId;
        this.playerId = playerId;
        this.previousEnergy = previousEnergy;
        this.currentEnergy = currentEnergy;
        this.energyChange = currentEnergy - previousEnergy;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 检查能量是否增加
     */
    public boolean isEnergyGained() {
        return energyChange > 0;
    }

    /**
     * 检查能量是否减少
     */
    public boolean isEnergySpent() {
        return energyChange < 0;
    }

    /**
     * 检查能量是否无变化
     */
    public boolean isUnchanged() {
        return energyChange == 0;
    }

    @Override
    public String toString() {
        String changeType = isEnergyGained() ? "gained" : isEnergySpent() ? "spent" : "unchanged";
        return String.format("EnergyUpdateEvent{monsterId=%d, playerId=%d, %d -> %d (%s %d), timestamp=%d}",
                monsterId, playerId, previousEnergy, currentEnergy, changeType, Math.abs(energyChange), timestamp);
    }
}
