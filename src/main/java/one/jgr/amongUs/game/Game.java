package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import one.jgr.amongUs.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
                if(PlayerColor.setPlayerRandom(p)) {
                    preGameInventory(p);
                }
                else {
                    setSpectator(p);
                }
            }
            if(PlayerColor.getPlayerNumber()
                    >= minPlayers) {
                startCountdown.start();
            }
            if(PlayerColor.getPlayerNumber() >= maxPlayers && startCountdown.getT() > 3) {
                startCountdown.set(3);
            }
        } else {
            //TODO teleport Player
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
            if(PlayerColor.getPlayerNumber() < minPlayers) { // stops and resets start countdown if there are not enough players in the lobby
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
    public static void preGameInventory(Player p) {
        // Inventory for non spectators before the game start
        p.getInventory().setItem(0, CustomItem.SELECT_COLOR.getItemStack(p));
    }
    public static void startGame() {
        for(int i = 0; i < impostors; i++) { // sets impostors
            int randomNum = ThreadLocalRandom.current().nextInt(0, PlayerColor.getPlayerNumber() + 1); // generates one random impostor
            int counter = 0;
            for (PlayerColor pc : PlayerColor.values()) {
                if (counter == randomNum) {
                    pc.setImpostor();
                }
                counter ++;
            }
        }
        // displays title screen and tells players who is with them
        //TODO give player blindness effect
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4*20, 200));
            if(PlayerColor.getPlayerColor(p) != null && PlayerColor.getPlayerColor(p).isImpostor()) {
                p.sendTitle(Main.getString(p, "title_impostor"), PlayerColor.getImpostorNames(), 1*20, 2*20, 1*20);
            } else {
                p.sendTitle(Main.getString(p, "title_crewmate"), "", 1*20, 2*20, 1*20);
            }
        }
        //TODO teleport players
        // assign inventories
    }
}
