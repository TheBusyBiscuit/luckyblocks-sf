package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class FlyingTNTSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Flying TNT";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 6; i++) {
			Bat bat = (Bat) l.getWorld().spawnEntity(l, EntityType.BAT);
			bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
			bat.getPassengers().add(l.getWorld().spawnEntity(l, EntityType.TNT));
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
