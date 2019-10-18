package io.github.thebusybiscuit.slimefunluckyblocks;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class WorldGenerator implements Listener {
	
	private SlimefunLuckyBlocks plugin;

	public WorldGenerator(SlimefunLuckyBlocks plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onRandomSpawn(ChunkPopulateEvent e) {
		if (!plugin.getCfg().getStringList("world-blacklist").contains(e.getWorld().getName()) && plugin.getRandom().nextInt(100) < plugin.getCfg().getInt("chance")) {
			int x = e.getChunk().getX() * 16 + plugin.getRandom().nextInt(16);
			int z = e.getChunk().getZ() * 16 + plugin.getRandom().nextInt(16) ;
			int y = e.getWorld().getHighestBlockYAt(x, z);
			
	    	Block current = e.getWorld().getBlockAt(x, y, z);
			if (!current.getType().isSolid() && current.getRelative(BlockFace.DOWN).getType().isSolid()) {
				plugin.spawnLuckyBlock(current);
				if (plugin.getCfg().getBoolean("debug")) {
					plugin.getLogger().info("spawned lucky block at " + current.getX() + " " + current.getY() + " " + current.getZ() + " " + current.getWorld().getName());
				}
			}
		}
	}

}
