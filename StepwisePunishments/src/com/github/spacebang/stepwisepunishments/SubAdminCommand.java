package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** ������������ĳ��� */
public abstract class SubAdminCommand extends SubCommand {
    protected SubAdminCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ��ȡ��ǰ����������Ƿ���϶��� admin ������Ĺ淶 */
    public boolean isCorrect() {
        return super.isCorrect(3, "Both");
    }

    /** ��ȡ��ǰ�� player �� object ��Ӧ��ִ���� */
    @SuppressWarnings("deprecation")
    protected Performance getPerformance() {
        Performance perf = new Performance(getPlugin().getServer().getOfflinePlayer(getArgs()[1]), 
                getArgs()[2], getPlugin());
        return perf;
    }
}
