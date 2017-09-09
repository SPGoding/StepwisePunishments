package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** minus 子命令的类 */
public class SubMinusCommand extends SubAdminCommand {
    protected SubMinusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 检测命令的形式是否符合minus子命令的要求 */
    @Override
    public boolean isCorrect() {
        return (super.isCorrect(3, "Both")||super.isCorrect(4, "Both"));
    }
    
    /** 执行 minus 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            Performance performance = getPerformance();
            if (getSender().hasPermission("swp.minus." + performance.getObject())) {
                performance.release();
                int oldCount = performance.getCount();
                int minusCount = 1;
                if (getArgs().length == 4) {
                    minusCount = Math.abs(Integer.parseInt(getArgs()[3]));
                }
                String releaseCmds = performance.getReleaseCommands().toString();
                performance.setCount(performance.getCount() - minusCount);
                getSender().sendMessage("§b已将玩家§6" + performance.getPlayer().getName() 
                        + "§b在项目§6" + performance.getObject() + "§b中的违规次数从§6" 
                        + Integer.toString(oldCount) + "§b减少至§6" 
                        + Integer.toString(performance.getCount()) + "§b。解除惩罚的命令是" 
                        + (releaseCmds != null? "§6" + releaseCmds: "§c无") + "§b，已自动执行");
                return true;
            } else {
                getSender().sendMessage("§c没有§6swp.minus." + performance.getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
