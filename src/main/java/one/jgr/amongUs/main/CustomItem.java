package one.jgr.one.main;

import one.jgr.amongUs.main.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
        public enum CustomItem {
            CHALLENGE,
            INVITE,
            STATS;

            public ItemStack getItemStack(String languageCode) {
                switch (this) {
                    case CHALLENGE:
                        return createItemStack(Material.GOLDEN_SWORD, Main.getString(languageCode, "item_challenge"), Main.getString(languageCode, "item_lore_challenge"));
                    case INVITE:
                        return createItemStack(Material.LEAD, Main.getString(languageCode, "item_invite"), Main.getString(languageCode, "item_lore_invite"));
                    case STATS:
                        return createItemStack(Material.MAP, Main.getString(languageCode, "item_stats"), Main.getString(languageCode, "item_lore_stats"));
                }
                return null;
            }

            public static ItemStack createItemStack(Material customMaterial, String customItemDisplayName, String customItemLore) {
                ItemStack currentItemStack = new ItemStack(customMaterial);
                ItemMeta currentItemMeta = currentItemStack.getItemMeta();
                currentItemMeta.setDisplayName(customItemDisplayName);
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(customItemLore);
                currentItemMeta.setLore(lore);
                currentItemStack.setItemMeta(currentItemMeta);
                return currentItemStack;
            }
        }
