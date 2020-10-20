package one.jgr.amongUs.main;

import org.bukkit.Bukkit;

public class Countdown {
    private int t;
    private int seconds;
    private int taskId;
    private Runnable action;
    public Countdown(int t) {
        seconds = t;
        this.t = t;
        this.action = null;
    }
    public Countdown(int t, Runnable action) {
        seconds = t;
        this.t = t;
        this.action = action;
    }
    public void start() {
        if(action == null) {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), this::onCountdown, 20, 20);
        } else {
            taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), action, 20, 20);
        }
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
    public void onCountdown() {
    }
}
