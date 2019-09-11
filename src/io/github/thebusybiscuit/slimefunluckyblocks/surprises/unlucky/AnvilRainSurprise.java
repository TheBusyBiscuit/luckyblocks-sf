package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class AnvilRainSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Anvil Rain";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
			for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
				for (int y = p.getLocation().getBlockY() - 1; y <= p.getLocation().getBlockY() + 2; y++) {
					if (y == p.getLocation().getBlockY() - 1) p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
					else if (!(p.getLocation().getBlockX() == x && p.getLocation().getBlockZ() == z)) p.getWorld().getBlockAt(x, y, z).setType(Material.IRON_BARS);
				}
			}
		}
		p.getWorld().spawnFallingBlock(p.getLocation().add(0, 16, 0), Material.ANVIL.createBlockData());
		p.getWorld().spawnFallingBlock(p.getLocation().add(0, 24, 0), Material.ANVIL.createBlockData());
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
