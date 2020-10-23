package one.jgr.amongUs.listeners;

import one.jgr.amongUs.game.Game;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsToBeCanceled implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDropsItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
