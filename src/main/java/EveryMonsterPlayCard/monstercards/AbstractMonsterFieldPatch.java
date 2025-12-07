package EveryMonsterPlayCard.monstercards;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 为 AbstractMonster 添加 SpireField 字段
 * 将 MonsterCardPlayer 字段直接存储在每个怪物实例上
 */
@SpirePatch(
    clz = AbstractMonster.class,
    method = SpirePatch.CLASS
)
public class AbstractMonsterFieldPatch {
    
    // 为每个怪物实例添加 MonsterCardPlayer 字段
    public static SpireField<MonsterCardPlayer> monsterCardPlayer = 
        new SpireField<>(() -> null);

    // 获取怪物的 MonsterCardPlayer
    public static MonsterCardPlayer getMonsterCardPlayer(AbstractMonster monster) {
        return monsterCardPlayer.get(monster);
    }

    // 设置怪物的 MonsterCardPlayer
    public static void setMonsterCardPlayer(AbstractMonster monster, MonsterCardPlayer player) {
        monsterCardPlayer.set(monster, player);
    }

    // 检查怪物是否有 MonsterCardPlayer
    public static boolean hasMonsterCardPlayer(AbstractMonster monster) {
        return getMonsterCardPlayer(monster) != null;
    }
}
