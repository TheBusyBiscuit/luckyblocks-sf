package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class ChargedCreeperSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Charged Creeper";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().spawnEntity(l, EntityType.CREEPER);
		l.getWorld().strikeLightning(l);
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
