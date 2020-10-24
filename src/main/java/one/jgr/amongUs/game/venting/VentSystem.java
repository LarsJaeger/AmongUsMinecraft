package one.jgr.amongUs.game.venting;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class VentSystem {
    private static ArrayList<VentSystem> ventSystems = new ArrayList<>();
    private ArrayList<Vent> vents = new ArrayList<>();
    final Material systemDefining; // the Block below the Coal block defining to which vent system the vent belongs
    VentSystem(Material systemDefining){
        this.systemDefining = systemDefining;
    }
    void addVent(Vent vent) {
        this.vents.add(vent);
    }
    Material getSystemDefining() {
        return systemDefining;
    }
    static VentSystem getVentSystem(Material systemDefining) {
        for(VentSystem vsys : VentSystem.getVentSystems()) {
            if(vsys.getSystemDefining().equals(systemDefining)) {
                return vsys;
            }
        }
        return null;
    }
    static ArrayList<VentSystem> getVentSystems() {
        return ventSystems;
    }
}
