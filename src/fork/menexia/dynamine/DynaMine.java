//This work is licensed under the Creative Commons Attribution-NoDerivs 3.0 United States License. 
//To view a copy of this license, visit http://creativecommons.org/licenses/by-nd/3.0/us/ 
//or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

// Credit to ChernobylStalker aka chernobyl360 for code on his project "BlackLung."
package fork.menexia.dynamine;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DynaMine extends JavaPlugin {
	public static DynaMine plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	static int COAL_DAMAGE = 0;
	static int FIRE_TICKER = 0;
	static int BOOM_RADIUS = 0;
	static int EXPLOSION_ODDS = 0;
	static String DAMAGE_MESSAGE = "null";
	// variables are mentioned for reference
	
    @Override
	public void onEnable() {
		try {
			File fileconfig = new File(getDataFolder(), "config.yml");
			if (!fileconfig.exists()) {
				getDataFolder().mkdir();
				new File(getDataFolder(), "config.yml");
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
			}
		} catch (Exception e1){
			e1.printStackTrace();
		}
		getVariables();
		PluginDescriptionFile pdf = this.getDescription();
		this.getServer().getPluginManager().registerEvents(new DMBlockListener(this), this);
		this.logger.info( "[DynaMine] version " + pdf.getVersion() + " by MeneXia successfully enabled!" );
	}
	
    @Override
	public void onDisable() {
    	PluginDescriptionFile pdf = this.getDescription();
    	this.logger.info( "[DynaMine] version " + pdf.getVersion() + " by MeneXia successfully enabled!" );
	}
	
	public void getVariables() {
		EXPLOSION_ODDS = this.getConfig().getInt("EXPLOSION_ODDS");
		COAL_DAMAGE = this.getConfig().getInt("COAL_DAMAGE");
		FIRE_TICKER = this.getConfig().getInt("FIRE_TICKER");
		BOOM_RADIUS = this.getConfig().getInt("BOOM_RADIUS");
		DAMAGE_MESSAGE = this.getConfig().getString("DAMAGE_MESSAGE");
	}
	
}