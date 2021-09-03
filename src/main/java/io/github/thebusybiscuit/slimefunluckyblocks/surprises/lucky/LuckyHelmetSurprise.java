package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public final class LuckyHelmetSurprise implements Surprise {

    private final ItemStack helmet;

    public LuckyHelmetSurprise() {
        helmet = new CustomItemStack(Material.DIAMOND_HELMET, "&e&lLucky Helmet");
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
        helmet.addUnsafeEnchantment(Enchantment.THORNS, 10);
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    @Override
    public String getName() {
        return "Lucky Helmet";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, helmet.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}
