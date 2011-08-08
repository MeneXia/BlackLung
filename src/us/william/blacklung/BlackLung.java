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
	protected static int COAL_DAMAGE = 1; //Damage Player Will Receive when mining COAL!
	protected static int RANDOM_BOOM = 1; //higher the # Less the chance of BOOM!
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
		config.load();//This will load the config file, and if it doesn't exist, create a new one without any hassle
		//Now, to load the default values (and set the new values if they've been changed)
		COAL_DAMAGE = config.getInt("BlackLung.Coal-Damage", COAL_DAMAGE); 
		FIRE_TICKER = config.getInt("BlackLung.Fire-Ticker", FIRE_TICKER); 
		RANDOM_BOOM = config.getInt("BlackLung.Random-Boom", RANDOM_BOOM);
		BOOM_RADIUS = config.getInt("BlackLung.Boom-Radius", BOOM_RADIUS);
		DAMAGE_MESSAGE = config.getString("BlackLung.Damage-Message", DAMAGE_MESSAGE);
		config.save(); //save the new configuration file
	}
}