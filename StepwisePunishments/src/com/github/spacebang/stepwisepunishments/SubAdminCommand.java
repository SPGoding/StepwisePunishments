package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** 管理类子命令的超类 */
public abstract class SubAdminCommand extends SubCommand {
    protected SubAdminCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 获取当前输入的命令是否符合多数 admin 子命令的规范 */
    public boolean isCorrect() {
        return super.isCorrect(3, "Both");
    }

    /** 获取当前的 player 和 object 对应的执行类 */
    @SuppressWarnings("deprecation")
    protected Performance getPerformance() {
        Performance perf = new Performance(getPlugin().getServer().getOfflinePlayer(getArgs()[1]), 
                getArgs()[2], getPlugin());
        return perf;
    }
}
