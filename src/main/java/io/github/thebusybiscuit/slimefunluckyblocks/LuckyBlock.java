package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class LuckyBlock extends SlimefunItem {
	
	private Collection<Surprise> surprises;
	private Predicate<Surprise> predicate;

	public LuckyBlock(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
		
		registerBlockHandler(getID(), (p, b, tool, reason) -> {
			BlockStorage.clearBlockInfo(b);
			b.setType(Material.AIR);
			
			if (p != null) {
				Random random = ThreadLocalRandom.current();
				List<Surprise> luckySurprises = surprises.stream().filter(predicate).collect(Collectors.toList());
				luckySurprises.get(random.nextInt(luckySurprises.size())).activate(random, p, b.getLocation());
			}
			return true;
		});
	}
	
	public void register(SlimefunLuckyBlocks plugin, Collection<Surprise> surprises, Predicate<Surprise> predicate) {
		this.surprises = surprises;
		this.predicate = predicate;
		super.register(plugin);
	}

}
