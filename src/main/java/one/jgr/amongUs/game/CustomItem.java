package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
        public enum CustomItem {
            //TODO overhaul
            CHALLENGE,
            INVITE,
            STATS;

            public ItemStack getItemStack(String languageCode) {
                switch (this) {
                    case CHALLENGE:
                        return createItemStack(1, Material.GOLDEN_SWORD, Main.getString(languageCode, "item_challenge"), Main.getString(languageCode, "item_lore_challenge"));
                    case INVITE:
                        return createItemStack(1, Material.LEAD, Main.getString(languageCode, "item_invite"), Main.getString(languageCode, "item_lore_invite"));
                    case STATS:
                        return createItemStack(1, Material.MAP, Main.getString(languageCode, "item_stats"), Main.getString(languageCode, "item_lore_stats"));
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
