package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** release ��������� */
public class SubReleaseCommand extends SubAdminCommand {
    protected SubReleaseCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ִ�� release ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.release." + getPerformance().getObject())) {
                getPerformance().release();
                getSender().sendMessage("��b�Ѹ�����Ŀ��6" + getPerformance().getObject() 
                        + "��b�еĳͷ�������6" + Integer.toString(getPerformance().getCount())
                        + "��b������Ӧ�Ľ������" + (getPerformance().getReleaseCommands()!=null? "��6"
                        + getPerformance().getReleaseCommands(): "��c��")
                        + "��b�������ҡ�6" + getPerformance().getPlayer().getName() + "��b�ĳͷ�");
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.release." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
