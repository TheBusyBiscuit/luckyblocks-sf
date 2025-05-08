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

public final class LuckyChestplateSurprise implements Surprise {

    private final ItemStack chestplate;

    public LuckyChestplateSurprise() {
        chestplate = CustomItemStack.create(Material.DIAMOND_CHESTPLATE, "&e&lLucky Chestplate");
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION, 10);
        chestplate.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 10);
        chestplate.addUnsafeEnchantment(Enchantment.BLAST_PROTECTION, 5);
        chestplate.addUnsafeEnchantment(Enchantment.THORNS, 10);
        chestplate.addUnsafeEnchantment(Enchantment.UNBREAKING, 10);
    }

    @Override
    public String getName() {
        return "Lucky Chestplate";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, chestplate.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}
