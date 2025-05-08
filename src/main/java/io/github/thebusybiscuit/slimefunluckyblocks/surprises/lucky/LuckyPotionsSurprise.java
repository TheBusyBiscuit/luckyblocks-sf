package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefunluckyblocks.SlimefunLuckyBlocks;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class LuckyPotionsSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Lucky Potions";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.FUCHSIA, new PotionEffect(PotionEffectType.REGENERATION, 45, 0), true));
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.SILVER, new PotionEffect(PotionEffectType.SPEED, 180, 0), true));
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.ORANGE, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180, 0), true));
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.RED, new PotionEffect(PotionEffectType.HEALTH_BOOST, 0, 0), true));
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.NAVY, new PotionEffect(PotionEffectType.NIGHT_VISION, 180, 0), true));
		l.getWorld().dropItemNaturally(l, SlimefunLuckyBlocks.createPotion(Color.MAROON, new PotionEffect(PotionEffectType.STRENGTH, 180, 0), true));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
