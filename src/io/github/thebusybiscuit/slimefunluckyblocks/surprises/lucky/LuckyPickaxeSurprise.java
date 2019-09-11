package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

public final class LuckyPickaxeSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Lucky Sword";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, new CustomItem(Material.GOLDEN_PICKAXE, "&e&lLucky Pickaxe", new String[] {"DIG_SPEED-10", "LOOT_BONUS_BLOCKS-10", "DURABILITY-10"}, 0));
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
