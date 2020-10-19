package one.jgr.amongUs.tasks;

import net.wargearworld.GUI_API.GUI.ChestGUI;
import net.wargearworld.GUI_API.Items.DefaultItem;
import net.wargearworld.GUI_API.Items.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Cables extends Task{

    public Cables(Player player) {
        super(player);
    }

    public void startTask() {
        createInv().open(super.p);
    }

    public ChestGUI createInv() {
        ChestGUI cable_gui = new ChestGUI(36, "Cables");
        Item item = new DefaultItem(Material.BLACK_STAINED_GLASS_PANE, "Test");
        Integer i = 0;
        for(i = 0; i < 4; i++) {
            cable_gui.setItem(i*9, item);
            cable_gui.setItem((i+1)*8, item);
        }
        return cable_gui;
    }
}
