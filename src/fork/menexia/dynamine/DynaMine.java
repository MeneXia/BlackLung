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
// WILL DELETE	protected static int COAL_DAMAGE = 1; //Damage Player Will Receive when mining COAL!!
// WILL DELETE	protected static int FIRE_TICKER = 200; //Seconds Player Will Burn
// WILL DELETE	protected static int BOOM_RADIUS = 2; //Radius Of BOOM!
// WILL DELETE	protected static String DAMAGE_MESSAGE = "*cough*";//Message They Will Receive!	 
	public final Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		try {
			File fileconfig = new File(getDataFolder(), "config.yml");
			if (!fileconfig.exists()) {
				config = getConfig();
				getDataFolder().mkdir();
				new File(getDataFolder(), "config.yml");
				loadConfiguration();
			} saveConfig();
		} catch (Exception e1){
			e1.printStackTrace();
		}
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info( pdf.getName() + " version " + pdf.getVersion() + " by MeneXia is enabled!" );
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, this.BlockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.BlockListener, Event.Priority.Normal, this);
	}
	
	public void onDisable() {
		this.logger.info("DynaMine disabled!");
	}
	
	public void loadConfiguration() {
		config.addDefault("COAL_DAMAGE", 1);
		config.addDefault("FIRE_TICKER", 200);
		config.addDefault("BOOM_RADIUS", 2);
		config.addDefault("DAMAGE_MESSAGE", "*cough*");
		config.options().copyDefaults(true);
		this.saveConfig();
	}
	
	
}