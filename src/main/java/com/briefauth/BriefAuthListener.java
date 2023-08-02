package com.briefauth;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class BriefAuthListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        LoginData.addPlayerName(e.getPlayer().getName()); // ����ҵ�¼ʱ ��ȡ��ҵ���Ϸ���Ʋ���ӽ�LoginData�� �����ж�
    }

    public void onPlayerQuit(PlayerQuitEvent e) {
        LoginData.removePlayerName(e.getPlayer().getName()); // ������˳�ʱ ��ȡ��ҵ���Ϸ���Ʋ���ӽ�LoginData�� �����ж�
    }

    public static  void cancelIfNotLoggedIn(Cancellable e) { // ����д�� Cancellable��������� List ��һ��ԭ��˵��������ֻ��Ҫ������ȡ����������ܾͿ����ˣ����ڵ������ĸ��࣬����Ҫ
        if (e instanceof PlayerEvent) { // instanceof �ؼ���ָʾ Java �����ж���߶���������ǲ����ұߵ�������ұ�������࣬Ҳ�����ж��ܷ����ǿ������ת��
            if (LoginData.hasPlayerName(((PlayerEvent) e).getPlayer().getName())) { // if ������ڿ�������ǲ����������б��У�(PlayerEvent) e ��������ת��
                e.setCancelled(true);
            }
        } else if (e instanceof InventoryOpenEvent) { // else if ��ʾ����һ�� if ������Ϊ�١����ҡ���ǰ�����е�����Ϊ�桹ʱ��ִ�д�������������ݣ��൱�ڡ����������������������������������
            LoginData.hasPlayerName(((InventoryOpenEvent) e).getPlayer().getName());// if ������ڿ�������ǲ����������б��У�(InventoryOpenEvent) e ��������ת��
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void restrictMove(PlayerMoveEvent e) { // �ƶ�
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricInteract(PlayerInteractEvent e) {  //����
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricInteractAtEntity(PlayerInteractAtEntityEvent e) { // ʵ�彻��
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricPortal(PlayerPortalEvent e) { //������
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricTeleport(PlayerTeleportEvent e) { //����
        cancelIfNotLoggedIn(e);
    }

    @EventHandler
    public void restricOpenInventory(InventoryOpenEvent e) { //����Ʒ��
        cancelIfNotLoggedIn(e);
    }

}
