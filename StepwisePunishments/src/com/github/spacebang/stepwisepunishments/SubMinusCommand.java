package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** minus ��������� */
public class SubMinusCommand extends SubAdminCommand {
    protected SubMinusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ִ�� minus ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.minus." + getPerformance().getObject())) {
                String releaseCmds = getPerformance().getReleaseCommands().toString();
                getPerformance().release();
                getPerformance().setCount(getPerformance().getCount() - 1);
                getSender().sendMessage("��b�ѽ���ҡ�6" + getPerformance().getPlayer().getName() + "��b����Ŀ��6" 
                        + getPerformance().getObject() + "��b�еĳͷ���������Ϊ��6" 
                        + Integer.toString(getPerformance().getCount())
                        + "��b������ͷ���������" + (releaseCmds!=null? "��6" 
                        + releaseCmds: "��c��") + "��b�����Զ�ִ��");
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.minus." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
