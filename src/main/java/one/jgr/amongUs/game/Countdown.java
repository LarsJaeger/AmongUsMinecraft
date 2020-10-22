package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Bukkit;

public abstract class Countdown {
    protected int t; //current time
    protected int dt; //time to pass between actions
    protected int seconds; // time
    protected int taskId;
    protected boolean running = false;
    public Countdown(int t) {
        seconds = t;
        this.t = t;
    }
    protected void start() {
            if(running == false) {
                taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), this::countdown, 20, 20);
                running = true;
            }
    }
    protected void pause() {
        if(running = true) {
            Bukkit.getServer().getScheduler().cancelTask(taskId);
            running = false;
        }
    }
    protected void set(int t) {
        this.t = t;
    }
    protected void reset() {
        t = seconds;
    }
    protected void countdown() {
        onCountdown();
        if(t == 0) {
            pause();
            reset();
        }
        t--;
    }
    public void onCountdown() {
        // executed every time
    }
    public int getT() {
        return t;
    }
}
