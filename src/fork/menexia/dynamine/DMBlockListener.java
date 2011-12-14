//This work is licensed under the Creative Commons Attribution-NoDerivs 3.0 United States License. 
//To view a copy of this license, visit http://creativecommons.org/licenses/by-nd/3.0/us/ 
//or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

// Credit to ChernobylStalker for code on his project "BlackLung."
package fork.menexia.dynamine;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class DMBlockListener extends BlockListener {
    public static DynaMine plugin;
    private static Random r = new Random();
    public DMBlockListener(DynaMine instance) {
        plugin = instance;
    }
    
    public void onBlockBreak(BlockBreakEvent event) {
    	if (DynaMine.EXPLOSION_ODDS > 0) {
    		if (event.getBlock().getTypeId() == 16) {
    			final int ch = r.nextInt(DynaMine.EXPLOSION_ODDS) + 1;
    			if (ch == 1) {
                Player p = event.getPlayer();
                World w = p.getWorld();
                Block b = event.getBlock();
                w.createExplosion(b.getLocation(), DynaMine.BOOM_RADIUS);
                p.setFireTicks(DynaMine.FIRE_TICKER);
            	}
            }
        }
    }
    
    public void onBlockDamage(BlockDamageEvent event){
        Player player = event.getPlayer();
        if (((player.getItemInHand().getTypeId() == 278)
        		|| (player.getItemInHand().getTypeId() == 285)
        		|| (player.getItemInHand().getTypeId() == 257) 
        		|| (player.getItemInHand().getTypeId() == 274) 
        		|| (player.getItemInHand().getTypeId() == 270)) 
        		&& (event.getBlock().getTypeId() == 16)) {
            Location loc = player.getLocation();
            World w = loc.getWorld();
            int repeat = 0;
            while(repeat < 1){
                for(double x = (loc.getX() - 3); x <= (loc.getX() + 3); x++){
                    for(double y = (loc.getY() - 3); y <= (loc.getY() + 3); y++){
                        for(double z = (loc.getZ() - 3); z <= (loc.getZ() + 3); z++){
                            w.playEffect(new Location(w, x, y, z), Effect.SMOKE, 1);
                        }
                    }
                }
                repeat++;
            }
            if ((player.getInventory().getHelmet().getTypeId() != 298) 
            		&& (player.getInventory().getHelmet().getTypeId() != 302) 
            		&& (player.getInventory().getHelmet().getTypeId() != 306)
            		&& (player.getInventory().getHelmet().getTypeId() != 314) 
            		&& (player.getInventory().getHelmet().getTypeId() != 310)) {
            	player.sendMessage(ChatColor.GRAY + DynaMine.DAMAGE_MESSAGE);
                player.damage(DynaMine.COAL_DAMAGE);
            }
        }
    }
}