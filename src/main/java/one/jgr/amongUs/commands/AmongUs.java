package one.jgr.amongUs.commands;

import one.jgr.amongUs.game.PlayerColor;
import one.jgr.amongUs.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AmongUs implements CommandExecutor {
    public Player p;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            p = null;
        }
        switch (args[0]) {
            //TODO TabComplete
            //TODO /au color list
            case "color":
                if(PlayerColor.getPlayerColor(args[1]) != null) {
                    PlayerColor.getPlayerColor(args[1]).setPlayer(p);
                } else {
                    Main.send(p, "color_notFound", args[1]);
                }
        }
        return true;
    }

    public static boolean hasPermission(Player hasPermission_player, boolean message, String... permissions) {
        for (String permission : permissions) {
            if (!(hasPermission_player.hasPermission(permission) || hasPermission_player.isOp())) {
                Main.send(hasPermission_player, "permissionDenied", permission);
                Main.send(null, "permissionDenied", "(" + hasPermission_player.getName() + ", " + permission + ")");
                return false;
            }
        }
        return true;
    }

    public static boolean validPlayer(Player messageReceiver, boolean message, String... isValid_players) {
        for (String isValid_player : isValid_players) {
            if (Bukkit.getPlayer(isValid_player) == null) {
                if (messageReceiver != null) {
                    Main.send(messageReceiver, "invalidPlayer", isValid_player);
                }
                return false;
            }
        }
        return true;
    }
}