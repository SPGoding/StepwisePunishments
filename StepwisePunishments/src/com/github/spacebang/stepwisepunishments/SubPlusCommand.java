package com.github.spacebang.stepwisepunishments;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/** plus ��������� */
public class SubPlusCommand extends SubAdminCommand{
    protected SubPlusCommand(CommandSender sender, Command cmd, String label, String[] args,
            StepwisePunishments plugin) {
        super(sender, cmd, label, args, plugin);
    }
    
    /** ����������ʽ�Ƿ����plus�������Ҫ�� */
    @Override
    public boolean isCorrect() {
        return (super.isCorrect(3, "Both")||super.isCorrect(4, "Both"));
    }
    
    /** ִ�� plus ������ */
    @Override
    public boolean perform() {
        // ��ʽ��ִ��������
        if (this.isCorrect()) {
            // �Ƿ���Ȩ��
            Performance performance = getPerformance();
            if (getSender().hasPermission("swp.plus." + performance.getObject())) {
                // �Ƿ�����ȴʱ�䣬�Լ�ִ�������޺�����ȴʱ���Ȩ��
                if (getPlugin().getPlayerCooldownMap()
                        .containsKey(performance.getPlayer().getName())) {
                    if (new Date().getTime() - getPlugin().getPlayerCooldownMap().get(performance
                            .getPlayer().getName()) <= getPlugin().getConfig()
                            .getLong("cooldown", 300000L)) {
                        if (new Date().getTime() - getPlugin().getPlayerCooldownMap().get(performance
                                .getPlayer().getName()) <= getPlugin().getConfig()
                                .getLong("cooldown", 300000L)) {
                            if (!getSender().hasPermission("swp.bypassCooldown." + 
                                    performance.getObject())) {
                                    getSender().sendMessage("��cĿǰ������ҡ�6" + 
                                performance.getPlayer().getName() + 
                                "��c�ı�������ȴʱ���У������ͨ����6swp.bypassCooldown." + 
                                performance.getObject() + "��cȨ����������ȴʱ��");
                                return true;
                            }  
                        }
                    }
                }
                    int oldCount = performance.getCount();
                    int plusCount = 1;
                    if (getArgs().length == 4) {
                        plusCount = Math.abs(Integer.parseInt(getArgs()[3]));
                    }
                    performance.setCount(performance.getCount() + plusCount);
                    performance.punish();
                    getSender().sendMessage("��b�ѽ���ҡ�6" + performance.getPlayer().getName()
                            + "��b����Ŀ��6" + performance.getObject() + "��b�е�Υ������ӡ�6" 
                            + Integer.toString(oldCount) + "��b��������6" 
                            + Integer.toString(performance.getCount())
                            + "��b����Ӧ�ĳͷ�������" + (performance.getCommands() != null? "��6" 
                            + performance.getCommands(): "��c��") + "��b�����Զ�ִ��");
                    getPlugin().getPlayerCooldownMap().put(performance.getPlayer().getName(),
                            new Date().getTime());
                    return true;                    
            } else {
                getSender().sendMessage("��cû�С�6swp.plus." + performance.getObject() + "��cȨ��");
                return true;
            }
        }
        return false;
    }
}
