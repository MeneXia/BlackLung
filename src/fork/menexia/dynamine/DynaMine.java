//This work is licensed under the Creative Commons Attribution-NoDerivs 3.0 United States License. 
//To view a copy of this license, visit http://creativecommons.org/licenses/by-nd/3.0/us/ 
//or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

// Credit to ChernobylStalker for code on his project "BlackLung."
package fork.menexia.dynamine;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DynaMine extends JavaPlugin {
	public static DynaMine plugin;
	private final DMBlockListener BlockListener = new DMBlockListener(this);
	protected FileConfiguration config; 
	public final Logger logger = Logger.getLogger("Minecraft");
	
	static int COAL_DAMAGE = 0;
	static int FIRE_TICKER = 0;
	static int BOOM_RADIUS = 0;
	static int EXPLOSION_ODDS = 0;
	static String DAMAGE_MESSAGE = "";
	
	public void onEnable() {
		try {
			File fileconfig = new File(getDataFolder(), "config.yml");
			if (!fileconfig.exists()) {
				config = getConfig();
				getDataFolder().mkdir();
				new File(getDataFolder(), "config.yml");
				config.options().copyDefaults(true);
			}
		} catch (Exception e1){
			e1.printStackTrace();
		}
		getVariables();
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info( pdf.getName() + " version " + pdf.getVersion() + " by MeneXia is enabled!" );
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, this.BlockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.BlockListener, Event.Priority.Normal, this);
	}
	
	public void onDisable() {
		saveConfig();
		this.logger.info("DynaMine disabled!");
	}
	
	public void getVariables() {
		EXPLOSION_ODDS = config.getInt("EXPLOSION_ODDS");
		COAL_DAMAGE = config.getInt("COAL_DAMAGE");
		FIRE_TICKER = config.getInt("FIRE_TICKER");
		BOOM_RADIUS = config.getInt("BOOM_RADIUS");
		DAMAGE_MESSAGE = config.getString("DAMGE_MESSAGE");
	}
	
}