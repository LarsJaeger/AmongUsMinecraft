package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
        public enum CustomItem {
            //TODO overhaul
            SELECT_COLOR;

            public ItemStack getItemStack(Player p) {
                switch (this) {
                    case SELECT_COLOR:
                        return createItemStack(1, PlayerColor.getPlayerColor(p).getMaterial(), Main.getString(p, PlayerColor.getPlayerColor(p).getNameKey()), Main.getString(p, "item_lore_selectColor"));
                }
                return null;
            }

            public static ItemStack createItemStack(int amount, Material customMaterial, String customItemDisplayName, String customItemLore) {
                ItemStack currentItemStack = new ItemStack(customMaterial);
                ItemMeta currentItemMeta = currentItemStack.getItemMeta();
                assert currentItemMeta != null;
                currentItemMeta.setDisplayName(customItemDisplayName);
                ArrayList<String> lore = new ArrayList<>();
                lore.add(customItemLore);
                currentItemMeta.setLore(lore);
                currentItemStack.setItemMeta(currentItemMeta);
                return currentItemStack;
            }
        }
