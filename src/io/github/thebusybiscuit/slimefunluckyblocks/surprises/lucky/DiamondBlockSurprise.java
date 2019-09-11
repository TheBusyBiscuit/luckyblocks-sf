package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class DiamondBlockSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Diamond Block with Lightning";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getBlock().setType(Material.DIAMOND_BLOCK);
		l.getWorld().strikeLightning(l.add(0, 1, 0));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
