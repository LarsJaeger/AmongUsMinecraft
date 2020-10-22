package one.jgr.amongUs.game;

import one.jgr.amongUs.main.Main;

public class StartCountdown extends Countdown{
    public StartCountdown() {
        super(60);
    }
    public void onCountdown() {
        if(t%10 == 0 || t<= 5) {
            Main.sendAll("game_startsIn", Integer.toString(super.t));
        }
    }
}
