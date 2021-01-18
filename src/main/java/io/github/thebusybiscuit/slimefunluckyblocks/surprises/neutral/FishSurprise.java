package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.FireworkUtils;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class FishSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Fish";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
	    FireworkUtils.launchRandom(p, 3);
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COD));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.SALMON));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.PUFFERFISH));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.TROPICAL_FISH));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
