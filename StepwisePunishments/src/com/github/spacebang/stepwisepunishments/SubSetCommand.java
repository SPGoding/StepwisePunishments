package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** set 子命令的类 */
public class SubSetCommand extends SubAdminCommand {
    protected SubSetCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 获取当前输入的命令是否符合 set 子命令的规范 */
    @Override
    public boolean isCorrect() {
        return super.isCorrect(4, "Both");
    }
    
    /** 执行 set 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.set." + getPerformance().getObject())) {
                getPerformance().setCount(Integer.parseInt(getArgs()[3]));
                getSender().sendMessage("§b已将玩家§6" + getPerformance().getPlayer().getName() + "§b在项目§6" 
                        + getPerformance().getObject() + "§b中的惩罚次数设置为§6" 
                        + Integer.toString(getPerformance().getCount())
                        + "§b。对应的惩罚命令是" + (getPerformance().getCommands()!=null? "§6" 
                        + getPerformance().getCommands(): "§c无"));
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.set." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
