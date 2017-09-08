package com.github.spacebang.stepwisepunishments;

import java.util.List;

import org.bukkit.OfflinePlayer;

/** 操作 players.yml 的类 */
public class Performance {
    private StepwisePunishments plugin;
    private OfflinePlayer player;
    private String object;
    
    public StepwisePunishments getPlugin() {
        return plugin;
    }

    private void setPlugin(StepwisePunishments plugin) {
        this.plugin = plugin;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }
    
    private void setPlayer(OfflinePlayer player) {
        this.player = player;
    }
    
    public String getObject() {
        return object;
    }
    
    private void setObject(String object) {
        this.object = object;
    }
    
    private String getPlayerIdentifier() {
        if (getPlugin().getServer().getOnlineMode()) {
            // 正版验证
            return getPlayer().getUniqueId().toString();
        } else {
            // 离线模式
            return getPlayer().getName();
        }
    }
    
    public int getCount() {
        // 获取该玩家在配置文件players.yml中指定项目的违规次数
        return this.getPlugin().getPlayersConfig().getInt(this.getPlayerIdentifier() + "." + getObject(), 0);
    }

    public void setCount(int count) {
        getPlugin().getPlayersConfig().set(this.getPlayerIdentifier() + "." + getObject(), count);
    }
    
    /** 获取当前的 object 和 count 对应的惩罚命令 */
    protected String getCommand() {
        if (getPlugin().getConfig().contains("punishments." + this.getObject())) {
            List<String> cmds = getPlugin().getConfig().getStringList("punishments." + this.getObject());
            for (String cmd: cmds) {
                if (Integer.parseInt(cmd.split(":")[0]) == this.getCount()) {
                    return cmd.split(":")[1].replaceAll("\\{player\\}", getPlayer().getName());
                }
            }
        }
        return null;
    }

    public Performance(OfflinePlayer player, String object, StepwisePunishments plugin) {
        setPlayer(player);
        setObject(object);
        setPlugin(plugin);
    }
    
    public void punish() {
        if (getCommand() != null) {
            getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), getCommand());
        }
    }
}
