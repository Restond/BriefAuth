package com.briefauth;

import java.util.ArrayList;
import java.util.List;

public final class LoginData {
    private static final List<String> RESTRICTS = new ArrayList<>(); // 定义一个存储受限制玩家名称的集合，RESTRICTS 可以看作是一个集合对象

    public static void addPlayerName(String playerNameIn) {
        String convertedName = playerNameIn.toLowerCase(); // toLowerCase 返回一个小写的副本，是 String 类的一个成员方法
        if (!RESTRICTS.contains(convertedName)) { // contains 方法返回一个逻辑值，! 符号把它变为相反的值，因此这个 if 语句只有在 RESTRICTS 中不含 convertedName 时才会执行里面的部分
            RESTRICTS.add(convertedName); // 从 RESTRICTS 集合中加入转换后的玩家名称
        }
    }

    public static void removePlayerName(String playerNameIn) { // 从受限制玩家列表中移除玩家名称的方法
        String convertedName = playerNameIn.toLowerCase(); // 将输入的玩家名称转换为小写，以便进行大小写不敏感的比较
            RESTRICTS.remove(convertedName); // 从 RESTRICTS 集合中移除转换后的玩家名称
    }

    public static boolean hasPlayerName(String playerNameIn) { // 检查受限制玩家列表中是否存在指定玩家名称的方法
        String convertedName = playerNameIn.toLowerCase(); // 将输入的玩家名称转换为小写，以便进行大小写不敏感的比较
        return RESTRICTS.contains(convertedName); // 判断 RESTRICTS 集合是否包含转换后的玩家名称，返回结果表示是否存在该名称
    }

}
