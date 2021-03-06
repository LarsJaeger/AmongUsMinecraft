package one.jgr.amongUs.main;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.wargearworld.GUI_API.GUI_API;
import one.jgr.amongUs.commands.AmongUs;
import one.jgr.amongUs.commands.AmongUsTabCompletion;
import one.jgr.amongUs.game.Game;
import one.jgr.amongUs.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static boolean enabled = true;
	public static String fallbackLanguageCode = "en";
	// config send
	public static final String langPack_de = "/de_german.properties";
	public static final String langPack_en = "/en_english.properties";
	public static String commandcolor = "§2";
	public static String argumentcolor = "§a";
	public static String textcolor = "§7";
	public static String prefix = "§8[§6Arena§8]§r"; // plugin prefix
	public static HashMap<Player, String> player_language = new HashMap<Player, String>();
	// database config
	public static DbConnection db = new DbConnection();
	public static Boolean dbenabled;
	public static String database;
	public static String host;
	public static String user;
	public static String passwd;
	public FileConfiguration config = null;

	public static Main getMain() {
		return plugin;
	}

	@Override
	public void onEnable() {
		plugin = this;
		this.saveDefaultConfig();
		configure();
		if (enabled) {
			register_Listener();
			new GUI_API(this, null);
			if (dbenabled) {
				new DataSource();
			}
			getCommand("amongus").setExecutor(new AmongUs());
			getCommand("amongus").setTabCompleter(new AmongUsTabCompletion());

			System.out.println("[AmongUs] finished enabling");
		} else {
			System.out.println("[AmongUs] finished, not enabled (see config.yml)");
		}
	}

	@Override
	public void onDisable() {
	}

	public void configure() {
		this.reloadConfig();
		config = this.getConfig();
		enabled = config.getBoolean("enabled");
		fallbackLanguageCode = config.getString("fallbackLanguage");
		// database
		dbenabled = config.getBoolean("database.enabled");
		database = config.getString("database.name");
		host = config.getString("database.host");
		user = config.getString("database.username");
		passwd = config.getString("database.password");
		if (enabled && dbenabled) {
			db.newConnection();
		}
		// game
		Game.minPlayers = config.getInt("game.minPlayers");
		Game.maxPlayers = config.getInt("game.maxPlayers");
		Game.impostors = config.getInt("game.impostors");
		Game.emergencyMeetings = config.getInt("game.emergencyMeetings");
		Game.emergencyCooldown = config.getInt("game.emergencyCooldown");
		Game.discussionTime = config.getInt("game.discussionTime");
		Game.votingTime = config.getInt("game.votingTime");
		Game.playerSpeed = config.getInt("game.playerSpeed");
		Game.crewmateVision = config.getInt("game.crewmateVision");
		Game.impostorVision = config.getInt("game.impostorVision");
		Game.killCooldown = config.getInt("game.killCooldown");
		Game.commonTasks = config.getInt("game.commonTasks");
		Game.shortTasks = config.getInt("game.shortTasks");
		Game.longTasks = config.getInt("game.longTasks");
	}

	public void register_Listener() {
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new PlayerJoins(), this);
		pm.registerEvents(new PlayerQuits(), this);
		pm.registerEvents(new PlayerClicks(), this);
		pm.registerEvents(new TaskEvents(), this);
		pm.registerEvents(new EventsToBeCanceled(), this);
/*
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerRespawn(), this);
		pm.registerEvents(new PlayerJoins(), this);
		pm.registerEvents(new PlayerQuits(), this);
		pm.registerEvents(new EntityDamageByEntity(), this);
		pm.registerEvents(new ClickInInventory(), this);
		pm.registerEvents(new PlayerClicks(), this);
		pm.registerEvents(new ProjectileHits(), this);
		pm.registerEvents(new FloorDamage(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new WaterRemover(), this);*/
	}
	public static void send(Player pe, String... args) {
		String message;
		Properties languagePack = new Properties();

		// if sender is console
		if (pe == null) {
			try {
				languagePack.load(Main.class.getClassLoader().getResourceAsStream(langPack_de));
			} catch (IOException e) {
				e.printStackTrace();
			}
			message = languagePack.getProperty(args[0]);
			if (args.length != 1) {
				for (int i = 1; i < args.length; i++) {
					message = message.replaceFirst("%r", args[i]);
				}
			}
			message = message.replace("'/", commandcolor + "/").replace("'", textcolor + "");
			message = message.replace("<", argumentcolor + "<").replace(">", ">" + textcolor);
			message = message.replace("ï¿½", "");
			System.out.println(prefix + textcolor + " " + message);
			return;
		}
		// loading the right languagePack for players
		if (!player_language.containsKey(pe)) {
			player_language.put(pe, getLanguage(pe));
		}
		if (player_language.get(pe).equalsIgnoreCase("de")) {
			// german Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_de), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (player_language.get(pe).equalsIgnoreCase("en")) {
			// english Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_en), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			pe.sendMessage("Error: unknown language, please report this to an administrator or moderator!");
		}
		message = languagePack.getProperty(args[0]);
		if (message == null) {
			pe.sendMessage("Error, no such message key: '" + args[0] + "'");
			System.out.println("Error, no such message key: '" + args[0] + "'");
		}
		if (args.length != 1) {
			for (int i = 1; i < args.length; i++) {
				message = message.replaceFirst("%r", args[i] + textcolor);
			}
		}
		message = message.replace("'/", commandcolor + "/").replace("'", textcolor + "");
		message = message.replace("<", argumentcolor + "<").replace(">", ">" + textcolor);
		message = message.replace("ï¿½", "");
		pe.sendMessage(prefix + textcolor + " " + message);
	}
	public static void sendAll(String... args) {
		for (Player l : Bukkit.getServer().getOnlinePlayers()) {
			send(l, args);
		}
	}

	public static void sendJSON(Player p, String msg1, String click, String command, String suggest, String hover,
								String msg2) {
		String msg = "{\"text\":\"" + msg1 + "\",\"extra\":[{\"text\":\"" + click + "\",\"clickEvent\":{\"action\":\""
				+ suggest + "\",\"value\":\"" + command
				+ "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"" + hover
				+ "\"}},\"extra\":[{\"text\":\"" + msg2 + "\"}]}]}";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + msg);
	}

	public static String getLanguage(Player p) {
		if(!dbenabled) {
			return fallbackLanguageCode;
		}
		// create HashMap of player and according language to reduce traffic
		Statement stmt = null;
		ResultSet result = null;
		String displayName = "";
		String countrycode = "";
		try {
			stmt = db.getConnection().createStatement();
			result = stmt.executeQuery("SELECT * FROM Player WHERE UUID = '" + p.getUniqueId().toString() + "'");
			result.first(); // <- first entry of the result set
			while (!result.isAfterLast()) {// as long as valid data is in the result set
				displayName = result.getString("name");
				countrycode = result.getString("countrycode");
				result.next(); // go to next line in the customer table
			}
			if (displayName.isEmpty()) {
				System.out.println("Player" + p.getName() + "is not registered.");
			}
			return countrycode;
		} catch (Exception ex) {
			ex.printStackTrace();

			// Fallback to english in case of errors
			return "en";
		}
	}

	public static String getString(Player player, String args) {
		String message = "";
		Properties languagePack = new Properties();
		// loading the right languagePack
		if (!player_language.containsKey(player)) {
			player_language.put(player, getLanguage(player));
		}
		if (player_language.get(player).equalsIgnoreCase("de")) {
			// german Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_de), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (player_language.get(player).equalsIgnoreCase("en")) {
			// english Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_en), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			player.sendMessage("Error: unknown language, please report this to an administrator or moderator!");
		}
		message = languagePack.getProperty(args);
		message = message.replace("ï¿½", "");
		return message;
	}
	public static String getString(String languageCode, String args) {
		String message = "";
		Properties languagePack = new Properties();
		// loading the right languagePack
		if (languageCode.equalsIgnoreCase("de")) {
			// german Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_de), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (languageCode.equalsIgnoreCase("en")) {
			// english Output
			try {
				languagePack.load(new InputStreamReader(Main.class.getResourceAsStream(langPack_en), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Error: unknown language, '" + languageCode + "'");
		}
		message = languagePack.getProperty(args);
		message = message.replace("ï¿½", "");
		return message;
	}
	public static void sendHotBar(Player p, String key) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getString(p, key)));
	}
}