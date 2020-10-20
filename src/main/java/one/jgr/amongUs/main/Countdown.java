package one.jgr.amongUs.main;

import org.bukkit.Bukkit;

public abstract class Countdown {
    private int t; //current time
    private int dt; //time to pass between actions
    private int seconds; // time
    private int taskId;
    public Countdown(int t) {
        seconds = t;
        this.t = t;
    }
    public void start() {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), this::countdown, 20, 20);
    }
    public void pause() {
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }
    public void set(int t) {
        this.t = t;
    }
    public void reset() {
        t = seconds;
    }
    private void countdown() {
        t--;
        onCountdown();
    }
    public void onCountdown() {
        // executed every time
    }
}
