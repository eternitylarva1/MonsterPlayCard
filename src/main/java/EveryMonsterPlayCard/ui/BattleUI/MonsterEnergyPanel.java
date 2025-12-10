package EveryMonsterPlayCard.ui.BattleUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//敌人的能量框 - 重新设计为独立系统，不依赖玩家
public class MonsterEnergyPanel {

    //关联的怪物
    public AbstractMonster monster = null;
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
    private float orbScale = 1.0f;
    private float orbAlpha = 1.0f;
    private float orbTimer = 0.0f;
    private boolean energyChanged = false;
    
    // 能量球纹理
    private Texture orbTexture;
    private Texture orbGlowTexture;
    private Hitbox orbHitbox;

    public MonsterEnergyPanel(float x, float y)
    {
        this.x = x;
        this.y = y;
        
        // 初始化能量球纹理
        try {
            // 使用游戏内置的能量球纹理
            this.orbTexture = ImageMaster.loadImage("images/ui/orb.png");
            this.orbGlowTexture = ImageMaster.loadImage("images/ui/orbGlow.png");
        } catch (Exception e) {
            // 如果加载失败，使用备用方案
            this.orbTexture = null;
            this.orbGlowTexture = null;
        }
        
        // 初始化碰撞箱
        this.orbHitbox = new Hitbox(x, y, 64.0f * Settings.scale, 64.0f * Settings.scale);
    }

    public void init(AbstractMonster monster, int masterEnergy)
    {
        this.monster = monster;
        this.masterEnergy = masterEnergy;
        this.currentEnergy = masterEnergy; // 初始化为满能量
    }

    public void setCurrentEnergy(int currentEnergy) {
        if (this.currentEnergy != currentEnergy) {
            this.currentEnergy = currentEnergy;
            this.energyChanged = true;
            this.orbTimer = 0.0f; // 重置动画计时器
        }
    }

    public void updatePosition(float monsterX, float monsterY) {
        // 基于怪物位置计算能量球位置（相对偏移）
        this.x = monsterX - Settings.WIDTH * 0.1f;
        this.y = monsterY + Settings.HEIGHT * 0.15f;
        
        // 更新碰撞箱位置
        if (this.orbHitbox != null) {
            this.orbHitbox.move(this.x, this.y);
        }
    }

    public void update()
    {
        // 更新字体动画
        if(this.fontScale > 1.f)
        {
            this.fontScale = MathHelper.scaleLerpSnap(this.fontScale,1.f);
        }
        
        // 更新能量球动画
        updateOrbAnimation();
        
        // 更新碰撞箱
        if (this.orbHitbox != null) {
            this.orbHitbox.update();
        }
    }
    
    private void updateOrbAnimation()
    {
        // 能量变化时的动画效果
        if (this.energyChanged) {
            this.orbTimer += com.badlogic.gdx.Gdx.graphics.getDeltaTime();
            
            // 缩放动画
            if (this.orbTimer < 0.3f) {
                this.orbScale = 1.0f + 0.2f * (1.0f - this.orbTimer / 0.3f);
            } else {
                this.orbScale = 1.0f;
                this.energyChanged = false;
            }
            
            // 透明度动画
            if (this.currentEnergy > 0) {
                this.orbAlpha = 1.0f;
            } else {
                this.orbAlpha = 0.5f + 0.5f * (float)Math.sin(this.orbTimer * 5.0f);
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        // 渲染能量球背景
        renderOrbBackground(sb);
        
        // 渲染能量球
        renderOrb(sb);
        
        // 渲染能量文字
        renderEnergyText(sb);
    }
    
    private void renderOrbBackground(SpriteBatch sb)
    {
        if (this.orbGlowTexture != null) {
            sb.setColor(1.0f, 1.0f, 1.0f, this.orbAlpha * 0.3f);
            sb.draw(this.orbGlowTexture,
                   this.x - 32.0f * Settings.scale,
                   this.y - 32.0f * Settings.scale,
                   64.0f * Settings.scale,
                   64.0f * Settings.scale);
            sb.setColor(Color.WHITE);
        }
    }
    
    private void renderOrb(SpriteBatch sb)
    {
        if (this.orbTexture != null) {
            // 根据能量状态设置颜色
            if (this.currentEnergy > 0) {
                sb.setColor(1.0f, 1.0f, 1.0f, this.orbAlpha);
            } else {
                sb.setColor(0.7f, 0.7f, 0.7f, this.orbAlpha);
            }
            
            sb.draw(this.orbTexture,
                   this.x - 32.0f * Settings.scale * this.orbScale,
                   this.y - 32.0f * Settings.scale * this.orbScale,
                   64.0f * Settings.scale * this.orbScale,
                   64.0f * Settings.scale * this.orbScale);
            sb.setColor(Color.WHITE);
        }
    }
    
    private void renderEnergyText(SpriteBatch sb)
    {
        // 准备用于渲染的文字
        String energyString = this.currentEnergy + "/" + this.masterEnergy;
        
        // 使用游戏默认字体
        BitmapFont font = FontHelper.cardDescFont_N;
        font.getData().setScale(this.fontScale);
        
        // 根据能量状态设置文字颜色
        Color textColor = this.currentEnergy > 0 ? Color.WHITE : new Color(0.7f, 0.7f, 0.7f, 1.0f);
        
        // 把文字渲染在中间
        FontHelper.renderFontCentered(sb, font, energyString, this.x, this.y, textColor);
    }
}
