package one.jgr.amongUs.tasks;

import net.wargearworld.GUI_API.GUI.ChestGUI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Cables extends Task{

    public Cables(Player player) {
        super(player);
    }

    public void startTask() {

    }

    public ChestGUI createInv() {
        ChestGUI cable_gui = new ChestGUI(45, "Cables");
        return cable_gui;
    }
}
