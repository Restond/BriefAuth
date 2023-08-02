package com.briefauth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) { // ��� sender �� Player ��ʵ������ô������������ҷ��͵ģ���֮����
            return false; // ����Ҳ�п��ܴӷ���������̨ʹ�������ȷ�������������
        }
        if (!LoginData.hasPlayerName(sender.getName())) {
            sender.sendMessage(ChatColor.GREEN + "���Ѿ���½�ˣ�");
            return true;  // �Ѿ���¼�ˣ���û��Ҫ��֤��
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "�����������룡"); // sendMessage ��������նˣ���һ����̨��������Ϣ
            return false; // �������������ܾ�����
        }
        String pwdConfig = String.join("<space>",args); // ��ҵ�������ܺ��пո�join ������ճ��һ��<space> ֻ�Ƕ���ķָ��������ɱ��Ҳ��
        if (ConfigReader.isPlayerRegistered(sender.getName())) { //�Ѿ�ע��
            if (ConfigReader.verifyPassword(sender.getName(),pwdConfig)) {  //��֤����
                LoginData.removePlayerName(sender.getName()); //�������
                sender.sendMessage(ChatColor.GREEN + "��½�ɹ�����ӭ������");
            } else {
                sender.sendMessage(ChatColor.RED + "�������");
            }
        } else {
            ConfigReader.addPlayer(sender.getName(),pwdConfig); // ���ûע�ᣬ׼��ע��
            LoginData.removePlayerName(sender.getName());  // ע�����
            sender.sendMessage(ChatColor.GREEN + "ע��ɹ���"); // �������
        }
        return true;
    }
}
