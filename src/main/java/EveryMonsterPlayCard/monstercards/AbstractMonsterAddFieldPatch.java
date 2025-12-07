package EveryMonsterPlayCard.monstercards;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractMonster.class,
        method = SpirePatch.CLASS
)
public class AbstractMonsterAddFieldPatch {
    public AbstractMonsterAddFieldPatch() {
    }

    // 为每个怪物实例添加 MonsterCardPlayer 字段
    public static SpireField<MonsterCardPlayer> monsterCardPlayer = new SpireField<>(() -> null);

    // 便捷方法：获取怪物的 MonsterCardPlayer
    public static MonsterCardPlayer getMonsterCardPlayer(AbstractMonster monster) {
        return monsterCardPlayer.get(monster);
    }

    // 便捷方法：设置怪物的 MonsterCardPlayer
    public static void setMonsterCardPlayer(AbstractMonster monster, MonsterCardPlayer player) {
        monsterCardPlayer.set(monster, player);
    }

    // 便捷方法：检查怪物是否有 MonsterCardPlayer
    public static boolean hasMonsterCardPlayer(AbstractMonster monster) {
        return getMonsterCardPlayer(monster) != null;
    }

    // 便捷方法：清除怪物的 MonsterCardPlayer
    public static void clearMonsterCardPlayer(AbstractMonster monster) {
        setMonsterCardPlayer(monster, null);
    }
}
