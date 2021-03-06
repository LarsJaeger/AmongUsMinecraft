package one.jgr.amongUs.listeners;

import one.jgr.amongUs.game.PlayerColor;
import one.jgr.amongUs.main.Main;
import one.jgr.amongUs.game.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerClicks implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClick(PlayerInteractEvent event) {
        if(!(event.getItem() == null || event.getHand().equals(EquipmentSlot.OFF_HAND))) {
            Player p = event.getPlayer();
            if (event.getItem().equals(CustomItem.SELECT_COLOR.getItemStack(p))) {
                PlayerColor.colorChangeGUI(p);
            }
        }
        event.setCancelled(true);
    }
}