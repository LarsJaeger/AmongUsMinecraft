package one.jgr.amongUs.listeners;

import one.jgr.amongUs.main.Main;
import one.jgr.amongUs.tasks.Task;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TaskEvents implements Listener {
    // rerouting Events to the right task
    @EventHandler(priority = EventPriority.NORMAL)
    public void inventoryClose(InventoryClickEvent event) {
        for(Task t: Main.activeTasks) {
            if(t.p.equals(event.getWhoClicked())) {
                inventoryClose(event);
            }
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void inventoryClose(InventoryCloseEvent event) {
        for(Task t: Main.activeTasks) {
            if(t.p.equals(event.getPlayer())) {
                inventoryClose(event);
            }
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void inventoryClose(InventoryDragEvent event) {
        for(Task t: Main.activeTasks) {
            if(t.p.equals(event.getWhoClicked())) {
                inventoryClose(event);
            }
        }
    }
}
