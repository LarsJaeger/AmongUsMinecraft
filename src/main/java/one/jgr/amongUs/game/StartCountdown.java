package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StartCountdown extends Countdown{
    public StartCountdown() {
        super(60);
    }
    public void onCountdown() {
        if(t == 0) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 13, 1);
            }
            Main.sendAll("game_startsNow", Integer.toString(super.t));
        }
        if(t%10 == 0 || t<= 5) {
            Main.sendAll("game_startsIn", Integer.toString(super.t));
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1);
            }
        }
    }
}
