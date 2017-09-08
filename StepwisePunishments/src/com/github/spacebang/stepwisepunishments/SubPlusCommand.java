package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** plus ��������� */
public class SubPlusCommand extends SubAdminCommand{
    protected SubPlusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ִ�� plus ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.plus." + getPerformance().getObject())) {
                getPerformance().setCount(getPerformance().getCount() + 1);
                getPerformance().punish();
                getSender().sendMessage("��b�ѽ���ҡ�6" + getPerformance().getPlayer().getName() + "��b����Ŀ��6" 
                        + getPerformance().getObject() + "��b�еĳͷ���������Ϊ��6" 
                        + Integer.toString(getPerformance().getCount())
                        + "��b����Ӧ�ĳͷ�������" + (getPerformance().getCommands()!=null? "��6" 
                        + getPerformance().getCommands(): "��c��") + "��b�����Զ�ִ��");
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.plus." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
