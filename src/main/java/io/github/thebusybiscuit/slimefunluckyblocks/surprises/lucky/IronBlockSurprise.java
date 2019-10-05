package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class IronBlockSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Iron Block";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().spawnFallingBlock(l.add(0, 5, 0), Material.IRON_BLOCK.createBlockData());
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
