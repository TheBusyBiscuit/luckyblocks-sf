package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

public final class LuckyLeggingsSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Lucky Leggings";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, new CustomItem(Material.DIAMOND_LEGGINGS, "&e&lLucky Leggings", new String[] {"PROTECTION_ENVIRONMENTAL-10", "PROTECTION_PROJECTILE-10", "PROTECTION_EXPLOSIONS-10", "THORNS-10", "DURABILITY-10"}, 0));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
