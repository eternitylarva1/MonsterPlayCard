package EveryMonsterPlayCard.ui.BattleUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

//敌方玩家的药水列表 (简化版本)
public class MonsterPotionPanel {

    //简化版本的药水列表
    private ArrayList<AbstractPotion> potionList = new ArrayList<>();

    public MonsterPotionPanel() {
        // 简化版本
    }

    public void render(SpriteBatch sb) {
        // 简化版本，不渲染具体内容
        // TODO: 在后续阶段实现具体的药水渲染
    }

    public void update() {
        // 更新逻辑
    }

    public void clearPanel() {
        potionList.clear();
    }

    public void addPotion(AbstractPotion potion) {
        if (potion != null) {
            potionList.add(potion);
        }
    }
}
