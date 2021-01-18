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

public final class LuckyBootsSurprise implements Surprise {

    private final ItemStack boots;

    public LuckyBootsSurprise() {
        boots = new CustomItem(Material.DIAMOND_BOOTS, "&e&lLucky Boots");
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
        boots.addUnsafeEnchantment(Enchantment.THORNS, 10);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    @Override
    public String getName() {
        return "Lucky Boots";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        l.getWorld().dropItemNaturally(l, boots.clone());
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}
