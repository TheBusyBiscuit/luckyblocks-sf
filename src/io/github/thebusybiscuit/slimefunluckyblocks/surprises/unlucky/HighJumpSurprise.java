package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class HighJumpSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Up up and away";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&bUp up and away!"), 10, 20, 10);
		p.setVelocity(new Vector(0, 2.75, 0));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
