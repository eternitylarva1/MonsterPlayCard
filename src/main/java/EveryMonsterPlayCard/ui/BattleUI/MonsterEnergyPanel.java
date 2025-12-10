package EveryMonsterPlayCard.ui.BattleUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;

//怪物专用能量框 - 完全独立于玩家系统
public class MonsterEnergyPanel {

    //当前的能量信息
    public int currentEnergy = 0;
    //最大的能量
    public int masterEnergy = 0;
    //能量框被渲染的位置
    float x;
    float y;
    //字体的大小
    float fontScale = 1.f;
    
    // 能量球动画相关
    private float energyTimer = 0.0f;
    private float energyScale = 1.0f;
    private boolean energyChanged = false;
    
    // 能量球纹理
    private static Texture energyOrbTexture = null;
    private static TextureRegion energyOrbRegion = null;

    public MonsterEnergyPanel(float x,float y)
    {
        this.x = x;
        this.y = y;
        
        // 初始化能量球纹理
        if (energyOrbTexture == null) {
            try {
                energyOrbTexture = ImageMaster.loadImage("images/ui/orb.png");
                energyOrbRegion = new TextureRegion(energyOrbTexture);
            } catch (Exception e) {
                // 如果加载失败，使用备用方案
                energyOrbTexture = null;
                energyOrbRegion = null;
            }
        }
    }

    public void init(int masterEnergy)
    {
        this.masterEnergy = masterEnergy;
        this.currentEnergy = masterEnergy; // 初始化为满能量
        this.energyChanged = true;
    }

    public void setCurrentEnergy(int currentEnergy) {
        int oldEnergy = this.currentEnergy;
        this.currentEnergy = Math.max(0, Math.min(currentEnergy, masterEnergy));
        
        // 如果能量发生变化，触发动画
        if (oldEnergy != this.currentEnergy) {
            this.energyChanged = true;
            this.energyTimer = 0.0f;
            this.energyScale = 1.2f; // 能量变化时稍微放大
        }
    }

    public void updatePosition(float monsterX, float monsterY) {
        // 基于怪物位置计算能量球位置（相对偏移）
        this.x = monsterX - Settings.WIDTH * 0.1f;
        this.y = monsterY + Settings.HEIGHT * 0.15f;
    }

    public void update()
    {
        // 更新能量球动画
        if (this.energyChanged) {
            this.energyTimer += Gdx.graphics.getDeltaTime();
            
            // 能量变化动画：从1.2f缩放回1.0f
            if (this.energyTimer < 0.3f) {
                this.energyScale = MathHelper.scaleLerpSnap(this.energyScale, 1.0f);
            } else {
                this.energyScale = 1.0f;
                this.energyChanged = false;
                this.energyTimer = 0.0f;
            }
        }
        
        // 如果字体正处于放大状态，对字体大小做一下更新
        if(this.fontScale > 1.f)
        {
            this.fontScale = MathHelper.scaleLerpSnap(this.fontScale,1.f);
        }
    }

    public void render(SpriteBatch sb)
    {
        // 渲染能量球背景
        renderEnergyOrb(sb);
        
        // 准备用于渲染的文字
        String energyString = this.currentEnergy + "/" + this.masterEnergy;
        
        // 使用游戏默认字体
        BitmapFont font = FontHelper.cardTitleFont;
        
        font.getData().setScale(this.fontScale * Settings.scale);
        
        // 把文字渲染在中间
        FontHelper.renderFontCentered(sb, font, energyString, this.x, this.y, Color.WHITE);
    }
    
    /**
     * 渲染能量球
     */
    private void renderEnergyOrb(SpriteBatch sb) {
        float orbSize = 80.0f * Settings.scale * this.energyScale;
        
        // 如果有自定义纹理，使用自定义纹理
        if (energyOrbRegion != null) {
            sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            sb.draw(energyOrbRegion,
                   this.x - orbSize / 2.0f,
                   this.y - orbSize / 2.0f,
                   orbSize,
                   orbSize);
        } else {
            // 备用方案：绘制简单的圆形能量球
            sb.setColor(this.currentEnergy > 0 ? Color.YELLOW : Color.GRAY);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG,
                   this.x - orbSize / 2.0f,
                   this.y - orbSize / 2.0f,
                   orbSize,
                   orbSize);
            
            // 绘制边框
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG,
                   this.x - orbSize / 2.0f - 2.0f,
                   this.y - orbSize / 2.0f - 2.0f,
                   orbSize + 4.0f,
                   orbSize + 4.0f);
        }
    }
}
