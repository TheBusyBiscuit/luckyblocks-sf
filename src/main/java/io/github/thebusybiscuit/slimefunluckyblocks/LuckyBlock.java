package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public class LuckyBlock extends SlimefunItem {

    private Collection<Surprise> surprises;
    private Predicate<Surprise> predicate;

    public LuckyBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(onBlockBreak());
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Random random = ThreadLocalRandom.current();
                List<Surprise> luckySurprises = surprises.stream().filter(predicate).collect(Collectors.toList());

                Player p = e.getPlayer();
                Location loc = e.getBlock().getLocation();
                luckySurprises.get(random.nextInt(luckySurprises.size())).activate(random, p, loc);
            }
        };
    }

    @Override
    public Collection<ItemStack> getDrops() {
        // Disable any drops from Lucky blocks (Air is not dropped but still counts as "overridden drops")
        return Arrays.asList(new ItemStack(Material.AIR));
    }

    public void register(SlimefunLuckyBlocks plugin, Collection<Surprise> surprises, Predicate<Surprise> predicate) {
        this.surprises = surprises;
        this.predicate = predicate;
        super.register(plugin);
    }

}
