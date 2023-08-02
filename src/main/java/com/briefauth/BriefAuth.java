package com.briefauth;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BriefAuth extends JavaPlugin {

    public static JavaPlugin instance;
    public void onLoad() {
        saveDefaultConfig(); // ��������ļ������ڣ�Bukkit �ᱣ��Ĭ�ϵ�����
    }
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BriefAuthListener(),this); // ע���¼����������������ʵ������this ����ע�ᵽ�������
        Objects.requireNonNull(Bukkit.getPluginCommand("login")).setExecutor(new CommandHandler());
        // ע���¼���������ҲҪʵ������requireNonNull �ǲ���Ҫ�ģ�������һ������˻��� Bukkit �����ˣ����ǻ���֪�������������
        instance = this;

    }
    @Override
    public void onDisable() {
        saveConfig(); //��������
    }

}
