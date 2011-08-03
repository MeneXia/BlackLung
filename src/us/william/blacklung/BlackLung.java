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
	protected static int COAL_DAMAGE = 1; //where 1 is the default value
	protected static String DAMAGE_MESSAGE = "*cough*";//again, default value	 
	public static Logger log;

	public void onEnable()
	{
		loadConfig();
		log = Logger.getLogger("Minecraft");
		log.info(getDescription().getName() + " version " + getDescription().getVersion() + " loaded.");

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.BlockListener, Event.Priority.Normal, this);
	}
	public void onDisable() {
		log.info(getDescription().getName() + " version " + getDescription().getVersion() + " unloaded.");
	}
	public void loadConfig(){
		Configuration config = getConfiguration();
		config.load();//This will load the config file, and if it doesn't exist, create a new one without any hassle
		//Now, to load the default values (and set the new values if they've been changed)
		COAL_DAMAGE = config.getInt("BlackLung.Coal-Damage", COAL_DAMAGE); //the first parameter is just what You want to display as the node in the configuration file
		DAMAGE_MESSAGE = config.getString("BlackLung.Damage-Message", DAMAGE_MESSAGE);
		config.save(); //save the new configuration file
	}
}