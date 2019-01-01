package me.mrCookieSlime.SlimefunLuckyBlocks;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class Generator implements Listener {
	
	public Generator(SlimefunLuckyBlocks plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRandomSpawn(ChunkPopulateEvent e) {
		if (!SlimefunLuckyBlocks.cfg.getStringList("world-blacklist").contains(e.getWorld().getName())) {
		    if (CSCoreLib.randomizer().nextInt(100) < SlimefunLuckyBlocks.cfg.getInt("chance")) {
		    	int x, z, y;
				x = e.getChunk().getX() * 16 + CSCoreLib.randomizer().nextInt(16);
				z = e.getChunk().getZ() * 16 + CSCoreLib.randomizer().nextInt(16);
				y = e.getWorld().getHighestBlockYAt(x, z) + 1;
				Block current = e.getWorld().getBlockAt(x, y, z);
				if (!current.getType().isSolid() && current.getRelative(BlockFace.DOWN).getType().isSolid()) {
					SlimefunLuckyBlocks.spawnLuckyBlock(current);
				}
		    }
		}
	}
}
