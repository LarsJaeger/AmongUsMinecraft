package one.jgr.amongUs.listeners;

import one.jgr.amongUs.game.Game;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuits implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuits(PlayerQuitEvent event) {
        Game.removePlayer(event.getPlayer());
    }
}