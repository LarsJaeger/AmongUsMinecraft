package net.wargearworld.missileWarsMain.listeners;

import net.wargearworld.missileWarsMain.main.CustomItem;
import net.wargearworld.missileWarsMain.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerClicks implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClick(PlayerInteractEvent event) {
        if(event.getItem() == null || event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        Player p = event.getPlayer();
        if(event.getItem().equals(CustomItem.CHALLENGE.getItemStack(Main.getLanguage(p))));
        else if(event.getItem().equals(CustomItem.INVITE.getItemStack(Main.getLanguage(p))));
        else System.out.println("nichts");
        event.setCancelled(true);
    }
}