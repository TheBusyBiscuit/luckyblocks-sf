package io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class IronGolemsSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Iron Golem Invasion";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 12; i++) {
			IronGolem golem = (IronGolem) l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
			golem.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 999999999, 3));
			golem.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
			golem.setTarget(p);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.PANDORA;
	}

}
