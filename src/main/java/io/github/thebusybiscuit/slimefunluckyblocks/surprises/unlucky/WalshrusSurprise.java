package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;

public final class WalshrusSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Walshrus";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
		zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
		zombie.setHealth(40D);
		
		zombie.getEquipment().setHelmet(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk2NmYwZWJkNzdmMWJjZDY1NmZhMmRjM2VmMDMwM2UyNmE2YTNkZTQ5OGMzOTk5ZDM5ZmRjYWNjNWY1YWQifX19"));
		zombie.getEquipment().setHelmetDropChance(0F);
		
		zombie.getEquipment().setItemInMainHand(new CustomItem(Material.GOLDEN_SWORD, "&e&lLucky Sword", new String[] {"DAMAGE_ALL-10", "LOOT_BONUS_MOBS-10", "FIRE_ASPECT-5", "DURABILITY-10"}, 0));
		zombie.getEquipment().setItemInMainHandDropChance(0F);
		zombie.setCanPickupItems(false);
		zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&4Walshrus"));
		zombie.setCustomNameVisible(true);
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
