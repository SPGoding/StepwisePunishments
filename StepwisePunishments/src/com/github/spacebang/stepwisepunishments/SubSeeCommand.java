package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SubSeeCommand extends SubAdminCommand{
    protected SubSeeCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 获取当前输入的命令是否符合多数 see 子命令的规范 */
    public boolean isCorrect() {
        return super.isCorrect(3, "Both");
    }
    
    /** 执行 see 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.see." + getPerformance().getObject())) {
                getSender().sendMessage("§b玩家§6" + getPerformance().getPlayer().getName() + "§b在项目§6" 
                        + getPerformance().getObject() + "§b中的违规次数为§6" 
                        + Integer.toString(getPerformance().getCount()));
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.see." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
