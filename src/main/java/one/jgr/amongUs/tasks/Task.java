package one.jgr.amongUs.tasks;

import one.jgr.amongUs.game.Game;
import one.jgr.amongUs.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class Task {
    public static Player p;
    public Task (Player player) {
        p = player;
        Game.activeTasks.add(this);
        this.startTask();
    }
    public void startTask() {
        return;
    }
    protected void finishTask() {
        //TODO finishTask
        return;
    }
    protected void cancelTask() {
        //TODO cancelTask
        return;
    }
    public void inventoryClickEvent(InventoryClickEvent event) {
        return;
    }
    public void inventoryDragEvent(InventoryDragEvent event) {
        return;
    }
    public void inventoryCloseEvent(InventoryCloseEvent event) {
        return;
    }
}
