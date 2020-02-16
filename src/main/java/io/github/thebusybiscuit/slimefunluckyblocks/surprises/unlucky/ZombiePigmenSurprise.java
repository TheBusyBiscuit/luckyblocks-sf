package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class ZombiePigmenSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Angry Zombie Pigmen";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 4; i++) {
			PigZombie pigman = (PigZombie) l.getWorld().spawnEntity(l.add(random.nextInt(4) - (double) random.nextInt(8), 1, random.nextInt(4) - (double) random.nextInt(8)), EntityType.PIG_ZOMBIE);
			pigman.setAngry(true);
			pigman.setTarget(p);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
