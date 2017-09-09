package com.github.spacebang.stepwisepunishments;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** plus 子命令的类 */
public class SubPlusCommand extends SubAdminCommand{
    protected SubPlusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** 检测命令的形式是否符合plus子命令的要求 */
    @Override
    public boolean isCorrect() {
        return (super.isCorrect(3, "Both")||super.isCorrect(4, "Both"));
    }
    
    /** 执行 plus 子命令 */
    @Override
    public boolean perform() {
        // 格式与执行者无误
        if (this.isCorrect()) {
            // 是否有权限
            Performance performance = getPerformance();
            if (getSender().hasPermission("swp.plus." + performance.getObject())) {
                // 是否处于冷却时间，以及执行者有无忽略冷却时间的权限
                if (getPlugin().getPlayerCooldownMap()
                        .containsKey(performance.getPlayer().getName())) {
                    if (new Date().getTime() - getPlugin().getPlayerCooldownMap().get(performance
                            .getPlayer().getName()) <= getPlugin().getConfig()
                            .getLong("cooldown", 300000L)) {
                        if (new Date().getTime() - getPlugin().getPlayerCooldownMap().get(performance
                                .getPlayer().getName()) <= getPlugin().getConfig()
                                .getLong("cooldown", 300000L)) {
                            if (!getSender().hasPermission("swp.bypassCooldown." + 
                                    performance.getObject())) {
                                    getSender().sendMessage("§c目前处于玩家§6" + 
                                performance.getPlayer().getName() + 
                                "§c的被处罚冷却时间中，你可以通过§6swp.bypassCooldown." + 
                                performance.getObject() + "§c权限来无视冷却时间");
                                return true;
                            }  
                        }
                    }
                }
                    int oldCount = performance.getCount();
                    int plusCount = 1;
                    if (getArgs().length == 4) {
                        plusCount = Math.abs(Integer.parseInt(getArgs()[3]));
                    }
                    performance.setCount(performance.getCount() + plusCount);
                    performance.punish();
                    getSender().sendMessage("§b已将玩家§6" + performance.getPlayer().getName()
                            + "§b在项目§6" + performance.getObject() + "§b中的违规次数从§6" 
                            + Integer.toString(oldCount) + "§b增加至§6" 
                            + Integer.toString(performance.getCount())
                            + "§b。对应的惩罚命令是" + (performance.getCommands() != null? "§6" 
                            + performance.getCommands(): "§c无") + "§b，已自动执行");
                    getPlugin().getPlayerCooldownMap().put(performance.getPlayer().getName(),
                            new Date().getTime());
                    return true;                    
            } else {
                getSender().sendMessage("§c没有§6swp.plus." + performance.getObject() + "§c权限");
                return true;
            }
        }
        return false;
    }
}
