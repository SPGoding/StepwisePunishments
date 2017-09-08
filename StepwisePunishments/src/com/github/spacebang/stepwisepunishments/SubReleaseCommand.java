package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** release 子命令的类 */
public class SubReleaseCommand extends SubAdminCommand {
    protected SubReleaseCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 执行 release 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.release." + getPerformance().getObject())) {
                getPerformance().release();
                getSender().sendMessage("§b已根据项目§6" + getPerformance().getObject() 
                        + "§b中的惩罚次数§6" + Integer.toString(getPerformance().getCount())
                        + "§b次所对应的解除命令" + (getPerformance().getReleaseCommands()!=null? "§6"
                        + getPerformance().getReleaseCommands(): "§c无")
                        + "§b解除了玩家§6" + getPerformance().getPlayer().getName() + "§b的惩罚");
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.release." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
