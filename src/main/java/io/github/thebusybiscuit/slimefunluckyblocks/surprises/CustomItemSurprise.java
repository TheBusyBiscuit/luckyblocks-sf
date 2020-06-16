package io.github.thebusybiscuit.slimefunluckyblocks.surprises;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class CustomItemSurprise implements Surprise {

    private final String name;
    private final LuckLevel luckLevel;
    private final List<ItemStack> items;

    public CustomItemSurprise(String name, List<ItemStack> items, LuckLevel luckLevel) {
        this.name = name;
        this.luckLevel = luckLevel;
        this.items = items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        for (ItemStack i : items) {
            l.getWorld().dropItemNaturally(l, i);
        }
    }

    @Override
    public LuckLevel getLuckLevel() {
        return luckLevel;
    }

}
