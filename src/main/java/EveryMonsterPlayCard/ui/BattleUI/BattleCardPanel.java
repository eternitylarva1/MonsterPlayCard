package EveryMonsterPlayCard.ui.BattleUI;

import EveryMonsterPlayCard.ui.BattleUI.CardBox;
import EveryMonsterPlayCard.ui.BattleUI.CardRecorder;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

//这虽然叫card panel,但时而会同时包括卡牌信息和能量信息
public class BattleCardPanel {

    //用于显示手牌的box
    public CardBox cardBox;
    //敌人的能量框
    public MonsterEnergyPanel energyPanel;
    //遗物列表
    public MonsterRelicPanel monsterRelicPanel;
    //玩家的药水列表
    public MonsterPotionPanel potionPanel;

    // 关联的怪物（用于获取能量信息）
    private AbstractMonster associatedMonster;
    // 关联的 MonsterCardPlayer，用于读取能量和其他状态
    public EveryMonsterPlayCard.monstercards.MonsterCardPlayer cardPlayer = null;

    public BattleCardPanel(float xCenter,
        float yCenter, CardRecorder shownCards, AbstractMonster monster
    )
    {
        // 保存关联的怪物
        this.associatedMonster = monster;

        //生成card box
        this.cardBox = new CardBox(xCenter,yCenter,shownCards,monster);
        this.energyPanel = new MonsterEnergyPanel(xCenter- Settings.WIDTH*0.1f,
            yCenter - Settings.HEIGHT*0.15f);
        //初始化遗物列表
        this.monsterRelicPanel = new MonsterRelicPanel();
        //更新玩家的药水列表
        this.potionPanel = new MonsterPotionPanel();
    }

    /**
     * 设置卡牌透明度（基于能量系统）
     */
    public void updateCardTransparency() {
        if (cardBox != null) {
            cardBox.updateCardTransparency(getCurrentEnergy());
        }
    }

    public void render(SpriteBatch sb)
    {
        //直接渲染card box
        this.cardBox.render(sb);
        this.energyPanel.render(sb);
        this.monsterRelicPanel.render(sb);
        this.potionPanel.render(sb);
    }

    public void update()
    {
        //修复：添加卡牌盒更新调用，确保持续跟随怪物移动
        if (this.cardBox != null) {
            this.cardBox.update();
        }
        this.energyPanel.update();
        this.monsterRelicPanel.update();
        this.potionPanel.update();
    }

    //设置能量
    public void setEnergy(int currEnergy)
    {
        this.energyPanel.setCurrentEnergy(currEnergy);
        // 移除energyPanel.update()调用，因为新的能量系统有自己的更新机制
    }

    /**
     * 获取当前能量（优先使用直接引用，回退到通过字段获取）
     */
    public int getCurrentEnergy() {
        // 优先使用直接引用的cardPlayer
        if (this.cardPlayer != null) {
            return this.cardPlayer.getCurrentEnergy();
        }
        
        // 回退到通过字段获取
        if (associatedMonster != null) {
            EveryMonsterPlayCard.monstercards.MonsterCardPlayer cardPlayer = EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch.getMonsterCardPlayer(associatedMonster);
            if (cardPlayer != null) {
                return cardPlayer.getCurrentEnergy();
            }
        }
        return 0; // 默认返回0
    }

    //初始化玩家的遗物列表
    public void initRelicList(ArrayList<AbstractRelic> relicList)
    {
        monsterRelicPanel.clearPanel();
        for(AbstractRelic eachRelic : relicList)
        {
            monsterRelicPanel.addRelic(eachRelic);
        }
    }

}
