//This work is licensed under the Creative Commons Attribution-NoDerivs 3.0 United States License. 
//To view a copy of this license, visit http://creativecommons.org/licenses/by-nd/3.0/us/ 
//or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

// WANNA USE MY CODE???? GIVE ME CREDIT!
package us.william.blacklung;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BlackLung extends JavaPlugin
{
	private final BLBlockListener BlockListener = new BLBlockListener(this);
	public Configuration config;
	protected static int COAL_DAMAGE = 1; //Damage Player Will Receive when mining COAL!!
	protected static int FIRE_TICKER = 200; //Seconds Player Will Burn
	protected static int BOOM_RADIUS = 2; //Radius Of BOOM!
	protected static String DAMAGE_MESSAGE = "*cough*";//Message They Will Receive!	 
	public static Logger log;

	public void onEnable()
	{
		loadConfig();
		log = Logger.getLogger("Minecraft");
		log.info(getDescription().getName() + " version " + getDescription().getVersion() + " loaded.");

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, this.BlockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.BlockListener, Event.Priority.Normal, this);
	}
	public void onDisable() {
		log.info(getDescription().getName() + " version " + getDescription().getVersion() + " unloaded.");
	}
	public void loadConfig(){
		Configuration config = getConfiguration();
		config.load();
		COAL_DAMAGE = config.getInt("BlackLung.Coal-Damage", COAL_DAMAGE); 
		FIRE_TICKER = config.getInt("BlackLung.Fire-Ticker", FIRE_TICKER); 
		BOOM_RADIUS = config.getInt("BlackLung.Boom-Radius", BOOM_RADIUS);
		DAMAGE_MESSAGE = config.getString("BlackLung.Damage-Message", DAMAGE_MESSAGE);
		config.save(); 
	}
}