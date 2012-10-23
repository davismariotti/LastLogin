package me.gomeow.lastlogin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class lastlogin extends JavaPlugin implements Listener {

	@Override
public void onEnable() {
	//public final Logger logger = Logger.getLogger("Minecraft");
		saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("lastlogin")) {
			String lastlogin = this.getConfig().getString("Last");
			if(lastlogin == null) {
				sender.sendMessage(ChatColor.RED + "You just got this plugin, no one has logged out yet!");
			}
			else {
				this.getServer().dispatchCommand(sender, "seen " + lastlogin);
			}
		}
		return false;
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		Player target = (Player) event.getPlayer();
		this.getConfig().set("Last", target.getName().toLowerCase());
		saveConfig();
	}
}
