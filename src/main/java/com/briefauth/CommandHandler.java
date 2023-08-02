package com.briefauth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) { // 如果 sender 是 Player 的实例，那么这条命令是玩家发送的，反之则不是
            return false; // 服主也有可能从服务器控制台使用命令，先确认命令来自玩家
        }
        if (!LoginData.hasPlayerName(sender.getName())) {
            sender.sendMessage(ChatColor.GREEN + "你已经登陆了！");
            return true;  // 已经登录了，就没必要验证了
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "必须输入密码！"); // sendMessage 用于向该终端（玩家或控制台）发送消息
            return false; // 参数不完整，拒绝处理
        }
        String pwdConfig = String.join("<space>",args); // 玩家的密码可能含有空格，join 将它们粘在一起，<space> 只是定义的分隔符，换成别的也行
        if (ConfigReader.isPlayerRegistered(sender.getName())) { //已经注册
            if (ConfigReader.verifyPassword(sender.getName(),pwdConfig)) {  //验证密码
                LoginData.removePlayerName(sender.getName()); //解锁玩家
                sender.sendMessage(ChatColor.GREEN + "登陆成功，欢迎回来！");
            } else {
                sender.sendMessage(ChatColor.RED + "密码错误！");
            }
        } else {
            ConfigReader.addPlayer(sender.getName(),pwdConfig); // 玩家没注册，准备注册
            LoginData.removePlayerName(sender.getName());  // 注册玩家
            sender.sendMessage(ChatColor.GREEN + "注册成功！"); // 解锁玩家
        }
        return true;
    }
}
