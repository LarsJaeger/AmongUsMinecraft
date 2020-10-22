package one.jgr.amongUs.game;

import one.jgr.amongUs.tasks.Task;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    //game data
    public static ArrayList<Task> activeTasks = new ArrayList<>();
    public static Boolean gameStarted = false;
    public static void addPlayer(Player p) {
        if(!gameStarted) {
            for(PlayerColor c: PlayerColor.values()) {
                if(PlayerColor.getPlayerColor(p) == null) {
                    if (c.getPlayer() == null) {
                        c.setPlayer(p);
                        p.setGameMode(GameMode.ADVENTURE);
                        break;
                    }
                }
            }
            if(PlayerColor.getPlayerColor(p) == null) {
                setSpectator(p);
            }

        }
    }
    public static void setSpectator(Player p) {
        p.getInventory().clear();
        p.setGameMode(GameMode.SPECTATOR);
    }
}
