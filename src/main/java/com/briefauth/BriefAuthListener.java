package com.briefauth;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class BriefAuthListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        LoginData.addPlayerName(e.getPlayer().getName()); // 当玩家登录时 获取玩家的游戏名称并添加进LoginData中 进行判断
    }

    public void onPlayerQuit(PlayerQuitEvent e) {
        LoginData.removePlayerName(e.getPlayer().getName()); // 当玩家退出时 获取玩家的游戏名称并添加进LoginData中 进行判断
    }

    public static  void cancelIfNotLoggedIn(Cancellable e) { // 这里写着 Cancellable，和上面的 List 是一个原理，说到底我们只需要「可以取消」这个功能就可以了，至于到底是哪个类，不重要
        if (e instanceof PlayerEvent) { // instanceof 关键字指示 Java 重新判断左边对象的类型是不是右边的类或者右边类的子类，也就是判断能否进行强制类型转换
            if (LoginData.hasPlayerName(((PlayerEvent) e).getPlayer().getName())) { // if 语句用于看看玩家是不是在限制列表中，(PlayerEvent) e 进行类型转换
                e.setCancelled(true);
            }
        } else if (e instanceof InventoryOpenEvent) { // else if 表示「上一条 if 的条件为假」并且「当前括号中的条件为真」时才执行大括号里面的内容，相当于「如果不是那样，而是这样，就做……」
            LoginData.hasPlayerName(((InventoryOpenEvent) e).getPlayer().getName());// if 语句用于看看玩家是不是在限制列表中，(InventoryOpenEvent) e 进行类型转换
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void restrictMove(PlayerMoveEvent e) { // 移动
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricInteract(PlayerInteractEvent e) {  //交互
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricInteractAtEntity(PlayerInteractAtEntityEvent e) { // 实体交互
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricPortal(PlayerPortalEvent e) { //传送门
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricTeleport(PlayerTeleportEvent e) { //传送
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricOpenInventory(InventoryOpenEvent e) { //打开物品栏
        cancelIfNotLoggedIn(e);
    }

}
