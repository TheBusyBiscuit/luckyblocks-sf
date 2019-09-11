package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class GrootSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "I am Groot";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getBlock().getRelative(BlockFace.DOWN).setType(Material.PODZOL);
		l.getBlock().setType(Material.OAK_SAPLING);
		p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&6I am Groot!"), 10, 20, 10);
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
