package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class VillagersSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Villager Number X";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 8; i++) {
			Villager v = (Villager) l.getWorld().spawnEntity(l, EntityType.VILLAGER);
			v.setAdult();
			v.setCustomName("Villager #" + (6 + random.nextInt(30)));
			v.setCustomNameVisible(true);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
