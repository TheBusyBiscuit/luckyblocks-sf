package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class EnclosedWaterSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Enclosed Water Pool";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
			for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
				for (int y = p.getLocation().getBlockY() - 1; y <= p.getLocation().getBlockY() + 2; y++) {
					if (y == p.getLocation().getBlockY() - 1 || y == p.getLocation().getBlockY() + 2) p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
					else if (p.getLocation().getBlockX() == x && p.getLocation().getBlockZ() == z) {
						p.getWorld().getBlockAt(x, y, z).setType(Material.WATER);
					}
					else p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
				}
			}
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
