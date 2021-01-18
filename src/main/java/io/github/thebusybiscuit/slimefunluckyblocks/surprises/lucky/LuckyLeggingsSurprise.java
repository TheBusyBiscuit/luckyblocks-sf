package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public final class LuckyLeggingsSurprise implements Surprise {

    private final ItemStack leggings;

    public LuckyLeggingsSurprise() {
        leggings = new CustomItem(Material.DIAMOND_LEGGINGS, "&e&lLucky Leggings");
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
        leggings.addUnsafeEnchantment(Enchantment.THORNS, 10);
        leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    @Override
    public String getName() {
        return "Lucky Leggings";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, leggings.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}
