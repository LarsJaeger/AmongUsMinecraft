package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Bukkit;

public abstract class Countdown {
    protected int t; //current time
    protected int dt; //time to pass between actions
    protected int seconds; // time
    protected int taskId;
    public Countdown(int t) {
        seconds = t;
        this.t = t;
    }
    protected void start() {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), this::countdown, 20, 20);
    }
    protected void pause() {
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }
    protected void set(int t) {
        this.t = t;
    }
    protected void reset() {
        t = seconds;
    }
    protected void countdown() {
        t--;
        onCountdown();
    }
    public void onCountdown() {
        // executed every time
    }
}
