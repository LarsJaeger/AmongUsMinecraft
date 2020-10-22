package one.jgr.amongUs.game;

import one.jgr.amongUs.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    //game config
    public static int minPlayers; // minimum number of players to start a game
    public static int maxPlayers; // maximum number of players to start a game
    public static int impostors; // number of impostors
    public static int emergencyMeetings; // number of emergency meetings per person
    public static int emergencyCooldown; // cooldown time of the emergency meeting after start or meeting
    public static int discussionTime; // time to discuss in meetings
    public static int votingTime; // time to vote in meetings
    public static int playerSpeed; // possible values: 0;1;2
    public static int crewmateVision; // when lights are off
    public static int impostorVision; // when lights are off
    public static int killCooldown; // cooldown between kills
    public static int commonTasks; // tasks that everyone has to fulfill
    public static int shortTasks; // single step tasks
    public static int longTasks; // multi step tasks
    //game data
    public static ArrayList<Task> activeTasks = new ArrayList<>();
    public static Boolean gameStarted = false;
    public static Countdown startCountdown = new StartCountdown();

    public static void addPlayer(Player p) {
        if(!gameStarted) {
            if(PlayerColor.getPlayerColor(p) == null) {
                if(PlayerColor.setPlayerRandom(p));
                else {
                    setSpectator(p);
                }
            }
            if(Bukkit.getServer().getOnlinePlayers().size() >= minPlayers) {
                startCountdown.start();
            }
            if(Bukkit.getServer().getOnlinePlayers().size() >= maxPlayers && startCountdown.getT() > 3) {
                startCountdown.set(3);
            }
        } else {
            p.setGameMode(GameMode.SPECTATOR);
        }
    }
    public static void removePlayer(Player p) {
        p.getInventory().clear();
        p.setGameMode(GameMode.SPECTATOR);
        PlayerColor.getPlayerColor(p).unsetPlayer();
        if(!gameStarted) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) { // checks for Players that are spectators so they get the color from the person who left
                if(!player.equals(p)) {
                    if (PlayerColor.getPlayerColor(player) == null) {
                        PlayerColor.setPlayerRandom(player);
                    }
                }
            }
            if(Bukkit.getServer().getOnlinePlayers().size() - 1 < minPlayers) { // stops and resets start countdown if there are not enough players in the lobby
                startCountdown.stop();
                startCountdown.reset();
            }
        }
        else {
            //TODO
        }
    }
    public static void setSpectator(Player p) {
        p.getInventory().clear();
        p.setGameMode(GameMode.SPECTATOR);
    }
}
