package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/** ����ִ�������� */
public class SwpCommandExecutor implements CommandExecutor {
    private StepwisePunishments plugin;
    
    /** ��ȡ�����������ʵ�� */
    public StepwisePunishments getPlugin() {
        return plugin;
    }

    private void setPlugin(StepwisePunishments plugin) {
        this.plugin = plugin;
    }

    public SwpCommandExecutor(StepwisePunishments plugin) {
        this.setPlugin(plugin);
    }
    
    /** �����������ʱ���� */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            SubCommand sc;
            if (args.length < 0) {
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "reload":
                    sc = new SubReloadCommand(sender, cmd, label, args, getPlugin());
                    break;
                case "plus":
                    sc = new SubPlusCommand(sender, cmd, label, args, getPlugin());
                    break;
                case "minus":
                    sc = new SubMinusCommand(sender, cmd, label, args, getPlugin());
                    break;
                case "see":
                    sc = new SubSeeCommand(sender, cmd, label, args, getPlugin());
                    break;
                default:
                    return false;
            }
            if (sc.isCorrect()) {
                return sc.perform();
            }
        } catch (Exception e) {
            return false;
        }
        getPlugin().getLogger().info("9");
        return false;
    }
}