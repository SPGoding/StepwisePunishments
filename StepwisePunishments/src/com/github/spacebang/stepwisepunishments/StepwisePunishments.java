package com.github.spacebang.stepwisepunishments;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/** ������� */
public final class StepwisePunishments extends JavaPlugin {
    private Map<String, Long> playerCooldownMap = new HashMap<>();
    private FileConfiguration playersConfig;
    
    public FileConfiguration getPlayersConfig() {
        return playersConfig;
    }

    public void setPlayersConfig(FileConfiguration playersConfig) {
        this.playersConfig = playersConfig;
    }

    public Map<String, Long> getPlayerCooldownMap() {
        return playerCooldownMap;
    }

    public void saveDefaultPlayersConfig() {
        saveResource("players.yml", false);
    }
    
    @Override
    public void onEnable() {
        // ��������ļ�
        if (!(this.getDataFolder().exists())) {
            this.getDataFolder().mkdir();
        }
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!(configFile.exists())) {
            this.saveDefaultConfig();
        }
        this.reloadConfig();
        File playersConfigFile = new File(this.getDataFolder(), "players.yml");
        if (!(playersConfigFile.exists())) {
            this.saveDefaultPlayersConfig();
        }
        // ��������ִ����
        this.getCommand("swp").setExecutor(new SwpCommandExecutor(this));
    }

    public void savePlayersConfig() throws IOException {
        getPlayersConfig().save(new File(getDataFolder(), "players.yml"));
    }
    
    @Override
    public void onDisable() {
        try {
            saveConfig();
            savePlayersConfig();
        } catch (Exception e) {
            getLogger().info(e.toString());
        }
    }
}