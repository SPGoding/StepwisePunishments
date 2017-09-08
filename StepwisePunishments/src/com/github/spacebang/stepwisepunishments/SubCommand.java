package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** ������ĳ��� */
public abstract class SubCommand {
    private CommandSender sender;
    private Command cmd;
    private String label;
    private String[] args;
    private StepwisePunishments plugin;
    
    /** ��ȡ�����������ʵ�� */
    public StepwisePunishments getPlugin() {
        return plugin;
    }

    private void setPlugin(StepwisePunishments plugin) {
        this.plugin = plugin;
    }
    
    public CommandSender getSender() {
        return sender;
    }

    private void setSender(CommandSender sender) {
        this.sender = sender;
    }
    
    public Command getCmd() {
        return cmd;
    }

    private void setCmd(Command cmd) {
        this.cmd = cmd;
    }
    
    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        this.label = label;
    }
    
    public String[] getArgs() {
        return args;
    }

    private void setArgs(String[] args) {
        this.args = args;
    }

    protected SubCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        this.setSender(sender);
        this.setCmd(cmd);
        this.setLabel(label);
        this.setArgs(args);
        this.setPlugin(plugin);
    }
    
    /** �жϵ�ǰ����������Ƿ�Ϻ��淶 */
    protected boolean isCorrect(int argsLength, String senderType) {
        // ���������Ƿ���ȷ
        if (this.getArgs().length != argsLength) {
            return false;
        }
        // sender �Ƿ���ȷ
        switch (senderType) {
            case "Player":
                if (!(this.getSender() instanceof Player)) {
                    return false;
                }
                break;
            case "Console":
                if (this.getSender() instanceof Player) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }
    
    /** ���� isCorrect(int argsLength, String senderType) �����ж�����������Ƿ�淶 */
    protected abstract boolean isCorrect();
    
    /** ִ�и����� */
    protected abstract boolean perform();
}
