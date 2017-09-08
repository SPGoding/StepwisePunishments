package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** set ��������� */
public class SubSetCommand extends SubAdminCommand {
    protected SubSetCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ��ȡ��ǰ����������Ƿ���� set ������Ĺ淶 */
    @Override
    public boolean isCorrect() {
        return super.isCorrect(4, "Both");
    }
    
    /** ִ�� set ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            if (getSender().hasPermission("swp.set." + getPerformance().getObject())) {
                getPerformance().setCount(Integer.parseInt(getArgs()[3]));
                getSender().sendMessage("��b�ѽ���ҡ�6" + getPerformance().getPlayer().getName() + "��b����Ŀ��6" 
                        + getPerformance().getObject() + "��b�еĳͷ���������Ϊ��6" 
                        + Integer.toString(getPerformance().getCount())
                        + "��b����Ӧ�ĳͷ�������" + (getPerformance().getCommands()!=null? "��6" 
                        + getPerformance().getCommands(): "��c��"));
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.set." + getPerformance().getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
