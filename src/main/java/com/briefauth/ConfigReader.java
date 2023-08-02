package com.briefauth;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigReader {

    public static FileConfiguration config = BriefAuth.instance.getConfig();

    public static boolean isPlayerRegistered(String playerName) {
        return false;
    }

    public static boolean verifyPassword(String playerName,String password) {
        return password.equals(config.getString(playerName.toLowerCase()));
    }

    public static void addPlayer(String playerName,String password) {
        BriefAuth.instance.getConfig().set(playerName.toLowerCase(),password);
        BriefAuth.instance.saveConfig();
    }

}
