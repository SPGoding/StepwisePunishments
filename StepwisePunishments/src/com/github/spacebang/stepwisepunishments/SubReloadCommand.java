package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** reload ��������� */
public class SubReloadCommand extends SubCommand {
    protected SubReloadCommand(CommandSender sender, Command cmd, String label, String[] args, 
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    public boolean isCorrect() {
        return super.isCorrect(1, "Both");
    }
        
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.reload")) {
                getPlugin().reloadConfig();
                getPlugin().reloadPlayersConfig();
                getSender().sendMessage("��b�����¼��������ļ�");
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.reload��cȨ��");
                return true;
            }
        }
        return false;
    }
}