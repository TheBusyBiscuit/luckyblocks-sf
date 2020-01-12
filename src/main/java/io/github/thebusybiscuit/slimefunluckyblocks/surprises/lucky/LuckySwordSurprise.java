package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

public final class LuckySwordSurprise implements Surprise {
	
	private final ItemStack sword;
	
	public LuckySwordSurprise() {
		sword = new CustomItem(Material.GOLDEN_SWORD, "&e&lLucky Sword");
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		sword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
		sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
	}
	
	@Override
	public String getName() {
		return "Lucky Sword";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getWorld().dropItemNaturally(l, sword.clone());
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
