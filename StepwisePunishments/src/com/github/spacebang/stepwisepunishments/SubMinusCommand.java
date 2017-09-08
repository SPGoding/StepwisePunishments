package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** minus 子命令的类 */
public class SubMinusCommand extends SubAdminCommand {
    protected SubMinusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 执行 minus 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.minus." + getPerformance().getObject())) {
                String releaseCmds = getPerformance().getReleaseCommands().toString();
                getPerformance().release();
                getPerformance().setCount(getPerformance().getCount() - 1);
                getSender().sendMessage("§b已将玩家§6" + getPerformance().getPlayer().getName() + "§b在项目§6" 
                        + getPerformance().getObject() + "§b中的惩罚次数减少为§6" 
                        + Integer.toString(getPerformance().getCount())
                        + "§b。解除惩罚的命令是" + (releaseCmds!=null? "§6" 
                        + releaseCmds: "§c无") + "§b。已自动执行");
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.minus." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
