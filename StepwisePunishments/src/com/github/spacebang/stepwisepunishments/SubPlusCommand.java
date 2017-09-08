package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** plus 子命令的类 */
public class SubPlusCommand extends SubAdminCommand{
    protected SubPlusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 执行 plus 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            if (getSender().hasPermission("swp.plus." + getPerformance().getObject())) {
                getPerformance().setCount(getPerformance().getCount() + 1);
                getPerformance().punish();
                getSender().sendMessage("§b已将玩家§6" + getPerformance().getPlayer().getName() + "§b在项目§6" 
                        + getPerformance().getObject() + "§b中的惩罚次数增加为§6" 
                        + Integer.toString(getPerformance().getCount())
                        + "§b。对应的惩罚命令是" + (getPerformance().getCommands()!=null? "§6" 
                        + getPerformance().getCommands(): "§c无") + "§b。已自动执行");
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.plus." + getPerformance().getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
