package one.jgr.amongUs.game;

import net.minecraft.server.v1_15_R1.BlockPosition;
import one.jgr.amongUs.game.venting.Vent;
import one.jgr.amongUs.game.venting.VentSystem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Map {
    THE_SKELD("theSkeld", 0, 0, 0, 100, 100, 100),
    POLUS("polus", 0, 0, 0, 100, 100, 100),
    MIRA_HQ("miraHq", 0, 0, 0, 100, 100, 100);
    private String name;
    // locations
    private World w;
    private Location mapBorder1;
    private Location mapBorder2;
    private Location lobbySpawn;
    private Location lights;
    private Location emergencyButton;
    private ArrayList<Location> gameSpawns = new ArrayList<>();

    Map (String name, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.name = name;
        WorldCreator wc = new WorldCreator(name);
        wc.type(WorldType.NORMAL);
        World w = Bukkit.getServer().createWorld(wc);
        this.mapBorder1 = new Location(w, getSmallest(x1, x2), getSmallest(y1,y2), getSmallest(z1, z2));
        this.mapBorder2 = new Location(w, getBiggest(x1, x2), getBiggest(y1, y2), getBiggest(z1, z2));
        setLocations();

    }
    private void setLocations() {
        // goes through every block inside the map borders and checks for block patterns
        for(int x = mapBorder1.getBlockX(); x <= mapBorder2.getBlockX(); x++) {
            for(int y = mapBorder1.getBlockY(); y <= mapBorder2.getBlockY(); y++) {
                for(int z = mapBorder1.getBlockZ(); z <= mapBorder2.getBlockZ(); z++) {
                    // initialising vents
                    if(w.getBlockAt(x,y,z).getType().equals(Material.IRON_TRAPDOOR) && w.getBlockAt(x,y-1,z).getType().equals(Material.COAL_BLOCK)) {
                        // if there is a trapdoor with a coalblock beneath, with a ventSystemBlock beneath
                        new Vent(new Location(w, x, y, z));
                    }
                    // initialising lobby spawn
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.DIAMOND_BLOCK)) {
                        lobbySpawn = new Location(w, x, y+1, z);
                    }
                    // initialising gamespawns
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.OBSIDIAN)) {
                        gameSpawns.add(new Location(w, x, y+1, z));
                    }
                    // initialising lights
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.REDSTONE_BLOCK)) {
                        lights = new Location(w, x, y+1, z);
                    }
                    // initialising emergency button
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.STONE_BUTTON)) {
                        emergencyButton = new Location(w, x, y+1, z);
                    }
                    // if needed add visual task locations, e. g. scanner
                }
            }
        }
    }
    private int getBiggest(int a, int b) {
        if(a>b) {
            return a;
        } else {
            return b;
        }
    }
    private int getSmallest(int a, int b) {
        if(a>b) {
            return a;
        } else {
            return b;
        }
    }
    public void toggleLights(Boolean isOn) {
        AnaloguePowerable lights_redstoneWire = (AnaloguePowerable) lights.getBlock().getBlockData();
        if (isOn == true) {
            lights_redstoneWire.setPower(15);
        } else {
            lights_redstoneWire.setPower(0);
        }
        applyPhysics(lights.getBlock());
    }
    private static void applyPhysics(Block block) {
        ((CraftWorld) block.getWorld()).getHandle().applyPhysics(
                new BlockPosition(block.getX(), block.getY(), block.getZ()), ((CraftBlock) block).getNMS().getBlock());
    }
    public void teleportPlayers() {
        int counter = 0;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(PlayerColor.getPlayerColor(p) != null) {
                // teleports player on one game spawn each
                p.teleport(this.gameSpawns.get(counter));
                // makes player face the emergency button
                p.teleport(lookAt(p.getLocation(), emergencyButton));
                counter ++;
            }
        }
    }
    private Location lookAt(Location loc, Location lookat) {
        //Clone the loc to prevent applied changes to the input loc
        loc = loc.clone();

        // Values of change in distance (make it relative)
        double dx = lookat.getX() - loc.getX();
        double dy = lookat.getY() - loc.getY();
        double dz = lookat.getZ() - loc.getZ();

        // Set yaw
        if (dx != 0) {
            // Set yaw start value based on dx
            if (dx < 0) {
                loc.setYaw((float) (1.5 * Math.PI));
            } else {
                loc.setYaw((float) (0.5 * Math.PI));
            }
            loc.setYaw((float) loc.getYaw() - (float) Math.atan(dz / dx));
        } else if (dz < 0) {
            loc.setYaw((float) Math.PI);
        }

        // Get the distance from dx/dz
        double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));

        // Set pitch
        loc.setPitch((float) -Math.atan(dy / dxz));

        // Set values, convert to degrees (invert the yaw since Bukkit uses a different yaw dimension format)
        loc.setYaw(-loc.getYaw() * 180f / (float) Math.PI);
        loc.setPitch(loc.getPitch() * 180f / (float) Math.PI);

        return loc;
    }

}
