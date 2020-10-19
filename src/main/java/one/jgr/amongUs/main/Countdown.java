package one.jgr.amongUs.main;

import org.bukkit.Bukkit;

public class Countdown {
    private int t;
    private int seconds;
    private String message;
    public Countdown(int t, String message) {
        seconds = t;
        this.t = t;
        this.message = message;
    }
    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {

            @Override
            public void run() {
                Main.sendAll(message);
            }
        }, 20, 20);
    }
    public void pause() {

    }
    public void set(int t) {
        this.t = t;
    }
    public void reset() {
        t = seconds;
    }
}
