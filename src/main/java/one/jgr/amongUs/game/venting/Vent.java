package one.jgr.amongUs.game.venting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Vent {
    private Location loc;
    private VentSystem system;
    public Vent(Location loc, VentSystem system) {
        this.loc = loc;
        this.system = system;
    }
    public Vent(Location loc) {
        // adds vent to vent system if exists else creates new vent system and adds vent
        Material currentSystemDefining = loc.getWorld().getBlockAt(loc.getBlockX(),loc.getBlockY() - 2, loc.getBlockZ()).getType();
        if(VentSystem.getVentSystem(currentSystemDefining) == null) {
            this.system = new VentSystem(loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ()).getType());
        } else {
            this.loc = loc;
            this.system = VentSystem.getVentSystem(currentSystemDefining);
        }
        this.system.addVent(this);
    }
    protected void use(Player p) {
        //TODO
    }
}
