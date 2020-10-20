package one.jgr.amongUs.game;

import org.apache.commons.codec.binary.Hex;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public enum Color {
    RED, //("color_red", org.bukkit.Color.fromRGB(197, 17, 17)),
    BLUE, //("color_blue", org.bukkit.Color.fromRGB(19, 46, 209)),
    GREEN, //("color_green", org.bukkit.Color.fromRGB(17, 127,45)),
    PINK, //("color_pink", org.bukkit.Color.fromRGB(237,84,186)),
    ORANGE, //("color_orange", org.bukkit.Color.fromRGB(239,125,13)),
    YELLOW, //("color_yellow", org.bukkit.Color.fromRGB(245,245,87)),
    BLACK, //("color_black", org.bukkit.Color.fromRGB(63,71,78)),
    WHITE, //("color_white", org.bukkit.Color.fromRGB(214,224,240)),
    PURPLE, //("color_purple", org.bukkit.Color.fromRGB(107,47,187)),
    BROWN, //("color_brown", org.bukkit.Color.fromRGB(113,73,30)),
    CYAN, //("color_cyan", org.bukkit.Color.fromRGB(56,254,220)),
    LIME; //("color_lime", org.bukkit.Color.fromRGB(80,239,57));

    public void setPlayer(Player p) {
        switch (this){
            case RED:
                setColoredArmor(p, org.bukkit.Color.fromRGB(197, 17, 17));
                break;
            case BLUE:
                setColoredArmor(p, org.bukkit.Color.fromRGB(19, 46, 209));
                break;
            case GREEN:
                setColoredArmor(p, org.bukkit.Color.fromRGB(17, 127,45));
                break;
            case PINK:
                setColoredArmor(p, org.bukkit.Color.fromRGB(237,84,186));
                break;
            case ORANGE:
                setColoredArmor(p, org.bukkit.Color.fromRGB(239,125,13));
                break;
            case YELLOW:
                setColoredArmor(p, org.bukkit.Color.fromRGB(245,245,87));
                break;
            case BLACK:
                setColoredArmor(p, org.bukkit.Color.fromRGB(63,71,78));
                break;
            case WHITE:
                setColoredArmor(p, org.bukkit.Color.fromRGB(214,224,240));
                break;
            case PURPLE:
                setColoredArmor(p, org.bukkit.Color.fromRGB(107,47,187));
                break;
            case BROWN:
                setColoredArmor(p, org.bukkit.Color.fromRGB(113,73,30));
                break;
            case CYAN:
                setColoredArmor(p, org.bukkit.Color.fromRGB(56,254,220));
                break;
            case LIME:
                setColoredArmor(p, org.bukkit.Color.fromRGB(80,239,57));
                break;
        }
    }
    public String getName() {
        switch (this) {
            case RED:
                return "color_red";
            case BLUE:
                return "color_blue";
            case GREEN:
                return "color_green";
            case PINK:
                return "color_pink";
            case ORANGE:
                return "color_orange";
            case YELLOW:
                return "color_yellow";
            case BLACK:
                return "color_black";
            case WHITE:
                return "color_white";
            case PURPLE:
                return "color_purple";
            case BROWN:
                return "color_brown";
            case CYAN:
                return "color_cyan";
            case LIME:
                return "color_lime";
        }
        return null;
    }
    public void setColoredArmor(Player p, org.bukkit.Color c) {
        // helmet
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta)helmet.getItemMeta();
        helmetMeta.setColor(c);
        helmet.setItemMeta(helmetMeta);
        p.getEquipment().setHelmet(helmet);
        // chestplate
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta chestMeta = (LeatherArmorMeta)chest.getItemMeta();
        chestMeta.setColor(c);
        chest.setItemMeta(chestMeta);
        p.getEquipment().setChestplate(chest);
        // leggings
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta)leggings.getItemMeta();
        leggingsMeta.setColor(c);
        leggings.setItemMeta(leggingsMeta);
        p.getEquipment().setLeggings(leggings);
        // boots
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta)boots.getItemMeta();
        bootsMeta.setColor(c);
        boots.setItemMeta(bootsMeta);
        p.getEquipment().setBoots(boots);
    }
}
