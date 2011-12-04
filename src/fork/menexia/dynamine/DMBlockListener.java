//This work is licensed under the Creative Commons Attribution-NoDerivs 3.0 United States License. 
//To view a copy of this license, visit http://creativecommons.org/licenses/by-nd/3.0/us/ 
//or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

// WANNA USE MY CODE???? GIVE ME CREDIT!
package fork.menexia.dynamine;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class DMBlockListener extends BlockListener{
    public static DynaMine plugin;
    public DMBlockListener(DynaMine instance){
        plugin = instance;
    }
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getBlock().getType() == (Material.COAL_ORE)) {
            Random r = new Random();
            if (r.nextInt(150)==1){
                Player p = event.getPlayer();
                World w = p.getWorld();
                Block b = event.getBlock();
                w.createExplosion(b.getLocation(), DynaMine.BOOM_RADIUS);
                p.setFireTicks(DynaMine.FIRE_TICKER);
            }
        }
    }
    public void onBlockDamage(BlockDamageEvent event){
        Player player = event.getPlayer();
        if (((player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.GOLD_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.IRON_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.STONE_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.WOOD_PICKAXE)) &&
                (event.getBlock().getType() == Material.COAL_ORE)) {
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
            if (player.getInventory().getHelmet().getTypeId() != 298)
                if (player.getInventory().getHelmet().getTypeId() != 302)
                    if (player.getInventory().getHelmet().getTypeId() != 306)
                        if (player.getInventory().getHelmet().getTypeId() != 314)
                            if (player.getInventory().getHelmet().getTypeId() != 310)
                                player.sendMessage(ChatColor.GRAY + DynaMine.DAMAGE_MESSAGE);
            if (player.getInventory().getHelmet().getTypeId() != 298)
                if (player.getInventory().getHelmet().getTypeId() != 302)
                    if (player.getInventory().getHelmet().getTypeId() != 306)
                        if (player.getInventory().getHelmet().getTypeId() != 314)
                            if (player.getInventory().getHelmet().getTypeId() != 310)
                                player.damage(DynaMine.COAL_DAMAGE);
        }
        if (player.getItemInHand().getType() == Material.AIR){
            event.isCancelled();
        }
    }
}