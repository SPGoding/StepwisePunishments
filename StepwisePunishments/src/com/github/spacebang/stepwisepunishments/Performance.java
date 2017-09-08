package com.github.spacebang.stepwisepunishments;

import java.util.ArrayList;
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
    
    /** ��ȡ��ǰ�� object �� count ��Ӧ�ĳ����ͷ������� */
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
    
    /** ���յ�ǰ��Ŀ��ǰ��ҵ�Υ��������гͷ� */
    public void punish() {
        if (getCommands() != null) {
            for (String cmd: getCommands()) {
                getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), cmd);
            }
        }
    }
    
    /** �����ͷ� */
    public void release() {
        if (getReleaseCommands() != null) {
            for (String cmd: getReleaseCommands()) {
                getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), cmd);
            }
        }
    }
}
