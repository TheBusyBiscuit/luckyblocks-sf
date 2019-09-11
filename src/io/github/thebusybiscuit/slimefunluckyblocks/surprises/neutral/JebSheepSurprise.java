package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class JebSheepSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "jeb Sheep";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
		sheep.setCustomName("jeb_");
		sheep.setCustomNameVisible(true);
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
