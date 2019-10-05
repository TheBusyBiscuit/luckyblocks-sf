package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class TamedCatsSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Tamed Cats";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 8; i++) {
			Cat cat = (Cat) l.getWorld().spawnEntity(l.add(random.nextInt(4) - (double) random.nextInt(8), 1, random.nextInt(4) - (double) random.nextInt(8)), EntityType.CAT);
			cat.setAdult();
			cat.setOwner(p);
			cat.setCatType(Type.values()[random.nextInt(Type.values().length)]);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
