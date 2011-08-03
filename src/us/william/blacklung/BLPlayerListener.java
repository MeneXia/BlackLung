package us.william.blacklung;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class BLPlayerListener extends PlayerListener{
		public static BlackLung plugin;

		public BLPlayerListener(BlackLung instance)
		{
			plugin = instance;
		}
		public void onPlayerInteract(PlayerInteractEvent event){
			Player player = event.getPlayer();
			if (((event.getAction() == Action.LEFT_CLICK_BLOCK)) && 
					(player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.GOLD_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.IRON_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.STONE_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.WOOD_PICKAXE)){ 
				player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 10);
			}
		}
}