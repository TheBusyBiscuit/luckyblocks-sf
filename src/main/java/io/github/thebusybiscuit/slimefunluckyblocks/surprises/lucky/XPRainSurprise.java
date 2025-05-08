package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class XPRainSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "XP Rain";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0, 0.5, 0.15));
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.15, 0.5, 0.15));
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.15, 0.5, 0));

		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0, 0.5, -0.15));
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.15, 0.5, -0.15));
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.15, 0.5, 0));

		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.15, 0.5, 0.15));
		l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.15, 0.5, -0.15));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
