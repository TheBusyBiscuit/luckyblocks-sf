package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class VoidHoleSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Void Hole";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
			for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
				for (int y = p.getLocation().getBlockY() + 1; y >= 0; y--) {
					l.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
				}
			}
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
