package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** punish ��������� */
public class SubPunishCommand extends SubAdminCommand {
    protected SubPunishCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ִ�� punish ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.punish." + getPerformance().getObject())) {
                getPerformance().punish();
                getSender().sendMessage("��b�Ѹ�����Ŀ��6" + getPerformance().getObject() 
                        + "��b�еĳͷ�������6" + Integer.toString(getPerformance().getCount())
                        + "��b������Ӧ�ĳͷ�����" + (getPerformance().getCommands()!=null? "��6" 
                        + getPerformance().getCommands(): "��c��")
                        + "��b�ͷ�����ҡ�6" + getPerformance().getPlayer().getName());
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.punish." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}