package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** punish 子命令的类 */
public class SubPunishCommand extends SubAdminCommand {
    protected SubPunishCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 执行 punish 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.punish." + getPerformance().getObject())) {
                getPerformance().punish();
                getSender().sendMessage("§b已根据项目§6" + getPerformance().getObject() 
                        + "§b中的惩罚次数§6" + Integer.toString(getPerformance().getCount())
                        + "§b次所对应的惩罚命令" + (getPerformance().getCommands()!=null? "§6" 
                        + getPerformance().getCommands(): "§c无")
                        + "§b惩罚了玩家§6" + getPerformance().getPlayer().getName());
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.punish." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}