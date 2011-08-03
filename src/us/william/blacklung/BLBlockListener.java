package us.william.blacklung;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class BLBlockListener extends BlockListener
{
	public static BlackLung plugin;

	public BLBlockListener(BlackLung instance)
	{
		plugin = instance;
	}
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (((player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.GOLD_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.IRON_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.STONE_PICKAXE) || (event.getPlayer().getItemInHand().getType() != Material.WOOD_PICKAXE)) && 
				(event.getBlock().getType() == Material.COAL_ORE)) {
			player.damage(BlackLung.COAL_DAMAGE);
			player.sendMessage(ChatColor.GRAY + BlackLung.DAMAGE_MESSAGE);
		}
	}
}