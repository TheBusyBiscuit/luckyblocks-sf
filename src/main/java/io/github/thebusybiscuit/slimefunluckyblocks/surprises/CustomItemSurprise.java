package io.github.thebusybiscuit.slimefunluckyblocks.surprises;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomItemSurprise implements Surprise {

    private final String name;
    private final LuckLevel luckLevel;
    private final List<ItemStack> items;
    private final List<String> commands;

    public CustomItemSurprise(String name, List<ItemStack> items, List<String> commands, LuckLevel luckLevel) {
        this.name = name;
        this.luckLevel = luckLevel;
        this.items = items;
        this.commands = commands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        if (!items.isEmpty()) {
            for (ItemStack i : items) {
                l.getWorld().dropItemNaturally(l, i);
            }
        }
        if (!commands.isEmpty()) {
            for (String cmd : commands) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), applyPlaceholders(cmd, p, l));
            }
        }
    }
    @Override
    public LuckLevel getLuckLevel() {
        return luckLevel;
    }

    private String applyPlaceholders(String str, Player p, Location l) {
        return str.replace("{player}", p.getName()).replace("{world}", l.getWorld().getName()).replace("{x}", l.getBlockX()+"").replace("{y}", l.getBlockY()+"").replace("{z}", l.getBlockZ()+"");
    }

}
