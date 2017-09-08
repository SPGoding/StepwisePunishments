package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** reload 子命令的类 */
public class SubReloadCommand extends SubCommand {
    protected SubReloadCommand(CommandSender sender, Command cmd, String label, String[] args, 
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    public boolean isCorrect() {
        return super.isCorrect(1, "Both");
    }
        
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.reload")) {
                getPlugin().reloadConfig();
                getPlugin().reloadPlayersConfig();
                getSender().sendMessage("§b已重新加载配置文件");
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.reload§c权限");
                return true;
            }
        }
        return false;
    }
}