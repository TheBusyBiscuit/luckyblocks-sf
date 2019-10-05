package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class RainbowSheepSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Mister Rainbow";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		String string = "Mr. Rainbow";
		ChatColor[] colors = new ChatColor[] {ChatColor.AQUA, ChatColor.BLUE, ChatColor.DARK_AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.YELLOW};
		StringBuilder name = new StringBuilder();
		
		for (int i = 0; i < string.length(); i++) {
			name.append(colors[random.nextInt(colors.length)]);
			name.append(string.charAt(i));
		}
		
		for (int i = 0; i < 8; i++) {
			Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
			sheep.setAdult();
			sheep.setColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
			sheep.setCustomName(name.toString());
			sheep.setCustomNameVisible(true);
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
