package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** 子命令的超类 */
public abstract class SubCommand {
    private CommandSender sender;
    private Command cmd;
    private String label;
    private String[] args;
    private StepwisePunishments plugin;
    
    /** 获取本插件的主类实例 */
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
    
    /** 判断当前输入的命令是否合乎规范 */
    protected boolean isCorrect(int argsLength, String senderType) {
        // 参数长度是否正确
        if (this.getArgs().length != argsLength) {
            return false;
        }
        // sender 是否正确
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
    
    /** 调用 isCorrect(int argsLength, String senderType) 方法判断输入的命令是否规范 */
    protected abstract boolean isCorrect();
    
    /** 执行该命令 */
    protected abstract boolean perform();
}
