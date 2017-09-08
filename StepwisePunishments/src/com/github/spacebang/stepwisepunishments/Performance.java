package com.github.spacebang.stepwisepunishments;

import java.util.List;

import org.bukkit.OfflinePlayer;

/** ���� players.yml ���� */
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
            // ������֤
            return getPlayer().getUniqueId().toString();
        } else {
            // ����ģʽ
            return getPlayer().getName();
        }
    }
    
    public int getCount() {
        // ��ȡ������������ļ�players.yml��ָ����Ŀ��Υ�����
        return this.getPlugin().getPlayersConfig().getInt(this.getPlayerIdentifier() + "." + getObject(), 0);
    }

    public void setCount(int count) {
        getPlugin().getPlayersConfig().set(this.getPlayerIdentifier() + "." + getObject(), count);
    }
    
    /** ��ȡ��ǰ�� object �� count ��Ӧ�ĳͷ����� */
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
