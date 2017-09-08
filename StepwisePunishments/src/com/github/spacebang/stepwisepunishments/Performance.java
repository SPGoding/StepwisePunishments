package com.github.spacebang.stepwisepunishments;

import java.util.ArrayList;
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
    protected List<String> getCommands() {
        List<String> resultCmds = new ArrayList<>();
        if (getPlugin().getConfig().contains("punishments." + this.getObject())) {
            List<String> cmds = getPlugin().getConfig().getStringList("punishments." + this.getObject());
            for (String cmd: cmds) {
                String[] seperate = cmd.split(":");
                if (Integer.parseInt(seperate[0]) == this.getCount()) {
                    if ((seperate.length == 2) || (seperate.length == 3)) {
                        resultCmds.add(seperate[1].replaceAll("\\{player\\}", getPlayer().getName()));
                    }
                }
            }
        }
        return resultCmds;
    }
    
    /** 获取当前的 object 和 count 对应的撤销惩罚的命令 */
    protected List<String> getReleaseCommands() {
        List<String> releaseCmds = new ArrayList<>();
        if (getPlugin().getConfig().contains("punishments." + this.getObject())) {
            List<String> cmds = getPlugin().getConfig().getStringList("punishments." + this.getObject());
            for (String cmd: cmds) {
                String[] seperate = cmd.split(":");
                if (Integer.parseInt(seperate[0]) == this.getCount()) {
                    if (seperate.length == 3) {
                        releaseCmds.add(seperate[2].replaceAll("\\{player\\}", getPlayer().getName()));
                    }
                }
            }
        }
        return releaseCmds;
    }
    
    public Performance(OfflinePlayer player, String object, StepwisePunishments plugin) {
        setPlayer(player);
        setObject(object);
        setPlugin(plugin);
    }
    
    /** 按照当前项目当前玩家的违规次数进行惩罚 */
    public void punish() {
        if (getCommands() != null) {
            for (String cmd: getCommands()) {
                getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), cmd);
            }
        }
    }
    
    /** 撤销惩罚 */
    public void release() {
        if (getReleaseCommands() != null) {
            for (String cmd: getReleaseCommands()) {
                getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), cmd);
            }
        }
    }
}
