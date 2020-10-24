package one.jgr.amongUs.game;

import one.jgr.amongUs.game.venting.Vent;
import one.jgr.amongUs.game.venting.VentSystem;
import org.bukkit.*;

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
    private Location lobbySpawn = w.getSpawnLocation();
    private ArrayList<Location> gameSpawns = new ArrayList<>();
    private ArrayList<VentSystem> ventsystems;

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
                    if(w.getBlockAt(x,y,z).getType().equals(Material.IRON_TRAPDOOR) && w.getBlockAt(x,y-1,z).getType().equals(Material.COAL_BLOCK)) {
                        // if there is a trapdoor with a coalblock beneath, with a ventSystemBlock beneath
                        new Vent(new Location(w, x, y, z));
                    }
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.DIAMOND_BLOCK)) {
                        lobbySpawn = new Location(w, x, y+1, z);
                    }
                    if(w.getBlockAt(x,y,z).getType().equals(Material.GOLD_BLOCK) && w.getBlockAt(x,y-1,z).getType().equals(Material.OBSIDIAN)) {
                        gameSpawns.add(new Location(w, x, y+1, z));
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
}
