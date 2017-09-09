package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SubSeeCommand extends SubAdminCommand{
    protected SubSeeCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ��ȡ��ǰ����������Ƿ���϶��� see ������Ĺ淶 */
    public boolean isCorrect() {
        return super.isCorrect(3, "Both");
    }
    
    /** ִ�� see ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.see." + getPerformance().getObject())) {
                getSender().sendMessage("��b��ҡ�6" + getPerformance().getPlayer().getName() + "��b����Ŀ��6" 
                        + getPerformance().getObject() + "��b�е�Υ�����Ϊ��6" 
                        + Integer.toString(getPerformance().getCount()));
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.see." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
