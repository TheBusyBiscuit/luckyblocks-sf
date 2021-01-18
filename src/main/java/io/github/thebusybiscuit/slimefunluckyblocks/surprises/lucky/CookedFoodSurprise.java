package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.FireworkUtils;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class CookedFoodSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Cooked Food";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		FireworkUtils.launchRandom(p, 3);
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_CHICKEN, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_PORKCHOP, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_COD, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_MUTTON, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_RABBIT, 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_SALMON, 4));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
