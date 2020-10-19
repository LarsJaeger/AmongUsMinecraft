package net.wargearworld.missileWarsMain.listeners;

import net.wargearworld.missileWarsMain.main.CustomItem;
import net.wargearworld.missileWarsMain.main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
//		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
//
//			@Override
//			public void run() {
//				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stoplag -c");
//			}
//		}, 20);

        Player p = event.getPlayer();
        p.setPlayerListName(p.getDisplayName());
        p.setGameMode(GameMode.SURVIVAL);
        createInventory(p);
        p.setExp(0);
    }
}