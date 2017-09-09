package com.github.spacebang.stepwisepunishments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** minus ��������� */
public class SubMinusCommand extends SubAdminCommand {
    protected SubMinusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ����������ʽ�Ƿ����minus�������Ҫ�� */
    @Override
    public boolean isCorrect() {
        return (super.isCorrect(3, "Both")||super.isCorrect(4, "Both"));
    }
    
    /** ִ�� minus ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            Performance performance = getPerformance();
            if (getSender().hasPermission("swp.minus." + performance.getObject())) {
                performance.release();
                int oldCount = performance.getCount();
                int minusCount = 1;
                if (getArgs().length == 4) {
                    minusCount = Math.abs(Integer.parseInt(getArgs()[3]));
                }
                String releaseCmds = performance.getReleaseCommands().toString();
                performance.setCount(performance.getCount() - minusCount);
                getSender().sendMessage("��b�ѽ���ҡ�6" + performance.getPlayer().getName() 
                        + "��b����Ŀ��6" + performance.getObject() + "��b�е�Υ������ӡ�6" 
                        + Integer.toString(oldCount) + "��b��������6" 
                        + Integer.toString(performance.getCount()) + "��b������ͷ���������" 
                        + (releaseCmds != null? "��6" + releaseCmds: "��c��") + "��b�����Զ�ִ��");
                return true;
            } else {
                getSender().sendMessage("��cû�С�6swp.minus." + performance.getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
