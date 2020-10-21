package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public enum PlayerColor {
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

    private Player colorPlayer;

    public void setPlayer(Player p) {
        if(this.colorPlayer == null) {
            if(this.getPlayerColor(p) != null) {
                this.getPlayerColor(p).setPlayer(null); // resets the previous color of player p so it is no longer taken
            }
            setColoredArmor(p, this.getColor());
            this.colorPlayer = p;
            Main.send(p, "color_new", Main.getString(p, this.getName()));

        } else {
            Main.send(p,"color_alreadyTaken", Main.getString(p, this.getName()), this.colorPlayer.getName());
        }
    }
    public Player getPlayer() {
        return colorPlayer;
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
    public org.bukkit.Color getColor() {
        switch (this){
            case RED:
                return org.bukkit.Color.fromRGB(197, 17, 17);
            case BLUE:
                return org.bukkit.Color.fromRGB(19, 46, 209);
            case GREEN:
                return org.bukkit.Color.fromRGB(17, 127,45);
            case PINK:
                return org.bukkit.Color.fromRGB(237,84,186);
            case ORANGE:
                return org.bukkit.Color.fromRGB(239,125,13);
            case YELLOW:
                return org.bukkit.Color.fromRGB(245,245,87);
            case BLACK:
                return org.bukkit.Color.fromRGB(63,71,78);
            case WHITE:
                return org.bukkit.Color.fromRGB(214,224,240);
            case PURPLE:
                return org.bukkit.Color.fromRGB(107,47,187);
            case BROWN:
                return org.bukkit.Color.fromRGB(113,73,30);
            case CYAN:
                return org.bukkit.Color.fromRGB(56,254,220);
            case LIME:
                return org.bukkit.Color.fromRGB(80,239,57);
        }
        return null;
    }
    private void setColoredArmor(Player p, org.bukkit.Color c) {
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
    public static PlayerColor getPlayerColor(Player p) {
        for (PlayerColor c: PlayerColor.values()) {
            if(c.getPlayer() != null && c.getPlayer().equals(p)) return c;
        }
        return null;
    }
    public static PlayerColor getPlayerColor(String name) {
        for (PlayerColor c: PlayerColor.values()) {
            if(c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }
}
