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
            for(Color c: Color.values()) {
                if(Color.getColor(p) == null) {
                    if (c.getPlayer() == null) {
                        c.setPlayer(p);
                        p.setGameMode(GameMode.ADVENTURE);
                        break;
                    }
                }
            }
            if(Color.getColor(p) == null) {
                setSpectator();
            }
        }
    }
    public static void setSpectator() {
        //TODO
    }
}
