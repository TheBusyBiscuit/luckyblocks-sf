package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class TamedDogsSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Tamed Dogs";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 8; i++) {
			Wolf dog = (Wolf) l.getWorld().spawnEntity(l.add(random.nextInt(4) - random.nextInt(8), 1, random.nextInt(4) - random.nextInt(8)), EntityType.WOLF);
			dog.setAdult();
			dog.setCollarColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
			dog.setOwner(p);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
