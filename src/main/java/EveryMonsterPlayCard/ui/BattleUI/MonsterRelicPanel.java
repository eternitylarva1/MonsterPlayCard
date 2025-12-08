package EveryMonsterPlayCard.ui.BattleUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

//敌方的relic的panel (简化版本)
public class MonsterRelicPanel {

    //简化版本的遗物列表
    private ArrayList<AbstractRelic> relicList = new ArrayList<>();

    public MonsterRelicPanel() {
        // 简化版本，不依赖复杂的BasePanel
    }

    public void render(SpriteBatch sb) {
        // 简化版本，不渲染具体内容
        // TODO: 在后续阶段实现具体的遗物渲染
    }

    public void update() {
        // 更新逻辑
    }

    public void clearPanel() {
        relicList.clear();
    }

    public void addRelic(AbstractRelic relic) {
        if (relic != null) {
            relicList.add(relic);
        }
    }
}
