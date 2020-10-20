package one.jgr.amongUs.listeners;

import one.jgr.amongUs.game.Game;
import one.jgr.amongUs.main.Main;
import one.jgr.amongUs.game.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoins implements Listener {
    static Material m_teleporter = Material.NETHER_STAR;

    public static void createInventory(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, CustomItem.CHALLENGE.getItemStack(Main.getLanguage(p)));
        p.getInventory().setItem(3, CustomItem.INVITE.getItemStack(Main.getLanguage(p)));
        p.getInventory().setItem(5, CustomItem.STATS.getItemStack(Main.getLanguage(p)));
        p.getInventory().setHeldItemSlot(4);
    }



    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.setPlayerListName(p.getDisplayName());
        p.setGameMode(GameMode.SPECTATOR);
        p.setExp(0);
        Game.addPlayer(p);
    }
}