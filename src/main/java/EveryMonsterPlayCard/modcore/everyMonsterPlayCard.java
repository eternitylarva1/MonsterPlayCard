package EveryMonsterPlayCard.modcore;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import EveryMonsterPlayCard.monstercards.MonsterCardManager;
import EveryMonsterPlayCard.monstercards.MonsterStartTurnPatch;
import EveryMonsterPlayCard.monstercards.MonsterTurnStartPatch;
import EveryMonsterPlayCard.utils.Hpr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class everyMonsterPlayCard implements
        EditStringsSubscriber,
        PostInitializeSubscriber,
        PostDungeonInitializeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber {

    public static final Logger logger = LogManager.getLogger(everyMonsterPlayCard.class);
    public static final String MOD_ID = "everyMonsterPlayCard";
    public static final String MOD_NAME = "Every Monster Play Card";
    private static SpireConfig modConfig;

    // Global switch for mod functionality
    public static boolean enabled = true;

    public everyMonsterPlayCard() {
        BaseMod.subscribe(this);
        
        // 初始化玩家回合开始补丁，用于怪物能量补充
        new MonsterStartTurnPatch();
        
        // 初始化怪物回合开始补丁，用于怪物出牌
        new MonsterTurnStartPatch();
        
        logger.info("Subscribed to BaseMod events");
    }

    public static void initialize() {
        new everyMonsterPlayCard();
        logger.info("EveryMonsterPlayCard Mod initialized");

    }

    @Override
    public void receiveEditStrings() {
        // Load localization strings
        loadLocalization();
        logger.info("Localization strings loaded");
    }

    private void loadLocalization() {
        String language = Settings.language.name().toLowerCase();
        String langFilePath = MOD_ID + "Resources/localization/" + (language.equals("zhs") ? "ZHS" : "ENG") + "/";

        // Load power strings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                langFilePath + "powers.json");

        // Load relic strings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                langFilePath + "relics.json");

        // Load UI strings
        BaseMod.loadCustomStringsFile(UIStrings.class,
                langFilePath + "ui.json");

        // Note: Keywords are not loaded via JSON as they require special handling
        // Our mod doesn't need custom keywords for "Monster Cards"

        logger.info("Loaded localization for language: " + language);
    }

    @Override
    public void receivePostInitialize() {
        // Initialize configuration
        try {
            modConfig = new SpireConfig(MOD_ID, "config");
            enabled = modConfig.getBool("enabled");
            if (modConfig.has("enabled")) {
                enabled = modConfig.getBool("enabled");
            } else {
                enabled = true;
                modConfig.setBool("enabled", true);
                modConfig.save();
            }
            logger.info("Mod configuration loaded, enabled=" + enabled);
        } catch (IOException e) {
            logger.error("Failed to load configuration: " + e.getMessage());
            enabled = true;
        }

        // Can add settings UI or buttons here
        logger.info("PostInitialize completed");
    }

    @Override
    public void receivePostDungeonInitialize() {
        // Called after dungeon initialization
        logger.info("Dungeon initialization completed");
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        if (!enabled) {
            logger.info("Mod disabled, skipping battle start processing");
            return;
        }

        // 确保是在战斗房间
        if (!(room instanceof MonsterRoom || room instanceof MonsterRoomElite)) {
            logger.info("Not a battle room, skipping monster card system initialization");
            return;
        }

        // At battle start, enable card system for all monsters
        MonsterCardManager.getInstance().enableRoomMonsters();
        logger.info("Enabled monster card system for all monsters in battle start");
    }


    @Override
    public void receivePostBattle(AbstractRoom room) {
        // Cleanup after battle
        logger.info("Battle ended, cleaning up monster card system");
        MonsterCardManager.getInstance().resetAll();
    }

    // Utility method to get mod configuration
    public static SpireConfig getModConfig() {
        return modConfig;
    }

    // Utility method to save configuration
    public static void saveConfig() {
        if (modConfig != null) {
            try {
                modConfig.save();
            } catch (IOException e) {
                logger.error("Failed to save configuration: " + e.getMessage());
            }
        }
    }
}