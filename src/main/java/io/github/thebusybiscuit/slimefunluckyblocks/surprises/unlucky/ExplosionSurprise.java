package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class ExplosionSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Explosion";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().createExplosion(l, 7F);
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
