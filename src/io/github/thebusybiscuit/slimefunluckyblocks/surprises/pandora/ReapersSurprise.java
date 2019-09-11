package io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

public final class ReapersSurprise implements Surprise {
	
	@Override
	public String getName() {
		return "Reapers";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 4; i++) {
			Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120D);
			zombie.setHealth(120D);
			
			try {
				zombie.getEquipment().setHelmet(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkzN2FmMjYzMzI2ZTJiNDA5MDQyNzFiODMxYzNiMTc2ZWEyMWYwMTg2YmZhZjRlMTZlZWUxZTI4OWRkYWQ4In19fQ=="));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			zombie.getEquipment().setHelmetDropChance(0F);
			zombie.getEquipment().setItemInMainHand(new CustomItem(Material.IRON_HOE, "&e&lLucky Hoe", new String[] {"DAMAGE_ALL-10", "LOOT_BONUS_MOBS-10", "FIRE_ASPECT-5", "DURABILITY-10"}, 0));
			zombie.getEquipment().setItemInMainHandDropChance(0F);
			zombie.setCanPickupItems(false);
			
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 1));
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.PANDORA;
	}

}
